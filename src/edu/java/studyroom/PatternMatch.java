package edu.java.studyroom;

import java.util.regex.Pattern;

public class PatternMatch {
	
	public static String matchId(String id) {
		String result = null;
		String regExp = null;
		
		regExp = "^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,20}$";  // 숫자와 영문만 가능, 4~20자리 (ID)
		boolean check = Pattern.matches(regExp, id);
		if (check == false) { // ID 매치 안됨
			result = "4~20자리의 영문 소문자, 영문+숫자를 사용하세요.";
			return result;
		}
		return result;
	} // end matchId
	
	public static int matchInfo(String id, String pw, String confirmPw, String name, String renum) {
		int result = 0;
		String regExp = null;
		
		regExp = "^(?!(?:[0-9]+)$)([a-zA-Z]|[0-9a-zA-Z]){4,20}$";  // 숫자와 영문만 가능, 4~20자리 (ID)
		boolean check = Pattern.matches(regExp, id);
		if (check == false) { // ID 매치 안됨
			result = 1;
			return result;
		}
		
		if(pw.equals(confirmPw)) {
			regExp = "^(?=.*\\d{1,})(?=.*[~`!@#$%\\^&*()-+=]{1,})(?=.*[a-zA-Z]{1,})(?=.*[a-zA-Z]{1,}).{8,20}$";
			// 숫자와 특수문자는 최소한 1개 이상, 영문은 최소한 2개 이상을 사용하여 8~20자리의 비밀번호 입력 (PW)
			check = Pattern.matches(regExp, pw);
			if (check == false) { // PW 매치 안됨
				result = 3;
				return result;
			}
		} else {
			result = 2;
			return result;
		}
		
		
		regExp = "^[가-힣]{2,4}|[a-zA-Z]{2,10}$";
		// 한글은 2 ~ 4글자, 영문은 (2~10글자)로 입력 (이름)
		if(name.equals("이름 입력")) {
			result = 6;
			return result;
		} else {
			check = Pattern.matches(regExp, name);
			if (check == false) { // 이름 매치 안됨
				result = 4;
				return result;
			}
		}
		

		regExp = "^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$";
		// 주민번호 - 로 구분해서 13자리 (주민등록번호)
		check = Pattern.matches(regExp, renum);
		if (check == false) { // 주민번호 매치 안됨
			result = 5;
			return result;
		}
		
		return result;
	} // end matchInfo

} // end PatternMatch
