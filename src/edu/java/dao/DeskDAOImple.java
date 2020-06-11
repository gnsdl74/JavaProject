package edu.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.java.studyroom.DeskOracleQuery;
import edu.java.vo.DeskVO;
import oracle.jdbc.OracleDriver;

public class DeskDAOImple implements DeskDAO, DeskOracleQuery {
	// 클래스의 위치 및 이름 저장
	private static String className = DeskDAOImple.class.getName();

	// Singleton 패턴 적용
	// 1. private static 자기자신 타입 멤버 변수 선언
	private static DeskDAOImple instance = null;

	// 2. private 생성자
	private DeskDAOImple() {
		// DB 드라이버 등록(메모리 로드)

		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 등록 성공");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	// 3. public static 자기자신을 리턴하는 메소드
	public static DeskDAOImple getInstance() {
		if (instance == null) {
			instance = new DeskDAOImple();
		}
		return instance;
	}

	// conn, stmt 리소스 해제 함수
	private void closeResource(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// conn, stmt, rs 리소스 해제 함수
	private void closeResource(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<DeskVO> select() {
		ArrayList<DeskVO> list = new ArrayList<DeskVO>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(SQL_SELECT);

			while (rs.next()) {
				String desknumber = rs.getString(1);
				String id = rs.getString(2);
				String part = rs.getString(3);
				String startTime = rs.getString(4);
				String exitTime = rs.getString(5);
				DeskVO vo = new DeskVO(desknumber, id, part, startTime, exitTime);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public ArrayList<DeskVO> select_desk() {
		ArrayList<DeskVO> list = new ArrayList<DeskVO>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			stmt = conn.createStatement();

			rs = stmt.executeQuery(SQL_SELECT_DESKNUMBER);

			while (rs.next()) {
				String desknumber = rs.getString(1);

				DeskVO vo = new DeskVO(desknumber);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource(conn, stmt, rs);
		}
		return list;
	}

	@Override
	public int updateToString(String deskNumber) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_UPDATE);

			pstmt.setString(1, "0");
			pstmt.setString(2, null);
			pstmt.setString(3, null);
			pstmt.setString(4, null);
			pstmt.setString(5, deskNumber);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}

		return result;
	}

	@Override
	public int updateToComboBox(String id, String part, String startTime, String exitTime, String deskNumber) {
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DB 연결 성공");

			pstmt = conn.prepareStatement(SQL_UPDATE);

			pstmt.setString(1, id);
			pstmt.setString(2, part);
			pstmt.setString(3, startTime);
			pstmt.setString(4, exitTime);
			pstmt.setString(5, deskNumber);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}

		return result;
	}
}
