package org.example;

import Veiculo.Caminhao;
import Veiculo.CaminhaoGrande;
import Veiculo.CaminhaoMedio;
import Veiculo.CaminhaoPequeno;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.Distance;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    /*
     * O sistema deve apresentar no terminal os trechos disponíveis para transporte. O usuário seleciona duas cidades
     * e o tipo de caminhão 🚛, e o programa mostrará a distância entre elas, juntamente com o custo total estimado
     * para o trecho. Caso um nome de cidade não exista, o programa informará ao usuário. Por exemplo, ao escolher
     * PORTO ALEGRE e SÃO PAULO, utilizando um caminhão de pequeno porte, a distância é de XXX km, e o custo será de R$ xxx,00.💰
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        // instancia os objetos caminhões
        CaminhaoPequeno caminhaoPequeno = new CaminhaoPequeno();
        CaminhaoMedio caminhaoMedio = new CaminhaoMedio();
        CaminhaoGrande caminhaoGrande = new CaminhaoGrande();
        caminhaoPequeno.setTamanho("Pequeno");
        caminhaoMedio.setTamanho("Medio");
        caminhaoGrande.setTamanho("Grande");

        // armazenar os caminhãos
        ArrayList<Caminhao> caminhaos = new ArrayList<>();
        caminhaos.add(caminhaoPequeno);
        caminhaos.add(caminhaoMedio);
        caminhaos.add(caminhaoGrande);

        // Inicialize o contexto da API do Google Maps com sua chave
        GeoApiContext contexto = new GeoApiContext.Builder().apiKey("AIzaSyDbyxgnL0gIjRLqjZFImKWlQHLgF2D08ms").build();

        // Lê o arquivo CSV e obtém a primeira linha
        String[] cidadesDisponiveis = readCSVHeader("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");

        // Mostra as cidades disponíveis
        System.out.println("Cidades disponíveis:");
        for (String cidade : cidadesDisponiveis) {
            System.out.println(cidade);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecione a cidade de partida: ");
        String cidadePartida = scanner.nextLine();
        System.out.print("Selecione a cidade de chegada: ");
        String cidadeChegada = scanner.nextLine();

        System.out.println("Selecione o tipo de caminhão [GRANDE, MEDIO, PEQUENO]:");
        for (Caminhao caminhao : caminhaos) {
            System.out.println("Caminhão " + caminhao.getTamanho());
        }

        String tipoDeCaminhao = scanner.nextLine();


        DistanceMatrixApiRequest request = DistanceMatrixApi.newRequest(contexto);
        DistanceMatrix distanceMatrix = request.origins(cidadePartida)
                .destinations(cidadeChegada)
                .mode(TravelMode.valueOf("DRIVING"))
                .await();

        Distance distance = distanceMatrix.rows[0].elements[0].distance;
        // VARIÁVEL NULA POR ALGUM MOTIVO

//        if (distance != null) {
//            String humanReadableDistance = distance.humanReadable;
//            // Faça algo com humanReadableDistance
//        } else {
//            // Lide com o caso em que distance é nulo
//            System.out.println("A variável 'distance' é nula.");
//        }

        System.out.println("Distância entre " + cidadePartida + " e " + cidadeChegada + ": " + distance.humanReadable);
//        String distanceToString = distance.toString();
//        double distanciaDouble = Double.parseDouble(distanceToString);
//        switch (tipoDeCaminhao) {
//            case "PEQUENO":
//                System.out.println("Valor total: " + caminhaoPequeno.calcularCustoViagem(distanciaDouble));
//                break;
//            case "MEDIO":
//                System.out.println("Valor total: " + caminhaoMedio.calcularCustoViagem(distanciaDouble));
//                break;
//            case "GRANDE":
//                System.out.println("Valor total: " + caminhaoGrande.calcularCustoViagem(distanciaDouble));
//                break;
//            default:
//                System.out.println("Tipo de caminhão não identificado!");
//                break;
//        }

        scanner.close();
    }

    private static String[] readCSVHeader(String caminhoDoCsv) throws IOException {
        String[] cidades = null;
        BufferedReader br = new BufferedReader(new FileReader(caminhoDoCsv));
        String linha;
        if ((linha = br.readLine()) != null) {
            cidades = linha.split(";");
        }
        br.close();
        return cidades;
    }
}