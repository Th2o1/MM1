import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    FileWriter writer;

    public Graph(String fileName) {
        //Create the fileWriter
        try {
            writer = new FileWriter("../data/"+fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeData(ArrayList<double[]> results)  {
        try {
            for (double[] data : results) {
                writer.write(data[0] + "\t" + data[1] + "\t" + data[2] + "\t" + data[3] + "\n");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture des données : " + e.getMessage());
        }
    }

    public void makeGraph(ArrayList<ArrayList<double[]>> rawData, int nbOfSimulations){
        // discretisation of the data
        int intervalSize = 1; // inter for discretisation
        Map<Integer, ArrayList<Double>> aggregatedData = aggregateData(rawData, intervalSize);
        // Calc mean and confidence interval
        ArrayList<double[]> results = calculateMeanAndConfidence(aggregatedData, nbOfSimulations, intervalSize);
        // Write the data in a .txt for gnuplot
        writeData(results);
    }

    // map data by using discretisation
    private static Map<Integer, ArrayList<Double>> aggregateData(ArrayList<ArrayList<double[]>> allData, int intervalSize) {
        Map<Integer, ArrayList<Double>> aggregatedData = new HashMap<>();

        for (ArrayList<double[]> simulationData : allData) {
            for (double[] point : simulationData) {
                int timeInterval = (int) (point[0] / intervalSize); // Calc the frame
                aggregatedData.putIfAbsent(timeInterval, new ArrayList<>());
                aggregatedData.get(timeInterval).add(point[1]); // add 2nd value
            }
        }

        return aggregatedData;
    }

    private static ArrayList<double[]> calculateMeanAndConfidence(Map<Integer, ArrayList<Double>> aggregatedData,int nbSimulations, int intervalSize) {
        ArrayList<double[]> results = new ArrayList<>();
        double z = 1.96; // Valeur pour un intervalle de confiance de 95%

        for (Map.Entry<Integer, ArrayList<Double>> entry : aggregatedData.entrySet()) {
            int interval = entry.getKey();
            ArrayList<Double> stayTimes = entry.getValue();

            // Calcul de la moyenne
            double sum = 0;
            for (double stayTime : stayTimes) {
                sum += stayTime;
            }
            double mean = sum / stayTimes.size();

            // Calcul de l'intervalle de confiance
            double sumSquaredDiffs = 0;
            for (double stayTime : stayTimes) {
                double diff = stayTime - mean;
                sumSquaredDiffs += diff * diff;
            }
            double variance = sumSquaredDiffs / (stayTimes.size() - 1);
            double standardError = Math.sqrt(variance / nbSimulations);
            double confidenceInterval = z * standardError;

            results.add(new double[]{interval * intervalSize, mean, mean - confidenceInterval, mean + confidenceInterval});
        }

        return results;
    }
}

