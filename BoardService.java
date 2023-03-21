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
			boardDTO.setWriter("Trave"+(int)(Math.random()*100));
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
		System.out.println("==============================공지 글=======================================");
		System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t게시일자");
		for (String key : keySet) {
			System.out.println(adminMap.get(key).toString());
		}
		System.out.println("==============================글 목록=======================================");
		for (String key : keySet2) {
			System.out.println(bMap.get(key).toString());
		}

	}
	public void open() {
		System.out.print("조회할 글 번호 입력");
		String openBno = sc.next();
		List<BoardDTO> openList = new ArrayList<>();
		openList = boardRepository.open(openBno);
		for(BoardDTO open : openList) {
			System.out.println(open.toString());
		}
	
	}
}
