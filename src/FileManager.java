import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<Fuvar> ReadIn(String path) throws Exception {
        List<Fuvar> fuvarok = new ArrayList<>();

        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        reader.readLine();
        String line = reader.readLine();
        while (line != null) {
            String[] tmp = line.split(";");
            Fuvar fuvar = new Fuvar(
                    Integer.parseInt(tmp[0]),
                    LocalDateTime.parse(tmp[1], formatter),
                    Integer.parseInt(tmp[2]),
                    Double.parseDouble(tmp[3].replace(',','.')),
                    Double.parseDouble(tmp[4].replace(',','.')),
                    Double.parseDouble(tmp[5].replace(',','.')),
                    tmp[6]
            );
            fuvarok.add(fuvar);
            line = reader.readLine();
        }
        return fuvarok;
    }
}
