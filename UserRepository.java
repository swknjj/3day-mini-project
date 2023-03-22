package project;

import java.util.*;

public class UserRepository {
	private static UserRepository userRepository = new UserRepository();
	Scanner sc = new Scanner(System.in);
	String adminI = "abcd";
	String adminP = "1234";

	private UserRepository() { // 생성자 제한
	}

	public static UserRepository getInstance() { // getinstance
		return userRepository;
	}

	UserService userService = UserService.getInstance();
	Map<String, UserDTO> uMap = new HashMap<>();
	List<UserDTO> uList = new ArrayList<>();

	public boolean save(UserDTO userDTO) { // 회원가입
		uMap.put(userDTO.getBno(), userDTO);
		return true;
	}

	public UserDTO findByUserDTO(String loginID, String loginPassword) {// UserDTO찾기
		for (String key : uMap.keySet()) {
			if (uMap.get(key).getId().equals(loginID) && uMap.get(key).getPw().equals(loginPassword)) {
				return uMap.get(key);
			}
		}
		return null;
	}

	public UserDTO findByUserId(String name) {// 아이디찾기
		for (String key : uMap.keySet()) {
			if (uMap.get(key).getName().equals(name)) {
				return uMap.get(key);
			}
		}
		return null;
	}

	public UserDTO findByUserPassword(String findId, String findName) {// userdto찾기
		for (String key : uMap.keySet()) {
			if (uMap.get(key).getId().equals(findId) && uMap.get(key).getName().equals(findName)) {
				return uMap.get(key);
			}
		}
		return null;
	}

	public boolean updatePassword(UserDTO userDTO, String updatePassword) {// 비번재설정
		userDTO.setPw(updatePassword);
		return true;
	}

	public boolean admin(String adminId, String adminPassword) {// 관리자계정확인
		if (adminI.equals(adminId) && adminP.equals(adminPassword)) {
			return true;
		}
		return false;
	}

	public Map<String, UserDTO> findAll() {// 전체리스트
		return uMap;
	}

	public boolean delete(String deleteUser) {// 유저삭제
		for (String key : uMap.keySet()) {
			if (deleteUser.equals(uMap.get(key).getBno())) {
				uMap.remove(key);
				return true;
			}
		}
		return false;
	}

	public boolean changeNickName(UserDTO userDTO, String next) {// 닉네임변경
		for (String key : uMap.keySet()) {
			if (next.equals(uMap.get(key).getNickName())) {
				return false;
			}
		}
		userDTO.setNickName(next);
		return true;
	}

	public String duCheck() {
		String result = sc.next();
		while (true) {
			boolean find = false;
			for (String key : uMap.keySet()) {
				if (result.equals(uMap.get(key).getNickName())) {
					return null;
				}
				if (!find) {
					break;
				}
			}
			return result;
		}
	}
}
