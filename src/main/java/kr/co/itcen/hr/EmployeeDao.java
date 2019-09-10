package kr.co.itcen.hr;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
	Connection conn=null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private Connection getConn() throws SQLException{
		Connection conn=null;
		try {
			
			//1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2. 연결하기
			String url = "jdbc:mariadb://192.168.1.40:3306/employees?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");
			System.out.println("연결성공!");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		return conn;
	}
	
	public boolean insert(EmployeeVo vo) {
		boolean r = false;
		try {
			conn=getConn();
			
			String sql = "insert into employees values(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "1");
			pstmt.setString(2, "1994-05-27");
			pstmt.setString(3, "의돈");
			pstmt.setString(4, "정");
			pstmt.setString(5, "M");
			pstmt.setString(6, "2019-02-04");
			
			rs = pstmt.executeQuery();
			
			if(rs==null) {
				System.out.println("insert 실패 !!!!");
			}else {
				System.out.println("insert 성공!!!");
				r = true;
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	//전체 리스트 가져오기
	public List<EmployeeVo> getList() {
		
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		try {
			
			conn = getConn();
			
			//3. Statement 객체 생성 ( 받아오기 )
			String sql = "select *, date_format(hire_date, '%Y년 %M월 %d일') from employees order by hire_date desc";
			
			pstmt = conn.prepareStatement(sql);
					
			//4. SQL 문 실행
			rs = pstmt.executeQuery();
			
			//5.결과 가져오기 
			while (rs.next()) {
				Long no = rs.getLong(1);
				String first_name = rs.getString(3);
				String last_name = rs.getString(4);
				String gender = rs.getString(5);
				String hireDate = rs.getString(2);
				
				EmployeeVo vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(first_name);
				vo.setLastName(last_name);
				vo.setGender(gender);
				vo.setHireDate(hireDate);

				result.add(vo);
			}
					
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	
	public List<EmployeeVo> getList(String keyword) {
		List<EmployeeVo> result = new ArrayList<EmployeeVo>();
		
		try {
			conn = getConn();
			
			//3. Statement 객체 생성 ( 받아오기 )
			String sql = "select emp_no, first_name, last_name, gender, date_format(hire_date, '%Y년 %m월 %d일'), birth_date from employees where first_name like ? and last_name like ? order by hire_date desc";
			
			pstmt = conn.prepareStatement(sql);
					
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			
			
			
			//4. SQL 문 실행
			rs = pstmt.executeQuery();
			
			
			//5.결과 가져오기 
			while (rs.next()) {
				EmployeeVo vo= new EmployeeVo();
				Long no = rs.getLong(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				String gender = rs.getString(4);
				String hireDate = rs.getString(5);
				String birthDate = rs.getString(6);
				
				vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(first_name);
				vo.setLastName(last_name);
				vo.setGender(gender);
				vo.setHireDate(hireDate);
				vo.setBirthDate(birthDate);

				result.add(vo);
			}
					
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
		
	}
	
	public EmployeeVo get(String empno) {
		EmployeeVo vo = new EmployeeVo();
		
		try {
			conn = getConn();
			
			//3. Statement 객체 생성 ( 받아오기 )
			String sql = "select emp_no, first_name, last_name, gender, date_format(hire_date, '%Y년 %m월 %d일'), birth_date from employees where emp_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empno);
			
			//4. SQL 문 실행
			rs = pstmt.executeQuery();
			
			//5.결과 가져오기 
			while (rs.next()) {
				Long no = rs.getLong(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				String gender = rs.getString(4);
				String hireDate = rs.getString(5);
				String birthDate = rs.getString(6);
				
				vo = new EmployeeVo();
				vo.setNo(no);
				vo.setFirstName(first_name);
				vo.setLastName(last_name);
				vo.setGender(gender);
				vo.setHireDate(hireDate);
				vo.setBirthDate(birthDate);
				
			}
			
					
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	public boolean update(EmployeeVo vo) {
		boolean b = false;
		try {
			
			conn = getConn();
			
			String sql = "update employees set first_name = ? where emp_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "초코파이");
			pstmt.setString(2, "2");

			rs = pstmt.executeQuery();
			
			if(rs!=null) {
				System.out.println("update 성공!!");
				b=true;
			}else  {
				System.out.println("update 실패!!");
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
				
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		
		return b;
	}

	public boolean delete(String no) {
		boolean b =false;
		
		try {
			conn = getConn();
			
			String sql = "delete from employees where emp_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			if(rs!=null) {
				System.out.println("delete 성공!!");
				b = true;
			}else { 
				System.out.println("delete 실패 !!!!");
			}
			
		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return b;
	}
}
