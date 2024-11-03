# Nom du fichier de données
set datafile separator "\t"
datafile = "../data/nbClientInQueue.txt"

# Nom du fichier de sortie (format PNG)
set terminal png size 800,600
set output "../graph/nbClientInQueueWithConfidence.png"

# Titres et labels
set title "Nombre de client dans la file"
set xlabel "Temps"
set ylabel "Nombre de client dans la file"

# Style de la bande de l'intervalle de confiance
set style fill transparent solid 0.2 noborder

# Instructions de tracé
plot datafile using 1:3:4 with filledcurves lc rgb "blue" title "Intervalle de confiance" \
     , datafile using 1:2 with lines lc rgb "red" title "Nombre de client moyen"