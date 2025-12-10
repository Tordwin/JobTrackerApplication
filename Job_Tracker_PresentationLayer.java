import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Job_Tracker_PresentationLayer {
    Job_Tracker_DataLayer dataLayer = new Job_Tracker_DataLayer();
    public static Font defaultFont = new Font("Courier", Font.BOLD, 20);

    public Job_Tracker_PresentationLayer() {
        JPanel loginBox = new JPanel(new GridLayout(3, 2));
        JLabel userInput = new JLabel("Username: ");
        JLabel passInput = new JLabel("Password: ");
        JTextField user = new JTextField("root");

        JTextField pass = new JPasswordField("");
        JLabel databaseInput = new JLabel("Database: ");
        JTextField database = new JTextField("job_application_db");

        loginBox.add(userInput);
        loginBox.add(user);
        loginBox.add(passInput);
        loginBox.add(pass);
        loginBox.add(databaseInput);
        loginBox.add(database);

        JOptionPane.showMessageDialog(null, loginBox,
            "Job Application Tracker", JOptionPane.QUESTION_MESSAGE);

        String username = user.getText();
        String databaseName = database.getText();

        String passwordInput = new String();

        passwordInput = pass.getText();

        // Set the default password to "student" even if the user doesn't enter anything
        if (passwordInput.equalsIgnoreCase("")) {
        } else {
            passwordInput = pass.getText();
        }
        dataLayer.connect(databaseName, username, passwordInput);
    }

    public static void main(String[] args) {
      new Job_Tracker_PresentationLayer();
   }
}
