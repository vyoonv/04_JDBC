package jdbc.member.view;

import java.util.Scanner;

import jdbc.main.common.Session;
import jdbc.member.dto.Member;
import jdbc.member.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in); 
	
	private MemberService mService = new MemberService(); 
	
	private Member member = new Member(); 
		

	public void memberMenu() {
		
		int input = 0;
		
		do {
		
			try {
				
				System.out.println("\n☆★☆☆★☆ MY PAGE ☆★☆☆★☆\n");
				System.out.println("1. MY INFO");
				System.out.println("2. VIEW ALL MEMBER");
				System.out.println("3. UPDATE MY INFO"); 

				System.out.println("8. UNREGISTER");	
				System.out.println("9. BACK TO MAIN");
				System.out.println("0. EXIT");
				
				System.out.println("\nSELECT MENU NUMBER : \n");
				input = sc.nextInt(); 
				sc.nextLine(); 
				
				switch(input) {
				
				case 1 :  selectMyInfo(); break; 
				case 2 :  //followerView.mainMenu(); break;
				case 3 :  updateInfo(); break;  

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
		 * 내정보 수정 
		 */
		private void updateInfo() {
		
			System.out.println("\n☆★☆☆★☆UPDATE INFO(NAME, PW)☆★☆☆★☆\n");
			
			System.out.print("수정할 이름 : ");
			String memName = sc.next(); 
				
			System.out.print("현재 비밀번호 : ");
			String current = sc.next(); 
			
			String newPw1 = null; 
			
			while(true) {
				System.out.print("새 비밀번호 : ");
				newPw1 = sc.next(); 
				
				System.out.print("새 비밀번호 확인 : ");
				String newPw2 = sc.next(); 
				
				if(newPw1.equals(newPw2)) {
					break; 
				} else {
					System.out.println("\n*** 새 비밀번호가 일치하지 않습니다 ***\n");
				}
				
				
			}
			
			try {
		 
				int result = mService.updateInfo(memName, current, newPw1, Session.loginMember.getMemNo());
				
				if(result > 0) {
					System.out.println("\n=== 정보 수정 완료 ===\n");
				} else {
					System.out.println("\n*** 현재 비밀번호가 일치하지 않음 ***\n");
				}
				
				
				
			} catch(Exception e) {
				System.out.println("\n*** 정보 수정 중 예외 발생 ***\n");
				e.printStackTrace(); 
			}
			

			
			
				
		}
				
		


		/**
		 * 내정보 조회 
		 */
		public void selectMyInfo() {
			
			System.out.println("\n☆★☆☆★☆ MY INFO ☆★☆☆★☆\n");
			
			
			System.out.println("회원번호 : " + Session.loginMember.getMemNo());
			System.out.println("아이디 : " + Session.loginMember.getMemID());
			System.out.println("이름 : " + Session.loginMember.getMemName());
			
			if(Session.loginMember.getMemGender().equals("M")) {
				System.out.println("성별 : 남");
			} else {
				System.out.println("성별 : 여");
			}
			
			System.out.println("가입일 : " + Session.loginMember.getEnrollDate());
						
		}	
	

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}




	
