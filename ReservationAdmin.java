/*-- ������������ ������� | ������ ������¥: 2022-03-22 | ������ ������: �輭��--*/

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, res_noLb; 
	private JButton addBtn, uptBtn, delBtn, homeBtn;
	private JTextField res_noTf;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result; 
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
	@SuppressWarnings({ "serial", "unchecked" })
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
		
		sp.setBounds(200, 200, 800, 400);
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
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();

				String res_no = (String)table.getValueAt(row, column);
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

}
