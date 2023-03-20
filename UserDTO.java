package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	private String Date;
	private String bno;
	private static Long number = 100L;
	private String newDate;
	
	public UserDTO() {
		this.bno = "U"+number++;
		this.newDate = DTF.format(LocalDateTime.now());
	}
	
	public String getBno() {
		return bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return Date;
	}
	
}
