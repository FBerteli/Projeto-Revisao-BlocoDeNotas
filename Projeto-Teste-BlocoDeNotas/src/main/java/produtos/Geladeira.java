package produtos;

public class Geladeira extends Produto {

    public static String nome = "Geladeira";
    public static double peso = 50;

    public Geladeira(String nome, double peso){

        super(nome, peso);

    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }

}
