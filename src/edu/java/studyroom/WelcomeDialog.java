package edu.java.studyroom;

import java.awt.Graphics;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class WelcomeDialog extends JDialog {
	private final JPanel welComePanel, imagePanel;
	
	public WelcomeDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(100, 100, 600, 300);
		
		final ImageIcon icon1 = new ImageIcon("img/welcome.png");
		
		welComePanel = new JPanel();
		welComePanel.setBackground(Color.WHITE);
		setTitle("WelCome");
		setResizable(false);
		setLocationRelativeTo(welComePanel);
		welComePanel.setBounds(0, 0, 594, 271);
		welComePanel.setLayout(null);
		getContentPane().add(welComePanel);
		
		imagePanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setBounds(48, 0, 500, 115);
		welComePanel.add(imagePanel);
		
		JLabel lblWelcome = new JLabel("스터디룸에 오신 것을 환영합니다 !");
		lblWelcome.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(79, 129, 440, 50);
		welComePanel.add(lblWelcome);
		
		JLabel lblEngWelcome = new JLabel("Welcome to the StudyRoom !");
		lblEngWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblEngWelcome.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		lblEngWelcome.setBounds(79, 179, 440, 30);
		welComePanel.add(lblEngWelcome);
		
		JButton okBtn = new JButton("확인");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okBtn.setFont(new Font("돋움", Font.PLAIN, 13));
		okBtn.setBounds(248, 229, 100, 30);
		welComePanel.add(okBtn);
	}
} // end WelCome
