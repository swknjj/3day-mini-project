package project;

import java.util.*;

public class UserRepository {
	private static UserRepository userRepository = new UserRepository();

	private UserRepository() {
	}

	public static UserRepository getInstance() {
		return userRepository;
	}

	UserService userService = UserService.getInstance();
	Map<String, UserDTO> uMap = new HashMap<>();
	List<UserDTO> uList = new ArrayList<>();

	public boolean save(UserDTO userDTO) {
		uMap.put(userDTO.getBno(),userDTO);
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
}
