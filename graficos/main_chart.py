import matplotlib.pyplot as plt
import numpy as np

# Nomes das entradas
entradas = [f"Entrada {i}" for i in range(1, 9)]

# Dados dos tempos em nanosegundos para cada entrada e algoritmo
tempos = {
    "Força Bruta": [1902650, 305830, 367660, 310530, 395150, 519920, 1314050, 4959790],
    "Heurística": [210130, 143070, 186930, 142250, 158080, 217260, 185960, 220220],
    "Top-Down": [254000, 153800, 154590, 167030, 121270, 154350, 171110, 240740],
    "Bottom-Up": [245170, 187860, 212390, 340110, 396120, 487520, 200570, 361210]
}

# Número de entradas e largura das barras
n_entradas = len(entradas)
bar_width = 0.2

# Definindo a posição das barras para cada algoritmo
index = np.arange(n_entradas)

# Plotando as barras
fig, ax = plt.subplots()
ax.bar(index, tempos["Força Bruta"], bar_width, label='Força Bruta', color='red')
ax.bar(index + bar_width, tempos["Heurística"], bar_width, label='Heurística', color='blue')
ax.bar(index + 2 * bar_width, tempos["Top-Down"], bar_width, label='Top-Down', color='green')
ax.bar(index + 3 * bar_width, tempos["Bottom-Up"], bar_width, label='Bottom-Up', color='purple')

# Adicionando legendas e títulos
ax.set_xlabel('Entradas')
ax.set_ylabel('Tempo Médio (nanosegundos)')
ax.set_title('Tempo Médio por Algoritmo e Entrada')
ax.set_xticks(index + 1.5 * bar_width)
ax.set_xticklabels(entradas)
ax.legend()

# Exibindo o gráfico
plt.tight_layout()
plt.show()
