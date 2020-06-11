package edu.java.vo;

public class ProjectVO {
	private String id;
	private String pw;
	private String name;
	private String renumber;
	
	public ProjectVO() {}
	public ProjectVO(String id, String pw, String name, String renumber) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.renumber = renumber;
	}
	
	// getter / setter
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
	public String getrenumber() {
		return renumber;
	}
	public void setrenumber(String renumber) {
		this.renumber = renumber;
	}
	
	@Override
	public String toString() {
		String str = "아이디 : " + id + "\n"
				+ "PW : " + pw + "\n"
				+ "이름 : " + name + "\n"
				+ "주민번호 : " + renumber + "\n";
		return str;
	}
	
}// end ProjectVO
