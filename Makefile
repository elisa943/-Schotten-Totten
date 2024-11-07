CC=javac
FILE=Main

all: $(FILE) run
	@echo "Fermeture du jeu"

$(FILE):
	$(CC) *.java

run: 
	java $(FILE)

clean:
	rm -f *.class