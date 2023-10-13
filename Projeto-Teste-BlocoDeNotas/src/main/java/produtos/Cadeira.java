package produtos;

public class Cadeira extends Produto {
    public static String nome = "Cadeira";
    public static double peso = 5;

    public Cadeira(String nome, double peso){
        super(nome, peso);
    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }

}
