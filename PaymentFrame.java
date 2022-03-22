package javaproject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PaymentFrame {
	private JFrame jf; 
	private int check;
	private int totalCost;
	
	// ������
	public PaymentFrame() {
		this(null);
	}
	
	// ������
	public PaymentFrame(String userId) {
		Panel p1, p2, p3, p4;
		Checkbox agmCb[]; // ��� ���� üũ�ڽ��� �迭���ҷ� ���

		Label title; // �����ϱ�
		Label agmTitle = new Label("�̿���"); // �̿���
		Label agmLb[]; // ��� ���� ���� �迭���ҷ� ���

		Label cardNameLb = new Label("��������");
		String cardName[] = { "�Ｚ", "��", "�ϳ�", "īī����ũ", "KB����", "�츮", "����", "NHä��" };
		JRadioButton cardBtn[]; // ī��� ���� ��ư
		ButtonGroup cdBtg; // ��ư �׷�

		Label p_costLb; // �����ݾ�
		JTextField p_costTf; // ��ü �����ݾ� �ؽ�Ʈ�ʵ�
		Label krwLb = new Label("��");

		Button p_btn; // ���� ��ư

		jf = new JFrame("�����ϱ�");
		
		Color c = new Color(255, 255, 255);
		Container con = jf.getContentPane();
		con.setBackground(c);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(300, 300);

		// ���̾ƿ� ���� : null��
		jf.setLayout(null);

		title = new Label("�����ϱ�");

		agmLb = new Label[3];
		agmLb[0] = new Label("���ڱ����ŷ� �̿���");
		agmLb[1] = new Label("�������� ���� �� ��Ź�ȳ�");
		agmLb[2] = new Label("�������� ���� �� �̿�ȳ�");

		// üũ�ڽ� �迭 ũ�� ����
		agmCb = new Checkbox[3];

		// üũ�ڽ� �迭 ��ü ����
		agmCb[0] = new Checkbox("����");
		agmCb[1] = new Checkbox("����");
		agmCb[2] = new Checkbox("����");
		
		
		for (int i = 0; i < agmCb.length; i++) {
			agmCb[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						check++; // private ������ �����ؼ� ��ܿ� �ø��� �ذ�
						System.out.println(check);
					} else if (e.getStateChange() == ItemEvent.DESELECTED) {
						check--;
						System.out.println(check);
					}
				}
			});
		}

		title.setBounds(280, 30, 50, 50);
		jf.add(title);

		agmTitle.setBounds(60, 70, 50, 20);
		jf.add(agmTitle);

		agmLb[0].setBounds(80, 100, 150, 20);
		agmCb[0].setBounds(240, 100, 40, 20);
		agmLb[1].setBounds(310, 100, 150, 20);
		agmCb[1].setBounds(470, 100, 40, 20);
		agmLb[2].setBounds(80, 120, 150, 20);
		agmCb[2].setBounds(240, 120, 40, 20);

		// (���) ��, üũ�ڽ� �߰�
		for (int i = 0; i < agmLb.length; i++) {
			jf.add(agmLb[i]);
			jf.add(agmCb[i]);
		}

		cardNameLb.setBounds(60, 150, 50, 20); // �������� ��
		jf.add(cardNameLb); // �� �߰�

		// ī��� �����ϱ� ���� radio��ư �׷� �����
		cardBtn = new JRadioButton[8]; // ũ�� 8 ����

		for (int i = 0; i < cardBtn.length; i++) {
			if (i == 0) {
				cardBtn[i] = new JRadioButton(cardName[i], true);
			} else {
				cardBtn[i] = new JRadioButton(cardName[i], false);
			}
			cardBtn[i].setBackground(Color.WHITE);
		}

		cardBtn[0].setBounds(80, 180, 70, 20);
		cardBtn[1].setBounds(200, 180, 70, 20);
		cardBtn[2].setBounds(320, 180, 100, 20);
		cardBtn[3].setBounds(440, 180, 100, 20);
		cardBtn[4].setBounds(80, 230, 100, 20);
		cardBtn[5].setBounds(200, 230, 100, 20);
		cardBtn[6].setBounds(320, 230, 100, 20);
		cardBtn[7].setBounds(440, 230, 100, 20);

		// �ϳ��� �����ϱ� ���� �׷�ȭ
		cdBtg = new ButtonGroup();

		for (int j = 0; j < cardBtn.length; j++) {
			jf.add(cardBtn[j]);
			// ��ư �׷쿡 ���(���߼��� �Ұ���)
			cdBtg.add(cardBtn[j]);
		}

		p_costLb = new Label("�����ݾ�");
		p_costTf = new JTextField(10);
		
		PaymentMgr mgr = new PaymentMgr();
		totalCost = mgr.totalCostChk(userId);
		p_costTf.setText(totalCost+"");

		p_costLb.setBounds(280, 270, 50, 30);
		jf.add(p_costLb);

		p_costTf.setBounds(270, 300, 80, 30);
		jf.add(p_costTf);

		krwLb.setForeground(Color.RED);
		krwLb.setBounds(350, 300, 20, 30);
		jf.add(krwLb);

		p_btn = new Button("����");
		p_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(check);
				// ��� ��� ���� + �������� 1�� ����(����Ʈ�� 1�� �����Ǿ� ����) 
				// + �����ݾ� �ؽ�Ʈ�ʵ� ���� �ƴҶ� ����â�� �ߴ� �̺�Ʈ
				if (check == 3 && p_costTf.getText().trim().length() > 0) {
					// ī�����â���� �Ѿ
					CardPaymentFrame cpf = new CardPaymentFrame(userId);
					jf.dispose();
				} else {
					// �Է� ���� �̺���
					JOptionPane.showMessageDialog(null, "��� ����� �������ּ���.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		p_btn.setBounds(270, 350, 80, 20);
		p_btn.setBackground(Color.BLACK);
		p_btn.setForeground(Color.WHITE);
		jf.add(p_btn);

		// �⺻ ����
		jf.setSize(600, 500);
		jf.setVisible(true);

		// ���ΰ�ħ
		jf.validate();
	}


	public static void main(String[] args) {
		// ������ ȣ��
		new PaymentFrame();

	}

}
