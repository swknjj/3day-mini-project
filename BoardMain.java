package project;

import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		UserService userService = UserService.getInstance();
		BoardService boardService = BoardService.getInstance();
		boolean loginOk = false;
		String loginid = null;
		String loginpw = null;
		String loginNickName = null;
		boolean admin = false;
		UserDTO userDTO = null;
		boolean belogin = false;

		while (true) {
			System.out.println("==============================게시판=======================================");
			if (!loginOk) {
				System.out.println("1.회원가입 2.Login 3.ID 찾기 4.Password 찾기 \n5.비회원으로 로그인하기 6.관리자 계정으로 접속 0.프로그램 종료");
				System.out.println("logoff 상태");
			} else {
				if (admin) {
					System.out.println("관리자 계정으로 접속중");
					System.out.println("1.공지 작성 2.글 목록 3.글 상세보기 4.글 수정 5.유저리스트 6.유저삭제 7.글삭제 0.로그아웃");
				} else if (userDTO.getRole() == "belogin") {
					System.out.println("비회원으로 접속중");
					System.out.println("비회원유저는 수정이 불가능합니다");
					System.out.println("1.글 작성 2.글 목록 3.글 상세보기 0.로그아웃");
				} else {
					System.out.println("1.글 작성 2.글 목록 3.글 상세보기 4.Id,Password,Name 수정\n 5.NickName 수정 6.글 신고하기 7.게시글 검색 0.로그아웃");
					System.out.println("현재 Login ID :" + loginid + "\n현재 Password :" + loginpw + "\n현재 NickName :"
							+ loginNickName + "\n현재 Point = " + userDTO.getPoint());
				}

			}
			System.out.print("Menu 선택>");
			int menu = boardService.numberCheck();
			if (menu == 1) {
				if (loginOk || admin) { // 로그인시 or admin이면
					boardService.user(userDTO); // user return
					boardService.set(userDTO); // 글작성
				} else {
					userService.save(); // 회원가입
				}
			} else if (menu == 2) {
				if (loginOk) {
					boardService.findAll(); // 글목록
				} else {
					userDTO = userService.login(); // 로그인
					boardService.getDTO(userDTO);
					if (userDTO != null) {
						loginid = userDTO.getId();
						loginpw = userDTO.getPw();
						loginNickName = userDTO.getNickName();
						loginOk = true;
					}
				}
			} else if (menu == 3) {
				if (loginOk || admin) {
					boardService.open(); // 검색
				} else {
					userService.findId();// id찾기
				}
			} else if (menu == 4) {
				if(belogin) {
					System.out.println("다시 입력");
					continue;
				}
				if (admin) {
					boardService.adminUpdate();
				} else {
					userService.findByPassword(); // pw찾기
				}
			} else if (menu == 5) {
				if(belogin) {
					System.out.println("다시 입력");
					continue;
				}
				if (admin) {
					userService.findAll();
				} else if (loginOk) {
					userService.changeNickName(); // 닉네임수정
				} else {
					loginid = "belogin";
					loginpw = "belogin";
					loginNickName = "belogin";
					userDTO = userService.belogin(); // 비로그인으로 로그인
					System.out.println(userDTO.getRole());
					loginOk = true;
					belogin = true;
				}

			} else if (menu == 6) {
				if(belogin) {
					System.out.println("다시 입력");
					continue;
				}
				if(admin) {
					userService.delete();
					continue;
				}
				if(loginOk){
					boardService.declaration(userDTO);
					continue;
				}
				if (userService.admin()) {
					userDTO = userService.adminUser(); // admin 로그인
					loginOk = true;
					admin = true;
				}
				
			}else if(menu == 7) {
				if(admin) {
					boardService.delete();
				} else if(loginOk) {
					boardService.search();
				}
			}
			else if (menu == 0) {
				if (loginOk || admin) {
					loginOk = false;
					admin = false;
					belogin = false;
				} else {
					break;
				}
			} else {
				System.out.println("다시입력");
			}
		}
		System.out.println("프로그램 종료");
	}

}
