package edu.java.studyroom;

public interface ProjectOracle {
	
	public static final String URL =
			"jdbc:oracle:thin:@172.16.1.22:1521:orcl";
	
	// 사용자와 비밀번호
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	
	public static final String TABLE_NAME = "sc_member";
	public static final String COL_ID = "id";
	public static final String COL_PW = "pw";
	public static final String COL_NAME = "name";
	public static final String COL_HP = "renumber";
	
	
	/*======================================================*/
	
	// 입실 DB접근
	public static final String TABLE_NAME_1 = "desk";
	public static final String TABLE_CHECK = "desknumber";
	public static final String TIME_PART = "part";
	
	
	/*======================================================*/
	
	// select
	public static final String SQL_SELECT = 
			"select * from " + TABLE_NAME + " where " + COL_ID + " = ?";
	
	// update
	public static final String SQL_UPDATE = 
			"update " + TABLE_NAME + " set " + COL_PW + " = ?, " + COL_NAME + " = ?, "
					+ COL_HP + " = ? " + " where " + COL_ID + " = ?";
	
	// delete
	public static final String SQL_DELETE = 
			"delete from " + TABLE_NAME + " where " + COL_ID + " = ?";
	
	// PW 확인
	public static final String SQL_PW =
			"select " + COL_PW + " from " + TABLE_NAME + " where " + COL_ID + " = ?";
	
	
	/*=================================================================================================*/
	
	// 입실접근
	public static final String SQL_TABLE_CHECK = 
			"update " + TABLE_NAME_1 + " set " + COL_ID + " = ?, " + TIME_PART + " = ? " + " where " + TABLE_CHECK + " = ?";
			
	
	// enabled 사용
	public static final String SQL_ENABLED =
			"select " + COL_ID + " from " + TABLE_NAME_1 + " where " + TABLE_CHECK + " = ?";
	
	// 입실커트
	public static final String SQL_INROOMCUT =
			"select " + COL_ID + " from " + TABLE_NAME_1 + " where " + COL_ID + " = ?";
	
	// 퇴실
	public static final String SQL_OUT_CHECK = 
			"update " + TABLE_NAME_1 + " set " + COL_ID + " = ?, " +  TIME_PART + " = ? " + " where " + COL_ID + " = ?";
	
	// 퇴실회원정보접근
	public static final String SQL_OUT_TEXT = 
			"select * from " + TABLE_NAME_1 + " where " + COL_ID + " = ?";
	
	

}
