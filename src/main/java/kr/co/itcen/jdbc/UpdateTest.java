package kr.co.itcen.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		
		update(1L, "총무1팀");
		update(2L, "영엉1팀");
		update(3L, "인사1팀");

	}
	
	public static boolean update2(Long no, String name) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			//1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.40:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "bit1234");

			System.out.println("연결성공!");
			
			
			//3. SQL문 준비시키기
			String sql = "update department set name = ? where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			
			//4. 바인딩 (SQL완성시키기)
			pstmt.setString(1, name);
			pstmt.setLong(2, no);
				
			//5. SQL 문 실행
			int count = pstmt.executeUpdate();
			
			result = (count==1);
					
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	
	public static boolean update(Long no, String name) {
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
			String sql = "update department set name ='" + name + "'where no ="+ no ;
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
