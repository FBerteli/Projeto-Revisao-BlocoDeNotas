package produtos;

public class Luminaria extends Produto{
    public static String nome = "Luminária";
    public static double peso = 0.8;

    public Luminaria(String nome, double peso){
        super(nome, peso);

    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }

}
