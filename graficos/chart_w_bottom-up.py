import matplotlib.pyplot as plt
import numpy as np

# Nomes das entradas
entradas = [f"Entrada {i}" for i in range(1, 11)]

# Dados dos tempos em nanosegundos para cada entrada e algoritmo
tempos = {
    "Força Bruta": [
        1997750, 647620, 10306070, 2034010, 2938470, 722620, 87220920, 567849710,
        5627804330, 7785324190
    ],
    "Heurística": [
        209620, 284950, 218440, 185560, 161670, 198180, 211670, 208210,
        204040, 208340
    ],
    "Bottom-Up": [
        760340, 1505880, 268570, 840320, 454980, 783740, 294570, 278960,
        264370, 2268540
    ]
}

# Número de entradas e largura das barras
n_entradas = len(entradas)
bar_width = 0.25

# Definindo a posição das barras para cada algoritmo
index = np.arange(n_entradas)

# Plotando as barras
fig, ax = plt.subplots(figsize=(14, 8))
ax.bar(index - bar_width, tempos["Força Bruta"], bar_width, label='Força Bruta', color='red')
ax.bar(index, tempos["Heurística"], bar_width, label='Heurística', color='blue')
ax.bar(index + bar_width, tempos["Bottom-Up"], bar_width, label='Bottom-Up', color='purple')

# Adicionando legendas e títulos
ax.set_xlabel('Entradas')
ax.set_ylabel('Tempo Médio (nanosegundos)')
ax.set_title('Tempo Médio por Algoritmo e Entrada')

# Usando escala logarítmica para o eixo Y
ax.set_yscale('log')

ax.set_xticks(index)
ax.set_xticklabels(entradas, rotation=45, ha="right")
ax.legend()

# Exibindo o gráfico
plt.tight_layout()
plt.show()
