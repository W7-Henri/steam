package view;
import controller.steamController;

import java.util.Scanner;

public class principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        steamController controller = new steamController();

        System.out.println("Escolha a operação:");
        System.out.println("1 - Exibir jogos por média");
        System.out.println("2 - Gerar relatório CSV");

        int opcao = scanner.nextInt();
        scanner.nextLine(); // limpa buffer

        System.out.print("Informe o ano: ");
        int ano = scanner.nextInt();

        System.out.print("Informe o mês: ");
        int mes = scanner.nextInt();

        if (opcao == 1) {
            System.out.print("Informe a média mínima: ");
            double media = scanner.nextDouble();
            controller.exibirJogosPorMedia(ano, mes, media);
        } else if (opcao == 2) {
            scanner.nextLine(); // limpar buffer
            System.out.print("Informe o diretório de destino (ex: C:\\TEMP): ");
            String diretorio = scanner.nextLine();

            System.out.print("Informe o nome do arquivo (ex: relatorio.csv): ");
            String nomeArquivo = scanner.nextLine();

            controller.gerarRelatorio(ano, mes, diretorio, nomeArquivo);
        } else {
            System.out.println("Opção inválida.");
        }

        scanner.close();
    }
}

