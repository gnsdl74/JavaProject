package edu.java.studyroom;

import javax.swing.*;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FindPwDialog extends JDialog {

	private final JPanel findPwPanel;
	private JTextField textFieldId;
	private JTextField textFieldRegistNum;

	private StudyRoomDAO dao;

	public FindPwDialog() {
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		dao = StudyRoomDAOImple.getInstance();

		findPwPanel = new JPanel();
		findPwPanel.setBackground(Color.WHITE);
		setModal(true);
		setResizable(false);
		setTitle("PW 찾기");
		setBounds(100, 100, 274, 188);
		getContentPane().setLayout(null);
		setLocationRelativeTo(findPwPanel);
		findPwPanel.setBounds(0, 0, 270, 122);
		getContentPane().add(findPwPanel);
		findPwPanel.setLayout(null);
		{
			JLabel lblRenum = new JLabel("주민등록번호 :");
			lblRenum.setHorizontalAlignment(SwingConstants.CENTER);
			lblRenum.setFont(new Font("굴림", Font.PLAIN, 12));
			lblRenum.setBounds(12, 70, 95, 20);
			findPwPanel.add(lblRenum);
		}
		{
			textFieldId = new JTextField("아이디 입력");
			textFieldId.setFont(new Font("굴림", Font.PLAIN, 12));
			textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldId.setForeground(Color.GRAY);
			textFieldId.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (textFieldId.getText().isEmpty()) {
						textFieldId.setForeground(Color.GRAY);
						textFieldId.setText("아이디 입력");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (textFieldId.getText().equals("아이디 입력")) {
						textFieldId.setText("");
						textFieldId.setForeground(Color.BLACK);
					}
				}
			});
			textFieldId.setBounds(111, 28, 120, 25);
			findPwPanel.add(textFieldId);
			textFieldId.setColumns(10);
		}
		{
			textFieldRegistNum = new JTextField("주민등록번호 입력");
			textFieldRegistNum.setFont(new Font("굴림", Font.PLAIN, 12));
			textFieldRegistNum.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldRegistNum.setForeground(Color.GRAY);
			textFieldRegistNum.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (textFieldRegistNum.getText().isEmpty()) {
						textFieldRegistNum.setForeground(Color.GRAY);
						textFieldRegistNum.setText("주민등록번호 입력");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (textFieldRegistNum.getText().equals("주민등록번호 입력")) {
						textFieldRegistNum.setText("");
						textFieldRegistNum.setForeground(Color.BLACK);
					}
				}
			});
			textFieldRegistNum.setColumns(10);
			textFieldRegistNum.setBounds(111, 68, 120, 25);
			findPwPanel.add(textFieldRegistNum);
		}
		{
			JLabel lblId = new JLabel("아이디 :");
			lblId.setHorizontalAlignment(SwingConstants.CENTER);
			lblId.setFont(new Font("굴림", Font.PLAIN, 12));
			lblId.setBounds(55, 30, 45, 20);
			findPwPanel.add(lblId);
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBounds(0, 121, 270, 40);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton findIdButton = new JButton("확인");
				findIdButton.setFont(new Font("굴림", Font.PLAIN, 12));
				findIdButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = textFieldId.getText();
						String registNum = textFieldRegistNum.getText();

						if (id.equals("") || registNum.equals("") || id.equals("이름 입력")
								|| registNum.equals("주민등록번호 입력")) {
							JOptionPane.showMessageDialog(null, "ID와 주민등록번호를 모두 입력해주세요.", "입력",
									JOptionPane.WARNING_MESSAGE);
						} else {
							int result = dao.findPw(id, registNum);
							if (result == 1) {
								JOptionPane.showMessageDialog(null, "비밀번호가  1234로 초기화되었습니다.", "비밀번호 초기화",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "ID와 주민등록번호를 정확히 입력해주세요.", "경고",
										JOptionPane.WARNING_MESSAGE);
							}
						}

					}
				});
				findIdButton.setBounds(118, 10, 65, 23);
				buttonPane.add(findIdButton);
				getRootPane().setDefaultButton(findIdButton);
			}
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.setFont(new Font("굴림", Font.PLAIN, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(193, 10, 65, 23);
				buttonPane.add(cancelButton);
			}
		}
	}

} // end FindId
