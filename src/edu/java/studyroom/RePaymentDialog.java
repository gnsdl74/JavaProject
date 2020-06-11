package edu.java.studyroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;

public class RePaymentDialog extends JDialog {
	private final JPanel seatChoosePanel;

	private StudyRoomDAO dao;
	private String deskNum;
	
	public RePaymentDialog(JFrame frame, String deskNumber, long repay) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		dao = StudyRoomDAOImple.getInstance();
		deskNum = deskNumber;
		
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle("추가결제");
		setBounds(100, 100, 364, 343);

		seatChoosePanel = new JPanel();
		seatChoosePanel.setBackground(Color.WHITE);
		seatChoosePanel.setBounds(0, 0, 360, 268);
		setLocationRelativeTo(rootPane);
		getContentPane().add(seatChoosePanel);
		seatChoosePanel.setLayout(null);
		{
			JLabel lblRepayName = new JLabel("연체료 금액");
			lblRepayName.setBackground(Color.WHITE);
			lblRepayName.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
			lblRepayName.setHorizontalAlignment(SwingConstants.CENTER);
			lblRepayName.setBounds(103, 64, 148, 39);
			seatChoosePanel.add(lblRepayName);
		}

		JLabel lblRepay = new JLabel(repay + "원");
		lblRepay.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblRepay.setForeground(Color.RED);
		lblRepay.setOpaque(true);
		lblRepay.setBackground(Color.WHITE);
		lblRepay.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepay.setFont(new Font("한컴산뜻돋움", Font.BOLD, 40));
		lblRepay.setBounds(80, 113, 200, 80);
		seatChoosePanel.add(lblRepay);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBounds(0, 267, 360, 50);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton billPayButton = new JButton("현금결제");
				billPayButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						billPayment(frame);
					}
				});
				billPayButton.setFont(new Font("돋움", Font.PLAIN, 13));
				billPayButton.setBounds(20, 10, 100, 30);
				buttonPane.add(billPayButton);
			} // end billPayButton

			{
				JButton cardPayButton = new JButton("카드결제");
				cardPayButton.setFont(new Font("돋움", Font.PLAIN, 13));
				cardPayButton.setBounds(130, 10, 100, 30);
				cardPayButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						cardPayment(frame);
					}
				});
				buttonPane.setLayout(null);
				buttonPane.add(cardPayButton);
			} // end cardPayButton

			{
				JButton cancelButton = new JButton("취소");
				cancelButton.setFont(new Font("돋움", Font.PLAIN, 13));
				cancelButton.setBounds(240, 10, 100, 30);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						OutSeatDialog outSeat = new OutSeatDialog(frame);
						outSeat.setVisible(true);
					}
				});
				buttonPane.add(cancelButton);
			} // end cancelButton
		} // 회원가입과 취소가 입력될 button Pane

	} // end Payment() - 생성자

	private void billPayment(JFrame frame) {
		int check = 0;

		check = JOptionPane.showConfirmDialog(null, "현금 결제하시겠습니까?", "현금결제", JOptionPane.YES_NO_OPTION);
		if (check == JOptionPane.YES_OPTION) {
			int result = dao.updateDesk("0", null, deskNum, null, null);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "결제 완료 !\n퇴실하였습니다.", "퇴실", JOptionPane.INFORMATION_MESSAGE);
				frame.setEnabled(true);
				dispose();
			} else { // 좌석 초기화 실패
				JOptionPane.showMessageDialog(null, "퇴실처리 에러\n관리자에게 문의하세요.", "Error",
						JOptionPane.ERROR_MESSAGE);
				frame.setEnabled(true);
			}

		} else {
			JOptionPane.showMessageDialog(null, "취소하였습니다.", "결제취소", JOptionPane.ERROR_MESSAGE);
		}
	} // end billPayment()

	private void cardPayment(JFrame frame) {
		int check = 0;

		check = JOptionPane.showConfirmDialog(null, "카드 결제하시겠습니까?", "카드결제", JOptionPane.YES_NO_OPTION);
		if (check == JOptionPane.YES_OPTION) {
			int result = dao.updateDesk("0", null, deskNum, null, null);
			if (result == 1) {
				JOptionPane.showMessageDialog(null, "결제 완료 !\n퇴실하였습니다.", "퇴실", JOptionPane.INFORMATION_MESSAGE);
				frame.setEnabled(true);
				dispose();
			} else { // 좌석 초기화 실패
				JOptionPane.showMessageDialog(null, "퇴실처리 에러\n관리자에게 문의하세요.", "Error",
						JOptionPane.ERROR_MESSAGE);
				frame.setEnabled(true);
			}

		} else {
			JOptionPane.showMessageDialog(null, "취소하였습니다.", "결제취소", JOptionPane.ERROR_MESSAGE);
		}
	} // end cardPayment()

} // end RePayment
