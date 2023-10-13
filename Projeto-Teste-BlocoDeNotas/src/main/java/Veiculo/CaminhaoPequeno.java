package Veiculo;

public class CaminhaoPequeno extends Caminhao implements CalculoViagem {

    public TamanhoVeiculo tamanho = TamanhoVeiculo.PEQUENO;

    private int CapacidadeToneladas = 1;

    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 5.83;
    }
}
