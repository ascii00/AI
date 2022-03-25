import java.util.ArrayList;
import java.util.List;

public class Observation {

    public static final List<String> classes = new ArrayList<>();

    private final double[] elements;
    private final String className;
    private final String rawLine;
    private int answer;

    public Observation(String line) {
        rawLine = line;
        String[] tmp = line.split(",");
        elements = new double[tmp.length-1];

        for (int i = 0; i < tmp.length-1; i++)
            elements[i] = Double.parseDouble(tmp[i]);
        className = tmp[tmp.length-1];

        setAnswer();
    }

    private void setAnswer(){
        if(classes.size() < 2 && !classes.contains(className))
            classes.add(className);
        answer = classes.get(0).equals(className) ? 0:1;
    }

    public int getAnswer() {
        return answer;
    }

    public double[] getElements() {
        return elements;
    }

    public String getClassName() {
        return className;
    }

    public String getRawLine() {
        return rawLine;
    }
}
