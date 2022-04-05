import java.util.List;

public class Observation {

    private final List<Double> frequency;
    private final String language;


    public Observation(List<Double> frequency, String language) {
        this.frequency = frequency;
        this.language = language;
    }

    public List<Double> getFrequency() {
        return frequency;
    }

    public String getLanguage() {
        return language;
    }

    public void normalizeObservation(){
        double sum = 0;
        for (double point : frequency)
            sum+=Math.pow(point,2);

        double length = Math.sqrt(sum);

        for (int i = 0; i < frequency.size() ; i++)
            frequency.set(i,(frequency.get(i)/length));
    }
}
