package project;

import java.util.*;

public class BoardRepository {
	private static BoardRepository boardRepository = new BoardRepository();

	private BoardRepository() {
	}

	public static BoardRepository getInstance() {
		return boardRepository;
	}

	BoardService boardService = BoardService.getInstance();
	UserDTO userDTO = new UserDTO();
	Map<String, BoardDTO> bMap = new HashMap<>(); // 글이 모일 맵, key = bno value=boardDTO
	List<BoardDTO> bList = new ArrayList<>();
	Map<UserDTO, Long> pointMap = new HashMap<>(); // 유저마다 포인트 쌓일 맵 key = userDTO value = point
	Map<String, BoardDTO> adminMap = new HashMap<>();// 공지글
	List<BoardDTO> openList = new ArrayList<>();

	public void point5(UserDTO userDTO, Long point) {
		userDTO.setPoint(point + 5);
		pointMap.put(userDTO, point);
	}

	public boolean set(String bno, BoardDTO boardDTO, UserDTO userDTO) {
		if (userDTO.getId() == "admin" && userDTO.getRole() == "admin") {
			boardDTO.setWriter(userDTO.getRole());
			adminMap.put(boardDTO.getBno(), boardDTO);
			return true;
		} else {
			bMap.put(bno, boardDTO);
			return true;
		}
	}

	public Map<String, BoardDTO> findAllAdmin() {
		return adminMap;
	}

	public Map<String, BoardDTO> findAll() {
		return bMap;
	}

	public List<BoardDTO> open(String openBno) {
		for(String key : adminMap.keySet()) {
			if(openBno.equals(adminMap.get(key).getBno())) {
				openList.add(adminMap.get(key));
			}
			if(openBno.equals(bMap.get(key).getBno())) {
				openList.add(bMap.get(key));
			}
		}
		return openList;
	}
}
