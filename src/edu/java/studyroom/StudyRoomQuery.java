package edu.java.studyroom;

// JDBC에서 사용될 상ㅅ들, SQL 문장들을 정의
public interface StudyRoomQuery {
	public static final String URL = "jdbc:oracle:thin:@172.16.1.22:1521:orcl"; // 접속할 오라클 DB 경로
	public static final String USER = "scott";
	public static final String PASSWORD = "tiger";
	// 이클립스와 연동할 DB의 사용자는 cmd를 통해서 계정 활성화 및 권한부여를하고 사용한다.
	
	// 프로그램에 사용될 테이블과 컬럼들을 상수로 설정
	public static final String TABLE_NAME_MEMBER = "sc_member";
	public static final String TABLE_NAME_DESK = "desk";
	public static final String COL_ID = "id";
	public static final String COL_PW = "pw";
	public static final String COL_NAME = "name";
	public static final String COL_RENUMBER = "renumber";
	public static final String COL_DESKNUMBER = "deskNumber";
	public static final String COL_PART = "part";
	public static final String COL_START = "starttime";
	public static final String COL_EXIT = "exittime";

	// 회원가입
	// insert into sc_member     
	// values (?, ?, ?, ?, null)
	public static final String SQL_INSERT = "insert into " + TABLE_NAME_MEMBER + " values (?, ?, ?, ?)";

	// ID, 주민등록번호 중복 찾기
	// select id from sc_member where id = ?
	// select reNumber from sc_member where reNumber = ?
	public static final String SQL_CHECK_ID = "select " + COL_ID + " from " + TABLE_NAME_MEMBER + " where " + COL_ID
			+ " = ?";
	public static final String SQL_CHECK_RENUMBER = "select " + COL_RENUMBER + " from " + TABLE_NAME_MEMBER + " where "
			+ COL_RENUMBER + " = ?";
	// 로그인
	// select id, pw from sc_member where id = ? and pw = ?
	public static final String SQL_LOGIN = "select id, pw from " + TABLE_NAME_MEMBER + " where " + COL_ID + " = ? and "
			+ COL_PW + " = ?";

	// ID 찾기
	// select id from sc_member where name = ? and renumber = ?
	public static final String SQL_FIND_ID = "select " + COL_ID + " from " + TABLE_NAME_MEMBER + " where " + COL_NAME
			+ " = ? and " + COL_RENUMBER + " = ?";

	// PW 찾음
	// select pw from sc_member where id = ? and renumber = ?
	public static final String SQL_FIND_PW = "select " + COL_PW + " from " + TABLE_NAME_MEMBER + " where " + COL_ID
			+ " = ? and " + COL_RENUMBER + " = ?";

	// PW를 찾으면 1234로 초기화
	// update from sc_member set pw = 1234 where id = ? and renumber = ?
	public static final String SQL_INIT_PW = "update " + TABLE_NAME_MEMBER + " set " + COL_PW + " = 1234 where "
			+ COL_ID + " = ? and " + COL_RENUMBER + " = ?";

	// 해당 좌석번호에 ID와 part를 변경
	// update desk set id = ?, part = ?, startTime = ?, exitTime = ? where deskNumber = ?
	public static final String SQL_DESK_UPDATE = "update " + TABLE_NAME_DESK + " set " + COL_ID + " = ?, " + COL_PART
			+ " = ?, "+COL_START+" = ?, "+COL_EXIT+" = ? where " + COL_DESKNUMBER + " = ?";

	// 임시ID를 입력하여 좌석번호 및 시간제 반환
	// select * from desk where id = ?
	public static final String SQL_CONFIRM_DESK = "select * from "
			+ TABLE_NAME_DESK + " where " + COL_ID + " = ?";

	// Desk테이블의 좌석번호와 ID 가져오기
	// select deskNumber, id from desk
	public static final String SQL_SELECT_ID_DESK = "select " + COL_DESKNUMBER + ", " + COL_ID + " from "
			+ TABLE_NAME_DESK;
}
