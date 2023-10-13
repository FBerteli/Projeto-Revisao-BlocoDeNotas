package produtos;

public class Switch extends Produto{

    public static String nome = "Nintendo Switch";
    public static double peso = 0.3;

    public Switch(String nome, double peso){
        super(nome, peso);
    }

    public static String getNome(){
        return nome;
    }
    public static double getPeso() {
        return peso;
    }

}
