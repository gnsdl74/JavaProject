package edu.java.vo;

public class DeskVO {
	private String deskNum;
	private String id;
	private String part;
	private String startTime;
	private String exitTime;
	
	public DeskVO() {}
	public DeskVO(String desknumber) {
		this.deskNum = desknumber;
	}
	
	public DeskVO(String id, String part) {
		this.id = id;
		this.part = part;
	}
	
	public DeskVO(String desknumber, String id, String part) {
		this.deskNum = desknumber;
		this.id = id;
		this.part = part;
	}
	public DeskVO(String deskNum, String id, String part, String startTime, String exitTime) {
		this.deskNum = deskNum;
		this.id = id;
		this.part = part;
		this.startTime = startTime;
		this.exitTime = exitTime;
	}
	public String getDeskNum() {
		return deskNum;
	}
	public void setDeskNum(String deskNum) {
		this.deskNum = deskNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	
	@Override
	public String toString() {
		String str = "좌석번호 : " + deskNum + "\n" 
				+ "ID : " + id + "\n" 
				+ "시간제 : " + part + "\n" 
				+ "입실시간 : " + startTime + "\n" 
				+ "퇴실시간 : " + exitTime;
		return str;
	}
	
} // end DeskVO
