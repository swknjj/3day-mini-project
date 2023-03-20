package project;

import java.util.Scanner;

public class BoardMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		UserService userService = UserService.getInstance();
		boolean loginOk = false;
		String loginid = null;
		String loginpw = null;
		boolean admin = false;

		while (true) {
			System.out.println("==============================게시판==============================");
			if (!loginOk) {
				System.out.println("1.회원가입 2.Login 3.ID 찾기 4.Password 찾기 5.비회원으로 로그인하기 6.관리자 계정으로 접속 0.프로그램 종료");
				System.out.println("logoff 상태");
			} else {
				System.out.println("1. ? 2. ? 3. ? 4. ? 5. ? 0. 로그아웃");
				if(admin) {
					System.out.println("관리자계정으로 접속중");
				}else if(loginid == "belogin") {
					System.out.println("비회원으로 접속중");
			}else {
				System.out.println("현재 Login ID :"+loginid+"\n현재 Password :"+loginpw);
			}
			}
			System.out.print("Menu 선택>");
			int menu = sc.nextInt();
			if (menu == 1) {
				userService.save();
			} else if (menu == 2) {
				UserDTO userDTO = userService.login();
				if(userDTO != null) {
					loginid	= userDTO.getId();
					loginpw = userDTO.getPw();
					loginOk = true;
				}
			} else if (menu == 3) {
				userService.findById();
			} else if (menu == 4) {
				userService.findByPassword();
			} else if (menu == 5) {
				if(userService.belogin()) {
					loginOk = true;
				}
			} else if (menu == 6) {
				if(userService.admin()) {
					loginOk = true;
					admin= true;
				}
			} else if (menu == 0) {
				if(!loginOk) {
				break;
				}else {
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

