import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Ech {
    private Graph graph;
    private final double mu;
    private final double lambda;
    private LinkedList<Evt> events = new LinkedList<Evt>();

    private double lastDeparture = 0;

    //object that compute theorical and simulation result
    private Stats stat;


    public Ech(double lambda,double mu) {
        this.mu = mu;
        this.lambda = lambda;
    }

    public void insert(Evt event) {
        Iterator<Evt> iterator = events.iterator();
        int index = 0;

        // search in the linked list
        while (iterator.hasNext()) {
            Evt currentEvent = iterator.next();
            // compare with current
            if (currentEvent.compareTo(event) > 0) {
                break; // we have found the index
            }
            index++;
        }

        // add event
        events.add(index, event);
    }

    public void simulation(double duration, int debug, Stats stat) {
        // Create the stat for the simulation
        this.stat = stat;

        // Create the first event
        events.addFirst(new Evt(0, State.ARRIVED));

        //On tourne dans la simulation jusqu'à ce que la liste de l'échéancier soit vide
        while (!events.isEmpty()) {
            handleEvent(duration,debug);
        }
        System.out.println(stat);
    }

    public void handleEvent(double duration,int debug){
        // We pop the fist event in list (closest in time)
        Evt currentEvent = events.pop();
        double timeArrival = currentEvent.getTimeArrival();
        if(currentEvent.getState() == State.ARRIVED) {
            if(timeArrival >= lastDeparture) { //if we arrive after the last departure we use our arrival time (no w8)
                // the last departure became the time of departure of the current client
                lastDeparture = Utils.duration(mu,timeArrival);
                currentEvent.setTimeDeparture(lastDeparture);
                stat.addClientWithoutWaiting(); // This client does not w8 so we add him to the stat
            }
            else { // if there is someone before us (LastDeparture > timeArrival) arrival we have to w8 until they finish
                // the last departure became the time of departure of the current client
                lastDeparture = Utils.duration(mu,lastDeparture);
                currentEvent.setTimeDeparture(lastDeparture);
                stat.addClientWithWaiting(); // cause timeArrival < lastDeparture they have to w8
            }
            stat.addDataNbClientInQueue(timeArrival);
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
            double lengthOfStay = currentEvent.getTimeDeparture() - currentEvent.getTimeArrival();
            stat.addTotalLengthOfStay(lengthOfStay); // use to calc mean of length of stay
            stat.addDataStayTime(timeArrival); // use length of stay and current time to fill list use to make a graph

            stat.subtractClient(); // Sub to current number of client
            //graph.writeData(stat.getCurrentAverageLengthOfStay(), timeArrival);
            if(debug == 1){
                System.out.println("Date="+currentEvent.getTimeDeparture()+"\t Départ   client#"
                        +currentEvent.getId()+ "\t arrive a t="+currentEvent.getTimeArrival());
            }
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