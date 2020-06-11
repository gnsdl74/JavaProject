package edu.java.dao;

import javax.swing.table.DefaultTableModel;

import edu.java.studyroom.UserJDailogGUI;

public interface UserDefaultTableDAO {
	 public boolean getIdByCheck(String id);
	 public int userListInsert(UserJDailogGUI user);
	 public void userSelectAll(DefaultTableModel t_model);
	 public int userDelete(String id);
	 public int userUpdate(UserJDailogGUI user);
	 public void getUserSearch(DefaultTableModel dt, String fieldName, String word);
}
