package Transporte;

import Produtos.ListaDeProdutos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class CadastrosTransporte extends ListaDeProdutos {
    // Variáveis para armazenar estatísticas de transporte
    public int trechos = 0;
    public int caminhoesPequenos = 0;
    public int caminhoesMedios = 0;
    public int caminhoesGrandes = 0;
    public double custoPorCaminhoesPequenos;
    public double custoPorCaminhoesMedios;
    public double custoPorCaminhoesGrandes;
    public ArrayList<Double> distanciaDeTrechos = new ArrayList<>();
    public ArrayList<Double> precosTotais = new ArrayList<>();
    public ArrayList<Double> precosAdicionados = new ArrayList<>();
    DecimalFormat formatoDecimal = new DecimalFormat("###.##");

    // Calcula o custo total com base na lista de preços
    public double calculaCustoTotal(ArrayList precosTotais) {
        double precoTotal = 0;
        for (Object preco : precosTotais) {
            precoTotal += (double) preco;
        }
        return precoTotal;
    }

    // Calcula a distância total de todos os trechos
    public double calculaDistanciaTotalDeTrechos(ArrayList distanciaDeTrechos) {
        double distanciaTotal = 0;
        for (Object distanciaDeTrecho : distanciaDeTrechos) {
            distanciaTotal += (double) distanciaDeTrecho;
        }
        return distanciaTotal;
    }

    // Calcula o custo médio por trecho
    public double calculaCustoPorTrecho(int numeroDeTrechos) {
        return calculaCustoTotal(this.precosTotais) / numeroDeTrechos;
    }

    // Calcula o custo médio por quilômetro
    public double calculaCustoMedioPorKm() {
        return calculaCustoTotal(this.precosTotais) / calculaDistanciaTotalDeTrechos(this.distanciaDeTrechos);
    }

    // Calcula o custo médio por produto e exibe os resultados
    public void calculaCustoMedioPorProduto(Map<String, Double> selectProducts, Map<String, Integer> productsQuantity, ArrayList<Double> precos) {
        for (Map.Entry<String, Double> chaveAtual : selectProducts.entrySet()) {
            if (chaveAtual.getKey() != null && chaveAtual.getValue() != null) {
                double precoMedioPorProduto = precos.get(0) / productsQuantity.get(chaveAtual.getKey());
                System.out.println("O preço médio por " + chaveAtual.getKey() + " é de : R$" + formatoDecimal.format(precoMedioPorProduto));
            }
        }
    }

    // Calcula o total de itens transportados e exibe o resultado
    public void contaTotalDeItensTransportados(Map<String, Integer> productsQuantity) {
        int totalDeItens = 0;
        for (Map.Entry<String, Integer> chaveAtual : productsQuantity.entrySet()) {
            totalDeItens += chaveAtual.getValue();
        }
        System.out.println("Itens transportados: " + totalDeItens);
    }

    // Calcula o custo total em caminhões pequenos com base na distância
    public double calculaCustoPorCaminhaoPequeno(double distance) {
        return (this.caminhoesPequenos * distance) * 5.83;
    }

    // Calcula o custo total em caminhões médios com base na distância
    public double calculaCustoPorCaminhaoMedio(double distance) {
        return (this.caminhoesMedios * distance) * 13.42;
    }

    // Calcula o custo total em caminhões grandes com base na distância
    public double calculaCustoPorCaminhaoGrande(double distance) {
        return (this.caminhoesGrandes * distance) * 29.21;
    }

    // Exibe as estatísticas de transporte
    public void exibeEstatisticas() {
        System.out.println("Preço total: R$ " + calculaCustoTotal(precosTotais));
        System.out.println("Custo médio por KM: R$" + calculaCustoMedioPorKm());
        System.out.println("Custo por trecho: R$" + calculaCustoPorTrecho(trechos));
        System.out.println("Caminhões pequenos utilizados: " + this.caminhoesPequenos);
        System.out.println("Caminhões médios utilizados: " + this.caminhoesMedios);
        System.out.println("Caminhões grandes utilizados: " + this.caminhoesGrandes);
    }
}
