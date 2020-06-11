package edu.java.studyroom;

//MenuJTabaleExam.java
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import edu.java.dao.UserDefaultJTableDAOImple;


public class MenuJTableExam extends JFrame implements ActionListener {
	
  JMenu m = new JMenu("관리");
  JMenuItem insert = new JMenuItem("등록");
  JMenuItem update = new JMenuItem("수정");
  JMenuItem delete = new JMenuItem("삭제");
  JMenuItem quit = new JMenuItem("종료");
  JMenuBar mb = new JMenuBar();
  
  JSeparator separator = new JSeparator(); // 분리선

  String[] name = { "id", "pw", "name", "renumber"}; // 테이블 내용물

  DefaultTableModel dt = new DefaultTableModel(name, 0);
  JTable jt = new JTable(dt);
  JScrollPane jsp = new JScrollPane(jt);

  /*
   * South 영역에 추가할 Component들
   */
  JPanel p = new JPanel();
  String[] comboName = { "  ALL  ", "  ID  ", " name ", " renumber " }; // 콤보상자 내용물

  JComboBox combo = new JComboBox(comboName); // 콤보상자 표시
  JTextField jtf = new JTextField(20); // 검색창
  JButton btnSerach = new JButton("검색"); // 검색 버튼

  UserDefaultJTableDAOImple dao = new UserDefaultJTableDAOImple();

  /**
   * 화면구성 및 이벤트등록
   */
  public MenuJTableExam() {
     
      super("관리자 메뉴");

      setResizable(false);
      
      //메뉴아이템을 메뉴에 추가
      
      m.add(insert); // 등록
      m.add(update); // 수정
      m.add(delete); // 삭제
      m.add(separator); // 분리선(-)
      m.add(quit); // 종료
      
      //메뉴를 메뉴바에 추가
      
      mb.add(m);
     
      //윈도우에 메뉴바 세팅
      
      setJMenuBar(mb);

     
      // South영역
      p.setBackground(Color.DARK_GRAY);
      p.add(combo);
      p.add(jtf);
      p.add(btnSerach);

      add(jsp, "Center");
      add(p, "South");

      setSize(500, 400);
      setVisible(true);

      super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

      // 이벤트등록
      insert.addActionListener(this);
      update.addActionListener(this);
      delete.addActionListener(this);
      quit.addActionListener(this);
      btnSerach.addActionListener(this);

      // 모든레코드를 검색하여 DefaultTableModle에 올리기
      dao.userSelectAll(dt);
     
      //첫번째행 선택 -> 테이블 내용행의 수가 0보다 크면(한 개 이상 있으면) 첫번 째 행(index(0)) 선택
      if (dt.getRowCount() > 0)
          jt.setRowSelectionInterval(0, 0);

  }// 생성자끝

  
  /**
   * 등록/수정/삭제/검색기능을 담당하는 메소드
   * */

  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == insert) {// 등록 메뉴아이템 클릭
          new UserJDailogGUI(this, "등록");

      } else if (e.getSource() == update) {// 수정 메뉴아이템 클릭
          new UserJDailogGUI(this, "수정");

      } else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
          // 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
          int row = jt.getSelectedRow();
          System.out.println("선택행 : " + row);

          Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
          System.out.println("값 : " + obj);

          if (dao.userDelete(obj.toString()) > 0) {
              UserJDailogGUI.messageBox(this, "삭제되었습니다.");
             
              //리스트 갱신
              dao.userSelectAll(dt);
              if (dt.getRowCount() > 0)
                  jt.setRowSelectionInterval(0, 0);

          } else {
              UserJDailogGUI.messageBox(this, "삭제되지 않았습니다.");
          }

      } else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
          System.exit(0);

      } else if (e.getSource() == btnSerach) {// 검색 버튼 클릭
          // JComboBox에 선택된 value 가져오기
          String fieldName = combo.getSelectedItem().toString();
          System.out.println("필드명 " + fieldName);

          if (fieldName.trim().equals("ALL")) {// 전체검색
              dao.userSelectAll(dt);
              if (dt.getRowCount() > 0)
                  jt.setRowSelectionInterval(0, 0);
          } else {
              if (jtf.getText().trim().equals("")) {
                  UserJDailogGUI.messageBox(this, "검색단어를 입력해주세요!");
                  jtf.requestFocus();
              } else {// 검색어를 입력했을경우
                  dao.getUserSearch(dt, fieldName, jtf.getText());
                  if (dt.getRowCount() > 0)
                      jt.setRowSelectionInterval(0, 0);
              }
          }
      }

  }//actionPerformed()----------
}
