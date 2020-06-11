package edu.java.studyroom;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import edu.java.dao.ProjectDAO;
import edu.java.dao.ProjectDAOimple;
import edu.java.vo.OutRoomVO;

public class Outroom extends JDialog{
	
		private final JPanel contentPanel = new JPanel();
		private static ProjectDAO dao;
		
	public Outroom(String id) {
		setModal(true);
		dao = ProjectDAOimple.getinstance();
		OutRoomVO deskDB = dao.OutText(id);
//		System.out.println(deskDB.getDesknumber());
//		System.out.println(deskDB.getId());
//		System.out.println(deskDB.getTimepart());
		
		setTitle("퇴실");
		setBounds(100,100,550,400);
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(contentPanel);
		
		JLabel NoticeLabel = new JLabel("퇴실하시겠습니까?");
		NoticeLabel.setForeground(Color.BLUE);
		NoticeLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		NoticeLabel.setBounds(95, 10, 245, 60);
		getContentPane().add(NoticeLabel);
		
		JLabel SeatLabel = new JLabel("좌석");
		SeatLabel.setForeground(Color.MAGENTA);
		SeatLabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		SeatLabel.setBounds(46, 80, 48, 53);
		getContentPane().add(SeatLabel);
		
		JTextArea SeatArea = new JTextArea();
		SeatArea.setBackground(UIManager.getColor("Button.background"));
		SeatArea.setEditable(false);
		SeatArea.setText(deskDB.getDesknumber());
		SeatArea.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 50));
		SeatArea.setBounds(40, 132, 65, 71);
		getContentPane().add(SeatArea);
		
		JLabel IDlabel = new JLabel("아이디");
		IDlabel.setForeground(Color.MAGENTA);
		IDlabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		IDlabel.setBounds(12, 242, 72, 42);
		getContentPane().add(IDlabel);
		
		JTextArea IDArea = new JTextArea();
		IDArea.setBackground(UIManager.getColor("Button.background"));
		IDArea.setEditable(false);
		IDArea.setText(deskDB.getId());
		IDArea.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 33));
		IDArea.setBounds(95, 239, 203, 42);
		getContentPane().add(IDArea);
		
		JButton YesButton = new JButton("예");
		YesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OutYes(id);
			}
		});
		YesButton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 25));
		YesButton.setBounds(142, 291, 116, 60);
		getContentPane().add(YesButton);
		
		JButton Nobutton = new JButton("아니요");
		Nobutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OutNo();
				Gui01 gui01 = new Gui01(id);
				gui01.setVisible(true);
			}
		});
		Nobutton.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 25));
		Nobutton.setBounds(295, 291, 116, 60);
		getContentPane().add(Nobutton);
		
		JLabel Timelabel = new JLabel("시간");
		Timelabel.setForeground(Color.MAGENTA);
		Timelabel.setFont(new Font("함초롬돋움", Font.PLAIN, 25));
		Timelabel.setBounds(165, 80, 48, 53);
		getContentPane().add(Timelabel);
		
		JTextArea TimeArea = new JTextArea();
		TimeArea.setBackground(UIManager.getColor("Button.background"));
		TimeArea.setEditable(false);
		TimeArea.setText(deskDB.getTimepart());
		TimeArea.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 50));
		TimeArea.setBounds(160, 132, 60, 71);
		getContentPane().add(TimeArea);
		
		JLabel lblNewLabel = new JLabel("잠 깐!!");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 35));
		lblNewLabel.setBounds(305, 99, 116, 60);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setBounds(413, 80, 109, 79);
		getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("img/Stop1.png"));
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(UIManager.getColor("Button.background"));
		textPane.setForeground(Color.BLUE);
		textPane.setEditable(false);
		textPane.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		textPane.setBounds(292, 169, 230, 60);
		getContentPane().add(textPane);
		contentPanel.setLayout(null);
		textPane.setText("잊으신 물건이 없는지 다시한번 확인바랍니다.");
		
	}// end Outroom()
	
		public void OutYes(String id) {
			System.out.println("아이디 : "+ id);
			
			int result = dao.OutUpDate("0", null, id);
			if(result > 0) {
				JOptionPane.showMessageDialog(null, "퇴실되었습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "실패.", "정보", JOptionPane.INFORMATION_MESSAGE);
				
			}
		}// end OutYes
		
		public void OutNo() {
			dispose();
		}
	}// end JDialog()
