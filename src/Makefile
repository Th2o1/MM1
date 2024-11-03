# Variables
JAVAC = javac
JAVA = java
SRC_DIR = ../src
OUT_DIR = ../out
GRAPH_DIR = ../graph
DATA_DIR = ../data
PLOT_SCRIPT = ../graph/*.gnuplot
OUTPUT_IMAGE = ../graph/graph.png
MAIN_CLASS = MM1

#default
all: compile run
graph: compile runGraph plot

#exec
unstable: compile unstable
big: compile big
medium:compile medium
small: compile small

#graph
unstableG: compile unstableGraph plot
bigG: compile bigGraph plot
mediumG: compile mediumGraph plot
smallG: compile smallGraph plot
# Compilation des fichiers Java
compile:
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) $(SRC_DIR)/*.java

# Exécution du programme Java avec les arguments
run: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 1000 1
runGraph: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 1000 0 10 1
medium: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 10000 1
mediumGraph: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 10000 0 10 1
small: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 100 1
smallGraph: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 100 0 5 1
big: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 1000000 0
bigGraph: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 5 6 1000000 0 3 1
unstable: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 6 4 1000 1
unstableGraph: compile
	$(JAVA) -cp $(OUT_DIR) $(MAIN_CLASS) 6 3 1000 0 5 1

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
	@echo "----------------------------------------------------------------------------------------------------"
	@echo "make           : Compile le programme et exécute avec les arguments de l'exemple du TP (5 6 1000 1)"
	@echo "make graph     : Compile, exécute, puis génère le graphique avec gnuplot"
	@echo "make big       : Compile et exécute avec une grande quantité de données (5 6 1000000 0)"
	@echo "make medium    : Compile et exécute avec une quantité moyenne de données (5 6 10000 1)"
	@echo "make small     : Compile et exécute avec une petite quantité de données (5 6 100 1)"
	@echo "make bigG      : Compile et exécute big avec génération du graphique"
	@echo "make mediumG   : Compile et exécute medium avec génération du graphique"
	@echo "make smallG    : Compile et exécute small avec génération du graphique"
	@echo "----------------------------------------------------------------------------------------------------"
	@echo "make compile   : Compile tous les fichiers Java"
	@echo "make run       : Exécute le programme avec les arguments par défaut (5 6 1000 1)"
	@echo "make debug     : Exécute le programme en mode debug (5 6 10000 1)"
	@echo "make plot      : Génère le graphique avec gnuplot"
	@echo "make clean     : Supprime les fichiers compilés (.class) et les images générées"
	@echo "make help      : Affiche ce message d'aide"