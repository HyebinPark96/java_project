/*-- 관리자페이지 예약관리 | 마지막 수정날짜: 2022-03-25 | 마지막 수정인: 김서하--*/

package javaproject;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin2 {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, res_noLb, idLb, r_roomLb, sdLb, edLb, hcLb, r_statLb, p_costLb, res_noLb2 ; 
	private JButton addBtn, uptBtn, delBtn, homeBtn;
	private JTextField res_noTf, idTf, r_roomTf, sdTf, edTf, hcTf, r_statTf, p_costTf, res_noTf2;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result; 
	private JTable table;
	private JScrollPane sp;
	private String room[] = {"101호", "102호", "201호", "202호"};
	private JComboBox<String> roomCombo; // 룸 콤보박스
	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationAdmin2 reservationAdmin = new ReservationAdmin2();
					reservationAdmin.jf.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자 (매개변수)
	@SuppressWarnings({ "serial", "unchecked" })
	public ReservationAdmin2(String userId) {
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
		
		jf.setTitle("관리자페이지("+userId+")");
		p1 = new JPanel();
		
		model = new DefaultTableModel() {
			// 테이블 셀 수정 불가 (디폴트: 수정가능)
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		//파넬 설정
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);
		
		//예약데이터 테이블
		result = mgr.selectAll();
		title = new Vector<>();
		title.add("id");
		title.add("객실");
		title.add("체크인");
		title.add("체크아웃");
		title.add("예약인원");
		title.add("결제 상태");
		title.add("결제 금액");
		title.add("예약번호");
		model.setDataVector(result, title);
		
		//테이블 위치
		sp.setBounds(450, 200, 550, 400);
		p1.add(sp);
		
		//홈버튼
		homeBtn = new JButton("홈으로");
		homeBtn.setBounds(20, 660, 100, 30);
		homeBtn.setFont(f2);
		homeBtn.setForeground(Color.white);
		homeBtn.setBackground(Color.gray);
		p1.add(homeBtn);
		
		//타이틀라벨
		titleLb = new JLabel("전체 예약 조회");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

		//아이디 라벨
		idLb = new JLabel("아이디");
		p1.add(idLb);
		idLb.setFont(f3);
		idLb.setBounds(150, 200, 40, 20);
		
		//아이디 텍스트필드
		idTf = new JTextField();
		p1.add(idTf);
		idTf.setBounds(220, 200, 150, 20);
		idTf.setEditable(false);
		
		//객실 라벨
		r_roomLb = new JLabel("객실");
		p1.add(r_roomLb);
		r_roomLb.setFont(f3);
		r_roomLb.setBounds(150, 230, 40, 20);
		
		//객실 텍스트필드
//		r_roomTf = new JTextField();
//		p1.add(r_roomTf);
//		r_roomTf.setBounds(220, 230, 150, 20);
		
		roomCombo = new JComboBox<String>(room);
		p1.add(roomCombo);
		roomCombo.setBounds(220,230,150,20);
		roomCombo.setBackground(Color.WHITE);
		
		
		//체크인 라벨
		sdLb = new JLabel("체크인");
		p1.add(sdLb);
		sdLb.setFont(f3);
		sdLb.setBounds(150,260,40,20);
		//체크인 텍스트필드
		sdTf = new JTextField();
		p1.add(sdTf);
		sdTf.setBounds(220, 260, 150, 20);
		
		//체크아웃 라벨
		edLb = new JLabel("체크아웃");
		p1.add(edLb);
		edLb.setFont(f3);
		edLb.setBounds(150, 290, 50, 20);
		//체크아웃 텍스트필드
		edTf = new JTextField();
		p1.add(edTf);
		edTf.setBounds(220, 290, 150, 20);
		
		//예약인원 라벨
		hcLb = new JLabel("에약인원");
		p1.add(hcLb);
		hcLb.setFont(f3);
		hcLb.setBounds(150, 320, 50, 20);
		//예약인원 텍스트필드
		hcTf = new JTextField();
		p1.add(hcTf);
		hcTf.setBounds(220, 320, 150, 20);
		
		//결제 상태 라벨
		r_statLb = new JLabel("결제상태");
		p1.add(r_statLb);
		r_statLb.setFont(f3);
		r_statLb.setBounds(150, 350, 50, 20);
		//결제상태 텍스트필드
		r_statTf = new JTextField();
		p1.add(r_statTf);
		r_statTf.setBounds(220, 350, 150, 20);
		r_statTf.setEditable(false);
		
		//결제금액 라벨
		p_costLb = new JLabel("결제금액");
		p1.add(p_costLb);
		p_costLb.setFont(f3);
		p_costLb.setBounds(150, 380, 50, 20);
		
		//결제금액 텍스트필드
		p_costTf = new JTextField();
		p1.add(p_costTf);
		p_costTf.setBounds(220, 380, 150, 20);
		p_costTf.setEditable(false);
		
		//예약번호 라벨
		res_noLb2 = new JLabel("예약번호");
		p1.add(res_noLb2);
		res_noLb2.setFont(f3);
		res_noLb2.setBounds(150, 410, 50, 20);
		//예약번호 텍스트필드
		res_noTf2 = new JTextField();
		p1.add(res_noTf2);
		res_noTf2.setBounds(220, 410, 150, 20);
		res_noTf2.setEditable(false);
		
		
		
		//예약 추가 버튼
		addBtn = new JButton("예약 추가");
		addBtn.setBounds(200, 620, 100, 30);
		addBtn.setFont(f2);
		addBtn.setForeground(Color.black);
		addBtn.setBackground(Color.white);
		p1.add(addBtn);
		
		//예약 수정 버튼
		uptBtn = new JButton("예약 수정");
		uptBtn.setBounds(360, 620, 100, 30);
		uptBtn.setFont(f2);
		uptBtn.setForeground(Color.black);
		uptBtn.setBackground(Color.white);
		p1.add(uptBtn);
		
		//예약 삭제 버튼
		delBtn = new JButton("예약 삭제");
		delBtn.setBounds(900, 620, 100, 30);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.black);
		delBtn.setBackground(Color.white);
		p1.add(delBtn);		
		
		//예약번호 라벨
		res_noLb = new JLabel("예약번호 선택");
		res_noLb.setBounds(650, 620, 100, 30);
		res_noLb.setFont(f3);
		p1.add(res_noLb);
		
		//예약번호 텍스트필드
		res_noTf = new JTextField();
		res_noTf.setBounds(740,620,150,30);
		res_noTf.setColumns(10);
		res_noTf.setEditable(false);
		p1.add(res_noTf);
		
		
		
		
		/*기능 구현*/
		
		//테이블 클릭시 선택cell값 -> 텍스트필드로 끌어오기
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow(); // 선택 레코드(행) 가져오기
				
				String id = (String)table.getValueAt(row, 0); // 선택 행의 0번째 컬럼 값 가져오기
				String roomNum =(String) table.getValueAt(row, 1); // 예약 룸번호
				String sd = (String) table.getValueAt(row, 2); // 레코드의 시작일
				String ed = (String) table.getValueAt(row, 3); // 레코드의 종료일
				String hc = (String) table.getValueAt(row, 4); // 예약 인원
				String res_no = (String)table.getValueAt(row, 7);
				
				idTf.setText(id);
				
				// 기존 예약된 룸을 콤보박스의 기본값으로 셋팅해두기
				if(roomNum.equals("101")) {
					roomCombo.setSelectedIndex(0);
				} else if(roomNum.equals("102")) {
					roomCombo.setSelectedIndex(1);
				} else if(roomNum.equals("201")) {
					roomCombo.setSelectedIndex(2);
				} else if(roomNum.equals("202")) {
					roomCombo.setSelectedIndex(3);
				}
				
				sdTf.setText(sd);
				edTf.setText(ed);
				
				hcTf.setText(hc);
				
				res_noTf.setText(res_no);
				
				System.out.println("(ResAdmin)선택된 예약번호:" + res_no);
				
				
			}
			
		
		}); 
		
		//예약삭제: 위에서 텍필로 끌어온 값 -> AdminMgr로 써서 삭제
		delBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String res_no = res_noTf.getText();
				mgr.delete(res_no);
				
				//삭제 후 디비값 불러오기
				@SuppressWarnings("rawtypes")
				Vector result = mgr.selectAll();
				model.setDataVector(result, title);
				
			} 
		});
		
		//홈버튼 클릭 액션: 메인으로 이동
		homeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainPage();
				jf.dispose();
				
			}
		});
		
		// 새로고침
		jf.validate();
	}
	
	// 생성자
	public ReservationAdmin2() {
		this(null);
	}
}
