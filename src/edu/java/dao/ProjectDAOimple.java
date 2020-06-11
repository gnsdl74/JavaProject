package edu.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.java.studyroom.ProjectOracle;
import edu.java.vo.OutRoomVO;
import edu.java.vo.ProjectVO;
import oracle.jdbc.OracleDriver;

public class ProjectDAOimple implements ProjectDAO, ProjectOracle {
	
	//------------------------------------------------//
	private static ProjectDAOimple instance = null;
	
	private ProjectDAOimple() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			System.out.println("드라이버 등록 성공");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static ProjectDAOimple getinstance() {
		if(instance == null) {
			instance = new ProjectDAOimple();
		}
		return instance;
	}
	//-------------------------------------------------//
	@Override
	public int update(String id, ProjectVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(SQL_UPDATE);
			
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getrenumber());
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
	}// end update

	@Override
	public ProjectVO select(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProjectVO vo = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(SQL_SELECT);
			System.out.println(id);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String Id = rs.getString(COL_ID);
				String pw = rs.getString(COL_PW);
				String name = rs.getString(COL_NAME);
				String hp = rs.getString(COL_HP);
				
				vo = new ProjectVO(Id, pw, name, hp);
				System.out.println(vo.toString());
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return vo;
	}// end Select

	@Override
	public int delete(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(SQL_DELETE);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

			
		return result;
	}// end delete

	@Override
	public String PwCheck(String pw) {	
		Connection conn1 = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		String PW = "";
		try {
			conn1 = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt1 = conn1.prepareStatement(SQL_PW);
			
			pstmt1.setString(1, pw);
			
			rs = pstmt1.executeQuery();
			
			if(rs.next()) {
				PW = rs.getString(COL_PW);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt1.close();
				conn1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return PW;
	}// end PwCheck

	@Override
	public int InRoom(String id, String time, String table) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("InRoom DB 연결성공");
			pstmt = conn.prepareStatement(SQL_TABLE_CHECK);
			pstmt.setString(1, id);
			pstmt.setString(2, time);
			pstmt.setString(3, table);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		return result;
	}// end InRoom

	@Override
	public String enabled(String enabledId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String enabled = "";
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_ENABLED);
			pstmt.setString(1, enabledId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				enabled = rs.getString(COL_ID);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return enabled;
	}// end enabled

	@Override
	public String InRoomCut(String Inroomcut) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String inroomcut = "";
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_INROOMCUT);
			pstmt.setString(1, Inroomcut);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				inroomcut = rs.getString(COL_ID);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		return inroomcut;
	}// end InRoomCut

	@Override
	public OutRoomVO OutText(String selectid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OutRoomVO vo = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(SQL_OUT_TEXT);
			pstmt.setString(1, selectid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String selectCheck = rs.getString(TABLE_CHECK);
				String selectId = rs.getString(COL_ID);
				String selectpart = rs.getString(TIME_PART);
				
				vo = new OutRoomVO(selectCheck, selectId, selectpart);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return vo;
	}// end OutText

	@Override
	public int OutUpDate(String setid, String part, String inId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pstmt = conn.prepareStatement(SQL_OUT_CHECK);
			
			pstmt.setString(1, setid);
			pstmt.setString(2, part);
			pstmt.setString(3, inId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return result;
		
	}// end OutUpDate

	
}// end main
