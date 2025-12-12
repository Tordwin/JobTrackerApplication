import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Job_Tracker_PresentationLayer {
    Job_Tracker_DataLayer dataLayer = new Job_Tracker_DataLayer();
    public static Font defaultFont = new Font("Courier", Font.BOLD, 20);

    public Job_Tracker_PresentationLayer() {
        JPanel loginBox = new JPanel(new GridLayout(3, 2));
        JLabel userInput = new JLabel("Username: ");
        JLabel passInput = new JLabel("Password: ");
        JLabel databaseInput = new JLabel("Database: ");

        JTextField user = new JTextField("root");
        JTextField pass = new JPasswordField("");
        JTextField database = new JTextField("job_application_db");

        loginBox.add(userInput); loginBox.add(user);
        loginBox.add(passInput); loginBox.add(pass);
        loginBox.add(databaseInput); loginBox.add(database);

        JOptionPane.showMessageDialog(null, loginBox, "Job Application Tracker", JOptionPane.QUESTION_MESSAGE);

        String username = user.getText();
        String databaseName = database.getText();
        String passwordInput = new String(pass.getText());

        // Set the default password to "student" even if the user doesn't enter anything
        if (passwordInput.equalsIgnoreCase("")) {
        } else {
            passwordInput = pass.getText();
        }
        Boolean connection = dataLayer.connect(databaseName, username, passwordInput);
        if (!connection) {
            JOptionPane.showMessageDialog(null, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        showMainMenu();
    }

    private void showMainMenu() {
        JFrame frame = new JFrame("Job Application Tracker - Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel title = new JLabel("Please select an option from the menu:", SwingConstants.CENTER); 
        title.setFont(defaultFont);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10));

        JButton getButton = new JButton("Get Applications");
        JButton addButton = new JButton("Add Applications");
        JButton deleteButton = new JButton("Delete Applications");
        JButton updateButton = new JButton("Update Applications");

        for (JButton button : new JButton[]{getButton, addButton, deleteButton, updateButton}) {
            button.setFont(defaultFont);
            buttonPanel.add(button);
        }

        getButton.addActionListener(e -> grabApplication());
        addButton.addActionListener(e -> insertApplication());
        deleteButton.addActionListener(e -> removeApplication());
        updateButton.addActionListener(e -> updateApplication());

        buttonPanel.add(getButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        panel.add(title, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        frame.setContentPane(panel);
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    private void grabApplication() {
        dataLayer.getApplication();
    }

    private void insertApplication() {
        // Placeholder for insert application functionality
        System.out.println("NOT IMPLEMENTED");
    }

    private void removeApplication() {
        // Placeholder for delete application functionality
        System.out.println("NOT IMPLEMENTED");
    }

    private void updateApplication() {
        // Placeholder for update application functionality
        System.out.println("NOT IMPLEMENTED");
    }

    public static void main(String[] args) {
      new Job_Tracker_PresentationLayer();
   }
}
