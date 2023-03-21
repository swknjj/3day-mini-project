package project;

import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		UserService userService = UserService.getInstance();
		BoardService boardService = BoardService.getInstance();
		boolean loginOk = false;
		String loginid = null;
		String loginpw = null;
		String loginNickName = null;
		boolean admin = false;
		UserDTO userDTO = new UserDTO();

		while (true) {
			System.out.println("==============================게시판=======================================");
			if (!loginOk) {
				System.out.println("1.회원가입 2.Login 3.ID 찾기 4.Password 찾기 \n5.비회원으로 로그인하기 6.관리자 계정으로 접속 0.프로그램 종료");
				System.out.println("logoff 상태");
			} else {
				if (admin) {
					System.out.println("관리자 계정으로 접속중");
					System.out.println("1.공지 작성 2.글 목록 ? 3.글 상세보기 ? 4. ? 5. ? 0.로그아웃");
				} else if (loginid == "belogin") {
					System.out.println("비회원으로 접속중");
					System.out.println("1.글 작성 2.글 목록 3.글 상세보기 0.로그아웃");
				} else {
					System.out.println("1.글 작성 2.글 목록 ? 3.글 상세보기 4.정보수정 ? 5. ? 0.로그아웃");
					System.out.println("현재 Login ID :" + loginid + "\n현재 Password :" + loginpw + "\n현재 NickName :"
							+ loginNickName + "\n현재 Point = " + userDTO.getPoint());
				}

			}
			System.out.print("Menu 선택>");
			int menu = sc.nextInt();
			if (menu == 1) {
				if (loginOk || admin) {
					boardService.user(userDTO);
					boardService.set(userDTO);
				} else {
					userService.save();
				}
			} else if (menu == 2) {
				if(loginOk) {
					boardService.findAll();
				}else {
				userDTO = userService.login();
				if (userDTO != null) {
					loginid = userDTO.getId();
					loginpw = userDTO.getPw();
					loginNickName = userDTO.getNickName();
					loginOk = true;
				}
				}
			} else if (menu == 3) {
				if(loginOk || admin) {
					boardService.open(); 
				}else {
					userService.findMyId();
				}
			} else if (menu == 4) {
				if(loginOk || admin) {
					userService.findMyId();
				}else {
				userService.findByPassword();
				}
			} else if (menu == 5) {
				loginid = "belogin";
				loginpw = "belogin";
				loginNickName = "belogin";
				userDTO = userService.belogin();
				loginOk = true;

			} else if (menu == 6) {
				if (userService.admin()) {
					userDTO = userService.adminUser();
					loginOk = true;
					admin = true;
				}
			} else if (menu == 0) {
				if (!loginOk) {
					break;
				} else {
					loginOk = false;
					admin = false;
				}
			} else {
				System.out.println("다시입력");
			}
		}
		System.out.println("프로그램 종료");
	}

}
