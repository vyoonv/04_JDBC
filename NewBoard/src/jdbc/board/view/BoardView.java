package jdbc.board.view;

import java.util.List;
import java.util.Scanner;

import jdbc.board.dto.Board;
import jdbc.board.service.BoardService;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in); 
	
	private BoardService bService = new BoardService(); 
	
	private CommentView cView = new CommentView(); 
	
	
	
	
	public void boardMenu() {
		
		int input = -1; 
		
		do {
			
			try {
				

				System.out.println("\n☆★☆☆★☆ BOARD ☆★☆☆★☆\n");
				System.out.println("1. VIEW ALL");
				System.out.println("2. SELECT POST NUMBER");
				System.out.println("3. NEW POST");
				
				System.out.println("9. BACK TO MAIN");
				System.out.println("0. EXIT");
				
				System.out.print("\nSELECT MENU NUMBER : ");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println(); 
				
				switch(input) {
				case 1: selectAllBoard();  break; 
				case 2: //selectBoard(); break; 
				case 3: //insertBoard(); break; 
				
				case 9:
					System.out.println("\n==== BACK TO MAIN ====\n");
					break;
				
				case 0:
					System.out.println("\n======== EXIT =======\n");
					System.exit(0);
				default: System.out.println("\n*** PLEASE PUT CORRECT NUMBER ***\n"); 
				}
				
				System.out.println();
				
				
			} catch (Exception e) {
				System.out.println("\n*** INPUT FORMAT IS INCORRECT ***\n");
				sc.nextLine(); 
				input = -1; 
			}
			
			
		} while (input != 9); 
		
		
		
		
		
		
	}




	private void selectAllBoard() {
		
		System.out.println("\n======== VIEW ALL ========\n");

		try {

			List<Board> boardList = bService.selectAllBoard(); 
			
			// 게시글이 없는 경우 
			if(boardList.isEmpty()) {
				System.out.println("\n*** 게시글이 존재하지 않습니다 ***\n");
				return;
			} 
			
			for(Board b : boardList) {
				System.out.printf("%d | %s[%d] | %s | %s | %d\n", 
						b.getBoardNo(), b.getBoardTitle(), b.getCommentCount(), b.getMemName(), b.getCreateDate(), b.getReadCount());
			}
			
			
		} catch(Exception e) {
			System.out.println("\n***게시글 목록 조회 중 예외 발생***\n");
			e.printStackTrace();
		}	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
