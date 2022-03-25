/*-- ������������ ������� | ������ ������¥: 2022-03-25 | ������ ������: �輭��--*/

package javaproject;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin2 {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, res_noLb, idLb, r_roomLb, sdLb, edLb, hcLb, r_statLb, p_costLb, res_noLb2 ; 
	private JButton addBtn, uptBtn, delBtn, homeBtn;
	private JTextField res_noTf, idTf, r_roomTf, sdTf, edTf, hcTf, r_statTf, p_costTf, res_noTf2;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result; 
	private JTable table;
	private JScrollPane sp;
	private String room[] = {"101ȣ", "102ȣ", "201ȣ", "202ȣ"};
	private JComboBox<String> roomCombo; // �� �޺��ڽ�
	
	//����
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationAdmin2 reservationAdmin = new ReservationAdmin2();
					reservationAdmin.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ������ (�Ű�����)
	@SuppressWarnings({ "serial", "unchecked" })
	public ReservationAdmin2(String userId) {
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
		
		jf.setTitle("������������("+userId+")");
		p1 = new JPanel();
		
		model = new DefaultTableModel() {
			// ���̺� �� ���� �Ұ� (����Ʈ: ��������)
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		//�ĳ� ����
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);
		
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
		
		//���̺� ��ġ
		sp.setBounds(450, 200, 550, 400);
		p1.add(sp);
		
		//Ȩ��ư
		homeBtn = new JButton("Ȩ����");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		
		//Ÿ��Ʋ��
		titleLb = new JLabel("��ü ���� ��ȸ");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		//���̵� ��
		idLb = new JLabel("���̵�");
		p1.add(idLb);
		idLb.setFont(f3);
		idLb.setBounds(150, 200, 40, 20);
		
		//���̵� �ؽ�Ʈ�ʵ�
		idTf = new JTextField();
		p1.add(idTf);
		idTf.setBounds(220, 200, 150, 20);
		idTf.setEditable(false);
		
		//���� ��
		r_roomLb = new JLabel("����");
		p1.add(r_roomLb);
		r_roomLb.setFont(f3);
		r_roomLb.setBounds(150, 230, 40, 20);
		
		//���� �ؽ�Ʈ�ʵ�
//		r_roomTf = new JTextField();
//		p1.add(r_roomTf);
//		r_roomTf.setBounds(220, 230, 150, 20);
		
		roomCombo = new JComboBox<String>(room);
		p1.add(roomCombo);
		roomCombo.setBounds(220,230,150,20);
		roomCombo.setBackground(Color.WHITE);
		
		
		//üũ�� ��
		sdLb = new JLabel("üũ��");
		p1.add(sdLb);
		sdLb.setFont(f3);
		sdLb.setBounds(150,260,40,20);
		//üũ�� �ؽ�Ʈ�ʵ�
		sdTf = new JTextField();
		p1.add(sdTf);
		sdTf.setBounds(220, 260, 150, 20);
		
		//üũ�ƿ� ��
		edLb = new JLabel("üũ�ƿ�");
		p1.add(edLb);
		edLb.setFont(f3);
		edLb.setBounds(150, 290, 50, 20);
		//üũ�ƿ� �ؽ�Ʈ�ʵ�
		edTf = new JTextField();
		p1.add(edTf);
		edTf.setBounds(220, 290, 150, 20);
		
		//�����ο� ��
		hcLb = new JLabel("�����ο�");
		p1.add(hcLb);
		hcLb.setFont(f3);
		hcLb.setBounds(150, 320, 50, 20);
		//�����ο� �ؽ�Ʈ�ʵ�
		hcTf = new JTextField();
		p1.add(hcTf);
		hcTf.setBounds(220, 320, 150, 20);
		
		//���� ���� ��
		r_statLb = new JLabel("��������");
		p1.add(r_statLb);
		r_statLb.setFont(f3);
		r_statLb.setBounds(150, 350, 50, 20);
		//�������� �ؽ�Ʈ�ʵ�
		r_statTf = new JTextField();
		p1.add(r_statTf);
		r_statTf.setBounds(220, 350, 150, 20);
		r_statTf.setEditable(false);
		
		//�����ݾ� ��
		p_costLb = new JLabel("�����ݾ�");
		p1.add(p_costLb);
		p_costLb.setFont(f3);
		p_costLb.setBounds(150, 380, 50, 20);
		
		//�����ݾ� �ؽ�Ʈ�ʵ�
		p_costTf = new JTextField();
		p1.add(p_costTf);
		p_costTf.setBounds(220, 380, 150, 20);
		p_costTf.setEditable(false);
		
		//�����ȣ ��
		res_noLb2 = new JLabel("�����ȣ");
		p1.add(res_noLb2);
		res_noLb2.setFont(f3);
		res_noLb2.setBounds(150, 410, 50, 20);
		//�����ȣ �ؽ�Ʈ�ʵ�
		res_noTf2 = new JTextField();
		p1.add(res_noTf2);
		res_noTf2.setBounds(220, 410, 150, 20);
		res_noTf2.setEditable(false);
		
		
		
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
		delBtn.setBounds(900, 620, 100, 30);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.black);
		delBtn.setBackground(Color.white);
		p1.add(delBtn);		
		
		//�����ȣ ��
		res_noLb = new JLabel("�����ȣ ����");
		res_noLb.setBounds(650, 620, 100, 30);
		res_noLb.setFont(f3);
		p1.add(res_noLb);
		
		//�����ȣ �ؽ�Ʈ�ʵ�
		res_noTf = new JTextField();
		res_noTf.setBounds(740,620,150,30);
		res_noTf.setColumns(10);
		res_noTf.setEditable(false);
		p1.add(res_noTf);
		
		
		
		
		/*��� ����*/
		
		//���̺� Ŭ���� ����cell�� -> �ؽ�Ʈ�ʵ�� �������
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // ���� ���ڵ�(��) ��������
				
				String id = (String)table.getValueAt(row, 0); // ���� ���� 0��° �÷� �� ��������
				String roomNum =(String) table.getValueAt(row, 1); // ���� ���ȣ
				String sd = (String) table.getValueAt(row, 2); // ���ڵ��� ������
				String ed = (String) table.getValueAt(row, 3); // ���ڵ��� ������
				String hc = (String) table.getValueAt(row, 4); // ���� �ο�
				String res_no = (String)table.getValueAt(row, 7);
				
				idTf.setText(id);
				
				// ���� ����� ���� �޺��ڽ��� �⺻������ �����صα�
				if(roomNum.equals("101")) {
					roomCombo.setSelectedIndex(0);
				} else if(roomNum.equals("102")) {
					roomCombo.setSelectedIndex(1);
				} else if(roomNum.equals("201")) {
					roomCombo.setSelectedIndex(2);
				} else if(roomNum.equals("202")) {
					roomCombo.setSelectedIndex(3);
				}
				
				sdTf.setText(sd);
				edTf.setText(ed);
				
				hcTf.setText(hc);
				
				res_noTf.setText(res_no);
				
				System.out.println("(ResAdmin)���õ� �����ȣ:" + res_no);
				
				
			}
			
		
		}); 
		
		//�������: ������ ���ʷ� ����� �� -> AdminMgr�� �Ἥ ����
		delBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String res_no = res_noTf.getText();
				mgr.delete(res_no);
				
				//���� �� ��� �ҷ�����
				@SuppressWarnings("rawtypes")
				Vector result = mgr.selectAll();
				model.setDataVector(result, title);
				
			} 
		});
		
		//Ȩ��ư Ŭ�� �׼�: �������� �̵�
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage();
				jf.dispose();
				
			}
		});
		
		// ���ΰ�ħ
		jf.validate();
	}
	
	// ������
	public ReservationAdmin2() {
		this(null);
	}
}
