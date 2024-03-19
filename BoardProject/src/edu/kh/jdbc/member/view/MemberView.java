package edu.kh.jdbc.member.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

/**
 * 회원 전용 화면 
 */
public class MemberView {

	private Scanner sc = new Scanner(System.in); 
		
	private MemberService service = new MemberService(); 
	
	private Member member = new Member(); 
	
	
	/**
	 * 회원 기능 메뉴 view  
	 * @throws Exception 
	 */
	public void memberMenu() {
		
		int input = 0 ; 
		
		do {
			
			try {
					System.out.println("\n=== 회원 기능 ===\n");
					System.out.println("1. 내 정보 조회");
					System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
					System.out.println("3. 내 정보 수정 (이름, 성별)"); // 현재 로그인한 회원번호 
					System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
					System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE)");
		
					System.out.println("9. 메인 메뉴로 돌아가기");
					System.out.println("0. 프로그램 종료");
					
					System.out.println("\n메뉴 선택 : \n");
					input = sc.nextInt(); 
					sc.nextLine(); 
					
					switch(input) {
					
					case 1 :  selectMyInfo(); break; 
					case 2 :  selectMemberList(); break; 
					case 3 :  updateMember(); break; 
					case 4 :  updatePassword(); break; 
					case 5 :  if(unRegisterMenu()) return; break; 
					case 9 : System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n");
					         break; 
					case 0 : System.out.println("\n===== 프로그램 종료 ===== \n");
					         // JVM 강제 종료 구문 
							 // 매개변수는 기본 0, 다른 숫자는 오류를 의미 
							System.exit(0); 
					default : System.out.println("\n*** 메뉴 번호만 입력해주세요 ***\n"); 	
					
				}
			
			
			} catch(InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine(); 
				input = -1; 
				
			}
						
		} while(input != 9); //  
		
		
	} 




	/**
	 * 내 정보 조회 
	 */
	public void selectMyInfo() {
		
		System.out.println("\n====내 정보 조회====\n");
		
		// 회원 번호, 아이디, 이름, 성별(남/여), 가입일
		// 로그인 회원 정보 ( Session.loginMember 이용)
		System.out.println("회원번호 : " + Session.loginMember.getMemberNo());
		System.out.println("아이디 : " + Session.loginMember.getMemberID());
		System.out.println("이름 : " + Session.loginMember.getMemberName());
		
		if(Session.loginMember.getMemberGender().equals("M")) {
			System.out.println("성별 : 남");
		} else {
			System.out.println("성별 : 여");
		}
		
		System.out.println("가입일 : " + Session.loginMember.getEnrollDate());
		
	}
	
	
	
	/**
	 * 회원 목록 조회 
	 */
	private void selectMemberList()  {
		 
		System.out.println("\n==== 회원 목록 조회 (아이디, 이름, 성별) ====\n");
		
		try {
			// 회원 목록 조회 서비스 호출 후 결과 반환받기 
				List<Member> list = service.selectMemberList(); 
				
			  for(Member member : list) {
				  System.out.println(member.getMemberID());
				  System.out.println(member.getMemberName());
				  System.out.println(member.getMemberGender());
			  }
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
				
	}
	
	

	/**
	 * 내 정보 수정 
	 */
	public void updateMember( ) {
		
		System.out.println("\n=====내 정보 수정(이름, 성별)\n");
			
		System.out.print("수정할 이름 : ");
		String memberName = sc.next(); 
			
		String memberGender = null; 
		
		while(true) {
			System.out.print("성별 : ");
			memberGender = sc.next().toUpperCase(); 
			
			if(memberGender.equals("M") || memberGender.equals("F")) {
				break;
			} else {
				System.out.println("** M 또는 F 를 입력해주세요 **");
			}
			
		}
			
		
		try {
			
			int result = service.updateMember(memberName, memberGender, Session.loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("수정되었습니다");
				
				// Service를 호출해서 DB만 수정 -> DB와 Java 프로그램 데이터 동기화 
				Session.loginMember.setMemberName(memberName);
				Session.loginMember.setMemberGender(memberGender);
								
			} else {
				System.out.println("수정 실패");
			}						
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	

	/**
	 * 비밀번호 변경 ( 현재 비번, 새 비번, 새비번 확인 ) 
	 */
	public void updatePassword() {
		
		System.out.println("\n==== 비밀 번호 변경 ====\n");
		
	
		// 현재 비밀번호 입력 
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
			
			// 서비스 호출 (현재 비밀번호, 새 비밀번호, 로그인한 회원번호)
			// int형 
			int result = service.updatePassword(current, newPw1, Session.loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n=== 비밀번호 변경 완료 ===\n");
			} else {
				System.out.println("\n*** 현재 비밀번호가 일치하지 않음 ***\n");
			}
			
			
			
		} catch(Exception e) {
			System.out.println("\n*** 비밀번호 변경 중 예외 발생 ***\n");
			e.printStackTrace(); 
		}
	
		
		
	/*	
	 * 
	 * 
	 * String memberPw = null;
		String updatePw = null; 
		String checkUpdatePw = null; 
	 * 
	 * 
	 * 
	 * 
	 * while(true) {
			
			System.out.print("현재 비밀번호 입력 : ");
			memberPw = sc.next(); 
				
			if(memberPw.equals(Session.loginMember.getMemberPw())) {
				break; 
			} else {
				System.out.println("비밀번호가 일치하지 않습니다");
			}
		
		} 
		
		
		while(true) {
		
			System.out.print("새 비밀번호 입력 : ");
			updatePw = sc.next(); 
			
			System.out.print("새 비밀번호 확인 : ");
			checkUpdatePw = sc.next(); 
		
			if(updatePw.equals(checkUpdatePw)) {
				break; 
				
			} else {
				System.out.println("비밀번호가 일치하지 않습니다");
			}
			
		}
		
		
		try {
			
			int result = service.updatePassword(memberPw, updatePw, Session.loginMember.getMemberNo()); 
			
			if(result > 0) {
				System.out.println("수정되었습니다");
				Session.loginMember.setMemberPw(updatePw); 
			} else {
				System.out.println("수정 실패");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		*/
		
	}

	
	/** 회원 탈퇴 (보안코드, 비밀번호, UPDATE)
	 * @return true/false  
	 */
	public boolean unRegisterMenu() {
		
		System.out.println("\n====== 회원 탈퇴 ======\n");
		
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next(); 
		
		String code = service.createSecurityCode(); 
		System.out.printf("보안 문자 입력 [%s] : ", code); // 보안문자 입력 [xxxxxx] 
		String input = sc.next(); // 보안문자 입력 받을 
		
		// 보안 문자 일치여부 확인
		if(!input.equals(code)) { //일치하지 않는 경우 
			
			System.out.println("\n*** 보안 문자가 일치하지 않습니다 ***\n");
			return false; 
			
		}
		
		while(true) {
		
			System.out.print("정말 탈퇴하시겠습니까? (Y/N) : ");
			char check = sc.next().toUpperCase().charAt(0); 
			
			if(check == 'N') {
				System.out.println("탈퇴 취소!");
				return false; // 메서드 종료 
			} 

			if(check == 'Y') {
				break; // 반복문 종료 
					
			}
		
			// 입력 값이 y/n 아닌 경우
			System.out.println("\n*** 잘못 입력하셨습니다 ***\n");
		}
		
		try {
			// 회원 탈퇴 서비스 호출 
			int result = service.unRegisterMember(memberPw, Session.loginMember.getMemberNo());
			
			if(result > 0) {
				System.out.println("\n=== 탈퇴 성공! ===\n");
				
				// 로그아웃 
				Session.loginMember = null; 
				
				return true; 
			} else {
				System.out.println("*** 현재 비밀번호 일치하지 않음!!! ***");
			}
			
		} catch(Exception e) {
			System.out.println("\n*** 회원 탈퇴 중 예외 발생 ***\n");
			e.printStackTrace();
		}
		
		return false; // 예외 발생해서 빠져나오게 되면 false 반환해야 register 메뉴로 돌아감!
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
