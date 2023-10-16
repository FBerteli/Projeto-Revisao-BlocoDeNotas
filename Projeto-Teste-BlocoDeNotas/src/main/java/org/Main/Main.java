package org.Main;


import Cidade.CadastroProdutos;
import Veiculo.*;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import produtos.ListaProdutos;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {

        Scanner scanner = new Scanner(System.in);
        // Inicialização do contexto da API do Google Maps com a chave de autenticação
        GeoApiContext contexto = new GeoApiContext.Builder().apiKey("AIzaSyDbyxgnL0gIjRLqjZFImKWlQHLgF2D08ms").build();
        while (true){

            System.out.println("Escolha uma opção:");
            System.out.println("1. Consultar trechos e modalidades.");
            System.out.println("2. Cadastrar transporte");
            System.out.println("3. Consulta de estatística.");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

        switch (opcao) {

            case 1:

                try {
                    // Lê os nomes das cidades a partir de um arquivo CSV
                    String[] cidadesDisponíveis = lerNomesCidadesDoCSV("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");

                    // Exibe as cidades disponíveis para o usuário
                    System.out.println("Cidades disponíveis:");
                    for (String cidade : cidadesDisponíveis) {
                        System.out.println(cidade);
                    }

                    // Solicita ao usuário que selecione as cidades de origem e destino
                    System.out.print("Selecione a primeira cidade: ");
                    String cidadeOrigem = scanner.nextLine();
                    System.out.print("Selecione a segunda cidade: ");
                    String cidadeDestino = scanner.nextLine();

                    // Cria uma requisição à API do Google Maps para calcular a distância entre as cidades
                    DistanceMatrixApiRequest requisicao = DistanceMatrixApi.newRequest(contexto);
                    DistanceMatrix matrizDistancia = requisicao.origins(cidadeOrigem)
                            .destinations(cidadeDestino)
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
                            System.out.println("Opção inválida. Usando caminhão pequeno por padrão.");
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

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                boolean quebraLoop = true;
                while(quebraLoop) {
                    System.out.println("Digite a função que deseja realizar:");
                    System.out.println("1. Cadastrar viagem.");
                    System.out.println("2. Cadastrar item");
                    System.out.println("3. Voltar ao menu inicial.");
                    int opcaoNavegacao = scanner.nextInt();
                    scanner.nextLine();
                    List<ListaProdutos> itens = new ArrayList<>();
                    switch (opcaoNavegacao) {
                        case 1:

                            try {
                                // Lê os nomes das cidades a partir de um arquivo CSV
                                String[] cidadesDisponíveis = lerNomesCidadesDoCSV("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");

                                // Exibe as cidades disponíveis para o usuário
                                System.out.println("Cidades disponíveis:");
                                for (String cidade : cidadesDisponíveis) {
                                    System.out.println(cidade);
                                }

                                // Solicita ao usuário que selecione as cidades de origem e destino
                                System.out.print("Selecione a primeira cidade: ");
                                String cidadeOrigem = scanner.nextLine();
                                System.out.print("Selecione a segunda cidade: ");
                                String cidadeDestino = scanner.nextLine();

                                // Cria uma requisição à API do Google Maps para calcular a distância entre as cidades
                                DistanceMatrixApiRequest requisicao = DistanceMatrixApi.newRequest(contexto);
                                DistanceMatrix matrizDistancia = requisicao.origins(cidadeOrigem)
                                        .destinations(cidadeDestino)
                                        .await();

                                // Obtém a distância entre as cidades e a exibe de forma legível
                                Distance distancia = matrizDistancia.rows[0].elements[0].distance;
                                System.out.println("Distância entre " + cidadeOrigem + " e " + cidadeDestino + ": " + distancia.humanReadable);
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        case 2:
                                System.out.println("Selecione os itens abaixo, para transporte!");
                                CadastroProdutos.listarItens();
                                int escolha = scanner.nextInt();
                                int quantidade = CadastroProdutos.obterQuantidade(scanner);
                                CadastroProdutos.adicionarItem(itens, escolha, quantidade);
                                CadastroProdutos.exibirItensSelecionados(itens);
                                break;
                        case 3:
                            System.out.println("Encerrando o cadastro.");
                            quebraLoop = false;
                            break;
                    }
                }


            case 3:
                break;
            case 4:
                System.out.println("Encerrando o programa.");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.\n");



        }
        }
    }
    //################################################################################################################
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
}