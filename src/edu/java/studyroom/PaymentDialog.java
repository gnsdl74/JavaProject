package edu.java.studyroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;

public class PaymentDialog extends JDialog {
	private final JPanel seatChoosePanel, payPanel;
	private String temporaryId;
	private String seatNumber;

	private StudyRoomDAO dao;

	public PaymentDialog(JFrame frame, String deskNum) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dao = StudyRoomDAOImple.getInstance();
		System.out.println(deskNum);

		seatNumber = deskNum;
		temporaryId = (UUID.randomUUID().toString()).substring(0, 8);
		// 임시 ID 발급 8자리

		getContentPane().setLayout(null);
		setTitle("결제");
		setResizable(false);
		setBounds(100, 100, 642, 447);

		seatChoosePanel = new JPanel();
		seatChoosePanel.setBackground(Color.WHITE);
		seatChoosePanel.setBounds(0, 0, 317, 415);
		setLocationRelativeTo(rootPane);
		getContentPane().add(seatChoosePanel);
		seatChoosePanel.setLayout(null);
		{
			JLabel seatChoose = new JLabel("선택한 좌석");
			seatChoose.setBackground(Color.WHITE);
			seatChoose.setFont(new Font("맑은 고딕", Font.BOLD, 27));
			seatChoose.setHorizontalAlignment(SwingConstants.CENTER);
			seatChoose.setBounds(83, 103, 150, 50);
			seatChoosePanel.add(seatChoose);
		}

		JLabel seatNumber = new JLabel("");
		seatNumber.setForeground(Color.RED);
		seatNumber.setOpaque(true);
		seatNumber.setText(deskNum);
		seatNumber.setBackground(Color.WHITE);
		seatNumber.setHorizontalAlignment(SwingConstants.CENTER);
		seatNumber.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		seatNumber.setBounds(83, 151, 150, 80);
		seatChoosePanel.add(seatNumber);

		{
			JButton cancelButton = new JButton("취소");
			cancelButton.setBounds(108, 350, 100, 30);
			seatChoosePanel.add(cancelButton);
			cancelButton.setFont(new Font("돋움", Font.PLAIN, 13));
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EnterSeatFrame inDesk = new EnterSeatFrame(frame);
					inDesk.setVisible(true);
					dispose();
				}
			});
		} // end cancelButton
		// 선택한 좌석 정보가 표시될 Panel

		payPanel = new JPanel();
		payPanel.setBackground(Color.WHITE);
		payPanel.setBorder(new LineBorder(new Color(255, 200, 0), 3, true));
		payPanel.setBounds(317, 0, 317, 415);
		getContentPane().add(payPanel);
		payPanel.setLayout(null);
		{
			JLabel timeChoose = new JLabel("▶ 시간제 선택");
			timeChoose.setBackground(Color.WHITE);
			timeChoose.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			timeChoose.setHorizontalAlignment(SwingConstants.CENTER);
			timeChoose.setBounds(50, 25, 170, 30);
			payPanel.add(timeChoose);
		}

		JRadioButton twoHours = new JRadioButton(" 2시간 - 3000원");
		twoHours.setBackground(Color.WHITE);
		twoHours.setActionCommand("3000");
		twoHours.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		twoHours.setBounds(60, 84, 175, 20);
		payPanel.add(twoHours);

		JRadioButton fourHours = new JRadioButton(" 4시간 - 5000원");
		fourHours.setBackground(Color.WHITE);
		fourHours.setActionCommand("5000");
		fourHours.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		fourHours.setBounds(60, 136, 175, 20);
		payPanel.add(fourHours);

		JRadioButton sixHours = new JRadioButton(" 6시간 - 7000원");
		sixHours.setBackground(Color.WHITE);
		sixHours.setActionCommand("7000");
		sixHours.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		sixHours.setBounds(60, 188, 175, 20);
		payPanel.add(sixHours);

		JRadioButton nineHours = new JRadioButton(" 9시간 - 10000원");
		nineHours.setBackground(Color.WHITE);
		nineHours.setActionCommand("10000");
		nineHours.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		nineHours.setBounds(60, 240, 190, 20);
		payPanel.add(nineHours);

		JRadioButton twelveHours = new JRadioButton(" 12시간 - 13000원");
		twelveHours.setBackground(Color.WHITE);
		twelveHours.setActionCommand("13000");
		twelveHours.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		twelveHours.setBounds(60, 295, 200, 20);
		payPanel.add(twelveHours);
		// 시간제 정보가 표시될 Panel

		ButtonGroup hoursGroup = new ButtonGroup();
		hoursGroup.add(twoHours);
		hoursGroup.add(fourHours);
		hoursGroup.add(sixHours);
		hoursGroup.add(nineHours);
		hoursGroup.add(twelveHours);
		JButton cardPayButton = new JButton("카드결제");
		cardPayButton.setBounds(181, 350, 100, 30);
		payPanel.add(cardPayButton);
		cardPayButton.setFont(new Font("돋움", Font.PLAIN, 13));
		{
			JButton billPayButton = new JButton("현금결제");
			billPayButton.setBounds(50, 350, 100, 30);
			payPanel.add(billPayButton);
			billPayButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Enumeration<AbstractButton> hours = null;
					for (hours = hoursGroup.getElements(); hours.hasMoreElements();) {
						AbstractButton chooseHour = hours.nextElement();

						if (chooseHour.isSelected()) {
							billPayment(frame, chooseHour.getActionCommand());
						}
					}
				}
			});
			billPayButton.setFont(new Font("돋움", Font.PLAIN, 13));
		} // end billPayButton
		cardPayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enumeration<AbstractButton> hours = null;
				for (hours = hoursGroup.getElements(); hours.hasMoreElements();) {
					AbstractButton chooseHour = hours.nextElement();

					if (chooseHour.isSelected()) {
						cardPayment(frame, chooseHour.getActionCommand());
					}
				}
			}
		});
	} // end Payment() - 생성자

	private void billPayment(JFrame frame, String pay) {
		int check = 0;

		check = JOptionPane.showConfirmDialog(null, "결제하실 금액 : " + pay + "원 입니다.", "현금결제", JOptionPane.YES_NO_OPTION);
		if (check == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "현금으로 결제되었습니다.", "결제완료", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "임시 아이디 : " + temporaryId, "임시 아이디 발급",
					JOptionPane.INFORMATION_MESSAGE);
			String part = payToTime(pay);
			// 지불금액에 따른 시간

			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
			String startTime = sdf.format(date);
			System.out.println(startTime); // 입실 시간(date)

			int time = Integer.parseInt(part);
			System.out.println(time + cal.get(Calendar.HOUR_OF_DAY));
			int expectExitHour = time + cal.get(Calendar.HOUR_OF_DAY); // 결제한 시간이 지난 후의 예상 시간(hour)
			cal.set(Calendar.HOUR_OF_DAY, expectExitHour);
			date = cal.getTime();
			String expectExitTime = sdf.format(date);
			System.out.println(expectExitTime); // 퇴실 시간(date)
			// 현재시간과 퇴실예상시간을 계산하여 DB에 연동

			int result = dao.updateDesk(temporaryId, part, seatNumber, startTime, expectExitTime); // tId = 임시ID,
																									// seatNumber = 좌석번호
			if (result != 1) { // DB에 ID 연동이 안되었을때
				JOptionPane.showMessageDialog(null, "좌석발급 실패\n관리자에게 문의하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			frame.setEnabled(true);
			dispose();

		} else {
			JOptionPane.showMessageDialog(null, "결제 실패하였습니다.", "결제실패", JOptionPane.ERROR_MESSAGE);
		}
	} // end billPayment()

	private void cardPayment(JFrame frame, String pay) {
		int check = 0;

		check = JOptionPane.showConfirmDialog(null, "결제하실 금액 : " + pay + "원 입니다.", "카드결제", JOptionPane.YES_NO_OPTION);
		if (check == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "카드로 결제되었습니다.", "결제완료", JOptionPane.INFORMATION_MESSAGE);
			JOptionPane.showMessageDialog(null, "임시 아이디 : " + temporaryId, "임시 아이디 발급",
					JOptionPane.INFORMATION_MESSAGE);
			String part = payToTime(pay);
			// 지불금액에 따른 시간

			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
			String startTime = sdf.format(date);
			System.out.println(startTime); // 입실 시간(date)

			int time = Integer.parseInt(part);
			System.out.println(time + cal.get(Calendar.HOUR_OF_DAY));
			int expectExitHour = time + cal.get(Calendar.HOUR_OF_DAY); // 결제한 시간이 지난 후의 예상 시간(hour)
			cal.set(Calendar.HOUR_OF_DAY, expectExitHour);
			date = cal.getTime();
			String expectExitTime = sdf.format(date);
			System.out.println(expectExitTime); // 퇴실 시간(date)
			// 현재시간과 퇴실예상시간을 계산하여 DB에 연동
			// 현재시간과 퇴실예상시간을 계산하여 DB에 연동

			int result = dao.updateDesk(temporaryId, part, seatNumber, startTime, expectExitTime); // tId = 임시ID,
																									// seatNumber = 좌석번호
			if (result != 1) { // DB에 ID 연동이 안되었을때
				JOptionPane.showMessageDialog(null, "좌석발급 실패\n관리자에게 문의하세요.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			frame.setEnabled(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "결제 실패하였습니다.", "결제실패", JOptionPane.ERROR_MESSAGE);
		}
	} // end cardPayment()

	private String payToTime(String pay) {
		String time = null;

		switch (pay) {
		case "3000":
			time = "2";
			break;
		case "5000":
			time = "4";
			break;
		case "7000":
			time = "6";
			break;
		case "10000":
			time = "9";
			break;
		case "13000":
			time = "12";
			break;
		default:
			time = "0";
			break;
		}

		return time;
	} // end payToTime()

} // end Payment
