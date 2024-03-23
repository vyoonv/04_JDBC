package jdbc.board.service;

import java.sql.Connection;
import java.util.List;
import static jdbc.main.common.JDBCTemplate.*;

import jdbc.board.dao.BoardDAO;
import jdbc.board.dto.Board;

public class BoardService {
	
	private BoardDAO bDao = new BoardDAO();  
	

	/** 게시글 전체조회 
	 * @return
	 */
	public List<Board> selectAllBoard() throws Exception {
		
		Connection conn = getConnection(); 
		
		List<Board> boardList = bDao.selectAllBoard(conn);
		
		close(conn); 
	
		return boardList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
