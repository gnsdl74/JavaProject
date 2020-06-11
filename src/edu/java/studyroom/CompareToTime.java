package edu.java.studyroom;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;
import edu.java.vo.DeskVO;

public class CompareToTime {
	private StudyRoomDAO dao;

	public void compareToTime(JFrame frame, DeskVO vo, String deskNumber) throws Exception {
		dao = StudyRoomDAOImple.getInstance();
		int result = 0;

		// 예상 퇴실 시간 DB에서 가져옴
		String expectExitTime = vo.getExitTime();

		// 실제 퇴실 시간 가져오기
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime(); // 실제 퇴실 시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");

		// 예상 퇴실 시간 parsing
		Date expectExitDate = sdf.parse(expectExitTime);
		long curTime = date.getTime() / 60000;
		long expectTime = expectExitDate.getTime() / 60000;
		System.out.println(curTime);
		System.out.println(expectTime);
		String curTimee = sdf.format(curTime * 60000);
		System.out.println(curTimee);
		String exTime = sdf.format(expectTime * 60000);
		System.out.println(exTime);

		// 분까지만 비교해서 처리하고 30분마다 1000원씩 추가 결제
		if (curTime <= expectTime) { // 예상퇴실 시간이 실제 퇴실 시간보다 일찍일때 - 퇴실처리
			System.out.println("퇴실 완료");
			result = dao.updateDesk("0", null, deskNumber, null, null); // 좌석을 초기화
			
			if (result == 1) { // 좌석초기화 성공
				JOptionPane.showMessageDialog(null, "퇴실하였습니다.", "퇴실", JOptionPane.INFORMATION_MESSAGE);
				frame.setEnabled(true);

			} else { // 좌석 초기화 실패
				JOptionPane.showMessageDialog(null, "퇴실처리 에러\n관리자에게 문의하세요.", "Error", JOptionPane.ERROR_MESSAGE);
				frame.setEnabled(true);
			}
			
		} else { // 예상퇴실 시간보다 실제 퇴실 시간이 늦을때 - 추가 결제(30분당 1000원, 3시간까지)
			System.out.println("추가 금액 결제");
			long diffTime = (curTime - expectTime) / 30; // 초과한 시간을 30분으로 나눠서 추가 결제 금액 계산
			long repay = (diffTime * 1000);
			System.out.println(repay + "원");
			RePaymentDialog repaymemt = new RePaymentDialog(frame, deskNumber, repay);
			repaymemt.setVisible(true);

//			curTime
		}

	} // end compareToTime()
} // end CompareToTime
