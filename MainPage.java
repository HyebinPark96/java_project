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

// // 0322 박혜빈 수정 : 1. 게시판 삭제
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
		if(userId == null) {
			frame.setTitle("Stay With Us :: Reservation Program (비회원 님)");
		} else {
			frame.setTitle("Stay With Us :: Reservation Program (" + userId + " 님)");
		}
		System.out.println(userId + "님이 이용중입니다.");
	
		
		//JLabel 이미지추가
		JLabel jImg = new JLabel();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("javaproject/images/mainimage.png");
		ImageIcon icon = new ImageIcon(img);
		jImg.setIcon(icon);
		jImg.setBounds(0, 43, 1186, 720);
		frame.getContentPane().add(jImg);
		
		
		//회원가입 버튼 - 클릭시 회원가입 창 호출 -> 회원가입 완료 후 메인페이지
		JButton joinBtn = new JButton("회원가입");
		joinBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		joinBtn.setBackground(Color.WHITE);
		joinBtn.setBounds(603, 10, 95, 23);
		frame.getContentPane().add(joinBtn);
		
		if(userId == null) { // 비회원 (null)
			joinBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						new JoinFrame();
				}
			});
		} else {
			joinBtn.setEnabled(false);
		}
		
		//로그인 버튼 - 클릭시 로그인 창 호출 -> 로그인 완료 후 메인페이지
		JButton loginBtn = new JButton("로그인");		
		loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		loginBtn.setBackground(Color.WHITE);
		loginBtn.setBounds(699, 10, 95, 23);
		frame.getContentPane().add(loginBtn);
		
		if (userId == null) { // 비회원 (null)
			loginBtn.setEnabled(true);
			loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				frame.dispose();
			}
		});

		} else { // 회원 (userId)
			loginBtn.setEnabled(false);
		}
		
		//마이페이지 버튼 - 클릭시 마이페이지로 이동
		JButton myPageBtn = new JButton("마이페이지");
		myPageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userId == null) { // 비회원 (null)
					JOptionPane.showMessageDialog(null, "로그인을 먼저 진행해주세요.");
					System.out.println(userId + " : 아이디 못 불러옴");	
				} else { // 회원 (userId)
					new ReservationAWT(userId);
					System.out.println(userId + "님이 마이페이지 버튼을 클릭했습니다.");
				}
			}
		});
		myPageBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		myPageBtn.setBackground(Color.WHITE);
		myPageBtn.setBounds(796, 10, 95, 23);
		frame.getContentPane().add(myPageBtn);
		
		//예약버튼 - 클릭시 예약 페이지로 이동
		JButton reservationBtn = new JButton("예약");
		reservationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(userId ==null) { // 비회원 (null)
					JOptionPane.showMessageDialog(null, "로그인을 먼저 진행해주세요.");
					System.out.println(userId + " : 아이디 못 불러옴");
				} else { // 회원 (userId)
					new ReservationAWT(userId);
					System.out.println(userId + "님이 예약버튼을 클릭했습니다.");
				}
				
			}
		});
		reservationBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		reservationBtn.setBackground(Color.WHITE);
		reservationBtn.setBounds(382, 10, 113, 23);
		frame.getContentPane().add(reservationBtn);
		
		//펜션 둘러보기 버튼 - 클릭시 둘러보기 페이지로 이동
		JButton guideBtn = new JButton("펜션 둘러보기");
		guideBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Guide(userId);
				frame.dispose();
			}
		});
		guideBtn.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		guideBtn.setBackground(Color.WHITE);
		guideBtn.setBounds(244, 10, 136, 23);
		frame.getContentPane().add(guideBtn);
	}
	
	//생성자
	public MainPage() {
		this(null);	
	}


}
