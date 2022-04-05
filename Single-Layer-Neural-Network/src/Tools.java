import java.io.*;
import java.util.*;

public class Tools {
    public static List<String> languagesNames;

    public static List<Observation> getObservations(String path){
        List<Observation> observationList = new ArrayList<>();
        languagesNames = getFolderContents(path);

        for (String languageName : languagesNames) {
            List<String> folderContents = getFolderContents(path + "/" + languageName);
            for(String fileName : folderContents) {
                StringBuilder text = getText(path + "/" + languageName + "/" + fileName);
                observationList.add(new Observation(getFrequency(text), languageName));
            }
        }
        return observationList;
    }

    private static StringBuilder getText(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while (null != (line = br.readLine()))
                stringBuilder.append(line.toLowerCase().replaceAll("[^a-zA-Z]", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }

    public static List<Double> getFrequency(StringBuilder text){
        List<Double> frequency = new ArrayList<>();

        ArrayList<Character> symbols = new ArrayList<>();
        for (char symbol : text.toString().toCharArray())
            symbols.add(symbol);
        for (char i = 97; i < 123; i++)
            frequency.add(Collections.frequency(symbols, i) / (double) symbols.size());
        return frequency;
    }

    private static List<String> getFolderContents(String path){
        return Arrays.asList(new File(path).list());
    }

    public static void defineLanguageFromFile(List<Perceptron> perceptronList){
        String path = "src/resources/testData";

        List<String> files = getFolderContents(path);
        for(String fileName : files){
            StringBuilder text = getText(path + "/" +fileName);
            Observation observation = new Observation(getFrequency(text), "[NULL]");
            observation.normalizeObservation();

            double maxScore=-1;
            String language="";
            for (Perceptron perceptron : perceptronList) {
                if (perceptron.compute(observation)>maxScore) {
                    maxScore = perceptron.compute(observation);
                    language = perceptron.getTrainedLanguage();
                }
            }
            System.out.println(fileName + " - " + language);
        }
    }

}