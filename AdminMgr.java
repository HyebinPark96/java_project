/* ������������ ������� �Ŵ��� | ������ ������¥: 2022-03-25 | ������ �ۼ���: �輭�� */
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JOptionPane;

// DB������ ����� Ŭ���� 
public class AdminMgr {
	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;

	public AdminMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	
	//DB ��ü���೻�� ��ȸ
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector selectAll() { 

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector data = new Vector<>();

		data.clear();

		try {
			con = pool.getConnection();

			// ������
			sql = "select * from reservation";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����

				String res_no = rs.getString(1);
				String id = rs.getString(2);
				String r_room = rs.getString(3);
				String startdate = rs.getString(4);
				String enddate = rs.getString(5);
				String headcount = rs.getString(6);
				String r_status = rs.getString(7);
				String p_cost = rs.getString(8);


				// ���Ϳ� ������ �� �߰�

				in.add(res_no);
				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
						
				// ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�
				data.add(in);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return data;
	}


	//�������: ReservationAdmin �ؽ�Ʈ�ʵ忡�� �����ȣ�� ����ͼ� ���� ����
	public void delete(String res_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		@SuppressWarnings("rawtypes")
		Vector data = new Vector<>();

		data.clear();
		
		try {
			con = pool.getConnection();

			// ������
			sql = "delete from reservation where res_no = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, res_no);
			//pstmt.executeUpdate();
			
			int cnt = pstmt.executeUpdate();
			if(cnt==1) 
				JOptionPane.showMessageDialog(null, "������� �Ϸ�.");
			else 
				JOptionPane.showMessageDialog(null, "������� ����."); 

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
	}

}
