package jdbc.member.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import static jdbc.main.common.JDBCTemplate.*;

public class MemberDAO {
	
	private Statement stmt; 
	private PreparedStatement pstmt; 
	private ResultSet rs;
	private Properties prop; 

	public MemberDAO() {
		try {
			
			prop = new Properties(); 
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/** 내정보 수정 
	 * @param conn
	 * @param memName
	 * @param current
	 * @param newPw1
	 * @param memNo
	 * @return
	 */
	public int updateInfo(Connection conn, String memName, String current, String newPw1, int memNo) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("updateInfo"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memName);
			pstmt.setString(2, newPw1);
			pstmt.setString(3, current);
			pstmt.setInt(4, memNo);
			
			result = pstmt.executeUpdate(); 
			
			
		} finally {
			
			close(pstmt); 
		}
		
		return result;
	}

}
