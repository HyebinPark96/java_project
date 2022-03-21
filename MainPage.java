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

public class MainPage {
	
	private JFrame frame;
	
	//생성자
	public MainPage() {
		initialize();
	}

	
	private void initialize() {//프레임 설정
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1200, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
	
		
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
				new Login();
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
				new Guide();
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

	//메인
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainPage window = new MainPage();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			}

}
