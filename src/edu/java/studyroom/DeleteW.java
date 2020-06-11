package edu.java.studyroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import edu.java.dao.ProjectDAO;
import edu.java.dao.ProjectDAOimple;

public class DeleteW extends JDialog {

	private static ProjectDAO dao;
	public DeleteW(String textID) {
		dao = ProjectDAOimple.getinstance();
		JPanel jp = new JPanel();
		
		setModal(true);
		setBounds(100, 100, 500, 300);
		setTitle("탈퇴 창");
//		setVisible(true);

		setLocationRelativeTo(null);
		
		getContentPane().add(jp);
		JLabel PWinsert = new JLabel("PW를 입력하시오.");
		PWinsert.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		PWinsert.setSize(300, 35);
		PWinsert.setLocation(160, 90);
		getContentPane().add(PWinsert);

		JTextPane notice = new JTextPane();
		notice.setForeground(Color.RED);
		notice.setBackground(UIManager.getColor("Button.background"));
		notice.setEditable(false);
		notice.setFont(new Font("함초롬돋움", Font.PLAIN, 20));
		notice.setSize(370, 55);
		notice.setLocation(70, 20);
		notice.setText("* 회원 탈퇴시 저장되있던 시간은 복구가 \n 불가능하오니 참고해주시기 바랍니다.");
		getContentPane().add(notice);

		getContentPane().setLayout(null);
		JTextField PW = new JTextField();
		PW.setSize(300, 35);
		PW.setLocation(100, 150);
		getContentPane().add(PW);

		JButton deleteButton = new JButton("탈퇴하기");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String a = PW.getText();
				boolean b = a.equals("");
				if (b) {
					JOptionPane.showMessageDialog(null, "PW를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
				} else {
					
					
					String o = PW.getText();
					System.out.println("비밀번호 : " + o);

					String project = dao.PwCheck(textID);
					System.out.println(project);
					String txt = "실패, ID혹은 PW를 다시 확인해주시요.";

//					String CheckID = ID;

					String secession = dao.InRoomCut(textID);

					if (secession.equals(textID)) {
						JOptionPane.showMessageDialog(null, "입실 중에 회원탈퇴 할 수 없습니다. 퇴실 후 이용해주세요.", "정보",
								JOptionPane.INFORMATION_MESSAGE);
					} else {

						if (project.equals(o)) {
							int dele = JOptionPane.showConfirmDialog(null, "탈퇴하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);

							if (dele == JOptionPane.YES_OPTION) {

								String number = textID;
								int result = dao.delete(number);

								if (result > 0) {
									JOptionPane.showMessageDialog(null, "삭제되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, txt, "정보", JOptionPane.INFORMATION_MESSAGE);
								}

							} else {
								JOptionPane.showMessageDialog(null, "취소되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
								dispose();
								Gui01 gui01 = new Gui01(textID);
								gui01.setVisible(true);
								
							}
						} else {
							JOptionPane.showMessageDialog(null, txt, "정보", JOptionPane.INFORMATION_MESSAGE);

						}
					}
					
				}
			}
		});
		deleteButton.setSize(100, 50);
		deleteButton.setLocation(200, 200);
		getContentPane().add(deleteButton);
	}
}
