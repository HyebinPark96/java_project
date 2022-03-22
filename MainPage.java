package javaproject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainPage {
	
	private JFrame frame;
	
	//����
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
							MainPage mainPage = new MainPage();
							mainPage.frame.setVisible(true);					
						

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	
	
	//������ (�Ű�����)
	public MainPage(String userId) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		frame.setTitle(userId + "�� �ݰ����ϴ�.");
		System.out.println(userId + "���� �α��� �ϼ̽��ϴ�.");
	
		
		//JLabel �̹����߰�
		JLabel jimg = new JLabel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("javaproject/images/mainimage.png");
		ImageIcon icon = new ImageIcon(img);
		jimg.setIcon(icon);
		jimg.setBounds(0, 43, 1186, 720);
		frame.getContentPane().add(jimg);
		
		
		//ȸ������ ��ư - Ŭ���� ȸ������ â ȣ�� -> ȸ������ �Ϸ� �� ����������
		JButton joinbutton = new JButton("ȸ������");
		joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new JoinFrame();
			}
		});
		joinbutton.setFont(new Font("���� ���", Font.BOLD, 12));
		joinbutton.setBackground(Color.WHITE);
		joinbutton.setBounds(603, 10, 95, 23);
		frame.getContentPane().add(joinbutton);
		
		//�α��� ��ư - Ŭ���� �α��� â ȣ�� -> �α��� �Ϸ� �� ����������
		JButton loginbutton = new JButton("�α���");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				frame.dispose();
			}
		});
		loginbutton.setBackground(Color.WHITE);
		loginbutton.setFont(new Font("���� ���", Font.BOLD, 12));
		joinbutton.setFont(new Font("���� ���", Font.BOLD, 12));
		joinbutton.setBackground(Color.WHITE);
		loginbutton.setBounds(699, 10, 95, 23);
		frame.getContentPane().add(loginbutton);
		
		//���������� ��ư - Ŭ���� ������������ �̵�
		JButton mypagebutton = new JButton("����������");
		mypagebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mypagebutton.setFont(new Font("���� ���", Font.BOLD, 12));
		mypagebutton.setBackground(Color.WHITE);
		mypagebutton.setBounds(796, 10, 95, 23);
		frame.getContentPane().add(mypagebutton);
		
		//�����ư - Ŭ���� ���� �������� �̵�
		JButton reservationbutton = new JButton("����");
		reservationbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(userId!=null) {
					new ReservationAWT(userId);
					System.out.println(userId + "���� �����ư�� Ŭ���߽��ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "�α����� ���� �������ּ���.");
					System.out.println(userId + " : ���̵� �� �ҷ���");
				}
				
			}
		});
		reservationbutton.setFont(new Font("���� ���", Font.BOLD, 12));
		reservationbutton.setBackground(Color.WHITE);
		reservationbutton.setBounds(382, 10, 113, 23);
		frame.getContentPane().add(reservationbutton);
		
		//��� �ѷ����� ��ư - Ŭ���� �ѷ����� �������� �̵�
		JButton guidebutton = new JButton("��� �ѷ�����");
		guidebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Guide(userId);
				frame.dispose();
			}
		});
		guidebutton.setFont(new Font("���� ���", Font.BOLD, 12));
		guidebutton.setBackground(Color.WHITE);
		guidebutton.setBounds(244, 10, 136, 23);
		frame.getContentPane().add(guidebutton);
		
		//�Խ��� ��ư - Ŭ���� �Խ��� �̵� 
		JButton boradbutton = new JButton("�Խ���");
		boradbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boradbutton.setFont(new Font("���� ���", Font.BOLD, 12));
		boradbutton.setBackground(Color.WHITE);
		boradbutton.setBounds(496, 10, 95, 23);
		frame.getContentPane().add(boradbutton);
		
	}
	
	//������
	public MainPage() {
		this(null);	
	}


}

