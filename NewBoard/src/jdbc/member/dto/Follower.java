package jdbc.member.dto;

import java.util.Scanner;



public class Follower {


	private Scanner sc = new Scanner(System.in); 
	
	private int memNo; 
	private String follwerId; 
	private String followerName;
	private String sendMessage;
	private String getMessage;
	
	public Follower() {}

	
	public String getFollwerId() {
		return follwerId;
	}


	public void setFollwerId(String follwerId) {
		this.follwerId = follwerId;
	}


	public String getFollowerName() {
		return followerName;
	}


	public void setFollowerName(String followerName) {
		this.followerName = followerName;
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

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	
	
	
	
	
}
