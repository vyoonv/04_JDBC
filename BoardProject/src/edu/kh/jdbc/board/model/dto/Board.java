package edu.kh.jdbc.board.model.dto;

import java.util.List;

// Data Transfer Object : 데이터 전달용 객체 (DB에서 가져온 데이터 저장하는 용도)


public class Board {  // db에 있는 컬럼이 모두 필드에 있어야 할 필요가 없음 
	

	private int boardNo; // 게시글 번호 
	private String boardTitle; // 게시글 제목 
	private String boardContent; //게시글 내용
	private String createDate; // 작성일
	private int readCount; //조회수 
	private int memberNo; //게시글 작성자(회원번호)
	private String memberName; // 게시글 작성자(이름)
	private int commentCount; // 댓글 수 
	private List<Comment> commentList; // 댓글 목록 
	
	public Board() {}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
