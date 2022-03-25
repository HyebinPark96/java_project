package javaproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import javax.swing.BorderFactory;
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

public class ReservationAWT{
	private JFrame jf;
	private String r_status;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationAWT reservationAwt = new ReservationAWT();
					reservationAwt.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public ReservationAWT(String userId) {
		jf = new JFrame();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setUndecorated(false);
		jf.setVisible(true);

		// 전체틀 레이아웃 지정
		jf.setLayout(new BorderLayout());
		
		Color bkColor = new Color(253,253,246); // 백그라운드 색상
		Color c1 = new Color(184,178,166); 
		Color c2 = new Color(187,153,129); 
		Color c3 = new Color(219,233,183); 
		Color c4 = new Color(244,218,218); 
		
		Font f1 = new Font("나눔고딕", Font.BOLD, 20); 
		Font f2 = new Font("나눔고딕", Font.PLAIN, 15);
		Font f3 = new Font("나눔고딕", Font.PLAIN, 6); 
		Font f4 = new Font("나눔고딕", Font.BOLD, 30); 
		Font f5 = new Font("나눔고딕", Font.PLAIN, 20);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR); // 클릭 커서 모양
		
		JLabel titleLb = new JLabel("예약하기");
		titleLb.setForeground(c1);
		JLabel roomLb = new JLabel("   룸 선택   ");
		roomLb.setForeground(c1);
		JLabel rsRoomLb = new JLabel("선택하신 룸 : ");
		rsRoomLb.setForeground(c1);
		JLabel rsSDateLb = new JLabel("시작 일정 : ");
		rsSDateLb.setForeground(c1);
		JLabel rsEDateLb = new JLabel("종료 일정 : ");
		rsEDateLb.setForeground(c1);
		
		JLabel rsHeadcountLb = new JLabel("예약 인원 : ");
		rsHeadcountLb.setForeground(c1);
		JTextField rsHeadcountTf = new JTextField();
		
		JButton paymentBtn = new JButton("결제하기");
		paymentBtn.setForeground(c1);
		paymentBtn.setBorder(BorderFactory.createLineBorder(c4,8));
		paymentBtn.setCursor(cursor);
		
		JTextField rsRoomTf = new JTextField();
		// rsRoomTf.setBackground(lbColor);
		rsRoomTf.setForeground(c2);
		JTextField rsSDateTf = new JTextField();
		// rsSDateTf.setBackground(lbColor);
		rsSDateTf.setForeground(c2);
		JTextField rsEDateTf = new JTextField();
		// rsEDateTf.setBackground(lbColor);
		rsEDateTf.setForeground(c2);
		
		JRadioButton roomBtn[];
		
		

		JPanel panel1 = new JPanel(); // 전체틀 중첩 보더
		panel1.setBackground(bkColor);
		panel1.setOpaque(true);
		JPanel panel2 = new JPanel(); // 전체틀 중첩 보더 센터 : 달력
		panel2.setBackground(bkColor);
		panel2.setOpaque(true);
		JPanel panel7 = new JPanel(); // 전체틀 중첩 보더1 북쪽 : 날짜 표시 및 이동
		panel7.setBackground(bkColor);
		panel7.setOpaque(true);
		JPanel panel8 = new JPanel(); // panel2의 중첩보더 (그리드 행 1)
		panel8.setBackground(bkColor);
		panel8.setOpaque(true);
		JPanel panel10 = new JPanel(); // panel2의 중첩보더인 panel8의 북쪽 : 날짜표시 및 이동
		panel10.setBackground(bkColor);
		panel10.setOpaque(true);
		JPanel panel12 = new JPanel(); // panel2의 중첩보더인 panel8의 센터 : 달력
		panel12.setBackground(bkColor);
		panel12.setOpaque(true);

		JPanel panel9 = new JPanel(); // panel2의 중첩보더 (그리드 행 2)
		panel9.setBackground(bkColor);
		panel9.setOpaque(true);
		JPanel panel11 = new JPanel(); // panel2의 중첩보더인 panel9의 북쪽 : 날짜표시 및 이동
		panel11.setBackground(bkColor);
		panel11.setOpaque(true);
		JPanel panel13 = new JPanel(); // panel2의 중첩보더인 panel9의 센터 : 달력
		panel13.setBackground(bkColor);
		panel13.setOpaque(true);

		JPanel panel3 = new JPanel(); // 전체틀 보더북쪽 타이틀 : "예약하기"
		panel3.setBackground(bkColor);
		panel3.setOpaque(true);
		JPanel panel4 = new JPanel(); // 전체틀 보더왼쪽 : "룸선택" 및 룸호수 선택
		panel4.setBackground(bkColor);
		panel4.setOpaque(true);
		JPanel panel5 = new JPanel(); // 전체틀 보더 오른쪽 : "선택하신 룸" 텍스트필드, "일정" 텍스트필드
		panel5.setBackground(bkColor);
		panel5.setOpaque(true);
		JPanel panel6 = new JPanel(); // 전체틀 보더 하단 : 결제하기 버튼
		panel6.setBackground(bkColor);
		panel6.setOpaque(true);
		
		JButton cPlusBtn = new JButton("증가 (+)");
		cPlusBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		cPlusBtn.setBackground(c4);
		cPlusBtn.setForeground(c1);
		cPlusBtn.setCursor(cursor);
		cPlusBtn.setBorder(BorderFactory.createLineBorder(c4,8));
		
		JButton cMinusBtn = new JButton("감소 (-)");
		cMinusBtn.setFont(new Font("나눔고딕", Font.BOLD, 18));
		cMinusBtn.setBackground(c4);
		cMinusBtn.setForeground(c1);
		cMinusBtn.setCursor(cursor);
		cMinusBtn.setBorder(BorderFactory.createLineBorder(c4,8));
		
		String date1;
		String date2;
		
		java.util.Date format1;
		java.util.Date format2;
		
		int sMove;
		int eMove;
		
		JButton sBeforeBtn = new JButton("   Before   ");
		JButton eBeforeBtn = new JButton("   Before   ");
		JButton sAfterBtn = new JButton("   After   ");
		JButton eAfterBtn = new JButton("   After   ");

		// 기본 셋팅 0000년 00월
		JLabel sLabel = new JLabel("0000년 00월");
		sLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		sLabel.setForeground(c1);
		
		JLabel eLabel = new JLabel("0000년 00월");
		eLabel.setFont(new Font("나눔고딕", Font.BOLD, 18));
		eLabel.setForeground(c1);

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
		
		String r_status;
		int p_cost;
		
		ReservationMgr rsMgr = new ReservationMgr();

		// Panel 위치 지정
		jf.add(panel1, "Center");

		jf.add(panel3, "North");
		titleLb.setFont(new Font("나눔고딕", Font.BOLD, 34));
		panel3.add(titleLb);

		jf.add(panel4, "West");
		panel4.setLayout(new GridLayout(5, 1));
		panel4.add(roomLb);
		roomLb.setFont(new Font("나눔고딕", Font.BOLD, 34));

		roomBtn = new JRadioButton[4];
		ButtonGroup bgr = new ButtonGroup();
		

		roomBtn[0] = new JRadioButton("    101호    ", true);
		roomBtn[1] = new JRadioButton("    102호    ", false);
		roomBtn[2] = new JRadioButton("    201호    ", false);
		roomBtn[3] = new JRadioButton("    202호    ", false);

		for (int i = 0; i < roomBtn.length; i++) {
			roomBtn[i].setFont(f1);
			bgr.add(roomBtn[i]);
			panel4.add(roomBtn[i]);
			roomBtn[i].setBackground(bkColor);
			roomBtn[i].setForeground(c1);
			roomBtn[i].setOpaque(true);
		}
		
		roomBtn[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[0].getText());
				rsRoomTf.setFont(f2);
				
			}
		});
		
		roomBtn[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[1].getText());
				rsRoomTf.setFont(f2);
			}
		});
		
		roomBtn[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[2].getText());
				rsRoomTf.setFont(f2);
			}
		});
		
		roomBtn[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rsRoomTf.setText(roomBtn[3].getText());
				rsRoomTf.setFont(f2);
			}
		});
		

		panel5.setLayout(new GridLayout(9, 1)); /* 보더랑헷갈려서 헛짓..; */
		jf.add(panel5, "East");
		rsRoomLb.setFont(f1);
		panel5.add(rsRoomLb);
		rsRoomTf.setFont(f5);
		rsRoomTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsRoomTf);

		// 시작일정 텍스트필드
		rsSDateLb.setFont(f1);
		panel5.add(rsSDateLb);
		rsSDateTf.setFont(f5);
		rsSDateTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsSDateTf);

		// 종료일정 텍스트필드
		rsEDateLb.setFont(f1);
		panel5.add(rsEDateLb);
		rsEDateTf.setFont(f5);
		rsEDateTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsEDateTf);
		
		// 인원 라벨
		rsHeadcountLb.setFont(f1);
		panel5.add(rsHeadcountLb);
		
		// 인원 텍스트필드 : 기본값 1
		rsHeadcountTf.setText("   "+1+"   ");
		rsHeadcountTf.setFont(f5);
		rsHeadcountTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsHeadcountTf);
		rsHeadcountTf.setForeground(c2);
		
		JPanel panel17 = new JPanel(); // 마지막 행
		panel17.setLayout(new GridLayout(1,2));
		panel5.add(panel17);
		panel17.setBackground(bkColor);
		panel17.setOpaque(true);
		
		panel17.add(cPlusBtn); // 오름 버튼
		panel17.add(cMinusBtn); // 내림 버튼
		
		cPlusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("인원 수 증가버튼 클릭");
				
				// 1. 현재 예약희망인원 Tf 값 가져오기
				int headcount = Integer.parseInt(rsHeadcountTf.getText().trim()); // headcount = 1;
				
				// 2. headcount +1씩 증가시키고, Tf에 셋팅
				headcount++;
				rsHeadcountTf.setText(headcount+"");
				
				// 3. 최대수용인원과 예약희망인원 비교위해 룸 Tf로부터 선택 룸 가져오기
				int rsRoom = (Integer.parseInt(rsRoomTf.getText().trim().replaceAll("호", "")));
				
				// 4. DB연동해서 선택룸의 최대 수용인원 가져오기 (SEELCT r_capacity)
				int r_capacity = rsMgr.capacityChk(rsRoom);
						
				// 5. headcount(+1 증가된 인원)과 rsMgr.r_capacity(DB상 룸별 최대 수용인원) 비교
				if(headcount>r_capacity) { // 예약인원 > 최대 수용인원 
					// 경고 알림창
					JOptionPane.showMessageDialog(null, rsRoom + "호의 최대 수용인원은 " + 
					r_capacity + "명 입니다.", "예약인원을 체크해주세요.", 
					JOptionPane.ERROR_MESSAGE);
					
					// 다시 1로 셋팅
					headcount = 1;
					rsHeadcountTf.setText(headcount+"");
				}
				
			}
		});
		
		cMinusBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("인원 수 감소버튼 클릭");
				
				// 1. 현재 예약희망인원 Tf 값 가져오기
				int headcount = Integer.parseInt(rsHeadcountTf.getText().trim());
				
				// 2. headcount +1씩 증가시키고, Tf에 셋팅
				headcount--;
				rsHeadcountTf.setText(headcount+"");
				
				// 3. 예약희망인원은 최소 1 이상!
				if(headcount < 1) {
					// 경고 알림창
					JOptionPane.showMessageDialog(null, "1명 이상의 인원만 예약 가능압니다.", "예약인원을 체크해주세요.", JOptionPane.ERROR_MESSAGE);

					// 다시 1로 셋팅
					headcount = 1;
					rsHeadcountTf.setText(headcount+"");
				}
			}
		});
		
		// 결제버튼 클릭 이벤트
		paymentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("무조건 떠야함");
				
				// 0~9 이외의 모든 문자 제거한 int
				int rsRoom = Integer.parseInt(rsRoomTf.getText().trim().replaceAll("[^0-9]", ""));
				int rsSDate = Integer.parseInt(rsSDateTf.getText().trim().replaceAll("[^0-9]", "")); 
				int rsEDate = Integer.parseInt(rsEDateTf.getText().trim().replaceAll("[^0-9]", "")); 
			
				System.out.println("선택하신 룸 : " + rsRoom + "\n" + "시작날짜 : "+ rsSDate + "\n" 
						+ "종료날짜 : " + rsEDate + "\n");
			
		        String date1 = rsEDateTf.getText().replaceAll("[^0-9]", ""); // String
		        String date2 = rsSDateTf.getText().replaceAll("[^0-9]", ""); // String
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date로 바꾸는 클래스
		       
		        try {
		        	java.util.Date format1 = sdf.parse(date1); // java.util.date yyyyMMdd 형변환
		        	java.util.Date format2 = sdf.parse(date2); // java.util.date yyyyMMdd 형변환
			        long diffSec = (format1.getTime() - format2.getTime()) / 1000; // 초 차이
			        long diffDays = diffSec / (24*60*60); // 일자수 차이 (월 차이가 고려된 일자수임)
			        System.out.println(diffDays + "일 차이");
			        
			        
			        // java.util.date -> java.sql.date 변환
				    java.sql.Date sqlDate = new java.sql.Date(format2.getTime());
			        
			        Calendar cal = Calendar.getInstance(); // 캘린더 클래스의 인스턴스 가져오기
					cal.setTime(sqlDate); // 캘린더에 선택한 시작날짜 설정
					System.out.println("선택하신 시작 날짜 : " + sqlDate);
					
					
					for (int j = 0; j < diffDays/*SELECT 될 레코드 개수*/; j++) {
						if (rsMgr.dateChk(rsRoom,sqlDate,sqlDate) /*true 반환 -> 중복일정 존재한다는 의미*/) {
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
							JOptionPane.showMessageDialog(null, "중복되는 일정이 있으므로 선택하신 일정이 모두 예약취소 되었습니다. 다시 선택해주십시오.");
							break;
			
						} else {
							int headcount = Integer.parseInt(rsHeadcountTf.getText().trim());
							// DB와 중복되지 않는 날짜 선택했다면 false 반환
							
							// 결제 전 단계인 예약파트에서 결제하기 버튼 누르면 무조건 결제 전 상태 셋팅됨
							ReservationAWT.this.r_status = "결제 전";
							
							// 위아래버튼 클릭 안했다면 headcount = 1; 기본셋팅, 클릭했으면 값 설정되어 있음 
							// 그러므로 p_cost만 셋팅하면 된다.
							int p_cost = rsMgr.costChk(rsRoom);
							System.out.println(p_cost + ": 예약하신 룸의 1박당 가격입니다.");
							
							
							// DB INSERT 
							rsMgr.InsertDate(userId, rsRoom, sqlDate, sqlDate, headcount, ReservationAWT.this.r_status, p_cost);
		
							// INSERT 완료 후 sqlDate 1일씩 증가시켜서 예약 마지막날까지 반복문 돌게 한다.
							cal.add(Calendar.DATE, 1); // 1일 증가 시키기
							
							// 그냥 getTime 하면 시간까지 불러와져서 sdf로 형식 지정 후 String으로 가져오기
							String plusSqlDate = sdf.format(cal.getTime());
							System.out.println("선택하신 시작 날짜에서 하루를 증가시킨 날짜 : " + plusSqlDate);
							try {
								JOptionPane.showMessageDialog(null, sqlDate + "일 기준 1박 일정이 무사히 예약되셨습니다. 결제창으로 넘어갑니다.");
								System.out.println(sqlDate + "일 기준 1박 일정이 무사히 예약되셨습니다. 결제창으로 넘어갑니다.");
								format2 = sdf.parse(plusSqlDate);// String -> java.util.date 변환
								sqlDate = new java.sql.Date(format2.getTime()); // java.util.date -> java.sql.date 변환
							} catch (Exception e2) {
								e2.printStackTrace();
							}

						}
					}
					
					if(ReservationAWT.this.r_status == "결제 전") {
						PaymentFrame cpf = new PaymentFrame(userId);
						cpf.setVisible(true);
						jf.dispose();
					}
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		panel6.add(paymentBtn);
		paymentBtn.setFont(f4);  ////////
		paymentBtn.setBackground(c4);
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

		
		
		// 시작일 달력 액션리스너 연결
		sAfterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sMove = 1;
				
				// sCF객체의 startCal 메소드 호출
				sCF.startCal(sMove);

				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				sLabel.setText(sCF.setCalText());
				
			}
		});
		
		sBeforeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sMove = -1;
				
				// sCF객체의 sMove 메소드 호출
				sCF.startCal(sMove);

				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				sLabel.setText(sCF.setCalText());
			}
		});

		
		sAfterBtn.setFont(f1);
		sAfterBtn.setBackground(c3);
		sAfterBtn.setForeground(bkColor);
		sAfterBtn.setCursor(cursor);
		sAfterBtn.setBorder(BorderFactory.createLineBorder(c3,8));
		
		sBeforeBtn.setFont(f1);
		sBeforeBtn.setBackground(c3);
		sBeforeBtn.setForeground(bkColor);
		sBeforeBtn.setCursor(cursor);
		sBeforeBtn.setBorder(BorderFactory.createLineBorder(c3,8));
		
		sLabel.setFont(f1);

		// setText : 초기화 -> 해당 달력의 년도와 월로 새로 셋팅
		sLabel.setText(sCF.setCalText());

		
		
		
		// 종료일 달력 액션리스너 연결
		eAfterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int eMove = 1;
				
				// eCF객체의 startCal 메소드 호출
				eCF.startCal(eMove);
		
				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				eLabel.setText(eCF.setCalText());
			}
		});
		eBeforeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int eMove = -1;
				
				// eCF객체의 startCal 메소드 호출
				eCF.startCal(eMove);
		
				// 해당 달력에 맞는 년도와 월 가져와서 달력 상단 라벨에 셋팅
				eLabel.setText(eCF.setCalText());
				
			}
		});

		eAfterBtn.setFont(f1);
		eAfterBtn.setBackground(c1);
		eAfterBtn.setForeground(bkColor);
		eAfterBtn.setCursor(cursor);
		eAfterBtn.setBorder(BorderFactory.createLineBorder(c1,8));
		
		eBeforeBtn.setFont(f1);
		eBeforeBtn.setBackground(c1);
		eBeforeBtn.setForeground(bkColor);
		eBeforeBtn.setCursor(cursor);
		eBeforeBtn.setBorder(BorderFactory.createLineBorder(c1,8));
		

		
		eLabel.setFont(f1);
		// setText : 초기화 -> 해당 달력의 년도와 월로 새로 셋팅
		eLabel.setText(eCF.setCalText());

		
		
		
		// 시작일 달력 표시
		panel12.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < sDayBtn.length; i++) {
			sDayBtn[i] = new JButton(); // 버튼 배열 객체 생성			
			
			if(i >= 0  && i < 7) {
				sDayBtn[i].setBackground(c3);
				sDayBtn[i].setForeground(bkColor);
				sDayBtn[i].setBorder(BorderFactory.createLineBorder(c3, 1)); 
			} else {
				sDayBtn[i].setBackground(bkColor);
				sDayBtn[i].setForeground(c1);
				sDayBtn[i].setBorder(BorderFactory.createLineBorder(c1, 1)); 
			}
			
			sDayBtn[i].setCursor(cursor);
			
			// 액션리스너 연결
			sDayBtn[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
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
				}
			});
			panel12.add(sDayBtn[i]);

			sDayBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 24));

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
		
		
		
		// 종료일 달력 표시
		panel13.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < eDayBtn.length; i++) {
			eDayBtn[i] = new JButton(); // 버튼 배열 객체 생성
			
			if(i>=0 && i<7) {
				eDayBtn[i].setBackground(c1);
				eDayBtn[i].setForeground(bkColor);
				eDayBtn[i].setBorder(BorderFactory.createLineBorder(c1, 1)); 
			} else {
				eDayBtn[i].setBackground(bkColor);
				eDayBtn[i].setForeground(c1);
				eDayBtn[i].setBorder(BorderFactory.createLineBorder(c1, 1)); 
			}
			
			eDayBtn[i].setCursor(cursor);
			
			// 액션리스너 연결
			eDayBtn[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 7; i < eDayBtn.length; i++) {
					// 일자 시작하는 날부터
					if (eDayBtn[i].getText().length() > 0) {
						if (e.getSource() == eDayBtn[i]) {
							
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
							
						}
					}
				}
				}
			});
			
			panel13.add(eDayBtn[i]);

			eDayBtn[i].setFont(new Font("나눔고딕", Font.BOLD, 24));

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
		
		
		// 새로고침
		jf.validate();
	}
	
	// 생성자
	public ReservationAWT() {
		this(null);
	} 

}
