package project;

import java.util.*;

public class UserService {

	private static UserService userService = new UserService(); //생성자제한
	Scanner sc = new Scanner(System.in);
	private UserService() {} // 생성자제한
	public static UserService getInstance() {
		return userService;
	}
	UserRepository userRepository = UserRepository.getInstance();
	String loginId = null;
	String loginPw = null;
	
	
	public void save() { // 회원가입
		UserDTO userDTO = new UserDTO();
		System.out.print("회원가입할 ID");
		userDTO.setId(sc.next());
		System.out.print("회원가입할 Password");
		userDTO.setPw(sc.next());
		System.out.print("회원가입할 Name");
		userDTO.setName(sc.next());
		if(userRepository.save(userDTO)) {
			System.out.println("회원가입 성공");
			System.out.println(userDTO.getName()+"님 회원가입을 축하합니다");
		}else {
			System.out.println("회원가입오류");
		}
	}
	
	public UserDTO login() {
		System.out.print("Login 할 ID 입력");
		String loginID = sc.next();
		System.out.print("Login 할 Password 입력");
		String loginPassword = sc.next();
		UserDTO userDTO = new UserDTO();
		userDTO = userRepository.findByUserDTO(loginID, loginPassword);
		if(userDTO == null) {
			System.out.println("Access Denied");
			return null;
		}else {
			loginId = loginID;
			loginPw = loginPassword;
			System.out.println("login 성공");
			System.out.println(userDTO.getName()+"님 반갑습니다");
			return userDTO;
		}
		
	}
	public void findById() {
		System.out.print("찾으시는 ID에 해당하는 Name 입력");
		String findName = sc.next();
		UserDTO userDTO = new UserDTO();
		userDTO = userRepository.findByUserId(findName);
		if(userDTO == null) {
			System.out.println("Access Denied");
		}else {
			System.out.println("찾으시는 계정의 ID는 "+userDTO.getId()+"입니다");
		}
		
	}
	public void findByPassword() {
		System.out.println("찾으시는 Password에 해당하는 ID와 이름 입력");
		System.out.print("ID :");
		String findId = sc.next();
		System.out.print("Name :");
		String findName = sc.next();
		UserDTO userDTO = new UserDTO();
		userDTO = userRepository.findByUserPassword(findId,findName);
		if(userDTO == null) {
			System.out.println("Access Denied");
		}else {
			System.out.print("Password 재설정할 Password 입력");
			String updatePassword = sc.next();
			if(userRepository.updatePassword(userDTO,updatePassword)) {
				System.out.println(userDTO.getName()+"님의 Password 재설정 완료");
				return;
			}else {
				System.out.println("System error");
			}
			
		}
	}
	public boolean belogin() {
		loginId = "belogin";
		loginPw = "belogin";
		return true;
	}
	public boolean admin() {
		System.out.print("관리자 계정 ID");
		String adminId = sc.next();
		System.out.println("관리자 계정 Password");
		String adminPassword = sc.next();
		if(userRepository.admin(adminId,adminPassword)) {
			System.out.println("관리자 계정 로그인 완료");
			return true;
		}else {
			System.out.println("Access Denied");
			return false;
		}
	}
}
