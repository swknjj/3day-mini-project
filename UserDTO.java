package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	private String bno;
	private static Long number = 96L;
	private String nickName;
	private Long point = 0L;
	private String newDate;
	private String role;
	

	@Override
	public String toString() {
		return bno+"\t"+id+"\t"+pw+"\t"+name+"\t"+nickName+"\t"+newDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

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

	public String getNewDate() {
		return newDate;
	}

}
