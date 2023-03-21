package project;

import java.util.*;

public class UserRepository {
	private static UserRepository userRepository = new UserRepository();
	String adminI = "abcd";
	String adminP = "1234";

	private UserRepository() {
	}

	public static UserRepository getInstance() {
		return userRepository;
	}

	UserService userService = UserService.getInstance();
	Map<String, UserDTO> uMap = new HashMap<>();
	List<UserDTO> uList = new ArrayList<>();

	public boolean save(UserDTO userDTO) {
		uMap.put(userDTO.getBno(), userDTO);
		return true;
	}

	public UserDTO findByUserDTO(String loginID, String loginPassword) {
		for (String key : uMap.keySet()) {
			if (uMap.get(key).getId().equals(loginID) && uMap.get(key).getPw().equals(loginPassword)) {
				return uMap.get(key);
			}
		}
		return null;
	}

	public UserDTO findByUserId(String name) {
		for (String key : uMap.keySet()) {
			if (uMap.get(key).getName().equals(name)) {
				return uMap.get(key);
			}
		}
		return null;
	}

	public UserDTO findByUserPassword(String findId, String findName) {
		for (String key : uMap.keySet()) {
			if (uMap.get(key).getId().equals(findId) && uMap.get(key).getName().equals(findName)) {
				return uMap.get(key);
			}
		}
		return null;
	}

	public boolean updatePassword(UserDTO userDTO, String updatePassword) {
		userDTO.setPw(updatePassword);
		return true;
	}

	public boolean admin(String adminId, String adminPassword) {
		if (adminI.equals(adminId) && adminP.equals(adminPassword)) {
			return true;
		}
		return false;
	}

	public Map<String, UserDTO> findAll() {
		return uMap;
	}

	public boolean delete(String deleteUser) {
		for (String key : uMap.keySet()) {
			if (deleteUser.equals(uMap.get(key).getBno())) {
				uMap.remove(key);
				return true;
			}
		}
		return false;
	}
}
