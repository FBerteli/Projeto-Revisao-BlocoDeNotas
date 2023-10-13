package produtos;

public class Produto {
    public static String nome;
    public static double peso;

    public Produto(String nome, double peso){
        this.nome = nome;
        this.peso = peso;
    }

    public static String getNome(){
        return null;
    }

    public static double getPeso(){
        return 0;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
