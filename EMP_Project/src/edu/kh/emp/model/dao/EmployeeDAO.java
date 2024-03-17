package edu.kh.emp.model.dao;

import static edu.kh.emp.model.common.Template.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.kh.emp.model.vo.Employee;

public class EmployeeDAO {

	private Statement stmt; 
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	
	private Properties prop; 
	
	
	public EmployeeDAO() {

		try {
			
			prop = new Properties(); 
			prop.loadFromXML(new FileInputStream("query.xml"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/** 전체 사원 정보 조회 
	 * @param conn
	 * @return
	 */
	public List<Employee> selectAll(Connection conn) throws Exception {
		
		List<Employee> list = new ArrayList<Employee>(); 
		
		try {
			
			String sql = prop.getProperty("selectAll"); 
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			
			while (rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME"); 
				String empNo = rs.getString("EMP_NO"); 
				String email = rs.getString("EMAIL"); 
				String phone = rs.getString("PHONE"); 
				String departmentTitle = rs.getString("DEPT_TITLE"); 
				String jobName = rs.getString("JOB_NAME"); 
				int salary = rs.getInt("SALARY"); 
				
				Employee emp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary); 
				
				list.add(emp); 
				
				
			}
			
			
			
			
		} finally {
			
			close(rs);
			close(stmt);
			
		}
		
		return list;
	}




	/** 사원 정보 추가 
	 * @param conn
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public int insertEmployee(Connection conn, Employee emp) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("insertEmployee"); 
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setString(3, emp.getEmpNo());
			pstmt.setString(4, emp.getEmail());
			pstmt.setString(5, emp.getPhone());
			pstmt.setString(6, emp.getDeptCode());
			pstmt.setString(7, emp.getJobCode());
			pstmt.setString(8, emp.getSalLevel());
			pstmt.setInt(9, emp.getSalary());
			pstmt.setDouble(10, emp.getBonus());
			pstmt.setInt(11, emp.getManagerId());
			
			result = pstmt.executeUpdate(); 
			
			
			
		} finally {
			
			close(pstmt); 
			
		}
		
		
		
		return result;
	}




	/** 사번이 일치하는 사원 정보 조회 
	 * @param conn
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpId(Connection conn, int empId) throws Exception {
		
		Employee emp = null; 
		
		try {
			
			String sql = prop.getProperty("selectEmpId"); 
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setInt(1, empId);
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				
				String empName = rs.getString("EMP_NAME"); 
				String empNo = rs.getString("EMP_NO"); 
				String email = rs.getString("EMAIL"); 
				String phone = rs.getString("PHONE"); 
				String departmentTitle = rs.getString("DEPT_TITLE"); 
				String jobName = rs.getString("JOB_NAME"); 
				int salary = rs.getInt("SALARY"); 
				
				emp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary);
				
			}
			
			
			
		} finally {
			
			close(rs); 
			close(pstmt); 
		}
		
		
		return emp;
	}




	/** 사번이 일치하는 사원 정보 수정 
	 * @param conn
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	public int updateEmployee(Connection conn, Employee emp) throws Exception {
		
		int result = 0; 
		
		try {
			String sql = prop.getProperty("updateEmployee");
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, emp.getEmail());
			pstmt.setString(2, emp.getPhone());
			pstmt.setInt(3, emp.getSalary());
			pstmt.setDouble(4, emp.getBonus());
			pstmt.setInt(5, emp.getEmpId());
			
			result = pstmt.executeUpdate(); 
			
			
			
		} finally {
			close(pstmt); 
		}
		
		
		
		return result;
	}




	/** 사번이 일치하는 사원 정보 삭제 
	 * @param conn
	 * @param empId
	 * @return
	 */
	public int deleteEmployee(Connection conn, int empId) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("deleteEmployee"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, empId);
			
			result = pstmt.executeUpdate(); 
							
			
		} finally {
			
			close(pstmt); 
			
		}
		
		
		return result;
	}




	/** 부서명이 일치하는 모든 사원 정보 조회 
	 * @param conn
	 * @param deptTitle
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectDeptEmp(Connection conn, String deptTitle) throws Exception {
		
		List<Employee> empList = new ArrayList<Employee>(); 
		
		try {
			
			String sql = prop.getProperty("selectDeptEmp"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, deptTitle);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME"); 
				String empNo = rs.getString("EMP_NO"); 
				String email = rs.getString("EMAIL"); 
				String phone = rs.getString("PHONE"); 
				
				String jobName = rs.getString("JOB_NAME"); 
				int salary = rs.getInt("SALARY"); 
				
				Employee emp = new Employee(empId, empName, empNo, email, phone, deptTitle, jobName, salary);
				
				empList.add(emp); 				
			}
			
			
			
		} finally {
			close(rs); 
			close(pstmt); 
		}
		
		
		
		return empList;
	}




	/** 입력 받은 급여 이상을 받는 모든 사원 정보 조회 
	 * @param conn
	 * @param salary
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectSalaryEmp(Connection conn, int salary) throws Exception {
		
		List<Employee> empList = new ArrayList<Employee>(); 
		
		try {
			
			String sql = prop.getProperty("selectSalaryEmp"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, salary);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME"); 
				String empNo = rs.getString("EMP_NO"); 
				String email = rs.getString("EMAIL"); 
				String phone = rs.getString("PHONE"); 
				String departmentTitle = rs.getString("DEPT_TITLE"); 
				String jobName = rs.getString("JOB_NAME"); 
				int salary2 = rs.getInt("SALARY"); 
				
				Employee emp = new Employee(empId, empName, empNo, email, phone, departmentTitle, jobName, salary2);
				
				empList.add(emp); 
				
			}
			
			
		} finally {
			close(pstmt); 
		}
		
		
		return empList;
	}




	/** 부서별 급여 합 조회 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> selectDeptTotalSalary(Connection conn) throws Exception {
		
		Map<String, Integer> map = new LinkedHashMap<String, Integer>(); 
		
		try {
			
			String sql = prop.getProperty("selectDeptTotalSalary"); 
			
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()) {
				
				String deptCode = rs.getString("DEPT_CODE"); 
				int salary = rs.getInt("SALARY"); 
				
				map.put(deptCode, salary); 
				
			}
			
			
		} finally {
			close(stmt); 
		}
		
		return map;
	}




	/** 주민번호 일치하는 사원 정보 조회 
	 * @param conn
	 * @param empNo
	 * @return
	 * @throws Exception
	 */
	public Employee selectEmpNo(Connection conn, String empNo) throws Exception {
		
		Employee emp = new Employee(); 
		
		try {
			
			String sql = prop.getProperty("selectEmpNo"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId, empName, empNo, email, 
								phone, departmentTitle, jobName, salary);
				
			}
			
			
		} finally {
			close(pstmt); 
		}
		
		
		return emp;
	}




	/** 직급별 급여 평균 조회 
	 * @param conn
	 * @return
	 */
	public Map<String, Double> selectJobAvgSalary(Connection conn) throws Exception {
		
		Map<String, Double> map = new LinkedHashMap<String, Double>(); 
		
		try {
			
			String sql = prop.getProperty("selectJobAvgSalary"); 
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
			
			while (rs.next()) {
				
				String jobName = rs.getString("JOB_NAME"); 
				double salary = rs.getDouble("SALARY"); 
				
				map.put(jobName, salary); 
				
			}
			
			
		} finally {
			close(stmt); 
		}
		
		return map;
	}




	/** 사번이 일치하는 사원의 사수 정보 조회 
	 * @param conn
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	public Employee selectManager(Connection conn, int empId) throws Exception {
		
		Employee emp = new Employee(); 
		
		try {
			
			String sql = prop.getProperty("selectManager"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				
				int empId2 = rs.getInt("EMP_ID");
				String empName = rs.getString("EMP_NAME");
				String empNo = rs.getString("EMP_NO"); 				
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				emp = new Employee(empId2, empName, empNo, email, 
								phone, departmentTitle, jobName, salary);
			}
			
			
		} finally {
			
			close(pstmt); 
		}
		
		return emp;
	}




	/** 부서별 평균 급여 조회 
	 * @param conn
	 * @return
	 */
	public Map<String, Double> selectDeptAvgSalary(Connection conn) throws Exception {
		
		Map<String, Double> map = new LinkedHashMap<String, Double>(); 
		
		try {
			
			String sql = prop.getProperty("selectDeptAvgSalary"); 
			
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			
			while(rs.next()) {
				String deptTitle = rs.getString("DEPT_TITLE"); 
				double salary = rs.getDouble("SALARY"); 
				
				map.put(deptTitle, salary); 
			}
			
			
			
		} finally {
			close(rs); 
			close(stmt); 
		}
		
		return map;
	}




	/** 입력 받은 직원과 같은 지역에 근무하는 사원 정보 조회 
	 * @param conn
	 * @param empName
	 * @return
	 * @throws Exception
	 */
	public List<Employee> selectLocal(Connection conn, String empName) throws Exception {
		
		List<Employee> empList = new ArrayList<Employee>(); 
		
		try {
			
			String sql = prop.getProperty("selectLocal"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, empName);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName2 = rs.getString("EMP_NAME"); 
				String empNo = rs.getString("EMP_NO"); 
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName2, empNo, email, 
								phone, departmentTitle, jobName, salary);
				
				empList.add(emp); 
				
			}
			
		} finally {
			close(pstmt); 
		}
		
		
		return empList;
	}




	/** 입력 받은 직급과 일치하는 모든 사원 조회 
	 * @param conn
	 * @param jobName
	 * @return
	 */
	public List<Employee> selectJobName(Connection conn, String jobName) throws Exception {
		
		List<Employee> empList = new ArrayList<Employee>(); 
		
		try {
			
			String sql = prop.getProperty("selectJobName"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, jobName);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				int empId = rs.getInt("EMP_ID"); 
				String empName = rs.getString("EMP_NAME"); 
				String empNo = rs.getString("EMP_NO"); 
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String departmentTitle = rs.getString("DEPT_TITLE");		
				int salary = rs.getInt("SALARY");
				
				Employee emp = new Employee(empId, empName, empNo, email, 
								phone, departmentTitle, jobName, salary);
				
				empList.add(emp); 
				
				
			}
			
			
		} finally {
			close (pstmt);
		}
		return empList;
	}

	


	
	
	
	
	
	
	
	
	
}
