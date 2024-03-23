package jdbc.member.dto;

public class Member {

	private int memNo; 
	private String memID; 
	private String memPw; 
	private String memName; 
	private String memGender; 
	private String enrollDate; 
	private String unregisterFlag; 
	private String followerId; 
	private String followingId;
	private String sendMessage;
	private String getMessage;
	
	public Member() {}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
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

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public String getGetMessage() {
		return getMessage;
	}

	public void setGetMessage(String getMessage) {
		this.getMessage = getMessage;
	}


	
	
	
	
}
