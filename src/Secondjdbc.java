import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Secondjdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Secondjdbc fj = new Secondjdbc();
		Message message = new Message("title2", "content2", 0, 7, "1", new Date(new java.util.Date().getTime()));
		//fj.add(message);
		//fj.delete(73);
		//fj.add_new(message);
		
		// sql注入的问题
		/*if(fj.select1("zhangsan' or '1' = '1", "************")) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败，请检查用户名密码");
		}*/
		
		/*if(fj.select2("'zhangsan' or '1' = '1'", "'123456'")) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败，请检查用户名密码");
		}*/
		
		fj.insertBatch();
		
	}

	public void add(Message message) {		
		Connection conn = null;
		Statement stmt = null;		
		try{
			conn = JDBCConnection.getConnection();
			stmt = conn.createStatement();

			// 通过执行对象执行sql语句
			String sql = "insert into message values(null, '" 
					+ message.getTitle() 
					+ "', '" 
					+ message.getContent() 
					+ "', " 
					+ message.getHits() 
					+ ", " 
					+ message.getCategoryId() 
					+ ", '" 
					+ message.getIsdelete() 
					+ "', '" 
					+ message.getCreatedate() 
					+ "')";
			System.out.println(sql);
			stmt.executeUpdate(sql); 
		} catch (SQLException ex){
			
		} finally{
			JDBCConnection.closeConnection(stmt, conn);
		}		
	}
	
	public void add_new(Message message) {		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		try{
			conn = JDBCConnection.getConnection();	
			
			String sql = "insert into message values(null, ?, ?, ?, ?, ?, ?)";			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message.getTitle());
			pstmt.setString(2, message.getContent());
			pstmt.setInt(3, message.getHits());
			pstmt.setInt(4, message.getCategoryId());
			pstmt.setString(5, message.getIsdelete());
			pstmt.setDate(6, message.getCreatedate());
			
			pstmt.executeUpdate();
			
		} catch (SQLException ex){
			
		} finally{
			JDBCConnection.closeConnection(pstmt, conn);
		}		
	}
	

	public void delete(int id) throws SQLException {
		// 加载驱动
		// Class.forName("com.mysql.jdbc.Driver");

		// 与数据库建立连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// 执行插入语句
		// 创建执行语句的对象
		Statement stmt = conn.createStatement();

		// 通过执行对象执行sql语句
		String sql = "delete from message where id = " + id;
		int resultCount = stmt.executeUpdate(sql); // 增删改都是用excute或者excuteUpdate
		System.out.println(resultCount);
		// 关闭连接
		stmt.close();
		conn.close();
	}

	public void update(int id, String content) throws SQLException {
		// 加载驱动
		// Class.forName("com.mysql.jdbc.Driver");

		// 与数据库建立连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// 执行插入语句
		// 创建执行语句的对象
		Statement stmt = conn.createStatement();

		// 通过执行对象执行sql语句
		String sql = "update message set content = '" + content + "' where id = " + id;
		System.out.println(sql);
		int resultcount = stmt.executeUpdate(sql); // 增删改都是用excute或者excuteUpdate
		System.out.println(resultcount);

		// 关闭连接
		JDBCConnection.closeConnection(stmt, conn);
	}
	
	public boolean select1(String name, String psw) {
		Connection conn = null;
		Statement stmt = null;		
		ResultSet rs = null;
		boolean bool = false;
		try {
			conn = JDBCConnection.getConnection();
			stmt = conn.createStatement();
	
			// 通过执行对象执行sql语句
			String sql = "select * from emp where name = '" + name + "' and password = '" + psw+ "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);			
			
			while(rs.next()){ 
				bool = true;
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {	
			// 关闭连接
			JDBCConnection.closeConnection(rs, stmt, conn);
		}
		return bool;
	}
	
	public boolean select2(String name, String psw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean bool = false;
		try {
			conn = JDBCConnection.getConnection();
			String sql = "select * from emp where name = ? and password = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, psw);
			
			System.out.println(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){ 
				bool = true;
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {	
			// 关闭连接
			JDBCConnection.closeConnection(rs, pstmt, conn);
		}
		return bool;
	}
	
	/*
	 * Statement和PreparedStatement的联系和区别?
	 */
	public void insertBatch(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JDBCConnection.getConnection();
			String sql = "insert into emp values(null, ?, ?, null)";
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			while (i <= 100){					
				pstmt.setString(1, "zhangsan" + i);
				pstmt.setString(2, i + "");
				i++;
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			
		} catch(SQLException ex) {
			
		} finally {
			JDBCConnection.closeConnection(pstmt, conn);
		}
		
	}

}
