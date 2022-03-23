package javaproject;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PaymentFrame2 extends JFrame {
	private Image bkImg = new ImageIcon("javaproject/images/background.png").getImage();
	private int check;
	private int totalCost;
	private String cardName[] = { "현대카드", "삼성카드", "비씨카드", "KB국민", "신한카드", "롯데카드", "NH농협", "하나카드", "씨티카드", "UnionPay" };

	public static void main(String[] args) {// 실행
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentFrame2 paymentFrame2 = new PaymentFrame2();
					paymentFrame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 그리기 함수
	public void paint(Graphics g) {
		// 배경이미지
		g.drawImage(bkImg, 0, 10, null);

		// 구분선
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);

		Font f2 = new Font("맑은 고딕", Font.BOLD, 10); // 버튼 폰트
		// 카드사 버튼 배열
		JButton cardBtn[] = new JButton[10]; // 크기 8 지정
		for (int i = 0; i < cardBtn.length; i++) {
			cardBtn[i] = new JButton(cardName[i]);
			cardBtn[i].setBackground(Color.WHITE);
			cardBtn[i].setFont(f2);
			add(cardBtn[i]);
		}

		cardBtn[0].setBounds(113, 155, 240, 40);
		cardBtn[1].setBounds(357, 155, 242, 40);
		cardBtn[2].setBounds(113, 200, 120, 40);
		cardBtn[3].setBounds(235, 200, 120, 40);
		cardBtn[4].setBounds(357, 200, 120, 40);
		cardBtn[5].setBounds(479, 200, 120, 40);
		cardBtn[6].setBounds(113, 245, 120, 40);
		cardBtn[7].setBounds(235, 245, 120, 40);
		cardBtn[8].setBounds(357, 245, 120, 40);
		cardBtn[9].setBounds(479, 245, 120, 40);

	}

	// 생성자
	public PaymentFrame2() {
		this(null);
	}

	// 생성자
	public PaymentFrame2(String userId) {

		// 프레임 설정
		setTitle(userId + "님 결제");// 타이틀
		setSize(800, 600);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setLayout(null);// 레이아웃을 내맘대로 설정가능하게 해줌.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게

		// 폰트
		Font f1 = new Font("맑은 고딕", Font.BOLD, 20); // 타이틀 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 10); // 버튼 폰트
		Font f3 = new Font("맑은 고딕", Font.PLAIN, 10); // 텍스트 폰트

		// 컬러
		Color cardLbColor = new Color(226, 56, 61);

		// 신용카드 라벨
		Label cardLb = new Label("신용카드");
		add(cardLb);
		cardLb.setFont(f1);
		cardLb.setForeground(Color.WHITE);
		cardLb.setBackground(cardLbColor);
		cardLb.setBounds(10, 40, 90, 30);

		// 이용약관 라벨
		Label agmTitle = new Label("이용약관");
		agmTitle.setFont(f2);
		add(agmTitle);
		agmTitle.setBackground(Color.white);
		agmTitle.setBounds(120, 20, 70, 20);

		// 전체동의 체크박스
		Checkbox allChkCb = new Checkbox("전체동의");
		allChkCb.setFont(f3);
		add(allChkCb);
		allChkCb.setBackground(Color.white);
		allChkCb.setBounds(530, 20, 60, 20);

		// 3개 약관 라벨
		Label agmLb[];
		agmLb = new Label[3];
		agmLb[0] = new Label("전자금융거래 이용약관");
		agmLb[0].setBounds(120, 60, 140, 20);
		agmLb[1] = new Label("개인정보 수집 및 이용안내");
		agmLb[1].setBounds(120, 80, 140, 20);
		agmLb[2] = new Label("개인정보 제공 및 위탁안내");
		agmLb[2].setBounds(400, 60, 140, 20);

		for (int i = 0; i < agmLb.length; i++) {
			agmLb[i].setFont(f3);
			agmLb[i].setBackground(Color.WHITE);

		}

		// 3개의 약관 체크박스 배열
		Checkbox agmCb[] = new Checkbox[3];

		for (int i = 0; i < agmCb.length; i++) {
			agmCb[i] = new Checkbox("동의"); // 체크박스 배열 객체 생성
			agmCb[i].setFont(f3);
			agmCb[i].setBackground(Color.WHITE);

			// 아이템 리스너
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

		agmCb[0].setBounds(270, 58, 40, 20);
		agmCb[1].setBounds(270, 78, 40, 20);
		agmCb[2].setBounds(550, 58, 40, 20);

		// 3개의 약관 라벨과 3개의 약관 체크박스 순차적 추가
		for (int i = 0; i < agmLb.length; i++) {
			add(agmLb[i]);
			add(agmCb[i]);
		}

		setLocationByPlatform(true);

		invalidate();
		revalidate();
		repaint();
	}

}
