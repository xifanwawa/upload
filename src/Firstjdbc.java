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
		// ��������
		// Class.forName("com.mysql.jdbc.Driver");

		// �����ݿ⽨������
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// ִ�в������
		// ����ִ�����Ķ���
		Statement stmt = conn.createStatement();

		// ͨ��ִ�ж���ִ��sql���
		String sql = "insert into message values(null, '���조��������Ȧ��', '���������ͨ�˴�', 0, 8, 1, now())";
		stmt.executeUpdate(sql); // ��ɾ�Ķ�����excute����excuteUpdate

		// �ر�����
		stmt.close();
		conn.close();
	}

	public void delete() throws SQLException {
		// ��������
		// Class.forName("com.mysql.jdbc.Driver");

		// �����ݿ⽨������
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// ִ�в������
		// ����ִ�����Ķ���
		Statement stmt = conn.createStatement();

		// ͨ��ִ�ж���ִ��sql���
		String sql = "delete from message where id = 70";
		int resultCount = stmt.executeUpdate(sql); // ��ɾ�Ķ�����excute����excuteUpdate
		System.out.println(resultCount);
		// �ر�����
		stmt.close();
		conn.close();
	}

	public void update() throws SQLException {
		// ��������
		// Class.forName("com.mysql.jdbc.Driver");

		// �����ݿ⽨������
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// ִ�в������
		// ����ִ�����Ķ���
		Statement stmt = conn.createStatement();

		// ͨ��ִ�ж���ִ��sql���
		String sql = "update message set category_id = 9 where category_id = 8";
		int resultcount = stmt.executeUpdate(sql); // ��ɾ�Ķ�����excute����excuteUpdate
		System.out.println(resultcount);

		// �ر�����
		JDBCConnection.closeConnection(stmt, conn);
	}

	public void select() throws SQLException {
		// ��������
		// Class.forName("com.mysql.jdbc.Driver");

		// �����ݿ⽨������
		Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");

		// ִ�в������
		// ����ִ�����Ķ���
		Statement stmt = conn.createStatement();

		// ͨ��ִ�ж���ִ��sql���
		String sql = "select * from message";
		
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){ // ûѭ��һ��ȡ��һ������
			int id = rs.getInt(1);  // ��ÿ�������л�ȡû���ֶε�ֵ��ͨ��������ȡ��������1��ʼȡ
			String title = rs.getString("title");
			String content = rs.getString(3);
			int hits = rs.getInt(4);
			int categoryId = rs.getInt(5);
//			Date date = rs.getDate(7);	
			Date date = rs.getDate("createdate");
			System.out.println(id + title + content + hits + categoryId + date );
		}

		// �ر�����
		rs.close();
		stmt.close();
		conn.close();
	}
	
	public void select1() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// �����ݿ⽨������
			//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/database1?user=root&password=root");
			conn = JDBCConnection.getConnection();
			// ִ�в������
			// ����ִ�����Ķ���
			stmt = conn.createStatement();
	
			// ͨ��ִ�ж���ִ��sql���
			String sql = "select title, content, createdate as cd from message where id < 60 and title like '%����%'";
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){ // ûѭ��һ��ȡ��һ������
				String title = rs.getString(1);  // ��ÿ�������л�ȡû���ֶε�ֵ��ͨ��������ȡ��������1��ʼȡ
				String content = rs.getString("content");
				Date date = rs.getDate("cd");
				System.out.println(title + content + date );
			}
		} catch (SQLException ex){
			ex.printStackTrace();
		} finally {	
			// �ر�����
			JDBCConnection.closeConnection(rs, stmt, conn);
		}
	}

}
