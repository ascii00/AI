public class Perceptron {

    private double[] vectorW;
    private double theta;
    private final double alpha;
    private final String trainedLanguage;

    public Perceptron(String trainedLanguage) {
        this.alpha = 0.1;
        this.theta = 1;
        this.trainedLanguage = trainedLanguage;

        vectorW = new double[26];
        for (int i = 0; i < 26; i++)
            vectorW[i]=Math.random()*0.2+0.1;
    }

    public void learn(Observation observation, int correctAnswer){
        double y = compute(observation) >= 0 ? 1:0;
        if ( y != correctAnswer ) {
            double[] vectorWPrime = vectorW.clone();

            for (int i = 0; i < observation.getFrequency().size(); i++)
                vectorWPrime[i] += ((correctAnswer - y) * alpha * observation.getFrequency().get(i));
            vectorW = vectorWPrime;
            theta = theta - (correctAnswer - y) * alpha;
        }
    }

    public double compute(Observation observation){
        double net = 0;
        for (int i = 0; i < observation.getFrequency().size(); i++)
            net += observation.getFrequency().get(i) * vectorW[i];
        return net - theta;
    }

    // Normalizuję wektor wag - dzielę każdą wspóżędną przez długość wektora
    public void normalizePerceptron(){
        double sum = 0;

        for (double point : vectorW)
            sum+=Math.pow(point,2);

        double length = Math.sqrt(sum);

        for (int i = 0; i < vectorW.length ; i++)
            vectorW[i] = vectorW[i]/length;

        theta = theta/length;
    }

    public String getTrainedLanguage() {
        return trainedLanguage;
    }
}