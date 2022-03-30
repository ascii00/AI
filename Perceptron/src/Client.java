import java.util.List;
import java.util.Scanner;

public class Client {

    public static List<Observation> trainData;
    public static List<Observation> testData;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj alpha:");
        double alpha = sc.nextDouble();

        System.out.println("Podaj ścieżkę do płiku z danymi treningowymi:");
        sc.nextLine();
        String trainSetAddress = sc.nextLine();

        System.out.println("Podaj ścieżkę do pliku z danymi testowymi:");
        String testSetAddress = sc.nextLine();

        trainData = ReadData.readObservations(trainSetAddress);
        testData = ReadData.readObservations(testSetAddress);

        Perceptron perceptron = new Perceptron(alpha, trainData.get(0).getElements().length);

        for (int i = 0; i < 100; i++) {
            for (Observation observation : trainData)
                perceptron.learn(observation);
        }

        Accuracy.getAccuracyFromTestData(testData,perceptron);

        while (true){
            System.out.println("\nPodaj " + testData.get(0).getElements().length + " wymiarowy wektor:");
            Accuracy.getAccuracyFromVector(sc.nextLine(), perceptron);
        }
    }
}
