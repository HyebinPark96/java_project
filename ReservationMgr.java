package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ReservationMgr {
	
	int r_capacity;
	int p_cost;

	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;

	// ������
	public ReservationMgr() {
		// �ν��Ͻ�ȭ ���Ѽ� ��������
		pool = DBConnectionMgr.getInstance();
	}

//	// INSERT : Bean���� ��¥ �ֱ�
//	public int insertDate(ReservationBean bean) {
//
//		int result = 0;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		String sql;
//
//		try {
//			con = pool.getConnection();
//			sql = "INSERT reservation (id, r_room, startdate, enddate, r_capacity, r_status, p_cost, res_no) "
//					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//			pstmt = con.prepareStatement(sql);
//
//			pstmt.setString(1, bean.getId());
//			pstmt.setInt(2, bean.getR_room());
//			pstmt.setInt(3, bean.getStartdate());
//			pstmt.setInt(4, bean.getEnddate());
//			pstmt.setInt(5, bean.getR_capacity());
//			pstmt.setString(6, bean.getR_status());
//			pstmt.setInt(7, bean.getP_cost());
//			pstmt.setInt(8, bean.getRes_no());
//
//			// executeUpdate : insert, update, delete ���๮
//			result = pstmt.executeUpdate();
//
//			pstmt.close();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return result;
//	} // --- Bean���� ��¥ �ֱ�

	
	
	// (�׽�Ʈ)INSERT : ��¥ �ֱ�
	public boolean testInsertDate(int room, java.sql.Date startDate, java.sql.Date endDate, int headcount, String r_status, int p_cost) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql;
		boolean flagForInsertDate = false;

		try {
			con = pool.getConnection();
			sql = "INSERT reservation (r_room, startdate, enddate, headcount, r_status, p_cost ) " 
			+ "VALUES (?, ?, DATE_ADD(?, INTERVAL 1 DAY), ?, ?, ?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, room);
			pstmt.setDate(2, startDate);
			pstmt.setDate(3, endDate);
			pstmt.setInt(4, headcount);
			pstmt.setString(5, r_status);
			pstmt.setInt(6, p_cost);

			// executeUpdate : insert, update, delete ���๮
			int aaa = pstmt.executeUpdate();
			
			// ����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 (1�྿ �����Ŵϱ�)
			if (aaa == 1)
				flagForInsertDate = true; // ���� �ִٸ� true ��ȯ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForInsertDate;
	} // --- (�׽�Ʈ) ��¥ �ֱ�

	

	// SELECT : �ߺ���¥ üũ
	// If) 3/1 ~ 3/6 ����
	// 1�� startdate : 20220301, enddate : startdate+1
	// 2�� ... 
	public boolean dateChk(int room, java.sql.Date startDate, java.sql.Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		boolean flagForDateDup = false;

		try {
			con = pool.getConnection();

			// ������
			sql = "SELECT r_room, startdate, enddate " 
			+ "FROM reservation " 
			+ "WHERE r_room = ? AND startdate = ? AND enddate = DATE_ADD(?, INTERVAL 1 DAY)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, room); // 1�� ù��° ?�� �ǹ�
			pstmt.setDate(2, startDate); // 2�� �ι�° ?�� �ǹ�
			pstmt.setDate(3, endDate); // 3�� ����° ?�� �ǹ�

			// executeQuery : select ���๮
			rs = pstmt.executeQuery();

			if (rs.next())
				flagForDateDup = true; // ���� �ִٸ� true ��ȯ -> �ߺ��� ������ �ִ�.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForDateDup;
	}

	
	
	// (�׽�Ʈ)DELETE : ��¥ ����
		public boolean deleteDate(int room, java.sql.Date startDate, java.sql.Date endDate) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql;
			boolean flagForDeleteDate = false;

			try {
				con = pool.getConnection();
				
				// ������
				sql = "DELETE FROM reservation " 
				+ "WHERE r_room = ? AND startdate = ? AND enddate = DATE_ADD(?, INTERVAL 1 DAY)"; // �빮�ڽἭ �ȵư�, room�̶� �ؼ� �ȵž�
				
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room);
				pstmt.setDate(2, startDate);
				pstmt.setDate(3, endDate);

				// executeUpdate : insert, update, delete ���๮
				int bbb = pstmt.executeUpdate();
				
				// ����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 (1�྿ �����Ŵϱ�)
				if (bbb == 1)
					flagForDeleteDate = true; // �ߺ��� ���� �־�� ���������ϴٴ� ���̸�, true ��ȯ
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flagForDeleteDate;
		} // --- (�׽�Ʈ) ��¥ �ֱ�
	

		// �뺰 �ִ�����ο��� �����ο� üũ
		public boolean capacityAndCostChk(int room) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			boolean flagForCapaChk = false;

			try {
				con = pool.getConnection();

				// ������
				sql = "SELECT p_cost, r_capacity " 
				+ "FROM room " 
				+ "WHERE r_room = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room); // 1�� ù��° ?�� �ǹ�

				// executeQuery : select ���๮
				rs = pstmt.executeQuery();

				if (rs.next())
					flagForCapaChk = true; // ���� �ִٸ� true ��ȯ -> �ߺ��� ������ �ִ�.
					p_cost = rs.getInt(1); // �뺰 1�ڴ� ���� ���ͼ� int�� p_cost�� �ֱ�
					r_capacity = rs.getInt(2); // �뺰 �ִ�����ο� ���ͼ� int�� r_capacity�� �ֱ�
					
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return flagForCapaChk;
		}

			
		
		
		
}
