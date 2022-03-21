package javaproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import javaproject.CalendarFunc;

public class ReservationAWT implements ActionListener {
	public static String id; // 전역변수
	public static int mode; // 전역변수
	
	
	JFrame jf = new JFrame();
	JLabel titleLb = new JLabel("예약하기");
	JLabel roomLb = new JLabel("룸 선택");
	JLabel rsRoomLb = new JLabel("선택하신 룸 : ");
	JLabel rsSDateLb = new JLabel("시작 일정 : ");
	JLabel rsEDateLb = new JLabel("종료 일정 : ");
	
	JLabel rsCapacityLb = new JLabel("예약 인원 : ");
	JTextField rsCapacityTf = new JTextField(10);
	
	JButton paymentBtn = new JButton("결제하기");
	JTextField rsRoomTf = new JTextField(10);
	JTextField rsSDateTf = new JTextField(10);
	JTextField rsEDateTf = new JTextField(10);
	
	JRadioButton roomBtn[];

	JPanel panel1 = new JPanel(); // 전체틀 중첩 보더
	JPanel panel2 = new JPanel(); // 전체틀 중첩 보더 센터 : 달력
	JPanel panel7 = new JPanel(); // 전체틀 중첩 보더1 북쪽 : 날짜 표시 및 이동
	JPanel panel8 = new JPanel(); // panel2의 중첩보더 (그리드 행 1)
	JPanel panel10 = new JPanel(); // panel2의 중첩보더인 panel8의 북쪽 : 날짜표시 및 이동
	JPanel panel12 = new JPanel(); // panel2의 중첩보더인 panel8의 센터 : 달력

	JPanel panel9 = new JPanel(); // panel2의 중첩보더 (그리드 행 2)
	JPanel panel11 = new JPanel(); // panel2의 중첩보더인 panel9의 북쪽 : 날짜표시 및 이동
	JPanel panel13 = new JPanel(); // panel2의 중첩보더인 panel9의 센터 : 달력

	JPanel panel3 = new JPanel(); // 전체틀 보더북쪽 타이틀 : "예약하기"
	JPanel panel4 = new JPanel(); // 전체틀 보더왼쪽 : "룸선택" 및 룸호수 선택
	JPanel panel5 = new JPanel(); // 전체틀 보더 오른쪽 : "선택하신 룸" 텍스트필드, "일정" 텍스트필드
	JPanel panel6 = new JPanel(); // 전체틀 보더 하단 : 결제하기 버튼
	
	JPanel panel14 = new JPanel(); // 마지막 행
	JPanel panel15 = new JPanel(); // 마지막 행의 1열
	JPanel panel16 = new JPanel(); // 마지막 행의 2열
	JPanel panel17 = new JPanel(); // 마지막 행의 2열의 보더레이아웃의 상단
	JPanel panel18 = new JPanel(); // 마지막 행의 2열의 보더레이아웃의 상단
	
	JButton cPlusBtn = new JButton("증가 (+)");
	JButton cMinusBtn = new JButton("감소 (-)");
	
	String date1;
	String date2;
	
	java.util.Date format1;
	java.util.Date format2;
	
	///////////////////////////////////////// 달력
	JButton sBeforeBtn = new JButton("Before");
	JButton eBeforeBtn = new JButton("Before");
	JButton sAfterBtn = new JButton("After");
	JButton eAfterBtn = new JButton("After");

	// 기본 셋팅 0000년 00월
	JLabel sLabel = new JLabel("0000년 00월");
	JLabel eLabel = new JLabel("0000년 00월");

	JButton[] sDayBtn = new JButton[49];
	String[] sDayName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	JButton[] eDayBtn = new JButton[49];
	String[] eDayName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

	// CalendarFunction 클래스로부터 sCF 객체 생성
	CalendarFunc sCF = new CalendarFunc(); // 시작일
	CalendarFunc eCF = new CalendarFunc(); // 종료일

	int rsSDate; // DB와 연동될 시작날짜 최종값 (YYYYMMDD)
	int rsEDate; // DB와 연동될 종료날짜 최종값 (YYYYMMDD)
	int rsRoom; // DB와 연동될 선택룸 최종값 (101, 102, 201, 202 중 하나)
	
	ReservationMgr rsMgr = new ReservationMgr();
	
	///////////////////////////////////////// 달력

	// 생성자
	public ReservationAWT() {

		// 기본 셋팅
		// jf.setSize(1200, 1000);

//		// 모니터 크기 
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		// 전체화면 (가로길이 : 모니터화면 너비, 세로길이 : 모니터화면 높이)
//		jf.setSize(screenSize.width,screenSize.height);
//		jf.setLocationRelativeTo(null);

		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setUndecorated(false);

		jf.setVisible(true);

		// 전체틀 레이아웃 지정
		jf.setLayout(new BorderLayout());

		// Panel 위치 지정
		jf.add(panel1, "Center");

		jf.add(panel3, "North");
		titleLb.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		panel3.add(titleLb);

		jf.add(panel4, "West");
		panel4.setLayout(new GridLayout(5, 1));
		panel4.add(roomLb);
		roomLb.setFont(new Font("맑은 고딕", Font.BOLD, 34));

		roomBtn = new JRadioButton[4];
		ButtonGroup bgr = new ButtonGroup();

		roomBtn[0] = new JRadioButton("101호", true);
		roomBtn[1] = new JRadioButton("102호", false);
		roomBtn[2] = new JRadioButton("201호", false);
		roomBtn[3] = new JRadioButton("202호", false);

		for (int i = 0; i < roomBtn.length; i++) {
			roomBtn[i].setFont(new Font("맑은 고딕", Font.PLAIN, 20));
			bgr.add(roomBtn[i]);
			panel4.add(roomBtn[i]);
			// 액션리스너 연결
			roomBtn[i].addActionListener(this);
		}

		panel5.setLayout(new GridLayout(8, 1)); /* 보더랑헷갈려서 헛짓..; */
		jf.add(panel5, "East");
		rsRoomLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel5.add(rsRoomLb);
		rsRoomTf.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel5.add(rsRoomTf);

		// 시작일정 텍스트필드
		rsSDateLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel5.add(rsSDateLb);
		rsSDateTf.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel5.add(rsSDateTf);

		// 종료일정 텍스트필드
		rsEDateLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel5.add(rsEDateLb);
		rsEDateTf.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		panel5.add(rsEDateTf);
		
		
		//////////////////////////////
		// 직접 텍스트필드에 적는 거 말고, 오름내림 버튼 만들어서 
		// 오름버튼 내림버튼에 액션리스너 달아서 각 호수선택된거에 따라 조건문 적기
		// 인원 텍스트필드 
		rsCapacityLb.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel5.add(rsCapacityLb);
		panel5.add(panel14); // 마지막 행에 panel14 추가 
		
		// panel5의 Grid 마지막 행에 panel14 1행 2열 그리드를 만든다.
		panel14.setLayout(new GridLayout(1,2)); 
		panel14.add(panel15); // 텍스트필드 들어갈 1열
		panel15.add(rsCapacityTf); 
	
		panel14.add(panel16); // 오르내림 버튼 들어갈 2열
		
		panel16.setLayout(new BorderLayout());
		
		panel17.add(cPlusBtn); // 오름 버튼
		panel18.add(cMinusBtn); // 내림 버튼
		
		panel16.add("North",panel17);
		panel16.add("South",panel18);
		
		
		paymentBtn.addActionListener(this);
		panel6.add(paymentBtn);
		paymentBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		jf.add(panel6, "South");

		// 중첩 레이아웃
		panel1.setLayout(new BorderLayout());
		panel1.add(panel7, "North");
		panel1.add(panel2, "Center");

		panel2.setLayout(new GridLayout(2, 1));
		panel2.add(panel8); // 1행
		panel2.add(panel9); // 2행

		// 시작일 달력
		panel8.setLayout(new BorderLayout());
		panel8.add(panel10, "North");
		panel8.add(panel12, "Center");

		// 가로로 늘어진 형태
		panel10.setLayout(new FlowLayout());
		panel10.add(sBeforeBtn);
		panel10.add(sLabel);
		panel10.add(sAfterBtn);

		// 종료일 달력
		panel9.setLayout(new BorderLayout());
		panel9.add(panel11, "North");
		panel9.add(panel13, "Center");

		// 가로로 늘어진 형태
		panel11.setLayout(new FlowLayout());
		panel11.add(eBeforeBtn);
		panel11.add(eLabel);
		panel11.add(eAfterBtn);

		/////////////////////////////
		// 시작일 달력 액션리스너 연결
		sAfterBtn.addActionListener(this);
		sBeforeBtn.addActionListener(this);

		Font font = new Font("SansSerif", Font.BOLD, 20);
		sAfterBtn.setFont(font);
		sBeforeBtn.setFont(font);
		sLabel.setFont(font);

		// setText : 초기화 -> 해당 달력의 년도와 월로 새로 셋팅
		sLabel.setText(sCF.setCalText());
		/////////////////////////////

		
		
		
		/////////////////////////////////////////////
		// 종료일 달력 액션리스너 연결
		eAfterBtn.addActionListener(this);
		eBeforeBtn.addActionListener(this);

		eAfterBtn.setFont(font);
		eBeforeBtn.setFont(font);
		eLabel.setFont(font);

		// setText : 초기화 -> 해당 달력의 년도와 월로 새로 셋팅
		eLabel.setText(eCF.setCalText());
		/////////////////////////////////////////////
		
		
		
		
		/////////////////////////////////
		// 시작일 달력 표시
		panel12.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < sDayBtn.length; i++) {
			sDayBtn[i] = new JButton(); // 버튼 배열 객체 생성
			// 액션리스너 연결
			sDayBtn[i].addActionListener(this);
			panel12.add(sDayBtn[i]);

			sDayBtn[i].setFont(new Font("SansSerif", Font.BOLD, 24));

			// 요일 배열 넣기
			if (i < 7)
				sDayBtn[i].setText(sDayName[i]);

			if (i % 7 == 0) // 일요일
				sDayBtn[i].setForeground(Color.RED);
			if (i % 7 == 6) // 토요일
				sDayBtn[i].setForeground(Color.BLUE);
		}

		sCF.setBtn(sDayBtn);
		sCF.setCal();
		/////////////////////////////////

		
		
		////////////////////////////////////////////
		// 종료일 달력 표시
		panel13.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < eDayBtn.length; i++) {
			eDayBtn[i] = new JButton(); // 버튼 배열 객체 생성
			// 액션리스너 연결
			eDayBtn[i].addActionListener(this);
			panel13.add(eDayBtn[i]);

			eDayBtn[i].setFont(new Font("SansSerif", Font.BOLD, 24));

			// 요일 배열 넣기
			if (i < 7)
				eDayBtn[i].setText(eDayName[i]);

			if (i % 7 == 0) // 일요일
				eDayBtn[i].setForeground(Color.RED);
			if (i % 7 == 6) // 토요일
				eDayBtn[i].setForeground(Color.BLUE);
		}

		eCF.setBtn(eDayBtn); //요일 셋팅
		eCF.setCal(); // 달력 버튼의 날짜 셋팅

		
		rsRoomTf.setText(roomBtn[0].getText());
		/////////////////////////////////////////////
	} // ---- 생성자
	

	// 액션이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		
		////////////////////////////////////////////
		for (int i = 0; i < roomBtn.length; i++) {
			if(e.getSource() == roomBtn[i]) {
				// 1. SELECT 예약내역테이블의 컬럼 WHERE 호수=? 
				// 
				// 2. 예약된 방은 달력 배경색 지정
				
				
			}
		}
		
		////////////////////////////////////////////
		
		
		/////////////////////////////////////////////
		// 룸번호 선택했을 때 tf에 입력
		for (int i = 0; i < roomBtn.length; i++) {
			if (e.getSource() == roomBtn[i]) {
				rsRoomTf.setText(roomBtn[i].getText());
			}
		}
		/////////////////////////////////////////////
		
		
		
		
		////////////////////////////////////////////
		// 시작일 달력 이벤트
		int sMove = 0;

		if (e.getSource() == sAfterBtn) { // 1달 후
			sMove = 1;
		} else if (e.getSource() == sBeforeBtn) { // 1달 전
			sMove = -1;
		}

		// cF객체의 allInit 메소드 호출
		sCF.startCal(sMove);

		// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
		sLabel.setText(sCF.setCalText());
		//////////////////////////////////////////////
		
		
		
		
		/////////////////////////////////////////////
		// 기능 구현할 거 : 날짜버튼 선택하면 setBackground 바뀌고, 비활성화

		for (int i = 7; i < sDayBtn.length; i++) {
			// 일자 시작하는 날부터
			if (sDayBtn[i].getText().length() > 0) {
				if (e.getSource() == sDayBtn[i]) {
					if(sLabel.getText().length()==8 && sDayBtn[i].getText().length() == 1 /*1. 두자리 월 + 한자리 일자 경우*/) {
						rsSDateTf.setText(sLabel.getText() + String.format("%02d", Integer.parseInt(sDayBtn[i].getText())) + "일");
					} else if(sLabel.getText().length()==8 && sDayBtn[i].getText().length() == 2/*2. 두자리 월 + 두자리 일자 경우*/) {
						rsSDateTf.setText(sLabel.getText() + sDayBtn[i].getText() + "일");
					} else if(sLabel.getText().length()==7 && sDayBtn[i].getText().length() == 1/*3. 한자리 월 + 한자리 일자 경우 */) {
						rsSDateTf.setText(sLabel.getText().substring(0,4)/*년도*/ + "년" + 
								String.format("%02d", Integer.parseInt(sLabel.getText().substring(5,6)))/*월 두자리수 맞춰서 가져오기*/ + "월" 
								+ String.format("%02d", Integer.parseInt(sDayBtn[i].getText())) + "일");
					}  else if(sLabel.getText().length()==7 && sDayBtn[i].getText().length() == 2) { /*4. 한자리 월 + 두자리 일자 경우*/
						rsSDateTf.setText(sLabel.getText().substring(0,4)/*년도*/ + "년" + 
								String.format("%02d", Integer.parseInt(sLabel.getText().substring(5,6)))/*월 두자리수 맞춰서 가져오기*/ + "월"
								+ sDayBtn[i].getText() + "일");
					}
				}
			}
		}

		////////////////////////////////////////////

		
		
		
		
		////////////////////////////////////////////
		// 종료일 달력 이벤트
		int eMove = 0;

		if (e.getSource() == eAfterBtn) { // 한달 후
			eMove = 1;
		} else if (e.getSource() == eBeforeBtn) { // 한달 전
			eMove = -1;
		}

		// cF객체의 allInit 메소드 호출
		eCF.startCal(eMove);

		// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
		eLabel.setText(eCF.setCalText());

		/////////////////////////////////////////////
		
		

		for (int i = 7; i < eDayBtn.length; i++) {
			// 일자 시작하는 날부터
			if (eDayBtn[i].getText().length() > 0) {
				if (e.getSource() == eDayBtn[i]) {
					
					/////////////////////////////////////
					
					if(eLabel.getText().length()==8 && eDayBtn[i].getText().length() == 1 /*1. 두자리 월 + 한자리 일자 경우*/) {
						rsEDateTf.setText(eLabel.getText() + String.format("%02d", Integer.parseInt(eDayBtn[i].getText())) + "일");
					} else if(eLabel.getText().length()==8 && eDayBtn[i].getText().length() == 2/* 2. 두자리 월 + 두자리 일자 경우*/) {
						rsEDateTf.setText(eLabel.getText() + eDayBtn[i].getText() + "일");
					} else if(eLabel.getText().length()==7 && eDayBtn[i].getText().length() == 1/*3. 한자리 월 + 한자리 일자 경우 */) {
						rsEDateTf.setText(eLabel.getText().substring(0,4)/*년도*/ + "년" + 
								String.format("%02d", Integer.parseInt(eLabel.getText().substring(5,6)))/* 두자리수 맞춰서 가져오기*/ + "월" 
								+ String.format("%02d", Integer.parseInt(eDayBtn[i].getText())) + "일");
					}  else if(eLabel.getText().length()==7 && eDayBtn[i].getText().length() == 2) { /*4. 한자리 월 + 두자리 일자 경우*/
						rsEDateTf.setText(eLabel.getText().substring(0,4)/*년도*/ + "년" + 
								String.format("%02d", Integer.parseInt(eLabel.getText().substring(5,6))) + "월"
								+ eDayBtn[i].getText() + "일");
					}
					
					/////////////////////////////////////
				}
			}
		}

		
		/////////////////////////////////////////////
		
		if (e.getSource() == paymentBtn) { /*paymentBtn에 액션리스너 연결 안했어서 계속 에러ㅠ*/
			
				System.out.println("무조건 떠야함");
				
				// 0~9 이외의 모든 문자 제거한 int
				rsRoom = Integer.parseInt(rsRoomTf.getText().replaceAll("[^0-9]", ""));
				rsSDate = Integer.parseInt(rsSDateTf.getText().replaceAll("[^0-9]", "")); 
				rsEDate = Integer.parseInt(rsEDateTf.getText().replaceAll("[^0-9]", "")); 
			
				System.out.println("선택하신 룸 : " + rsRoom + "\n" + "시작날짜 : "+ rsSDate + "\n" 
						+ "종료날짜 : " + rsEDate + "\n");
			
		        date1 = rsEDateTf.getText().replaceAll("[^0-9]", ""); // String
		        date2 = rsSDateTf.getText().replaceAll("[^0-9]", ""); // String
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date로 바꾸는 클래스
		       
		        try {
					format1 = sdf.parse(date1); // java.util.date yyyyMMdd 형변환
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		        try {
					format2 = sdf.parse(date2); // java.util.date yyyyMMdd 형변환
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

		        long diffSec = (format1.getTime() - format2.getTime()) / 1000; // 초 차이
		        long diffDays = diffSec / (24*60*60); // 일자수 차이
		        
		        System.out.println(diffDays + "일 차이");
				
		        
		        //////////////////////////////////////////여기서부터!! 다시작업
		        
		        // java.util.date -> java.sql.date 변환
			    java.sql.Date sqlDate = new java.sql.Date(format2.getTime());
		        
		        Calendar cal = Calendar.getInstance(); // 캘린더 클래스의 인스턴스 가져오기
				cal.setTime(sqlDate); // 캘린더에 선택한 시작날짜 설정
				System.out.println("선택하신 시작 날짜 : " + sqlDate);
				
				//////////////////////////////////////////여기서부터!! 다시작업
				
				
				for (int j = 0; j < diffDays/*SELECT 될 레코드 개수*/; j++) { // 달다른거 고려해주기
					if (rsMgr.dateChk(rsRoom,sqlDate,sqlDate) /*true 반환 -> 중복일정 존재한다는 의미 */) {
						// Ex. 20220304 ~ 20220307 예약했는데, 3/6~3/7 중복된 경우 
						// 1. 지금까지 위 반복문에 의해 insert 되었던 행을 삭제,
						// 2. 중복된 즉시 break로 반복문 빠져나간다
						
						for (int k = j; k >0; k--) {
							
							cal.add(Calendar.DATE, -1); // 1일 감소 시키기
							// 그냥 getTime 하면 시간까지 불러와져서 sdf로 형식 지정 후 String으로 가져오기
							String minusSqlDate = sdf.format(cal.getTime());
							System.out.println("선택하신 시작 날짜에서 하루를 감소시킨 날짜(스트링) : " + minusSqlDate);
							try {
								format2 = sdf.parse(minusSqlDate); // String -> java.util.date 변환
								sqlDate = new java.sql.Date(format2.getTime()); // java.util.date -> java.sql.date 변환
								System.out.println("선택하신 시작 날짜에서 하루를 감소시킨 날짜(sql데이트) : " + sqlDate);
							} catch (Exception e2) {
								e2.printStackTrace();
							}
							
							rsMgr.deleteDate(rsRoom, sqlDate, sqlDate);	
						}
						System.out.println("중복되는 일정이 있으므로 선택하신 일정이 모두 예약취소 되었습니다. 다시 선택해주십시오.");
						break;
		
					} else {
						// DB와 중복되지 않는 날짜 선택했다면 false 반환
						// DB INSERT 
						rsMgr.testInsertDate(rsRoom, sqlDate, sqlDate);
						
						// INSERT 완료 후 sqlDate 1일씩 증가시켜서 예약 마지막날까지 반복문 돌게 한다.
						cal.add(Calendar.DATE, 1); // 1일 증가 시키기
						
						// 그냥 getTime 하면 시간까지 불러와져서 sdf로 형식 지정 후 String으로 가져오기
						String plusSqlDate = sdf.format(cal.getTime());
						System.out.println("선택하신 시작 날짜에서 하루를 증가시킨 날짜 : " + plusSqlDate);
						try {
							format2 = sdf.parse(plusSqlDate);// String -> java.util.date 변환
							sqlDate = new java.sql.Date(format2.getTime()); // java.util.date -> java.sql.date 변환
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						
						System.out.println("중복되는 일정없이 무사히 예약되셨습니다.");
					}
				}
			}
		/////////////////////////////////////////////
		} // -- 액션이벤트
	
	
	
	public static void main(String[] args) {
		// 생성자 호출
		new ReservationAWT();
	}

}
