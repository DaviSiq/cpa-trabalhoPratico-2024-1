package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Disciplina GCC253 - Complexidade e Projeto de Algoritmos
 * Professor: Vinícius Dias
 * Atividade Avaliativa 6 - Projeto Prático
 *
 * Implementação do Problema da Mochila:
 * "Dados um conjunto de n itens com seus respectivos pesos w1,... , wn e valores/benefícios v1,... , vn, e
 * uma capacidade de mochila W , o objetivo é encontrar um subconjunto de itens que caibam na mochila
 * (pesos) e que maximizem o benefício. Portanto, a entrada consiste de dois arranjos (w e v) e um número
 * (W ); e a saída consiste de um subconjunto I ∈ {1, 2,.. , n} de itens."
 *
 * Implementação 1 - Força Bruta
 * Implementação 2 - Heurística
 *
 * @author Francisco Afonso de Oliveira Neto
 * @author Lucas Gomes Colombo
 * @author Davi Hermogenes Siqueira
 * @author André Marçal Medeiros
 * @author Eduardo Dezena Goncalvez
 */
public class Main {

    public static void main(String[] args) {
        String inputFilePath = "arquivosTeste/entrada.txt";
        /* 
        Um disclaimer, para reconhecer os caminhos em java, 
        tem que colocar o caminho referente ao diretório
        central, ou seja 'arquivosTeste/entrada.txt', ../etc não funcionaria
        */
        ArrayList<Long> pesoItens = new ArrayList<>();
        ArrayList<Long> beneficioItens = new ArrayList<>();
        int quantidadeItens = 0;
        long capacidadeMochila = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            quantidadeItens = Integer.parseInt(br.readLine().trim());
            capacidadeMochila = Long.parseLong(br.readLine().trim());

            /*Le a qntd e a capacidade, dps imediatamente já começa a ler 
            os pesos e em seguida os beneficios.
            */
            
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

        System.out.println("\u001B[33m" + "[1] Solução de Força Bruta\n" + "[2] Solução Heurística" + "\u001B[0m");
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
            }
        }
    }
}
