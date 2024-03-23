package jdbc.member.dto;

import java.util.Scanner;



public class Follower {


	private Scanner sc = new Scanner(System.in); 
	
	
	private String followerId; 
	private String follwingId; 
	private String sendMessage;
	private String getMessage;
	
	public Follower() {}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getFollwingId() {
		return follwingId;
	}

	public void setFollwingId(String follwingId) {
		this.follwingId = follwingId;
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
