import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fuvar {
    private int id;                         // Azonosító
    private LocalDateTime departureTime;    // Indulási idő
    private int travelTime;                 // Utazás időtartalma
    private double traveledDistance;        // Megtett távolság
    private double priceOfTrip;             // VitelDíj
    private double tip;                     // Borravaló
    private String paymentMethod;           // Fizetés módja

    public Fuvar(int id, LocalDateTime departureTime, int travelTime, double traveledDistance, double priceOfTrip, double tip, String paymentMethod) {
        this.id = id;
        this.departureTime = departureTime;
        this.travelTime = travelTime;
        this.traveledDistance = traveledDistance;
        this.priceOfTrip = priceOfTrip;
        this.tip = tip;
        this.paymentMethod = paymentMethod;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public double getTraveledDistance() {
        return traveledDistance;
    }

    public double getPriceOfTrip() {
        return priceOfTrip;
    }

    public double getTip() {
        return tip;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s = String.format(
                "Taxi szám: %d\n" +
                    "Indulási idő: %s\n" +
                    "Utazás időtartalma: %d másodperc\n" +
                    "Megtett távolság: %.2f mérföld\n" +
                    "Viteldíj: %.2f$\n" +
                    "Borravaló: %.2f$\n" +
                    "Fizetés módja: %s\n",
                this.id, this.departureTime.format(formatter),this.travelTime,
                this.traveledDistance, this.priceOfTrip, this.tip, this.paymentMethod
        );
        return s;
    }
}
