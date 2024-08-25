
import java.util.ArrayList;

public class ProblemaMochilaBottomUp {
    private long capacidadeMochila;
    private ArrayList<Long> pesoItens;
    private ArrayList<Long> beneficioItens;

    public ProblemaMochilaBottomUp(long capacidadeMochila, ArrayList<Long> pesoItens, ArrayList<Long> beneficioItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pesoItens = pesoItens;
        this.beneficioItens = beneficioItens;
    }

    public void calcularSolucao() {
        long resultado = knapSack((int)capacidadeMochila, pesoItens.size());
        System.out.println("\u001B[32m" + "Benefício da solução ótima (Bottom-Up): " + resultado + "\u001B[0m");
    }

    private long knapSack(int W, int n) {
        long[][] K = new long[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (pesoItens.get(i - 1) <= w)
                    K[i][w] = Math.max(beneficioItens.get(i - 1) + K[i - 1][(int)(w - pesoItens.get(i - 1))],
                                       K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        return K[n][W];
    }
}
