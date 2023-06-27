import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMICalculatorGUI extends JFrame implements ActionListener {
    private JTextField weightField;
    private JTextField heightField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JButton calculateButton;
    private JLabel bmiLabel;
    private JLabel categoryLabel;

    public BMICalculatorGUI() {
        setTitle("BMI Calculator");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        JLabel genderLabel = new JLabel("Gender:");
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        JLabel heightLabel = new JLabel("Height (m):");
        heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight (kg):");
        weightField = new JTextField();

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        bmiLabel = new JLabel("BMI:");
        categoryLabel = new JLabel("Category:");

        add(genderLabel);
        add(maleRadioButton);
        add(new JLabel()); // Empty label for spacing
        add(femaleRadioButton);
        add(heightLabel);
        add(heightField);
        add(weightLabel);
        add(weightField);
        add(calculateButton);
        add(bmiLabel);
        add(categoryLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            String gender = maleRadioButton.isSelected() ? "Male" : "Female";

            double bmi = calculateBMI(weight, height);
            String category = interpretBMI(bmi);

            bmiLabel.setText("BMI: " + bmi);
            categoryLabel.setText("Category: " + category);
        }
    }

    public static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    public static String interpretBMI(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal weight";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BMICalculatorGUI calculator = new BMICalculatorGUI();
                calculator.setVisible(true);
            }
        });
    }
}