package project;

import java.util.*;

public class BoardService {
	private static BoardService boardService = new BoardService();

	private BoardService() {
	}

	Scanner sc = new Scanner(System.in);

	public static BoardService getInstance() {
		return boardService;
	}

	BoardRepository boardRepository = BoardRepository.getInstance();
	UserDTO user = new UserDTO();
	BoardDTO boardDTO = new BoardDTO();
	Map<UserDTO, String> adminMap = new HashMap<>();
	String admin = "admin";
	String belogin = "belogin";

	public void user(UserDTO userDTO) {
		user = userDTO;
		return;
	}

	public void set(UserDTO userDTO) {
		BoardDTO boardDTO = new BoardDTO();
		System.out.print("제목 작성");
		boardDTO.setTitle(sc.nextLine());
		System.out.print("내용 작성");
		boardDTO.setBody(sc.nextLine());
		if (user.getRole() == "belogin") {
			boardDTO.setWriter("Trave" + (int) (Math.random() * 100));
		} else {
			boardDTO.setWriter(user.getNickName());
		}
		if (boardRepository.set(boardDTO.getBno(), boardDTO, userDTO)) {
			System.out.println("작성완료");
		} else {
			System.out.println("Error");
		}
		if (!(userDTO.getRole() == "admin" || userDTO.getRole() == "belogin")) {
			boardRepository.point5(user, user.getPoint());
			System.out.println("포인트 5 누적");
		}
	}

	public void findAll() {
		Map<String, BoardDTO> adminMap = boardRepository.findAllAdmin();
		Map<String, BoardDTO> bMap = boardRepository.findAll();
		List<String> keySet = new ArrayList<>(adminMap.keySet());
		Collections.sort(keySet);
		List<String> keySet2 = new ArrayList<>(bMap.keySet());
		Collections.sort(keySet2);
		if (keySet.size() == 0) {
			System.out.println("공지글이 없습니다");
		} else {
			System.out.println("==============================공지 글=======================================");
			System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t게시일자");
			for (String key : keySet) {
				System.out.println(adminMap.get(key).toString());
			}
		}
		if (keySet2.size() == 0) {
			System.out.println("글이 없습니다");
		} else {
			System.out.println("==============================글 목록=======================================");
			for (String key : keySet2) {
				System.out.println(bMap.get(key).toString());
			}
		}

	}

	public void open() {
		System.out.println("1.공지글 2.일반글");
		int menu = sc.nextInt();
		System.out.print("조회할 글 번호 입력");
		String openBno = sc.next();
		if (menu == 1) {
			boardDTO = boardRepository.adminopen(openBno);
		} else if (menu == 2) {
			boardDTO = boardRepository.open(openBno);
		}
		boardDTO.setCnt(boardDTO.getCnt() + 1);
		System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t게시일자");
		System.out.println(boardDTO.toString());
	}

	public void adminUpdate() {
		System.out.println("1.공지글 수정 2.일반글 수정 0.종료");
		int menu = sc.nextInt();
		if (menu == 1) {
			System.out.print("수정할 글번호 입력");
			String update = sc.next();
			if (boardRepository.adminUpdate(update)) {
				System.out.println("수정완료");
			} else {
				System.out.println("입력한 글번호가 없습니다");
			}
			return;
		} else if (menu == 2) {
			System.out.print("수정할 글번호 입력");
			String update = sc.next();
			if (boardRepository.update(update)) {
				System.out.println("수정완료");
			} else {
				System.out.println("입력한 글번호가 없습니다");
			}
			return;
		} else {
			System.out.println("다시입력");
		}
	}

}
