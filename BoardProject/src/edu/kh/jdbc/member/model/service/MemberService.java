package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.util.List;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	/** 회원 목록 조회 
	 * @return list
	 */
	public List<Member> selectMemberList() throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Member> list = dao.selectMemberList(conn); 
		
		close(conn); 
		
		return list;
	} 
	
	
	/** 내정보 수정 
	 * @param memberName
	 * @param memberGender
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception {
	
	Connection conn = getConnection(); 
	
	int result = dao.updateMember(conn, memberName, memberGender, memberNo);
	
	if(result > 0) commit(conn); 
	else rollback(conn); 
	
	close(conn); 
	
	return result;
	
	}
	
}
