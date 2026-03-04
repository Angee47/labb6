/**
 * Representerar en bil i systemet.
 */
public class Car {

    private int id; /* Den lagrar bilens unika id.*/
    private double arrivalTime;
    /* konstruktorn*/
    /**
     * @param id unikt id för bilen
     * @param arrivalTime tiden då bilen kom
     */
    public Car(int id, double arrivalTime) {
        this.id = id; /* kopierar värdet från parametern till objektets variabel*/
        this.arrivalTime = arrivalTime;
    }
    public int getId() {
        return id;
    }
}
