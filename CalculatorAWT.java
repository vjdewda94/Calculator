import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorAWT {
    private JFrame frame;
    private JTextField textField;
    private double num1, num2, result;
    private String operator;

    public CalculatorAWT() {
        
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        
        textField = new JTextField();
        frame.add(textField, BorderLayout.NORTH);

        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add buttons for digits and operations
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        // Loop to create buttons dynamically
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        // Add panel with buttons to the frame
        frame.add(panel, BorderLayout.CENTER);

        // Make frame visible
        frame.setVisible(true);
    }

    // ActionListener for button clicks
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            // If the button clicked is a number, append to the text field
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command);
            }
            // If the button clicked is an operator, store the first number and operator
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (textField.getText().isEmpty()) return; // Ignore if text field is empty
                num1 = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
            // If the button clicked is "=", perform the calculation
            else if (command.equals("=")) {
                if (textField.getText().isEmpty()) return; // Ignore if text field is empty
                num2 = Double.parseDouble(textField.getText());

                // Perform the operation based on the operator
                try {
                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                textField.setText("Error: Div by 0");
                                return;
                            }
                            result = num1 / num2;
                            break;
                    }
                    textField.setText(String.valueOf(result));
                } catch (Exception ex) {
                    textField.setText("Error");
                }
            }
            // If the button clicked is "C", clear the text field
            else if (command.equals("C")) {
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        // Create the calculator instance and show the GUI
        new CalculatorAWT();
    }
}
