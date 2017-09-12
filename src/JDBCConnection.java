import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	
	
	public static Connection getConnection() throws SQLException{		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database1?user=root&password=root");
		
		return conn;
	}
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null){
				conn.close();					
			}
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
	
	public static void closeConnection(Statement stmt, Connection conn) {		
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null){
				conn.close();					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
