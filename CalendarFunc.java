package javaproject;

import java.util.Calendar;

import javax.swing.JButton;

public class CalendarFunc {
	// Calendar Ŭ�����κ��� �ν��Ͻ� ��������
	Calendar cal = Calendar.getInstance();
	int year, month;
	
	public CalendarFunc() {
		year = cal.get(Calendar.YEAR); // �⵵ ��������
		// Ķ���� Ŭ���� 1�� : 0 -> +1 ����� ���� ���� ��
		month = cal.get(Calendar.MONTH) + 1; // �� �������� 
	}
	
	// �޷��� ���� ��ư �迭
	JButton dayBtns[];
	
	// �ش� ���� ���� ��������
	public void setBtn(JButton[] btns) {
		this.dayBtns = btns;
	}
	
	public String setCalText() {
		return year + "��" + String.format("%02d", month) + "��";
	}

	// �޷� ��ư�� ��¥ ����
	public void setCal() {
		// Cal.set( YEAR �� , MONTH �� , DAY ��)
		cal.set(year, Integer.parseInt(String.format("%02d", month-1)/*�� �ڸ� ���� ��� 0 �ٿ���*/), 1); // 1��
		
		// �ش� ���� 1���� ���� ��������
		int firstDay = cal.get(Calendar.DAY_OF_WEEK); 
		
		// DAY_OF_WEEK : 1~7 ���� �����ϹǷ� ���� �迭(sDayName,eDayName)�� �����ַ��� -1 ó��
		firstDay--;
		
		for(int i = 1; i <= cal.getActualMaximum(cal.DATE); i++) {
			// dayBtns[0] ~ [6] : ����, dayBtns[7] ~ ��: ���� ����
			dayBtns[6 + firstDay + i].setText(String.valueOf(i));
		}			
	}
	
	
	public void startCal(int move) {
		// dayBtns[0] ~ [6] : ����
		for(int i =7; i < dayBtns.length; i++) {
			// ������ ���ΰ� ���� ��ư ��� �ʱ�ȭ
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