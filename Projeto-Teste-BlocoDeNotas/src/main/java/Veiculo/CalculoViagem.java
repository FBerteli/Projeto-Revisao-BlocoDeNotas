package Veiculo;

import Cidade.CalculoDistancia;

import java.util.Scanner;
//ESSA CLASSE FOI DESCARTADA, SÓ CONTINA AQUI PRA OS DEVS NOTAREM O QUE FOI FEITO
// A INTERFACE FUNCIONADO FOI DEIXADA NA MAIN
public class CalculoViagem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita ao usuário que selecione as cidades de origem e destino
        System.out.print("Selecione a primeira cidade: ");
        String cidadeOrigem = scanner.next();
        System.out.print("Selecione a segunda cidade: ");
        String cidadeDestino = scanner.next();

        // Calcula a distância entre as cidades
        double distancia = calcularDistancia(cidadeOrigem, cidadeDestino);

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

        // Cria o veículo com base no tamanho selecionado
        Caminhao caminhao = criarVeiculo(tamanhoVeiculoSelecionado);

        // Calcula o custo da viagem
        double custo = calcularCustoViagem(caminhao, distancia);

        System.out.println("Distância entre " + cidadeOrigem + " e " + cidadeDestino + ": " + distancia + " km");
        System.out.println("Custo da viagem com caminhão " + tamanhoVeiculoSelecionado + ": R$" + custo);
    }

    private static double calcularDistancia(String cidadeOrigem, String cidadeDestino) {
        // Implemente o cálculo da distância aqui. Pode usar a classe CalculoDistancia.
        // Exemplo: return CalculoDistancia.calcularDistancia(cidadeOrigem, cidadeDestino);
        return 0.0; // Substitua pelo cálculo real.
    }

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

    private static double calcularCustoViagem(Caminhao caminhao, double distancia) {
        return caminhao.getPrecoPorKm() * distancia;
    }
}
