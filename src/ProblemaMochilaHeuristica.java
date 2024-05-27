package src;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class ProblemaMochilaHeuristica {
    private class Tupla {
        public double first;
        public long second;

        public Tupla(double first, long second) {
            this.first = first;
            this.second = second;
        }
    }
    private long capacidadeMochila;
    private ArrayList<Long> pesoItens;
    private ArrayList<Long> beneficioItens;
    private PriorityQueue<Tupla> razaoPesoBeneficio;

    public ProblemaMochilaHeuristica(long capacidadeMochila, ArrayList<Long> pesoItens, ArrayList<Long> beneficioItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pesoItens = pesoItens;
        this.beneficioItens = beneficioItens;
        razaoPesoBeneficio = new PriorityQueue<>(Comparator.comparingDouble((Tupla t) -> t.first).reversed());
    }

    public void calcularSolucao () {
        definirPrioridades();
        long capacidadeAtual = 0;
        ArrayList<Long> elementosSolucao = new ArrayList<>();

        while (!razaoPesoBeneficio.isEmpty() && capacidadeAtual <= capacidadeMochila) {
            Tupla elemento = razaoPesoBeneficio.poll();
            if (pesoItens.get((int) elemento.second) + capacidadeAtual <= capacidadeMochila) {
                capacidadeAtual += pesoItens.get((int)elemento.second);
                elementosSolucao.add(elemento.second);
            }
            else {
                break;
            }
        }
        System.out.println(elementosSolucao);
    }

    private void definirPrioridades() {
        for (int i=0; i<pesoItens.size(); i++) {
            razaoPesoBeneficio.add(new Tupla((double)beneficioItens.get(i)/(double)pesoItens.get(i),i));
        }
    }
}
