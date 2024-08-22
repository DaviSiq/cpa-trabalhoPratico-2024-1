import plotly.graph_objects as go
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

# Definindo as cores
colors = ['#FF6F61', '#6B5B95', '#88B04B', '#92A8D1']

# Criando a figura
fig = go.Figure()

# Adicionando barras para cada algoritmo
fig.add_trace(go.Bar(
    x=entradas,
    y=tempos["Força Bruta"],
    name='Força Bruta',
    marker_color=colors[0]
))
fig.add_trace(go.Bar(
    x=entradas,
    y=tempos["Heurística"],
    name='Heurística',
    marker_color=colors[1]
))
fig.add_trace(go.Bar(
    x=entradas,
    y=tempos["Top-Down"],
    name='Top-Down',
    marker_color=colors[2]
))
fig.add_trace(go.Bar(
    x=entradas,
    y=tempos["Bottom-Up"],
    name='Bottom-Up',
    marker_color=colors[3]
))

# Layout do gráfico
fig.update_layout(
    title='Tempo Médio por Algoritmo e Entrada',
    xaxis_title='Entradas',
    yaxis_title='Tempo Médio (nanosegundos)',
    barmode='group',  # Barras lado a lado
    template='plotly_dark',  # Tema escuro estilizado
    title_font=dict(size=24, color='white'),
    xaxis=dict(tickfont=dict(size=14)),
    yaxis=dict(tickfont=dict(size=14)),
    legend=dict(font=dict(size=14)),
    paper_bgcolor='rgba(0,0,0,0)',  # Fundo transparente
    plot_bgcolor='rgba(0,0,0,0)',   # Fundo do gráfico transparente
)

# Adicionando valores nas barras
fig.update_traces(texttemplate='%{y:.0f}', textposition='outside', textfont_size=12)

# Exibindo o gráfico
fig.show()
