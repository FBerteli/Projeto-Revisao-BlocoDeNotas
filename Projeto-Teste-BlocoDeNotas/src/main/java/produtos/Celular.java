package produtos;

public class Celular extends Produto{

    public static String nome = "Celular";
    public static double peso = 0.7;

    public Celular(String nome, double peso){
        super(nome, peso);

    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }
}
