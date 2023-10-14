package Veiculo;

public class Veiculo {
    protected TamanhoVeiculo tamanho;
    protected double precoPorKm;
    protected int capacidadeCarga;

    // Construtor da classe Veiculo
    public Veiculo(TamanhoVeiculo tamanho, double precoPorKm, int capacidadeCarga) {
        this.tamanho = tamanho; // Define o tamanho do veículo (Pequeno, Médio, Grande)
        this.precoPorKm = precoPorKm; // Define o preço por quilômetro
        this.capacidadeCarga = capacidadeCarga; // Define a capacidade de carga do veículo
    }

    // Método para obter o tamanho do veículo
    public TamanhoVeiculo getTamanho() {
        return tamanho;
    }

    // Método para obter o preço por quilômetro do veículo
    public double getPrecoPorKm() {
        return precoPorKm;
    }

    // Método para obter a capacidade de carga do veículo
    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }
}
