package edu.java.dao;

import java.sql.*;
import java.util.ArrayList;

import edu.java.studyroom.StudyRoomQuery;
import edu.java.vo.DeskVO;
import edu.java.vo.StudyRoomMemberVO;
import oracle.jdbc.driver.OracleDriver;

public class StudyRoomDAOImple implements StudyRoomDAO, StudyRoomQuery {
	// start singleton
	private static StudyRoomDAOImple instance;
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	private StudyRoomDAOImple() {

		// DB 연결 초기화
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 연결 성공");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // end ContactDAOImple() - 생성자

	public static StudyRoomDAOImple getInstance() {
		if (instance == null) {
			instance = new StudyRoomDAOImple();
		}
		return instance;
	}
	// end singleton

	// conn, stmt 리소스 해제
	private void closeResource(Connection conn, Statement stmt) {
		try {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ce) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception pe) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end closerResource

	// conn, stmt, rs 리소스 해제 함수
	private void closeResource(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ce) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception pe) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception re) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end closerResource

	@Override
	public int checkId(String id) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_CHECK_ID);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}

		return result;
	} // end checkId - Join에서 사용(ID 중복방지)

	@Override
	public int checkReNumber(String reNumber) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_CHECK_RENUMBER);
			pstmt.setString(1, reNumber);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}

		return result;
	} // end checkReNumber - Join에서 사용(주민번호 중복방지)

	@Override
	public int insert(StudyRoomMemberVO vo) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getReNumber());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}
		return result;
	} // end insert - ScMain 사용(회원가입)

	@Override
	public StudyRoomMemberVO login(String id, String pw) {
		StudyRoomMemberVO vo = new StudyRoomMemberVO();

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_LOGIN);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cid = rs.getString(COL_ID);
				String cpw = rs.getString(COL_PW);
				vo.setId(cid);
				vo.setPw(cpw);
			} else {
				vo = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		return vo;
	} // end login - ScMain 사용

	@Override
	public String findId(String name, String reNumber) {
		String result = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_FIND_ID);
			pstmt.setString(1, name);
			pstmt.setString(2, reNumber);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getString(COL_ID);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}

		return result;
	} // end findId - ScMain 사용(ID찾기)

	@Override
	public int findPw(String id, String reNumber) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_FIND_PW);
			pstmt.setString(1, id);
			pstmt.setString(2, reNumber);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt = conn.prepareStatement(SQL_INIT_PW);
				pstmt.setString(1, id);
				pstmt.setString(2, reNumber);

				result = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}

		return result;
	} // end findPw - ScMain 사용(PW찾기)

	@Override
	public int updateDesk(String id, String part, String desk, String startTime, String expectExitTime) {
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_DESK_UPDATE);
			pstmt.setString(1, id);
			pstmt.setString(2, part);
			pstmt.setString(3, startTime);
			pstmt.setString(4, expectExitTime);
			pstmt.setString(5, desk);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt);
		}

		return result;
	} // end chooseDesk - Payment에서 사용(비회원 > 좌석번호를 찾아 ID 입력)

	@Override
	public DeskVO confirmDesk(String id) {
		DeskVO vo = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);

			pstmt = conn.prepareStatement(SQL_CONFIRM_DESK);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String cdesk = rs.getString(COL_DESKNUMBER);
				String cid = rs.getString(COL_ID);
				String cpart = rs.getString(COL_PART);
				String startTime = rs.getString(COL_START);
				String exitTime = rs.getString(COL_EXIT);

				String part = convertPart(cpart);
				vo = new DeskVO(cdesk, cid, part, startTime, exitTime);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}

		return vo;
	} // end confirmDesk - OutDesk에서 사용(좌석확인버튼 클릭 시)

	public String convertPart(String part) {
		String result = null;

		switch (part) {
		case "2":
			result = "2시간 - 3000원";
			break;
		case "4":
			result = "4시간 - 5000원";
			break;
		case "6":
			result = "6시간 - 7000원";
			break;
		case "9":
			result = "9시간 - 10000원";
			break;
		case "12":
			result = "12시간 - 13000원";
			break;
		default:
			result = "입력오류";
			break;
		}

		return result;
	} // end convertPart - confirmDesk에서 사용

	@Override
	public ArrayList<DeskVO> select_ID_Desk() {
		ArrayList<DeskVO> list = new ArrayList<DeskVO>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(SQL_SELECT_ID_DESK);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String deskNum = rs.getString(COL_DESKNUMBER);
				String id = rs.getString(COL_ID);
				
				DeskVO vo = new DeskVO(deskNum, id, null, null, null);
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResource(conn, pstmt, rs);
		}
		
		return list;
	} // end select_ID_Desk - InDesk에서 사용

} // end ScHomeDAOImple