// mgr : Manager
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

// DB������ ����� Ŭ���� 
public class AdminMgr {
	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;

	public AdminMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	public Vector selectAll() {

		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector data = new Vector<>();

		data.clear();


		try {
			con = pool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, r_room from reservation");

			// ������
			sql = "select * from reservation";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				Vector in = new Vector<String>(); // 1���� ���ڵ� �����ϴ� ���� ����
				String id = rs.getString(1);
				String r_room = rs.getString(2);
				String startdate = rs.getString(3);
				String enddate = rs.getString(4);
				String headcount = rs.getString(5);
				String r_status = rs.getString(6);
				String p_cost = rs.getString(7);
				String res_no = rs.getString(8);

				// ���Ϳ� ������ �� �߰�
				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
				in.add(res_no);
				
				
				// ��ü �����͸� �����ϴ� ���Ϳ� in(1���� ������ ����) ���� �߰�
				data.add(in);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private void insert(String id, String r_room) {

		PreparedStatement pstmtAdd = null;
		Connection con = null;

		try {
			pstmtAdd = con.prepareStatement("insert into reservation values(?,?)");
//			insert into member values(? -> 1 ,? -> 2, ? -> 3)" ������ ? �� �� ����
			pstmtAdd.setString(1, id);
			pstmtAdd.setString(2, r_room);

//			���Թ��� ������ ���� -> �Է� (insert)
			pstmtAdd.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
