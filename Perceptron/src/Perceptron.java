public class Perceptron {

    private double[] vectorW;
    private double theta;
    private final double alpha;


    //inicjalizacja alpha, theta i wektora wag
    public Perceptron(double alpha, int vectorSize) {
        this.alpha = alpha;
        theta = 3;
        vectorW = new double[vectorSize];
        for (int i = 0; i < vectorSize; i++)
            vectorW[i]=Math.random()*0.2+0.1;
    }

    //modyfikacja theta i wektora wag, jeśli trzeba
    public void learn(Observation observation){

        int y = compute(observation);
        if ( y != observation.getAnswer() ) {

            double[] vectorWPrime = vectorW.clone();

            for (int i = 0; i < observation.getElements().length; i++)
                vectorWPrime[i] += ((observation.getAnswer() - y) * alpha * observation.getElements()[i]);
            vectorW = vectorWPrime;
        }
    }

    public void learnTheta(Observation observation){
        int y = compute(observation);
        if ( y != observation.getAnswer() ) {
            theta = theta - (observation.getAnswer() - y) * alpha;
        }
    }

    //iloczyn skalarny wektorów wag i wejść
    public int compute(Observation observation){
        double net = 0;
        for (int i = 0; i < observation.getElements().length; i++)
            net += observation.getElements()[i] * vectorW[i];

        return (net>=theta?1:0);
    }

    public double[] getVectorW() {
        return vectorW;
    }

    public double getTheta() {
        return theta;
    }
}
