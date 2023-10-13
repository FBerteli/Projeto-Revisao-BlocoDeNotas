package Cidade;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class CalculoDistancia {
    public static void main(String[] args) throws Exception {
        // Inicialize o contexto da API do Google Maps com sua chave
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDbyxgnL0gIjRLqjZFImKWlQHLgF2D08ms").build();

        // Lê o arquivo CSV e obtém a primeira linha
        String[] availableCities = readCSVHeader("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");

        // Mostra as cidades disponíveis
        System.out.println("Cidades disponíveis:");
        for (String city : availableCities) {
            System.out.println(city);
        }

        // Solicita ao usuário que selecione duas cidades
        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecione a primeira cidade: ");
        String city1 = scanner.nextLine();
        System.out.print("Selecione a segunda cidade: ");
        String city2 = scanner.nextLine();

        // Solicita ao usuário que selecione o tipo de caminhão
        System.out.print("Selecione o tipo de caminhão (por exemplo, 'DRIVING'): ");
        String truckType = scanner.nextLine();

        // Usa a API do Google Maps para calcular a distância
        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(context);
        DistanceMatrix distanceMatrix = request.origins(city1)
                .destinations(city2)
                .mode(TravelMode.valueOf(truckType))
                .await();

        // A resposta inclui a distância em metros e outras informações
        Distance distance = distanceMatrix.rows[0].elements[0].distance;
        System.out.println("Distância entre " + city1 + " e " + city2 + ": " + distance.humanReadable);
    }

    // Função para ler o cabeçalho do arquivo CSV e retornar a lista de cidades disponíveis
    private static String[] readCSVHeader(String csvFilePath) throws IOException {
        String[] cities = null;
        BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
        String line;
        if ((line = br.readLine()) != null) {
            cities = line.split(";");
        }
        br.close();
        return cities;
    }
}
