
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class ProblemaMochilaHeuristica {
    // Classe interna Tupla que armazena a razão benefício/peso e o índice do item
    private class Tupla {
        public double first; // Razão benefício/peso
        public long second;  // Índice do item

        // Construtor que inicializa a razão e o índice do item
        public Tupla(double first, long second) {
            this.first = first;
            this.second = second;
        }
    }
    // Atributos da classe principal
    private long capacidadeMochila;
    private ArrayList<Long> pesoItens;
    private ArrayList<Long> beneficioItens;
    private PriorityQueue<Tupla> razaoPesoBeneficio;

    // Construtor que inicializa a capacidade da mochila, pesos, benefícios e a fila de prioridade
    public ProblemaMochilaHeuristica(long capacidadeMochila, ArrayList<Long> pesoItens, ArrayList<Long> beneficioItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pesoItens = pesoItens;
        this.beneficioItens = beneficioItens;

        // Inicializa a fila de prioridade, ordenando os itens pela razão benefício/peso em ordem decrescente
        razaoPesoBeneficio = new PriorityQueue<>(Comparator.comparingDouble((Tupla t) -> t.first).reversed());
    }
    // Método para calcular a solução heurística
    public void calcularSolucao () {
        // Preenche a fila de prioridade com os itens ordenados pela razão benefício/peso
        definirPrioridades();
        long capacidadeAtual = 0; // Capacidade utilizada da mochila até o momento
        ArrayList<Long> elementosSolucao = new ArrayList<>();
        long beneficioTotal = 0;

        // Enquanto ainda houver capacidade na mochila e itens na fila de prioridade
        while (!razaoPesoBeneficio.isEmpty() && capacidadeAtual <= capacidadeMochila) {
            // Retira o item com a maior razão benefício/peso da fila
            Tupla elemento = razaoPesoBeneficio.poll();
            
            // Verifica se o item cabe na mochila sem exceder a capacidade
            if (pesoItens.get((int) elemento.second) + capacidadeAtual <= capacidadeMochila) {
                capacidadeAtual += pesoItens.get((int)elemento.second);     // Adiciona o peso do item à capacidade atual
                beneficioTotal += beneficioItens.get((int)elemento.second);
                elementosSolucao.add(elemento.second);                      // Adiciona o índice do item à lista de solução
            }
            else {
                break;  // Se o próximo item não cabe na mochila, interrompe o loop
            }
        }
        System.out.println("Posição dos itens da solução: ");
        System.out.println(elementosSolucao);
        System.out.println("Beneficio total: " + beneficioTotal);
    }

    // Método privado para calcular a razão benefício/peso de cada item e adicioná-los à fila de prioridade
    private void definirPrioridades() {
        // Para cada item, calcula a razão e a adiciona na fila de prioridade
        for (int i=0; i<pesoItens.size(); i++) {
            // Adiciona uma nova Tupla contendo a razão benefício/peso e o índice do item
            razaoPesoBeneficio.add(new Tupla((double)beneficioItens.get(i)/(double)pesoItens.get(i),i));
        }
    }
}
