package project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private final static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
	private String bno;
	private static Long number = 100L;
	private String nickName;
	private Long point = 0L;
	private String newDate;
	private String role;
	private int dec = 0;

	@Override
	public String toString() {
		return bno + "\t" + id + "\t" + pw + "\t\t" + name + "\t" + nickName + "\t\t" + newDate;
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
		this.bno = "U" + number++;
		this.newDate = DTF.format(LocalDateTime.now());

	}
	public UserDTO(String id , String pw , String nickname , String role) {
		this.id = id;
		this.pw = pw;
		this.nickName = nickname;
		this.role = role;
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

	public int getDec() {
		return dec;
	}

	public void setDec(int dec) {
		this.dec = dec;
	}

}
