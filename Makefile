CC=javac
FILE=Main

all: $(FILE) run
	@echo "Fermeture du jeu"

$(FILE):
	@echo "Construction de la classe"
	$(CC) *.java

run: 
	java $(FILE)

clean:
	rm -f *.class