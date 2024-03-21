package jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import static jdbc.main.common.JDBCTemplate.*;
import jdbc.member.dto.Member;

public class MainDAO {
	
	private Statement stmt; 
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	private Properties prop; 
	
	public MainDAO() {
		try {
			
			prop = new Properties(); 
			prop.loadFromXML(new FileInputStream("main-sql.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	/** 로그인 
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return member
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception {
		
		Member member = null; 
		
		try {
			
			String sql = prop.getProperty("login"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				member = new Member(); 
				
				member.setMemNo(rs.getInt(1));
				member.setMemName(rs.getString(3));
				member.setMemGender(rs.getString(4));
				member.setEnrollDate(rs.getString(5));
			}
			
			
		} finally {
			close(rs); 
			close(pstmt); 
		}
		
		return member;
	}




	public int idDuplicationCheck(Connection conn, String memberId) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("idDuplicationCheck"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) {
				result = rs.getInt(1); 
			}
			
			
		} finally {
			close(rs); 
			close(pstmt); 
		}
		
		return result;
	}




	public int signUp(Connection conn, Member member) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("signUp"); 
			
			pstmt = conn.prepareStatement(sql); 
			
			pstmt.setString(1, member.getMemID());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemGender());
			
			result = pstmt.executeUpdate(); 
			
			
			
		} finally {
			close(pstmt); 
		}
		
		
		return result;
	}

	
	
}
