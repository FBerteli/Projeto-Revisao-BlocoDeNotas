package Veiculo;

public class CaminhaoMedio extends Caminhao implements CalculoViagem {
    public TamanhoVeiculo tamanho = TamanhoVeiculo.MEDIO;
    private int CapacidadeToneladas = 4;

    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 13.42;
    }
}
