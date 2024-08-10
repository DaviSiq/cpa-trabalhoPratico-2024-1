package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "arquivosTeste/entrada1.txt";

        ArrayList<Long> pesoItens = new ArrayList<>();
        ArrayList<Long> beneficioItens = new ArrayList<>();
        int quantidadeItens = 0;
        long capacidadeMochila = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            quantidadeItens = Integer.parseInt(br.readLine().trim());
            capacidadeMochila = Long.parseLong(br.readLine().trim());
            
            for (int i = 0; i < quantidadeItens; i++) {
                pesoItens.add(Long.parseLong(br.readLine().trim()));
            }

            // Pulando a linha vazia
            br.readLine();

            for (int i = 0; i < quantidadeItens; i++) {
                beneficioItens.add(Long.parseLong(br.readLine().trim()));
            }
        } catch (IOException e) {
            System.err.println("Erro lendo o arquivo de entrada: " + e.getMessage());
            return;
        }

        System.out.println("\u001B[33m" + "[1] Solução de Força Bruta\n" 
            + "[2] Solução Heurística\n" 
            + "[3] Solução Top-Down (Memoization)\n" 
            + "[4] Solução Bottom-Up (Programação Dinâmica)" + "\u001B[0m");
        System.out.print(">");

        try (Scanner console = new Scanner(System.in)) {
            int opcao = Integer.parseInt(console.nextLine());

            if (opcao == 1) {
                try {
                    if (quantidadeItens > 30) {
                        throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                    }
                    ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);
                    mochila.calcularSolucao();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcao == 2) {
                ProblemaMochilaHeuristica mochila = new ProblemaMochilaHeuristica(capacidadeMochila, pesoItens, beneficioItens);
                mochila.calcularSolucao();
            } else if (opcao == 3) {
                ProblemaMochilaTopDown mochila = new ProblemaMochilaTopDown(capacidadeMochila, pesoItens, beneficioItens);
                mochila.calcularSolucao();
            } else if (opcao == 4) {
                ProblemaMochilaBottomUp mochila = new ProblemaMochilaBottomUp(capacidadeMochila, pesoItens, beneficioItens);
                mochila.calcularSolucao();
            }
        }
    }
}
