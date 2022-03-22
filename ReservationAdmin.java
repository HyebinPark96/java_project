/*-- 관리자페이지 예약관리 | 마지막 수정날짜: 2022-03-22 | 마지막 수정인: 김서하--*/

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
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ReservationAdmin {
	AdminMgr mgr = new AdminMgr();
	JFrame jf = new JFrame();
	
	private JPanel p1;
	private JLabel titleLb, res_noLb; 
	private JButton addBtn, uptBtn, delBtn, homeBtn;
	private JTextField res_noTf;
	private DefaultTableModel model;
	@SuppressWarnings("rawtypes")
	private Vector title, result; 
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
	@SuppressWarnings({ "serial", "unchecked" })
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
		
		sp.setBounds(200, 200, 800, 400);
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
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();

				String res_no = (String)table.getValueAt(row, column);
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

}
