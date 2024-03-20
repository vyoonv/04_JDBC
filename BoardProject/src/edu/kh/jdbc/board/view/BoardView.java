package edu.kh.jdbc.board.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Comment;
import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.common.Session;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in); 
	
	private BoardService boardService = new BoardService(); 
	
	// 댓글 화면 출력 객체 
	private CommentView commentView = new CommentView(); 
	

	public void boardMenu() {
		
		int input = -1;
		
		do {
			try {
				System.out.println("\n===== 게시판 기능 =====\n");
				System.out.println("1. 게시글 목록 조회");
				System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
				System.out.println("3. 게시글 작성");
				// 제목, 내용(StringBuffer 이용) 입력
				// -> 게시글 삽입 서비스(제목, 내용, 로그인 회원 번호) 호출
				
				System.out.println("9. 메인 메뉴로 돌아가기");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				System.out.println(); 
				
				switch(input) {
				case 1: selectAllBoard();  break; // 게시글 목록 조회
				case 2: selectBoard(); break; // 게시글 상세 조회
				case 3: insertBoard(); break; // 게시글 등록(삽입)
				
				case 9:
					System.out.println("\n===== 메인 메뉴로 돌아갑니다 =====\n");
					break;
				
				case 0:
					System.out.println("\n=== 프로그램 종료 ===\n");
					System.exit(0);
				default: System.out.println("\n*** 메뉴 번호만 입력 해주세요 ***\n"); 
				}
				
				System.out.println();
				
			}catch (InputMismatchException e) {
				System.out.println("\n*** 입력 형식이 올바르지 않습니다***\n");
				sc.nextLine(); // 입력버퍼에 잘못된 문자열 제거
				input = -1; // while문 종료 방지
			}
			
		}while(input != 9);
		

		
	}






	/**
	 * 게시글 목록 조회 
	 */
	public void selectAllBoard() {
 	
		System.out.println("\n===== 게시글 목록 조회 =====\n");
		
		// 3 | 샘플 제목 [2] | 유저일 | 2024-03-19 | 0 
		// ...
		
		try {
			
			// 게시글 목록 조회 서비스 호출 
			List<Board> boardList = boardService.selectAllBoard(); 
			
			// 게시글이 없는 경우 
			if(boardList.isEmpty()) {
				System.out.println("\n*** 게시글이 존재하지 않습니다 ***\n");
				return;
			} 
			
			for(Board b : boardList) {
				System.out.printf("%d | %s[%d] | %s | %s | %d\n", 
						b.getBoardNo(), b.getBoardTitle(), b.getCommentCount(), b.getMemberName(), b.getCreateDate(), b.getReadCount());
			}
			
			
		} catch(Exception e) {
			System.out.println("\n***게시글 목록 조회 중 예외 발생***\n");
			e.printStackTrace();
		}	
		
	}

	
	/**
	 * 게시글 상세 조회 + 댓글 목록 조회 
	 */
	public void selectBoard() {
		
		System.out.println("\n===== 게시글 상세 조회 =====\n");
		
		// 게시글 번호 입력 
		// 1) 번호가 일치하는 게시글이 있으면 조회 
		// -> 조회수 증가 (단, 본인 작성글일 경우 조회수 증가 x) 
		// -> 자신이 작성한 게시글일 경우 ///수정, 삭제/// 기능 노출 
		// 댓글 목록, 댓글 기능 추가 예정 
		// 2) 번호가 일치하는 게시글이 없으면 해당 게시글 존재하지 않음 출력 
		
		System.out.print("게시글 번호 입력 : ");
		int input = sc.nextInt();
		sc.nextLine(); 
		
		// 게시글 상세 조회 서비스 호출 
	
		try {
	
			Board board = boardService.selectBoard(input, Session.loginMember.getMemberNo()); 
										// 입력 받은 게시글 번호, 로그인한 회원번호(조회수 증가에 사용)
			
			if(board == null) {
				System.out.println("\n*** 해당 게시글이 존재하지 않습니다 ***\n");
				return; 
			} 
			
			System.out.println("--------------------------------------------------------");
			System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
			System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
					board.getMemberName(), board.getCreateDate(), board.getReadCount());
			System.out.println("--------------------------------------------------------\n");
			System.out.println(board.getBoardContent());
			System.out.println("\n--------------------------------------------------------");
			
			//**********************************************************************************
			
			/* 해당 게시글의 댓글 목록 조회 */
			if(!board.getCommentList().isEmpty()) {
				
				for( Comment c : board.getCommentList()) {
					System.out.printf("[댓글 번호 : %d] 작성자 : %s  작성일 : %s\n%s\n", 
							c.getCommentNo(), c.getMemberName(), c.getCreateDate(), c.getCommentContent());
					System.out.println("-------------------------------------------------------------");
				}
			}
			
			/* 댓글 메뉴 출력 *****************************************************/ 
			// 1) 댓글 등록 - 누가 몇 번 게시글에 작성하는가 
			// 2) 댓글 수정 - 누가 몇 번 게시글에 있는 몇 번 댓글을 수정할 것인가 
			// 3) 댓글 삭제 - 누가 몇 번 게시글에 있는 몇 번 댓글을 삭제할 것인가 
			
			commentView.commentMenu(input); // input : 게시글 번호
			//**********************************************************************
			
			
			// -> 자신이 작성한 게시글일 경우 ///수정, 삭제/// 기능 노출 
			if(Session.loginMember.getMemberNo() == board.getMemberNo()) {
				
				while(true) {
					System.out.println("1) 수정");
					System.out.println("2) 삭제");
					System.out.println("0) 게시판 메뉴로 돌아가기");
					
					System.out.print("선택 >>> ");
					input = sc.nextInt(); 
					sc.nextLine(); 
					
					
					// 수정/삭제 기능 수행 후 게시판 메뉴로 돌아가기 
					switch(input) {
					
					case 1 : updateBoard(board.getBoardNo()); return;  // 게시글 번호를 매개변수로 전달 - 해당 번호의 게시글을 수정 
					case 2 : deleteBoard(board.getBoardNo()); return;  // 게시글 번호 매개변수 - 해당번호 게시글 삭제
					case 0 : return;  
					default : System.out.println("\n*** 잘못 입력하셨습니다 ***\n");
					
					}
					
					
				}
				
			}
			
			
			
			
			
			
			
			
		} catch(Exception e) {
			System.out.println("\n**** 게시글 상세 조회 중 예외 발생 ****\n");
			e.printStackTrace();
		}
		
		
	}









	/** 게시글 수정 
	 * @param boardNo
	 */
	private void updateBoard(int boardNo) {
		
		System.out.println("\n==== 게시글 수정 ====\n");
		
		//게시글 제목, 내용 수정 
		System.out.print("수정할 제목 : ");
		String boardTitle = sc.nextLine(); 
		
		StringBuffer sb = new StringBuffer(); 
		
		System.out.println("<!wq 입력 시 종료>");
		
		while (true) {
			String str = sc.nextLine(); 
			
			if(str.equals("!wq")) break; 
			
			sb.append(str); 
			sb.append("\n"); 
			
		}
		
		try {
			
			// 게시글 수정 서비스 호출 
			int result = boardService.updateBoard(boardTitle, sb.toString(), boardNo);
			
			if(result > 0) {
				System.out.println("\n==== 게시글이 수정되었습니다 ====\n");
			
			} else {
				System.out.println("\n*** 수정 실패 ***\n");
			}
			
			
		} catch (Exception e) {
			System.out.println("\n*** 게시글 수정 중 예외 발생 ***\n");
			e.printStackTrace();
		}
		
	}

	
	

	/**
	 * 게시글 등록(insert)
	 */
	private void insertBoard() {
		
		
		System.out.println("\n==== 게시글 등록 ====\n");
		
		// 제목 입력 
		System.out.print("제목 입력 : ");
		String boardTitle =sc.nextLine(); 
		
		StringBuffer sb = new StringBuffer(); 
		
		// 내용 입력(StringBuffer) 
		System.out.println("<!wq 입력 시 종료>");
		
		while (true) {
			String str = sc.nextLine(); 
			
			if(str.equals("!wq")) break; 
			
			sb.append(str); 
			sb.append("\n"); 
			
		}
		
		try {
			
			// 게시글 삽입 서비스 호출 
			int result = boardService.insertBoard(boardTitle, sb.toString(), 
										Session.loginMember.getMemberNo()); 
			
			// + 이미지 삽입 (게시글 번호, 이미지 경로 ...)
			
			if(result > 0) { // 성공
				
				System.out.println("\n=== 등록되었습니다 ===\n");
				
				// 등록된 게시글 상세 조회 서비스 호출 
				// -> 게시글 번호, 로그인 회원 번호 
				Board board = boardService.selectBoard(result, Session.loginMember.getMemberNo());
													// 등록된 게시글 번호, 회원 번호 

				System.out.println("--------------------------------------------------------");
				System.out.printf("글번호 : %d \n제목 : %s\n", board.getBoardNo(), board.getBoardTitle());
				System.out.printf("작성자 : %s | 작성일 : %s  \n조회수 : %d\n",
						board.getMemberName(), board.getCreateDate(), board.getReadCount());
				System.out.println("--------------------------------------------------------\n");
				System.out.println(board.getBoardContent());
				System.out.println("\n--------------------------------------------------------");
				
				
			} else { // 실패 
				
				System.out.println("\n*** 게시글 등록 실패 ***\n");
				
			}
			
			
		} catch (Exception e) {
			System.out.println("\n*** 게시글 등록 중 예외 발생 ***\n");
		}
		
	}
	
	

	/** 게시글 삭제 
	 * @param boardNo
	 */
	private void deleteBoard(int boardNo) {  // 게시글 번호 매개변수 - 해당번호 게시글 삭제
		
		try {
			System.out.println("\n[게시글 삭제]\n");
			
			System.out.print("삭제할 게시글 번호 입력 : ");
			int delBoardNo = sc.nextInt();
			sc.nextLine(); 
			
			int check = boardService.checkBoardNo(boardNo, Session.loginMember.getMemberNo()); 
			
			if(check == 0) {
				System.out.println("\n작성한 댓글만 삭제할 수 있습니다\n");
				return; 
			}
			
			while(true) {
				System.out.println("\n정말로 삭제하시겠습니까? (Y/N) : \n");
				char input = sc.next().toUpperCase().charAt(0); 
				
				if(input == 'N') {
					System.out.println("[삭제 취소]");
					return; 
				}
				
				if(input != 'Y') {
					System.out.println("[잘못 입력하셨습니다]");
					continue; 
					
				}
				
				break; 
			}
			
			int result = boardService.deleteBoard(boardNo); 
			
			if(result > 0) {
				System.out.println("\n===== 게시글이 삭제되었습니다 =====\n");
				
			} else {
				System.out.println("\n*** 게시글 삭제 실패 ***\n");
			}
				
			
		} catch (Exception e) {
			System.out.println("\n*** 게시글 삭제 중 예외 발생 ***\n");
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
