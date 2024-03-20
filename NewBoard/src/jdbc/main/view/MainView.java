package jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.board.view.BoardView;
import jdbc.main.common.Session;
import jdbc.main.model.service.MainService;
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
					System.out.println("\n\n");
					
					
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다 ***\n");
				sc.nextLine(); 
				input = -1; 
			}
		} while(input != 0); 
		
		
		
		
	}
	
	
}
