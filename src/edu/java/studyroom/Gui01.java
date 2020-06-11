package edu.java.studyroom;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import edu.java.dao.ProjectDAO;
import edu.java.dao.ProjectDAOimple;
import edu.java.vo.ProjectVO;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

public class Gui01 extends JDialog{

	private static JPanel contentPane;
	private JTextField textField, UpID, UpPW, UpName, UpRenumber, PW;
	private static ProjectDAO dao;
	private static JTextArea SelectSee;
	private static int OneExit = 0;
	private static JFrame newWin = new JFrame();

	public Gui01(String id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) { // 로그인 첫화면
		setModal(true);
		dao = ProjectDAOimple.getinstance();
		
		SelectSee = new JTextArea();
		UpID = new JTextField();
		UpPW = new JTextField();
		UpName = new JTextField();
		UpRenumber = new JTextField();
		contentPane = new JPanel();

		setBounds(100, 100, 500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setLocationRelativeTo(contentPane);
		
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("ID를 입력하시오.");
		lblNewLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		lblNewLabel.setBounds(160, 10, 169, 76);
		contentPane.add(lblNewLabel);

		textField = new JTextField("ID");// ID입력 텍스트
		/*---------------------------------------------*/
		textField.setForeground(Color.GRAY);
		textField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			if (textField.getText().isEmpty()) {
				textField.setForeground(Color.GRAY);
				textField.setText("ID");
			}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals("ID")) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
				
			}
		});
		textField.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		textField.setBounds(12, 123, 460, 76);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton Update = new JButton("수정");
		Update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				UpdateNewWindow(id);
				
			}
		});

		Update.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		Update.setBounds(12, 209, 213, 66);
		contentPane.add(Update);

		JButton secession = new JButton("탈퇴");
		secession.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String a = textField.getText();
				if (a.equals("")) {
					JOptionPane.showMessageDialog(null, "로그인한 아이디와 같은 아이디를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
				} else {
					/*-----------------------------------------------------*/
					if (id.equals(textField.getText())) {
						dispose();
						DeleteW dw = new DeleteW(textField.getText());
						dw.setVisible(true);
						
					} else {
						JOptionPane.showMessageDialog(null, "로그인한 아이디와 같은 아이디를 입력해주세요.", "정보",
								JOptionPane.INFORMATION_MESSAGE);
					}
					/*-----------------------------------------------------*/
				}
			}
		});
		secession.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		secession.setBounds(259, 209, 213, 66);
		contentPane.add(secession);

		JButton Inroom = new JButton("입실");
		Inroom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String loginID = id;
				String Loginboolean = dao.InRoomCut(loginID);

				if (Loginboolean.equals(loginID)) {
					JOptionPane.showMessageDialog(contentPane, "입실되어있는 ID입니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Inroom inroom = new Inroom(id);
					inroom.setVisible(true);
//					dispose();
				}

			}// end Override
		});
		Inroom.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		Inroom.setBounds(12, 285, 213, 66);
		contentPane.add(Inroom);

		JButton Outroom = new JButton("퇴실");
		Outroom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					dispose();
					Outroom outroom = new Outroom(id);
					outroom.setVisible(true);
				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(contentPane, "입실되어있지 않은 ID입니다.", "정보",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}// end actionPerformed
		});
		Outroom.setFont(new Font("함초롬돋움", Font.PLAIN, 22));
		Outroom.setBounds(259, 285, 213, 66);
		contentPane.add(Outroom);

		JLabel Message = new JLabel("입실/퇴실은 ID입력을 하지 않아도 됩니다.");
		Message.setForeground(Color.RED);
		Message.setFont(new Font("휴먼매직체", Font.PLAIN, 20));
		Message.setBounds(82, 71, 317, 42);
		contentPane.add(Message);
	}// end initialize()

	private void UpdateNewWindow(String id) { // ID를 입력했을시 같은 ID가 존재하는지 확인 맨 처음 확인 받는곳
		System.out.println("UpdateNewWindow()");
		
		try {
			String id_1 = textField.getText();
			System.out.println(id_1);
			ProjectVO project = dao.select(id_1);
			
			if (id.equals(textField.getText())) {
				UpdateWindow Update = new UpdateWindow(project.toString(),project.getName(),project.getrenumber(),id);
				Update.setVisible(true);
				
			} else {
				JOptionPane.showMessageDialog(null, "로그인한 아이디와 같은 아이디를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "로그인한 아이디와 같은 아이디를 입력해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
		}

	}// end UpdateNewWindow()

}// end Gui01
