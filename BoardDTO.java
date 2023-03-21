package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardDTO {
	private String title;
	private String writer;
	private static Long number = 100L;
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	private long cnt;
	private String bno;
	private String postDate;
	private String body;

	public String getBno() {
		return bno;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public BoardDTO () {
			this.bno = "B"+number++;
			this.postDate = DTF.format(LocalDateTime.now());
			this.cnt = 0;
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

	public static Long getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return bno+"\t"+title+"\t"+writer+"\t"+cnt+"\t"+body+"\t"+postDate; 
	}

	public Long getCnt() {
		return cnt;
	}

}
