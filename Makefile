# Parâmetro padrão: arquivo de entrada
input ?= "testes/entrada1.txt"

# Compilador
JAVAC = javac
JAVA = java

# Diretório de origem
SRC_DIR = src

# Lista de arquivos-fonte
SOURCES = $(SRC_DIR)/Main.java $(SRC_DIR)/ProblemaMochilaForcaBruta.java $(SRC_DIR)/ProblemaMochilaHeuristica.java

# Diretório de classes compiladas
CLASS_DIR = bin

# Regra default: compilar os arquivos Java
all: $(CLASS_DIR) $(CLASS_DIR)/Main.class

# Cria o diretório de classes compiladas, se não existir
$(CLASS_DIR):
	mkdir -p $(CLASS_DIR)

# Compila os arquivos Java
$(CLASS_DIR)/Main.class: $(SOURCES)
	$(JAVAC) -d $(CLASS_DIR) $(SOURCES)

# Executa o programa com entrada default ou especificada
run: $(CLASS_DIR)/Main.class
	$(JAVA) -cp $(CLASS_DIR) src.Main < $(input)

# Limpa os arquivos compilados
clean:
	rm -rf $(CLASS_DIR)