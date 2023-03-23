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
	UserDTO user = null;
	BoardDTO boardDTO = null;
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
		boardDTO.setTitle(sc.next());
		System.out.print("내용 작성");
		boardDTO.setBody(sc.next());
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

	public void getDTO(UserDTO userDTO) {
		user = userDTO;
		return;
	}

	public void findAll() {
		Map<String, BoardDTO> adminMap = boardRepository.findAllAdmin();
		List<String> keySet = new ArrayList<>(adminMap.keySet());
		Collections.sort(keySet);
		Map<String, BoardDTO> bMap = boardRepository.findAll();
		List<String> keySet2 = new ArrayList<>(bMap.keySet());
		Collections.sort(keySet2);
		List<BoardDTO> likeList = boardRepository.findLikeList();
		if (keySet.size() == 0) {
			System.out.println("공지글이 없습니다");
		} else {
			System.out.println("==============================공지 글=======================================");
			System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t추천수\t게시일자");
			for (String key : keySet) {
				System.out.println(adminMap.get(key).toString());
			}
		}
		System.out.println("=============================================================================");
		if (keySet2.size() == 0) {
			System.out.println("글이 없습니다");
			return;
		} else {
			System.out.println("==============================글 목록=======================================");
			System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t추천수\t게시일자");
			for (String key : keySet2) {
				System.out.println(bMap.get(key).toString());
			}
		}
		System.out.println("추천 수 정렬하시겠습니까? ( Y / N ) ");
		String answer = sc.next();
		if (answer.equals("Y") || answer.equals("y")) {
			Collections.sort(likeList, new LikeComparator().reversed());
			System.out.println("==============================글 목록=======================================");
			System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t추천수\t게시일자");
			for (BoardDTO key : likeList) {
				System.out.println(key);
			}
		} else if (answer.equals("N") || answer.equals("n")) {
			System.out.println("종료");
			return;
		} else {
			System.out.println("다시입력");
		}
	}

	public void open() {
		System.out.println("1.공지글 2.일반글");
		int menu = boardService.numberCheck();
		if (menu == 1) {
			System.out.print("조회할 글 번호 입력");
			String openBno = sc.next();
			boardDTO = boardRepository.adminopen(openBno);
			if (boardDTO == null) {
				System.out.println("조회오류");
				return;
			}
		} else if (menu == 2) {
			System.out.print("조회할 글 번호 입력");
			String openBno = sc.next();
			boardDTO = boardRepository.open(openBno);
			if (boardDTO == null) {
				System.out.println("조회오류");
				return;
			}
		} else {
			System.out.println("조회오류");
			return;
		}
		System.out.println(boardDTO.print());
		System.out.println("추천 수 : " + boardDTO.getLike() + "\n" + "비추천 수 : " + boardDTO.getUnLike());
		while (true) {
			System.out.println("1.추천하기 2.비추천하기 3.뒤로가기");
			menu = boardService.numberCheck();
			if (menu == 1) {
				if (boardRepository.like(boardDTO)) {
					System.out.println("추천성공");
				}
				return;
			} else if (menu == 2) {
				if (boardRepository.unLike(boardDTO)) {
					System.out.println("비추천성공");
				}
				return;
			} else if (menu == 3) {
				return;
			} else {
				System.out.println("다시입력");
			}
		}
	}

	public void adminUpdate() {
		System.out.println("1.공지글 수정 2.일반글 수정 0.종료");
		int menu = boardService.numberCheck();
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
		} else if (menu == 0) {
			System.out.println("종료");
			return;
		} else {
			System.out.println("다시입력");
		}
	}

	public int numberCheck() {
		int result;
		while (true) {
			if (sc.hasNextInt()) {
				result = sc.nextInt();
				break;
			} else {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
			}
		}
		return result;
	}

	public void declaration(UserDTO user) {
		if (user.getDec() == 0) {
			System.out.println("신고할 글번호를 입력하세요");
			String Declarationbno = sc.next();
			int num = boardRepository.declaration(Declarationbno, user);
			if (num == 1) {
				System.out.println("신고가 2이 되어 글이 삭제되었습니다");
			} else if (num == 2) {
				System.out.println("신고되었습니다");
			} else if (num == 3) {
				System.out.println("신고오류");
			}
		} else {
			System.out.println("신고는 유저당 한번만 할 수 있습니다");
		}
		return;
	}

	public void search() {
		System.out.print("검색할 글 작성자 입력");
		String search = sc.next();
		Map<String, BoardDTO> searchMap = boardRepository.search(search);
		List<String> keySet = new ArrayList<>(searchMap.keySet());
		Collections.sort(keySet);
		if (searchMap.size() == 0) {
			System.out.println("검색된 작성자의 글이 없습니다");
		} else {
			System.out.println("==============================검색글=======================================");
			System.out.println("글 번호\t글 제목\t글 작성자\t조회수\t글 내용\t게시일자");
			for (String key : keySet) {
				System.out.println(searchMap.get(key).toString());
			}
		}
	}

	public void delete() {
		while (true) {
			System.out.println("삭제할 곳");
			System.out.println("1.공지글 2.일반글 3.뒤로가기");
			int menu = boardService.numberCheck();
			if (menu == 1) {
				System.out.print("삭제할 글번호 입력");
				String deleteBno = sc.next();
				if (boardRepository.adminDelete(deleteBno)) {
					System.out.println("삭제 완료");
				} else {
					System.out.println("삭제 실패");
				}
			} else if (menu == 2) {
				System.out.print("삭제할 글번호 입력");
				String deleteBno = sc.next();
				if (boardRepository.Delete(deleteBno)) {
					System.out.println("삭제 완료");
				} else {
					System.out.println("삭제 실패");
				}
			} else if (menu == 3) {
				break;
			} else {
				System.out.println("다시입력");
			}
		}
	}
	public UserDTO belogin(UserDTO userDTO) {
		return userDTO;
	}
}

class LikeComparator implements Comparator<BoardDTO> {

	public int compare(BoardDTO board1, BoardDTO board2) {
		if (board1.getLike() > board2.getLike()) {
			return 1;
		} else if (board1.getLike() < board2.getLike()) {
			return -1;
		}
		return 0;
	}
}
