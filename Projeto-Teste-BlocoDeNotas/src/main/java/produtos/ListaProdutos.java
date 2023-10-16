package produtos;

public class ListaProdutos {
    private String nome;
    private double peso;
    private int quantidade;

    public ListaProdutos(String nome, double peso, int quantidade) {
        this.nome = nome;
        this.peso = peso;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPeso() {
        return peso;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
