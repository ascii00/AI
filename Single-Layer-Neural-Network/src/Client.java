import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        List<Observation> trainData = Tools.getObservations("src/resources/trainData");
        List<Perceptron> perceptronList = new ArrayList<>();

        for(String languageName : Tools.languagesNames)
            perceptronList.add(new Perceptron(languageName));

        for (int i = 0; i < 1000; i++) {
            for (Observation observation : trainData)
                for (Perceptron perceptron : perceptronList)
                    perceptron.learn(
                            observation,
                            (observation.getLanguage().equals(perceptron.getTrainedLanguage()) ? 1:0 )
                    );
        }

        for(Perceptron perceptron : perceptronList)
            perceptron.normalizePerceptron();

        Tools.defineLanguageFromFile(perceptronList);
        SwingUtilities.invokeLater(() -> new View(perceptronList));
    }
}
