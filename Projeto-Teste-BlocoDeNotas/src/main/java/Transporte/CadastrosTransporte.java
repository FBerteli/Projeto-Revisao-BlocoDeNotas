package Transporte;

import Produtos.ListaDeProdutos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class CadastrosTransporte extends ListaDeProdutos{
    public int trechos = 0;
    public ArrayList<Double> distanciaDeTrechos = new ArrayList<>();
    public ArrayList<Double> precosTotais = new ArrayList<>();
    public DecimalFormat df = new DecimalFormat("0.00");

    public ArrayList<Double> precosAdicionados = new ArrayList<>();

    public double calculaCustoTotal(ArrayList<Double> precosTotais) {
        return precosTotais.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculaDistanciaTotalDeTrechos(ArrayList<Double> distanciaDeTrechos) {
        return distanciaDeTrechos.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculaCustoPorTrecho(int numeroDeTrechos) {
        return calculaCustoTotal(precosTotais) / numeroDeTrechos;
    }

    public double calculaCustoMedioPorKm() {
        return calculaCustoTotal(precosTotais) / calculaDistanciaTotalDeTrechos(distanciaDeTrechos);
    }

    public void calculaCustoMedioPorProduto(Map<String, Double> selectProducts, Map<String, Integer> productsQuantity, ArrayList<Double> precosAdicionados) {
        for (Map.Entry<String, Double> entry : selectProducts.entrySet()) {
            String produto = entry.getKey();
            double precoProduto = entry.getValue();
            int quantidadeProduto = productsQuantity.get(produto);
            double precoMedioPorProduto = precoProduto / quantidadeProduto;
            precosTotais.add(precoMedioPorProduto);
            System.out.println("O preço médio por " + produto + " é de: R$" + df.format(precoMedioPorProduto));
        }
    }


    public void contaTotalDeItensTransportados(Map<String, Integer> productsQuantity) {
        int totalDeItens = productsQuantity.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total de itens transportados: " + totalDeItens);
    }

    public void exibeEstatisticas() {
        System.out.println("Preço total: R$" + df.format(calculaCustoTotal(precosTotais)));
        System.out.println("Custo médio por KM: R$" + df.format(calculaCustoMedioPorKm()));
        System.out.println("Custo por trecho: R$" + df.format(calculaCustoPorTrecho( trechos )));
    }

    public void atualizarDados(double distancia, double valor) {
        distanciaDeTrechos.add(distancia);
        precosTotais.add(valor);
        precosAdicionados.add(valor); // Adicione o valor também a esta lista, se necessário.
    }
}
