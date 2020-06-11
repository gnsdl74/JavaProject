package edu.java.studyroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;
import edu.java.vo.StudyRoomMemberVO;

import java.awt.Toolkit;

/*
 * Join 클래스는 ScMain(메인화면)에서 회원가입을 담당하는 클래스
 */
public class JoinDialog extends JDialog {
	private final JPanel joinPanel = new JPanel();
	private JTextField textFieldId, textFieldPw, textFieldName, textFieldRegistNum, textFieldConfirmPw;
	private JLabel lblId, lblPw, lblName, lblRenum, lblPassword;
	private JButton checkIdBtn;
	private JTextArea textAreaCheckId, textAreaCheckPw, txtAreaChckNameRenum;

	private StudyRoomDAO dao;

	public JoinDialog() {
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\goott\\Desktop\\Java Swing project\\img\\leaf.png"));
		dao = StudyRoomDAOImple.getInstance();
		
		setModal(true); // 회원가입 Dialog가 실행되었을 때, 입력을 끝내기 전까지 사용자 입력 독접.
		setResizable(false);
		setTitle("회원가입");
		setBounds(100, 100, 355, 382);
		getContentPane().setLayout(null);
		setLocationRelativeTo(joinPanel);
		joinPanel.setBackground(Color.WHITE);
		joinPanel.setBounds(0, 0, 349, 310);
		getContentPane().add(joinPanel);
		joinPanel.setLayout(null);

		lblId = new JLabel("아이디");
		lblId.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblId.setBounds(30, 25, 48, 25);
		joinPanel.add(lblId);
		
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
		textFieldId.setBounds(30, 50, 130, 25);
		joinPanel.add(textFieldId);
		textFieldId.setColumns(10);

		lblPw = new JLabel("비밀번호");
		lblPw.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblPw.setBounds(31, 108, 69, 25);
		joinPanel.add(lblPw);

		textFieldPw = new JPasswordField();
		textFieldPw.setColumns(10);
		textFieldPw.setBounds(31, 133, 130, 25);
		joinPanel.add(textFieldPw);

		lblName = new JLabel("이름");
		lblName.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblName.setBounds(31, 191, 32, 25);
		joinPanel.add(lblName);

		textFieldName = new JTextField("이름 입력");
		textFieldName.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textFieldName.setForeground(Color.GRAY);
		textFieldName.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldName.getText().isEmpty()) {
					textFieldName.setForeground(Color.GRAY);
					textFieldName.setText("이름 입력");
		        }
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldName.getText().equals("이름 입력")) {
					textFieldName.setText("");
					textFieldName.setForeground(Color.BLACK);
		        }
			}
		});
		textFieldName.setColumns(10);
		textFieldName.setBounds(31, 216, 130, 25);
		joinPanel.add(textFieldName);

		lblRenum = new JLabel("주민등록번호");
		lblRenum.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblRenum.setBounds(191, 191, 100, 25);
		joinPanel.add(lblRenum);

		textFieldRegistNum = new JTextField("주민등록번호 입력");
		textFieldRegistNum.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textFieldRegistNum.setForeground(Color.GRAY);
		textFieldRegistNum.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldRegistNum.getText().isEmpty()) {
					textFieldRegistNum.setForeground(Color.GRAY);
					textFieldRegistNum.setText("주민등록번호 입력");
		        }
				
			}
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldRegistNum.getText().equals("주민등록번호 입력")) {
					textFieldRegistNum.setText("");
					textFieldRegistNum.setForeground(Color.BLACK);
		        }
			}
		});
		textFieldRegistNum.setColumns(10);
		textFieldRegistNum.setBounds(191, 216, 130, 25);
		joinPanel.add(textFieldRegistNum);

		JCheckBox chckbxConfirm = new JCheckBox("  개인정보의 이용과 수집에 동의합니다.");
		chckbxConfirm.setBackground(Color.WHITE);
		chckbxConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		chckbxConfirm.setBounds(55, 280, 250, 17);
		joinPanel.add(chckbxConfirm);

		checkIdBtn = new JButton("중복확인");
		checkIdBtn.setFont(new Font("돋움", Font.PLAIN, 13));
		checkIdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaCheckId.setVisible(true);
				String id = textFieldId.getText();
				if (id.equals("admin")) {
					textAreaCheckId.setText("admin은 사용할 수 없습니다.");
				} else {
					String matchId = PatternMatch.matchId(id);
					if (matchId == null) {
						int result = dao.checkId(id);
						if (result == 1) {
							textAreaCheckId.setText("해당 ID는 사용 중입니다.");
						} else {
							textAreaCheckId.setText("사용 가능한 ID 입니다.");
						}
					} else {
						textAreaCheckId.setText(matchId);
					}

				}

			}
		});
		checkIdBtn.setBounds(190, 50, 100, 25);
		joinPanel.add(checkIdBtn);

		textAreaCheckId = new JTextArea();
		textAreaCheckId.setBackground(SystemColor.menu);
		textAreaCheckId.setOpaque(false);
		textAreaCheckId.setBorder(null);
		textAreaCheckId.setVisible(false);
		textAreaCheckId.setEditable(false);
		textAreaCheckId.setBounds(29, 75, 290, 25);
		joinPanel.add(textAreaCheckId);
		textAreaCheckId.setColumns(10);

		lblPassword = new JLabel("비밀번호 확인");
		lblPassword.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lblPassword.setBounds(191, 108, 102, 25);
		joinPanel.add(lblPassword);

		textFieldConfirmPw = new JPasswordField();
		textFieldConfirmPw.setColumns(10);
		textFieldConfirmPw.setBounds(191, 133, 130, 25);
		joinPanel.add(textFieldConfirmPw);

		textAreaCheckPw = new JTextArea();
		textAreaCheckPw.setVisible(false);
		textAreaCheckPw.setBackground(SystemColor.menu);
		textAreaCheckPw.setOpaque(false);
		textAreaCheckPw.setBorder(null);
		textAreaCheckPw.setEditable(false);
		textAreaCheckPw.setColumns(10);
		textAreaCheckPw.setBounds(31, 158, 290, 25);
		joinPanel.add(textAreaCheckPw);

		txtAreaChckNameRenum = new JTextArea();
		txtAreaChckNameRenum.setVisible(false);
		txtAreaChckNameRenum.setOpaque(false);
		txtAreaChckNameRenum.setBackground(SystemColor.menu);
		txtAreaChckNameRenum.setBorder(null);
		txtAreaChckNameRenum.setEditable(false);
		txtAreaChckNameRenum.setColumns(10);
		txtAreaChckNameRenum.setBounds(30, 241, 290, 25);
		joinPanel.add(txtAreaChckNameRenum);
		// 회원의 정보가 입력될 joinPane

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBounds(0, 310, 349, 45);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton joinButton = new JButton("확인");
				joinButton.setFont(new Font("돋움", Font.PLAIN, 13));
				joinButton.setBounds(187, 8, 70, 30);
				joinButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String id = textFieldId.getText();
						String pw = textFieldPw.getText();
						String confirmPw = textFieldConfirmPw.getText();
						String name = textFieldName.getText();
						String renum = textFieldRegistNum.getText();

						if (id.equals("admin")) {
							JOptionPane.showMessageDialog(null, "admin은 사용할 수 없습니다.", "경고",
									JOptionPane.WARNING_MESSAGE);
						} else {
							int matchCheck = PatternMatch.matchInfo(id, pw, confirmPw, name, renum);
							String check = matchType(matchCheck);
							System.out.println("PatternMatch = " + check);
							
							if (check == null) { // 회원정보 등록 형식에 알맞다면
								StudyRoomMemberVO vo = new StudyRoomMemberVO(id, pw, name, renum, null);
								System.out.println(vo);

								int result = checkOverlap(vo.getId(), vo.getReNumber());
								System.out.println("확인의 중복검사 = " + result);
								
								if (result == 0) { // 중복값이 없을때
									boolean confirm = chckbxConfirm.isSelected();
									if (confirm == true) { // 개인정보 수집에 동의하면
										result = dao.insert(vo);
										if (result == 1) { // 회원 가입 실행
											JOptionPane.showMessageDialog(null, "회원 가입에 성공했습니다.", "회원 가입 메시지",
													JOptionPane.INFORMATION_MESSAGE);
											dispose();
										} else { // 회원 가입 실패
											JOptionPane.showMessageDialog(null, "회원 가입 실패했습니다.\n관리자에게 문의하세요.",
													"회원 가입 메시지", JOptionPane.WARNING_MESSAGE);
										}
									} else { // 개인정보 수집에 비동의하면
										JOptionPane.showMessageDialog(null, "개인정보 수집에 동의하지 않으셨습니다.", "개인정보확인",
												JOptionPane.INFORMATION_MESSAGE);
									}
								} 

							}
						}
					}
				});
				buttonPane.setLayout(null);
				buttonPane.add(joinButton);
				getRootPane().setDefaultButton(joinButton); // 포커스가 어디있든 Enter를 치면 joinButton을 누른 효과 설정
			}

			{
				JButton cancelButton = new JButton("취소");
				cancelButton.setFont(new Font("돋움", Font.PLAIN, 13));
				cancelButton.setBounds(272, 8, 70, 30);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		} // 회원가입과 취소가 입력될 button Pane

	} // end Join() - 생성자

	private String matchType(int check) {
		String result = null;
		textAreaCheckId.setText(null);
		textAreaCheckPw.setText(null);
		txtAreaChckNameRenum.setText(null);
		
		switch (check) {
		case 1:
			result = "4~20자리의 영문 소문자, 영문+숫자를 사용하세요.";
			textAreaCheckId.setText(result);
			textAreaCheckId.setVisible(true);
			break;
		case 2:
			result = "비밀번호가 서로 다릅니다.";
			textAreaCheckPw.setText(result);
			textAreaCheckPw.setVisible(true);
			break;
		case 3:
			result = "8~20자리의 숫자, 특수문자, 영문자를 조합하세요.";
			textAreaCheckPw.setText(result);
			textAreaCheckPw.setVisible(true);
			break;
		case 4:
			result = "이름은 2~4자리의 한글, 2~10자리의 영문을 사용하세요.";
			txtAreaChckNameRenum.setText(result);
			txtAreaChckNameRenum.setVisible(true);
			break;
		case 5:
			result = "주민등록번호는 '-'로 구분하여 13자리로 입력하세요.";
			txtAreaChckNameRenum.setText(result);
			txtAreaChckNameRenum.setVisible(true);
			break;
		case 6:
			result = "이름을 입력해주세요.";
			txtAreaChckNameRenum.setText(result);
			txtAreaChckNameRenum.setVisible(true);
		default:
			break;
		}
		return result;
	} // end matchType()

	private int checkOverlap(String id, String reNumber) {
		int result = 0;

		result = dao.checkId(id);
		if (result == 1) {
			textAreaCheckId.setText("해당 ID는 사용 중입니다.");
		} else {
			textAreaCheckId.setText("사용 가능한 ID 입니다.");
			System.out.println("아이디 중복 검사 확인 : " + result);
			System.out.println();
			
			result = dao.checkReNumber(reNumber);
			System.out.println("주민번호 중복 검사 확인 : " + result);
			if (result == 1) { // 주민등록번호 중복
				txtAreaChckNameRenum.setVisible(true);
				txtAreaChckNameRenum.setText("이미 등록된 회원입니다.");
			}
		}

		return result;
	} // end checkOverlap
} // end Join