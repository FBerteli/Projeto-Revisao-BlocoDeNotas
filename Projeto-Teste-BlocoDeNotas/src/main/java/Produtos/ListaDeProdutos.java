package Produtos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListaDeProdutos {
    // Scanners para entrada de dados
    Scanner terminalString = new Scanner(System.in);
    Scanner terminalDouble = new Scanner(System.in);
    private Scanner scanner = new Scanner(System.in);

    // Mapa para armazenar produtos e seus pesos
    private Map<String, Double> produtos;

    // Mapas para selecionar produtos, quantidades e quantificar produtos
    public Map<String, Double> selecionarProdutos = new HashMap<>();
    public Map<String, Integer> QuantidadeProdutos = new HashMap<>();
    public Map<String, Integer> QuantificarProdutos = new HashMap<>();

    public ListaDeProdutos() {
        produtos = new HashMap<>();
        // Inicialize a lista de produtos com os nomes e pesos
        initializeProducts();
    }

    public void listarProdutos() {
        // Exibe a lista de produtos disponíveis
        System.out.println("Lista de Produtos Disponíveis:");
        for (String productName : produtos.keySet()) {
            System.out.println(productName + " - Peso: " + produtos.get(productName) + " kg");
        }
    }

    public void selecionarProdutos() {
        int flag;
        do {
            System.out.println(produtos);
            System.out.println("Qual item você deseja adicionar na carga? (Digite o item como ele aparece na lista) ");
            String item = terminalString.nextLine().toUpperCase();
            System.out.println("Quantos itens você deseja transportar?");
            int quantidade = terminalDouble.nextInt();
            switch (item) {
                case "CELULAR" -> {
                    selecionarProdutos.put(item, (quantidade * 0.70));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "GELADEIRA" -> {
                    selecionarProdutos.put(item, (quantidade * 50.0));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "AIR FRYER" -> {
                    selecionarProdutos.put(item, (quantidade * 3.50));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "CADEIRA" -> {
                    selecionarProdutos.put(item, (quantidade * 5.0));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "LUMINARIA" -> {
                    selecionarProdutos.put(item, (quantidade * 0.80));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "LAVADORA DE ROUPA" -> {
                    selecionarProdutos.put(item, (quantidade * 15.0));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "PLAYSTATION 5" -> {
                    selecionarProdutos.put(item, (quantidade * 3.90));
                    QuantificarProdutos.put(item, quantidade);
                }
                case "NINTENDO SWITCH" -> {
                    selecionarProdutos.put(item, (quantidade * 0.3));
                    QuantificarProdutos.put(item, quantidade);
                }
                default -> System.out.println("Este produto não está na lista. Verifique a sua ortografia e tente novamente");
            }
            System.out.println("Você deseja (Digite o número): ");
            System.out.println("1 - Continuar adicionando itens ");
            System.out.println("2 - Sair ");
            flag = terminalDouble.nextInt();
        } while (flag != 2);
        System.out.println("Sua lista de carga: (Produto / Peso total em KG) " + selecionarProdutos);
    }

    private void initializeProducts() {
        // Inicializa a lista de produtos com nomes e pesos
        produtos.put("Celular", 0.7);
        produtos.put("Geladeira", 50.0);
        produtos.put("Air Fryer", 3.5);
        produtos.put("Cadeira", 5.0);
        produtos.put("Luminária", 0.8);
        produtos.put("Lavadora de Roupa", 15.0);
        produtos.put("PlayStation 5", 3.9);
        produtos.put("Nintendo Switch", 0.3);
    }

    private void adicionarProdutoSelecionado(String item, int quantidade) {
        if (produtos.containsKey(item)) {
            selecionarProdutos.put(item, quantidade * produtos.get(item));
            QuantidadeProdutos.put(item, quantidade);
        } else {
            System.out.println("Este produto não está na lista. Verifique a ortografia e tente novamente.");
        }
    }

    private void mostrarProdutoSelecionado() {
        System.out.println("Sua lista de carga: (Produto / Peso total em KG) " + selecionarProdutos);
    }
}
