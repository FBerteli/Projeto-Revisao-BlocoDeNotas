package Cidade;

import Veiculo.CalculoViagem;
import Veiculo.*;
import com.google.maps.*;
import com.google.maps.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//ESSA CLASSE FOI DESCARTADA, SÓ CONTINA AQUI PRA OS DEVS NOTAREM O QUE FOI FEITO OU ALTERADO
// A INTERFACE FUNCIONADO FOI DEIXADA NA MAIN
public class CalculoDistancia {
    // Chave da API do Google Maps para autorização
    private static final String CHAVE_API_GOOGLE_MAPS = "AIzaSyDbyxgnL0gIjRLqjZFImKWlQHLgF2D08ms";

    public static void main(String[] args) {
        // Inicializa o contexto da API do Google Maps com a chave de autorização
        GeoApiContext contexto = new GeoApiContext.Builder().apiKey(CHAVE_API_GOOGLE_MAPS).build();

        try {
            // Lê os nomes das cidades a partir de um arquivo CSV
            // Nesse momento é lido apenas a primeira linha(ou o cabeçalho) da planilha csv
            String[] cidadesDisponiveis = lerNomesCidadesDoCSV("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");

            // Mostra as cidades disponíveis para o usuário
            System.out.println("Cidades disponíveis:");
            for (String cidade : cidadesDisponiveis) {
                System.out.println(cidade);
            }

            // Solicita ao usuário que selecione as cidades de origem e destino
            Scanner scanner = new Scanner(System.in);
            System.out.print("Selecione a primeira cidade: ");
            String cidadeOrigem = scanner.nextLine();
            System.out.print("Selecione a segunda cidade: ");
            String cidadeDestino = scanner.nextLine();

            // Cria uma requisição à API do Google Maps para calcular a distância
            DistanceMatrixApiRequest requisicao = DistanceMatrixApi.newRequest(contexto);
            DistanceMatrix matrizDistancia = requisicao.origins(cidadeOrigem)
                    .destinations(cidadeDestino)
                    .await();

            // Obtém a distância e a exibe de forma legível para o usuário
            Distance distancia = matrizDistancia.rows[0].elements[0].distance;
            System.out.println("Distância entre " + cidadeOrigem + " e " + cidadeDestino + ": " + distancia.humanReadable);
        } catch (Exception e) {
            // Em caso de erro, imprime o rastreamento da pilha
            e.printStackTrace();
        }
    }

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
}