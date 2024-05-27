package src;
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
        Scanner console = new Scanner(System.in);
        ArrayList<Long> pesoItens = new ArrayList<>();
        ArrayList<Long> beneficioItens = new ArrayList<>();

        System.out.println("\u001B[33m" + "Digite a quantidade de itens a colocar na mochila" + "\u001B[0m");
        System.out.print(">");
        int quantidadeItens = Integer.parseInt(console.nextLine());

        System.out.println("\u001B[33m" + "Digite a Capacidade da Mochila" + "\u001B[0m");
        System.out.print(">");
        long capacidadeMochila = Long.parseLong(console.nextLine());

        System.out.println("\u001B[31m" + "Digite os pesos dos itens" + "\u001B[0m");
        for (int i=0; i<quantidadeItens; i++) {
            System.out.print(">");
            pesoItens.add(Long.parseLong(console.nextLine()));
        }

        System.out.println("\u001B[34m" + "Digite os benefícios dos itens" + "\u001B[0m");
        for (int i=0; i<quantidadeItens; i++) {
            System.out.print(">");
            beneficioItens.add(Long.parseLong(console.nextLine()));
        }

        System.out.println("\u001B[33m" + "[1] Solução de Força Bruta\n" + "[2] Solução Heurística" + "\u001B[0m");
        System.out.print(">");
        int opcao = Integer.parseInt(console.nextLine());

        if (opcao == 1) {
            try {
                if (quantidadeItens > 30) {
                    throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                }
                ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);
                mochila.calcularSolucao();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else if (opcao == 2) {
            ProblemaMochilaHeuristica mochila = new ProblemaMochilaHeuristica(capacidadeMochila,pesoItens,beneficioItens);
            mochila.calcularSolucao();
        }
    }
}
