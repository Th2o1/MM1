public class Stats {
    //lambda<mu : file stable
    //ro (lambda/mu) = 0.8333333333333334
    //nombre de clients attendus (lambda x duree) = 5000.0
    //Prob de service sans attente (1 - ro) = 0.16666666666666663
    //Prob file occupee (ro) = 0.8333333333333334
    //Debit (lambda) = 5.0
    //Esp nb clients (ro/1-ro) = 5.000000000000002
    //Temps moyen de sejour (1/mu(1-ro)) = 1.0000000000000002
    //Theorical data
    private final double mu;
    private final double lambda;
    private final double duration;
    private final double ro;
    private final double expectedClient;
    private final double serviceWithoutWaiting;
    private final double espNbClient;
    private final double AverageLengthStay;
    private final String QueueStability;
    //Simulation Data
    private int nbClient = 1;
    private double ClientWithoutWaiting;
    private double ClientWithWaiting;
    private double totalLengthOfStay;

    public Stats(double lambda, double mu, double duration) {
        this.mu = mu;
        if(lambda <= 0){
            lambda = 1;
        }
        this.lambda = lambda;
        this.duration = duration;
        this.ro = lambda / mu;
        this.expectedClient = lambda*duration;
        this.serviceWithoutWaiting = 1 - ro;
        this.espNbClient = ro/(1-ro);
        this.AverageLengthStay = 1/(mu*(1-ro));
        this.QueueStability = lambda<mu ? "file stable" : "file instable";

    }

    @Override
    public String toString() {
        // calculate % of client that w8 vs dont by divide them by the total nb of client
        double proportionNoWaiting = ClientWithoutWaiting / nbClient;
        double proportionWaiting = ClientWithWaiting / nbClient;
        double debit = nbClient / duration;
        double meanNumberOfClientWaiting = ClientWithWaiting / duration;
        double averageLengthOfStay = totalLengthOfStay / nbClient;
        // Big print
        return  "--------------------\n" +
                "RESULTATS THEORIQUES\n" +
                "--------------------\n" +
                "lambda<mu : "+QueueStability+"\n" +
                "    ro (lambda/mu) = "+ ro +"\n" +
                "    nombre de clients attendus (lambda x duree) = "+ expectedClient +"\n" +
                "    Prob de service sans attente (1 - ro) = "+ serviceWithoutWaiting +"\n" +
                "    Prob file occupee (ro) = "+ro+"\n" +
                "    Debit (lambda) = "+lambda+"\n" +
                "    Esp nb clients (ro/1-ro) ="+ espNbClient +"\n" +
                "    Temps moyen de sejour (1/mu(1-ro)) ="+ AverageLengthStay +"\n" +
                "--------------------\n" +
                "RESULTATS SIMULATION\n" +
                "--------------------\n" +
                "Nombre total de clients = "+ nbClient +"\n" +
                "Proportion clients sans attente = "+ proportionNoWaiting  +"\n" +
                "Proportion clients avec attente ="+ proportionWaiting +"\n" +
                "Debit = "+ debit +"\n" +
                "Nb moyen de clients dans systeme ="+ meanNumberOfClientWaiting+"\n" +
                "Temps moyen de sejour = "+ averageLengthOfStay +"\n";

    }
    // Adding client for later stat
    public void addClient() {
        nbClient++;
    }

    // Adding client that dont have to w8
    public void addClientWithoutWaiting() {
        ClientWithoutWaiting++;
    }
    public void addClientWithWaiting() {
        ClientWithWaiting++;
    }

    public void addTotalLengthOfStay(double lengthOfStay) {
        totalLengthOfStay += lengthOfStay;
    }
}
