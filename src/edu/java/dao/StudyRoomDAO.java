package edu.java.dao;

import java.util.ArrayList;

import edu.java.vo.DeskVO;
import edu.java.vo.StudyRoomMemberVO;

public interface StudyRoomDAO {
	public abstract int checkId(String id);
	public abstract int checkReNumber(String reNumber);
	public abstract int insert(StudyRoomMemberVO vo);
	public abstract StudyRoomMemberVO login(String id, String pw);
	public abstract String findId(String name, String reNumber);
	public abstract int findPw(String id, String reNumber);
	public abstract int updateDesk(String id, String part, String desk, String startTime, String expectExitTime);
	public abstract DeskVO confirmDesk(String id);
	public abstract ArrayList<DeskVO> select_ID_Desk();
} // end ScHome
