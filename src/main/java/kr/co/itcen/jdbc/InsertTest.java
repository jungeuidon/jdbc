package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertTest {

	public static void main(String []args) {
		insert("경영지원1팀");
		insert("경영지원2팀");
		insert("경영지원3팀");
	}
	
	public static boolean insert(String name) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		
		
		try {
			//1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.40:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "bit1234");
			
			System.out.println("연결성공!");
			
			//3. Statement 객체 생성 ( 받아오기 )
			stmt = conn.createStatement();
					
			//4. SQL 문 실행
			String sql = "insert into department values(null, '" + name + "')";
			int count = stmt.executeUpdate(sql);
			
			result = (count==1);
					
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}
	

}
