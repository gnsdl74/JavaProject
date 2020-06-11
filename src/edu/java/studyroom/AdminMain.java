package edu.java.studyroom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminMain {

	private JFrame frame;
	
	
	public AdminMain() {
		initialize();
	}
	

	private void initialize() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("관리자페이지");
		frame.getContentPane().setBackground(Color.darkGray); // 배경색 변경
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // 창이 가운데에 나오도록 지정
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("관리자페이지");
		lblNewLabel.setPreferredSize(new Dimension(75, 15));
		lblNewLabel.setBounds(12, 10, 460, 62);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 35));
		lblNewLabel.setForeground(Color.WHITE); // 폰트색 변경
		frame.getContentPane().add(lblNewLabel);
		
		Font btnFont = new Font("굴림", Font.BOLD, 24);
		JButton btnMemInfo = new JButton("회원 정보");
		btnMemInfo.addActionListener(new ActionListener() { // 회원 정보 버튼을 눌렀을 때
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MenuJTableExam menu = new MenuJTableExam(); // 관리자 메뉴창 연결
				menu.setLocationRelativeTo(null);
			}
		});
		btnMemInfo.setBounds(79, 299, 143, 46);
		btnMemInfo.setFont(btnFont);
		btnMemInfo.setBackground(Color.BLACK); // 회원 정보 버튼색 변경
		btnMemInfo.setForeground(Color.WHITE); // 회원 정보 폰트색 변경
		frame.getContentPane().add(btnMemInfo);
		
		JButton btnChange = new JButton("좌석 정보");
		btnChange.addActionListener(new ActionListener() { // 좌석 정보 버튼을 눌렀을 때
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("좌석 정보 버튼 클릭");
				// TODO Auto-generated method stub
				DeskGUI desk = new DeskGUI(); // 좌석 정보창 출력
				
			}
		});
		btnChange.setBounds(282, 299, 143, 46);
		btnChange.setFont(btnFont);
		btnChange.setBackground(Color.BLACK); // 좌석 변경 버튼색 변경
		btnChange.setForeground(Color.WHITE); // 좌석 변경 폰트색 변경
		frame.getContentPane().add(btnChange);
		
		JLabel label = new JLabel(); // 이미지를 넣을 라벨 생성
		label.setIcon(new ImageIcon("img/image1.png"));
		label.setBounds(134, 73, 225, 200);
		frame.getContentPane().add(label);
		
		
	}
}
