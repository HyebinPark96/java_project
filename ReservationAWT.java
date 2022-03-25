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

	// ������
	public ReservationAWT(String userId) {
		jf = new JFrame();
		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setUndecorated(false);
		jf.setVisible(true);

		// ��üƲ ���̾ƿ� ����
		jf.setLayout(new BorderLayout());
		
		Color bkColor = new Color(253,253,246); // ��׶��� ����
		Color c1 = new Color(184,178,166); 
		Color c2 = new Color(187,153,129); 
		Color c3 = new Color(219,233,183); 
		Color c4 = new Color(244,218,218); 
		
		Font f1 = new Font("�������", Font.BOLD, 20); 
		Font f2 = new Font("�������", Font.PLAIN, 15);
		Font f3 = new Font("�������", Font.PLAIN, 6); 
		Font f4 = new Font("�������", Font.BOLD, 30); 
		Font f5 = new Font("�������", Font.PLAIN, 20);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR); // Ŭ�� Ŀ�� ���
		
		JLabel titleLb = new JLabel("�����ϱ�");
		titleLb.setForeground(c1);
		JLabel roomLb = new JLabel("   �� ����   ");
		roomLb.setForeground(c1);
		JLabel rsRoomLb = new JLabel("�����Ͻ� �� : ");
		rsRoomLb.setForeground(c1);
		JLabel rsSDateLb = new JLabel("���� ���� : ");
		rsSDateLb.setForeground(c1);
		JLabel rsEDateLb = new JLabel("���� ���� : ");
		rsEDateLb.setForeground(c1);
		
		JLabel rsHeadcountLb = new JLabel("���� �ο� : ");
		rsHeadcountLb.setForeground(c1);
		JTextField rsHeadcountTf = new JTextField();
		
		JButton paymentBtn = new JButton("�����ϱ�");
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
		
		

		JPanel panel1 = new JPanel(); // ��üƲ ��ø ����
		panel1.setBackground(bkColor);
		panel1.setOpaque(true);
		JPanel panel2 = new JPanel(); // ��üƲ ��ø ���� ���� : �޷�
		panel2.setBackground(bkColor);
		panel2.setOpaque(true);
		JPanel panel7 = new JPanel(); // ��üƲ ��ø ����1 ���� : ��¥ ǥ�� �� �̵�
		panel7.setBackground(bkColor);
		panel7.setOpaque(true);
		JPanel panel8 = new JPanel(); // panel2�� ��ø���� (�׸��� �� 1)
		panel8.setBackground(bkColor);
		panel8.setOpaque(true);
		JPanel panel10 = new JPanel(); // panel2�� ��ø������ panel8�� ���� : ��¥ǥ�� �� �̵�
		panel10.setBackground(bkColor);
		panel10.setOpaque(true);
		JPanel panel12 = new JPanel(); // panel2�� ��ø������ panel8�� ���� : �޷�
		panel12.setBackground(bkColor);
		panel12.setOpaque(true);

		JPanel panel9 = new JPanel(); // panel2�� ��ø���� (�׸��� �� 2)
		panel9.setBackground(bkColor);
		panel9.setOpaque(true);
		JPanel panel11 = new JPanel(); // panel2�� ��ø������ panel9�� ���� : ��¥ǥ�� �� �̵�
		panel11.setBackground(bkColor);
		panel11.setOpaque(true);
		JPanel panel13 = new JPanel(); // panel2�� ��ø������ panel9�� ���� : �޷�
		panel13.setBackground(bkColor);
		panel13.setOpaque(true);

		JPanel panel3 = new JPanel(); // ��üƲ �������� Ÿ��Ʋ : "�����ϱ�"
		panel3.setBackground(bkColor);
		panel3.setOpaque(true);
		JPanel panel4 = new JPanel(); // ��üƲ �������� : "�뼱��" �� ��ȣ�� ����
		panel4.setBackground(bkColor);
		panel4.setOpaque(true);
		JPanel panel5 = new JPanel(); // ��üƲ ���� ������ : "�����Ͻ� ��" �ؽ�Ʈ�ʵ�, "����" �ؽ�Ʈ�ʵ�
		panel5.setBackground(bkColor);
		panel5.setOpaque(true);
		JPanel panel6 = new JPanel(); // ��üƲ ���� �ϴ� : �����ϱ� ��ư
		panel6.setBackground(bkColor);
		panel6.setOpaque(true);
		
		JButton cPlusBtn = new JButton("���� (+)");
		cPlusBtn.setFont(new Font("�������", Font.BOLD, 18));
		cPlusBtn.setBackground(c4);
		cPlusBtn.setForeground(c1);
		cPlusBtn.setCursor(cursor);
		cPlusBtn.setBorder(BorderFactory.createLineBorder(c4,8));
		
		JButton cMinusBtn = new JButton("���� (-)");
		cMinusBtn.setFont(new Font("�������", Font.BOLD, 18));
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

		// �⺻ ���� 0000�� 00��
		JLabel sLabel = new JLabel("0000�� 00��");
		sLabel.setFont(new Font("�������", Font.BOLD, 18));
		sLabel.setForeground(c1);
		
		JLabel eLabel = new JLabel("0000�� 00��");
		eLabel.setFont(new Font("�������", Font.BOLD, 18));
		eLabel.setForeground(c1);

		JButton[] sDayBtn = new JButton[49];
		String[] sDayName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		
		
		JButton[] eDayBtn = new JButton[49];
		String[] eDayName = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };

		// CalendarFunction Ŭ�����κ��� sCF ��ü ����
		CalendarFunc sCF = new CalendarFunc(); // ������
		CalendarFunc eCF = new CalendarFunc(); // ������

		int rsSDate; // DB�� ������ ���۳�¥ ������ (YYYYMMDD)
		int rsEDate; // DB�� ������ ���ᳯ¥ ������ (YYYYMMDD)
		int rsRoom; // DB�� ������ ���÷� ������ (101, 102, 201, 202 �� �ϳ�)
		
		String r_status;
		int p_cost;
		
		ReservationMgr rsMgr = new ReservationMgr();

		// Panel ��ġ ����
		jf.add(panel1, "Center");

		jf.add(panel3, "North");
		titleLb.setFont(new Font("�������", Font.BOLD, 34));
		panel3.add(titleLb);

		jf.add(panel4, "West");
		panel4.setLayout(new GridLayout(5, 1));
		panel4.add(roomLb);
		roomLb.setFont(new Font("�������", Font.BOLD, 34));

		roomBtn = new JRadioButton[4];
		ButtonGroup bgr = new ButtonGroup();
		

		roomBtn[0] = new JRadioButton("    101ȣ    ", true);
		roomBtn[1] = new JRadioButton("    102ȣ    ", false);
		roomBtn[2] = new JRadioButton("    201ȣ    ", false);
		roomBtn[3] = new JRadioButton("    202ȣ    ", false);

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
		

		panel5.setLayout(new GridLayout(9, 1)); /* �������򰥷��� ����..; */
		jf.add(panel5, "East");
		rsRoomLb.setFont(f1);
		panel5.add(rsRoomLb);
		rsRoomTf.setFont(f5);
		rsRoomTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsRoomTf);

		// �������� �ؽ�Ʈ�ʵ�
		rsSDateLb.setFont(f1);
		panel5.add(rsSDateLb);
		rsSDateTf.setFont(f5);
		rsSDateTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsSDateTf);

		// �������� �ؽ�Ʈ�ʵ�
		rsEDateLb.setFont(f1);
		panel5.add(rsEDateLb);
		rsEDateTf.setFont(f5);
		rsEDateTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsEDateTf);
		
		// �ο� ��
		rsHeadcountLb.setFont(f1);
		panel5.add(rsHeadcountLb);
		
		// �ο� �ؽ�Ʈ�ʵ� : �⺻�� 1
		rsHeadcountTf.setText("   "+1+"   ");
		rsHeadcountTf.setFont(f5);
		rsHeadcountTf.setHorizontalAlignment(JTextField.CENTER);
		panel5.add(rsHeadcountTf);
		rsHeadcountTf.setForeground(c2);
		
		JPanel panel17 = new JPanel(); // ������ ��
		panel17.setLayout(new GridLayout(1,2));
		panel5.add(panel17);
		panel17.setBackground(bkColor);
		panel17.setOpaque(true);
		
		panel17.add(cPlusBtn); // ���� ��ư
		panel17.add(cMinusBtn); // ���� ��ư
		
		cPlusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�ο� �� ������ư Ŭ��");
				
				// 1. ���� ��������ο� Tf �� ��������
				int headcount = Integer.parseInt(rsHeadcountTf.getText().trim()); // headcount = 1;
				
				// 2. headcount +1�� ������Ű��, Tf�� ����
				headcount++;
				rsHeadcountTf.setText(headcount+"");
				
				// 3. �ִ�����ο��� ��������ο� ������ �� Tf�κ��� ���� �� ��������
				int rsRoom = (Integer.parseInt(rsRoomTf.getText().trim().replaceAll("ȣ", "")));
				
				// 4. DB�����ؼ� ���÷��� �ִ� �����ο� �������� (SEELCT r_capacity)
				int r_capacity = rsMgr.capacityChk(rsRoom);
						
				// 5. headcount(+1 ������ �ο�)�� rsMgr.r_capacity(DB�� �뺰 �ִ� �����ο�) ��
				if(headcount>r_capacity) { // �����ο� > �ִ� �����ο� 
					// ��� �˸�â
					JOptionPane.showMessageDialog(null, rsRoom + "ȣ�� �ִ� �����ο��� " + 
					r_capacity + "�� �Դϴ�.", "�����ο��� üũ���ּ���.", 
					JOptionPane.ERROR_MESSAGE);
					
					// �ٽ� 1�� ����
					headcount = 1;
					rsHeadcountTf.setText(headcount+"");
				}
				
			}
		});
		
		cMinusBtn.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�ο� �� ���ҹ�ư Ŭ��");
				
				// 1. ���� ��������ο� Tf �� ��������
				int headcount = Integer.parseInt(rsHeadcountTf.getText().trim());
				
				// 2. headcount +1�� ������Ű��, Tf�� ����
				headcount--;
				rsHeadcountTf.setText(headcount+"");
				
				// 3. ��������ο��� �ּ� 1 �̻�!
				if(headcount < 1) {
					// ��� �˸�â
					JOptionPane.showMessageDialog(null, "1�� �̻��� �ο��� ���� ���ɾдϴ�.", "�����ο��� üũ���ּ���.", JOptionPane.ERROR_MESSAGE);

					// �ٽ� 1�� ����
					headcount = 1;
					rsHeadcountTf.setText(headcount+"");
				}
			}
		});
		
		// ������ư Ŭ�� �̺�Ʈ
		paymentBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������ ������");
				
				// 0~9 �̿��� ��� ���� ������ int
				int rsRoom = Integer.parseInt(rsRoomTf.getText().trim().replaceAll("[^0-9]", ""));
				int rsSDate = Integer.parseInt(rsSDateTf.getText().trim().replaceAll("[^0-9]", "")); 
				int rsEDate = Integer.parseInt(rsEDateTf.getText().trim().replaceAll("[^0-9]", "")); 
			
				System.out.println("�����Ͻ� �� : " + rsRoom + "\n" + "���۳�¥ : "+ rsSDate + "\n" 
						+ "���ᳯ¥ : " + rsEDate + "\n");
			
		        String date1 = rsEDateTf.getText().replaceAll("[^0-9]", ""); // String
		        String date2 = rsSDateTf.getText().replaceAll("[^0-9]", ""); // String
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date�� �ٲٴ� Ŭ����
		       
		        try {
		        	java.util.Date format1 = sdf.parse(date1); // java.util.date yyyyMMdd ����ȯ
		        	java.util.Date format2 = sdf.parse(date2); // java.util.date yyyyMMdd ����ȯ
			        long diffSec = (format1.getTime() - format2.getTime()) / 1000; // �� ����
			        long diffDays = diffSec / (24*60*60); // ���ڼ� ���� (�� ���̰� ����� ���ڼ���)
			        System.out.println(diffDays + "�� ����");
			        
			        
			        // java.util.date -> java.sql.date ��ȯ
				    java.sql.Date sqlDate = new java.sql.Date(format2.getTime());
			        
			        Calendar cal = Calendar.getInstance(); // Ķ���� Ŭ������ �ν��Ͻ� ��������
					cal.setTime(sqlDate); // Ķ������ ������ ���۳�¥ ����
					System.out.println("�����Ͻ� ���� ��¥ : " + sqlDate);
					
					
					for (int j = 0; j < diffDays/*SELECT �� ���ڵ� ����*/; j++) {
						if (rsMgr.dateChk(rsRoom,sqlDate,sqlDate) /*true ��ȯ -> �ߺ����� �����Ѵٴ� �ǹ�*/) {
							// Ex. 20220304 ~ 20220307 �����ߴµ�, 3/6~3/7 �ߺ��� ��� 
							// 1. ���ݱ��� �� �ݺ����� ���� insert �Ǿ��� ���� ����,
							// 2. �ߺ��� ��� break�� �ݺ��� ����������
							
							for (int k = j; k >0; k--) {
								
								cal.add(Calendar.DATE, -1); // 1�� ���� ��Ű��
								// �׳� getTime �ϸ� �ð����� �ҷ������� sdf�� ���� ���� �� String���� ��������
								String minusSqlDate = sdf.format(cal.getTime());
								System.out.println("�����Ͻ� ���� ��¥���� �Ϸ縦 ���ҽ�Ų ��¥(��Ʈ��) : " + minusSqlDate);
								try {
									format2 = sdf.parse(minusSqlDate); // String -> java.util.date ��ȯ
									sqlDate = new java.sql.Date(format2.getTime()); // java.util.date -> java.sql.date ��ȯ
									System.out.println("�����Ͻ� ���� ��¥���� �Ϸ縦 ���ҽ�Ų ��¥(sql����Ʈ) : " + sqlDate);
								} catch (Exception e2) {
									e2.printStackTrace();
								}
								
								rsMgr.deleteDate(rsRoom, sqlDate, sqlDate);	
							}
							System.out.println("�ߺ��Ǵ� ������ �����Ƿ� �����Ͻ� ������ ��� ������� �Ǿ����ϴ�. �ٽ� �������ֽʽÿ�.");
							JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� ������ �����Ƿ� �����Ͻ� ������ ��� ������� �Ǿ����ϴ�. �ٽ� �������ֽʽÿ�.");
							break;
			
						} else {
							int headcount = Integer.parseInt(rsHeadcountTf.getText().trim());
							// DB�� �ߺ����� �ʴ� ��¥ �����ߴٸ� false ��ȯ
							
							// ���� �� �ܰ��� ������Ʈ���� �����ϱ� ��ư ������ ������ ���� �� ���� ���õ�
							ReservationAWT.this.r_status = "���� ��";
							
							// ���Ʒ���ư Ŭ�� ���ߴٸ� headcount = 1; �⺻����, Ŭ�������� �� �����Ǿ� ���� 
							// �׷��Ƿ� p_cost�� �����ϸ� �ȴ�.
							int p_cost = rsMgr.costChk(rsRoom);
							System.out.println(p_cost + ": �����Ͻ� ���� 1�ڴ� �����Դϴ�.");
							
							
							// DB INSERT 
							rsMgr.InsertDate(userId, rsRoom, sqlDate, sqlDate, headcount, ReservationAWT.this.r_status, p_cost);
		
							// INSERT �Ϸ� �� sqlDate 1�Ͼ� �������Ѽ� ���� ������������ �ݺ��� ���� �Ѵ�.
							cal.add(Calendar.DATE, 1); // 1�� ���� ��Ű��
							
							// �׳� getTime �ϸ� �ð����� �ҷ������� sdf�� ���� ���� �� String���� ��������
							String plusSqlDate = sdf.format(cal.getTime());
							System.out.println("�����Ͻ� ���� ��¥���� �Ϸ縦 ������Ų ��¥ : " + plusSqlDate);
							try {
								JOptionPane.showMessageDialog(null, sqlDate + "�� ���� 1�� ������ ������ ����Ǽ̽��ϴ�. ����â���� �Ѿ�ϴ�.");
								System.out.println(sqlDate + "�� ���� 1�� ������ ������ ����Ǽ̽��ϴ�. ����â���� �Ѿ�ϴ�.");
								format2 = sdf.parse(plusSqlDate);// String -> java.util.date ��ȯ
								sqlDate = new java.sql.Date(format2.getTime()); // java.util.date -> java.sql.date ��ȯ
							} catch (Exception e2) {
								e2.printStackTrace();
							}

						}
					}
					
					if(ReservationAWT.this.r_status == "���� ��") {
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

		// ��ø ���̾ƿ�
		panel1.setLayout(new BorderLayout());
		panel1.add(panel7, "North");
		panel1.add(panel2, "Center");

		panel2.setLayout(new GridLayout(2, 1));
		panel2.add(panel8); // 1��
		panel2.add(panel9); // 2��

		// ������ �޷�
		panel8.setLayout(new BorderLayout());
		panel8.add(panel10, "North");
		panel8.add(panel12, "Center");

		// ���η� �þ��� ����
		panel10.setLayout(new FlowLayout());
		panel10.add(sBeforeBtn);
		panel10.add(sLabel);
		panel10.add(sAfterBtn);

		// ������ �޷�
		panel9.setLayout(new BorderLayout());
		panel9.add(panel11, "North");
		panel9.add(panel13, "Center");

		// ���η� �þ��� ����
		panel11.setLayout(new FlowLayout());
		panel11.add(eBeforeBtn);
		panel11.add(eLabel);
		panel11.add(eAfterBtn);

		
		
		// ������ �޷� �׼Ǹ����� ����
		sAfterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sMove = 1;
				
				// sCF��ü�� startCal �޼ҵ� ȣ��
				sCF.startCal(sMove);

				// �ش� �޷¿� �´� �⵵�� �� �����ͼ� �޷� ��� �󺧿� ����
				sLabel.setText(sCF.setCalText());
				
			}
		});
		
		sBeforeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int sMove = -1;
				
				// sCF��ü�� sMove �޼ҵ� ȣ��
				sCF.startCal(sMove);

				// �ش� �޷¿� �´� �⵵�� �� �����ͼ� �޷� ��� �󺧿� ����
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

		// setText : �ʱ�ȭ -> �ش� �޷��� �⵵�� ���� ���� ����
		sLabel.setText(sCF.setCalText());

		
		
		
		// ������ �޷� �׼Ǹ����� ����
		eAfterBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int eMove = 1;
				
				// eCF��ü�� startCal �޼ҵ� ȣ��
				eCF.startCal(eMove);
		
				// �ش� �޷¿� �´� �⵵�� �� �����ͼ� �޷� ��� �󺧿� ����
				eLabel.setText(eCF.setCalText());
			}
		});
		eBeforeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int eMove = -1;
				
				// eCF��ü�� startCal �޼ҵ� ȣ��
				eCF.startCal(eMove);
		
				// �ش� �޷¿� �´� �⵵�� �� �����ͼ� �޷� ��� �󺧿� ����
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
		// setText : �ʱ�ȭ -> �ش� �޷��� �⵵�� ���� ���� ����
		eLabel.setText(eCF.setCalText());

		
		
		
		// ������ �޷� ǥ��
		panel12.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < sDayBtn.length; i++) {
			sDayBtn[i] = new JButton(); // ��ư �迭 ��ü ����			
			
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
			
			// �׼Ǹ����� ����
			sDayBtn[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 7; i < sDayBtn.length; i++) {
						// ���� �����ϴ� ������
						if (sDayBtn[i].getText().length() > 0) {
							if (e.getSource() == sDayBtn[i]) {
								if(sLabel.getText().length()==8 && sDayBtn[i].getText().length() == 1 /*1. ���ڸ� �� + ���ڸ� ���� ���*/) {
									rsSDateTf.setText(sLabel.getText() + String.format("%02d", Integer.parseInt(sDayBtn[i].getText())) + "��");
								} else if(sLabel.getText().length()==8 && sDayBtn[i].getText().length() == 2/*2. ���ڸ� �� + ���ڸ� ���� ���*/) {
									rsSDateTf.setText(sLabel.getText() + sDayBtn[i].getText() + "��");
								} else if(sLabel.getText().length()==7 && sDayBtn[i].getText().length() == 1/*3. ���ڸ� �� + ���ڸ� ���� ��� */) {
									rsSDateTf.setText(sLabel.getText().substring(0,4)/*�⵵*/ + "��" + 
											String.format("%02d", Integer.parseInt(sLabel.getText().substring(5,6)))/*�� ���ڸ��� ���缭 ��������*/ + "��" 
											+ String.format("%02d", Integer.parseInt(sDayBtn[i].getText())) + "��");
								}  else if(sLabel.getText().length()==7 && sDayBtn[i].getText().length() == 2) { /*4. ���ڸ� �� + ���ڸ� ���� ���*/
									rsSDateTf.setText(sLabel.getText().substring(0,4)/*�⵵*/ + "��" + 
											String.format("%02d", Integer.parseInt(sLabel.getText().substring(5,6)))/*�� ���ڸ��� ���缭 ��������*/ + "��"
											+ sDayBtn[i].getText() + "��");
								}
							}
						}
					}
				}
			});
			panel12.add(sDayBtn[i]);

			sDayBtn[i].setFont(new Font("�������", Font.BOLD, 24));

			// ���� �迭 �ֱ�
			if (i < 7)
				sDayBtn[i].setText(sDayName[i]);

			if (i % 7 == 0) // �Ͽ���
				sDayBtn[i].setForeground(Color.RED);
			if (i % 7 == 6) // �����
				sDayBtn[i].setForeground(Color.BLUE);
		}

		sCF.setBtn(sDayBtn);
		sCF.setCal();
		
		
		
		// ������ �޷� ǥ��
		panel13.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < eDayBtn.length; i++) {
			eDayBtn[i] = new JButton(); // ��ư �迭 ��ü ����
			
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
			
			// �׼Ǹ����� ����
			eDayBtn[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 7; i < eDayBtn.length; i++) {
					// ���� �����ϴ� ������
					if (eDayBtn[i].getText().length() > 0) {
						if (e.getSource() == eDayBtn[i]) {
							
							if(eLabel.getText().length()==8 && eDayBtn[i].getText().length() == 1 /*1. ���ڸ� �� + ���ڸ� ���� ���*/) {
								rsEDateTf.setText(eLabel.getText() + String.format("%02d", Integer.parseInt(eDayBtn[i].getText())) + "��");
							} else if(eLabel.getText().length()==8 && eDayBtn[i].getText().length() == 2/* 2. ���ڸ� �� + ���ڸ� ���� ���*/) {
								rsEDateTf.setText(eLabel.getText() + eDayBtn[i].getText() + "��");
							} else if(eLabel.getText().length()==7 && eDayBtn[i].getText().length() == 1/*3. ���ڸ� �� + ���ڸ� ���� ��� */) {
								rsEDateTf.setText(eLabel.getText().substring(0,4)/*�⵵*/ + "��" + 
										String.format("%02d", Integer.parseInt(eLabel.getText().substring(5,6)))/* ���ڸ��� ���缭 ��������*/ + "��" 
										+ String.format("%02d", Integer.parseInt(eDayBtn[i].getText())) + "��");
							}  else if(eLabel.getText().length()==7 && eDayBtn[i].getText().length() == 2) { /*4. ���ڸ� �� + ���ڸ� ���� ���*/
								rsEDateTf.setText(eLabel.getText().substring(0,4)/*�⵵*/ + "��" + 
										String.format("%02d", Integer.parseInt(eLabel.getText().substring(5,6))) + "��"
										+ eDayBtn[i].getText() + "��");
							}
							
						}
					}
				}
				}
			});
			
			panel13.add(eDayBtn[i]);

			eDayBtn[i].setFont(new Font("�������", Font.BOLD, 24));

			// ���� �迭 �ֱ�
			if (i < 7)
				eDayBtn[i].setText(eDayName[i]);

			if (i % 7 == 0) // �Ͽ���
				eDayBtn[i].setForeground(Color.RED);
			if (i % 7 == 6) // �����
				eDayBtn[i].setForeground(Color.BLUE);
		}

		eCF.setBtn(eDayBtn); //���� ����
		eCF.setCal(); // �޷� ��ư�� ��¥ ����

		
		rsRoomTf.setText(roomBtn[0].getText());
		
		
		// ���ΰ�ħ
		jf.validate();
	}
	
	// ������
	public ReservationAWT() {
		this(null);
	} 

}
