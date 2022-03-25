package javaproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calendar.CalendarFunction;

public class CalendarMain extends JFrame implements ActionListener, ItemListener {

	Container container = getContentPane();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();

	JButton buttonBefore = new JButton("Before");
	JButton buttonAfter = new JButton("After");

	// 기본 셋팅 00년 0월
	JLabel label = new JLabel("00년 0월");

	// 7 * 7 = 49개의 버튼 생성
	JButton[] buttons = new JButton[49];
	String[] dayNames = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };

	// CalendarFunction 클래스로부터 cf 객체 생성
	CalendarFunction cF = new CalendarFunction();

	JTextField dateTf = new JTextField(10);

	// 생성자
	public CalendarMain() {

		// 기본 셋팅
		setTitle("만년 달력");
		setSize(550, 400);
		setLocation(400, 400);

		// 메소드 호출
		init();
		start();

		setVisible(true);

	}

	private void start() {
		// 도우창 종료 시 프로세스까지 깔끔하게 닫을 수 있다.
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 액션리스너 연결
		buttonAfter.addActionListener(this);
		buttonBefore.addActionListener(this);

	}

	private void init() {
		container.setLayout(new BorderLayout());
		container.add("North", panel1);
		container.add("Center", panel2);

		// 가로로 늘어진 형태
		panel1.setLayout(new FlowLayout());
		panel1.add(buttonBefore);
		panel1.add(label);
		panel1.add(buttonAfter);
		panel1.add(dateTf);

		Font font = new Font("SansSerif", Font.BOLD, 20);
		buttonAfter.setFont(font);
		buttonBefore.setFont(font);
		label.setFont(font);

		// 00년 0월 -> 텍스트 초기화 -> 날짜텍스트 가져오기
		label.setText(cF.getCalText());
		// 5,5 : 가로 행과 세로 열 그리고 행의 간격과 열의 간격
		panel2.setLayout(new GridLayout(7, 7, 5, 5));

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(); // 버튼 배열 객체 생성
			// 액션리스너 연결
			buttons[i].addActionListener(this);
			panel2.add(buttons[i]);

			buttons[i].setFont(new Font("SansSerif", Font.BOLD, 24));

			// 0~6번째 버튼 배열 원소에 datNames 배열 순서대로 넣기
			if (i < 7)
				buttons[i].setText(dayNames[i]);

			if (i % 7 == 0)
				buttons[i].setForeground(Color.RED);
			if (i % 7 == 6)
				buttons[i].setForeground(Color.BLUE);
		}
		cF.setButtons(buttons);
		cF.calSet();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int gap = 0;

		if (e.getSource() == buttonAfter) { // 1달 후
			gap = 1;
		} else if (e.getSource() == buttonBefore) { // 1달 전
			gap = -1;
		}

		// cF객체의 allInit 메소드 호출
		cF.allInit(gap);

		// 년월 글자 갱신
		label.setText(cF.getCalText());

		// 날짜버튼 선택하면 setBackground 바뀌고, 비활성화

		for (int i = 7; i < buttons.length; i++) {
			// 일자 시작하는 날부터
			if (buttons[i].getText().trim().length() > 0) {
				if (e.getSource() == buttons[i]) {
					
					
				}
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}

	public static void main(String[] args) {
		new CalendarMain();
	}

}
