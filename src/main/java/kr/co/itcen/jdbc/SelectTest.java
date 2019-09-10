package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
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
			String sql = "select no, name from department";
			rs = stmt.executeQuery(sql);
			
			//5.결과 가져오기 
			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				
				System.out.println(no  + " "+ name);
			}
					
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
					System.out.println("rs 닫기");
				}if(stmt != null) {
					stmt.close();
					System.out.println("stmt 닫기");
				}if(conn != null) {
					conn.close();
					System.out.println("conn 닫기");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
