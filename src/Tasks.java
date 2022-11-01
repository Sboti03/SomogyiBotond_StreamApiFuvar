import javax.sound.midi.Soundbank;
import java.io.FilterOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Tasks {

    List<Fuvar> fuvarok;

    public Tasks(List<Fuvar> fuvarok) {
        this.fuvarok = fuvarok;

        System.out.println("1. feladat");
        System.out.println("Összesen: " + fuvarok.size() + "db utazás került feljegyzésre.");


        System.out.println("\n2.Feladat");
        List<Fuvar> taxi6185 = getFuvarById(6185);
        System.out.println("A 6185-ös taxi bevétele: " + getIncome(taxi6185) +
                "$\n Fuvarjai száma: " + taxi6185.size()
        );

        System.out.println("\n3.Feladat");
        System.out.printf("A taxisok összesen: %.2f mérföldet tettek meg.\n", getMiles(this.fuvarok));


        System.out.println("\n4.Feladat");
        System.out.printf("A leghosszabb fuvar adatai: %s" +
                "\n", getMax(this.fuvarok));

        Fuvar bestFuvar = getBestTip(this.fuvarok);
        System.out.println("\n5.Feladat");
        System.out.printf("A legbőkezűbb fuvar adatai: %s\n" +
                "Borravaló aránya: %.2f%%\n", bestFuvar, (bestFuvar.getTip() / bestFuvar.getPriceOfTrip()) * 100);


        List<Fuvar> taxi4261 = getFuvarById(4261);
        System.out.println("\n6.Feladat");
        System.out.printf("A 4261-es taxi összesen: %.2fkm-et tett meg.\n", getDistance(taxi4261) * 1.6);


        List<Fuvar> wrongFuvar = getWornFuvar(this.fuvarok);
        System.out.println("\n7.Feladat");
        System.out.printf("Rossz fuvarok száma: %ddb\n" +
                        "Összes időtartalma: %d sec\n" +
                        "Teljes bevétel: %.2f$", wrongFuvar.size(), getAllTravelTime(wrongFuvar),
                getIncome(wrongFuvar)
        );


        System.out.println("\n8.Feladat");
        if (isContains(1452, this.fuvarok)) {
            System.out.println("Tartalmazza a 1452-es azonosítójú taxit az állómány");
        } else {
            System.out.println("Nem tartalmazza a 1452-es azonosítójú taxit az állómány");
        }

        System.out.println("\n9.Feladat");
        Arrays.stream(getThreeShortestTime(this.fuvarok)).forEach(System.out::println);


        System.out.println("\n10.Feladat");
        System.out.println("December 24-én "+ getFuvarByDate(this.fuvarok, LocalDate.parse("2000-12-24")).size()
                +"db fuvar volt.");

        List<Fuvar> dec31Fuvar = getFuvarByDate(this.fuvarok, LocalDate.parse("2000-12-31"));
        System.out.println("\n11.Feladat");
        System.out.println("December 31-én a fuvarok borravaló aránya: ");
        dec31Fuvar.forEach(f-> System.out.print((f.getTip() / f.getPriceOfTrip())*100 + "% "));
    }

    private List<Fuvar> getFuvarById(int id) {
        List<Fuvar> fuvar = fuvarok.stream()
                .filter(f -> f.getId() == id)
                .collect(Collectors.toList());
        return fuvar;
    }

    private double getIncome(List<Fuvar> taxi) {
        Double tips = taxi.stream()
                .mapToDouble(Fuvar::getTip)
                .sum();
        Double priceOfTrips = taxi.stream()
                .mapToDouble(Fuvar::getPriceOfTrip)
                .sum();

        return tips + priceOfTrips;
    }

    private double getMiles(List<Fuvar> fuvarok) {
        double count = fuvarok.stream()
                .mapToDouble(Fuvar::getTraveledDistance)
                .sum();
        return count;
    }

    private Fuvar getMax(List<Fuvar> fuvarok) {
        Fuvar fuvar = fuvarok.stream()
                .max(Comparator.comparingInt(Fuvar::getTravelTime))
                .get();
        return fuvar;
    }

    private Fuvar getBestTip(List<Fuvar> fuvarok) {
        Fuvar fuvar = fuvarok.stream()
                .max(Comparator.comparingDouble(trip -> trip.getTip() / trip.getPriceOfTrip()))
                .get();
        return fuvar;
    }

    private double getDistance(List<Fuvar> taxi) {
        return taxi.stream()
                .mapToDouble(Fuvar::getTraveledDistance)
                .sum();
    }

    private int getAllTravelTime(List<Fuvar> fuvarok) {
        return fuvarok.stream()
                .mapToInt(Fuvar::getTravelTime)
                .sum();
    }

    private List<Fuvar> getWornFuvar(List<Fuvar> fuvarok) {
        List<Fuvar> wrong = fuvarok.stream()
                .filter(trip -> trip.getTravelTime() > 0 && trip.getPriceOfTrip() > 0
                        && trip.getTraveledDistance() == 0)
                .collect(Collectors.toList());
        return wrong;
    }

    private boolean isContains(int id, List<Fuvar> fuvarok) {
        return fuvarok.stream()
                .anyMatch(t -> t.getId() == id);
    }

    private Fuvar[] getThreeShortestTime(List<Fuvar> fuvarok) {
        List<Fuvar> filtered = fuvarok.stream()
                .filter(f -> f.getTravelTime() != 0)
                .sorted(Comparator.comparingInt(Fuvar::getTravelTime))
                .collect(Collectors.toList());
        Fuvar[] result = {filtered.get(0), filtered.get(1), filtered.get(2)};
        return result;
    }

    private List<Fuvar> getFuvarByDate(List<Fuvar> fuvarok, LocalDate date) {
        return fuvarok.stream()
                .filter(f -> f.getDepartureTime().getMonth().equals(date.getMonth()) &&
                        f.getDepartureTime().getDayOfMonth() == date.getDayOfMonth())
                .collect(Collectors.toList());
    }

}
