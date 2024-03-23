package jdbc.board.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static jdbc.main.common.JDBCTemplate.*;

import jdbc.board.dto.Board;

public class BoardDAO {
	

	private Statement stmt;
	private PreparedStatement pstmt; 
	private ResultSet rs; 
	
	private Properties prop; 
	
	public BoardDAO() {  // 기본생성자 
		
		try {
			
			prop = new Properties(); 			
			prop.loadFromXML(new FileInputStream("board-sql.xml"));
					
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	

	/** 전체 게시글 조회 
	 * @param conn
	 * @return
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception {
		
		List<Board> boardList = new ArrayList<Board>(); 
		
		try {
			
			String sql = prop.getProperty("selectAllBoard"); 
			
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Board board = new Board(); 
				
				board.setBoardNo(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setMemName(rs.getString(3));
				board.setCreateDate(rs.getString(4));
				board.setReadCount(rs.getInt(5));
				board.setCommentCount(rs.getInt(6));
				
				boardList.add(board);
				
				
			}
			
			
			
		} finally {
		
			close(rs); 
			close(stmt);
		}
		
		
		
		return boardList;
	}

}
