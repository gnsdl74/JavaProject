package edu.java.studyroom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class EnterSeatFrame extends JFrame {
	private JPanel enterSeatPanel;
	private JTextField exitInfo, exit;
	private ButtonGroup deskGroup;

	private JLabel lblChoose2;
	private JMenuBar menuBar;
	private JMenu mnMove;
	private JMenuItem mntmOutDesk;
	private JTextField textField;
	private JTextField textField_1;
	
	public EnterSeatFrame(JFrame frame) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		getContentPane().setBackground(Color.WHITE);
		final ImageIcon one = new ImageIcon("img/one.png");
		final ImageIcon two = new ImageIcon("img/two.png");
		final ImageIcon three = new ImageIcon("img/three.png");
		final ImageIcon four = new ImageIcon("img/four.png");
		final ImageIcon five = new ImageIcon("img/five.png");
		
		frame.setEnabled(false);
		
		setTitle("좌석표");
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 790, 610);
		getContentPane().setLayout(null);

		enterSeatPanel = new JPanel();
		enterSeatPanel.setBackground(Color.WHITE);
		setLocationRelativeTo(enterSeatPanel);
		enterSeatPanel.setBounds(0, 0, 400, 517);
		enterSeatPanel.setLayout(null);
		getContentPane().add(enterSeatPanel);
		{
			JLabel lblSeat = new JLabel("좌 석 배 치 표");
			lblSeat.setFont(new Font("맑은 고딕", Font.BOLD, 30));
			lblSeat.setHorizontalAlignment(SwingConstants.CENTER);
			lblSeat.setBounds(100, 30, 200, 34);
			enterSeatPanel.add(lblSeat);
		}

		JPanel seatLocation = new JPanel();
		seatLocation.setBackground(Color.WHITE);
		seatLocation.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		seatLocation.setBounds(12, 85, 375, 420);
		enterSeatPanel.add(seatLocation);
		seatLocation.setLayout(null);
		
		{
			deskGroup = new ButtonGroup();
			
			SittingList sittingList = new SittingList(enterSeatPanel, seatLocation, 12);
			// 좌석을 표현할 Panel과 좌석 수를 매개 변수로 전달.
			repaint();
			revalidate();
			deskGroup = sittingList.getSeatGroup();
			
			
		} // end 토글 버튼들과 출입문을 생성하는 클래스 호출
		
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setBackground(Color.WHITE);
			buttonPanel.setLayout(null);
			buttonPanel.setBounds(0, 517, 784, 44);
			getContentPane().add(buttonPanel);

			JButton payBtn = new JButton("결제");
			payBtn.setFont(new Font("굴림", Font.PLAIN, 12));
			payBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Enumeration<AbstractButton> buttons = null;
					for (buttons = deskGroup.getElements(); buttons.hasMoreElements();) {
						AbstractButton button = buttons.nextElement();

						if (button.isSelected()) {
							PaymentDialog payment = new PaymentDialog(frame, button.getText());
							payment.setVisible(true);
							dispose();
						}
					} // end for
				}
			});
			payBtn.setBounds(616, 10, 65, 23);
			buttonPanel.add(payBtn);
			getRootPane().setDefaultButton(payBtn);
			// end outBtn - 퇴실 확인

			JButton cancelBtn = new JButton("취소");
			cancelBtn.setFont(new Font("굴림", Font.PLAIN, 12));
			cancelBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.setEnabled(true);
					dispose();
				}
			});
			cancelBtn.setBounds(693, 10, 65, 23);
			buttonPanel.add(cancelBtn);
			// end cancelBtn - 취소
		}

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setLayout(null);
		infoPanel.setBounds(400, 0, 384, 517);
		getContentPane().add(infoPanel);

		JLabel info = new JLabel("알 림 사 항");
		info.setForeground(Color.RED);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		info.setBounds(130, 35, 135, 25);
		infoPanel.add(info);

		JLabel tel = new JLabel("이용 불편 문의");
		tel.setHorizontalAlignment(SwingConstants.CENTER);
		tel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tel.setBounds(70, 447, 108, 25);
		infoPanel.add(tel);

		JLabel telNum = new JLabel("☎ 02-345-6789");
		telNum.setForeground(Color.BLUE);
		telNum.setHorizontalAlignment(SwingConstants.CENTER);
		telNum.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		telNum.setBounds(177, 446, 135, 25);
		infoPanel.add(telNum);

		exitInfo = new JTextField();
		exitInfo.setText("EXIT");
		exitInfo.setHorizontalAlignment(SwingConstants.CENTER);
		exitInfo.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		exitInfo.setEditable(false);
		exitInfo.setColumns(10);
		exitInfo.setBackground(new Color(0, 128, 0));
		exitInfo.setBounds(53, 481, 60, 25);
		infoPanel.add(exitInfo);

		exit = new JTextField();
		exit.setBorder(null);
		exit.setText(" : 출입구");
		exit.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		exit.setEditable(false);
		exit.setColumns(10);
		exit.setBackground(Color.WHITE);
		exit.setBounds(118, 481, 60, 25);
		infoPanel.add(exit);

		JLabel lblChoose = new JLabel("1인당 1 좌석만 선택가능힙니다.");
		lblChoose.setForeground(Color.BLACK);
		lblChoose.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblChoose.setBounds(73, 84, 277, 25);
		infoPanel.add(lblChoose);
		
		lblChoose2 = new JLabel("※ 좌석을 선택하지 않으면 결제가 진행되지 않습니다.");
		lblChoose2.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoose2.setForeground(Color.RED);
		lblChoose2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		lblChoose2.setBounds(12, 415, 360, 25);
		infoPanel.add(lblChoose2);
		
		JPanel onePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(one.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		onePanel.setBackground(Color.WHITE);
		onePanel.setBounds(12, 65, 50, 50);
		infoPanel.add(onePanel);
		
		JPanel twoPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(two.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		twoPanel.setBackground(Color.WHITE);
		twoPanel.setBounds(12, 135, 50, 50);
		infoPanel.add(twoPanel);
		
		JPanel threePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(three.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		threePanel.setBackground(Color.WHITE);
		threePanel.setBounds(12, 205, 50, 50);
		infoPanel.add(threePanel);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(Color.BLACK));
		textField.setForeground(Color.DARK_GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(SystemColor.menu);
		textField.setBounds(200, 481, 60, 25);
		infoPanel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setText(" : 사용 중");
		textField_1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(270, 481, 60, 25);
		infoPanel.add(textField_1);
		
		JPanel fourPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(four.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		fourPanel.setBackground(Color.WHITE);
		fourPanel.setBounds(12, 275, 50, 50);
		infoPanel.add(fourPanel);
		
		JPanel fivePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(five.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		fivePanel.setBackground(Color.WHITE);
		fivePanel.setBounds(12, 345, 50, 50);
		infoPanel.add(fivePanel);
		
		JLabel label = new JLabel("결제 후에 임시 ID가 발급됩니다. (퇴실 시 사용)");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label.setBounds(73, 151, 299, 25);
		infoPanel.add(label);
		
		JLabel label_1 = new JLabel("퇴실 시간이 지나면 연체료가 발생합니다.");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_1.setBounds(70, 205, 250, 25);
		infoPanel.add(label_1);
		
		JLabel label_2 = new JLabel("좌석 변경 시 관리자에게 문의하세요.");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_2.setBounds(70, 291, 277, 25);
		infoPanel.add(label_2);
		
		JLabel label_3 = new JLabel("화장실은 출입구 바깥 좌측에 있습니다.");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_3.setBounds(70, 359, 277, 25);
		infoPanel.add(label_3);
		
		JLabel label_4 = new JLabel("(30분마다 1000원)");
		label_4.setForeground(Color.BLACK);
		label_4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		label_4.setBounds(70, 230, 277, 19);
		infoPanel.add(label_4);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(220, 220, 220));
		setJMenuBar(menuBar);
		
		mnMove = new JMenu("Menu");
		menuBar.add(mnMove);
		
		mntmOutDesk = new JMenuItem("퇴실");
		mntmOutDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OutSeatDialog outDesk = new OutSeatDialog(frame);
				outDesk.setVisible(true);
				dispose();
			}
		});
		mnMove.add(mntmOutDesk);
		
	} // end InDesk - 생성자
} // end InDesk
