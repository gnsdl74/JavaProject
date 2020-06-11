package edu.java.studyroom;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class AboutDialog extends JDialog {
	private final JPanel aboutPanel;
	
	public AboutDialog() {
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		
		setModal(true);
		getContentPane().setLayout(null);
		setBounds(100, 100, 400, 200);
		setResizable(false);
		
		aboutPanel = new JPanel();
		aboutPanel.setBackground(Color.WHITE);
		setTitle("Developers");
		setLocationRelativeTo(aboutPanel);
		aboutPanel.setBounds(0, 0, 394, 171);
		aboutPanel.setLayout(null);
		getContentPane().add(aboutPanel);
		
		JButton okBtn = new JButton("확인");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okBtn.setFont(new Font("돋움", Font.PLAIN, 13));
		okBtn.setBounds(157, 122, 70, 30);
		aboutPanel.add(okBtn);
		
		JLabel lblThanks = new JLabel("Thanks to :");
		lblThanks.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblThanks.setBounds(30, 25, 120, 30);
		aboutPanel.add(lblThanks);
		
		JLabel lblNames = new JLabel("김동현, 정기훈, 최인묵");
		lblNames.setHorizontalAlignment(SwingConstants.CENTER);
		lblNames.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNames.setBounds(87, 65, 210, 30);
		aboutPanel.add(lblNames);
	}
} // end WelCome
