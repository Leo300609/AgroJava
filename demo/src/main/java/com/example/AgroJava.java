package com.example;
import java.util.Scanner;

public class AgroJava {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Declaração de variáveis
            double[] chuva = new double[7];
            double[][] campo = new double[4][4];
            int opcao; 

        // Começo do programa 
            do{
                System.out.println("======================");
                System.out.println("Bem-vindo ao AgroJava!");
                System.out.println("======================");
                System.out.println("1-) Cadastrar Dados");
                System.out.println("2-) Exibir mapa do campo");
                System.out.println("3-) Relatórios de Alertas de Irrigação");
                System.out.println("4-) Sair");

                opcao = scanner.nextInt();

                if (opcao == 1) {
                        System.out.println("Me diga a quantidade de chuva para cada um dos 7 dias da semana (em mm):");
                        for (int i = 0; i < 7; i++) {
                            System.out.print("Dia " + (i + 1) + ": ");
                            chuva[i] = scanner.nextDouble();
                        }

                        System.out.println("Agora me diga a quantidade de umidade de cada talhao do campo 4x4 (em %):");
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                System.out.print("Talhão [" + i + "][" + j + "]: ");
                                campo[i][j] = scanner.nextDouble();
                            }
                        }

                } else if (opcao == 2) {
                    System.out.println("Mapa do campo (umidade em %):");
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                System.out.print(campo[i][j] + "\t");
                            }
                            System.out.println();
                        }
                } else if (opcao == 3) {
                    System.out.println("Relatórios de Alertas de Irrigação:");
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (campo[i][j] < 30) {
                                    System.out.println("Talhão [" + i + "][" + j + "] precisa de irrigação! Umidade atual: " + campo[i][j] + "%");
                                }
                            }
                        }
                }

            } while (opcao != 4);

            System.out.println("Obrigado por usar o AgroJava! Até a próxima!");
    }
}
