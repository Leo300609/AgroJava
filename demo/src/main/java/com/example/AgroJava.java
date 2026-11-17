package com.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AgroJava {
    static final String ARQUIVO_CHUVAS = "historico_chuvas.csv";
    static final String ARQUIVO_TALHOES = "mapa_umidade.csv";
    static final String ARQUIVO_RELATORIO = "relatorio_fazenda.txt";
    static final String[] DIAS = {"seg", "ter", "qua", "qui", "sex", "sab", "dom"};
    static final String[] NOMES_DIAS = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"};
    static final String[] LINHAS = {"A", "B", "C", "D"};

    static Scanner scanner = new Scanner(System.in);
        // Declaração de variáveis
            static double[] chuva = new double[7];
            static double[][] campo = new double[4][4];

    public static void main(String[] args) {
        carregarMapaTalhoes();
        menuPrincipal();
        System.out.println("Obrigado por usar o AgroJava! Até a próxima!");
    }

    static void menuPrincipal() {
        int opcao;
        // Começo do programa
            do{
                System.out.println("======================");
                System.out.println("Bem-vindo ao AgroJava!");
                System.out.println("======================");
                System.out.println("1-) Cadastrar Dados");
                System.out.println("2-) Exibir mapa do campo");
                System.out.println("3-) Relatórios de Alertas de Irrigação");
                System.out.println("4-) Consultar histórico de chuvas");
                System.out.println("5-) Semana mais chuvosa");
                System.out.println("6-) Dashboard climático");
                System.out.println("7-) Relatório gerencial da fazenda");
                System.out.println("8-) Exportar relatório da fazenda");
                System.out.println("9-) Sair");

                opcao = scanner.nextInt();

                if (opcao == 1) {
                    cadastrarChuvas();
                    cadastrarTalhoes();
                } else if (opcao == 2) {
                    exibirMapaCampo();
                } else if (opcao == 3) {
                    gerarRelatorioIrrigacao();
                } else if (opcao == 4) {
                    consultarHistoricoChuvas();
                } else if (opcao == 5) {
                    identificarSemanaMaisChuvosa();
                } else if (opcao == 6) {
                    gerarDashboardClimatico();
                } else if (opcao == 7) {
                    gerarRelatorioGerencial();
                } else if (opcao == 8) {
                    exportarRelatorio();
                }

            } while (opcao != 9);
    }

    static void cadastrarChuvas() {
        System.out.print("Data da semana (dd/MM/aaaa): ");
        String data = scanner.next();

        System.out.println("Me diga a quantidade de chuva para cada um dos 7 dias da semana (em mm):");
        for (int i = 0; i < 7; i++) {
            System.out.print(NOMES_DIAS[i] + ": ");
            chuva[i] = scanner.nextDouble();
        }

        double total = 0;
        int diaMaisChuvoso = 0;
        for (int i = 0; i < 7; i++) {
            total += chuva[i];
            if (chuva[i] > chuva[diaMaisChuvoso]) {
                diaMaisChuvoso = i;
            }
        }
        double media = total / 7;

        System.out.println("Média semanal de pluviosidade: " + umaCasa(media) + " mm");
        System.out.println("Total de precipitação: " + formatar(total) + " mm");
        System.out.println("Dia com maior índice de chuva: " + NOMES_DIAS[diaMaisChuvoso] + " (" + formatar(chuva[diaMaisChuvoso]) + " mm)");

        salvarHistoricoChuvas(data, media, total);
    }

    static void salvarHistoricoChuvas(String data, double media, double total) {
        File arquivo = new File(ARQUIVO_CHUVAS);
        boolean novo = !arquivo.exists() || arquivo.length() == 0;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
            if (novo) {
                writer.write("data," + String.join(",", DIAS) + ",media,total");
                writer.newLine();
            }
            StringBuilder linha = new StringBuilder(data);
            for (int i = 0; i < 7; i++) {
                linha.append(",").append(formatar(chuva[i]));
            }
            linha.append(",").append(umaCasa(media));
            linha.append(",").append(formatar(total));
            writer.write(linha.toString());
            writer.newLine();
            System.out.println("Semana registrada em " + ARQUIVO_CHUVAS);
        } catch (IOException e) {
            System.out.println("Erro ao salvar histórico de chuvas: " + e.getMessage());
        }
    }

    static ArrayList<String[]> lerHistoricoChuvas() {
        ArrayList<String[]> registros = new ArrayList<>();
        File arquivo = new File(ARQUIVO_CHUVAS);
        if (!arquivo.exists()) {
            return registros;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            while ((linha = reader.readLine()) != null) {
                if (!linha.isBlank()) {
                    registros.add(linha.split(","));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler histórico de chuvas: " + e.getMessage());
        }
        return registros;
    }

    static void consultarHistoricoChuvas() {
        ArrayList<String[]> registros = lerHistoricoChuvas();
        if (registros.isEmpty()) {
            System.out.println("Nenhuma semana registrada.");
            return;
        }
        System.out.println("===== HISTÓRICO DE CHUVAS =====");
        for (String[] registro : registros) {
            System.out.println("Semana: " + registro[0]);
            System.out.println("Média: " + formatar(Double.parseDouble(registro[8])) + " mm");
            System.out.println("Total: " + formatar(Double.parseDouble(registro[9])) + " mm");
            System.out.println();
        }
    }

    static void identificarSemanaMaisChuvosa() {
        ArrayList<String[]> registros = lerHistoricoChuvas();
        if (registros.isEmpty()) {
            System.out.println("Nenhuma semana registrada.");
            return;
        }
        String[] maior = registros.get(0);
        for (String[] registro : registros) {
            if (Double.parseDouble(registro[9]) > Double.parseDouble(maior[9])) {
                maior = registro;
            }
        }
        System.out.println("Semana mais chuvosa:");
        System.out.println(maior[0]);
        System.out.println("Total:");
        System.out.println(formatar(Double.parseDouble(maior[9])) + " mm");
    }

    static void gerarDashboardClimatico() {
        ArrayList<String[]> registros = lerHistoricoChuvas();
        if (registros.isEmpty()) {
            System.out.println("Nenhuma semana registrada.");
            return;
        }
        double acumulado = 0;
        String[] maior = registros.get(0);
        String[] menor = registros.get(0);
        for (String[] registro : registros) {
            double total = Double.parseDouble(registro[9]);
            acumulado += total;
            if (total > Double.parseDouble(maior[9])) {
                maior = registro;
            }
            if (total < Double.parseDouble(menor[9])) {
                menor = registro;
            }
        }
        double mediaGeral = acumulado / registros.size();

        System.out.println("===== DASHBOARD =====");
        System.out.println("Semanas cadastradas: " + registros.size());
        System.out.println("Volume acumulado: " + formatar(acumulado) + " mm");
        System.out.println("Semana mais chuvosa: " + maior[0]);
        System.out.println("Maior chuva:");
        System.out.println(formatar(Double.parseDouble(maior[9])) + " mm");
        System.out.println("Semana mais seca: " + menor[0]);
        System.out.println("Menor chuva:");
        System.out.println(formatar(Double.parseDouble(menor[9])) + " mm");
        System.out.println("Média geral:");
        System.out.println(umaCasa(mediaGeral) + " mm");
    }

    static void cadastrarTalhoes() {
        System.out.println("Agora me diga a quantidade de umidade de cada talhao do campo 4x4 (em %):");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print("Talhão " + nomeTalhao(i, j) + ": ");
                campo[i][j] = scanner.nextDouble();
            }
        }
        salvarMapaTalhoes();
    }

    static void salvarMapaTalhoes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TALHOES))) {
            writer.write("talhao,umidade,status");
            writer.newLine();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    writer.write(nomeTalhao(i, j) + "," + formatar(campo[i][j]) + "," + classificarTalhao(campo[i][j]));
                    writer.newLine();
                }
            }
            System.out.println("Mapa de umidade salvo em " + ARQUIVO_TALHOES);
        } catch (IOException e) {
            System.out.println("Erro ao salvar mapa de umidade: " + e.getMessage());
        }
    }

    static void carregarMapaTalhoes() {
        File arquivo = new File(ARQUIVO_TALHOES);
        if (!arquivo.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 2 && partes[0].length() == 2) {
                    int i = partes[0].charAt(0) - 'A';
                    int j = partes[0].charAt(1) - '1';
                    if (i >= 0 && i < 4 && j >= 0 && j < 4) {
                        campo[i][j] = Double.parseDouble(partes[1]);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao carregar mapa de umidade: " + e.getMessage());
        }
    }

    static String classificarTalhao(double umidade) {
        if (umidade < 20) {
            return "Crítico";
        } else if (umidade <= 30) {
            return "Atenção";
        }
        return "Adequado";
    }

    static void exibirMapaCampo() {
        System.out.println("Mapa do campo (umidade em %):");
        System.out.print("\t");
        for (int j = 0; j < 4; j++) {
            System.out.print((j + 1) + "\t");
        }
        System.out.println();
        for (int i = 0; i < 4; i++) {
            System.out.print(LINHAS[i] + "\t");
            for (int j = 0; j < 4; j++) {
                System.out.print(formatar(campo[i][j]) + "\t");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("talhao,umidade,status");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.println(nomeTalhao(i, j) + "," + formatar(campo[i][j]) + "," + classificarTalhao(campo[i][j]));
            }
        }
    }

    static void gerarRelatorioIrrigacao() {
        int alertas = 0;
        System.out.println("===== ALERTAS =====");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String status = classificarTalhao(campo[i][j]);
                if (!status.equals("Adequado")) {
                    System.out.println(nomeTalhao(i, j) + " - " + status + " (" + formatar(campo[i][j]) + "%)");
                    alertas++;
                }
            }
        }
        System.out.println("Total de alertas: " + alertas);
    }

    static String montarRelatorioGerencial() {
        double soma = 0;
        int criticos = 0;
        int adequados = 0;
        int seco = 0;
        int umido = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                soma += campo[i][j];
                String status = classificarTalhao(campo[i][j]);
                if (status.equals("Crítico")) {
                    criticos++;
                } else if (status.equals("Adequado")) {
                    adequados++;
                }
                if (campo[i][j] < campo[seco / 4][seco % 4]) {
                    seco = i * 4 + j;
                }
                if (campo[i][j] > campo[umido / 4][umido % 4]) {
                    umido = i * 4 + j;
                }
            }
        }
        double media = soma / 16;
        int percentualIrrigacao = criticos * 100 / 16;

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Media Geral: ").append(formatar(Math.round(media * 10) / 10.0)).append("%\n");
        relatorio.append("Talhoes Criticos: ").append(criticos).append("\n");
        relatorio.append("Talhoes Adequados: ").append(adequados).append("\n");
        relatorio.append("Necessidade de Irrigacao:\n").append(percentualIrrigacao).append("%\n");
        relatorio.append("Talhao mais seco:\n").append(nomeTalhao(seco / 4, seco % 4)).append(" - ").append(formatar(campo[seco / 4][seco % 4])).append("%\n");
        relatorio.append("Talhao mais umido:\n").append(nomeTalhao(umido / 4, umido % 4)).append(" - ").append(formatar(campo[umido / 4][umido % 4])).append("%\n");
        return relatorio.toString();
    }

    static void gerarRelatorioGerencial() {
        System.out.println("===== RELATORIO DA FAZENDA =====");
        System.out.print(montarRelatorioGerencial());
    }

    static void exportarRelatorio() {
        System.out.print("Data do relatório (dd/MM/aaaa): ");
        String data = scanner.next();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_RELATORIO))) {
            writer.write("===== RELATORIO DA FAZENDA =====");
            writer.newLine();
            writer.write("Data: " + data);
            writer.newLine();
            writer.write(montarRelatorioGerencial());
            System.out.println("Relatório exportado para " + ARQUIVO_RELATORIO);
        } catch (IOException e) {
            System.out.println("Erro ao exportar relatório: " + e.getMessage());
        }
    }

    static String nomeTalhao(int i, int j) {
        return LINHAS[i] + (j + 1);
    }

    static String formatar(double valor) {
        if (valor == Math.floor(valor)) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(valor);
    }

    static String umaCasa(double valor) {
        return String.valueOf(Math.round(valor * 10) / 10.0);
    }
}
