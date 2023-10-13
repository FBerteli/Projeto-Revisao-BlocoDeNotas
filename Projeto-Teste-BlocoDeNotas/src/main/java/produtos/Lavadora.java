package produtos;

public class Lavadora extends Produto {

    public static String nome = "Lavadora de Roupas";
    public static double peso = 15;

    public Lavadora(String nome, double peso){
        super(nome, peso);
    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }

}
