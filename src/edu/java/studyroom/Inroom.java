package edu.java.studyroom;

import javax.swing.JLabel;
import javax.swing.JPanel;

import edu.java.dao.ProjectDAO;
import edu.java.dao.ProjectDAOimple;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Inroom extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static ProjectDAO dao;

	public Inroom(String id) {
		setModal(true);
		
		dao = ProjectDAOimple.getinstance();
		
		String Enabled = null;
		String IDZero = "0";
		JFrame newWin = new JFrame();
		newWin.setTitle("입실");
		setBounds(100, 100, 666, 440);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		setLocationRelativeTo(contentPanel);
		
		JLabel lblNewLabel = new JLabel("좌석표");
		lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 30));
		lblNewLabel.setBounds(175, 10, 84, 53);
		getContentPane().add(lblNewLabel);

		JButton A1 = new JButton("A1");
		A1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		// Enabled
		Enabled = dao.enabled("A1");
		if (Enabled.equals(IDZero)) {
			A1.setEnabled(true);
		} else {
			A1.setEnabled(false);
		}
		A1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String A1 = "A1";
				ButtonClick(id, null, A1);
				dispose();

			}
		});
		A1.setBounds(12, 67, 110, 60);
		getContentPane().add(A1);

		JButton A2 = new JButton("A2");
		A2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		// Enabled
		Enabled = dao.enabled("A2");
		if (Enabled.equals(IDZero)) {
			A2.setEnabled(true);
		} else {
			A2.setEnabled(false);
		}
		A2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "A2");
				dispose();
			}
		});
		A2.setBounds(165, 67, 110, 60);
		getContentPane().add(A2);

		JButton A3 = new JButton("A3");
		A3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		// Enabled
		Enabled = dao.enabled("A3");
		if (Enabled.equals(IDZero)) {
			A3.setEnabled(true);
		} else {
			A3.setEnabled(false);
		}
		A3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "A3");
				dispose();
			}
		});
		A3.setBounds(312, 67, 110, 60);
		getContentPane().add(A3);

		JButton B1 = new JButton("B1");
		// Enabled
		Enabled = dao.enabled("B1");
		B1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			B1.setEnabled(true);
		} else {
			B1.setEnabled(false);
		}
		B1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "B1");
				dispose();
			}
		});
		B1.setBounds(12, 137, 110, 60);
		getContentPane().add(B1);

		JButton B2 = new JButton("B2");
		// Enabled
		Enabled = dao.enabled("B2");
		B2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			B2.setEnabled(true);
		} else {
			B2.setEnabled(false);
		}
		B2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "B2");
				dispose();
			}
		});
		B2.setBounds(165, 137, 110, 60);
		getContentPane().add(B2);

		JButton B3 = new JButton("B3");
		// Enabled
		Enabled = dao.enabled("B3");
		B3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			B3.setEnabled(true);
		} else {
			B3.setEnabled(false);
		}
		B3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "B3");
				dispose();
			}
		});
		B3.setBounds(312, 137, 110, 60);
		getContentPane().add(B3);

		JButton C1 = new JButton("C1");
		// Enabled
		Enabled = dao.enabled("C1");
		C1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			C1.setEnabled(true);
		} else {
			C1.setEnabled(false);
		}
		C1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "C1");
				dispose();
			}
		});
		C1.setBounds(12, 207, 110, 60);
		getContentPane().add(C1);

		JButton C2 = new JButton("C2");
		// Enabled
		Enabled = dao.enabled("C2");
		C2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			C2.setEnabled(true);
		} else {
			C2.setEnabled(false);
		}
		C2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "C2");
				dispose();
			}
		});
		C2.setBounds(165, 207, 110, 60);
		getContentPane().add(C2);

		JButton C3 = new JButton("C3");
		// Enabled
		Enabled = dao.enabled("C3");
		C3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			C3.setEnabled(true);
		} else {
			C3.setEnabled(false);
		}
		C3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "C3");
				dispose();
			}
		});
		C3.setBounds(312, 207, 110, 60);
		getContentPane().add(C3);

		JButton D1 = new JButton("D1");
		// Enabled
		Enabled = dao.enabled("D1");
		D1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			D1.setEnabled(true);
		} else {
			D1.setEnabled(false);
		}
		D1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "D1");
				dispose();
			}
		});
		D1.setBounds(12, 277, 110, 60);
		getContentPane().add(D1);

		JButton D2 = new JButton("D2");
		// Enabled
		Enabled = dao.enabled("D2");
		D2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			D2.setEnabled(true);
		} else {
			D2.setEnabled(false);
		}
		D2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "D2");
				dispose();
			}
		});
		D2.setBounds(165, 277, 110, 60);
		getContentPane().add(D2);

		JButton D3 = new JButton("D3");
		// Enabled
		Enabled = dao.enabled("D3");
		D3.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		if (Enabled.equals(IDZero)) {
			D3.setEnabled(true);
		} else {
			D3.setEnabled(false);
		}
		D3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtonClick(id, null, "D3");
				dispose();
			}
		});
		D3.setBounds(312, 277, 110, 60);
		getContentPane().add(D3);

		JLabel ExitIcon = new JLabel("EXIT ↓");
		ExitIcon.setFont(new Font("함초롬바탕", Font.PLAIN, 20));
		ExitIcon.setBounds(12, 345, 90, 60);
		getContentPane().add(ExitIcon);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("\uC124\uBA85");
		lblNewLabel_1.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(555, 20, 38, 40);
		getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(490, 67, 58, 40);
		getContentPane().add(btnNewButton);
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setBounds(490, 117, 58, 40);
		getContentPane().add(button);
		
		JLabel lblNewLabel_2 = new JLabel("\uC790\uB9AC\uC788\uC74C");
		lblNewLabel_2.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(556, 67, 90, 40);
		getContentPane().add(lblNewLabel_2);
		
		JLabel label = new JLabel("\uC790\uB9AC\uC5C6\uC74C");
		label.setFont(new Font("한컴산뜻돋움", Font.PLAIN, 20));
		label.setBounds(556, 117, 90, 40);
		getContentPane().add(label);
		
		JLabel lblNewLabel_3 = new JLabel();
		lblNewLabel_3.setBounds(480, 163, 180, 228);
		getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("img/inroom_1.png"));
		
	}//end inroom()

		private void ButtonClick(String id, String time, String number) { // InButtonWindow 이동
		InButtonWindow inbuttonwindow = new InButtonWindow(id, time, number);
		inbuttonwindow.setVisible(true);
	}
}// end JDialog
