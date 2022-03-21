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
	public static String id; // ��������
	public static int mode; // ��������
	
	
	JFrame jf = new JFrame();
	JLabel titleLb = new JLabel("�����ϱ�");
	JLabel roomLb = new JLabel("�� ����");
	JLabel rsRoomLb = new JLabel("�����Ͻ� �� : ");
	JLabel rsSDateLb = new JLabel("���� ���� : ");
	JLabel rsEDateLb = new JLabel("���� ���� : ");
	
	JLabel rsCapacityLb = new JLabel("���� �ο� : ");
	JTextField rsCapacityTf = new JTextField(10);
	
	JButton paymentBtn = new JButton("�����ϱ�");
	JTextField rsRoomTf = new JTextField(10);
	JTextField rsSDateTf = new JTextField(10);
	JTextField rsEDateTf = new JTextField(10);
	
	JRadioButton roomBtn[];

	JPanel panel1 = new JPanel(); // ��üƲ ��ø ����
	JPanel panel2 = new JPanel(); // ��üƲ ��ø ���� ���� : �޷�
	JPanel panel7 = new JPanel(); // ��üƲ ��ø ����1 ���� : ��¥ ǥ�� �� �̵�
	JPanel panel8 = new JPanel(); // panel2�� ��ø���� (�׸��� �� 1)
	JPanel panel10 = new JPanel(); // panel2�� ��ø������ panel8�� ���� : ��¥ǥ�� �� �̵�
	JPanel panel12 = new JPanel(); // panel2�� ��ø������ panel8�� ���� : �޷�

	JPanel panel9 = new JPanel(); // panel2�� ��ø���� (�׸��� �� 2)
	JPanel panel11 = new JPanel(); // panel2�� ��ø������ panel9�� ���� : ��¥ǥ�� �� �̵�
	JPanel panel13 = new JPanel(); // panel2�� ��ø������ panel9�� ���� : �޷�

	JPanel panel3 = new JPanel(); // ��üƲ �������� Ÿ��Ʋ : "�����ϱ�"
	JPanel panel4 = new JPanel(); // ��üƲ �������� : "�뼱��" �� ��ȣ�� ����
	JPanel panel5 = new JPanel(); // ��üƲ ���� ������ : "�����Ͻ� ��" �ؽ�Ʈ�ʵ�, "����" �ؽ�Ʈ�ʵ�
	JPanel panel6 = new JPanel(); // ��üƲ ���� �ϴ� : �����ϱ� ��ư
	
	JPanel panel14 = new JPanel(); // ������ ��
	JPanel panel15 = new JPanel(); // ������ ���� 1��
	JPanel panel16 = new JPanel(); // ������ ���� 2��
	JPanel panel17 = new JPanel(); // ������ ���� 2���� �������̾ƿ��� ���
	JPanel panel18 = new JPanel(); // ������ ���� 2���� �������̾ƿ��� ���
	
	JButton cPlusBtn = new JButton("���� (+)");
	JButton cMinusBtn = new JButton("���� (-)");
	
	String date1;
	String date2;
	
	java.util.Date format1;
	java.util.Date format2;
	
	///////////////////////////////////////// �޷�
	JButton sBeforeBtn = new JButton("Before");
	JButton eBeforeBtn = new JButton("Before");
	JButton sAfterBtn = new JButton("After");
	JButton eAfterBtn = new JButton("After");

	// �⺻ ���� 0000�� 00��
	JLabel sLabel = new JLabel("0000�� 00��");
	JLabel eLabel = new JLabel("0000�� 00��");

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
	
	ReservationMgr rsMgr = new ReservationMgr();
	
	///////////////////////////////////////// �޷�

	// ������
	public ReservationAWT() {

		// �⺻ ����
		// jf.setSize(1200, 1000);

//		// ����� ũ�� 
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		// ��üȭ�� (���α��� : �����ȭ�� �ʺ�, ���α��� : �����ȭ�� ����)
//		jf.setSize(screenSize.width,screenSize.height);
//		jf.setLocationRelativeTo(null);

		jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setUndecorated(false);

		jf.setVisible(true);

		// ��üƲ ���̾ƿ� ����
		jf.setLayout(new BorderLayout());

		// Panel ��ġ ����
		jf.add(panel1, "Center");

		jf.add(panel3, "North");
		titleLb.setFont(new Font("���� ����", Font.BOLD, 34));
		panel3.add(titleLb);

		jf.add(panel4, "West");
		panel4.setLayout(new GridLayout(5, 1));
		panel4.add(roomLb);
		roomLb.setFont(new Font("���� ����", Font.BOLD, 34));

		roomBtn = new JRadioButton[4];
		ButtonGroup bgr = new ButtonGroup();

		roomBtn[0] = new JRadioButton("101ȣ", true);
		roomBtn[1] = new JRadioButton("102ȣ", false);
		roomBtn[2] = new JRadioButton("201ȣ", false);
		roomBtn[3] = new JRadioButton("202ȣ", false);

		for (int i = 0; i < roomBtn.length; i++) {
			roomBtn[i].setFont(new Font("���� ����", Font.PLAIN, 20));
			bgr.add(roomBtn[i]);
			panel4.add(roomBtn[i]);
			// �׼Ǹ����� ����
			roomBtn[i].addActionListener(this);
		}

		panel5.setLayout(new GridLayout(8, 1)); /* �������򰥷��� ����..; */
		jf.add(panel5, "East");
		rsRoomLb.setFont(new Font("���� ����", Font.BOLD, 20));
		panel5.add(rsRoomLb);
		rsRoomTf.setFont(new Font("���� ����", Font.PLAIN, 20));
		panel5.add(rsRoomTf);

		// �������� �ؽ�Ʈ�ʵ�
		rsSDateLb.setFont(new Font("���� ����", Font.BOLD, 20));
		panel5.add(rsSDateLb);
		rsSDateTf.setFont(new Font("���� ����", Font.PLAIN, 20));
		panel5.add(rsSDateTf);

		// �������� �ؽ�Ʈ�ʵ�
		rsEDateLb.setFont(new Font("���� ����", Font.BOLD, 20));
		panel5.add(rsEDateLb);
		rsEDateTf.setFont(new Font("���� ����", Font.PLAIN, 20));
		panel5.add(rsEDateTf);
		
		
		//////////////////////////////
		// ���� �ؽ�Ʈ�ʵ忡 ���� �� ����, �������� ��ư ���� 
		// ������ư ������ư�� �׼Ǹ����� �޾Ƽ� �� ȣ�����õȰſ� ���� ���ǹ� ����
		// �ο� �ؽ�Ʈ�ʵ� 
		rsCapacityLb.setFont(new Font("���� ����", Font.BOLD, 20));
		panel5.add(rsCapacityLb);
		panel5.add(panel14); // ������ �࿡ panel14 �߰� 
		
		// panel5�� Grid ������ �࿡ panel14 1�� 2�� �׸��带 �����.
		panel14.setLayout(new GridLayout(1,2)); 
		panel14.add(panel15); // �ؽ�Ʈ�ʵ� �� 1��
		panel15.add(rsCapacityTf); 
	
		panel14.add(panel16); // �������� ��ư �� 2��
		
		panel16.setLayout(new BorderLayout());
		
		panel17.add(cPlusBtn); // ���� ��ư
		panel18.add(cMinusBtn); // ���� ��ư
		
		panel16.add("North",panel17);
		panel16.add("South",panel18);
		
		
		paymentBtn.addActionListener(this);
		panel6.add(paymentBtn);
		paymentBtn.setFont(new Font("���� ����", Font.BOLD, 20));
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

		/////////////////////////////
		// ������ �޷� �׼Ǹ����� ����
		sAfterBtn.addActionListener(this);
		sBeforeBtn.addActionListener(this);

		Font font = new Font("SansSerif", Font.BOLD, 20);
		sAfterBtn.setFont(font);
		sBeforeBtn.setFont(font);
		sLabel.setFont(font);

		// setText : �ʱ�ȭ -> �ش� �޷��� �⵵�� ���� ���� ����
		sLabel.setText(sCF.setCalText());
		/////////////////////////////

		
		
		
		/////////////////////////////////////////////
		// ������ �޷� �׼Ǹ����� ����
		eAfterBtn.addActionListener(this);
		eBeforeBtn.addActionListener(this);

		eAfterBtn.setFont(font);
		eBeforeBtn.setFont(font);
		eLabel.setFont(font);

		// setText : �ʱ�ȭ -> �ش� �޷��� �⵵�� ���� ���� ����
		eLabel.setText(eCF.setCalText());
		/////////////////////////////////////////////
		
		
		
		
		/////////////////////////////////
		// ������ �޷� ǥ��
		panel12.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < sDayBtn.length; i++) {
			sDayBtn[i] = new JButton(); // ��ư �迭 ��ü ����
			// �׼Ǹ����� ����
			sDayBtn[i].addActionListener(this);
			panel12.add(sDayBtn[i]);

			sDayBtn[i].setFont(new Font("SansSerif", Font.BOLD, 24));

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
		/////////////////////////////////

		
		
		////////////////////////////////////////////
		// ������ �޷� ǥ��
		panel13.setLayout(new GridLayout(7, 7, 0, 0));

		for (int i = 0; i < eDayBtn.length; i++) {
			eDayBtn[i] = new JButton(); // ��ư �迭 ��ü ����
			// �׼Ǹ����� ����
			eDayBtn[i].addActionListener(this);
			panel13.add(eDayBtn[i]);

			eDayBtn[i].setFont(new Font("SansSerif", Font.BOLD, 24));

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
		/////////////////////////////////////////////
	} // ---- ������
	

	// �׼��̺�Ʈ
	@Override
	public void actionPerformed(ActionEvent e) {
		
		////////////////////////////////////////////
		for (int i = 0; i < roomBtn.length; i++) {
			if(e.getSource() == roomBtn[i]) {
				// 1. SELECT ���೻�����̺��� �÷� WHERE ȣ��=? 
				// 
				// 2. ����� ���� �޷� ���� ����
				
				
			}
		}
		
		////////////////////////////////////////////
		
		
		/////////////////////////////////////////////
		// ���ȣ �������� �� tf�� �Է�
		for (int i = 0; i < roomBtn.length; i++) {
			if (e.getSource() == roomBtn[i]) {
				rsRoomTf.setText(roomBtn[i].getText());
			}
		}
		/////////////////////////////////////////////
		
		
		
		
		////////////////////////////////////////////
		// ������ �޷� �̺�Ʈ
		int sMove = 0;

		if (e.getSource() == sAfterBtn) { // 1�� ��
			sMove = 1;
		} else if (e.getSource() == sBeforeBtn) { // 1�� ��
			sMove = -1;
		}

		// cF��ü�� allInit �޼ҵ� ȣ��
		sCF.startCal(sMove);

		// �ش� �޷¿� �´� �⵵�� �� �����ͼ� �޷� ��� �󺧿� ����
		sLabel.setText(sCF.setCalText());
		//////////////////////////////////////////////
		
		
		
		
		/////////////////////////////////////////////
		// ��� ������ �� : ��¥��ư �����ϸ� setBackground �ٲ��, ��Ȱ��ȭ

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

		////////////////////////////////////////////

		
		
		
		
		////////////////////////////////////////////
		// ������ �޷� �̺�Ʈ
		int eMove = 0;

		if (e.getSource() == eAfterBtn) { // �Ѵ� ��
			eMove = 1;
		} else if (e.getSource() == eBeforeBtn) { // �Ѵ� ��
			eMove = -1;
		}

		// cF��ü�� allInit �޼ҵ� ȣ��
		eCF.startCal(eMove);

		// �ش� �޷¿� �´� �⵵�� �� �����ͼ� �޷� ��� �󺧿� ����
		eLabel.setText(eCF.setCalText());

		/////////////////////////////////////////////
		
		

		for (int i = 7; i < eDayBtn.length; i++) {
			// ���� �����ϴ� ������
			if (eDayBtn[i].getText().length() > 0) {
				if (e.getSource() == eDayBtn[i]) {
					
					/////////////////////////////////////
					
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
					
					/////////////////////////////////////
				}
			}
		}

		
		/////////////////////////////////////////////
		
		if (e.getSource() == paymentBtn) { /*paymentBtn�� �׼Ǹ����� ���� ���߾ ��� ������*/
			
				System.out.println("������ ������");
				
				// 0~9 �̿��� ��� ���� ������ int
				rsRoom = Integer.parseInt(rsRoomTf.getText().replaceAll("[^0-9]", ""));
				rsSDate = Integer.parseInt(rsSDateTf.getText().replaceAll("[^0-9]", "")); 
				rsEDate = Integer.parseInt(rsEDateTf.getText().replaceAll("[^0-9]", "")); 
			
				System.out.println("�����Ͻ� �� : " + rsRoom + "\n" + "���۳�¥ : "+ rsSDate + "\n" 
						+ "���ᳯ¥ : " + rsEDate + "\n");
			
		        date1 = rsEDateTf.getText().replaceAll("[^0-9]", ""); // String
		        date2 = rsSDateTf.getText().replaceAll("[^0-9]", ""); // String
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // Date�� �ٲٴ� Ŭ����
		       
		        try {
					format1 = sdf.parse(date1); // java.util.date yyyyMMdd ����ȯ
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		        try {
					format2 = sdf.parse(date2); // java.util.date yyyyMMdd ����ȯ
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

		        long diffSec = (format1.getTime() - format2.getTime()) / 1000; // �� ����
		        long diffDays = diffSec / (24*60*60); // ���ڼ� ����
		        
		        System.out.println(diffDays + "�� ����");
				
		        
		        //////////////////////////////////////////���⼭����!! �ٽ��۾�
		        
		        // java.util.date -> java.sql.date ��ȯ
			    java.sql.Date sqlDate = new java.sql.Date(format2.getTime());
		        
		        Calendar cal = Calendar.getInstance(); // Ķ���� Ŭ������ �ν��Ͻ� ��������
				cal.setTime(sqlDate); // Ķ������ ������ ���۳�¥ ����
				System.out.println("�����Ͻ� ���� ��¥ : " + sqlDate);
				
				//////////////////////////////////////////���⼭����!! �ٽ��۾�
				
				
				for (int j = 0; j < diffDays/*SELECT �� ���ڵ� ����*/; j++) { // �޴ٸ��� �������ֱ�
					if (rsMgr.dateChk(rsRoom,sqlDate,sqlDate) /*true ��ȯ -> �ߺ����� �����Ѵٴ� �ǹ� */) {
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
						break;
		
					} else {
						// DB�� �ߺ����� �ʴ� ��¥ �����ߴٸ� false ��ȯ
						// DB INSERT 
						rsMgr.testInsertDate(rsRoom, sqlDate, sqlDate);
						
						// INSERT �Ϸ� �� sqlDate 1�Ͼ� �������Ѽ� ���� ������������ �ݺ��� ���� �Ѵ�.
						cal.add(Calendar.DATE, 1); // 1�� ���� ��Ű��
						
						// �׳� getTime �ϸ� �ð����� �ҷ������� sdf�� ���� ���� �� String���� ��������
						String plusSqlDate = sdf.format(cal.getTime());
						System.out.println("�����Ͻ� ���� ��¥���� �Ϸ縦 ������Ų ��¥ : " + plusSqlDate);
						try {
							format2 = sdf.parse(plusSqlDate);// String -> java.util.date ��ȯ
							sqlDate = new java.sql.Date(format2.getTime()); // java.util.date -> java.sql.date ��ȯ
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						
						System.out.println("�ߺ��Ǵ� �������� ������ ����Ǽ̽��ϴ�.");
					}
				}
			}
		/////////////////////////////////////////////
		} // -- �׼��̺�Ʈ
	
	
	
	public static void main(String[] args) {
		// ������ ȣ��
		new ReservationAWT();
	}

}