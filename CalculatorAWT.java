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

       
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            panel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        
        frame.add(panel, BorderLayout.CENTER);

       
        frame.setVisible(true);
    }

    
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                textField.setText(textField.getText() + command);
            }
            
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                if (textField.getText().isEmpty()) return; // Ignore if text field is empty
                num1 = Double.parseDouble(textField.getText());
                operator = command;
                textField.setText("");
            }
            
            else if (command.equals("=")) {
                if (textField.getText().isEmpty()) return; // Ignore if text field is empty
                num2 = Double.parseDouble(textField.getText());

                
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
            
            else if (command.equals("C")) {
                textField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        
        new CalculatorAWT();
    }
}
