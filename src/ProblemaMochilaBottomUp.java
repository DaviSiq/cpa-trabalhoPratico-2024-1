
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
        long resultado = calcularValorMaximoMochila((int)capacidadeMochila, pesoItens.size());
        System.out.println("\u001B[32m" + "Benefício da solução de Programação Dinâmica: " + resultado + "\u001B[0m");
    }

    private long calcularValorMaximoMochila(int capacidadeMochila, int quantidadeItens) {
        long[][] tabelaValores = new long[quantidadeItens + 1][capacidadeMochila + 1];
    
        for (int indiceItem = 0; indiceItem <= quantidadeItens; indiceItem++) {
            for (int pesoAtual = 0; pesoAtual <= capacidadeMochila; pesoAtual++) {
                if (indiceItem == 0 || pesoAtual == 0) {
                    tabelaValores[indiceItem][pesoAtual] = 0;
                } 
                else if (pesoItens.get(indiceItem - 1) <= pesoAtual) {
                    tabelaValores[indiceItem][pesoAtual] = Math.max(
                        beneficioItens.get(indiceItem - 1) + tabelaValores[indiceItem - 1][(int)(pesoAtual - pesoItens.get(indiceItem - 1))],
                        tabelaValores[indiceItem - 1][pesoAtual]
                    );
                } 
                else {
                    tabelaValores[indiceItem][pesoAtual] = tabelaValores[indiceItem - 1][pesoAtual];
                }
            }
        }
    
        return tabelaValores[quantidadeItens][capacidadeMochila];
    }
}
