# Variables
JAVAC = javac
JAVA = java
SRC_DIR = ../src
OUT_DIR = ../out
GRAPH_DIR = ../graph
DATA_DIR = ../data
PLOT_SCRIPT = ../graph/averageStayTimePlot.gnuplot
OUTPUT_IMAGE = ../graph/graph.png
MAIN_CLASS = MM1

# Cible par défaut : compile et génère le graph
all: compile run plot
smallGraph: compile small plot
# Compilation des fichiers Java
compile:
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $(SRC_DIR)/*.java

# Exécution du programme Java avec les arguments
run: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 10000 0 5 1
small: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 100 1 3 1
big: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 1000000 0
debug: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 10000 1
# Génération du graphique avec Gnuplot
plot:
	gnuplot $(PLOT_SCRIPT)

# Nettoyage des fichiers compilés et du graph généré
clean:
	rm -rf $(OUT_DIR)/*.class
	rm -f $(GRAPH_DIR)/*.png
	rm -rf $(DATA_DIR)/*.txt

# Aide pour utiliser le Makefile
help:
	@echo "Utilisation du Makefile :"
	@echo "make           : Compile, exécute le programme, puis génère le graph avec gnuplot"
	@echo "make compile   : Compile tous les fichiers Java"
	@echo "make run       : Exécute le programme avec les arguments 5 6 10000 0"
	@echo "make plot      : Génère le graphique avec gnuplot"
	@echo "make clean     : Nettoie les fichiers .class et l'image générée"
	@echo "make help      : Affiche ce message d'aide"