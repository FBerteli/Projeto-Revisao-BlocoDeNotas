package Caminhao;

public class Caminhao {
    private int tipo;
    private double valorKm;
    private double capacidadeMaxima;
    private double capacidadeAtual;

    public Caminhao(int tipo) {
        this.tipo = tipo;

        // Definir as propriedades com base no tipo de caminhão
        switch (tipo) {
            case 1:
                this.capacidadeMaxima = 1000.0;
                this.valorKm = 5.83;
                break;
            case 2:
                this.capacidadeMaxima = 4000.0;
                this.valorKm = 13.42;
                break;
            case 3:
                this.capacidadeMaxima = 10000.0;
                this.valorKm = 29.21;
                break;
            default:
                System.out.println("!!Erro!! Escolha um caminhão válido e tente novamente.");
                this.capacidadeMaxima = 0.0;
                this.valorKm = 0.0;
                break;
        }

        this.capacidadeAtual = 0.0;
    }

    public double calcularValor(double distancia, int quantidadeCaminhoes) {
        double valor = this.valorKm * distancia * quantidadeCaminhoes;
        return valor;
    }

    public boolean verificaPeso(double peso) {
        return peso <= capacidadeMaxima;
    }
}
