import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int numExecucoes = 1;
        String[] arquivosEntrada = {
            "testes/casosSimples/entrada1.txt",
            "testes/casosSimples/entrada2.txt"
        };

        // Map para armazenar os resultados: chave é o nome do arquivo, valor é outro map com os resultados dos algoritmos
        Map<String, Map<String, Long>> resultados = new HashMap<>();

        for (String inputFilePath : arquivosEntrada) {
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
                continue;
            }

            // Inicializa o map de resultados para o arquivo atual
            Map<String, Long> mediasAlgoritmos = new HashMap<>();

            // Executar para cada algoritmo e calcular a média
            for (int opcao = 1; opcao <= 3; opcao++) {
                long totalElapsedTime = 0;

                for (int i = 0; i < numExecucoes; i++) {
                    long startTime = 0, endTime = 0;

                    if (opcao == 1) {
                        try {
                            if (quantidadeItens > 30) {
                                throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                            }
                            ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);
                            System.out.println("Força Bruta: ");
                            startTime = System.nanoTime();
                            mochila.calcularSolucao();
                            endTime = System.nanoTime();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            return;
                        }
                    } else if (opcao == 2) {
                        ProblemaMochilaHeuristica mochila = new ProblemaMochilaHeuristica(capacidadeMochila, pesoItens, beneficioItens);

                        startTime = System.nanoTime();
                        System.out.println("Heurística: ");
                        mochila.calcularSolucao();
                        endTime = System.nanoTime();
                    } else if (opcao == 3) {
                        ProblemaMochilaBottomUp mochila = new ProblemaMochilaBottomUp(capacidadeMochila, pesoItens, beneficioItens);

                        startTime = System.nanoTime();
                        System.out.println("Bottom-up:");
                        mochila.calcularSolucao();
                        endTime = System.nanoTime();
                    }

                    long elapsedTime = endTime - startTime;
                    //totalElapsedTime += elapsedTime;
                    totalElapsedTime = elapsedTime;
                }

                //long averageTime = totalElapsedTime / numExecucoes;
                String nomeAlgoritmo = getNomeAlgoritmo(opcao);
                mediasAlgoritmos.put(nomeAlgoritmo,totalElapsedTime);
            }

            // Armazena os resultados para o arquivo de entrada atual
            resultados.put(inputFilePath, mediasAlgoritmos);
        }

        // Exibe os resultados
        for (String arquivo : resultados.keySet()) {
            System.out.println("Resultados para " + arquivo + ":");
            Map<String, Long> medias = resultados.get(arquivo);
            for (String algoritmo : medias.keySet()) {
                System.out.println("  " + algoritmo + ": " + medias.get(algoritmo) + " nanosegundos");
            }
            System.out.println();
        }
    }

    private static String getNomeAlgoritmo(int opcao) {
        switch (opcao) {
            case 1:
                return "Força Bruta";
            case 2:
                return "Heurística";
            case 3:
                return "Bottom-Up (Programação Dinâmica)";
            default:
                return "Desconhecido";
        }
    }
}




