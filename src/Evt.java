public class Evt {
    private double timeArrival;
    private double timeDeparture;

    private int id = 0;
    private static int order = 1;
    private State state;

    public Evt(double time, State state) {
        this.timeArrival = time;
        this.state = state;
        this.id = order++;

    }

    public State getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public double getTimeArrival() {
        return timeArrival;
    }
    public double getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(double timeDeparture) {
        this.state = State.DEPARTURE;
        this.timeDeparture = timeDeparture;
    }

    public double getTime(){
        if(state == State.DEPARTURE){
            return timeDeparture;
        }
        return timeArrival;
    }

    public int compareTo(Evt other) {
        return Double.compare(this.getTime(), other.getTime());
    }
    @Override
    public String toString() {
        return
                "---------id=" + id + "---------\n" +
                "Date=" + timeArrival + "\n" +
                "Departure=" + timeDeparture + "\n" +
                "state=" + state+ "\n"+
                "-------------------------------\n";
    }
}
//

