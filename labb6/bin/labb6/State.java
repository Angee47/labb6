import java.util.LinkedList;
import java.util.Queue; /*Queue är ett interface i Java som representerar en kö-struktur*/

/**
 * Representerar hela systemets tillstånd.
 * Här sparas all data som förändras under simuleringen.
 */
public class State {

    public double currentTime = 0; /*Den aktuella simuleringstiden*/
    public boolean stop = false; /* Simulatorn kör sålänge kö är inte tom*/

    /* Antal lediga maskiner*/
    private int freeFast;
    private int freeSlow;

    //kö
    private int maxQueue; /*max antal bilar som får vänta*/
    public Queue<Car> queue = new LinkedList<>(); /* en implementation av Queue (ta bort först (FIFO)*/

    public int rejected = 0; /* mäta antal bilar som inte fick plats*/
    public double totalQueueTime = 0;  /*summan av all väntetid*/
    public int servedCars = 0; // antal bilar som blivit tvättade

    /**
     * Skapar ett nytt state.
     */
    public State(int fast, int slow, int maxQueue) {
        this.freeFast = fast;
        this.freeSlow = slow;
        this.maxQueue = maxQueue;
    }
    public int getFreeFast() {
        return freeFast;
    }
    public int getFreeSlow() {
        return freeSlow;
    }
    public int getMaxQueue() {
        return maxQueue;
    }

}


