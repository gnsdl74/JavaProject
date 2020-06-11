package edu.java.studyroom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;
import edu.java.vo.DeskVO;

public class OutSeatDialog extends JDialog {
	private final JPanel guestLoginPanel;
	private JTextField textFieldId, confirmSeat, confirmPart, textFieldStartTime, textFieldExpectExitTime;

	private DeskVO vo;
	private String deskNumber;

	private StudyRoomDAO dao;

	public OutSeatDialog(JFrame frame) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		setBackground(Color.WHITE);
		final ImageIcon memoIcon = new ImageIcon("img/memo.jpeg");
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dao = StudyRoomDAOImple.getInstance();
		frame.setEnabled(false);

		setTitle("퇴실 확인");
		setResizable(false);
		setBounds(100, 100, 788, 607);
		getContentPane().setLayout(null);

		guestLoginPanel = new JPanel();
		guestLoginPanel.setBackground(Color.WHITE);
		getContentPane().add(guestLoginPanel);
		guestLoginPanel.setBounds(0, 0, 400, 561);
		guestLoginPanel.setLayout(null);
		setLocationRelativeTo(rootPane);
		{
			JLabel lblDeskNum = new JLabel("임시 아이디");
			lblDeskNum.setBackground(Color.WHITE);
			lblDeskNum.setFont(new Font("굴림", Font.PLAIN, 25));
			lblDeskNum.setBounds(80, 67, 130, 35);
			guestLoginPanel.add(lblDeskNum);
		}

		textFieldId = new JTextField("임시 아이디 입력");

		textFieldId.setFont(new Font("굴림", Font.BOLD, 20));
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setForeground(Color.GRAY);
		textFieldId.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldId.getText().isEmpty()) {
					textFieldId.setForeground(Color.GRAY);
					textFieldId.setText("임시 아이디 입력");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldId.getText().equals("임시 아이디 입력")) {
					textFieldId.setText("");
					textFieldId.setForeground(Color.BLACK);
				}
			}
		});
		textFieldId.setBounds(80, 112, 240, 50);
		guestLoginPanel.add(textFieldId);
		textFieldId.setColumns(10);

		JButton checkBtn = new JButton("좌석확인");
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				if (textFieldId.getText().length() != 8) {
					JOptionPane.showMessageDialog(null, "정확한 ID를 입력하세요.", "ID 확인", JOptionPane.WARNING_MESSAGE);
				} else {
					vo = dao.confirmDesk(id);
					if (vo == null) {
						JOptionPane.showMessageDialog(null, "ID를 확인해주세요.", "ID Error", JOptionPane.ERROR_MESSAGE);
					} else {
						deskNumber = vo.getDeskNum();
						String startTime = vo.getStartTime();
						String expectExitTime = vo.getExitTime();
						confirmSeat.setText(deskNumber);
						textFieldStartTime.setText(startTime);
						textFieldExpectExitTime.setText(expectExitTime);
						if (vo.getPart().equals("입력오류")) {
							JOptionPane.showMessageDialog(null, "관리자에게 문의하세요.", "Error", JOptionPane.ERROR_MESSAGE);
						} else {
							confirmPart.setText(vo.getPart());
						}

					}
				}
			}
		});
		checkBtn.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		checkBtn.setFont(new Font("굴림", Font.PLAIN, 12));
		checkBtn.setBounds(220, 170, 100, 35);
		guestLoginPanel.add(checkBtn);
		
		JPanel memoPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(memoIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		memoPanel.setBackground(Color.WHITE);
		memoPanel.setBounds(12, 247, 375, 304);
		guestLoginPanel.add(memoPanel);
		memoPanel.setLayout(null);
		
				JLabel lblWarn = new JLabel("※ 주 의 사 항");
				lblWarn.setBackground(Color.WHITE);
				lblWarn.setBounds(40, 37, 140, 23);
				memoPanel.add(lblWarn);
				lblWarn.setForeground(Color.RED);
				lblWarn.setHorizontalAlignment(SwingConstants.CENTER);
				lblWarn.setFont(new Font("맑은 고딕", Font.BOLD, 21));
				
				JLabel lblWarncontent1 = new JLabel("1. 임시 아이디를 잊은 경우, 관리자에게 문의하세요.");
				lblWarncontent1.setForeground(Color.BLACK);
				lblWarncontent1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				lblWarncontent1.setBackground(Color.WHITE);
				lblWarncontent1.setBounds(40, 80, 310, 23);
				memoPanel.add(lblWarncontent1);
				
				JLabel label = new JLabel("2. 남은 시간은 저장해주지 않습니다.");
				label.setForeground(Color.BLACK);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label.setBackground(Color.WHITE);
				label.setBounds(40, 121, 220, 23);
				memoPanel.add(label);
				
				JLabel label_1 = new JLabel("3. 회원 가입하면 할인된 금액으로 이용 가능합니다.");
				label_1.setForeground(Color.BLACK);
				label_1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label_1.setBackground(Color.WHITE);
				label_1.setBounds(40, 162, 310, 23);
				memoPanel.add(label_1);
				
				JLabel label_3 = new JLabel("4. 이용 후 뒷정리를 깔끔하게 해주시길 바랍니다.");
				label_3.setForeground(Color.BLACK);
				label_3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
				label_3.setBackground(Color.WHITE);
				label_3.setBounds(40, 203, 300, 23);
				memoPanel.add(label_3);
				
				JLabel label_2 = new JLabel("이용해주셔서 감사합니다.");
				label_2.setHorizontalAlignment(SwingConstants.CENTER);
				label_2.setForeground(Color.BLACK);
				label_2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				label_2.setBackground(Color.WHITE);
				label_2.setBounds(175, 243, 175, 23);
				memoPanel.add(label_2);
				
				JSeparator separator = new JSeparator();
				separator.setOrientation(SwingConstants.VERTICAL);
				separator.setBounds(399, 10, 1, 540);
				guestLoginPanel.add(separator);

		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setBounds(400, 0, 384, 561);
		getContentPane().add(infoPanel);
		infoPanel.setLayout(null);

		JLabel lblDesk = new JLabel("좌석");
		lblDesk.setBackground(Color.WHITE);
		lblDesk.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblDesk.setBounds(20, 106, 40, 20);
		infoPanel.add(lblDesk);

		confirmSeat = new JTextField();
		confirmSeat.setBackground(Color.WHITE);
		confirmSeat.setHorizontalAlignment(SwingConstants.CENTER);
		confirmSeat.setFont(new Font("굴림", Font.BOLD, 20));
		confirmSeat.setEditable(false);
		confirmSeat.setColumns(10);
		confirmSeat.setBounds(20, 136, 100, 40);
		infoPanel.add(confirmSeat);

		JLabel lblPart = new JLabel("시간제");
		lblPart.setBackground(Color.WHITE);
		lblPart.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblPart.setBounds(132, 106, 55, 20);
		infoPanel.add(lblPart);

		confirmPart = new JTextField();
		confirmPart.setBackground(Color.WHITE);
		confirmPart.setHorizontalAlignment(SwingConstants.CENTER);
		confirmPart.setFont(new Font("굴림", Font.BOLD, 20));
		confirmPart.setEditable(false);
		confirmPart.setColumns(10);
		confirmPart.setBounds(132, 136, 233, 40);
		infoPanel.add(confirmPart);

		JLabel lblStartTime = new JLabel("입실 시간");
		lblStartTime.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblStartTime.setBounds(20, 196, 78, 20);
		infoPanel.add(lblStartTime);

		JLabel lblExpectExitTime = new JLabel("퇴실 예정 시간");
		lblExpectExitTime.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		lblExpectExitTime.setBounds(20, 286, 120, 20);
		infoPanel.add(lblExpectExitTime);

		textFieldStartTime = new JTextField();
		textFieldStartTime.setBackground(Color.WHITE);
		textFieldStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldStartTime.setFont(new Font("굴림", Font.BOLD, 20));
		textFieldStartTime.setEditable(false);
		textFieldStartTime.setBounds(20, 226, 345, 40);
		textFieldStartTime.setColumns(10);
		infoPanel.add(textFieldStartTime);

		textFieldExpectExitTime = new JTextField();
		textFieldExpectExitTime.setBackground(Color.WHITE);
		textFieldExpectExitTime.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldExpectExitTime.setFont(new Font("굴림", Font.BOLD, 20));
		textFieldExpectExitTime.setEditable(false);
		textFieldExpectExitTime.setColumns(10);
		textFieldExpectExitTime.setBounds(20, 316, 345, 40);
		infoPanel.add(textFieldExpectExitTime);
		
		JLabel lblSeatInfo = new JLabel("좌 석 정 보");
		lblSeatInfo.setForeground(Color.RED);
		lblSeatInfo.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblSeatInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeatInfo.setBounds(127, 30, 130, 40);
		infoPanel.add(lblSeatInfo);
		
		JLabel lblInfo = new JLabel("※ 연체료 결제는 퇴실을 진행하면 이루어집니다.");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setForeground(Color.RED);
		lblInfo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblInfo.setBounds(30, 410, 326, 20);
		infoPanel.add(lblInfo);
		
					JButton outBtn = new JButton("퇴실");
					outBtn.setBounds(72, 450, 100, 40);
					infoPanel.add(outBtn);
					outBtn.setFont(new Font("굴림", Font.PLAIN, 12));
					outBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							int result = JOptionPane.showConfirmDialog(null, "퇴실하시겠습니까?", "퇴실 확인", JOptionPane.YES_NO_OPTION);

							if (result == JOptionPane.YES_OPTION) { // 퇴실 확인
								if (vo == null) { // 좌석확인을 하지 않음
									JOptionPane.showMessageDialog(null, "임시 ID를 입력하세요.", "ID입력", JOptionPane.ERROR_MESSAGE);

								} else { // 좌석확인했다면
									try {
										dispose();
										// 예상퇴실시간과 실제퇴실시간을 비교하는 클래스 호출
										CompareToTime compare = new CompareToTime();
										compare.compareToTime(frame, vo, deskNumber);

									} catch (Exception e1) {
										e1.printStackTrace();
									}

								}

							} else { // 퇴실 취소(NO_OPTION)
								return;
							}

						}
					});
					getRootPane().setDefaultButton(outBtn);
					// end outBtn - 퇴실 확인

					JButton cancelBtn = new JButton("취소");
					cancelBtn.setBounds(212, 450, 100, 40);
					infoPanel.add(cancelBtn);
					cancelBtn.setFont(new Font("굴림", Font.PLAIN, 12));
					cancelBtn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.setEnabled(true);
							dispose();
						}
					});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(220, 220, 220));
		setJMenuBar(menuBar);

		JMenu mnMove = new JMenu("Menu");
		menuBar.add(mnMove);

		JMenuItem mntmInDesk = new JMenuItem("입실");
		mntmInDesk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterSeatFrame inDesk = new EnterSeatFrame(frame);
				inDesk.setVisible(true);
				dispose();
			}
		});
		mnMove.add(mntmInDesk);
	}
}
