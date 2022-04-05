import javax.swing.*;
import java.awt.*;
import java.util.List;

public class View extends JFrame {

    JPanel panelMain = new JPanel();
    JButton okButton = new JButton("OK");
    JTextArea jTextArea = new JTextArea();
    JScrollPane jScrollPane = new JScrollPane(jTextArea);
    JTextField jTextField = new JTextField();

    List<Perceptron> perceptronList;


    public View(List<Perceptron> perceptronList) {
        this.perceptronList = perceptronList;

        setTitle("Define language");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMain.setLayout(new BorderLayout());
        panelMain.add(jTextField, BorderLayout.PAGE_START);
        panelMain.add(jScrollPane, BorderLayout.CENTER);
        panelMain.add(okButton, BorderLayout.PAGE_END);

        jTextArea.setEditable(true);
        jTextField.setEditable(false);
        jTextField.setForeground(Color.RED);
        jTextField.setHorizontalAlignment(JLabel.CENTER);
        jScrollPane.setBorder(BorderFactory.createBevelBorder(1));

        okButton.addActionListener(e -> {
            String textInArea = jTextArea.getText();
            if (!jTextArea.getText().equals("")){
                StringBuilder text = new StringBuilder();
                text.append(textInArea.toLowerCase().replaceAll("[^a-zA-Z]", ""));
                Observation observation = new Observation(Tools.getFrequency(text), "[NULL]");
                observation.normalizeObservation();


                double maxScore=-1;
                String language="";
                for (Perceptron perceptron : perceptronList) {
                    if (perceptron.compute(observation)>maxScore) {
                        maxScore = perceptron.compute(observation);
                        language = perceptron.getTrainedLanguage();
                    }
                    System.out.println(perceptron.compute(observation));
                }
                jTextField.setText(language);
            }
        });

        add(panelMain);
        setResizable(true);
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
