package project;

import java.util.*;

public class BoardRepository {
	private static BoardRepository boardRepository = new BoardRepository();

	private BoardRepository() {
	}

	public static BoardRepository getInstance() {
		return boardRepository;
	}

	Scanner sc = new Scanner(System.in);
	BoardService boardService = BoardService.getInstance();
	UserDTO userDTO = new UserDTO();
	BoardDTO boardDTO = new BoardDTO();
	Map<String, BoardDTO> bMap = new HashMap<>(); // 글이 모일 맵, key = bno value=boardDTO
	List<BoardDTO> bList = new ArrayList<>();// 공지글을 제외한 모든글
	Map<UserDTO, Long> pointMap = new HashMap<>(); // 유저마다 포인트 쌓일 맵 key = userDTO value = point
	Map<String, BoardDTO> adminMap = new HashMap<>();// 공지글

	public void point5(UserDTO userDTO, Long point) {
		userDTO.setPoint(point + 5);
		pointMap.put(userDTO, point);
	}

	public boolean set(String bno, BoardDTO boardDTO, UserDTO userDTO) {
		if (userDTO.getId() == "admin" && userDTO.getRole() == "admin") {
			boardDTO.setWriter(userDTO.getRole());
			adminMap.put(bno, boardDTO);
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

	public BoardDTO adminopen(String openBno) {
		
		for (String key : adminMap.keySet()) {
			if (openBno.equals(adminMap.get(key).getBno())) {
				boardDTO = adminMap.get(key);
				
			}
		}
		return boardDTO;
	}
	public BoardDTO open(String openBno) {
		for (String key : bMap.keySet()) {
			if (openBno.equals(bMap.get(key).getBno())) {
				boardDTO = bMap.get(key);
				
			}
		}
		return boardDTO;

	}

	public boolean adminUpdate(String bno) {
		for (String key : adminMap.keySet()) {
			if (bno.equals(adminMap.get(key).getBno())) {
				System.out.print("수정할 제목");
				adminMap.get(key).setTitle(sc.next());
				System.out.println("수정할 내용");
				adminMap.get(key).setBody(sc.next());
				return true;
			}
		}return false;
	}

	public boolean update(String bno) {
		for (String key : bMap.keySet()) {
			if (bno.equals(bMap.get(key).getBno())) {
				System.out.print("수정할 제목");
				adminMap.get(key).setTitle(sc.next());
				System.out.println("수정할 내용");
				adminMap.get(key).setBody(sc.next());
				return true;
			}
		}return false;
	}
}
