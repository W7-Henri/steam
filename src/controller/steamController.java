package controller;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class steamController {

    private final String FILE_PATH = "C:\\TEMP\\SteamCharts.csv"; // ou onde o arquivo estiver

    // Método 1: Filtra e exibe no console
    public void exibirJogosPorMedia(int ano, int mes, double mediaEsperada) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha = br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",", -1);
                if (dados.length < 4) continue;

                String nome = dados[0].trim();
                int anoArquivo = Integer.parseInt(dados[1].trim());
                int mesArquivo = Integer.parseInt(dados[2].trim());
                double media = Double.parseDouble(dados[3].trim());

                if (anoArquivo == ano && mesArquivo == mes && media >= mediaEsperada) {
                    System.out.println(nome + " | " + media);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    // Método 2: Gera novo CSV com base nos filtros
    public void gerarRelatorio(int ano, int mes, String diretorioDestino, String nomeArquivo) {
        File dir = new File(diretorioDestino);
        if (!dir.exists()) {
            System.out.println("Diretório não existe: " + diretorioDestino);
            return;
        }

        File saida = new File(dir, nomeArquivo);
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
             BufferedWriter bw = new BufferedWriter(new FileWriter(saida))) {

            String linha = br.readLine(); // pula cabeçalho
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",", -1);
                if (dados.length < 4) continue;

                String nome = dados[0].trim();
                int anoArquivo = Integer.parseInt(dados[1].trim());
                int mesArquivo = Integer.parseInt(dados[2].trim());
                double media = Double.parseDouble(dados[3].trim());

                if (anoArquivo == ano && mesArquivo == mes) {
                    bw.write(nome + ";" + media);
                    bw.newLine();
                }
            }

            System.out.println("Relatório gerado em: " + saida.getAbsolutePath());

        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao processar arquivo: " + e.getMessage());
        }
    }
}
