package JobTrackerApplication;
import java.sql.*;

public class Job_Tracker_DataLayer {
    private Connection conn;
    private ResultSet rs;
    private Statement stmt;
    private String sql;

    final String DEFAULT_DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public boolean connect(String database, String user, String password) {
        conn = null;
        String url = "jdbc:mysql://localhost/" + database + "?serverTimezone=UTC";
        try {
            Class.forName(DEFAULT_DRIVER);
            conn = DriverManager.getConnection(url, user, password);

            System.out.println("Connection to database established!");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection to database failed!");
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException sqle) {
            System.out.println("SQLException!");
            System.out.println("Error: " + sqle.getMessage());
        } catch (Exception e) {
            System.out.println("Exception!");
            System.out.println("Error: " + e.getMessage());
        }
        return (conn != null);
    }

    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Disconnected from database.");
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException during disconnection!");
            System.out.println("Error: " + sqle.getMessage());
        }
    }

    public void getApplication(){
        sql = "SELECT * FROM applications";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Application ID: " + rs.getInt("id") +
                                   ", Company: " + rs.getString("company") +
                                   ", Position: " + rs.getString("position") +
                                   ", Status: " + rs.getString("status"));
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException!");
            System.out.println("Error: " + sqle.getMessage());
        } catch (Exception e) {
            System.out.println("Exception!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addApplication(String title, String company, String application_link, Date date_sent, int elapsed_time, String status, String interview_status, String email){
        sql = "INSERT INTO applications (title, company, application_link, date_sent, elapsed_time, status, interview_status, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            stmt = conn.prepareStatement(sql);

        } catch (SQLException sqle) {
            System.out.println("SQLException!");
            System.out.println("Error: " + sqle.getMessage());
        } catch (Exception e) {
            System.out.println("Exception!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateApplication(int application_id){
    
    }

    public void deleteApplication(int application_id){
        sql = "DELETE FROM applications WHERE application_id = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, application_id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Application with ID " + application_id + " deleted successfully.");
            } else {
                System.out.println("No application found with ID " + application_id + ".");
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException!");
            System.out.println("Error: " + sqle.getMessage());
        } catch (Exception e) {
            System.out.println("Exception!");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
