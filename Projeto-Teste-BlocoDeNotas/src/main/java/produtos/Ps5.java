package produtos;

public class Ps5 extends Produto {

    public static String nome = "Playstation 5";
    public static double peso = 3.9;

    public Ps5(String nome, double peso){
        super(nome, peso);
    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }

}


