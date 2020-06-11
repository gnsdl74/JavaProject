package edu.java.studyroom;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.LineBorder;

import edu.java.dao.StudyRoomDAO;
import edu.java.dao.StudyRoomDAOImple;
import edu.java.vo.DeskVO;

public class SittingList {
	ButtonGroup seatGroup;

	private ArrayList<DeskVO> list = new ArrayList<DeskVO>();
	private StudyRoomDAO dao;

	/*
	 * 토글버튼들을 반복문으로 생성하고 생성된 버튼들을 포함하는 버튼그룹을 리턴 SittingList 클래스를 호출한 후 repaint와
	 * revalidate를 할 것 multipleSeat는 Desk 테이블에서 좌석번호의 개수(좌석개수)를 가져오는 걸로 대체 가능
	 */
	public SittingList(JPanel enterSeatPanel, JPanel seatLocation, int multipleSeat) {
		dao = StudyRoomDAOImple.getInstance();
		final int width = 80, height = 60;
		int x = 30, y = 40;

		seatGroup = new ButtonGroup();
		char eng = 'A';
		int number = 0;

		for (int i = 0; i < multipleSeat; i++) {
			JToggleButton toggleBtn = new JToggleButton();
			number++;

			if (i == 0) {
				toggleBtn.setText((eng + "" + (number))); // 문자열로 변환시키기 위해서 ""를 추가
				toggleBtn.setBounds(x, y, width, height);
				seatLocation.add(toggleBtn);
				seatGroup.add(toggleBtn);

			} else { // i가 0이 아니면
				if (i % 3 == 0) {
					number = 1;
					x = 30;
					y += 95;
					eng += 1;
					toggleBtn.setText((eng + "" + (number)));
					toggleBtn.setBounds(x, y, width, height);
					seatLocation.add(toggleBtn);
					seatGroup.add(toggleBtn);

				} else {
					x += 120;
					toggleBtn.setText((eng + "" + (number)));
					toggleBtn.setBounds(x, y, width, height);
					seatLocation.add(toggleBtn);
					seatGroup.add(toggleBtn);
				}
			}

		} // end for - 좌석 수에 맞춰 토글버튼 생성

		JTextField exitDesk = new JTextField();
		exitDesk.setBorder(new LineBorder(Color.BLACK, 2));
		exitDesk.setBackground(new Color(0, 128, 0));
		exitDesk.setEditable(false);
		exitDesk.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		exitDesk.setHorizontalAlignment(SwingConstants.CENTER);
		exitDesk.setText("EXIT");
		exitDesk.setBounds(0, 395, 60, 25);
		seatLocation.add(exitDesk);
		exitDesk.setColumns(10);
		// 출입문 표시

		existSeat(enterSeatPanel, seatLocation, multipleSeat);
	} // end SittingList() - 생성자

	public ButtonGroup getSeatGroup() {
		return seatGroup;
	} // end getSeatGroup()

	/*
	 * DB의 좌석들을 확인하여 ID가 존재하면 선택할 수 없는 좌석으로 표시하는 기능
	 */
	private void existSeat(JPanel enterSeatPanel, JPanel seatLocation, int multipleSeat) {
		list = dao.select_ID_Desk(); // DB에서 좌석의 ID와 좌석번호를 가져옴
		int count = 0;

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				String dbID = list.get(i).getId();
				String deskNumber = list.get(i).getDeskNum();

				if (!dbID.equals("0")) { // ID가 0이 아니면 -> 좌석이 비어있지않으면
					Enumeration<AbstractButton> buttons = null;
					for (buttons = seatGroup.getElements(); buttons.hasMoreElements();) {
						AbstractButton button = buttons.nextElement(); // seatGroup의 좌석들의 데이터들을 하나씩 가져와서

						if (button.getActionCommand().equals(deskNumber)) { // 해당 좌석의 액션커맨드와 좌석번호가 같으면
							count++;
							button.setEnabled(false); // 해당 좌석을 이용 불가능하게 함.

							if (count == multipleSeat) { // 좌석이 모두 이용불가능할때(만석일때)
								seatLocation.setVisible(false); // 기존의 Panel을 보이지 않게하고 새로운 Panel로 대체

								JPanel fullPanel = new JPanel();
								fullPanel.setBounds(12, 85, 375, 420);
								fullPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
								fullPanel.setVisible(true);
								fullPanel.setLayout(null);
								enterSeatPanel.add(fullPanel);

								JLabel fullSeat = new JLabel();
								fullSeat.setBounds(140, 140, 150, 150);
								fullSeat.setFont(new Font("휴먼엑스포", Font.BOLD, 40));
								fullSeat.setText("만 석");
								fullPanel.add(fullSeat);
							}
						}
					} // end innerFor
				} // end if
			} // end outerFor
		}
	} // end existSeat

} // end SittingList
