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
	private static int declarartionstatus = 0;

	Map<String, BoardDTO> bMap = new HashMap<>(); // 글이 모일 맵, key = bno value=boardDTO
	List<BoardDTO> bList = new ArrayList<>();// 공지글을 제외한 모든글
	Map<UserDTO, Long> pointMap = new HashMap<>(); // 유저마다 포인트 쌓일 맵 key = userDTO value = point
	Map<String, BoardDTO> adminMap = new HashMap<>();// 공지글
	Map<String, BoardDTO> searchMap = new HashMap<>(); // 검색글

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
				adminMap.get(key).setCnt(adminMap.get(key).getCnt() + 1);
				return adminMap.get(key);

			}
		}
		return null;
	}

	public BoardDTO open(String openBno) {
		for (String key : bMap.keySet()) {
			if (openBno.equals(bMap.get(key).getBno())) {
				bMap.get(key).setCnt(bMap.get(key).getCnt() + 1);
				return bMap.get(key);
			}
		}
		return null;

	}

	public boolean adminUpdate(String bno) {
		for (String key : adminMap.keySet()) {
			if (bno.equals(adminMap.get(key).getBno())) {
				System.out.print("수정할 제목");
				adminMap.get(key).setTitle(sc.next());
				System.out.print("수정할 내용");
				adminMap.get(key).setBody(sc.next());
				return true;
			}
		}
		return false;
	}

	public boolean update(String bno) {
		for (String key : bMap.keySet()) {
			if (bno.equals(bMap.get(key).getBno())) {
				System.out.print("수정할 제목");
				String title = sc.next();
				bMap.get(key).setTitle(title);
				System.out.print("수정할 내용");
				String body = sc.next();
				bMap.get(key).setBody(body);
				return true;
			}
		}
		return false;
	}

	public int declaration(String Declarationbno, UserDTO user) {
		for (String key : bMap.keySet()) {
			if (Declarationbno.equals(bMap.get(key).getBno())) {
				user.setDec(1);
				bMap.get(key).setDeclaration(bMap.get(key).getDeclaration() + 1);
				if (bMap.get(key).getDeclaration() == 2) {
					bMap.remove(key);
				}
				if (bMap.containsKey(Declarationbno)) {
					return 2;
				}
				return 1;
			}
		}
		return 3;
	}
	public Map<String,BoardDTO> search(String search) {
		Map<String,BoardDTO> ss = new HashMap<>();
		for(String key : bMap.keySet()) {
			if(search.equals(bMap.get(key).getWriter())) {
				ss.put(bMap.get(key).getBno(),bMap.get(key));
			}
		}
		return ss;
	}
	public boolean adminDelete(String deleteBno) {
		for(String key : adminMap.keySet()) {
			if(deleteBno.equals(adminMap.get(key).getBno())) {
				adminMap.remove(key);
				return true;
			}
		}
		return false;
	}
	public boolean Delete(String deleteBno) {
		for(String key : bMap.keySet()) {
			if(deleteBno.equals(bMap.get(key).getBno())) {
				bMap.remove(key);
				return true;
			}
		}
		return false;
	}
	
}
