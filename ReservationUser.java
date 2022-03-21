package javaproject;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import member.DialogBox;

//�����ο� �߰����� ����غ��� - �������̺� �����ο��÷�, �����̺� �����ο� �÷��� �־����.

public class ReservationUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, idLb, pwdLb, p_costLb, res_noLb, r_statusLb, r_roomLb, startdateLb, enddateLb, nameLb; 
	private JButton updateBtn, listBtn, cancelBtn, homeBtn;
	private JTextField res_noTf, startdateTf, enddateTf, r_statusTf, r_roomTf, p_costTf, idTf, nameTf;
	private JPasswordField pwdTf;
	
	//����
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ReservationUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������
	public ReservationUser() {
		// �⺻ ����
		jf.setSize(1200,800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//����� ũ��
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//�������� ũ��
		Dimension fDim = jf.getSize();
		
		// �������� ���� �𼭸� ��ǥ
		// �߾���ǥ : (����� ũ�� - ������ ũ��) / 2
		int x = (int)((dim.getWidth()-fDim.getWidth())/2);
		int y = (int)((dim.getHeight()- fDim.getHeight())/2);
		
		// ������ ��ġ ��Ű��
		jf.setLocation(x, y);
		
		// font ����
		Font f1 = new Font("���� ���", Font.BOLD, 40); //Ÿ��Ʋ ��Ʈ
		Font f2 = new Font("���� ���", Font.BOLD, 12); //��ư ��Ʈ
		Font f3 = new Font("���� ���", Font.BOLD, 12); //�� ��Ʈ
		

		jf.setTitle("ȸ�� ���� ��ȸ");
		p1 = new JPanel();
		
		//�ĳڲٹ̱�
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		//����������ư
		updateBtn = new JButton("����������");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.white);
		updateBtn.setBackground(Color.black);
		p1.add(updateBtn);
		//������������ �̵�
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUser();
				jf.dispose();
			}
		});
		
		//������ȸ��ư(��)
		listBtn = new JButton("��������ȸ");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.black);
		listBtn.setBackground(Color.white);
		listBtn.setEnabled(false);
		p1.add(listBtn);
		
		//Ȩ��ư(��������)
		homeBtn = new JButton("Ȩ����");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		//�����̵�
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage();
				jf.dispose();
			}
		});
		
		
		//Ÿ��Ʋ��
		titleLb = new JLabel("�� ���� ��ȸ");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);
		
		//"�����ȣ" ��
		res_noLb = new JLabel("�����ȣ");
		res_noLb.setFont(f3);
		res_noLb.setBounds(450, 260, 100, 20);
		p1.add(res_noLb);
		//"�����ȣ" �ؽ�Ʈ�ʵ�
		res_noTf = new JTextField();
		res_noTf.setColumns(10);
		res_noTf.setBounds(520, 260, 160, 20);
		p1.add(res_noTf);
		
		//"���̵�" ��
		idLb = new JLabel("���̵�");
		idLb.setFont(f3);
		idLb.setBounds(450, 290, 100, 20);
		p1.add(idLb);
		//"���̵�" �ؽ�Ʈ�ʵ� 
		idTf = new JTextField();
		idTf.setColumns(10);
		idTf.setBounds(520, 290, 160, 20);
		//idTf.setEditable(false);
		p1.add(idTf);
		
		//"�̸�" ��
		nameLb = new JLabel("������ ����");
		nameLb.setFont(f3);
		nameLb.setBounds(450, 320, 100, 20);
		p1.add(nameLb);
		//"�̸�" �ؽ�Ʈ�ʵ�
		nameTf = new JTextField();
		nameTf.setColumns(10);
		nameTf.setBounds(520, 320, 160, 20);
		p1.add(nameTf);
		
		//"���� ����" ��
		r_roomLb = new JLabel("���ఴ��");
		r_roomLb.setFont(f3);
		r_roomLb.setBounds(450, 350, 100, 20);
		p1.add(r_roomLb);
		//"���� ����" �ؽ�Ʈ�ʵ�
		r_roomTf = new JTextField();
		r_roomTf.setColumns(10);
		r_roomTf.setBounds(520, 350, 160, 20);
		p1.add(r_roomTf);
		
		//"üũ��" ��
		startdateLb = new JLabel("üũ��");
		startdateLb.setFont(f3);
		startdateLb.setBounds(450, 380, 100, 20);
		p1.add(startdateLb);
		//"üũ��" �ؽ�Ʈ�ʵ�
		startdateTf = new JTextField();
		startdateTf.setColumns(10);
		startdateTf.setBounds(520, 380, 160, 20);
		p1.add(startdateTf);
		
		//"üũ�ƿ�" ��
		enddateLb = new JLabel("üũ�ƿ�");
		enddateLb.setFont(f3);
		enddateLb.setBounds(450, 410, 100, 20);
		p1.add(enddateLb);
		//"üũ�ƿ�" �ؽ�Ʈ�ʵ�
		enddateTf = new JTextField();
		enddateTf.setColumns(10);
		enddateTf.setBounds(520, 410, 160, 20);
		p1.add(enddateTf);		
		
		//"���� ����" ��
		r_statusLb = new JLabel("���� ����");
		r_statusLb.setFont(f3);
		r_statusLb.setBounds(450, 440, 100, 20);
		p1.add(r_statusLb);
		//"���� ����" �ؽ�Ʈ�ʵ�
		r_statusTf = new JTextField();
		r_statusTf.setColumns(10);
		r_statusTf.setBounds(520, 440, 160, 20);
		p1.add(r_statusTf);	
		
		//"���� �ݾ�" ��
		p_costLb = new JLabel("���� �ݾ�");
		p_costLb.setFont(f3);
		p_costLb.setBounds(450, 470, 100, 20);
		p1.add(p_costLb);
		//"���� �ݾ�" �ؽ�Ʈ�ʵ�
		p_costTf = new JTextField();
		p_costTf .setColumns(10);
		p_costTf .setBounds(520, 470, 160, 20);
		p1.add(p_costTf );	
		
		//"��й�ȣ" ��
		pwdLb = new JLabel("��й�ȣ");
		pwdLb.setFont(f3);
		pwdLb.setBounds(450, 590, 100, 20);
		p1.add(pwdLb);
		//"��й�ȣ" �ؽ�Ʈ�ʵ�
		pwdTf = new JPasswordField();
		pwdTf.setColumns(10);
		pwdTf.setBounds(520, 590, 160, 20);
		pwdTf.setEchoChar('��');
		p1.add(pwdTf);	
			
		
		//"����" ��ư
		cancelBtn = new JButton("�������");
		cancelBtn.setBounds(450, 650, 240, 40);
		cancelBtn.setFont(f2);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(Color.BLACK);
		p1.add(cancelBtn);
		
		

		
		/*��� ����*/
		
		// ���������ҷ�����
		
		// ���� ���
		cancelBtn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				//��й�ȣ, �̸���, ����ó �� ĭ���� �� ���
				if (pwdTf.getText().equals("")) { // ��й�ȣ �Ⱦ�
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.");
				} else if (!pwdTf.getText().equals("")) { //���������
					if(mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim())/* true */) { // ��й�ȣ�� ���̵� üũ
						JOptionPane.showMessageDialog(null, "������Ҹ� �ϰڽ��ϱ�?");
					}else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						pwdTf.setText("");
					}
				}
		// ���ΰ�ħ
		jf.validate();
	}
});

}
}
		
	
