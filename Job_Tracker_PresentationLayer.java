import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

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
        panel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JLabel title = new JLabel("Please select an option from the menu:", SwingConstants.CENTER); 
        title.setFont(defaultFont);

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 0));

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
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void grabApplication() {
        // dataLayer.getApplication();
        DefaultTableModel model = dataLayer.getApplicationsTable();
        JTable table = new JTable(model);
        table.setRowHeight(20);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        JOptionPane.showMessageDialog(null, scrollPane, "Applications", JOptionPane.INFORMATION_MESSAGE);
    }

    private void insertApplication() {
        JPanel addApplicationBox = new JPanel(new GridLayout(3, 2));
        JLabel titleInput = new JLabel("Title: ");
        JLabel companyInput = new JLabel("Company: ");
        JLabel summaryInput = new JLabel("Summary: ");
        JLabel applicationLinkInput = new JLabel("Application Link: ");
        JLabel dateSentInput = new JLabel("Date Sent (YYYY-MM-DD): ");
        JLabel elapsedTimeInput = new JLabel("Elapsed Time (days): ");
        JLabel statusInput = new JLabel("Status: ");
        JLabel interviewStatusInput = new JLabel("Interview Status: ");
        JLabel emailInput = new JLabel("Email: ");

        // sql = "INSERT INTO applications (`title`, `company`, `summary`, `application_link`, `date_sent`, `elapsed_time`, `status`, `interview_status`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        dataLayer.addApplication(null, null, null, null, null, 0, null, null, null);
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
