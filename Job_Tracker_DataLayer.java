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

    }

    public void addApplication(){

    }

    public void updateApplication(){

    }

    public void deleteApplication(){

    }
}
