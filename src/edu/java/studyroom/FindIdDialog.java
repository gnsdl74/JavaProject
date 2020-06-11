
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

public class FindIdDialog extends JDialog {

	private final JPanel findIdPanel;
	private JTextField textFieldName;
	private JTextField textFieldRenum;

	private StudyRoomDAO dao;

	public FindIdDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		dao = StudyRoomDAOImple.getInstance();

		findIdPanel = new JPanel();
		findIdPanel.setBackground(Color.WHITE);
		setModal(true);
		setResizable(false);
		setTitle("ID 찾기");
		setBounds(100, 100, 274, 189);
		getContentPane().setLayout(null);
		setLocationRelativeTo(findIdPanel);
		findIdPanel.setBounds(0, 0, 270, 122);
		getContentPane().add(findIdPanel);
		findIdPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("이름 :");
			lblName.setFont(new Font("굴림", Font.PLAIN, 12));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setBounds(67, 30, 32, 20);
			findIdPanel.add(lblName);
		}
		{
			JLabel lblRenum = new JLabel("주민등록번호 :");
			lblRenum.setHorizontalAlignment(SwingConstants.CENTER);
			lblRenum.setFont(new Font("굴림", Font.PLAIN, 12));
			lblRenum.setBounds(12, 70, 95, 20);
			findIdPanel.add(lblRenum);
		}
		{
			textFieldName = new JTextField("이름 입력");
			textFieldName.setFont(new Font("굴림", Font.PLAIN, 12));
			textFieldName.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldName.setForeground(Color.GRAY);
			textFieldName.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (textFieldName.getText().isEmpty()) {
						textFieldName.setForeground(Color.GRAY);
						textFieldName.setText("이름 입력");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (textFieldName.getText().equals("이름 입력")) {
						textFieldName.setText("");
						textFieldName.setForeground(Color.BLACK);
					}
				}
			});
			textFieldName.setBounds(111, 28, 120, 25);
			findIdPanel.add(textFieldName);
			textFieldName.setColumns(10);
		}
		{
			textFieldRenum = new JTextField("주민등록번호 입력");
			textFieldRenum.setFont(new Font("굴림", Font.PLAIN, 12));
			textFieldRenum.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldRenum.setForeground(Color.GRAY);
			textFieldRenum.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					if (textFieldRenum.getText().isEmpty()) {
						textFieldRenum.setForeground(Color.GRAY);
						textFieldRenum.setText("주민등록번호 입력");
					}
				}

				@Override
				public void focusGained(FocusEvent e) {
					if (textFieldRenum.getText().equals("주민등록번호 입력")) {
						textFieldRenum.setText("");
						textFieldRenum.setForeground(Color.BLACK);
					}
				}
			});
			textFieldRenum.setColumns(10);
			textFieldRenum.setBounds(111, 68, 120, 25);
			findIdPanel.add(textFieldRenum);
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
						String name = textFieldName.getText();
						String registNum = textFieldRenum.getText();

						if (name.equals("") || registNum.equals("") || name.equals("이름 입력") || registNum.equals("주민등록번호 입력")) {
							JOptionPane.showMessageDialog(null, "ID와 주민등록번호를 모두 입력해주세요.", "입력",
									JOptionPane.WARNING_MESSAGE);
						} else {
							String id = dao.findId(name, registNum);
							if (id != null) {
								JOptionPane.showMessageDialog(null, "찾으려는 ID는 " + id + " 입니다.", "ID확인",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "해당하는 ID가 없습니다.", "경고", JOptionPane.ERROR_MESSAGE);
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
