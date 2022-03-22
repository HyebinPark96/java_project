package javaproject;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PaymentFrame {
	private JFrame jf; 
	private int check;
	private int totalCost;
	
	// 생성자
	public PaymentFrame() {
		this(null);
	}
	
	// 생성자
	public PaymentFrame(String userId) {
		Panel p1, p2, p3, p4;
		Checkbox agmCb[]; // 약관 동의 체크박스들 배열원소로 담기

		Label title; // 결제하기
		Label agmTitle = new Label("이용약관"); // 이용약관
		Label agmLb[]; // 약관 동의 문장 배열원소로 담기

		Label cardNameLb = new Label("결제수단");
		String cardName[] = { "삼성", "비씨", "하나", "카카오뱅크", "KB국민", "우리", "신한", "NH채움" };
		JRadioButton cardBtn[]; // 카드사 선택 버튼
		ButtonGroup cdBtg; // 버튼 그룹

		Label p_costLb; // 결제금액
		JTextField p_costTf; // 전체 결제금액 텍스트필드
		Label krwLb = new Label("원");

		Button p_btn; // 결제 버튼

		jf = new JFrame("결제하기");
		
		Color c = new Color(255, 255, 255);
		Container con = jf.getContentPane();
		con.setBackground(c);
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(300, 300);

		// 레이아웃 지정 : null값
		jf.setLayout(null);

		title = new Label("결제하기");

		agmLb = new Label[3];
		agmLb[0] = new Label("전자금융거래 이용약관");
		agmLb[1] = new Label("개인정보 제공 및 위탁안내");
		agmLb[2] = new Label("개인정보 수집 및 이용안내");

		// 체크박스 배열 크기 지정
		agmCb = new Checkbox[3];

		// 체크박스 배열 객체 생성
		agmCb[0] = new Checkbox("동의");
		agmCb[1] = new Checkbox("동의");
		agmCb[2] = new Checkbox("동의");
		
		
		for (int i = 0; i < agmCb.length; i++) {
			agmCb[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						check++; // private 변수로 변경해서 상단에 올리니 해결
						System.out.println(check);
					} else if (e.getStateChange() == ItemEvent.DESELECTED) {
						check--;
						System.out.println(check);
					}
				}
			});
		}

		title.setBounds(280, 30, 50, 50);
		jf.add(title);

		agmTitle.setBounds(60, 70, 50, 20);
		jf.add(agmTitle);

		agmLb[0].setBounds(80, 100, 150, 20);
		agmCb[0].setBounds(240, 100, 40, 20);
		agmLb[1].setBounds(310, 100, 150, 20);
		agmCb[1].setBounds(470, 100, 40, 20);
		agmLb[2].setBounds(80, 120, 150, 20);
		agmCb[2].setBounds(240, 120, 40, 20);

		// (약관) 라벨, 체크박스 추가
		for (int i = 0; i < agmLb.length; i++) {
			jf.add(agmLb[i]);
			jf.add(agmCb[i]);
		}

		cardNameLb.setBounds(60, 150, 50, 20); // 결제수단 라벨
		jf.add(cardNameLb); // 라벨 추가

		// 카드사 선택하기 위해 radio버튼 그룹 만들기
		cardBtn = new JRadioButton[8]; // 크기 8 지정

		for (int i = 0; i < cardBtn.length; i++) {
			if (i == 0) {
				cardBtn[i] = new JRadioButton(cardName[i], true);
			} else {
				cardBtn[i] = new JRadioButton(cardName[i], false);
			}
			cardBtn[i].setBackground(Color.WHITE);
		}

		cardBtn[0].setBounds(80, 180, 70, 20);
		cardBtn[1].setBounds(200, 180, 70, 20);
		cardBtn[2].setBounds(320, 180, 100, 20);
		cardBtn[3].setBounds(440, 180, 100, 20);
		cardBtn[4].setBounds(80, 230, 100, 20);
		cardBtn[5].setBounds(200, 230, 100, 20);
		cardBtn[6].setBounds(320, 230, 100, 20);
		cardBtn[7].setBounds(440, 230, 100, 20);

		// 하나만 선택하기 위해 그룹화
		cdBtg = new ButtonGroup();

		for (int j = 0; j < cardBtn.length; j++) {
			jf.add(cardBtn[j]);
			// 버튼 그룹에 담기(다중선택 불가능)
			cdBtg.add(cardBtn[j]);
		}

		p_costLb = new Label("결제금액");
		p_costTf = new JTextField(10);
		
		PaymentMgr mgr = new PaymentMgr();
		totalCost = mgr.totalCostChk(userId);
		p_costTf.setText(totalCost+"");

		p_costLb.setBounds(280, 270, 50, 30);
		jf.add(p_costLb);

		p_costTf.setBounds(270, 300, 80, 30);
		jf.add(p_costTf);

		krwLb.setForeground(Color.RED);
		krwLb.setBounds(350, 300, 20, 30);
		jf.add(krwLb);

		p_btn = new Button("결제");
		p_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(check);
				// 약관 모두 동의 + 결제수단 1개 선택(디폴트로 1개 지정되어 있음) 
				// + 결제금액 텍스트필드 공백 아닐때 결제창이 뜨는 이벤트
				if (check == 3 && p_costTf.getText().trim().length() > 0) {
					// 카드결제창으로 넘어감
					CardPaymentFrame cpf = new CardPaymentFrame(userId);
					jf.dispose();
				} else {
					// 입력 조건 미부합
					JOptionPane.showMessageDialog(null, "모든 약관에 동의해주세요.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		p_btn.setBounds(270, 350, 80, 20);
		p_btn.setBackground(Color.BLACK);
		p_btn.setForeground(Color.WHITE);
		jf.add(p_btn);

		// 기본 셋팅
		jf.setSize(600, 500);
		jf.setVisible(true);

		// 새로고침
		jf.validate();
	}


	public static void main(String[] args) {
		// 생성자 호출
		new PaymentFrame();

	}

}
