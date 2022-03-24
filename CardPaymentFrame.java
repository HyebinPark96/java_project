package javaproject;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CardPaymentFrame extends JFrame {

	private Image bkImg = new ImageIcon("javaproject/images/background.png").getImage();
	PaymentMgr mgr = new PaymentMgr();
	String mm[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String yy[] = { "22", "23", "24", "25", "26" };
	private JPanel p1;

	// �׸��� �Լ�
	public void paint(Graphics g) {

		// ����̹���
		g.drawImage(bkImg, 0, 10, null);

		// ���м�
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);

		// "��"�޺��ڽ�
		JComboBox<String> mmCom = new JComboBox<String>(mm);
		add(mmCom);
		mmCom.setEditable(true);
		mmCom.setBounds(230, 95, 50, 20);
		mmCom.setBackground(Color.white);

		mmCom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				JComboBox cb = (JComboBox) e.getSource();

				System.out.println(mmCom.getSelectedItem());
				
				cb.setSelectedItem(mmCom.getSelectedItem());
				
				

//				// setIcon() : ������ ���� �޼ҵ�
//				label.setIcon(images[idx]);

			}
		});

//		TextField mmTf = new TextField();
//		add(mmTf);
//		mmTf.setBounds(230, 95, 50, 20);
//		
//		TextField yyTf = new TextField();
//		add(yyTf);
//		yyTf.setBounds(320, 95, 50, 20);

		// "��"�޺��ڽ�
		JComboBox<String> yyCom = new JComboBox<String>(yy);
		add(yyCom);
		yyCom.setEditable(true);
		yyCom.setBounds(320, 95, 50, 20);
		yyCom.setBackground(Color.white);
		yyCom.setOpaque(true);
		yyCom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				

				JComboBox cb = (JComboBox) e.getSource();

				System.out.println(yyCom.getSelectedItem());
				
				cb.setSelectedItem(yyCom.getSelectedItem());

			}
		});

		// ī�屸�� radio
		JRadioButton pcRBtn = new JRadioButton("����ī��", true);
		JRadioButton ccRBtn = new JRadioButton("����ī��");
		ButtonGroup group = new ButtonGroup();
		add(pcRBtn);
		add(ccRBtn);
		group.add(pcRBtn);
		group.add(ccRBtn);
		pcRBtn.setBackground(Color.white);
		pcRBtn.setBounds(230, 120, 80, 15);
		ccRBtn.setBackground(Color.white);
		ccRBtn.setBounds(320, 120, 80, 15);

		// ī���й�ȣ �ؽ�Ʈ�ʵ�
		JTextField c_pwdTf = new JTextField();
		add(c_pwdTf);
		c_pwdTf.setBounds(230, 145, 30, 20);

//		// ī���й�ȣ ��2
		JLabel c_pwdLb2 = new JLabel("X X (�� ���ڸ�)");
		add(c_pwdLb2);
		c_pwdLb2.setBounds(260, 145, 100, 20);
//		c_pwdLb2.setFont(f3);

		// �ֹε�Ϲ�ȣ �ؽ�Ʈ�ʵ�
		JTextField idnumTf1 = new JTextField();
		JTextField idnumTf2 = new JTextField();
		add(idnumTf1);
		add(idnumTf2);
		idnumTf1.setBounds(230, 170, 70, 20);
		idnumTf2.setBounds(330, 170, 70, 20);

	}

	// ������
	public CardPaymentFrame() {
		this(null);
	}

	// ������ (�Ű�����)
	public CardPaymentFrame(String userId) {

		// �����Ӽ���
		setTitle(userId + "�� ī�����â");
		setSize(800, 600);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		setLayout(null);// ���̾ƿ��� ������� ���������ϰ� ����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ�

		// ��Ʈ
		Font f1 = new Font("���� ���", Font.BOLD, 20); // Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("���� ���", Font.BOLD, 10); // ��ư ��Ʈ
		Font f3 = new Font("���� ���", Font.PLAIN, 10); // �ؽ�Ʈ ��Ʈ
		Font f4 = new Font("���� ���", Font.PLAIN, 10);

		// �÷�
		Color c = new Color(226, 56, 61);

		// Ÿ��Ʋ ��
		Label title = new Label("�����ϱ�");
		title.setFont(f1);
		title.setForeground(Color.gray);
		title.setBackground(Color.white);
		title.setBounds(120, 10, 100, 30);
		add(title);

		// ī���ȣ ��
		Label c_numLb = new Label("ī���ȣ");
		c_numLb.setBounds(120, 70, 100, 20);
		c_numLb.setFont(f4);
		add(c_numLb);

		// ī���ȣ �ؽ�Ʈ�ʵ�
		TextField c_numTf[] = new TextField[4]; // ī���ȣ 4�ڸ�
		for (int i = 0; i < 4; i++) {
			c_numTf[i] = new TextField();
			add(c_numTf[i]);
		}
		c_numTf[0].setBounds(230, 70, 40, 20);
		c_numTf[1].setBounds(280, 70, 40, 20);
		c_numTf[2].setBounds(330, 70, 40, 20);
		c_numTf[3].setBounds(380, 70, 40, 20);

		// ��ȿ�Ⱓ ��
		Label c_yymm = new Label("��ȿ�Ⱓ");
		c_yymm.setBounds(120, 95, 100, 20);
		c_yymm.setFont(f4);
		add(c_yymm);

		// "��" ��
		Label mm = new Label("��");
		mm.setBounds(280, 95, 20, 20);
		mm.setFont(f4);
		mm.setBackground(Color.white);
		add(mm);

		// "��" ��
		Label yy = new Label("��");
		yy.setBounds(370, 95, 20, 20);
		yy.setFont(f4);
		yy.setBackground(Color.white);
		add(yy);

		// ī�屸�� ��
		Label c_classLb = new Label("ī�屸��");
		c_classLb.setBounds(120, 120, 100, 20);
		c_classLb.setFont(f4);
		add(c_classLb);

		// ī���й�ȣ ��
		Label c_pwdLb = new Label("ī�� ��й�ȣ");
		c_pwdLb.setBounds(120, 145, 100, 20);
		c_pwdLb.setFont(f4);
		add(c_pwdLb);

		// �ֹε�Ϲ�ȣ ��
		Label idnumLb = new Label("�ֹε�Ϲ�ȣ");
		idnumLb.setBounds(120, 170, 100, 20);
		idnumLb.setFont(f4);
		add(idnumLb);

		// ������ư
		Button p_btn = new Button("����");

//		p_btn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int check=0; // �ؽ�Ʈ�ʵ� ��� �ԷµǾ����� Ȯ�ο�
//				
//				for (int j = 0; j < c_numTf.length; j++) {
//					if (c_numTf[j].getText().trim().length() > 0) {
//						if(c_numTf[j].getText().trim().length() == 4) {
//							check++;
//							System.out.println(check);
//						} else {
//							JOptionPane.showMessageDialog(null,"ī���ȣ " + (j+1) + "��° ĭ ERROR : " + "4�ڸ��� ä���ּ���. (" + c_numTf[j].getText() + ")");
//							System.out.println("ī���ȣ " + (j+1) + "��° ĭ ERROR : " + "4�ڸ��� ä���ּ���. (" + c_numTf[j].getText() + ")");
//						}
//					}
//				}
//				if (c_pwdTf.getText().trim().length() > 0) {
//					if(c_pwdTf.getText().trim().length() == 4) {
//						check++;
//						System.out.println(check);
//					} else {
//						JOptionPane.showMessageDialog(null,"�� 4�ڸ��� ī�� ��й�ȣ�� �Է��ϼ���. (" + c_pwdTf.getText() +")" );
//						System.out.println("�� 4�ڸ��� ī�� ��й�ȣ�� �Է��ϼ���. (" + c_pwdTf.getText() +")" );
//					}	
//				}

//				if (c_cvcTf.getText().trim().length() > 0) {
//					if(c_cvcTf.getText().trim().length() == 3) {
//						check++;
//						System.out.println(check);
//					} else {
//						JOptionPane.showMessageDialog(null,"�� 3�ڸ��� CVC ��ȣ�� �Է��ϼ���. (" + c_cvcTf.getText() + ")");
//						System.out.println("�� 3�ڸ��� CVC ��ȣ�� �Է��ϼ���. (" + c_cvcTf.getText() + ")");
//					}
//					
//				}
//				if (c_mmTf.getText().trim().length() > 0 && c_yyTf.getText().trim().length() > 0) {
//					if(c_mmTf.getText().trim().length() == 2 && c_yyTf.getText().trim().length() == 2) {
//						check++;
//						System.out.println(check);
//					} else {
//						JOptionPane.showMessageDialog(null, "ī�� ��ȿ�Ⱓ ����� 2�ڸ��� �Է��ϼ���. Ex. MMYY : 0926");
//						System.out.println("ī�� ��ȿ�Ⱓ ����� 2�ڸ��� �Է��ϼ���. Ex. MMYY : 0926");
//					}
//					
//				}

//				if (check == 7) {
//					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
//					mgr.updateStatus(userId); // update�� ������ �ش� id�� ���೻���� �������¸� ������ -> �����Ϸ�� ����
//					dispose();		
//				} else {
//					// tf ��� �Էµ��� �ʾҴٸ� 0���� �ʱ�ȭ�ؼ� �ٽ� ���
//					check = 0;
//				}
//				
//			}
//		});
//		p_btn.setBounds(270, 350, 80, 20);
//		p_btn.setBackground(Color.BLACK);
//		p_btn.setForeground(Color.WHITE);
//		frame.add(p_btn);
//
//		frame.setSize(600, 500);

//
//		frame.validate();

	}

	public static void main(String[] args) {// ����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardPaymentFrame CardPaymentFrame = new CardPaymentFrame();
					CardPaymentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
