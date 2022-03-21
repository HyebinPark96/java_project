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

//예약인원 추가할지 고민해보기 - 예약테이블에 예약인원컬럼, 방테이블에 수용인원 컬럼이 있어야함.

public class ReservationUser {

	LoginMgr mgr = new LoginMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, idLb, pwdLb, p_costLb, res_noLb, r_statusLb, r_roomLb, startdateLb, enddateLb, nameLb; 
	private JButton updateBtn, listBtn, cancelBtn, homeBtn;
	private JTextField res_noTf, startdateTf, enddateTf, r_statusTf, r_roomTf, p_costTf, idTf, nameTf;
	private JPasswordField pwdTf;
	
	//메인
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

	// 생성자
	public ReservationUser() {
		// 기본 셋팅
		jf.setSize(1200,800);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//모니터 크기
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		//프레임의 크기
		Dimension fDim = jf.getSize();
		
		// 프레임의 왼쪽 모서리 좌표
		// 중앙좌표 : (모니터 크기 - 프레임 크기) / 2
		int x = (int)((dim.getWidth()-fDim.getWidth())/2);
		int y = (int)((dim.getHeight()- fDim.getHeight())/2);
		
		// 프레임 위치 시키기
		jf.setLocation(x, y);
		
		// font 설정
		Font f1 = new Font("맑은 고딕", Font.BOLD, 40); //타이틀 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 12); //버튼 폰트
		Font f3 = new Font("맑은 고딕", Font.BOLD, 12); //라벨 폰트
		

		jf.setTitle("회원 예약 조회");
		p1 = new JPanel();
		
		//파넬꾸미기
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);

		//정보수정버튼
		updateBtn = new JButton("내정보수정");
		updateBtn.setBounds(20, 200, 100, 30);
		updateBtn.setFont(f2);
		updateBtn.setForeground(Color.white);
		updateBtn.setBackground(Color.black);
		p1.add(updateBtn);
		//정보수정으로 이동
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateUser();
				jf.dispose();
			}
		});
		
		//예약조회버튼(탭)
		listBtn = new JButton("내예약조회");
		listBtn.setBounds(20, 250, 100, 30);
		listBtn.setFont(f2);
		listBtn.setForeground(Color.black);
		listBtn.setBackground(Color.white);
		listBtn.setEnabled(false);
		p1.add(listBtn);
		
		//홈버튼(메인으로)
		homeBtn = new JButton("홈으로");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		//메인이동
		homeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage();
				jf.dispose();
			}
		});
		
		
		//타이틀라벨
		titleLb = new JLabel("내 예약 조회");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);
		
		//"예약번호" 라벨
		res_noLb = new JLabel("예약번호");
		res_noLb.setFont(f3);
		res_noLb.setBounds(450, 260, 100, 20);
		p1.add(res_noLb);
		//"예약번호" 텍스트필드
		res_noTf = new JTextField();
		res_noTf.setColumns(10);
		res_noTf.setBounds(520, 260, 160, 20);
		p1.add(res_noTf);
		
		//"아이디" 라벨
		idLb = new JLabel("아이디");
		idLb.setFont(f3);
		idLb.setBounds(450, 290, 100, 20);
		p1.add(idLb);
		//"아이디" 텍스트필드 
		idTf = new JTextField();
		idTf.setColumns(10);
		idTf.setBounds(520, 290, 160, 20);
		//idTf.setEditable(false);
		p1.add(idTf);
		
		//"이름" 라벨
		nameLb = new JLabel("예약자 성명");
		nameLb.setFont(f3);
		nameLb.setBounds(450, 320, 100, 20);
		p1.add(nameLb);
		//"이름" 텍스트필드
		nameTf = new JTextField();
		nameTf.setColumns(10);
		nameTf.setBounds(520, 320, 160, 20);
		p1.add(nameTf);
		
		//"예약 객실" 라벨
		r_roomLb = new JLabel("예약객실");
		r_roomLb.setFont(f3);
		r_roomLb.setBounds(450, 350, 100, 20);
		p1.add(r_roomLb);
		//"예약 객실" 텍스트필드
		r_roomTf = new JTextField();
		r_roomTf.setColumns(10);
		r_roomTf.setBounds(520, 350, 160, 20);
		p1.add(r_roomTf);
		
		//"체크인" 라벨
		startdateLb = new JLabel("체크인");
		startdateLb.setFont(f3);
		startdateLb.setBounds(450, 380, 100, 20);
		p1.add(startdateLb);
		//"체크인" 텍스트필드
		startdateTf = new JTextField();
		startdateTf.setColumns(10);
		startdateTf.setBounds(520, 380, 160, 20);
		p1.add(startdateTf);
		
		//"체크아웃" 라벨
		enddateLb = new JLabel("체크아웃");
		enddateLb.setFont(f3);
		enddateLb.setBounds(450, 410, 100, 20);
		p1.add(enddateLb);
		//"체크아웃" 텍스트필드
		enddateTf = new JTextField();
		enddateTf.setColumns(10);
		enddateTf.setBounds(520, 410, 160, 20);
		p1.add(enddateTf);		
		
		//"결제 상태" 라벨
		r_statusLb = new JLabel("결제 상태");
		r_statusLb.setFont(f3);
		r_statusLb.setBounds(450, 440, 100, 20);
		p1.add(r_statusLb);
		//"결제 상태" 텍스트필드
		r_statusTf = new JTextField();
		r_statusTf.setColumns(10);
		r_statusTf.setBounds(520, 440, 160, 20);
		p1.add(r_statusTf);	
		
		//"결제 금액" 라벨
		p_costLb = new JLabel("결제 금액");
		p_costLb.setFont(f3);
		p_costLb.setBounds(450, 470, 100, 20);
		p1.add(p_costLb);
		//"결제 금액" 텍스트필드
		p_costTf = new JTextField();
		p_costTf .setColumns(10);
		p_costTf .setBounds(520, 470, 160, 20);
		p1.add(p_costTf );	
		
		//"비밀번호" 라벨
		pwdLb = new JLabel("비밀번호");
		pwdLb.setFont(f3);
		pwdLb.setBounds(450, 590, 100, 20);
		p1.add(pwdLb);
		//"비밀번호" 텍스트필드
		pwdTf = new JPasswordField();
		pwdTf.setColumns(10);
		pwdTf.setBounds(520, 590, 160, 20);
		pwdTf.setEchoChar('●');
		p1.add(pwdTf);	
			
		
		//"저장" 버튼
		cancelBtn = new JButton("예약취소");
		cancelBtn.setBounds(450, 650, 240, 40);
		cancelBtn.setFont(f2);
		cancelBtn.setForeground(Color.WHITE);
		cancelBtn.setBackground(Color.BLACK);
		p1.add(cancelBtn);
		
		

		
		/*기능 구현*/
		
		// 예약정보불러오기
		
		// 예약 취소
		cancelBtn.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				//비밀번호, 이메일, 연락처 빈 칸으로 둔 경우
				if (pwdTf.getText().equals("")) { // 비밀번호 안씀
					JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.");
				} else if (!pwdTf.getText().equals("")) { //비번은썼음
					if(mgr.loginChk(idTf.getText().trim(), pwdTf.getText().trim())/* true */) { // 비밀번호와 아이디 체크
						JOptionPane.showMessageDialog(null, "예약취소를 하겠습니까?");
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
						pwdTf.setText("");
					}
				}
		// 새로고침
		jf.validate();
	}
});

}
}
		
	
