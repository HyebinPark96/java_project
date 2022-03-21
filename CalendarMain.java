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

	// �⺻ ���� 00�� 0��
	JLabel label = new JLabel("00�� 0��");

	// 7 * 7 = 49���� ��ư ����
	JButton[] buttons = new JButton[49];
	String[] dayNames = { "Su", "Mo", "Tu", "We", "Th", "Fr", "Sa" };

	// CalendarFunction Ŭ�����κ��� cf ��ü ����
	CalendarFunction cF = new CalendarFunction();

	JTextField dateTf = new JTextField(10);

	// ������
	public CalendarMain() {

		// �⺻ ����
		setTitle("���� �޷�");
		setSize(550, 400);
		setLocation(400, 400);

		// �޼ҵ� ȣ��
		init();
		start();

		setVisible(true);

	}

	private void start() {
		// ����â ���� �� ���μ������� ����ϰ� ���� �� �ִ�.
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// �׼Ǹ����� ����
		buttonAfter.addActionListener(this);
		buttonBefore.addActionListener(this);

	}

	private void init() {
		container.setLayout(new BorderLayout());
		container.add("North", panel1);
		container.add("Center", panel2);

		// ���η� �þ��� ����
		panel1.setLayout(new FlowLayout());
		panel1.add(buttonBefore);
		panel1.add(label);
		panel1.add(buttonAfter);
		panel1.add(dateTf);

		Font font = new Font("SansSerif", Font.BOLD, 20);
		buttonAfter.setFont(font);
		buttonBefore.setFont(font);
		label.setFont(font);

		// 00�� 0�� -> �ؽ�Ʈ �ʱ�ȭ -> ��¥�ؽ�Ʈ ��������
		label.setText(cF.getCalText());
		// 5,5 : ���� ��� ���� �� �׸��� ���� ���ݰ� ���� ����
		panel2.setLayout(new GridLayout(7, 7, 5, 5));

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(); // ��ư �迭 ��ü ����
			// �׼Ǹ����� ����
			buttons[i].addActionListener(this);
			panel2.add(buttons[i]);

			buttons[i].setFont(new Font("SansSerif", Font.BOLD, 24));

			// 0~6��° ��ư �迭 ���ҿ� datNames �迭 ������� �ֱ�
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

		if (e.getSource() == buttonAfter) { // 1�� ��
			gap = 1;
		} else if (e.getSource() == buttonBefore) { // 1�� ��
			gap = -1;
		}

		// cF��ü�� allInit �޼ҵ� ȣ��
		cF.allInit(gap);

		// ��� ���� ����
		label.setText(cF.getCalText());

		// ��¥��ư �����ϸ� setBackground �ٲ��, ��Ȱ��ȭ

		for (int i = 7; i < buttons.length; i++) {
			// ���� �����ϴ� ������
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
