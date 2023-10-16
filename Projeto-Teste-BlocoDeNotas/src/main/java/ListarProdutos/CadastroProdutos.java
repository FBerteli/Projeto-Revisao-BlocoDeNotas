package ListarProdutos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import produtos.*;

public class CadastroProdutos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<ListaProdutos> itens = new ArrayList<>();

        while (true) {
            listarItens(); // Exibe a lista de itens disponíveis
            int escolha = scanner.nextInt(); // Lê a escolha do usuário

            if (escolha == 0) {
                break; // Se o usuário escolher 0, sai do loop
            } else if (escolha >= 1 && escolha <= 8) {
                int quantidade = obterQuantidade(scanner); // Solicita a quantidade desejada
                adicionarItem(itens, escolha, quantidade); // Adiciona o item escolhido à lista
            } else {
                System.out.println("Opção inválida. Escolha um número válido.");
            }
        }

        exibirItensSelecionados(itens); // Exibe os itens selecionados
    }

    public static void listarItens() {
        // Exibe a lista de itens disponíveis para escolha
        System.out.println("Selecione um item para adicionar à lista:");
        System.out.println("1. Celular");
        System.out.println("2. Geladeira");
        System.out.println("3. Air Fryer");
        System.out.println("4. Cadeira");
        System.out.println("5. Luminária");
        System.out.println("6. Lavadora de Roupa");
        System.out.println("7. PlayStation 5");
        System.out.println("8. Nintendo Switch");
        System.out.println("0. Finalizar lista");
    }

    public static int obterQuantidade(Scanner scanner) {
        // Solicita ao usuário a quantidade desejada
        System.out.print("Digite a quantidade desejada: ");
        return scanner.nextInt();
    }

    public static void adicionarItem(List<ListaProdutos> itens, int escolha, int quantidade) {
        ListaProdutos item;

        // Com base na escolha do usuário, cria uma instância do item selecionado
        switch (escolha) {
            case 1:
                item = new Celular();
                break;
            case 2:
                item = new Geladeira();
                break;
            case 3:
                item = new AirFryer();
                break;
            case 4:
                item = new Cadeira();
                break;
            case 5:
                item = new Luminaria();
                break;
            case 6:
                item = new Lavadora();
                break;
            case 7:
                item = new Ps5();
                break;
            case 8:
                item = new NintendoSwitch();
                break;
            default:
                System.out.println("Encerrando o programa.");
                System.exit(0); // Encerra o programa se a escolha for inválida
                return;
        }

        itens.add(item); // Adiciona o item à lista de itens
        System.out.println("Adicionado: " + quantidade + "x " + item.getNome());
    }

    public static void exibirItensSelecionados(List<ListaProdutos> itens) {
        // Exibe os itens selecionados pelo usuário
        System.out.println("Itens selecionados:");
        for (ListaProdutos item : itens) {
            System.out.println(item.getNome() + " - Peso: " + item.getPeso());
        }
    }
}
