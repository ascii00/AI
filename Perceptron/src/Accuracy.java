import java.util.Arrays;
import java.util.List;

public class Accuracy {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void getAccuracyFromTestData(List<Observation> testData, Perceptron perceptron){
        int numberOfCorrectAnswerOfFirstClass = 0;
        int numberOfCorrectAnswerOfSecondClass = 0;
        int numberOfAppearancesOfFirstClass = 0;
        int numberOfAppearancesOfSecondClass = 0;

        for (Observation observation: testData) {
            int y = perceptron.compute(observation);

            if (observation.getAnswer() == 0) {
                numberOfAppearancesOfFirstClass++;
                if (observation.getAnswer() == y) {
                    numberOfCorrectAnswerOfFirstClass++;
                    print(observation, true);
                }else
                    print(observation, false);
            }

            if (observation.getAnswer() == 1) {
                numberOfAppearancesOfSecondClass++;
                if (observation.getAnswer() == y) {
                    numberOfCorrectAnswerOfSecondClass++;
                    print(observation, true);
                }else
                    print(observation, false);
            }
        }

        System.out.println(numberOfCorrectAnswerOfFirstClass);
        System.out.println(numberOfCorrectAnswerOfSecondClass);

        double accuracyOne = (double)numberOfCorrectAnswerOfFirstClass/numberOfAppearancesOfFirstClass*100;
        double accuracyTwo = (double)numberOfCorrectAnswerOfSecondClass/numberOfAppearancesOfSecondClass*100;
        double totalAccuracy = (double)(numberOfCorrectAnswerOfFirstClass+numberOfCorrectAnswerOfSecondClass)/testData.size()*100;

        accuracyOne = Math.round(accuracyOne * 1000d) / 1000d;
        accuracyTwo = Math.round(accuracyTwo * 1000d) / 1000d;
        totalAccuracy = Math.round(totalAccuracy * 1000d) / 1000d;

        System.out.println("Dokładność dla klasy 1: " + accuracyOne + "%");
        System.out.println("Dokładność dla klasy 2: "+ accuracyTwo + "%");
        System.out.println("Dokładność: "+ totalAccuracy +"%");
        System.out.println("Wektor: "+ Arrays.toString(perceptron.getVectorW()) + " Theta: "+ perceptron.getTheta());

    }

    public static void getAccuracyFromVector(String vector, Perceptron perceptron){
        Observation observation = new Observation(vector+",[CLASS_NULL]");
        int y = perceptron.compute(observation);

        print(observation, observation.getAnswer() == y);
    }

    private static void print(Observation observation, boolean status){
        if(status){
            System.out.println(observation.getRawLine()
                    + ANSI_GREEN
                    + " "
                    + observation.getClassName()
                    + ANSI_RESET);
        }else {
            if (Observation.classes.get(0).equals(observation.getClassName()))
                System.out.println(observation.getRawLine()
                        + ANSI_RED
                        + " "
                        + Observation.classes.get(1)
                        + ANSI_RESET);
            else
                System.out.println(observation.getRawLine()
                        + ANSI_RED
                        + " "
                        + Observation.classes.get(0)
                        + ANSI_RESET);
        }
    }
}
