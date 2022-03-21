package javaproject;

import java.util.Calendar;

import javax.swing.JButton;

public class CalendarFunc {
	// Calendar 클래스로부터 인스턴스 가져오기
	Calendar cal = Calendar.getInstance();
	int year, month;
	
	public CalendarFunc() {
		year = cal.get(Calendar.YEAR); // 년도 가져오기
		// 캘린더 클래스 1월 : 0 -> +1 해줘야 실제 월이 됨
		month = cal.get(Calendar.MONTH) + 1; // 월 가져오기 
	}
	
	// 달력의 일자 버튼 배열
	JButton dayBtns[];
	
	// 해당 월의 일자 가져오기
	public void setBtn(JButton[] btns) {
		this.dayBtns = btns;
	}
	
	public String setCalText() {
		return year + "년" + String.format("%02d", month) + "월";
	}

	// 달력 버튼의 날짜 셋팅
	public void setCal() {
		// Cal.set( YEAR 값 , MONTH 값 , DAY 값)
		cal.set(year, Integer.parseInt(String.format("%02d", month-1)/*한 자리 월의 경우 0 붙여서*/), 1); // 1일
		
		// 해당 월의 1일의 요일 가져오기
		int firstDay = cal.get(Calendar.DAY_OF_WEEK); 
		
		// DAY_OF_WEEK : 1~7 값을 리턴하므로 요일 배열(sDayName,eDayName)과 맞춰주려면 -1 처리
		firstDay--;
		
		for(int i = 1; i <= cal.getActualMaximum(cal.DATE); i++) {
			// dayBtns[0] ~ [6] : 요일, dayBtns[7] ~ 끝: 일자 셋팅
			dayBtns[6 + firstDay + i].setText(String.valueOf(i));
		}			
	}
	
	
	public void startCal(int move) {
		// dayBtns[0] ~ [6] : 요일
		for(int i =7; i < dayBtns.length; i++) {
			// 요일은 놔두고 일자 버튼 모두 초기화
			dayBtns[i].setText("");
		}
		
		month += move;
		
		if(month <= 0) {
			year--;
			month = 12;
		} else if(month >= 13) {
			year++;
			month = 1;
		}
		setCal();
	}		
}