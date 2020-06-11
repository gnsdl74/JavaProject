package edu.java.dao;

import edu.java.vo.OutRoomVO;
import edu.java.vo.ProjectVO;

public interface ProjectDAO{
	
	// 아이디 검색기능
	public abstract ProjectVO select(String id);
	
	// 정보 수정
	public abstract int update(String id, ProjectVO vo);
	
	// 정보삭제
	public abstract int delete(String id);
	
	// pw확인
	public abstract String PwCheck(String pw);
		
	// 입실 관리
	public abstract int InRoom(String id, String time,String table);
	
	// enabled 접근
	public abstract String enabled(String enabledId);
	
	// 입실커트
	public abstract String InRoomCut(String Inroomcut);
	
	// 퇴실회원 정보 접근
	public abstract OutRoomVO OutText(String selectid);
	
	// 퇴실관리
	public abstract int OutUpDate(String setid, String part, String inId);
	
}
