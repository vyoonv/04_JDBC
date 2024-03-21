package jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.board.view.BoardView;
import jdbc.main.common.Session;
import jdbc.main.model.service.MainService;
import jdbc.member.dto.Member;
import jdbc.member.view.MemberView;

public class MainView {

	private Scanner sc = new Scanner(System.in); 
	
	private MainService service = new MainService();
	
	private MemberView memberView = new MemberView(); 
	
	private BoardView boardView = new BoardView(); 
	
	
	public void mainMenu() {
		
		int input = 0; 
		
		do {
			try {
				
				if(Session.loginMember == null) {
					System.out.println("\n☆★☆☆★☆☆★☆☆★☆ Welcome to Queens ☆★☆☆★☆☆★☆☆★☆\n");
					System.out.println("1.☆★☆ SIGN IN ☆★☆");
					System.out.println("2.☆★☆★☆ SIGN UP ☆★☆★☆");
					System.out.println("0. EXIT");
					
					System.out.println("\nSELECT MENU NUMBER : ");
					input = sc.nextInt();
					sc.nextLine(); 
					
					switch(input) {
					
					case 1 : login(); break; 
					case 2 : signUp(); break;
					case 0 : System.out.println("\n~~~\\BYE/~~~\n"); break; 	
					default : System.out.println("\nPLEASE PUT CORRECT NUMBER\n");
					}
										
					
				} else { //로그인 o
					
					System.out.println("\n☆★☆☆★☆ MY PAGE ☆★☆☆★☆\n");
					System.out.println("1. MY PAGE");
					System.out.println("2. BOARD");
					System.out.println("3. SIGN OUT");
					System.out.println("0. EXIT");
					
					System.out.println("\nSELECT MENU NUMBER : ");
					input = sc.nextInt();
					sc.nextLine(); //입력 버퍼 개행 문자 제거
					
					switch(input) {
					
					case 1 : //memberView.memberMenu(); break;  //회원기능 view  
					case 2 : //boardView.boardMenu(); break; 
					case 3 : System.out.println("\nSIGNED OUT\n");
					         Session.loginMember = null; //참조하고 있던 로그인 회원 객체 없앰
					         break; 
					case 0 : System.out.println("\nSEE U AGAIN\n"); break;         
					default : System.out.println("\nPLEASE PUT CORRECT NUMBER\n");
					
					}
										
				}
					
					
			}
				
				
			catch (InputMismatchException e) {
				System.out.println("\n*** INPUT FORMAT IS INCORRECT ***\n");
				sc.nextLine(); 
				input = -1; 
			}
		} while(input != 0); 
		
		
		
		
	}


	public void signUp() {
		
		
		System.out.println("\n[회원가입]\n");
		
		// 아이디, 비밀번호, 비밀번호 확인, 이름, 성별(M/F) 저장할 변수 선언 
		String memberId = null; 
		String memberPw = null; 
		String pwConfirm = null; // 비밀번호 확인용 변수 
		String memberName = null; 
		String memberGender = null; 
		
		try {
			
			// 아이디 입력 -> 중복 검사 
			// -DB에 탈퇴하지 않은 회원 중 
			//  입력한 아이디와 같은 아이디가 존재하면 중복으로 판정 
			// -> 중복이 입력되지 않을 때까지 무한 반복
			
			while(true) {
				
				System.out.print("아이디 입력 : ");
				memberId = sc.next(); 
				
				//아이디 중복 확인 서비스 호출 
				// -> 중복인 경우 1, 아닌 경우 0반환 
				int result = service.idDuplicationCheck(memberId); 
			
				// 중복 검사 결과에 따라 반복 제어 
				if(result == 0) {// 중복 아닌 경우 
					
					System.out.println("\n=== 사용 가능한 아이디입니다 ===\n");
					break; 
				} else {
					System.out.println("\n*** 이미 사용중인 아이디입니다 ***\n");
				}
			}
			
			// 비밀번호, 비밀번호 확인 입력 받아 같을 때까지 무한 반복 
			
			while(true) {
				
				System.out.print("비밀번호 입력 : ");
				memberPw = sc.next(); 
				
				System.out.print("비밀번호 확인 : ");
				pwConfirm = sc.next(); 
				
				if(memberPw.equals(pwConfirm)) {
					System.out.println("\n☆★☆ 비밀번호 일치 ☆★☆\n");
					break; 
				} else {
					System.out.println("\n*** 비밀번호가 일치하지 않습니다 ***\n");
				}
						
			}
			
			//이름 입력 
			System.out.print("이름 : ");
			memberName = sc.next(); 
			
			//성별 입력 m 또는 f가 입력될때까지 무한 반복 
			while(true) {
				System.out.print("성별(F/M) : ");
				memberGender = sc.next().toUpperCase(); 
				
				// 정상 입력 
				if(memberGender.equals("M") || memberGender.equals("F")) {
					break; 
				} else {
					System.out.println("\n*** M 또는 F만 입력해주세요 ***\n");
				}
	
			}
			
			// Member 객체 생성하여 입력 받은 값 세팅
			Member member = new Member(); 
			
			member.setMemID(memberId); 
			member.setMemPw(memberPw); 
			member.setMemName(memberName); 
			member.setMemGender(memberGender); 
			
			// 회원가입 서비스 호출, 멤버 객체 전달 
			int result = service.signUp(member); 
			
			if(result > 0) {
				System.out.println("\n☆★☆☆★☆ 회원 가입 성공 ☆★☆☆★☆\n");
				
			} else {
				System.out.println("\n*** 회원 가입 실패 ***\n");
			}
			
		} catch(Exception e) {
			System.out.println("\n*** 회원 가입 중 예외 발생 ***\n");
			
			e.printStackTrace();
		}
		
	}


	/**
	 * 로그인 view 
	 */
	public void login() {
		
		System.out.println("\n[로그인]\n");
		
		System.out.print("아이디 입력 : ");
		String memberId = sc.next(); 
		
		System.out.print("비밀번호 입력 : ");
		String memberPw = sc.next(); 
		
		try {
			
			
			Session.loginMember = service.login(memberId, memberPw); 
			
			if(Session.loginMember == null) { //로그인 실패 
				System.out.println("\n*** INCORRECT ID/PASSWORD ***\n");
			} else {
				System.out.printf("\n☆★☆★☆ %s님 환영합니다 ☆★☆★☆\n", 
									Session.loginMember.getMemName());
			}
			
			
		} catch(Exception e ) {
			System.out.println("\n**** 로그인 중 예외 발생 ****\n");
			e.printStackTrace();
		}
		
	}
	
	
}
