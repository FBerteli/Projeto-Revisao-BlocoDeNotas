package Cidade;
import produtos.*;
import org.Main.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class CadastroTransportes {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<String> cidades = new ArrayList<>(); // Cria uma lista para armazenar cidades
            List<ListaProdutos> itens = new ArrayList<>(); // Cria uma lista para armazenar itens a serem transportados

            while (true) {
                MostrarOpcoes(); // Exibe o menu de opções para o usuário

                int opcao = scanner.nextInt(); // Lê a opção escolhida pelo usuário
                scanner.nextLine(); // Limpa o buffer

                switch (opcao) {
                    case 1:
                        listarCidades(cidades); // Opção 1: Lista as cidades disponíveis
                        break;
                    case 2:
                        adicionarCidade(cidades, scanner); // Opção 2: Permite ao usuário adicionar uma cidade
                        break;
                    case 3:
                        listarItens(); // Opção 3: Lista os itens disponíveis para transporte
                        break;
                    case 4:
                        exibirItensSelecionados(itens); // Opção 4: Exibe os itens selecionados para transporte
                        break;
                    case 5:
                        listarItens(); // Opção 5: Lista os itens disponíveis para escolha
                        int escolha = scanner.nextInt(); // Lê a escolha do usuário

                        if (escolha == 0) {
                            break; // Se o usuário escolher 0, sai da escolha de itens
                        } else if (escolha >= 1 && escolha <= 8) {
                            int quantidade = obterQuantidade(scanner); // Solicita a quantidade desejada
                            adicionarItemTransporte(itens, escolha, quantidade); // Adiciona o item escolhido à lista de itens
                        } else {
                            System.out.println("Opção inválida. Escolha um número válido.");
                        }
                        break;
                    case 6:
                        calcularDistanciaECusto(cidades, itens); // Opção 6: Calcula distância e custo com base nas cidades e itens
                        break;
                    case 7:
                        exibirRelatorioEstatistico(cidades, itens); // Opção 7: Exibe um relatório de dados estatísticos
                        break;
                    case 8:
                        System.out.println("Encerrando o programa."); // Opção 8: Encerra o programa
                        scanner.close(); // Fecha o scanner
                        System.exit(0); // Encerra o programa
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente."); // Opção inválida
                        break;
                }
            }
        }

        public static void MostrarOpcoes() {
            // Exibe as opções do menu
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar cidades");
            System.out.println("2. Adicionar cidade");
            System.out.println("3. Lista dos produtos");
            System.out.println("4. Produtos a serem transportados");
            System.out.println("5. Adicionar item para transporte");
            System.out.println("6. Calcular distância e o custo");
            System.out.println("7. Relatório de Dados Estatísticos");
            System.out.println("8. Sair");
        }

        public static void listarItens() {
            // Lista os itens disponíveis para escolha
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

        public static void listarCidades(List<String> cidades) {
            // Lista as cidades disponíveis para transporte
        }

    public static void adicionarCidade(List<String> cidades, Scanner scanner) {
        // Permite ao usuário adicionar uma cidade
        System.out.print("Digite o nome da cidade a ser adicionada: ");
        String cidade = scanner.nextLine();
        cidades.add(cidade); // Adiciona a cidade à lista de cidades
        System.out.println("Cidade adicionada: " + cidade);
    }

    public static void adicionarItemTransporte(List<ListaProdutos> itens, int escolha, int quantidade) {
        ListaProdutos item;

        switch (escolha) {
            case 1:
                item = new ListaProdutos("Celular", 0.7, quantidade);
                break;
            case 2:
                item = new ListaProdutos("Geladeira", 50.0, quantidade);
                break;
            case 3:
                item = new ListaProdutos("Air Fryer", 3.5, quantidade);
                break;
            case 4:
                item = new ListaProdutos("Cadeira", 5.0, quantidade);
                break;
            case 5:
                item = new ListaProdutos("Luminária", 0.8, quantidade);
                break;
            case 6:
                item = new ListaProdutos("Lavadora de Roupa", 15.0, quantidade);
                break;
            case 7:
                item = new ListaProdutos("PlayStation 5", 3.9, quantidade);
                break;
            case 8:
                item = new ListaProdutos("Nintendo Switch", 0.3, quantidade);
                break;
            default:
                System.out.println("Encerrando o programa.");
                System.exit(0);
                return;
        }

        itens.add(item); // Adiciona o item à lista de itens
        System.out.println("Adicionado: " + quantidade + "x " + item.getNome());
    }

    public static void exibirItensSelecionados(List<ListaProdutos> itens) {
        // Exibe os itens selecionados para transporte
        System.out.println("Itens a serem transportados:");
        for (ListaProdutos item : itens) {
            System.out.println(item.getNome() + " - Peso: " + item.getPeso() + " - Quantidade: " + item.getQuantidade());
        }
    }

    public static void calcularDistanciaECusto(List<String> cidades, List<ListaProdutos> itens) {
        // Implemente aqui a lógica para calcular distância e custo com base nas cidades e itens.
        // Pode usar a API do Google Maps para calcular distâncias entre as cidades e decidir o modelo de caminhão adequado.
    }

    public static void exibirRelatorioEstatistico(List<String> cidades, List<ListaProdutos> itens) {
        double custoTotal = 0;
        double custoPorTrecho = 0;
        double custoMedioPorKm = 0;
        double custoMedioPorTipoProduto = 0;
        double totalPorTrecho = 0;
        double totalPorModalidadeTransporte = 0;
        int numeroTotalVeiculosDeslocados = 0;
        int totalItensTransportados = 0;

        // Calcule os valores estatísticos com base nos dados do programa

        // Exiba o relatório de dados estatísticos
        System.out.println("Relatório de Dados Estatísticos:");
        System.out.println("Custo Total: " + custoTotal);
        System.out.println("Custo por Trecho: " + custoPorTrecho);
        System.out.println("Custo Médio por Km: " + custoMedioPorKm);
        System.out.println("Custo Médio por Tipo de Produto: " + custoMedioPorTipoProduto);
        System.out.println("Total por Trecho: " + totalPorTrecho);
        System.out.println("Total por Modalidade de Transporte: " + totalPorModalidadeTransporte);
        System.out.println("Número Total de Veículos Deslocados: " + numeroTotalVeiculosDeslocados);
        System.out.println("Total de Itens Transportados: " + totalItensTransportados);
    }
}
