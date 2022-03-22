package javaproject;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb; 
	private JButton addBtn, uptBtn, delBtn, homeBtn;
	private DefaultTableModel model;
	private Vector title, data, result; 
	private JTable table;
	private JScrollPane sp;
	
	//메인
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ReservationAdmin();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// 생성자
	public ReservationAdmin() {
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
		
		jf.setTitle("관리자페이지");
		p1 = new JPanel();
		model = new DefaultTableModel();
		table = new JTable(model);
		sp = new JScrollPane(table);
		
		//파넬 설정
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(p1);
		p1.setLayout(null);
		p1.setBackground(Color.white);
		

		data = new Vector<>();
		
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
		
		sp.setBounds(200, 200, 800, 400);
		p1.add(sp);
		

		
		//기능

//		table.addMouseListener(new MouseAdapter() {
//		
//		public void mouseClicked(MouseEvent e) {
//		int index = table.getSelectedRow();	
//		
//		Vector in = (Vector) data.get(index);
//		
//		String id = (String)in.get(0);
//		String r_room = (String)in.get(1);
//		String startdate = (String)in.get(2);
//		String enddate = (String)in.get(3);
//		String headcount = (String)in.get(4);
//		String r_status = (String)in.get(5);
//		String p_cost = (String)in.get(6);
//		String res_no = (String)in.get(7);
//		
//		idTf.setText(id);
//		r_roomTf.setText(r_room);
//		
//		}
//			
//		});
		

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
		titleLb = new JLabel("전체 예약 조회");
		titleLb.setFont(f1);
		titleLb.setBounds(450, 70, 300, 100);
		p1.add(titleLb);

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
		delBtn.setBounds(520, 620, 100, 30);
		delBtn.setFont(f2);
		delBtn.setForeground(Color.black);
		delBtn.setBackground(Color.white);
		p1.add(delBtn);		

		/*기능 구현*/
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			} }
		);
		

		// 새로고침
		jf.validate();
	}


}


		
	
