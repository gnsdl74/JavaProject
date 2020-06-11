package edu.java.studyroom;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import edu.java.dao.ProjectDAO;
import edu.java.dao.ProjectDAOimple;
import edu.java.vo.ProjectVO;

public class UpdateWindow extends JDialog {
	private JTextField textField, UpID, UpPW, UpName, UpRenumber, PW;
	private static JTextArea SelectSee;
	private static ProjectDAO dao;
	
	private final JPanel contentPanel = new JPanel();
	
	public UpdateWindow(String Area, String AreaID, String AreaRenumber, String loginId) {
		setModal(true);
		dao = ProjectDAOimple.getinstance();
		
		setTitle("수정 창");
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		
		setLocationRelativeTo(contentPanel);

		JButton UpdateButton = new JButton("수정하기");
		UpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				FinalUpdate(loginId);
				
			}
		});
		UpdateButton.setBounds(190, 380, 100, 60);
		getContentPane().add(UpdateButton);

		JLabel lblNewPW = new JLabel("수정할 PW 입력");
		lblNewPW.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		lblNewPW.setBounds(10,220,300,35);
		getContentPane().add(lblNewPW);

		JLabel lblNewName = new JLabel("수정할 이름 입력");
		lblNewName.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		lblNewName.setSize(300, 35);
		lblNewName.setLocation(10, 270);
		getContentPane().add(lblNewName);

		JLabel lblNewHP = new JLabel("수정할 renumber 입력");
		lblNewHP.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		lblNewHP.setSize(300, 35);
		lblNewHP.setLocation(10, 320);
		getContentPane().add(lblNewHP);

		JLabel lblsaveid = new JLabel("회원 정보");
		lblsaveid.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		lblsaveid.setSize(300, 35);
		lblsaveid.setLocation(100, 10);
		getContentPane().add(lblsaveid);

		UpPW = new JTextField();
		UpPW.setSize(200, 35); // 수정할 비밀번호 입력 Text
		UpPW.setLocation(250, 220);
		getContentPane().add(UpPW);
		
		UpName = new JTextField();
		UpName.setSize(200, 35); // 수정할 이름 입력 Text
		UpName.setLocation(250, 270);
		getContentPane().add(UpName);
		
		UpRenumber = new JTextField();
		UpRenumber.setSize(200, 35); // 수정할 주민번호 입력 Text
		UpRenumber.setLocation(250, 320);
		getContentPane().add(UpRenumber);
		
		SelectSee = new JTextArea();
		SelectSee.setSize(320, 140);// 저장되있는 DB회원 정보를 표시해 주는 창
		SelectSee.setBackground(UIManager.getColor("Button.background"));
		SelectSee.setEditable(false);
		SelectSee.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		SelectSee.setLocation(10, 50);
		getContentPane().add(SelectSee);
		
		JLabel security = new JLabel("ddd");
		security.setSize(130, 140);
		security.setLocation(340, 50);
		getContentPane().add(security);
		security.setIcon(new ImageIcon("img/security_1.png"));
		
		JLabel security1 = new JLabel("※ 보안에 주의해 주세요 ※");
		security1.setForeground(Color.RED);
		security1.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		security1.setSize(250, 44);
		security1.setLocation(30, 180);
		getContentPane().add(security1);
		
		SelectSee.setText(Area);
		UpName.setText(AreaID);
		UpRenumber.setText(AreaRenumber);
		
	}// end UpdateWindow()
	
	private void FinalUpdate(String LoginID) { // 수정 데이터 입력 후 최종 완료 창
		dao = ProjectDAOimple.getinstance();
		String id = LoginID;
		String pw = UpPW.getText();
		String name = UpName.getText();
		String renumber = UpRenumber.getText();
		String regExp = "(?=.*\\d{1,})(?=.*[~`!@#$%\\^&*()-+=]{1,})(?=.*[a-zA-Z]{2,}).{8,20}$";
		// 숫자와 특수문자는 최소한 1개 이상, 영문은 최소한 2개 이상을 사용하여 8~20자리의 비밀번호 입력
		boolean check = Pattern.matches(regExp, pw);

		if (pw.equals("")) {
			JOptionPane.showMessageDialog(null, "pw를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
		} else if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "name를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
		} else if (renumber.equals("")) {
			JOptionPane.showMessageDialog(null, "renumber를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
		} else {

			if (check == false) {

				JOptionPane.showMessageDialog(null, "숫자와 특수문자는 최소한 1개 이상, 영문은 최소한 2개 이상을 사용하여 8~20자리의 비밀번호 입력해주세요.",
						"정보", JOptionPane.INFORMATION_MESSAGE);
			} else {
				regExp = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$";
				boolean check_1 = Pattern.matches(regExp, renumber);
				if (check_1 == false) {
					JOptionPane.showMessageDialog(null, "주민번호는 '-' 로 구분해서 13자리로 입력해주세요.\\n(생년월일-7자리)로 해주세요.", "정보",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "정보수정이 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					ProjectVO vo = new ProjectVO(id, pw, name, renumber);
					dao.update(id, vo);
					TextReset();
					dispose();
					
				}

			} // end pw check else

		} // end number입력 else

	}// end FinalUpdate()
	
	private void TextReset() {
		UpPW.setText("");
		UpName.setText("");
		UpRenumber.setText("");
	}

}// end JDialog
