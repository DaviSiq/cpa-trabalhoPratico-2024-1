package src;
import java.util.ArrayList;

public class ProblemaMochilaTopDown {
    private long capacidadeMochila;
    private ArrayList<Long> pesoItens;
    private ArrayList<Long> beneficioItens;
    private long[][] dp;

    public ProblemaMochilaTopDown(long capacidadeMochila, ArrayList<Long> pesoItens, ArrayList<Long> beneficioItens) {
        this.capacidadeMochila = capacidadeMochila;
        this.pesoItens = pesoItens;
        this.beneficioItens = beneficioItens;
        this.dp = new long[beneficioItens.size() + 1][(int)capacidadeMochila + 1];

        // Inicializando a tabela com -1
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
    }

    public void calcularSolucao() {
        long resultado = knapSackRec((int)capacidadeMochila, pesoItens.size());
        System.out.println("\u001B[32m" + "Benefício da solução ótima (Top-Down): " + resultado + "\u001B[0m");
    }

    private long knapSackRec(int W, int n) {
        if (n == 0 || W == 0) return 0;

        if (dp[n][W] != -1) return dp[n][W];

        if (pesoItens.get(n - 1) > W)
            return dp[n][W] = knapSackRec(W, n - 1);
        else
            return dp[n][W] = Math.max(beneficioItens.get(n - 1) + knapSackRec(W - pesoItens.get(n - 1).intValue(), n - 1), 
                                       knapSackRec(W, n - 1));
    }
}
