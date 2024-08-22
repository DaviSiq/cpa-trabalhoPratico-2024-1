package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int numExecucoes = 10;
        String[] arquivosEntrada = {
            "testes/instancias_teste_apresentacao/entrada1.txt",
            "testes/instancias_teste_apresentacao/entrada2.txt",
            "testes/instancias_teste_apresentacao/entrada3.txt",
            "testes/instancias_teste_apresentacao/entrada4.txt",
            "testes/instancias_teste_apresentacao/entrada5.txt",
            "testes/instancias_teste_apresentacao/entrada6.txt",
            "testes/instancias_teste_apresentacao/entrada7.txt",
            "testes/instancias_teste_apresentacao/entrada8.txt"
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
            for (int opcao = 1; opcao <= 4; opcao++) {
                long totalElapsedTime = 0;

                for (int i = 0; i < numExecucoes; i++) {
                    long startTime = 0, endTime = 0;

                    if (opcao == 1) {
                        try {
                            if (quantidadeItens > 30) {
                                throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                            }
                            ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);

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
                        mochila.calcularSolucao();
                        endTime = System.nanoTime();
                    } else if (opcao == 3) {
                        ProblemaMochilaTopDown mochila = new ProblemaMochilaTopDown(capacidadeMochila, pesoItens, beneficioItens);

                        startTime = System.nanoTime();
                        mochila.calcularSolucao();
                        endTime = System.nanoTime();
                    } else if (opcao == 4) {
                        ProblemaMochilaBottomUp mochila = new ProblemaMochilaBottomUp(capacidadeMochila, pesoItens, beneficioItens);

                        startTime = System.nanoTime();
                        mochila.calcularSolucao();
                        endTime = System.nanoTime();
                    }

                    long elapsedTime = endTime - startTime;
                    totalElapsedTime += elapsedTime;
                }

                long averageTime = totalElapsedTime / numExecucoes;
                String nomeAlgoritmo = getNomeAlgoritmo(opcao);
                mediasAlgoritmos.put(nomeAlgoritmo, averageTime);
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
                return "Top-Down (Memoization)";
            case 4:
                return "Bottom-Up (Programação Dinâmica)";
            default:
                return "Desconhecido";
        }
    }
}





/*
package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "testes/instancias_teste_apresentacao/entrada8.txt";

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
            int numExecucoes = 10;
            long startTime, endTime, elapsedTime;
            long totalElapsedTime = 0;

            for (int i = 0; i < numExecucoes; i++) {
                if (opcao == 1) {
                    try {
                        if (quantidadeItens > 30) {
                            throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                        }
                        ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);
                        
                        startTime = System.nanoTime();
                        mochila.calcularSolucao();
                        endTime = System.nanoTime();
                        
                        elapsedTime = endTime - startTime;
                        totalElapsedTime += elapsedTime;
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                } else if (opcao == 2) {
                    ProblemaMochilaHeuristica mochila = new ProblemaMochilaHeuristica(capacidadeMochila, pesoItens, beneficioItens);
                    
                    startTime = System.nanoTime();
                    mochila.calcularSolucao();
                    endTime = System.nanoTime();
                    
                    elapsedTime = endTime - startTime;
                    totalElapsedTime += elapsedTime;

                } else if (opcao == 3) {
                    ProblemaMochilaTopDown mochila = new ProblemaMochilaTopDown(capacidadeMochila, pesoItens, beneficioItens);
                    
                    startTime = System.nanoTime();
                    mochila.calcularSolucao();
                    endTime = System.nanoTime();
                    
                    elapsedTime = endTime - startTime;
                    totalElapsedTime += elapsedTime;

                } else if (opcao == 4) {
                    ProblemaMochilaBottomUp mochila = new ProblemaMochilaBottomUp(capacidadeMochila, pesoItens, beneficioItens);
                    
                    startTime = System.nanoTime();
                    mochila.calcularSolucao();
                    endTime = System.nanoTime();
                    
                    elapsedTime = endTime - startTime;
                    totalElapsedTime += elapsedTime;
                }
            }

            // Calculando a média do tempo de execução
            long averageTime = totalElapsedTime / numExecucoes;
            System.out.println("Média de tempo de execução: " + averageTime + " nanosegundos");
        }
    }
}
*/




/*
package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "testes/instancias_teste_apresentacao/entrada8.txt";

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
            long startTime, endTime, elapsedTime;

            if (opcao == 1) {
                try {
                    if (quantidadeItens > 30) {
                        throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                    }
                    ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);
                    
                    // Captura o tempo antes da execução do algoritmo
                    startTime = System.nanoTime();
                    mochila.calcularSolucao();
                    // Captura o tempo após a execução do algoritmo
                    endTime = System.nanoTime();
                    
                    // Calcula o tempo de execução
                    elapsedTime = endTime - startTime;
                    System.out.println("Tempo de execução (Força Bruta): " + elapsedTime + " nanosegundos");
                    
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcao == 2) {
                ProblemaMochilaHeuristica mochila = new ProblemaMochilaHeuristica(capacidadeMochila, pesoItens, beneficioItens);
                
                startTime = System.nanoTime();
                mochila.calcularSolucao();
                endTime = System.nanoTime();
                
                elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução (Heurística): " + elapsedTime + " nanosegundos");

            } else if (opcao == 3) {
                ProblemaMochilaTopDown mochila = new ProblemaMochilaTopDown(capacidadeMochila, pesoItens, beneficioItens);
                
                startTime = System.nanoTime();
                mochila.calcularSolucao();
                endTime = System.nanoTime();
                
                elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução (Top-Down): " + elapsedTime + " nanosegundos");

            } else if (opcao == 4) {
                ProblemaMochilaBottomUp mochila = new ProblemaMochilaBottomUp(capacidadeMochila, pesoItens, beneficioItens);
                
                startTime = System.nanoTime();
                mochila.calcularSolucao();
                endTime = System.nanoTime();
                
                elapsedTime = endTime - startTime;
                System.out.println("Tempo de execução (Bottom-Up): " + elapsedTime + " nanosegundos");
            }
        }
    }
}
*/



/* 
package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "testes/instancias_teste_apresentacao/entrada7.txt";

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
            long startTime, endTime, elapsedTime;
            if (opcao == 1) {
                try {
                    if (quantidadeItens > 30) {
                        throw new StackOverflowError("A complexidade do Algoritmo não suporta uma entrada tão grande!");
                    }
                    ProblemaMochilaForcaBruta mochila = new ProblemaMochilaForcaBruta(capacidadeMochila, pesoItens, beneficioItens);
                    startTime = System.nanoTime();
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
*/