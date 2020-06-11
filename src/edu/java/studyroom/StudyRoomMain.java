package edu.java.studyroom;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;
import edu.java.vo.StudyRoomMemberVO;

/*
 * ScMain은 StudyCafe 프로그램의 첫 화면 출력. 
 */
public class StudyRoomMain {

	private JFrame frmStudyroom;
	private JTextField textFieldId;
	private JTextField textFieldPw;
	private JLabel lblId, lblPassword;

	private static StudyRoomDAO dao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudyRoomMain window = new StudyRoomMain();
					window.frmStudyroom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // end main()

	public StudyRoomMain() {
		initialize();
	} // end ScMain() - 생성자

	private void initialize() {
		final ImageIcon logoIcon = new ImageIcon("img/logo.jpg");

		dao = StudyRoomDAOImple.getInstance();

		frmStudyroom = new JFrame();
		frmStudyroom.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		frmStudyroom.getContentPane().setBackground(Color.WHITE);
		frmStudyroom.setTitle("StudyRoom");
		frmStudyroom.setResizable(false);
		frmStudyroom.setBounds(100, 100, 559, 419);
		frmStudyroom.setLocationRelativeTo(null);
		frmStudyroom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudyroom.getContentPane().setLayout(null);

		Font font = new Font("맑은 고딕", Font.BOLD, 30);

		lblId = new JLabel("아이디");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblId.setBounds(289, 60, 80, 30);
		frmStudyroom.getContentPane().add(lblId);

		lblPassword = new JLabel("비밀번호");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblPassword.setBounds(289, 104, 80, 30);
		frmStudyroom.getContentPane().add(lblPassword);

		textFieldId = new JTextField("아이디 입력");
		textFieldId.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textFieldId.setForeground(Color.GRAY);
		textFieldId.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldId.getText().isEmpty()) {
					textFieldId.setForeground(Color.GRAY);
					textFieldId.setText("아이디 입력");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldId.getText().equals("아이디 입력")) {
					textFieldId.setText("");
					textFieldId.setForeground(Color.BLACK);
				}
			}
		});
		textFieldId.setBounds(382, 60, 135, 30);
		frmStudyroom.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);

		textFieldPw = new JPasswordField();
		textFieldPw.setColumns(10);
		textFieldPw.setBounds(382, 104, 135, 30);
		frmStudyroom.getContentPane().add(textFieldPw);

		font = new Font("굴림", Font.PLAIN, 20);
		JButton btnJoin = new JButton("회원가입");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinDialog join = new JoinDialog();
				join.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				join.setVisible(true);
			}
		});
		btnJoin.setFont(new Font("돋움", Font.PLAIN, 13));
		btnJoin.setBounds(27, 294, 135, 40);
		frmStudyroom.getContentPane().add(btnJoin);
		// end btnJoin - 회원가입 버튼

		JButton btnMemberLogin = new JButton("로그인");
		btnMemberLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textFieldId.getText();
				String pw = textFieldPw.getText();
				
				// id와 pw 값을 입력받은 후 초기화
				textFieldId.setForeground(Color.GRAY);
				textFieldId.setText("아이디 입력");
				textFieldPw.setText("");
				
				// id와 pw의 값이 없을 경우
				if (id.equals("") || pw.equals("")) {
					JOptionPane.showMessageDialog(null, "ID와 PW를 모두 입력하세요.", "로그인", JOptionPane.INFORMATION_MESSAGE);
				} else { // id와 pw를 모두 입력한 경우
					StudyRoomMemberVO vo = dao.login(id, pw);
					if (vo == null) { // 해당하는 회원이 없으면
						String result = "ID와 PW를 확인하세요.";
						JOptionPane.showMessageDialog(null, result, "로그인 메시지", JOptionPane.ERROR_MESSAGE);
						
					} else { // 해당하는 회원이 있으면
						if (vo.getId().equals("admin")) { // 회원 중 ID가 admin이면
							new AdminMain();
						} else { // admin이 아니면
							Gui01 gui = new Gui01(id);
							gui.setVisible(true);
						}
					}
				}
			}
		});
		frmStudyroom.getRootPane().setDefaultButton(btnMemberLogin);
		btnMemberLogin.setFont(new Font("돋움", Font.PLAIN, 13));
		btnMemberLogin.setBounds(382, 161, 135, 40);
		frmStudyroom.getContentPane().add(btnMemberLogin);
		// end btnM_Login

		JButton btnFindId = new JButton("아이디 찾기");
		btnFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIdDialog findId = new FindIdDialog();
				findId.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				findId.setVisible(true);
			}
		});
		btnFindId.setFont(new Font("돋움", Font.PLAIN, 13));
		btnFindId.setBounds(205, 294, 135, 40);
		frmStudyroom.getContentPane().add(btnFindId);
		// end btnFindId

		JButton btnFindPw = new JButton("비밀번호 찾기");
		btnFindPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindPwDialog findpw = new FindPwDialog();
				findpw.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				findpw.setVisible(true);
			}
		});
		btnFindPw.setFont(new Font("돋움", Font.PLAIN, 13));
		btnFindPw.setBounds(382, 294, 135, 40);
		frmStudyroom.getContentPane().add(btnFindPw);
		// end btnFindPw

		JButton btnG_login = new JButton("비회원 로그인");
		btnG_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestLoginDialog gLogin = new GuestLoginDialog(frmStudyroom);
				gLogin.setVisible(true);
			}
		});
		btnG_login.setFont(new Font("돋움", Font.PLAIN, 13));
		btnG_login.setBounds(382, 210, 135, 40);
		frmStudyroom.getContentPane().add(btnG_login);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 277, 490, 2);
		frmStudyroom.getContentPane().add(separator_1);
		
		JPanel image = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(logoIcon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		image.setBackground(Color.WHITE);
		image.setBounds(56, 21, 221, 174);
		frmStudyroom.getContentPane().add(image);
		
		JLabel lblNewLabel = new JLabel("Boys, Be Ambitious !");
		lblNewLabel.setFont(new Font("한컴 고딕", Font.BOLD, 27));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 205, 284, 40);
		frmStudyroom.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("스터디룸 좌석관리 프로그램 ver 1.0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("한컴 고딕", Font.PLAIN, 13));
		label.setBounds(27, 243, 284, 24);
		frmStudyroom.getContentPane().add(label);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(220, 220, 220));
		frmStudyroom.setJMenuBar(menuBar);

		JMenu mnMain = new JMenu("Menu");
		menuBar.add(mnMain);

		JMenuItem mntmJoin = new JMenuItem("회원가입");
		mntmJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinDialog join = new JoinDialog();
				join.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				join.setVisible(true);
			}
		});
		mnMain.add(mntmJoin);

		JMenuItem mntmFindId = new JMenuItem("ID 찾기");
		mntmFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindIdDialog findId = new FindIdDialog();
				findId.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				findId.setVisible(true);
			}
		});
		mnMain.add(mntmFindId);

		JMenuItem mntmFindPw = new JMenuItem("PW 찾기");
		mntmFindPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindPwDialog findpw = new FindPwDialog();
				findpw.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				findpw.setVisible(true);
			}
		});
		mnMain.add(mntmFindPw);

		JSeparator separator = new JSeparator();
		mnMain.add(separator);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmStudyroom.dispose();
			}
		});
		mnMain.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmWelcome = new JMenuItem("Welcome");
		mntmWelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WelcomeDialog welcome = new WelcomeDialog();
				welcome.setVisible(true);
			}
		});
		mnHelp.add(mntmWelcome);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog about = new AboutDialog();
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		// end btnG_login
	} // end initialize()
} // end ScMain