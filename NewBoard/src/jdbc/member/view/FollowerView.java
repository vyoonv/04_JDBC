package jdbc.member.view;

import java.util.List;
import java.util.Scanner;

import jdbc.main.common.Session;
import jdbc.member.dto.Follower;
import jdbc.member.service.FollowerService;

public class FollowerView {
	
	private Scanner sc = new Scanner(System.in);
	
	private FollowerService fservice = new FollowerService();
	

	public void mainMenu() {
		
		int input = 0;
		
		do {
		
			try {
				
				System.out.println("\n☆★☆☆★☆ MEMBERS ☆★☆☆★☆\n");
				System.out.println("1. FOLLOWERS");
				System.out.println("2. FOLLOWING");
				System.out.println("3. MESSAGES"); 
	
				System.out.println("9. BACK TO MAIN");
				System.out.println("0. EXIT");
				
				System.out.println("\nSELECT MENU NUMBER : \n");
				input = sc.nextInt(); 
				sc.nextLine(); 
				
				switch(input) {
				
				case 1 :  viewFollowers(); break; 
				case 2 :  //viewFollowing(); break;
				case 3 :  //messages(); break;  

				case 8 :  //if(unRegisterMenu()) return; break; 
				case 9 : System.out.println("\n==== BACK TO MAIN ====\n");
				         break; 
				         
				case 0 : System.out.println("\n======== EXIT =======\n");				      
						System.exit(0); 
						
				default : System.out.println("\n*** PLEASE PUT CORRECT NUMBER ***\n"); 
			
				
				} 
				
			}	
				catch (Exception e) {
				System.out.println("\n*** INPUT FORMAT IS INCORRECT ***\n");
				sc.nextLine(); 
				input = -1; 
			}
			
		} while(input != 9); 	
		
		
		
	}


	/**
	 * 팔로워 목록 
	 */
	private void viewFollowers() {
		
		System.out.println("☆★☆ FOLLOWERS ☆★☆");
		
		try {
			
			List<Follower> fList = fservice.viewFollowers(Session.loginMember.getMemNo()); 
			
			for(Follower f : fList) {
				System.out.printf("follower Id : %s / follower Name : %s", 
						f.getFollwerId(), f.getFollowerName()); 
				System.out.println();

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
