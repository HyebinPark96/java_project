package javaproject;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PaymentFrame2 extends JFrame {
	private Image bkImg = new ImageIcon("javaproject/images/background.png").getImage();
	private int check;
	private int totalCost;
	private String cardName[] = { "����ī��", "�Ｚī��", "��ī��", "KB����", "����ī��", "�Ե�ī��", "NH����", "�ϳ�ī��", "��Ƽī��", "UnionPay" };

	public static void main(String[] args) {// ����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentFrame2 paymentFrame2 = new PaymentFrame2();
					paymentFrame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// �׸��� �Լ�
	public void paint(Graphics g) {
		// ����̹���
		g.drawImage(bkImg, 0, 10, null);

		// ���м�
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);

		Font f2 = new Font("���� ���", Font.BOLD, 10); // ��ư ��Ʈ
		// ī��� ��ư �迭
		JButton cardBtn[] = new JButton[10]; // ũ�� 8 ����
		for (int i = 0; i < cardBtn.length; i++) {
			cardBtn[i] = new JButton(cardName[i]);
			cardBtn[i].setBackground(Color.WHITE);
			cardBtn[i].setFont(f2);
			add(cardBtn[i]);
		}

		cardBtn[0].setBounds(113, 155, 240, 40);
		cardBtn[1].setBounds(357, 155, 242, 40);
		cardBtn[2].setBounds(113, 200, 120, 40);
		cardBtn[3].setBounds(235, 200, 120, 40);
		cardBtn[4].setBounds(357, 200, 120, 40);
		cardBtn[5].setBounds(479, 200, 120, 40);
		cardBtn[6].setBounds(113, 245, 120, 40);
		cardBtn[7].setBounds(235, 245, 120, 40);
		cardBtn[8].setBounds(357, 245, 120, 40);
		cardBtn[9].setBounds(479, 245, 120, 40);

	}

	// ������
	public PaymentFrame2() {
		this(null);
	}

	// ������
	public PaymentFrame2(String userId) {

		// ������ ����
		setTitle(userId + "�� ����");// Ÿ��Ʋ
		setSize(800, 600);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		setLayout(null);// ���̾ƿ��� ������� ���������ϰ� ����.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame�� ���������� ����ǰ�

		// ��Ʈ
		Font f1 = new Font("���� ���", Font.BOLD, 20); // Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("���� ���", Font.BOLD, 10); // ��ư ��Ʈ
		Font f3 = new Font("���� ���", Font.PLAIN, 10); // �ؽ�Ʈ ��Ʈ

		// �÷�
		Color cardLbColor = new Color(226, 56, 61);

		// �ſ�ī�� ��
		Label cardLb = new Label("�ſ�ī��");
		add(cardLb);
		cardLb.setFont(f1);
		cardLb.setForeground(Color.WHITE);
		cardLb.setBackground(cardLbColor);
		cardLb.setBounds(10, 40, 90, 30);

		// �̿��� ��
		Label agmTitle = new Label("�̿���");
		agmTitle.setFont(f2);
		add(agmTitle);
		agmTitle.setBackground(Color.white);
		agmTitle.setBounds(120, 20, 70, 20);

		// ��ü���� üũ�ڽ�
		Checkbox allChkCb = new Checkbox("��ü����");
		allChkCb.setFont(f3);
		add(allChkCb);
		allChkCb.setBackground(Color.white);
		allChkCb.setBounds(530, 20, 60, 20);

		// 3�� ��� ��
		Label agmLb[];
		agmLb = new Label[3];
		agmLb[0] = new Label("���ڱ����ŷ� �̿���");
		agmLb[0].setBounds(120, 60, 140, 20);
		agmLb[1] = new Label("�������� ���� �� �̿�ȳ�");
		agmLb[1].setBounds(120, 80, 140, 20);
		agmLb[2] = new Label("�������� ���� �� ��Ź�ȳ�");
		agmLb[2].setBounds(400, 60, 140, 20);

		for (int i = 0; i < agmLb.length; i++) {
			agmLb[i].setFont(f3);
			agmLb[i].setBackground(Color.WHITE);

		}

		// 3���� ��� üũ�ڽ� �迭
		Checkbox agmCb[] = new Checkbox[3];

		for (int i = 0; i < agmCb.length; i++) {
			agmCb[i] = new Checkbox("����"); // üũ�ڽ� �迭 ��ü ����
			agmCb[i].setFont(f3);
			agmCb[i].setBackground(Color.WHITE);

			// ������ ������
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

		agmCb[0].setBounds(270, 58, 40, 20);
		agmCb[1].setBounds(270, 78, 40, 20);
		agmCb[2].setBounds(550, 58, 40, 20);

		// 3���� ��� �󺧰� 3���� ��� üũ�ڽ� ������ �߰�
		for (int i = 0; i < agmLb.length; i++) {
			add(agmLb[i]);
			add(agmCb[i]);
		}

		setLocationByPlatform(true);

		invalidate();
		revalidate();
		repaint();
	}

}
