package kr.co.itcen.hr;

import java.util.List;
import java.util.Scanner;

public class HRMain {

	public static void main(String[] args) {
		EmployeeDao dao = new EmployeeDao();
		EmployeeVo vo = new EmployeeVo();
		Scanner sc = new Scanner(System.in);
		
		System.out.println(">>");
		String keyword = sc.nextLine();
		
		//직원 정보 저장 ( C : Create)  완료
//		dao.insert(vo); 
		
		//전체 리스트    완료
//		List<EmployeeVo> list = dao.getList();
//		printEmployee(list);
		
		
		//직원 검색 (이름) (R : Result )    완료 and 연결이어서 이름 둘다 있어야함
//		List<EmployeeVo> list = dao.getList(keyword);
//		printEmployee(list);
		
		//직원 검색 (사번) (R : Result )  완료
//		vo = dao.get(keyword);
//		printEmployeevo(vo);
		
		
		//직원 정보 수정 ( U : Update )
		boolean resultUP = dao.update(vo);
		System.out.println(resultUP);
		
		//직원 삭제 (사번) (D : Delete ) 완료
//		boolean resultDel = dao.delete(keyword);
//		System.out.println(resultDel);
	}
	
	private static void printEmployee(List<EmployeeVo> list) {
		for(EmployeeVo vo:list) {
			System.out.println(vo);
		}
	}
	private static void printEmployeevo(EmployeeVo vo) {
		System.out.println(vo);
	}

}
