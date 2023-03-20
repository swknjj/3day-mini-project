package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardDTO {
	private String title;
	private String writer;
	private static int number;
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	private String name;
	private long cnt;
	private String bno;
	private String postDate;
	private long point;

	public String getBno() {
		return bno;
	}

	public BoardDTO () {
			this.bno = "B"+number++;
			this.postDate = DTF.format(LocalDateTime.now());
			this.cnt = 0;
			this.point = 0;
			
	}


	public long getPoint() {
		return point;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public static int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCnt() {
		return cnt;
	}

}
