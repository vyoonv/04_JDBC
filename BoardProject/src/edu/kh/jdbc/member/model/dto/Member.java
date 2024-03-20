package edu.kh.jdbc.member.model.dto;

public class Member {

	private int memberNo; 
	private String memberID; 
	private String memberPw; 
	private String memberName; 
	private String memberGender; 
	private String enrollDate; 
	private String unregisterFlag; 
	
	public Member() {}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUnregisterFlag() {
		return unregisterFlag;
	}

	public void setUnregisterFlag(String unregisterFlag) {
		this.unregisterFlag = unregisterFlag;
	}
	
	
	
	
	
}
