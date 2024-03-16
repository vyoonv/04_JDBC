package edu.kh.emp.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.emp.model.vo.Employee;

public class EmployeeService {

	/**전체 사원 정보 조회 서비스 
	 * @return
	 */
	public List<Employee> selectAll() {
		
		 Connection conn = getConnection(); 
		 
		 List<Employee> list = dao.selectAll(); 
		
		
		return null;
	}

}
