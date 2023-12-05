import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class GUI {

    private static ExpenseTracker expenseTracker = new ExpenseTracker();

    private static JFrame frame = new JFrame();

    private static String value = "";
    private static JLabel expenseValue = new JLabel();

    private static JTextField amountTextField = new JTextField();
    private static JTextField descriptionTextField = new JTextField();
    private static JLabel expenseHistory = new JLabel();

    private static String descriptionInput = "";
    private static double amountInput = 0;

    private static void baseGUI() {
        JLabel appName = new JLabel();
        appName.setText("Expense Tracker");
        appName.setBounds(170, 2, 300, 50);

        Font bigFontSize = new Font(appName.getFont().getName(), Font.BOLD, 20);
        appName.setFont(bigFontSize);

        JLabel descriptionLabel = new JLabel();
        descriptionLabel.setText("Expense Description:");
        descriptionLabel.setBounds(25, 55, 150, 50);
        
        JLabel amountLabel = new JLabel();
        amountLabel.setText("Amount:");
        amountLabel.setBounds(25, 90, 150, 50);
        
        descriptionTextField.setBounds(175, 70, 275, 25);
        amountTextField.setBounds(175, 105, 275, 25);

        amountLabel.setBounds(25, 90, 50, 50);

        expenseHistory.setText("Expenses");
        expenseHistory.setBounds(185, 140, 300, 50);
        expenseHistory.setFont(bigFontSize);

        frame.setTitle("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(500, 700);
        frame.setLayout(null);
        frame.setVisible(true);
        
        frame.add(appName);
        frame.add(descriptionLabel);
        frame.add(descriptionTextField);
        frame.add(amountLabel);
        frame.add(amountTextField);
    }
    // Communicator for ExpenseTracker Class
    private static void expenses() {
        expenseTracker.addExpense(amountInput, descriptionInput);
        value = expenseTracker.allExpensesGetter(value);
        System.out.println(value);
    
        expenseValue.setText("<html>" + value + "</html>");
        expenseValue.setVerticalAlignment(SwingConstants.TOP);
        expenseValue.setHorizontalAlignment(SwingConstants.LEFT);
        expenseValue.setBounds(10, 180, 680, 500);
    }

    public static void getInput(String descriptionTextField, String amountTextField) {
        descriptionInput = descriptionTextField;
        amountInput = Double.parseDouble(amountTextField);
    }

    public static void main(String[] args) {
        GUI.baseGUI();

        descriptionTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {    // Will only accept if both text fields aren't empty and the amount textfield has a valid double value
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !descriptionTextField.getText().isEmpty() && !amountTextField.getText().isEmpty() && amountTextField.getText().matches("\\d+(\\.\\d*)?")) {
                    getInput(descriptionTextField.getText(), amountTextField.getText());
                    descriptionTextField.setText("");
                    amountTextField.setText("");
                    expenses();
                }
            }
        });
        amountTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !descriptionTextField.getText().isEmpty() && !amountTextField.getText().isEmpty() && amountTextField.getText().matches("\\d+(\\.\\d*)?")) {
                    getInput(descriptionTextField.getText(), amountTextField.getText());
                    descriptionTextField.setText("");
                    amountTextField.setText("");
                    expenses();
                }
            }
        });
        expenseValue.repaint();
        frame.add(expenseHistory);
        frame.add(expenseValue);
    }
}
