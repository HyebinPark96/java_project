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
	
	//메인
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
	
	
	//생성자 (매개변수)
	public MainPage(String userId) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		frame.setTitle(userId + "님 반갑습니다.");
		System.out.println(userId + "님이 로그인 하셨습니다.");
	
		
		//JLabel 이미지추가
		JLabel jimg = new JLabel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("javaproject/images/mainimage.png");
		ImageIcon icon = new ImageIcon(img);
		jimg.setIcon(icon);
		jimg.setBounds(0, 43, 1186, 720);
		frame.getContentPane().add(jimg);
		
		
		//회원가입 버튼 - 클릭시 회원가입 창 호출 -> 회원가입 완료 후 메인페이지
		JButton joinbutton = new JButton("회원가입");
		joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					new JoinFrame();
			}
		});
		joinbutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinbutton.setBackground(Color.WHITE);
		joinbutton.setBounds(603, 10, 95, 23);
		frame.getContentPane().add(joinbutton);
		
		//로그인 버튼 - 클릭시 로그인 창 호출 -> 로그인 완료 후 메인페이지
		JButton loginbutton = new JButton("로그인");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				frame.dispose();
			}
		});
		loginbutton.setBackground(Color.WHITE);
		loginbutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinbutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinbutton.setBackground(Color.WHITE);
		loginbutton.setBounds(699, 10, 95, 23);
		frame.getContentPane().add(loginbutton);
		
		//마이페이지 버튼 - 클릭시 마이페이지로 이동
		JButton mypagebutton = new JButton("마이페이지");
		mypagebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mypagebutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		mypagebutton.setBackground(Color.WHITE);
		mypagebutton.setBounds(796, 10, 95, 23);
		frame.getContentPane().add(mypagebutton);
		
		//예약버튼 - 클릭시 예약 페이지로 이동
		JButton reservationbutton = new JButton("예약");
		reservationbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(userId!=null) {
					new ReservationAWT(userId);
					System.out.println(userId + "님이 예약버튼을 클릭했습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "로그인을 먼저 진행해주세요.");
					System.out.println(userId + " : 아이디 못 불러옴");
				}
				
			}
		});
		reservationbutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		reservationbutton.setBackground(Color.WHITE);
		reservationbutton.setBounds(382, 10, 113, 23);
		frame.getContentPane().add(reservationbutton);
		
		//펜션 둘러보기 버튼 - 클릭시 둘러보기 페이지로 이동
		JButton guidebutton = new JButton("펜션 둘러보기");
		guidebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Guide(userId);
				frame.dispose();
			}
		});
		guidebutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		guidebutton.setBackground(Color.WHITE);
		guidebutton.setBounds(244, 10, 136, 23);
		frame.getContentPane().add(guidebutton);
		
		//게시판 버튼 - 클릭시 게시판 이동 
		JButton boradbutton = new JButton("게시판");
		boradbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		boradbutton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		boradbutton.setBackground(Color.WHITE);
		boradbutton.setBounds(496, 10, 95, 23);
		frame.getContentPane().add(boradbutton);
		
	}
	
	//생성자
	public MainPage() {
		this(null);	
	}


}

