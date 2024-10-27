import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloWorldGUI extends JFrame {

    private JTextField nameField;
    private JButton greetButton;

    public HelloWorldGUI() {
        setTitle("Hello World GUI");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Create components
        nameField = new JTextField(15);
        greetButton = new JButton("Greet");

        // Add action listener to the button
        greetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                JOptionPane.showMessageDialog(HelloWorldGUI.this, "Hello, " + name + "!");
            }
        });

        // Add components to the frame
        add(new JLabel("Enter your name:"));
        add(nameField);
        add(greetButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HelloWorldGUI();
    }
}