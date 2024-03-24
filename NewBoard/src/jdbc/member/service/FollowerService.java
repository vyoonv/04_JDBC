package jdbc.member.service;

import java.sql.Connection;
import java.util.List;
import static jdbc.main.common.JDBCTemplate.*;

import jdbc.member.dao.FollowerDAO;
import jdbc.member.dto.Follower;

public class FollowerService {
	
	private FollowerDAO fdao = new FollowerDAO(); 

	/** 팔로워 목록 
	 * @param memNo 
	 * @return
	 */
	public List<Follower> viewFollowers(int memNo) throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Follower> fList = fdao.viewFollowers(conn, memNo);
		
		close(conn);
		
		
		return fList;
	}

}
