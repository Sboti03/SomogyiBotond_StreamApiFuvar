import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Fuvar> fuvarok;
        try {
            fuvarok = FileManager.ReadIn("fuvar.csv");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        new Tasks(fuvarok);

    }
}