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
	private long declaration;
	private Integer like = 0;
	private Integer unLike = 0;
	
	
	public Integer getUnLike() {
		
		return unLike;
	}

	public void setUnLike(Integer unLike) {
		this.unLike = unLike;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

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
			this.declaration = 0L;
	}

	public long getDeclaration() {
		return declaration;
	}

	public void setDeclaration(long declaration) {
		this.declaration = declaration;
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
		return bno+"\t"+title+"\t"+writer+"\t"+cnt+"\t"+body+"\t"+like+"\t"+postDate; 
	}
	public String print() {
		return "================================================================================\n\n"
			+ "글 번호 : " + bno + "\n\n================================================================================\n\n"
			+ "글 제목 : " + title + "\n\n================================================================================\n\n"
			+ "글 작성자 : " + writer + "\n\n================================================================================\n\n"
			+ "글 내용 : " + body + "\n\n================================================================================";
				
	}
	public Long getCnt() {
		return cnt;
	}

	public void setCnt(long cnt) {
		this.cnt = cnt;
	}

}
