package edu.java.studyroom;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
/*
 * 비회원 로그인 시 출력할 화면
 */
public class GuestLoginDialog extends JDialog {
	private final JPanel guestLoginPanel;
	
	public GuestLoginDialog(JFrame frame) {
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		
		guestLoginPanel = new JPanel();
		guestLoginPanel.setBackground(Color.WHITE);
		
		final ImageIcon enterIcon = new ImageIcon("img/enter.png");
		final ImageIcon studyIcon = new ImageIcon("img/study.png");
		final ImageIcon exitIcon = new ImageIcon("img/exit.png");
		final ImageIcon hurrayIcon = new ImageIcon("img/hurray.png");
		
		setModal(true);
		setResizable(false);
		setTitle("비회원 로그인");
		setBounds(100, 100, 643, 387);
		setLocationRelativeTo(guestLoginPanel);
		getContentPane().setLayout(null);
		guestLoginPanel.setBounds(0, 0, 640, 360);
		getContentPane().add(guestLoginPanel);
		guestLoginPanel.setLayout(null);
		{
			
			JPanel enterPanel = new JPanel();
			enterPanel.setBackground(Color.WHITE);
			enterPanel.setBorder(null);
			enterPanel.setBounds(35, 30, 250, 300);
			guestLoginPanel.add(enterPanel);
			enterPanel.setLayout(null);
			
			JLabel lblEnter = new JLabel("입 실");
			lblEnter.setBackground(Color.WHITE);
			lblEnter.setBorder(null);
			lblEnter.setFont(new Font("휴먼엑스포", Font.BOLD, 30));
			lblEnter.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnter.setBounds(0, 250, 250, 50);
			enterPanel.add(lblEnter);
			
			JButton enterSeat = new JButton(enterIcon);
			enterSeat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			enterSeat.setRolloverIcon(studyIcon);
			enterSeat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EnterSeatFrame enterSeat = new EnterSeatFrame(frame);
					enterSeat.setVisible(true);
					dispose();
				}
			});
			enterSeat.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
			enterSeat.setBounds(0, 0, 250, 250);
			enterPanel.add(enterSeat);
			
			JPanel exitPanel = new JPanel();
			exitPanel.setBackground(Color.WHITE);
			exitPanel.setBorder(null);
			exitPanel.setLayout(null);
			exitPanel.setBounds(355, 30, 250, 300);
			guestLoginPanel.add(exitPanel);
			
			JLabel lblExit = new JLabel("퇴 실");
			lblExit.setBackground(Color.WHITE);
			lblExit.setBorder(null);
			lblExit.setHorizontalAlignment(SwingConstants.CENTER);
			lblExit.setFont(new Font("휴먼엑스포", Font.BOLD, 30));
			lblExit.setBounds(0, 250, 250, 50);
			exitPanel.add(lblExit);
			
			JButton exitSeat = new JButton(exitIcon);
			exitSeat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			exitSeat.setRolloverIcon(hurrayIcon);
			exitSeat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					OutSeatDialog outDesk = new OutSeatDialog(frame);
					outDesk.setVisible(true);
					dispose();
				}
			});
			exitSeat.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
			exitSeat.setBounds(0, 0, 250, 250);
			exitPanel.add(exitSeat);
		}
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(320, 20, 1, 320);
		guestLoginPanel.add(separator);
	}
} // end GLogin
