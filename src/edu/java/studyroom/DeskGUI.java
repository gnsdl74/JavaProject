package edu.java.studyroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.java.dao.DeskDAO;
import edu.java.dao.DeskDAOImple;
import edu.java.vo.DeskVO;

public class DeskGUI {

	private static DeskDAO dao;
	private JFrame frame;
	private JTable table;
	private String[] colNames = { "좌석 번호", "아이디", "요금제" };
	private Object[] records = new Object[colNames.length];
	private DefaultTableModel tableModel;
	private String selectedSeatNum;
	private JComboBox comboBox;
	private JTextField txtField_1, txtFieid_2;

	public DeskGUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setTitle("좌석 정보");
		frame.setBounds(100, 100, 557, 462);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); // 가운데 출력
		frame.getContentPane().setLayout(null);

		Font btnfont = new Font("굴림", Font.BOLD, 19);
		JButton btnAllSearch = new JButton("전체 검색");
		btnAllSearch.addActionListener((e) -> {
			selectAllDeskInfo();
		});

		btnAllSearch.setFont(btnfont);
		btnAllSearch.setBounds(76, 387, 158, 36);
		frame.getContentPane().add(btnAllSearch);

		JButton btnChangeSeat = new JButton("좌석 변경");
		btnChangeSeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeSeat();
			}
		});

		btnChangeSeat.setFont(btnfont);
		btnChangeSeat.setBounds(320, 387, 158, 36);
		frame.getContentPane().add(btnChangeSeat);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 551, 377);
		frame.getContentPane().add(scrollPane);

		// 테이블 기본 초기화
		tableModel = new DefaultTableModel(colNames, 0); // 모델 설정
		table = new JTable(tableModel); // 모델 테이블에 적용
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
//				int column = table.getSelectedColumn();
				selectedSeatNum = (String) tableModel.getValueAt(row, 0);
				System.out.println(selectedSeatNum + "좌석 선택");
			}
		});
		table.setFont(new Font("굴림", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

	}

	private void selectAllDeskInfo() {
		dao = DeskDAOImple.getInstance(); // 싱글톤 인스턴스 불러오기
		// TODO Auto-generated method stub
		tableModel.setNumRows(0); // 테이블 데이터 초기화
		ArrayList<DeskVO> list = dao.select();

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			buffer.append(list.get(i).toString());
			// 테이블 각 요소들(좌석 번호, 아이디, 요금제)을 테이블 모델에 저장
			records[0] = list.get(i).getDeskNum();
			records[1] = list.get(i).getId();
			records[2] = list.get(i).getPart();
			tableModel.addRow(records);
		}

	}

	private void changeSeat() {

		JFrame changeFrame = new JFrame();

		int row = table.getSelectedRow();
		if (row == -1) { // 좌석 번호를 선택하지 않았을 경우
			JOptionPane.showMessageDialog(changeFrame, "좌석 번호를 선택하세요.");
			return;
		}
		Object obj = table.getValueAt(row, 0);
		System.out.println("테이블 번호 : " + obj);
		System.out.println(table.getValueAt(row, 1));

		if (table.getValueAt(row, 1).equals("0")) {
			JOptionPane.showMessageDialog(table, selectedSeatNum + " 좌석에 회원이 없습니다.\n회원이 있는 좌석을 선택하세요.", "안내창",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		changeFrame.setTitle("좌석 변경");
		changeFrame.getContentPane().setLayout(null);
		changeFrame.setResizable(false);
		changeFrame.setLocationRelativeTo(null);
		changeFrame.setSize(500, 300);
		changeFrame.setVisible(true);

		Font l_font = new Font("굴림", Font.BOLD, 17);
		JLabel t_label1 = new JLabel("현재 좌석");
		JLabel t_label2 = new JLabel("변경후 좌석");
		JButton btnUpdate = new JButton("변경");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// TODO : selectedSeatNum로 선택한 데이터의 아이디(id), 요금제(part)를 콤보박스에서 선택한 데이터로 DB 데이터를
				// 이동
				dao = DeskDAOImple.getInstance(); // 싱글톤 인스턴스 불러오기
				ArrayList<DeskVO> list = dao.select();
//				DeskVO updateVo = new DeskVO(id, part);
				DeskVO selectedVo = list.get(row);

				for (int i = 0; i < list.size(); i++) {
					DeskVO vo = list.get(i);
					System.out.println(selectedSeatNum);
					// TODO : 콤보박스에서 선택한 데이터로 DB 데이터를 이동

					System.out.println("변경후 좌석 : " + comboBox.getSelectedItem());
					if (vo.getDeskNum().equals(comboBox.getSelectedItem())) {
						System.out.println("선택된 인덱스 : " + i);
						tableModel.setValueAt(selectedVo.getId(), i, 1); // 변경 후 좌석에 ID
						tableModel.setValueAt(selectedVo.getPart(), i, 2); // 변경 후 좌석에 요금제
						System.out.println(comboBox.getSelectedItem());
						// DB 연동
						// selectedSeatNum의 좌석번호를 가지는 데이터를 update
						// update desk set (id = 0, part = null, startTime = null, exitTime = null)
						// where deskNum = selectedSeatNum; // D3를 초기화
						int result = dao.updateToString(selectedSeatNum);
						if (result == 1) {
							System.out.println("성공");
						} else {
							System.out.println("실패");
						}
						// update desk set (id = selectedVO.getId(), part = selectedVO.getPart()) where
						// deskNum = comboBox.getSelectedItem();
						// 변경할 좌석에 ID와 Part를 update
						// update desk set id = selectedVo.getId(), part = selectedVo.getPart(),
						// startTime = selectedVo.getStartTime();, exitTime = null
						// where desknumber = comboBox.getSelectedItem();
						result = dao.updateToComboBox(selectedVo.getId(), selectedVo.getPart(),
								selectedVo.getStartTime(), selectedVo.getExitTime(),
								(String) comboBox.getSelectedItem());
						if (result == 1) {
							System.out.println("성공");
						} else {
							System.out.println("실패");
						}
						break;
					}
				}

				if (comboBox.getSelectedItem().toString() != null) {
					tableModel.setValueAt(0, row, 1);
					tableModel.setValueAt(null, row, 2);
				}

			}
		});
		t_label1.setBounds(95, 40, 150, 30);
		t_label1.setFont(l_font);
		t_label2.setBounds(300, 40, 150, 30);
		t_label2.setFont(l_font);
		btnUpdate.setBounds(215, 225, 60, 30);
		changeFrame.getContentPane().add(t_label1);
		changeFrame.getContentPane().add(t_label2);
		changeFrame.getContentPane().add(btnUpdate);

		Font txtFont = new Font("굴림", Font.PLAIN, 60);
		txtField_1 = new JTextField();
		changeFrame.getContentPane().add(txtField_1);
		txtField_1.setText(selectedSeatNum);
		txtField_1.setBounds(83, 80, 100, 100);
		txtField_1.setFont(txtFont);
		txtField_1.setHorizontalAlignment(JTextField.CENTER); // 글자 가운데정렬
		txtField_1.setVisible(true);

		comboBox = new JComboBox();
		dao = DeskDAOImple.getInstance(); // 싱글톤 인스턴스 불러오기
		ArrayList<DeskVO> list = dao.select_desk();
		for (DeskVO vo : list) {
			comboBox.addItem(vo.getDeskNum());
		}

		comboBox.setEditable(false);
		comboBox.setBounds(240, 80, 60, 31);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtFieid_2.setText(comboBox.getSelectedItem().toString());
			}
		});
		changeFrame.getContentPane().add(comboBox);

		txtFieid_2 = new JTextField();
		changeFrame.getContentPane().add(txtFieid_2);
		txtFieid_2.setBounds(300, 80, 100, 100);
		txtFieid_2.setFont(txtFont);
		txtFieid_2.setHorizontalAlignment(txtFieid_2.CENTER);
		txtFieid_2.setVisible(true);

	}

}
