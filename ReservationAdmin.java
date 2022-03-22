package javaproject;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb; 
	private JButton addBtn, uptBtn, delBtn, homeBtn;
	private DefaultTableModel model;
	private Vector title, data, result; 
	private JTable table;
	private JScrollPane sp;
	
	//����
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ReservationAdmin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������
	public ReservationAdmin() {
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
		
		jf.setTitle("������������");
		p1 = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		//�ĳ� ����
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);
		

		data = new Vector<>();
		
		//���൥���� ���̺�
		result = mgr.selectAll();
		title = new Vector<>();
		title.add("id");
		title.add("����");
		title.add("üũ��");
		title.add("üũ�ƿ�");
		title.add("�����ο�");
		title.add("���� ����");
		title.add("���� �ݾ�");
		title.add("�����ȣ");
		model.setDataVector(result, title);
		
		sp.setBounds(200, 200, 800, 400);
		p1.add(sp);
		

		
		//���

//		table.addMouseListener(new MouseAdapter() {
//		
//		public void mouseClicked(MouseEvent e) {
//		int index = table.getSelectedRow();	
//		
//		Vector in = (Vector) data.get(index);
//		
//		String id = (String)in.get(0);
//		String r_room = (String)in.get(1);
//		String startdate = (String)in.get(2);
//		String enddate = (String)in.get(3);
//		String headcount = (String)in.get(4);
//		String r_status = (String)in.get(5);
//		String p_cost = (String)in.get(6);
//		String res_no = (String)in.get(7);
//		
//		idTf.setText(id);
//		r_roomTf.setText(r_room);
//		
//		}
//			
//		});
		

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
		titleLb = new JLabel("��ü ���� ��ȸ");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		//���� �߰� ��ư
		addBtn = new JButton("���� �߰�");
		addBtn.setBounds(200, 620, 100, 30);
		addBtn.setFont(f2);
		addBtn.setForeground(Color.black);
		addBtn.setBackground(Color.white);
		p1.add(addBtn);
		
		//���� ���� ��ư
		uptBtn = new JButton("���� ����");
		uptBtn.setBounds(360, 620, 100, 30);
		uptBtn.setFont(f2);
		uptBtn.setForeground(Color.black);
		uptBtn.setBackground(Color.white);
		p1.add(uptBtn);
		
		//���� ���� ��ư
		delBtn = new JButton("���� ����");
		delBtn.setBounds(520, 620, 100, 30);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.black);
		delBtn.setBackground(Color.white);
		p1.add(delBtn);		

		/*��� ����*/
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			} }
		);
		

		// ���ΰ�ħ
		jf.validate();
	}


}


		
	
