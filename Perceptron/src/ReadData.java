import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    public static List<Observation> readObservations(String fileAddress){
        List<Observation> observations = new ArrayList<>();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileAddress))){
            String line;
            while ((line = bufferedReader.readLine()) != null)
                observations.add(new Observation(line));
        }catch (IOException e){
            e.printStackTrace();
        }
        return observations;
    }
}
