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
		//메소드 호출
		initialize();
	
	}

	//프레임 설정
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 500, 613);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);//팝업시 화면 가운데로 뜨기
		frame.setTitle("로그인 페이지");
		frame.setVisible(true);
		
		
		//panel 생성
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 10, 462, 556);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		//상단 panel (로그인 Text)
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(12, 10, 438, 89);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		//하단 panel
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(12, 108, 438, 438);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		//로그인 Label
		JLabel loginlabel = new JLabel("로그인");
		loginlabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginlabel.setFont(new Font("맑은 고딕", Font.BOLD, 34));
		loginlabel.setBounds(0, 0, 438, 89);
		panel_1.add(loginlabel);
		
		//아이디 Label
		JLabel idlabel = new JLabel("아이디");
		idlabel.setBounds(105, 29, 180, 40);
		idlabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		panel_2.add(idlabel);
		
		//아이디 텍스트필드
		idTf = new JTextField();
		idTf.setBounds(105, 67, 216, 31);
		panel_2.add(idTf);
		idTf.setColumns(10);
		
		//비밀번호 Label
		JLabel pwdlabel = new JLabel("비밀번호");
		pwdlabel.setBounds(105, 121, 180, 31);
		pwdlabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		panel_2.add(pwdlabel);
		
		//비밀번호 텍스트필드
		TextField pwdTf = new TextField();
		pwdTf.setBounds(105, 155, 216, 31);
		panel_2.add(pwdTf);
		pwdTf.setColumns(10);
		pwdTf.setEchoChar('*');//비밀번호 입력시 *로 출력
		
		//로그인 버튼 클릭 시, DB베이스 연동되어 로그인 성공 or 실패여부 확인
		JButton loginbutton = new JButton("로그인");
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				//클릭 이벤트 
				if(obj == loginbutton) {
					if(mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim())/*true*/) {
						JOptionPane.showMessageDialog(null, "로그인 성공하였습니다.");
						//new MainPage();//로그인 성공 시 메인화면으로 이동
						frame.dispose();
						
					}else {
						JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 정확하지 않습니다.");
						idTf.setText("");//아이디 입력 칸 리셋
						pwdTf.setText("");//비밀번호 입력 칸 리셋
					}
				}
			}
		});
		loginbutton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		loginbutton.setForeground(Color.WHITE);
		loginbutton.setBackground(Color.BLACK);
		loginbutton.setBounds(105, 240, 216, 40);
		panel_2.add(loginbutton);
		
		/*아이디 찾기 버튼 - 클릭시 아이디찾기 화면으로 전환 
		JButton idfbutton = new JButton("아이디");
		idfbutton.setForeground(Color.BLACK);
		idfbutton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		idfbutton.setBackground(Color.WHITE);
		idfbutton.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
			}
		});
		idfbutton.setBounds(105, 327, 82, 23);
		panel_2.add(idfbutton);*/
		
		/*비밀번호 찾기 버튼 - 클릭시 비밀번호 찾기 화면으로 전환.
		JButton pwdfbutton = new JButton("비밀번호찾기");
		pwdfbutton.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		pwdfbutton.setBackground(Color.WHITE);
		pwdfbutton.setBounds(185, 327, 136, 23);
		panel_2.add(pwdfbutton);*/
		
		//회원가입 버튼 - 클릭시 회원가입 화면으로 전환 
		JButton joinbutton = new JButton("회원가입");
		joinbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();//회원가입 창으로 이동
			}
		});
		joinbutton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		joinbutton.setBackground(Color.WHITE);
		joinbutton.setBounds(105, 314, 216, 40);
		panel_2.add(joinbutton);
	}
}
