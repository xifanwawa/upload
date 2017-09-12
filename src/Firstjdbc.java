import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.JdbcRowSet;

public class Firstjdbc {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Firstjdbc fj = new Firstjdbc();
		// fj.add();
		// fj.delete();
		//fj.update();

		fj.select1();
	}

	public void add() throws SQLException {
		// 加载驱动
		// Class.forName("com.mysql.jdbc.Driver");

		// 与数据库建立连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// 执行插入语句
		// 创建执行语句的对象
		Statement stmt = conn.createStatement();

		// 通过执行对象执行sql语句
		String sql = "insert into message values(null, '重庆“高铁朋友圈”', '重庆高铁四通八达', 0, 8, 1, now())";
		stmt.executeUpdate(sql); // 增删改都是用excute或者excuteUpdate

		// 关闭连接
		stmt.close();
		conn.close();
	}

	public void delete() throws SQLException {
		// 加载驱动
		// Class.forName("com.mysql.jdbc.Driver");

		// 与数据库建立连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// 执行插入语句
		// 创建执行语句的对象
		Statement stmt = conn.createStatement();

		// 通过执行对象执行sql语句
		String sql = "delete from message where id = 70";
		int resultCount = stmt.executeUpdate(sql); // 增删改都是用excute或者excuteUpdate
		System.out.println(resultCount);
		// 关闭连接
		stmt.close();
		conn.close();
	}

	public void update() throws SQLException {
		// 加载驱动
		// Class.forName("com.mysql.jdbc.Driver");

		// 与数据库建立连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// 执行插入语句
		// 创建执行语句的对象
		Statement stmt = conn.createStatement();

		// 通过执行对象执行sql语句
		String sql = "update message set category_id = 9 where category_id = 8";
		int resultcount = stmt.executeUpdate(sql); // 增删改都是用excute或者excuteUpdate
		System.out.println(resultcount);

		// 关闭连接
		JDBCConnection.closeConnection(stmt, conn);
	}

	public void select() throws SQLException {
		// 加载驱动
		// Class.forName("com.mysql.jdbc.Driver");

		// 与数据库建立连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// 执行插入语句
		// 创建执行语句的对象
		Statement stmt = conn.createStatement();

		// 通过执行对象执行sql语句
		String sql = "select * from message";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){ // 没循环一次取出一行数据
			int id = rs.getInt(1);  // 从每行数据中获取没个字段的值，通过索引获取，索引从1开始取
			String title = rs.getString("title");
			String content = rs.getString(3);
			int hits = rs.getInt(4);
			int categoryId = rs.getInt(5);
//			Date date = rs.getDate(7);	
			Date date = rs.getDate("createdate");
			System.out.println(id + title + content + hits + categoryId + date );
		}

		// 关闭连接
		rs.close();
		stmt.close();
		conn.close();
	}
	
	public void select1() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 与数据库建立连接
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");
			conn = JDBCConnection.getConnection();
			// 执行插入语句
			// 创建执行语句的对象
			stmt = conn.createStatement();
	
			// 通过执行对象执行sql语句
			String sql = "select title, content, createdate as cd from message where id < 60 and title like '%皇马%'";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){ // 没循环一次取出一行数据
				String title = rs.getString(1);  // 从每行数据中获取没个字段的值，通过索引获取，索引从1开始取
				String content = rs.getString("content");
				Date date = rs.getDate("cd");
				System.out.println(title + content + date );
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {	
			// 关闭连接
			JDBCConnection.closeConnection(rs, stmt, conn);
		}
	}

}
