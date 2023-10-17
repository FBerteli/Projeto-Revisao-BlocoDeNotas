package Produtos;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


public class ListaDeProdutos {
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Double> produtos;
    public Map<String, Double> selecionarProdutos = new HashMap<>();
    public Map<String, Integer> QuantidadeProdutos = new HashMap<>();

    public ListaDeProdutos() {
        produtos = new HashMap<>();
        // Inicialize a lista de produtos com os nomes e pesos
        initializeProducts();
    }

    public void listarProdutos() {
        System.out.println("Lista de Produtos Disponíveis:");
        for (String productName : produtos.keySet()) {
            System.out.println(productName + " - Peso: " + produtos.get(productName) + " kg");
        }
    }

    public void selecionarProdutos() {
        int flag;
        do {
            displayProductList();
            System.out.println("Qual item você deseja adicionar na carga? (Digite o item como ele aparece na lista) ");
            String item = scanner.nextLine().toUpperCase();
            Scanner scanner = new Scanner(System.in);
            int quantidade = 0;

            try {
                System.out.println("Quantos itens você deseja transportar?");
                quantidade = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número inteiro válido.");
                // Limpe o buffer do scanner e permita que o usuário insira novamente
                scanner.next();
            }
            addSelectedProduct(item, quantidade);
            System.out.println("Você deseja (Digite o número): ");
            System.out.println("1 - Continuar adicionando itens ");
            System.out.println("2 - Sair ");
            flag = scanner.nextInt();
        } while (flag != 2);
        displaySelectedProducts();
    }

    private void initializeProducts() {
        produtos.put("Celular", 0.7);
        produtos.put("Geladeira", 50.0);
        produtos.put("Air Fryer", 3.5);
        produtos.put("Cadeira", 5.0);
        produtos.put("Luminária", 0.8);
        produtos.put("Lavadora de Roupa", 15.0);
        produtos.put("PlayStation 5", 3.9);
        produtos.put("Nintendo Switch", 0.3);
    }

    private void displayProductList() {
        System.out.println("Produtos Disponíveis:");
        for (String productName : produtos.keySet()) {
            System.out.println(productName + " - Peso: " + produtos.get(productName) + " kg");
        }
    }

    private void addSelectedProduct(String item, int quantidade) {
        if (produtos.containsKey(item)) {
            selecionarProdutos.put(item, quantidade * produtos.get(item));
            QuantidadeProdutos.put(item, quantidade);
        } else {
            System.out.println("Este produto não está na lista. Verifique a ortografia e tente novamente.");
        }
    }

    private void displaySelectedProducts() {
        System.out.println("Sua lista de carga: (Produto / Peso total em KG) " + selecionarProdutos);
    }
}
