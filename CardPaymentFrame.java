package javaproject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CardPaymentFrame {
	private JFrame frame;
	PaymentMgr mgr = new PaymentMgr();
	
	// 생성자
	public CardPaymentFrame() {
		this(null);
	}
	
	// 생성자
	public CardPaymentFrame(String userId) {
		Label title = new Label("결제하기");
		
		Panel p1, p2, p3;
		
		Label c_numLb = new Label("카드번호");
		Label c_pwdLb = new Label("카드 비밀번호");
		Label c_cvcLb = new Label("CVC");
		Label c_yymm = new Label("유효기간");

		TextField c_numTf[] = new TextField[4]; // 카드번호 4자리
		TextField c_pwdTf = new TextField(5); // 카드 비밀번호
		TextField c_cvcTf = new TextField(5); // CVC
		TextField c_yyTf = new TextField(5); // 유효기간 년
		Label yy = new Label("년");
		Label mm = new Label("월");
		TextField c_mmTf = new TextField(5); // 유효기간 월
		Button p_btn = new Button("결제");

		
		// 프레임 설정
		frame = new JFrame("결제하기");
		Color c = new Color(255, 255, 255);

		Container con = frame.getContentPane();
		con.setBackground(c);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.setLayout(null);

		title.setBounds(270, 30, 50, 50);
		frame.add(title);

		c_numLb.setBounds(50, 100, 100, 20);
		frame.add(c_numLb);

		for (int i = 0; i < c_numTf.length; i++) {
			c_numTf[i] = new TextField();
		}

		c_numTf[0].setBounds(170, 100, 50, 20);
		c_numTf[1].setBounds(240, 100, 50, 20);
		c_numTf[2].setBounds(310, 100, 50, 20);
		c_numTf[3].setBounds(370, 100, 50, 20);

		// 카드번호 입력하는 텍스트필드 추가
		for (int i = 0; i < c_numTf.length; i++) {
			frame.add(c_numTf[i]);
		}

		c_pwdLb.setBounds(50, 150, 100, 20);
		frame.add(c_pwdLb);
		c_pwdTf.setBounds(170, 150, 50, 20);
		frame.add(c_pwdTf);

		c_cvcLb.setBounds(50, 200, 100, 20);
		frame.add(c_cvcLb);
		c_cvcTf.setBounds(170, 200, 50, 20);
		frame.add(c_cvcTf);

		c_yymm.setBounds(50, 250, 50, 20);
		frame.add(c_yymm);

		c_mmTf.setBounds(170, 250, 50, 20);
		frame.add(c_mmTf);

		mm.setBounds(220, 250, 20, 20);
		frame.add(mm);

		c_yyTf.setBounds(240, 250, 50, 20);
		frame.add(c_yyTf);

		yy.setBounds(290, 250, 20, 20);
		frame.add(yy);

		p_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int check=0; // 텍스트필드 모두 입력되었는지 확인용
				
				for (int j = 0; j < c_numTf.length; j++) {
					if (c_numTf[j].getText().trim().length() > 0) {
						if(c_numTf[j].getText().trim().length() == 4) {
							check++;
							System.out.println(check);
						} else {
							JOptionPane.showMessageDialog(null,"카드번호 " + (j+1) + "번째 칸 ERROR : " + "4자리로 채워주세요. (" + c_numTf[j].getText() + ")");
							System.out.println("카드번호 " + (j+1) + "번째 칸 ERROR : " + "4자리로 채워주세요. (" + c_numTf[j].getText() + ")");
						}
					}
				}
				if (c_pwdTf.getText().trim().length() > 0) {
					if(c_pwdTf.getText().trim().length() == 4) {
						check++;
						System.out.println(check);
					} else {
						JOptionPane.showMessageDialog(null,"총 4자리의 카드 비밀번호를 입력하세요. (" + c_pwdTf.getText() +")" );
						System.out.println("총 4자리의 카드 비밀번호를 입력하세요. (" + c_pwdTf.getText() +")" );
					}	
				}
				
				if (c_cvcTf.getText().trim().length() > 0) {
					if(c_cvcTf.getText().trim().length() == 3) {
						check++;
						System.out.println(check);
					} else {
						JOptionPane.showMessageDialog(null,"총 3자리의 CVC 번호를 입력하세요. (" + c_cvcTf.getText() + ")");
						System.out.println("총 3자리의 CVC 번호를 입력하세요. (" + c_cvcTf.getText() + ")");
					}
					
				}
				if (c_mmTf.getText().trim().length() > 0 && c_yyTf.getText().trim().length() > 0) {
					if(c_mmTf.getText().trim().length() == 2 && c_yyTf.getText().trim().length() == 2) {
						check++;
						System.out.println(check);
					} else {
						JOptionPane.showMessageDialog(null, "카드 유효기간 년월을 2자리로 입력하세요. Ex. MMYY : 0926");
						System.out.println("카드 유효기간 년월을 2자리로 입력하세요. Ex. MMYY : 0926");
					}
					
				}
				
				if (check == 7) {
					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
					mgr.updateStatus(userId); // update문 돌려서 해당 id의 예약내역의 결제상태를 결제전 -> 결제완료로 변경
					frame.dispose();		
				} else {
					// tf 모두 입력되지 않았다면 0으로 초기화해서 다시 계산
					check = 0;
				}
				
			}
		});
		p_btn.setBounds(270, 350, 80, 20);
		p_btn.setBackground(Color.BLACK);
		p_btn.setForeground(Color.WHITE);
		frame.add(p_btn);

		frame.setSize(600, 500);
		frame.setVisible(true);

		frame.validate();
		
	}


	public static void main(String[] args) {//실행
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardPaymentFrame cardPaymentFrame = new CardPaymentFrame();
					cardPaymentFrame.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
