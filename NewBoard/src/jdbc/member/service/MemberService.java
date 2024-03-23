package jdbc.member.service;

import static jdbc.main.common.JDBCTemplate.*;

import java.sql.Connection;

import jdbc.member.dao.MemberDAO;
import jdbc.member.dto.Member;


public class MemberService {
	
	private MemberDAO dao = new MemberDAO(); 

	/** 내정보 수정
	 * @param memName
	 * @param current
	 * @param newPw1
	 * @param memNo
	 * @return result 
	 */
	public int updateInfo(String memName, String current, String newPw1, int memNo) throws Exception{
		
		Connection conn = getConnection(); 
		
		int result = dao.updateInfo(conn, memName, current, newPw1, memNo); 
		
		if(result > 0) commit(conn); 
		else rollback(conn); 
		
		close(conn); 
	
		return result;
	}

}
