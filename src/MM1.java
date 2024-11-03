import java.util.ArrayList;
import java.util.Arrays;

public class MM1 {
    public static void main(String[] args) {
        if (!(args.length == 4 || args.length == 6)) {
            System.out.println("Usage: java MM1 <lambda> <mu> <duration> <debug> <number of simulations> <graph>");
            System.exit(1);
        }
        double lambda = Double.parseDouble(args[0]);
        double mu = Double.parseDouble(args[1]);
        double duration = Double.parseDouble(args[2]);
        int debug = Integer.parseInt(args[3]);
        int nbOfSimulations = 1;
        int graph = 0;
        if (args.length == 6) {
            nbOfSimulations = Integer.parseInt(args[4]);
            graph = Integer.parseInt(args[5]);
        }
        // Data to make graph
        ArrayList<ArrayList<double[]>> allSimulationsData = new ArrayList<>();
        ArrayList<double[]> simulationData = new ArrayList<>();
        // Create ech and run the simulation and print result
        for(int i=0; i<nbOfSimulations; i++) {
             Ech ech = new Ech(lambda, mu);
             simulationData = ech.simulation(duration, debug);
             System.out.println("-------------------");
             System.out.println(Arrays.toString(simulationData.getFirst()));
             allSimulationsData.add(simulationData);
        }
        //Create graph if ask
        if(graph > 0){
            Graph graphAverageStayTime = new Graph("averageStayTime.txt");

            graphAverageStayTime.makeGraph(allSimulationsData, nbOfSimulations);
        }

    }
}
