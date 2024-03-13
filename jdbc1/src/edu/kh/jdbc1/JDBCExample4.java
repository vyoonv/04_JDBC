package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Emp;
import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
	
	public static void main(String[] args) {
		
		
		// 직급명, 급여를 입력받아 
		// 해당 직급에서 입력받은 급여보다 많이 받는 사원의 
		// 이름 직급명, 급여, 연봉을 조회하여 출력 
		// 단 조회결과가 없으면 "조회 결과 없음" 출력 
		
		// 조회결과가 있으면 아래와 같이 출력 
		// 직급명 입력 : 부사장 
		// 급여 입력 : 5000000
		// 송종기 / 부사장 / 6000000/ 7200000
		// 노옹철 / 부사장 / /...
		// Employee (empName, jobName, salary, annualIncome ) 
		
		Connection conn = null; 
		Statement stmt = null; 
		ResultSet rs = null; 
		
		Scanner sc = new Scanner (System.in);

		try {
			
			System.out.print("직급명 입력 : ");  
			String inputJ = sc.nextLine();
			
			System.out.print("급여 입력 : ");
			int inputS = sc.nextInt(); 
			
			// JDBC 참조변수에 알맞은 객체 대입 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			String type = "jdbc:oracle:thin:@";  
			String ip = "localhost";
			String port = ":1521"; 			
			String sid = ":XE"; 			
			String user = "kh_jyh";  			
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(type+ip+port+sid, user, pw); 
			
			String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, SALARY*12 AS annualIncome"
					+ " FROM EMPLOYEE"
					+ " JOIN JOB USING(JOB_CODE)"
					+ " WHERE SALARY > '" + inputS + "'" 
					+ " AND JOB_NAME = '" + inputJ + "'";
			
			stmt = conn.createStatement(); 
			
			rs = stmt.executeQuery(sql); 
			
			List<Employee> list = new ArrayList<Employee>(); 
			
			while (rs.next()) {
				
				String empName = rs.getString("EMP_NAME"); 
				String jobName = rs.getString("JOB_NAME"); 
				int salary = rs.getInt("SALARY"); 
				int annualIncome = rs.getInt("annualIncome");
				
		/*		Employee employee = new Employee(empName, jobName, salary, annualIncome); 
				
				list.add(employee);	       */
				
				 list.add(new Employee (empName, jobName, salary, annualIncome));
				
			}
				
				if(list.isEmpty()) { 
					System.out.println("조회 결과 없음");
					
					
				} else {					 
					
					for ( Employee emp : list ) {
						
						System.out.println(emp);
					}
				}			
			
			
			
		 } catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
