import java.util.LinkedList;

public class Ech {

    private double mu;
    private double lambda;
    private LinkedList<Evt> events = new LinkedList<Evt>();

    private static double lastDeparture = 0;

    //Init of stat, object that compute theorical and simulation result
    private Stats stat;


    public Ech(double lambda,double mu) {
        this.mu = mu;
        this.lambda = lambda;
        // Create the first event
        //insert(new Evt(0, State.ARRIVED));
        events.addFirst(new Evt(0, State.ARRIVED));
    }

    public void insert(Evt event) {
        int index = 0;
        // find our place via our time of arrival or departure (depends on our state)
        while (index < events.size() && events.get(index).compareTo(event) <= 0) {
            index++;
        }
        events.add(index, event);
    }

    public void simulation(double duration,int debug){
        // Create the stat for the simulation
        stat = new Stats(lambda, mu, duration);
        //On tourne dans la simulation jusqu'à ce que la liste de l'échéancier soit vide
        while (!events.isEmpty()) {
            handleEvent(duration,debug);
        }
        System.out.println(stat);
    }

    public void handleEvent(double duration,int debug){
        Evt currentEvent = events.getFirst();
        double timeArrival = currentEvent.getTimeArrival();
        if(currentEvent.getState() == State.ARRIVED) {
            if(timeArrival > lastDeparture) { //if we arrive after the last departure we use our arrival time (no w8)
                // the last departure became the time of departure of the current client
                lastDeparture = Utils.duration(mu,timeArrival);
                currentEvent.setTimeDeparture(lastDeparture);
                stat.addClientWithoutWaiting(); // This client does not w8 so we add him to the stat
            }
            else { // if there is someone before us (LastDeparture > time) arrival we have to w8 until they finish
                // the last departure became the time of departure of the current client
                lastDeparture = Utils.duration(mu,lastDeparture);
                currentEvent.setTimeDeparture(lastDeparture);
                stat.addClientWithWaiting(); // cause timeArrival < lastDeparture they have to w8
            }
            if(debug == 1){
                System.out.println("Date="+currentEvent.getTimeArrival()+"\t Arrivé   client#"
                        +currentEvent.getId());
            }
            insert(currentEvent);
            //preparation of the next event, only added if he does not Exceed duration
            double timeNextEvent = Utils.duration(lambda, timeArrival);
            if(timeNextEvent < duration) {
                stat.addClient(); // add a client to the count
                insert(new Evt(timeNextEvent, State.ARRIVED));
            }
        }
        else {
            // if we are a departure we update lastDeparture
            if(debug == 1){
                System.out.println("Date="+currentEvent.getTimeDeparture()+"\t Départ   client#"
                        +currentEvent.getId()+ "\t arrive a t="+currentEvent.getTimeArrival());
            }
            // remove the evt that leave
            events.removeFirst();
        }
    }




    public void printEvents(){
        int index = 0;
        while (index < events.size()) {

            System.out.println("["+index);
            System.out.println(events.get(index)+"]");
            index++;
        }
    }


}
//struct donné permettant de classé toute les event (linkedlist)
// fait appel a fct de stat qui sont dans des variable de l'objet stat
// var debug si 1 on affiche tout si 0 juste stat à la fin
// 10 million unité de temps = max 22s sur turing
// Rejeux (voir cours) moyenne avec intervalle de confiance à discuter
// résultat de statistique pour montrer que c'est pertinent où non
// COmpléxité de notre prog (est il bien oui / non pq ?)
// les limites que se passe t'il si lambda = NU