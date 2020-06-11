package edu.java.vo;

public class OutRoomVO {
	private String desknumber;
	private String id;
	private String timepart;
	
	public OutRoomVO() {}
	public OutRoomVO(String desknumber, String id , String timepart) {
		this.desknumber = desknumber;
		this.id = id;
		this.timepart = timepart;
	}
	public String getDesknumber() {
		return desknumber;
	}
	public void setDesknumber(String desknumber) {
		this.desknumber = desknumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTimepart() {
		return timepart;
	}
	public void setTimepart(String timepart) {
		this.timepart = timepart;
	}
	@Override
	public String toString() {
		String str = "좌석 : " + desknumber + "\n"
				+ "id : " + id + "\n"
				+ "time : " + timepart + "\n";
		return str;
	}
}// end OutRoomVO
