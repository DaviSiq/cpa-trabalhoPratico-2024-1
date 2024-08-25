
import java.util.ArrayList;

/**
 * Implementação do Algoritmo de Força Bruta
 * Dado as listas de pesos e benefícios de cada item, gerar todas as possíveis
 * soluções e retornar a solução com o melhor benefício.
 */
public class ProblemaMochilaForcaBruta {
    private long capacidadeMochila;
    private ArrayList<Long> pesoItens;
    private ArrayList<Long> beneficioItens;
    private long melhorBeneficio;
    private ArrayList<Long> melhorSolucao;

    public ProblemaMochilaForcaBruta(long capacidadeMochila, ArrayList<Long> pesoItens, ArrayList<Long> beneficioItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pesoItens = pesoItens;
        this.beneficioItens = beneficioItens;
        this.melhorBeneficio = 0;
        this.melhorSolucao = new ArrayList<>();
    }

    public void calcularSolucao() {
        gerarCombinacoes(0, new ArrayList<>(), 0, 0);
        System.out.println("\u001B[32m" + "Benefício da  solução otima: " + melhorBeneficio + "\u001B[0m");
        System.out.println("\u001B[32m" + "Itens da solução otima: " + melhorSolucao + "\u001B[0m");
    }

    /**
     * Chamadas recursivas para gerar todas as possíveis combinações
     * Para cada índice dos vetores de pesos e benefícios, faça 2 chamadas recursivas
     * - Uma contendo o elemento do índice atual
     * - Outra excluíndo o elemento do índice atual
     * O critério de parada é um dos seguintes:
     * - Cheguei ao último elemento dos vetores
     * - A mochila atingiu a capacidade máxima //verificar isso aqui dps (davi)
     *
     * @param indice
     * @param itensAtuais
     * @param pesoAtual
     * @param beneficioAtual
     */
    //esse condicional ae é o critério de parada
    public void gerarCombinacoes(int indice, ArrayList<Long> itensAtuais, long pesoAtual, long beneficioAtual) {
        if (indice == pesoItens.size() || pesoAtual > capacidadeMochila) {
            if (beneficioAtual > melhorBeneficio && pesoAtual <= capacidadeMochila) {
                melhorBeneficio = beneficioAtual;
                melhorSolucao = new ArrayList<>(itensAtuais);
            }
            return;
        }

        itensAtuais.add(pesoItens.get(indice));
        gerarCombinacoes(indice + 1, itensAtuais, pesoAtual + pesoItens.get(indice), beneficioAtual + beneficioItens.get(indice));

        itensAtuais.remove(pesoItens.get(indice));
        gerarCombinacoes(indice + 1, itensAtuais, pesoAtual, beneficioAtual);
    }
}
