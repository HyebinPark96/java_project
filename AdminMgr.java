// mgr : Manager
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

// DB연동의 기능의 클래스 
public class AdminMgr {
	// 매번 정보 가져오는 과부하 안걸리게 pool에 만든 인스턴스를 담기
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

			// 쿼리문
			sql = "select * from reservation";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				Vector in = new Vector<String>(); // 1개의 레코드 저장하는 벡터 생성
				String id = rs.getString(1);
				String r_room = rs.getString(2);
				String startdate = rs.getString(3);
				String enddate = rs.getString(4);
				String headcount = rs.getString(5);
				String r_status = rs.getString(6);
				String p_cost = rs.getString(7);
				String res_no = rs.getString(8);

				// 벡터에 각각의 값 추가
				in.add(id);
				in.add(r_room);
				in.add(startdate);
				in.add(enddate);
				in.add(headcount);
				in.add(r_status);
				in.add(p_cost);
				in.add(res_no);
				
				
				// 전체 데이터를 저장하는 벡터에 in(1명의 데이터 저장) 벡터 추가
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
//			insert into member values(? -> 1 ,? -> 2, ? -> 3)" 각각의 ? 에 값 대입
			pstmtAdd.setString(1, id);
			pstmtAdd.setString(2, r_room);

//			대입받은 쿼리를 실행 -> 입력 (insert)
			pstmtAdd.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
