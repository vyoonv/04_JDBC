package edu.kh.emp.view;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.kh.emp.model.service.EmployeeService;
import edu.kh.emp.model.vo.Employee;

public class EmployeeView {

	
	Scanner sc = new Scanner(System.in);
	
	private EmployeeService service = new EmployeeService(); 
	

	public void displayMenu() {
		
		int input = 0; 
		
		do {
			
			try {
				
					System.out.println("======================================================");	
					System.out.println("================사원 관리 프로그램====================");
					System.out.println("1. 전체 사원 정보 조회");
					System.out.println("2. 새로운 사원 추가");
					System.out.println("3. 사번이 일치하는 사원 정보 조회");
					System.out.println("4. 사번이 일치하는 사원 정보 수정");
					System.out.println("5. 사번이 일치하는 사원 정보 삭제");
					System.out.println("6. 입력 받은 부서와 일치하는 모든 사원 정보 조회");
					System.out.println("7. 입력 받은 급여 이상을 받는 모든 사원 정보 조회");
					System.out.println("8. 부서별 급여 합 전체 조회");
					System.out.println("9. 주민등록번호가 일치하는 사원정보 조회");
					System.out.println("10. 직급별 급여 평균 조회");
					System.out.println("11. 사번이 일치하는 사원 사수 정보 조회");
					System.out.println("12. 부서별 급여 평균 조회");
					System.out.println("13. 입력 받은 사원과 같은 지역에서 근무하는 사원 정보 조회");
					System.out.println("14. 입력 받은 직급과 일치하는 모든 사원 정보 조회");					
					System.out.println("0. 프로그램 종료");
					
					System.out.print("메뉴 선택 >>");
					input = sc.nextInt(); 
					sc.nextLine(); 
					
					System.out.println();
					
					switch(input) {
					
					case 1 : selectAll(); break; 
					case 2 : insertEmployee(); break; 
					case 3 : selectEmpId(); break; 
					case 4 : updateEmployee(); break; 
					case 5 : deleteEmployee(); break; 
					case 6 : selectDeptEmp(); break; 
					case 7 : selectSalaryEmp(); break; 
					case 8 : selectDeptTotalSalary(); break; 
					case 9 : selectEmpNo(); break; 
					case 10 :selectJobAvgSalary(); break; 
					case 11 :selectManager(); break; 
					case 12 :selectDeptAvgSalary(); break; 
					case 13 :selectLocal(); break; 
					case 14 :selectJobName(); break; 
					case 0 : System.out.println("프로그램을 종료합니다.."); break; 
					default : System.out.println("메뉴에 존재하는 번호만 입력하세요");
					
					}
			
				
			} catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요");
				input = -1; 
				sc.nextLine(); 
			} catch (Exception e) {
				e.printStackTrace();
			}		
			
		} while(input != 0); 
			
		
	}




	/**
	 * 전체 사원 정보 조회
	 */
	private void selectAll() throws Exception {
		
		System.out.println("<전체 사원 정보 조회>");
		
		List<Employee> empList = service.selectAll(); 
		
		printAll(empList); 
	}

	
	/** 보조메서드 사번 입력 받아 반환하는 메서드 
	 * @return
	 */
	public int inputEmpId() {
		
		System.out.print("사번 입력 : ");
		int empId = sc.nextInt(); 		
		sc.nextLine(); 
		return empId; 
		
	}
	
	/**
	 * 사원 정보 추가 
	 */
	private void insertEmployee() throws Exception {
		
		System.out.println("<사원 정보 추가>");
		
		int empId = inputEmpId(); 
		
		System.out.print("이름 : ");
		String empName = sc.next(); 
		
		System.out.print("주민등록번호('-' 포함) : ");
		String empNo = sc.next(); 
		
		System.out.print("이메일 : ");
		String email = sc.next(); 
		
		System.out.print("전화번호 : ");
		String phone = sc.next(); 
		
		System.out.print("부서코드(D1~D9) : ");
		String deptCode = sc.next(); 
		
		System.out.print("직급코드(J1~J7) : ");
		String jobCode = sc.next();
		
		System.out.print("급여등급(S1~S6) : ");
		String salLevel = sc.next();
		
		System.out.print("급여 : ");
		int salary = sc.nextInt(); 
		
		System.out.print("보너스 : ");
		double bonus = sc.nextDouble(); 
		
		System.out.print("사수번호 : ");
		int managerId = sc.nextInt(); 
		
		Employee emp = new Employee(empId, empName, empNo, email, phone, salary, deptCode, jobCode, salLevel, bonus, managerId); 
		
		int result  = service.insertEmployee(emp); 
		
		if(result > 0) {
			System.out.println("사원 정보 추가 성공!");
		} else {
			System.out.println("사원 정보 추가 실패!");
		}
		
		
		
		
		
	}

	/**
	 * 사번이 일치하는 사원 정보 조회 
	 */
	private void selectEmpId() throws Exception {
		
		System.out.println("<사번이 일치하는 사원 정보 조회>");
		
		int empId = inputEmpId();
		
		Employee emp = service.selectEmpId(empId); 
		
		printOne(emp); 
	}

	/**
	 * 사번이 일치하는 사원 정보 수정
	 */
	private void updateEmployee() throws Exception {
		
		System.out.println("<사번이 일치하는 사원 정보 수정>");
		
		int empId = inputEmpId(); 
		
		System.out.print("이메일 : ");
		String email = sc.next(); 
		
		System.out.print("전화번호(-제외) : ");
		String phone = sc.next(); 
		
		System.out.print("급여 : ");
		int salary = sc.nextInt(); 
		
		System.out.print("보너스 : ");
		double bonus = sc.nextDouble(); 
		
		Employee emp = new Employee(); 
		
		emp.setEmpId(empId);
		emp.setEmail(email);
		emp.setPhone(phone);
		emp.setSalary(salary);
		emp.setBonus(bonus);
		
		int result = service.updateEmployee(emp); 
		
		if(result > 0) {
			System.out.println("사원 정보가 수정되었습니다");
			
		} else {
			System.out.println("사번이 일치하는 직원이 없습니다");
		}
		
		
		
	}

	/**
	 * 사번이 일치하는 사원 삭제 
	 */
	private void deleteEmployee() throws Exception {
		
		System.out.println("<사번이 일치하는 사원 정보 삭제>");
		
		int empId = inputEmpId(); 
		
		System.out.print("정말 삭제하시겠습니까? (Y/N) : ");
		char input = sc.next().toUpperCase().charAt(0); 
		
		if(input == 'Y') {
			int result = service.deleteEmployee(empId); 
			
			if(result > 0) {
				System.out.println("삭제되었습니다");
			} else {
				System.out.println("사번이 일치하는 사원이 없습니다");
			}
		} else {
			System.out.println("취소되었습니다");
		}
		
	
	}

	/**
	 * 부서명이 일치하는 모든 사원 정보 조회 
	 */
	private void selectDeptEmp() throws Exception {
		
		System.out.println("<부서명이 일치하는 모든 사원 정보 조회>");
		
		System.out.print("부서명 : ");
		String deptTitle = sc.next(); 		
		
		printAll(service.selectDeptEmp(deptTitle)); 
		
	}

	/**
	 * 입력받은 급여 이상을 받는 모든 사원 정보 조회 
	 */
	private void selectSalaryEmp() throws Exception {
		
		System.out.println("<입력 받은 급여 이상을 받는 모든 사원 정보 조회>");
		
		System.out.print("급여 입력 : ");
		int salary = sc.nextInt(); 
		
		printAll(service.selectSalaryEmp(salary)); 
		
	}

	/**
	 * 부서 급여 총합 조회 
	 */
	private void selectDeptTotalSalary() throws Exception {
		
		System.out.println("<부서별 급여 총합 조회>");
		
		Map<String, Integer> map = service.selectDeptTotalSalary(); 
		
		for(String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key));
		}
		
		
		
	}

	/**
	 * 주민 번호 일치하는 사원 정보 조회 
	 */
	private void selectEmpNo() throws Exception {
		
		System.out.println("<주민등록번호 일치하는 사원 정보 조회>");
		
		System.out.print("주민등록번호 입력('-'포함) : ");
		String empNo = sc.next(); 
		
		Employee emp = service.selectEmpNo(empNo); 
		
		printOne(emp); 
		
	}
	
	/**
	 * 직급별 급여 평균 조회 
	 */
	private void selectJobAvgSalary() throws Exception{
		
		System.out.println("<직급별 급여 평균 조회>");
		
		Map<String, Double> map = service.selectJobAvgSalary(); 
		
		for(String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key) + "원");
		}
		
	}
	
	
	/**
	 * 사번이 일치하는 사수 정보 조회 
	 */
	private void selectManager() throws Exception {
		System.out.println("<사번이 일치하는 사원의 사수 정보 조회>");
		
		int empId = inputEmpId(); 
		
		Employee emp = service.selectManager(empId); 
		
		printOne(emp); 
		
	}
	
	
	
	/**
	 * 부서별 급여 평균 조회 
	 */
	private void selectDeptAvgSalary() throws Exception {
		System.out.println("<부서별 급여 평균 조회>");
		
		Map<String, Double> map = service.selectDeptAvgSalary(); 
		
		for(String key : map.keySet()) {
			System.out.println(key + " : " + map.get(key) + "원");
		}
		
	}
	
	

	/**
	 * 입력받은 사원과 같은 지역에 근무하는 직원 정보 조회 
	 */
	private void selectLocal() throws Exception{
		
		System.out.println("<입력받은 사원과 같은 지역에 근무하는 직원 정보 조회>");
		
		System.out.print("사원명 입력 : ");
		String empName = sc.next(); 

		printAll(service.selectLocal(empName));
		
		
		
	}
	
	

	/**
	 * 입력받은 직급과 일치하는 사원 정보 조회 
	 */
	private void selectJobName() throws Exception {
		
		System.out.println("<입력 받은 직급과 일치하는 모든 사원 정보 조회>");
		
		System.out.print("직급 입력 : ");
		String jobName = sc.next(); 
		
		printAll(service.selectJobName(jobName)); 
		
	}
	
	
	
	
	
	
	
	










	// 보조 메서드
	
		/** 전달받은 사원 List 모두 출력
		 *
		 */
		public void printAll(List<Employee> empList) {
			
			if(empList.isEmpty()) {
				System.out.println("조회된 사원 정보가 없습니다.");
				
			} else {
				System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
				System.out.println("------------------------------------------------------------------------------------------------");
				for(Employee emp : empList) {
					System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
							emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(),
							emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
				}
			
			}
			
			return;
		}

	
	
		/** 사원 1명 정보 출력
		 * @param emp
		 */
		public void printOne(Employee emp) {
			if(emp == null) {
				System.out.println("조회된 사원 정보가 없습니다.");
				
			} else {
				System.out.println("사번 |   이름  | 주민 등록 번호 |        이메일        |   전화 번호   | 부서 | 직책 | 급여" );
				System.out.println("------------------------------------------------------------------------------------------------");
				
				System.out.printf(" %2d  | %4s | %s | %20s | %s | %s | %s | %d\n",
						emp.getEmpId(), emp.getEmpName(), emp.getEmpNo(), emp.getEmail(),
						emp.getPhone(), emp.getDepartmentTitle(), emp.getJobName(), emp.getSalary());
			}
		}



	
	
	
}
