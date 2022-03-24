package javaproject;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CardPaymentFrame extends JFrame {

	private Image bkImg = new ImageIcon("javaproject/images/background.png").getImage();
	PaymentMgr mgr = new PaymentMgr();
	String mm[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String yy[] = { "22", "23", "24", "25", "26" };
	private JPanel p1;

	// 그리기 함수
	public void paint(Graphics g) {

		// 배경이미지
		g.drawImage(bkImg, 0, 10, null);

		// 구분선
		Color lineColor = new Color(169, 169, 169);
		g.setColor(lineColor);
		g.drawLine(130, 80, 590, 80);

		// "월"콤보박스
		JComboBox<String> mmCom = new JComboBox<String>(mm);
		add(mmCom);
		mmCom.setEditable(true);
		mmCom.setBounds(230, 95, 50, 20);
		mmCom.setBackground(Color.white);

		mmCom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				JComboBox cb = (JComboBox) e.getSource();

				System.out.println(mmCom.getSelectedItem());
				
				cb.setSelectedItem(mmCom.getSelectedItem());
				
				

//				// setIcon() : 아이콘 설정 메소드
//				label.setIcon(images[idx]);

			}
		});

//		TextField mmTf = new TextField();
//		add(mmTf);
//		mmTf.setBounds(230, 95, 50, 20);
//		
//		TextField yyTf = new TextField();
//		add(yyTf);
//		yyTf.setBounds(320, 95, 50, 20);

		// "년"콤보박스
		JComboBox<String> yyCom = new JComboBox<String>(yy);
		add(yyCom);
		yyCom.setEditable(true);
		yyCom.setBounds(320, 95, 50, 20);
		yyCom.setBackground(Color.white);
		yyCom.setOpaque(true);
		yyCom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				

				JComboBox cb = (JComboBox) e.getSource();

				System.out.println(yyCom.getSelectedItem());
				
				cb.setSelectedItem(yyCom.getSelectedItem());

			}
		});

		// 카드구분 radio
		JRadioButton pcRBtn = new JRadioButton("개인카드", true);
		JRadioButton ccRBtn = new JRadioButton("법인카드");
		ButtonGroup group = new ButtonGroup();
		add(pcRBtn);
		add(ccRBtn);
		group.add(pcRBtn);
		group.add(ccRBtn);
		pcRBtn.setBackground(Color.white);
		pcRBtn.setBounds(230, 120, 80, 15);
		ccRBtn.setBackground(Color.white);
		ccRBtn.setBounds(320, 120, 80, 15);

		// 카드비밀번호 텍스트필드
		JTextField c_pwdTf = new JTextField();
		add(c_pwdTf);
		c_pwdTf.setBounds(230, 145, 30, 20);

//		// 카드비밀번호 라벨2
		JLabel c_pwdLb2 = new JLabel("X X (앞 두자리)");
		add(c_pwdLb2);
		c_pwdLb2.setBounds(260, 145, 100, 20);
//		c_pwdLb2.setFont(f3);

		// 주민등록번호 텍스트필드
		JTextField idnumTf1 = new JTextField();
		JTextField idnumTf2 = new JTextField();
		add(idnumTf1);
		add(idnumTf2);
		idnumTf1.setBounds(230, 170, 70, 20);
		idnumTf2.setBounds(330, 170, 70, 20);

	}

	// 생성자
	public CardPaymentFrame() {
		this(null);
	}

	// 생성자 (매개변수)
	public CardPaymentFrame(String userId) {

		// 프레임설정
		setTitle(userId + "님 카드결제창");
		setSize(800, 600);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setLayout(null);// 레이아웃을 내맘대로 설정가능하게 해줌.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// JFrame이 정상적으로 종료되게

		// 폰트
		Font f1 = new Font("맑은 고딕", Font.BOLD, 20); // 타이틀 폰트
		Font f2 = new Font("맑은 고딕", Font.BOLD, 10); // 버튼 폰트
		Font f3 = new Font("맑은 고딕", Font.PLAIN, 10); // 텍스트 폰트
		Font f4 = new Font("맑은 고딕", Font.PLAIN, 10);

		// 컬러
		Color c = new Color(226, 56, 61);

		// 타이틀 라벨
		Label title = new Label("결제하기");
		title.setFont(f1);
		title.setForeground(Color.gray);
		title.setBackground(Color.white);
		title.setBounds(120, 10, 100, 30);
		add(title);

		// 카드번호 라벨
		Label c_numLb = new Label("카드번호");
		c_numLb.setBounds(120, 70, 100, 20);
		c_numLb.setFont(f4);
		add(c_numLb);

		// 카드번호 텍스트필드
		TextField c_numTf[] = new TextField[4]; // 카드번호 4자리
		for (int i = 0; i < 4; i++) {
			c_numTf[i] = new TextField();
			add(c_numTf[i]);
		}
		c_numTf[0].setBounds(230, 70, 40, 20);
		c_numTf[1].setBounds(280, 70, 40, 20);
		c_numTf[2].setBounds(330, 70, 40, 20);
		c_numTf[3].setBounds(380, 70, 40, 20);

		// 유효기간 라벨
		Label c_yymm = new Label("유효기간");
		c_yymm.setBounds(120, 95, 100, 20);
		c_yymm.setFont(f4);
		add(c_yymm);

		// "월" 라벨
		Label mm = new Label("월");
		mm.setBounds(280, 95, 20, 20);
		mm.setFont(f4);
		mm.setBackground(Color.white);
		add(mm);

		// "년" 라벨
		Label yy = new Label("년");
		yy.setBounds(370, 95, 20, 20);
		yy.setFont(f4);
		yy.setBackground(Color.white);
		add(yy);

		// 카드구분 라벨
		Label c_classLb = new Label("카드구분");
		c_classLb.setBounds(120, 120, 100, 20);
		c_classLb.setFont(f4);
		add(c_classLb);

		// 카드비밀번호 라벨
		Label c_pwdLb = new Label("카드 비밀번호");
		c_pwdLb.setBounds(120, 145, 100, 20);
		c_pwdLb.setFont(f4);
		add(c_pwdLb);

		// 주민등록번호 라벨
		Label idnumLb = new Label("주민등록번호");
		idnumLb.setBounds(120, 170, 100, 20);
		idnumLb.setFont(f4);
		add(idnumLb);

		// 결제버튼
		Button p_btn = new Button("결제");

//		p_btn.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int check=0; // 텍스트필드 모두 입력되었는지 확인용
//				
//				for (int j = 0; j < c_numTf.length; j++) {
//					if (c_numTf[j].getText().trim().length() > 0) {
//						if(c_numTf[j].getText().trim().length() == 4) {
//							check++;
//							System.out.println(check);
//						} else {
//							JOptionPane.showMessageDialog(null,"카드번호 " + (j+1) + "번째 칸 ERROR : " + "4자리로 채워주세요. (" + c_numTf[j].getText() + ")");
//							System.out.println("카드번호 " + (j+1) + "번째 칸 ERROR : " + "4자리로 채워주세요. (" + c_numTf[j].getText() + ")");
//						}
//					}
//				}
//				if (c_pwdTf.getText().trim().length() > 0) {
//					if(c_pwdTf.getText().trim().length() == 4) {
//						check++;
//						System.out.println(check);
//					} else {
//						JOptionPane.showMessageDialog(null,"총 4자리의 카드 비밀번호를 입력하세요. (" + c_pwdTf.getText() +")" );
//						System.out.println("총 4자리의 카드 비밀번호를 입력하세요. (" + c_pwdTf.getText() +")" );
//					}	
//				}

//				if (c_cvcTf.getText().trim().length() > 0) {
//					if(c_cvcTf.getText().trim().length() == 3) {
//						check++;
//						System.out.println(check);
//					} else {
//						JOptionPane.showMessageDialog(null,"총 3자리의 CVC 번호를 입력하세요. (" + c_cvcTf.getText() + ")");
//						System.out.println("총 3자리의 CVC 번호를 입력하세요. (" + c_cvcTf.getText() + ")");
//					}
//					
//				}
//				if (c_mmTf.getText().trim().length() > 0 && c_yyTf.getText().trim().length() > 0) {
//					if(c_mmTf.getText().trim().length() == 2 && c_yyTf.getText().trim().length() == 2) {
//						check++;
//						System.out.println(check);
//					} else {
//						JOptionPane.showMessageDialog(null, "카드 유효기간 년월을 2자리로 입력하세요. Ex. MMYY : 0926");
//						System.out.println("카드 유효기간 년월을 2자리로 입력하세요. Ex. MMYY : 0926");
//					}
//					
//				}

//				if (check == 7) {
//					JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "알림", JOptionPane.PLAIN_MESSAGE);
//					mgr.updateStatus(userId); // update문 돌려서 해당 id의 예약내역의 결제상태를 결제전 -> 결제완료로 변경
//					dispose();		
//				} else {
//					// tf 모두 입력되지 않았다면 0으로 초기화해서 다시 계산
//					check = 0;
//				}
//				
//			}
//		});
//		p_btn.setBounds(270, 350, 80, 20);
//		p_btn.setBackground(Color.BLACK);
//		p_btn.setForeground(Color.WHITE);
//		frame.add(p_btn);
//
//		frame.setSize(600, 500);

//
//		frame.validate();

	}

	public static void main(String[] args) {// 실행
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardPaymentFrame CardPaymentFrame = new CardPaymentFrame();
					CardPaymentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
