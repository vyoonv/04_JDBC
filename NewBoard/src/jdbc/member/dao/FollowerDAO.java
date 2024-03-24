package jdbc.member.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static jdbc.main.common.JDBCTemplate.*;

import jdbc.member.dto.Follower;

public class FollowerDAO {
	
	private Statement stmt; 
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	private Properties prop; 
	
	public FollowerDAO() {
		
		try {
		prop = new Properties();
		prop.loadFromXML(new FileInputStream("follower-sql.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 팔로워 목록 
	 * @param conn
	 * @param memNo
	 * @return 
	 */
	public List<Follower> viewFollowers(Connection conn, int memNo) throws Exception{
		
		List<Follower> fList = new ArrayList<Follower>(); 
		
		try {
			
			String sql = prop.getProperty("viewFollowers");
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, memNo);
			
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				
				Follower f = new Follower(); 
				
				f.setFollwerId(rs.getString("FOLLOWER_ID"));
				f.setFollowerName(rs.getString("FOLLOWER_NAME"));
				
				fList.add(f); 
				
			}
			
			
			
		} finally {
			close(rs); 
			close(pstmt);
		}
		
		return fList;
	}

}
