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

    public void addApplication(String title, String company, String summary, String application_link, String date_sent, int elapsed_time, String status, String interview_status, String email){
        sql = "INSERT INTO applications (`title`, `company`, `summary`, `application_link`, `date_sent`, `elapsed_time`, `status`, `interview_status`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, company);
            stmt.setString(3, summary);
            stmt.setString(4, application_link);
            stmt.setString(5, date_sent);
            stmt.setInt(6, elapsed_time);
            stmt.setString(7, status);
            stmt.setString(8, interview_status);
            stmt.setString(9, email);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("A new application has been inserted successfully.");
            } else {
                System.out.println("Insertion failed.");
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException!");
            System.out.println("Error: " + sqle.getMessage());
        } catch (Exception e) {
            System.out.println("Exception!");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateApplication(int application_id, String status){
        sql = "UPDATE applications SET status = ? WHERE application_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, application_id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Application with ID " + application_id + " updated successfully.");
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

    public void deleteApplication(int application_id){
        sql = "DELETE FROM applications WHERE application_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, application_id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
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
