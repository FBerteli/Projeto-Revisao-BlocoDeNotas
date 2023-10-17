package Cidade;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DistanciasCidades {
    public Map<String, Integer> Indice = new HashMap<>();
    private int[][] matrizDistancia;

    public DistanciasCidades(String caminhoCSV) {
        carregaMatrizDeDistancias(caminhoCSV);
    }

    public void carregaMatrizDeDistancias(String caminhoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            String[] cidades = null;
            int linhaAtual = 0;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(";");
                if (cidades == null) {

                    cidades = valores;
                    matrizDistancia = new int[cidades.length][cidades.length];
                    for (int i = 0; i < cidades.length; i++) {
                        Indice.put(cidades[i], i);
                    }
                } else {
                    for (int coluna = 0; coluna < valores.length; coluna++) {
                        matrizDistancia[linhaAtual][coluna] = Integer.parseInt(valores[coluna]);
                    }
                    linhaAtual++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Tu coloca duas cidades, ele vai te retornar a distância delas
    public int pegaDistancia(String cidade1, String cidade2) {
        if (Indice.containsKey(cidade1) && Indice.containsKey(cidade2)) {
            int indice1 = Indice.get(cidade1);
            int indice2 = Indice.get(cidade2);
            return matrizDistancia[indice1][indice2];
        } else {
            return -1;
        }
    }

    public void listaCidades() {

        final String corPadrao = "\u001B[0m";
        final String corAmarela = "\u001B[33m";

        String titulo = "Aqui estão as cidades/rotas disponíveis: ";


        System.out.println(corAmarela + String.format("%53s", titulo) + corPadrao);
        System.out.println(" ");

        int count = 0;

        for (String cidade : Indice.keySet()) {
            System.out.print(corAmarela +  " " + String.format("%-27s", cidade) + " " + corPadrao);
            count++;

            if (count == 3) {

                System.out.println("\n" +  "                                                           " );

                count = 0;
            }
        }
        for (int i = count; i < 3; i++) {
            System.out.print("                           ");
        }
        System.out.println(" ");
    }
    public void limparTela() {
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }

        for (int i = 0; i < 40; i++) {
            System.out.println("                                                     ");
        }
    }

    // verifica se a cidade existe
    public boolean verificaCidade(String cidade) {
        return Indice.containsKey(cidade);
    }
}