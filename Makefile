# Variables
SRC_DIR = com
BIN_DIR = bin
MAIN_CLASS = com.schottenTotten.main.Main

# Récupérer tous les fichiers .java
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Règle par défaut : build
default: build

# Compilation
build:
	mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) -sourcepath $(SRC_DIR) $(SOURCES)
	@echo "Compilation terminée."

# Exécution
run: build
	java -cp $(BIN_DIR) $(MAIN_CLASS)

# Nettoyage des fichiers compilés
clean:
	rm -rf $(BIN_DIR)
	@echo "Dossier bin nettoyé."
