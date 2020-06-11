package edu.java.studyroom;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.java.dao.UserDefaultJTableDAOImple;

public class UserJDailogGUI extends JDialog implements ActionListener{
	
	// FlowLayout : 순서대로 배치 시킨다.
	// BorderLayout : 동서남북으로 배치시킨다.
	// GridLayout : x,y 로 배치시킨다.
	// CardLayout : 겹쳐 보이게 배치시킨다.

  JPanel pwest = new JPanel(new GridLayout(4,1));
  JPanel pcenter = new JPanel(new GridLayout(4,1));
  JPanel psouth = new JPanel();

  JLabel lblId = new JLabel("ID");
  JLabel lblPw = new JLabel("PW");
  JLabel lblName = new JLabel("이름");
  JLabel lblRenumber = new JLabel("주민등록번호");


  public JTextField id = new JTextField();
  public JTextField pw = new JTextField();
  public JTextField name = new JTextField();
  public JTextField renumber = new JTextField();
 

  JButton confirm;
  JButton reset = new JButton("취소");

 MenuJTableExam mejt;

 JPanel idCkP =new JPanel(new BorderLayout());
 JButton idCkBtn = new JButton("IDCheck");
 
 UserDefaultJTableDAOImple dao =new UserDefaultJTableDAOImple();
 

  public UserJDailogGUI(MenuJTableExam menu, String index){
      this.mejt=menu;
      if(index.equals("등록")){
    	  setTitle("회원등록");
          confirm=new JButton(index);
      }else{
    	  setTitle("회원수정");
          confirm=new JButton("수정"); 
         
          //text박스에 선택된 레코드의 정보 넣기
          int row = menu.jt.getSelectedRow();//선택된 행
          id.setText( menu.jt.getValueAt(row, 0).toString() );
          pw.setText( menu.jt.getValueAt(row, 1).toString() );
          name.setText( menu.jt.getValueAt(row, 2).toString() );
          renumber.setText( menu.jt.getValueAt(row, 3).toString() );
         
          //id text박스 비활성
          id.setEditable(false);
 
          //IDCheck버튼 비활성화
          idCkBtn.setEnabled(false);
      }
     
     
      //Label추가부분
      pwest.add(lblId); // ID
      lblId.setFont(lblId.getFont().deriveFont(15.0f)); // 글자 크기 변경
      pwest.add(lblPw); // PW
      lblPw.setFont(lblPw.getFont().deriveFont(15.0f));
      pwest.add(lblName); // 이름
      lblName.setFont(lblName.getFont().deriveFont(15.0f));
      pwest.add(lblRenumber); //주민등록번호
      lblRenumber.setFont(lblRenumber.getFont().deriveFont(15.0f));
 
     
      idCkP.add(id,"Center");
      idCkP.add(idCkBtn,"East");
     
      //TextField 추가
      pcenter.add(idCkP);
      pcenter.add(pw);
      pcenter.add(name);
      pcenter.add(renumber);
     
     
     
      psouth.add(confirm);
      psouth.add(reset);
 
      add(pwest,"West");
      add(pcenter,"Center");
      add(psouth,"South");
     
      setSize(400,300);
      setVisible(true);
      setLocationRelativeTo(null);

      setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
     
      //이벤트등록
      confirm.addActionListener(this); // 등록/수정 이벤트등록
      reset.addActionListener(this); // 취소 이벤트등록
      idCkBtn.addActionListener(this);// ID중복체크 이벤트 등록
     
  }//생성자끝
 
  /**
   * 등록/수정/삭제 기능에 대한 부분
   * */
  @Override
  public void actionPerformed(ActionEvent e) {
     String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
     
     if(btnLabel.equals("등록")){
    	 if(id.getText().trim().equals("")){
             messageBox(this,"ID를 입력해주세요.");
             id.requestFocus();//포커스이동
             return;
    	 }
    	 if(pw.getText().trim().equals("")){
             messageBox(this,"비밀번호를 입력해주세요.");
             pw.requestFocus();//포커스이동
             return;
    	 }
    	 if(name.getText().trim().equals("")){
             messageBox(this,"이름을 입력해주세요.");
             name.requestFocus();//포커스이동
             return;
    	 }
    	 if(renumber.getText().trim().equals("")){
             messageBox(this,"주민등록번호를 입력해주세요.");
             renumber.requestFocus();//포커스이동
             return;
    	 }
    	 if(renumber.getText().length() >= 15) { // 주민등록번호 글자수 제한(14글자) -> 14글자를 넘었을때
    		 JOptionPane.showMessageDialog(this, "정해진 글자수를 초과하였습니다."
    		 		+ "\n초과한 글자는 초기화됩니다.", "경고", JOptionPane.ERROR_MESSAGE);
    		 renumber.setText(renumber.getText().substring(0, 14)); // 14글자까지만 출력
    		 return;
    	 }
    	 if(renumber.getText().length() < 14) { // 주민등록번호 글자수 제한(14글자) -> 14글자보다 적을때
    		 JOptionPane.showMessageDialog(this, "글자수를 확인하십시오."
    		 		+ "\n글자는 초기화됩니다.", "경고", JOptionPane.ERROR_MESSAGE);
    		 renumber.setText(renumber.getText().substring(0, 0)); // 초기화
    		 return;
    	 }
         if(dao.userListInsert(this) > 0 ){ // 등록성공
             messageBox(this , id.getText()+"님이 등록되었습니다.");
             dispose(); // JDialog 창닫기
             
             dao.userSelectAll(mejt.dt); // 모든레코드가져와서 DefaultTableModel에 올리기
             
             if(mejt.dt.getRowCount() > 0)
                 mejt.jt.setRowSelectionInterval(0, 0); // 첫번째 행 선택
             
         }else{ // 등록실패
             messageBox(this,"등록되지 않았습니다.");
         }
         
     }else if(btnLabel.equals("수정")){
    	 if(pw.getText().trim().equals("")){
             messageBox(this,"비밀번호를 입력해주세요.");
             pw.requestFocus();//포커스이동
             return;
    	 }
    	 if(name.getText().trim().equals("")){
             messageBox(this,"이름을 입력해주세요.");
             name.requestFocus();//포커스이동
             return;
    	 }
    	 if(renumber.getText().trim().equals("")){
             messageBox(this,"주민등록번호를 입력해주세요.");
             renumber.requestFocus();//포커스이동
             return;
    	 }    
    	 if(renumber.getText().length() >= 15) { // 주민등록번호 글자수 제한(14글자) -> 14글자를 넘었을때
    		 JOptionPane.showMessageDialog(this, "정해진 글자수를 초과하였습니다."
    		 		+ "\n초과한 글자는 초기화됩니다.", "경고", JOptionPane.ERROR_MESSAGE);
    		 renumber.setText(renumber.getText().substring(0, 14)); // 14글자까지만 출력
    		 return;
    	 }
    	 if(renumber.getText().length() < 14) { // 주민등록번호 글자수 제한(14글자) -> 14글자보다 적을때
    		 JOptionPane.showMessageDialog(this, "글자수를 확인하십시오."
    		 		+ "\n글자는 초기화됩니다.", "경고", JOptionPane.ERROR_MESSAGE);
    		 renumber.setText(renumber.getText().substring(0, 0)); // 초기화
    		 return;
    	 }
          if( dao.userUpdate(this) > 0){
              messageBox(this, "수정완료되었습니다.");
              dispose();
              dao.userSelectAll(mejt.dt);
              if(mejt.dt.getRowCount() > 0 ) mejt.jt.setRowSelectionInterval(0, 0);
             
          }else{
              messageBox(this, "수정되지 않았습니다.");
          }
         
         
         
     }else if(btnLabel.equals("취소")){
         dispose();
         
     }else if(btnLabel.equals("IDCheck")){
         //id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
         if(id.getText().trim().equals("")){
             messageBox(this,"ID를 입력해주세요.");
             id.requestFocus();//포커스이동
         }else{
             
            if(  dao.getIdByCheck(id.getText()) ){ //중복아니다.(사용가능)
                messageBox(this, id.getText()+"는 사용가능합니다.");  
            }else{ //중복이다.
                messageBox(this,id.getText()+"는 중복입니다.");
               
                id.setText(""); // text박스지우기
                id.requestFocus(); // 커서놓기
            }
             
         }
         
     }
     
     
  }//actionPerformed끝
 
  /**
   * 메시지박스 띄워주는 메소드 작성
   * */
  public static void messageBox(Object obj , String message){
      JOptionPane.showMessageDialog( (Component)obj , message);
  }

}//클래스끝
