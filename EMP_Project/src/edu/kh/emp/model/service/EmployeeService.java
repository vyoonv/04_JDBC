package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import static edu.kh.emp.model.common.Template.*;

import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {
	
	
	private EmployeeDAO dao = new EmployeeDAO(); 
	

	/**전체 사원 정보 조회 서비스 
	 * @return
	 */
	public List<Employee> selectAll() throws Exception {
		
		 Connection conn = getConnection(); 
		 
		 List<Employee> list = dao.selectAll(conn);
		 
		 close(conn); 
		
		
		return list;
	}


	/** 사원 정보 추가 
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public int insertEmployee(Employee emp) throws Exception {
		
		Connection conn = getConnection(); 
		
		int result = dao.insertEmployee(conn, emp); 
		
		if(result > 0 ) commit(conn); 
		else 			rollback(conn); 
		
		close(conn); 
	
		return result;
	}


	/** 사번이 일치하는 사원 정보 조회 
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpId(int empId) throws Exception {
		
		Connection conn = getConnection(); 
		
		Employee emp = dao.selectEmpId(conn, empId); 
		
		close(conn); 
		
		return emp;
	}


	/** 사번이 일치하는 사원 정보 수정 
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public int updateEmployee(Employee emp) throws Exception {
		
		Connection conn = getConnection(); 
		
		int result = dao.updateEmployee(conn, emp); 
		
		if(result > 0) commit(conn); 
		else 		rollback(conn); 
		
		close(conn); 
		
		return result;
	}


	/** 사번이 일치하는 사원 정보 삭제 
	 * @param empId
	 * @return
	 */
	public int deleteEmployee(int empId) throws Exception {
		
		 Connection conn = getConnection(); 
		 
		 int result = dao.deleteEmployee(conn, empId); 
		 
		 if(result > 0) commit(conn); 
		 else 			rollback(conn); 
		 
		 close(conn); 
		
		return result;
	}


	/** 부서명이 일치하는 모든 사원 정보 조회 
	 * @param deptTitle
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectDeptEmp(String deptTitle) throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Employee> list = dao.selectDeptEmp(conn, deptTitle); 
		
		close(conn); 
		
		
		return list;
	}


	/** 입력받은 급여 이상을 받는 모든 사원 정보 조회 서비스 
	 * @param salary
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalaryEmp(int salary) throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Employee> list = dao.selectSalaryEmp(conn, salary); 
		
		close(conn); 
		
		
		return list;
	}


	/** 부서별 급여 합 조회 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> selectDeptTotalSalary() throws Exception {
		
		Connection conn = getConnection(); 
		
		Map<String, Integer> map = dao.selectDeptTotalSalary(conn); 
		
		close(conn); 
		
		return map;
	}


	/** 주민번호 일치하는 사원 정보 조회 
	 * @param empNo
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpNo(String empNo) throws Exception {
		
		Connection conn = getConnection(); 
		
		Employee emp = dao.selectEmpNo(conn, empNo);
		
		close(conn);
		
		return emp;
	}


	/**  직급별 급여 평균 조회 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Double> selectJobAvgSalary() throws Exception {
		
		Connection conn = getConnection(); 
		
		Map<String, Double> map = dao.selectJobAvgSalary(conn); 
		
		close(conn); 
		
		return map;
	}


	/** 사번이 일치하는 사원의 사수 정보 조회 
	 * @param empId
	 * @return
	 */
	public Employee selectManager(int empId) throws Exception {
		
		Connection conn = getConnection(); 
		
		Employee emp = dao.selectManager(conn, empId);
		
		close(conn); 
				
		return emp;
	}


	/** 부서별 급여 평균 조회 
	 * @return
	 */
	public Map<String, Double> selectDeptAvgSalary() throws Exception {
		
		Connection conn = getConnection(); 
		
		Map<String, Double> map = dao.selectDeptAvgSalary(conn);
		
		close(conn); 
		return map;
	}


	/** 입력받은 사원과 같은 지역에 근무하는 사원 정보 조회 
	 * @param empName
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectLocal(String empName) throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Employee> list = dao.selectLocal(conn, empName); 
		
		close(conn); 
		
		return list;
	}


	/** 입력받은 직급과 일치하는 모든 사원 조회 
	 * @param jobName
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectJobName(String jobName) throws Exception {

		Connection conn = getConnection(); 
		
		List<Employee> list = dao.selectJobName(conn, jobName); 
		
		close(conn); 
		
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
