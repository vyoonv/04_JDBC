package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static edu.kh.emp.common.JDBCTemplate.*;
import edu.kh.emp.model.dao.EmployeeDAO;
import edu.kh.emp.model.vo.Employee;

public class EmployeeService {
	
	private EmployeeDAO dao = new EmployeeDAO();

	/** 전체 사원 정보 조회 서비스 
	 * @return list 
	 */
	public List<Employee> selectAll() throws Exception {
		
		// 1. 커넥션 생성 
		Connection conn = getConnection();
		
		List<Employee> list = dao.selectAll(conn); 
		
		close(conn);
				
		return list;
	}

	
	/** 사원 정보 추가 서비스 
	 * @param emp
	 * @return result (1/0)
	 */
	public int insertEmployee(Employee emp) throws Exception {
		
		Connection conn = getConnection(); 
		
		int result = dao.insertEmployee(conn, emp); 
			
		if(result > 0) commit(conn); 
		else 		 rollback(conn);
		
		close(conn); 
		
		return result;
	}


	/** 사번이 일치하는 사원 정보 조회 서비스 
	 * @param empId
	 * @return emp
	 * @throws Exception
	 */
	public Employee selectEmpId(int empId) throws Exception {
		
		Connection conn = getConnection(); 
		
		Employee emp = dao.selectEmpId(conn, empId); 
				
		close(conn); 
		
		return emp;
	}


	/** 사번이 일치하는 사원 정보 수정 서비스 
	 * @param emp
	 * @return result
	 */
	public int updateEmployee(Employee emp) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateEmployee(conn, emp); 
		
		if(result > 0) commit(conn); 
		else 		rollback(conn); 
		
		close(conn); 
		
		return result;
	}


	/** 사번이 일치하는 사원 정보 삭제 서비스 
	 * @param empId
	 * @return result
	 */
	public int deleteEmployee(int empId) throws Exception {
		
		Connection conn = getConnection(); 
		
		int result = dao.deleteEmployee(conn, empId); 
		
		if(result > 0 ) commit(conn); 
		else 			rollback(conn); 
		
		close(conn); 
		
		return result;
	}


	/** 부서명이 일치하는 모든 사원 정보 조회 서비스 
	 * @param deptTitle
	 * @return list 
	 * @throws Exception
	 */
	public List<Employee> selectDeptEmp(String departmentTitle) throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Employee> list = dao.selectDeptEmp(conn, departmentTitle); 
		
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
		
		Map<String,Integer> map = dao.selectDeptTotalSalary(conn);
		
		close(conn); 
	
		return map;
	}

	
	/** 주민번호 일치하는 사원정보 조회 
	 * @param empNo
	 * @return
	 */
	public Employee selectEmpNo(String EmpNo) throws Exception {
		
		Connection conn = getConnection(); 
		
		Employee emp = dao.selectEmpNo(conn, EmpNo); 
		
		close (conn);
		
		return emp;
	}
	
	
	/** 직급별 급여 평균 조회 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Double> selectJobAvgSalary() throws Exception {
		
		Connection conn = getConnection(); 
		
		Map<String, Double> map = dao.selectJobAvgSalary(conn); 
		
		close(conn); 
		
		
		return map;
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
