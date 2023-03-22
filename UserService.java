package project;

import java.util.*;

public class UserService {

	private static UserService userService = new UserService();
	Scanner sc = new Scanner(System.in);

	private UserService() {
	} // 생성자제한

	public static UserService getInstance() {
		return userService;
	}

	UserRepository userRepository = UserRepository.getInstance();
	BoardService boardService = BoardService.getInstance();
	String loginId = null;
	String loginPw = null;
	String loginNickName = null;
	UserDTO userDTO = new UserDTO();

	public void save() { // 회원가입
		UserDTO userDTO = new UserDTO();
		while (true) {
			System.out.print("회원가입할 ID");
			String id = userRepository.duCheck();
			if (id == null) {
				System.out.println("중복된 ID가 있습니다");
			} else {
				userDTO.setId(id);
				break;
			}
		}
		System.out.print("회원가입할 Password");
		userDTO.setPw(sc.next());
		System.out.print("회원가입할 Name");
		userDTO.setName(sc.next());
		while (true) {
			System.out.print("회원가입할 NickName 입력");
			String du = userRepository.duCheck();
			if (du == null) {
				System.out.println("중복된 NickName이 있습니다");
			} else {
				userDTO.setNickName(du);
				break;
			}
		}
		if (userRepository.save(userDTO)) {
			System.out.println("회원가입 성공");
			System.out.println(userDTO.getName() + "님 회원가입을 축하합니다");
		} else {
			System.out.println("회원가입오류");
		}
	}

	public UserDTO login() {
		System.out.print("Login 할 ID 입력");
		String loginID = sc.next();
		System.out.print("Login 할 Password 입력");
		String loginPassword = sc.next();
		userDTO = userRepository.findByUserDTO(loginID, loginPassword);
		if (userDTO == null) {
			System.out.println("Access Denied");
			return null;
		} else {
			loginId = loginID;
			loginPw = loginPassword;
			loginNickName = userDTO.getNickName();
			System.out.println("login 성공");
			return userDTO;
		}

	}

	public void findId() {
		System.out.print("찾으시는 ID에 해당하는 Name 입력");
		String findName = sc.next();
		userDTO = userRepository.findByUserId(findName);
		if (userDTO == null) {
			System.out.println("Access Denied");
		} else {
			System.out.println("찾으시는 계정의 ID는 " + userDTO.getId() + "입니다");
		}

	}

	public void findByPassword() {
		System.out.println("찾으시는 Password에 해당하는 ID와 이름 입력");
		System.out.print("ID :");
		String findId = sc.next();
		System.out.print("Name :");
		String findName = sc.next();
		userDTO = userRepository.findByUserPassword(findId, findName);
		if (userDTO == null) {
			System.out.println("Access Denied");
		} else {
			System.out.print("Password 재설정할 Password 입력");
			String updatePassword = sc.next();
			if (userRepository.updatePassword(userDTO, updatePassword)) {
				System.out.println(userDTO.getName() + "님의 Password 재설정 완료");
				return;
			} else {
				System.out.println("System error");
			}

		}
	}

	public UserDTO belogin() {
		userDTO.setRole("belogin");
		userDTO.setId("belogin");
		return userDTO;
	}

	public UserDTO adminUser() {
		userDTO.setRole("admin");
		userDTO.setId("admin");
		userDTO.setName("admin");
		userDTO.setNickName("admin");
		userDTO.setPw("admin");
		return userDTO;
	}

	public boolean admin() {
		System.out.print("관리자 계정 ID");
		String adminId = sc.next();
		System.out.print("관리자 계정 Password");
		String adminPassword = sc.next();
		if (userRepository.admin(adminId, adminPassword)) {
			System.out.println("관리자 계정 로그인 완료");
			return true;
		} else {
			System.out.println("Access Denied");
			return false;
		}
	}

	public void changeNickName() {
		System.out.println("NickName 수정에는 50포인트가 필요합니다");
		System.out.println("수정하시겠습니까?");
		System.out.print("1.예 2.아니오");
		int menu = boardService.numberCheck();
		if (menu == 1) {
			if (userDTO.getPoint() >= 50) {
				userDTO.setPoint(userDTO.getPoint() - 50);
				System.out.print("NickName 입력");
				String next = sc.next();
				if (userRepository.changeNickName(userDTO, next)) {
					System.out.println("수정완료");
				} else {
					System.out.println("닉네임 중복");
				}
				return;
			} else {
				System.out.println("Point가 부족합니다");
				return;
			}
		} else if (menu == 2) {
			System.out.println("수정 종료");
			return;
		} else {
			System.out.println("다시입력");
		}
	}

	public void findAll() {
		System.out.println("유저 목록");
		Map<String, UserDTO> uMap = userRepository.findAll();
		List<String> keySet = new ArrayList<>(uMap.keySet());
		Collections.sort(keySet);
		if (uMap.size() == 0) {
			System.out.println("조회할 수 없습니다");
		} else {
			System.out.println("유저번호\tId\tPassword\tName\tNickName\t가입날짜");
			for (String key : keySet) {
				System.out.println(uMap.get(key).toString());
			}
		}
	}

	public void delete() {
		System.out.println("삭제할 유저번호 입력");
		String deleteUser = sc.next();
		if (userRepository.delete(deleteUser)) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
	}

}
