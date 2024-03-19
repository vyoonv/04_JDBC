package edu.kh.jdbc.board.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.board.model.dto.Board;

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

	/** 게시글 목록 조회 SQL 수행 DAO
	 * @param conn
	 * @return boardList
	 */
	public List<Board> selectAllBoard(Connection conn) throws Exception {
		
		// 결과 저장용 객체 생성 
		List<Board> boardList = new ArrayList<Board>(); 
		
		try {
			
			String sql = prop.getProperty("selectAllBoard"); 
			
			// SQL 수행 후 결과 반환받기 
			stmt = conn.createStatement(); 
			rs = stmt.executeQuery(sql); 
			
			//1행씩 접근하여 컬럼값을 얻어와 옮겨 담기
			
			while (rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO"); 
				String boardTitle = rs.getString("BOARD_TITLE"); 
				String memberName = rs.getString("MEMBER_NM"); 
				String createDate = rs.getString("CREATE_DT"); 
				int readCount = rs.getInt("READ_COUNT"); 
				int commentCount = rs.getInt("COMMENT_COUNT"); 
				
				Board board = new Board(); 
				
				board.setBoardNo(boardNo);
				board.setBoardTitle(boardTitle);
				board.setMemberName(memberName);
				board.setCreateDate(createDate);
				board.setReadCount(readCount);
				board.setCommentCount(commentCount);
				
				boardList.add(board); 
			}
		
			
		} finally {
			//JDBC 객체 자원 반환 
			close(rs); 
			close(stmt);
		}
		
		
		return boardList;
	}

	/** 게시글 상세 조회 SQL 수행 DAO
	 * @param conn
	 * @param input
	 * @return board 
	 */
	public Board selectBoard(Connection conn, int input) throws Exception {
		
		Board board = null; 
		
		try {
			
			String sql = prop.getProperty("selectBoard"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, input); 
			rs = pstmt.executeQuery(); 
					
			if(rs.next()) {
				
				board = new Board(); 
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				
			}
						
		} finally {
			
			close(rs); 
			close(pstmt); 
		}	
		
		return board;
	}

	/** 조회수 증가 sql 수행 dao 
	 * @param conn
	 * @param input
	 * @return result 
	 */
	public int updateReadCount(Connection conn, int input) throws Exception {
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("updateReadCount"); 
			
			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, input);
			
			result = pstmt.executeUpdate(); 
			
	
			
		} finally {
			close(pstmt); 
		}
		
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
