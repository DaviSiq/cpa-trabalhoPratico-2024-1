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

# Utilizando um estilo de tema disponível no Matplotlib
plt.style.use('ggplot')  # Ou 'bmh', 'fivethirtyeight', 'dark_background', etc.

# Cores vibrantes para as barras
colors = ['#FF6F61', '#6B5B95', '#88B04B', '#92A8D1']

# Criando a figura e os eixos
fig, ax = plt.subplots(figsize=(12, 6))

# Plotando as barras para cada algoritmo
bars1 = ax.bar(index, tempos["Força Bruta"], bar_width, label='Força Bruta', color=colors[0])
bars2 = ax.bar(index + bar_width, tempos["Heurística"], bar_width, label='Heurística', color=colors[1])
bars3 = ax.bar(index + 2 * bar_width, tempos["Top-Down"], bar_width, label='Top-Down', color=colors[2])
bars4 = ax.bar(index + 3 * bar_width, tempos["Bottom-Up"], bar_width, label='Bottom-Up', color=colors[3])

# Adicionando legendas e títulos
ax.set_xlabel('Entradas', fontsize=14, weight='bold')
ax.set_ylabel('Tempo Médio (nanosegundos)', fontsize=14, weight='bold')
ax.set_title('Tempo Médio por Algoritmo e Entrada', fontsize=16, weight='bold')
ax.set_xticks(index + 1.5 * bar_width)
ax.set_xticklabels(entradas, fontsize=12)
ax.legend(loc='best', fontsize=12)

# Adicionando os valores nas barras
def add_labels(bars):
    for bar in bars:
        height = bar.get_height()
        ax.annotate(f'{height}',
                    xy=(bar.get_x() + bar.get_width() / 2, height),
                    xytext=(0, 3),  # 3 points vertical offset
                    textcoords="offset points",
                    ha='center', va='bottom', fontsize=10, color='black', weight='bold')

# Chamando a função para adicionar os rótulos
add_labels(bars1)
add_labels(bars2)
add_labels(bars3)
add_labels(bars4)

# Melhorando a disposição do gráfico
plt.tight_layout()

# Exibindo o gráfico
plt.show()
