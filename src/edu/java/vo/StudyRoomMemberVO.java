package edu.java.vo;

public class StudyRoomMemberVO {
	private String id;	// id
	private String name; // 이름
	private String pw;	// pw
	private String reNumber; // 주민번호
	private String part; // 시간제
	
	public StudyRoomMemberVO() {};
	public StudyRoomMemberVO(String id, String pw, String name, String reNumber, String part) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.reNumber = reNumber;
		this.part = part;
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
	public String getReNumber() {
		return reNumber;
	}
	public void setReNumber(String reNumber) {
		this.reNumber = reNumber;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	
	@Override
	public String toString() {
		String str = "ID : " + id + "\n" + "Password : " + pw + "\n" + "이름 : " + name + "\n" + "주민등록번호 : " + reNumber + "\n" + "시간제 : " + part;
		return str;
	}
	
} // end ScMemberVO
