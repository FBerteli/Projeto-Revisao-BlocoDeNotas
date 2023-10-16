package org.Main;

import Veiculo.*;
import com.google.maps.*;
import com.google.maps.model.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class Main {
    public static void main(String[] args) {
        // Inicialização do contexto da API do Google Maps com a chave de autenticação
        GeoApiContext contexto = new GeoApiContext.Builder().apiKey("AIzaSyDbyxgnL0gIjRLqjZFImKWlQHLgF2D08ms").build();

        try {
            // Lê os nomes das cidades a partir de um arquivo CSV
            String[] cidadesDisponíveis = lerNomesCidadesDoCSV("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");

            // Exibe as cidades disponíveis para o usuário
            System.out.println("Cidades disponíveis:");
            for (String cidade : cidadesDisponíveis) {
                System.out.println(cidade);
            }

            Scanner scanner = new Scanner(System.in);

            // Solicita ao usuário que selecione as cidades de origem e destino
            String cidadeOrigem;
            String cidadeDestino;

            while (true) {
                System.out.print("Selecione a primeira cidade: ");
                cidadeOrigem = scanner.nextLine();
                String cidadeOrigemTeste = cidadeOrigem.toUpperCase();
                if (verificaLista(cidadeOrigemTeste, cidadesDisponíveis)) {
                    break; // Sai do loop se a cidade estiver na lista
                } else {
                    System.out.println("A cidade não está na lista. Por favor, selecione uma cidade válida.");
                }
            }

            while (true) {
                System.out.print("Selecione a segunda cidade: ");
                cidadeDestino = scanner.nextLine();
                String cidadeDestinoTeste = cidadeDestino.toUpperCase();
                if (verificaLista(cidadeDestinoTeste, cidadesDisponíveis) && !cidadeDestinoTeste.equalsIgnoreCase(cidadeOrigem)) {
                    break; // Sai do loop se a cidade estiver na lista
                } else {
                    if (!verificaLista(cidadeDestino, cidadesDisponíveis)) {
                        System.out.println("A cidade não está na lista. Por favor, verifique se escreveu corretamente ou se a cidade está na lista");
                    } else {
                        System.out.println("A cidade de destino não pode ser igual à cidade de origem. Por favor, selecione outra cidade.");
                    }
                }
            }

            // Cria uma requisição à API do Google Maps para calcular a distância entre as
            // cidades
            
            String cidadeOrigemUpper = cidadeOrigem.toUpperCase();
            String cidadeDestinoUpper = cidadeDestino.toUpperCase();

            DistanceMatrixApiRequest requisicao = DistanceMatrixApi.newRequest(contexto);
            DistanceMatrix matrizDistancia = requisicao.origins(cidadeOrigemUpper)
                    .destinations(cidadeDestinoUpper)
                    .await();

            // Obtém a distância entre as cidades e a exibe de forma legível
            Distance distancia = matrizDistancia.rows[0].elements[0].distance;
            System.out.println("Distância entre " + cidadeOrigem + " e " + cidadeDestino + ": " + distancia.humanReadable);

            System.out.println("Agora, vamos calcular o custo da viagem:");

            // Solicita ao usuário que selecione o tamanho do caminhão
            System.out.println("Selecione o tamanho do caminhão: ");
            System.out.println("1 - Pequeno");
            System.out.println("2 - Médio");
            System.out.println("3 - Grande");
            int opcaoTamanho = scanner.nextInt();

            TamanhoVeiculo tamanhoVeiculoSelecionado = TamanhoVeiculo.PEQUENO; // Tamanho padrão (pequeno)

            switch (opcaoTamanho) {
                case 1:
                    tamanhoVeiculoSelecionado = TamanhoVeiculo.PEQUENO;
                    break;
                case 2:
                    tamanhoVeiculoSelecionado = TamanhoVeiculo.MEDIO;
                    break;
                case 3:
                    tamanhoVeiculoSelecionado = TamanhoVeiculo.GRANDE;
                    break;
                default:
                    System.out.println("Opção inválida. Usaeremos caminhão pequeno por padrão.");
            }

            // Converte a distância da viagem de metros para quilômetros
            double distanciaViagem = distancia.inMeters / 1000.0;

            // Cria um objeto de caminhão com base no tamanho selecionado
            Caminhao caminhao = criarVeiculo(tamanhoVeiculoSelecionado);

            // Calcula o custo da viagem
            double custo = caminhao.getPrecoPorKm() * distanciaViagem;

            // Formata o custo como valor em Reais (R$) com duas casas decimais
            DecimalFormat df = new DecimalFormat("###,###.00");
            String valorFormatado = "R$" + df.format(custo);

            // Exibe o custo da viagem com o tamanho do caminhão selecionado
            System.out.println("Custo da viagem com caminhão " + tamanhoVeiculoSelecionado + ": " + valorFormatado);
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Função para ler os nomes das cidades a partir de um arquivo CSV
    private static String[] lerNomesCidadesDoCSV(String caminhoArquivoCSV) throws IOException {
        String[] cidades = null;
        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivoCSV))) {
            String linha;
            if ((linha = leitor.readLine()) != null) {
                // Divide a linha do arquivo CSV em nomes de cidades usando ';' como delimitador
                cidades = linha.split(";");
            }
        }
        return cidades;
    }

    // Função para criar um objeto de caminhão com base no tamanho selecionado
    private static Caminhao criarVeiculo(TamanhoVeiculo tamanhoVeiculo) {
        switch (tamanhoVeiculo) {
            case PEQUENO:
                return new CaminhaoPequeno();
            case MEDIO:
                return new CaminhaoMedio();
            case GRANDE:
                return new CaminhaoGrande();
            default:
                return new CaminhaoPequeno(); // Usando caminhão pequeno por padrão.
        }
    }

    private static boolean verificaLista(String cidade, String[] cidadesDisponíveis) {
        for (String cidadeDisponivel : cidadesDisponíveis) {
            if (cidadeDisponivel.equalsIgnoreCase(cidade)) {
                return true;
            }
        }
        return false;
    }
}