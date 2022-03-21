package javaproject;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {
	LoginMgr mgr = new LoginMgr();

	private JFrame frame;
	private JTextField idTf;
	private JTextField pwdTf;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		//�޼ҵ� ȣ��
		initialize();
	
	}

	//������ ����
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 500, 613);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);//�˾��� ȭ�� ����� �߱�
		frame.setTitle("�α��� ������");
		frame.setVisible(true);
		
		
		//panel ����
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 462, 556);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//��� panel (�α��� Text)
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 438, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		//�ϴ� panel
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 108, 438, 438);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		//�α��� Label
		JLabel loginlabel = new JLabel("�α���");
		loginlabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginlabel.setFont(new Font("���� ���", Font.BOLD, 34));
		loginlabel.setBounds(0, 0, 438, 89);
		panel_1.add(loginlabel);
		
		//���̵� Label
		JLabel idlabel = new JLabel("���̵�");
		idlabel.setBounds(105, 29, 180, 40);
		idlabel.setFont(new Font("���� ���", Font.BOLD, 16));
		panel_2.add(idlabel);
		
		//���̵� �ؽ�Ʈ�ʵ�
		idTf = new JTextField();
		idTf.setBounds(105, 67, 216, 31);
		panel_2.add(idTf);
		idTf.setColumns(10);
		
		//��й�ȣ Label
		JLabel pwdlabel = new JLabel("��й�ȣ");
		pwdlabel.setBounds(105, 121, 180, 31);
		pwdlabel.setFont(new Font("���� ���", Font.BOLD, 16));
		panel_2.add(pwdlabel);
		
		//��й�ȣ �ؽ�Ʈ�ʵ�
		TextField pwdTf = new TextField();
		pwdTf.setBounds(105, 155, 216, 31);
		panel_2.add(pwdTf);
		pwdTf.setColumns(10);
		pwdTf.setEchoChar('*');//��й�ȣ �Է½� *�� ���
		
		//�α��� ��ư Ŭ�� ��, DB���̽� �����Ǿ� �α��� ���� or ���п��� Ȯ��
		JButton loginbutton = new JButton("�α���");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				//Ŭ�� �̺�Ʈ 
				if(obj == loginbutton) {
					if(mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim())/*true*/) {
						JOptionPane.showMessageDialog(null, "�α��� �����Ͽ����ϴ�.");
						//new MainPage();//�α��� ���� �� ����ȭ������ �̵�
						frame.dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "���̵� �Ǵ� ��й�ȣ�� ��Ȯ���� �ʽ��ϴ�.");
						idTf.setText("");//���̵� �Է� ĭ ����
						pwdTf.setText("");//��й�ȣ �Է� ĭ ����
					}
				}
			}
		});
		loginbutton.setFont(new Font("���� ���", Font.BOLD, 16));
		loginbutton.setForeground(Color.WHITE);
		loginbutton.setBackground(Color.BLACK);
		loginbutton.setBounds(105, 240, 216, 40);
		panel_2.add(loginbutton);
		
		/*���̵� ã�� ��ư - Ŭ���� ���̵�ã�� ȭ������ ��ȯ 
		JButton idfbutton = new JButton("���̵�");
		idfbutton.setForeground(Color.BLACK);
		idfbutton.setFont(new Font("���� ���", Font.BOLD, 13));
		idfbutton.setBackground(Color.WHITE);
		idfbutton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			}
		});
		idfbutton.setBounds(105, 327, 82, 23);
		panel_2.add(idfbutton);*/
		
		/*��й�ȣ ã�� ��ư - Ŭ���� ��й�ȣ ã�� ȭ������ ��ȯ.
		JButton pwdfbutton = new JButton("��й�ȣã��");
		pwdfbutton.setFont(new Font("���� ���", Font.BOLD, 13));
		pwdfbutton.setBackground(Color.WHITE);
		pwdfbutton.setBounds(185, 327, 136, 23);
		panel_2.add(pwdfbutton);*/
		
		//ȸ������ ��ư - Ŭ���� ȸ������ ȭ������ ��ȯ 
		JButton joinbutton = new JButton("ȸ������");
		joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();//ȸ������ â���� �̵�
			}
		});
		joinbutton.setFont(new Font("���� ���", Font.BOLD, 16));
		joinbutton.setBackground(Color.WHITE);
		joinbutton.setBounds(105, 314, 216, 40);
		panel_2.add(joinbutton);
	}
}
