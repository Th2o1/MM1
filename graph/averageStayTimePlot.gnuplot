set terminal png size 800,600
set output 'graph.png'
set xlabel 'X-axis Label'
set ylabel 'Y-axis Label'
set title 'Graph Title'
plot 'example.txt' using 2:1 with lines title 'Data'