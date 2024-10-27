import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame {

    private JTextField filePathField;
    private JTextArea textArea;
    private File selectedFile;

    public TextEditor() {
        setTitle("Simple Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // File selection panel
        JPanel filePanel = new JPanel();
        JButton chooseFileButton = new JButton("Choose File...");
        chooseFileButton.addActionListener(new ChooseFileListener());
        filePathField = new JTextField(30);
        filePathField.setEditable(false);
        filePanel.add(chooseFileButton);
        filePanel.add(filePathField);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton editButton = new JButton("Edit Selected");
        editButton.addActionListener(new EditButtonListener());
        JButton saveButton = new JButton("Save Edits");
        saveButton.addActionListener(new SaveButtonListener());
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);

        // Text area
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add components to the frame
        add(filePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ChooseFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(TextEditor.this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                filePathField.setText(selectedFile.getAbsolutePath());
            }
        }
    }

    private class EditButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedFile != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    String line;
                    textArea.setText("");
                    while ((line = reader.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(TextEditor.this, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(TextEditor.this, "Please select a file first.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedFile != null) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile))) {
                    writer.write(textArea.getText());
                    JOptionPane.showMessageDialog(TextEditor.this, "File saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(TextEditor.this, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(TextEditor.this, "Please select a file first.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}