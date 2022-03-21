package javaproject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CardPaymentFrame implements ActionListener {

	// 필드선언
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

	int check; // 텍스트필드 모두 입력되었는지 확인용

	public CardPaymentFrame() {
		JFrame jf = new JFrame("결제하기");

		Color c = new Color(255, 255, 255);

		Container con = jf.getContentPane();

		con.setBackground(c);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jf.setSize(300, 300);

		jf.setVisible(true);

		jf.setLayout(null);

		title.setBounds(270, 30, 50, 50);
		jf.add(title);

		c_numLb.setBounds(50, 100, 100, 20);
		jf.add(c_numLb);

		for (int i = 0; i < c_numTf.length; i++) {
			c_numTf[i] = new TextField();
		}

		// 코드 줄일 방법 생각해보기
		c_numTf[0].setBounds(170, 100, 50, 20);
		c_numTf[1].setBounds(240, 100, 50, 20);
		c_numTf[2].setBounds(310, 100, 50, 20);
		c_numTf[3].setBounds(370, 100, 50, 20);

		// 카드번호 입력하는 텍스트필드 추가
		for (int i = 0; i < c_numTf.length; i++) {
			jf.add(c_numTf[i]);
		}

		c_pwdLb.setBounds(50, 150, 100, 20);
		jf.add(c_pwdLb);
		c_pwdTf.setBounds(170, 150, 50, 20);
		jf.add(c_pwdTf);

		c_cvcLb.setBounds(50, 200, 100, 20);
		jf.add(c_cvcLb);
		c_cvcTf.setBounds(170, 200, 50, 20);
		jf.add(c_cvcTf);

		c_yymm.setBounds(50, 250, 50, 20);
		jf.add(c_yymm);

		c_mmTf.setBounds(170, 250, 50, 20);
		jf.add(c_mmTf);

		mm.setBounds(220, 250, 20, 20);
		jf.add(mm);

		c_yyTf.setBounds(240, 250, 50, 20);
		jf.add(c_yyTf);

		yy.setBounds(290, 250, 20, 20);
		jf.add(yy);

		p_btn.addActionListener(this);
		p_btn.setBounds(270, 350, 80, 20);
		p_btn.setBackground(Color.BLACK);
		p_btn.setForeground(Color.WHITE);
		jf.add(p_btn);

		jf.setSize(600, 500);
		jf.setVisible(true);

		jf.validate();

	}

	// 액션리스너 이벤트
	public void check() {
		for (int j = 0; j < c_numTf.length; j++) {
			if (c_numTf[j].getText().trim().length() > 0) {
				check++;
				System.out.println(check);
			}
		}
		if (c_pwdTf.getText().trim().length() > 0) {
			check++;
			System.out.println(check);
		}
		if (c_cvcTf.getText().trim().length() > 0) {
			check++;
			System.out.println(check);
		}
		if (c_mmTf.getText().trim().length() > 0 && c_yyTf.getText().trim().length() > 0) {
			check++;
			System.out.println(check);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == p_btn) {
			check();
			if (check == 7) {
				// tf 모두 입력됐을 경우 결제 완료 알림창 후 정상 종료
				// Q. 중간정렬 or 아이콘 (4번째매개변수)
				JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
				System.exit(1);
			} else {
				// tf 모두 입력되지 않았다면 0으로 초기화해서 다시 계산
				check = 0;
				// System.out.println(check);
			}

		}
	}

	public static void main(String[] args) {

	}

}
