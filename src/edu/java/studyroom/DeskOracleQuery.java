package edu.java.studyroom;

public interface DeskOracleQuery {
	// DB에 사용될 상수들
	public static final String URL =
			"jdbc:oracle:thin:@172.16.1.22:1521:orcl";
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	
	public static final String TABLE_NAME = "desk";
	public static final String COL_DESKNUMBER = "desknumber";
	public static final String COL_ID = "id";
	public static final String COL_PART = "part";
	public static final String COL_START = "startTime";
	public static final String COL_EXIT = "exitTime";
	
	public static final String SQL_SELECT =
			"select * from " + TABLE_NAME + " order by " + COL_DESKNUMBER;
	
	public static final String SQL_SELECT_DESKNUMBER =
			"select " + COL_DESKNUMBER + " from " + TABLE_NAME + " order by " + COL_DESKNUMBER; 
	
	public static final String SQL_UPDATE =
			"update " + TABLE_NAME + " set " 
			+ COL_ID + " = ?, " + COL_PART + " = ?, " + COL_START + " = ?, " + COL_EXIT + " = ? "
			+ " where " + COL_DESKNUMBER + " = ?";
}
