package edu.java.studyroom;


import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextPane;

import edu.java.dao.ProjectDAO;
import edu.java.dao.ProjectDAOimple;



public class InButtonWindow extends JDialog {
	
	private static ProjectDAO dao;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	
	
	public InButtonWindow(String id, String time,String number) {
		setModal(true);
		
		setDefaultCloseOperation(InButtonWindow.DISPOSE_ON_CLOSE);
		dao = ProjectDAOimple.getinstance();
		
		setTitle("좌석");
		setBounds(100, 100, 600, 520);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		
		
		setLocationRelativeTo(contentPanel);

		JLabel Maseege = new JLabel("시간 입력");
		Maseege.setFont(new Font("함초롬바탕", Font.PLAIN, 25));
		Maseege.setBounds(264, 350, 120, 40);
		getContentPane().add(Maseege);

		textField = new JTextField();
		textField.setBounds(382, 350, 64, 40);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton PaymentButton = new JButton("결제하기");
		PaymentButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				paybutton(id, time, number);
				System.out.println(number);
			}
		});
		PaymentButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 25));
		PaymentButton.setBounds(382, 400, 190, 71);
		getContentPane().add(PaymentButton);

		JLabel lblNewLabel = new JLabel("* 시간은 1시간 ~ 12시간까지 입력가능합니다.");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 18));
		lblNewLabel.setBounds(12, 421, 358, 50);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("회원 이용시간 가격표");
		lblNewLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.MAGENTA);
		lblNewLabel_1.setBounds(12, 10, 275, 62);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("1시간 : 1000원");
		lblNewLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(12, 82, 200, 40);
		getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("3시간 : 3000원");
		label.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		label.setBounds(12, 132, 200, 40);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("6시간 : 5000원");
		label_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		label_1.setBounds(12, 182, 200, 40);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("9시간 : 8000원");
		label_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		label_2.setBounds(12, 232, 200, 40);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("12시간 : 10000원");
		label_3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		label_3.setBounds(12, 282, 233, 40);
		getContentPane().add(label_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setForeground(Color.BLACK);
		textPane.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 18));
		textPane.setText("1. 다른 사람을 생각해서 자리는 소중히 써주세요. \n \n2. 너무 심한 소음은 1번 경고 후 퇴실 조치 취하도록 하겠습니다."
				+ "\n\n3. 음식은 냄새가 너무 많이 나므로 식당에서 섭취해주세요.(간단한 음료나 초코릿등 냄새가 안나는건 가능)");
		textPane.setBounds(264, 109, 308, 231);
		getContentPane().add(textPane);
		
		JLabel lblNewLabel_3 = new JLabel("※ 이용시 주의사항 ※");
		lblNewLabel_3.setForeground(Color.RED);
		lblNewLabel_3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(300, 59, 250, 40);
		getContentPane().add(lblNewLabel_3);

	}// end InbuttonWindow

	private void paybutton(String id, String time, String number) {
		String TimeText = textField.getText();
		System.out.println(id);
		try {
			String TimeText1 = textField.getText();
			int i = Integer.parseInt(TimeText1);
			if(i == 1 || i == 3 || i == 6 || i == 9 || i == 12) {
				
				int dele = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				
				if (dele == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					
					int result = dao.InRoom(id,textField.getText(),number);
					System.out.println(result);
					
					textField.setText("");
					dispose();
					
				} else {
					JOptionPane.showMessageDialog(null, "취소되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "3시간 단위만 결제 가능합니다. 가격표를 확인해주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (NumberFormatException e) {
			
		
		if (TimeText.equals("")) {
			JOptionPane.showMessageDialog(null, "결제할 시간을 적어주세요.", "정보", JOptionPane.INFORMATION_MESSAGE);
		}
		
		}// end NumberFormatException

	}// end paybutton()
}// end JDialog
