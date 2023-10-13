package Veiculo;

public class CaminhaoGrande extends Caminhao implements CalculoViagem {
    public TamanhoVeiculo tamanho = TamanhoVeiculo.GRANDE;
    public int CapacidadeToneladas = 10;

    @Override
    public double calcularCustoViagem(double distancia) {
        return distancia * 29.21;
    }
}
