package Veiculo;

public class Caminhao extends Veiculo {
    // Construtor da classe Caminhao
    public Caminhao(TamanhoVeiculo tamanho) {
        // Chama o construtor da classe base (Veiculo) com preço e capacidade inicializados como zero
        super(tamanho, 0, 0);
        // Define os valores com base no tamanho do caminhão
        definirValoresBaseadoNoTamanho();
    }

    // Método privado para definir os valores do caminhão com base no tamanho
    private void definirValoresBaseadoNoTamanho() {
        switch (tamanho) {
            case PEQUENO:
                precoPorKm = 5.83; // Preço por quilômetro para caminhão pequeno
                capacidadeCarga = 2000; // Capacidade de carga para caminhão pequeno
                break;
            case MEDIO:
                precoPorKm = 13.42; // Preço por quilômetro para caminhão médio
                capacidadeCarga = 6000; // Capacidade de carga para caminhão médio
                break;
            case GRANDE:
                precoPorKm = 29.21; // Preço por quilômetro para caminhão grande
                capacidadeCarga = 15000; // Capacidade de carga para caminhão grande
                break;
        }
    }
}
