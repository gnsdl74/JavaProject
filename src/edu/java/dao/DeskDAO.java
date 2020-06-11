package edu.java.dao;

import java.util.ArrayList;

import edu.java.vo.DeskVO;

public interface DeskDAO {

	// 전체 검색 기능
	public abstract ArrayList<DeskVO> select();
	
	// 좌석 번호 출력 기능
	public abstract ArrayList<DeskVO> select_desk();
	
	// String으로 좌석번호를 받아 수정 기능
	public abstract int updateToString(String deskNumber);
	
	// ComboBox로 좌석번호, id, part를 받아 수정 기능
	public abstract int updateToComboBox(String id, String part, String startTime, String exitTime, String deskNumber);
	
}
