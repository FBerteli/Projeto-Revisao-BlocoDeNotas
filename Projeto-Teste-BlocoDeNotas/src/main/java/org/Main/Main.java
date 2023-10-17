package org.Main;

import java.text.DecimalFormat;
import java.util.Scanner;

import Caminhao.Caminhao;
import Cidade.DistanciasCidades;
import Produtos.ListaDeProdutos;
import Transporte.CadastrosTransporte;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialização dos objetos
        DistanciasCidades sistema = new DistanciasCidades("Projeto-Teste-BlocoDeNotas/DistanciasCidadesCSV.csv");
        ListaDeProdutos ListaProduto = new ListaDeProdutos();
        CadastrosTransporte cadastrosDeTransportes = new CadastrosTransporte();
        boolean controlaDados = false;

        while (true) {
            System.out.println(" ");
            System.out.println(" --> Bem-vindo ao Sistema de Transporte \u001B[33m sistema \u001B[0m   ");
            System.out.println(" ");
            System.out.println("  \u001B[33m1\u001B[0m. Consultar Trechos e Modalidades");
            System.out.println("  \u001B[33m2\u001B[0m. Cadastrar transporte");
            System.out.println("  \u001B[33m3\u001B[0m. Dados estatísticos");
            System.out.println("  \u001B[33m4\u001B[0m. Finalizar o programa");
            System.out.println("  ");
            System.out.println(" --> Digite uma alternativa conforme os números em \u001B[33mamarelo\u001B[0m: ");
            System.out.println(" ");

            int choice = scanner.nextInt();
            sistema.limparTela();
            switch (choice) {
                case 1:
                    Scanner scanner1 = new Scanner(System.in);
                    sistema.listaCidades();
                    System.out.println(" ");
                    System.out.println("   Escolha as cidades!   ");
                    System.out.println(" ");
                    String cidade1;
                    String cidade2;
                    String enter;
                    System.out.println("Digite a primeira cidade (partida): ");
                    cidade1 = scanner1.nextLine();
                    System.out.println("Digite a segunda cidade (destino): ");
                    cidade2 = scanner1.nextLine();
                    String Cidade1 = cidade1.toUpperCase();
                    String Cidade2 = cidade2.toUpperCase();
                    if (Cidade1.equals(Cidade2)) {
                        System.out.println(" ");
                        System.out.println("  Você digitou cidades iguais! ");
                        System.out.println("  Ambas as cidades devem ser diferentes. ");
                        System.out.println(" ");
                        break;
                    } else {
                        if (sistema.verificaCidade(Cidade1) && sistema.verificaCidade(Cidade2)) {
                            System.out.println(" ");
                            System.out.println("   As cidades selecionadas foram " + Cidade1 + " e " + Cidade2 + "   ");
                            System.out.println("   Escolha o tipo de caminhão que será utilizado para o trajeto!  ");
                            System.out.println("   1 - Pequeno  ");
                            System.out.println("   2 - Médio  ");
                            System.out.println("   3 - Grande  ");
                            System.out.println(" ");
                            int caminhaoEscolhido = scanner1.nextInt();
                            Caminhao caminhao = new Caminhao(caminhaoEscolhido);
                            double distancia = sistema.pegaDistancia(Cidade1, Cidade2);
                            double Valor = caminhao.calcularValor(distancia, 1);
                            System.out.println(" ");
                            System.out.println("  Detalhes sobre o \u001B[33mtrajeto\u001B[0m   ");
                            System.out.println(" ");
                            System.out.println("|   Origem: " + Cidade1);
                            System.out.println("|   Destino: " + Cidade2);
                            System.out.println("|   Distância: " + distancia + " km");
                            System.out.println("|   Custo: R$ " + Valor);
                            System.out.println(" ");
                            System.out.println("Pressione a tecla \u001B[33mEnter\u001B[0m para continuar");
                            scanner1.nextLine();
                            scanner1.nextLine();
                        } else {
                            System.out.println(" ");
                            System.out.println("|  Erro, tente novamente  |");
                            System.out.println(" ");
                            System.out.println("Pressione a tecla \u001B[33mEnter\u001B[0m para continuar");
                            scanner1.nextLine();
                            scanner1.nextLine();
                            break;
                        }
                    }
                    break;
                case 2:
                    // Arredondar números e interação com o usuário
                    DecimalFormat df = new DecimalFormat("###.##");
                    Scanner prompt = new Scanner(System.in);

                    // Listar Cidades
                    sistema.listaCidades();
                    String cidadeOrigem;
                    String cidadeDestino;
                    do {
                        System.out.println("Qual é a cidade de origem? ");
                        cidadeOrigem = prompt.nextLine().toUpperCase();
                        System.out.println("Qual é a cidade de destino? ");
                        cidadeDestino = prompt.nextLine().toUpperCase();
                        if (!sistema.Indice.containsKey(cidadeOrigem) || !sistema.Indice.containsKey(cidadeDestino) || cidadeOrigem.equals(cidadeDestino)) {
                            System.out.println("Atenção: Uma das cidades digitadas não está presente na lista ou as duas cidades são iguais. Revise a ortografia e tente novamente.");
                        }
                    } while (!sistema.Indice.containsKey(cidadeOrigem) || !sistema.Indice.containsKey(cidadeDestino) || cidadeOrigem.equals(cidadeDestino));
                    double distanciaASerPercorrida = sistema.pegaDistancia(cidadeOrigem, cidadeDestino);

                    // Criar Lista de Carga
                    ListaProduto.listarProdutos();
                    ListaProduto.selecionarProdutos();

                    // Calcular Preço e Melhor Caminhão
                    double soma = ListaProduto.selecionarProdutos.values().stream().mapToDouble(Double::doubleValue).sum();
                    double valorTransporte = 0;
                    double valorOpcional = 0;
                    int caminhaoPequeno = 0;
                    int caminhaoMedio = 0;
                    int caminhaoGrande = 0;
                    int contador = 0;

                    if (soma > 10000) {
                        Caminhao caminhoes = new Caminhao(3);
                        while (soma > 10000) {
                            soma -= 10000;
                            contador += 1;
                        }
                        valorOpcional += caminhoes.calcularValor(distanciaASerPercorrida, contador);
                    }
                    if (soma <= 2301.88) {
                        if (soma > 1000) {
                            Caminhao caminhoes1 = new Caminhao(1);
                            valorTransporte += caminhoes1.calcularValor(distanciaASerPercorrida, 2);
                            caminhaoPequeno += 2;
                        } else {
                            Caminhao caminhoes2 = new Caminhao(1);
                            valorTransporte += caminhoes2.calcularValor(distanciaASerPercorrida, 1);
                            caminhaoPequeno += 1;
                        }
                    }
                    if (soma > 2301.88 && soma <= 8706.40) {
                        if (soma <= 4000) {
                            Caminhao caminhoes3 = new Caminhao(2);
                            valorTransporte += caminhoes3.calcularValor(distanciaASerPercorrida, 1);
                            caminhaoMedio += 1;
                        } else if (soma < 8706.40 && soma > 4000) {
                            Caminhao caminhoes4 = new Caminhao(2);
                            Caminhao caminhoes42 = new Caminhao(1);
                            valorTransporte += caminhoes4.calcularValor(distanciaASerPercorrida, 1) + caminhoes42.calcularValor(distanciaASerPercorrida, 1);
                            caminhaoPequeno += 1;
                            caminhaoMedio += 1;
                        }
                    }
                    if (soma > 8706.40) {
                        Caminhao caminhoes5 = new Caminhao(3);
                        valorTransporte += caminhoes5.calcularValor(distanciaASerPercorrida, 1);
                        caminhaoGrande += 1;
                    }

                    String valorTotal = df.format(valorTransporte + valorOpcional);
                    valorTotal = valorTotal.replace(',', '.');
                    System.out.println("A distância da viagem é de " + distanciaASerPercorrida + " KM.");
                    System.out.println("O custo da viagem de " + cidadeOrigem + " até a cidade de " + cidadeDestino + " é de: R$" + valorTotal);
                    System.out.println("Foram utilizados: " + caminhaoPequeno + " caminhões pequenos, " + caminhaoMedio + " caminhões médios" + " e " + (caminhaoGrande + contador) + " caminhões grandes!");

                    // Atualiza os dados de cadastrosDeTransportes
                    cadastrosDeTransportes.caminhoesPequenos += caminhaoPequeno;
                    cadastrosDeTransportes.caminhoesMedios += caminhaoMedio;
                    cadastrosDeTransportes.caminhoesGrandes += (caminhaoGrande + contador);
                    cadastrosDeTransportes.custoPorCaminhoesPequenos = cadastrosDeTransportes.calculaCustoPorCaminhaoPequeno(distanciaASerPercorrida);
                    cadastrosDeTransportes.custoPorCaminhoesMedios = cadastrosDeTransportes.calculaCustoPorCaminhaoMedio(distanciaASerPercorrida);
                    cadastrosDeTransportes.custoPorCaminhoesGrandes = cadastrosDeTransportes.calculaCustoPorCaminhaoGrande(distanciaASerPercorrida);
                    cadastrosDeTransportes.trechos++;
                    cadastrosDeTransportes.precosAdicionados.add(Double.valueOf(valorTotal));
                    cadastrosDeTransportes.distanciaDeTrechos.add(distanciaASerPercorrida);
                    cadastrosDeTransportes.precosTotais.add(Double.valueOf(valorTotal));
                    controlaDados = true;
                    System.out.println("Pressione Enter para continuar");
                    prompt.nextLine();
                    break;
                case 3:
                    if (controlaDados) {
                        Scanner input = new Scanner(System.in);
                        cadastrosDeTransportes.exibeEstatisticas();
                        cadastrosDeTransportes.calculaCustoMedioPorProduto(ListaProduto.selecionarProdutos, ListaProduto.QuantidadeProdutos, cadastrosDeTransportes.precosAdicionados);
                        cadastrosDeTransportes.contaTotalDeItensTransportados(ListaProduto.QuantidadeProdutos);
                        System.out.println("Custo total em caminhões pequenos: R$" + cadastrosDeTransportes.custoPorCaminhoesPequenos);
                        System.out.println("Custo total em caminhões médios: R$" + cadastrosDeTransportes.custoPorCaminhoesMedios);
                        System.out.println("Custo total em caminhões grandes: R$" + cadastrosDeTransportes.custoPorCaminhoesGrandes);
                        System.out.println("Pressione Enter para continuar");
                        input.nextLine();
                    } else {
                        System.out.println("\u001B[31mSem dados estatísticos registrados.\u001B[0m");
                    }
                    break;
                case 4:
                    System.out.println("Sistema encerrado. Obrigado por utilizar a \u001B[33mAmarelinha\u001B[0m!");
                    System.exit(0);
                default:
                    System.out.println("\u001B[31mOpção inválida.\u001B[0m Por favor, escolha novamente uma opção válida.");
            }
        }
    }
}
