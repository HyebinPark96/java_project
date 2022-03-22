package javaproject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CardPaymentFrame {
	private JFrame frame;
	PaymentMgr mgr = new PaymentMgr();
	
	// ������
	public CardPaymentFrame() {
		this(null);
	}
	
	// ������
	public CardPaymentFrame(String userId) {
		Label title = new Label("�����ϱ�");
		
		Panel p1, p2, p3;
		
		Label c_numLb = new Label("ī���ȣ");
		Label c_pwdLb = new Label("ī�� ��й�ȣ");
		Label c_cvcLb = new Label("CVC");
		Label c_yymm = new Label("��ȿ�Ⱓ");

		TextField c_numTf[] = new TextField[4]; // ī���ȣ 4�ڸ�
		TextField c_pwdTf = new TextField(5); // ī�� ��й�ȣ
		TextField c_cvcTf = new TextField(5); // CVC
		TextField c_yyTf = new TextField(5); // ��ȿ�Ⱓ ��
		Label yy = new Label("��");
		Label mm = new Label("��");
		TextField c_mmTf = new TextField(5); // ��ȿ�Ⱓ ��
		Button p_btn = new Button("����");

		
		// ������ ����
		frame = new JFrame("�����ϱ�");
		Color c = new Color(255, 255, 255);

		Container con = frame.getContentPane();
		con.setBackground(c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setLayout(null);

		title.setBounds(270, 30, 50, 50);
		frame.add(title);

		c_numLb.setBounds(50, 100, 100, 20);
		frame.add(c_numLb);

		for (int i = 0; i < c_numTf.length; i++) {
			c_numTf[i] = new TextField();
		}

		c_numTf[0].setBounds(170, 100, 50, 20);
		c_numTf[1].setBounds(240, 100, 50, 20);
		c_numTf[2].setBounds(310, 100, 50, 20);
		c_numTf[3].setBounds(370, 100, 50, 20);

		// ī���ȣ �Է��ϴ� �ؽ�Ʈ�ʵ� �߰�
		for (int i = 0; i < c_numTf.length; i++) {
			frame.add(c_numTf[i]);
		}

		c_pwdLb.setBounds(50, 150, 100, 20);
		frame.add(c_pwdLb);
		c_pwdTf.setBounds(170, 150, 50, 20);
		frame.add(c_pwdTf);

		c_cvcLb.setBounds(50, 200, 100, 20);
		frame.add(c_cvcLb);
		c_cvcTf.setBounds(170, 200, 50, 20);
		frame.add(c_cvcTf);

		c_yymm.setBounds(50, 250, 50, 20);
		frame.add(c_yymm);

		c_mmTf.setBounds(170, 250, 50, 20);
		frame.add(c_mmTf);

		mm.setBounds(220, 250, 20, 20);
		frame.add(mm);

		c_yyTf.setBounds(240, 250, 50, 20);
		frame.add(c_yyTf);

		yy.setBounds(290, 250, 20, 20);
		frame.add(yy);

		p_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check=0; // �ؽ�Ʈ�ʵ� ��� �ԷµǾ����� Ȯ�ο�
				
				for (int j = 0; j < c_numTf.length; j++) {
					if (c_numTf[j].getText().trim().length() > 0) {
						if(c_numTf[j].getText().trim().length() == 4) {
							check++;
							System.out.println(check);
						} else {
							JOptionPane.showMessageDialog(null,"ī���ȣ " + (j+1) + "��° ĭ ERROR : " + "4�ڸ��� ä���ּ���. (" + c_numTf[j].getText() + ")");
							System.out.println("ī���ȣ " + (j+1) + "��° ĭ ERROR : " + "4�ڸ��� ä���ּ���. (" + c_numTf[j].getText() + ")");
						}
					}
				}
				if (c_pwdTf.getText().trim().length() > 0) {
					if(c_pwdTf.getText().trim().length() == 4) {
						check++;
						System.out.println(check);
					} else {
						JOptionPane.showMessageDialog(null,"�� 4�ڸ��� ī�� ��й�ȣ�� �Է��ϼ���. (" + c_pwdTf.getText() +")" );
						System.out.println("�� 4�ڸ��� ī�� ��й�ȣ�� �Է��ϼ���. (" + c_pwdTf.getText() +")" );
					}	
				}
				
				if (c_cvcTf.getText().trim().length() > 0) {
					if(c_cvcTf.getText().trim().length() == 3) {
						check++;
						System.out.println(check);
					} else {
						JOptionPane.showMessageDialog(null,"�� 3�ڸ��� CVC ��ȣ�� �Է��ϼ���. (" + c_cvcTf.getText() + ")");
						System.out.println("�� 3�ڸ��� CVC ��ȣ�� �Է��ϼ���. (" + c_cvcTf.getText() + ")");
					}
					
				}
				if (c_mmTf.getText().trim().length() > 0 && c_yyTf.getText().trim().length() > 0) {
					if(c_mmTf.getText().trim().length() == 2 && c_yyTf.getText().trim().length() == 2) {
						check++;
						System.out.println(check);
					} else {
						JOptionPane.showMessageDialog(null, "ī�� ��ȿ�Ⱓ ����� 2�ڸ��� �Է��ϼ���. Ex. MMYY : 0926");
						System.out.println("ī�� ��ȿ�Ⱓ ����� 2�ڸ��� �Է��ϼ���. Ex. MMYY : 0926");
					}
					
				}
				
				if (check == 7) {
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�.", "�˸�", JOptionPane.PLAIN_MESSAGE);
					mgr.updateStatus(userId); // update�� ������ �ش� id�� ���೻���� �������¸� ������ -> �����Ϸ�� ����
					frame.dispose();		
				} else {
					// tf ��� �Էµ��� �ʾҴٸ� 0���� �ʱ�ȭ�ؼ� �ٽ� ���
					check = 0;
				}
				
			}
		});
		p_btn.setBounds(270, 350, 80, 20);
		p_btn.setBackground(Color.BLACK);
		p_btn.setForeground(Color.WHITE);
		frame.add(p_btn);

		frame.setSize(600, 500);
		frame.setVisible(true);

		frame.validate();
		
	}


	public static void main(String[] args) {//����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardPaymentFrame cardPaymentFrame = new CardPaymentFrame();
					cardPaymentFrame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
