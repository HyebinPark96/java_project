package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class ReservationMgr {
	
	int r_capacity;
	int p_cost;

	// 매번 정보 가져오는 과부하 안걸리게 pool에 만든 인스턴스를 담기
	private DBConnectionMgr pool;

	// 생성자
	public ReservationMgr() {
		// 인스턴스화 시켜서 가져오기
		pool = DBConnectionMgr.getInstance();
	}

//	// INSERT : Bean으로 날짜 넣기
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
//			// executeUpdate : insert, update, delete 실행문
//			result = pstmt.executeUpdate();
//
//			pstmt.close();
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//		return result;
//	} // --- Bean으로 날짜 넣기

	
	
	// (테스트)INSERT : 날짜 넣기
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

			// executeUpdate : insert, update, delete 실행문
			int aaa = pstmt.executeUpdate();
			
			// 적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 (1행씩 넣을거니까)
			if (aaa == 1)
				flagForInsertDate = true; // 값이 있다면 true 반환
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForInsertDate;
	} // --- (테스트) 날짜 넣기

	

	// SELECT : 중복날짜 체크
	// If) 3/1 ~ 3/6 예약
	// 1행 startdate : 20220301, enddate : startdate+1
	// 2행 ... 
	public boolean dateChk(int room, java.sql.Date startDate, java.sql.Date endDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		boolean flagForDateDup = false;

		try {
			con = pool.getConnection();

			// 쿼리문
			sql = "SELECT r_room, startdate, enddate " 
			+ "FROM reservation " 
			+ "WHERE r_room = ? AND startdate = ? AND enddate = DATE_ADD(?, INTERVAL 1 DAY)";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, room); // 1은 첫번째 ?를 의미
			pstmt.setDate(2, startDate); // 2은 두번째 ?를 의미
			pstmt.setDate(3, endDate); // 3은 세번째 ?를 의미

			// executeQuery : select 실행문
			rs = pstmt.executeQuery();

			if (rs.next())
				flagForDateDup = true; // 값이 있다면 true 반환 -> 중복된 일정이 있다.
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForDateDup;
	}

	
	
	// (테스트)DELETE : 날짜 빼기
		public boolean deleteDate(int room, java.sql.Date startDate, java.sql.Date endDate) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql;
			boolean flagForDeleteDate = false;

			try {
				con = pool.getConnection();
				
				// 쿼리문
				sql = "DELETE FROM reservation " 
				+ "WHERE r_room = ? AND startdate = ? AND enddate = DATE_ADD(?, INTERVAL 1 DAY)"; // 대문자써서 안됐고, room이라 해서 안돼씀
				
				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room);
				pstmt.setDate(2, startDate);
				pstmt.setDate(3, endDate);

				// executeUpdate : insert, update, delete 실행문
				int bbb = pstmt.executeUpdate();
				
				// 적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 (1행씩 넣을거니까)
				if (bbb == 1)
					flagForDeleteDate = true; // 중복된 값이 있어야 삭제가능하다는 뜻이며, true 반환
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flagForDeleteDate;
		} // --- (테스트) 날짜 넣기
	

		// 룸별 최대수용인원과 예약인원 체크
		public boolean capacityAndCostChk(int room) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;
			boolean flagForCapaChk = false;

			try {
				con = pool.getConnection();

				// 쿼리문
				sql = "SELECT p_cost, r_capacity " 
				+ "FROM room " 
				+ "WHERE r_room = ?";

				pstmt = con.prepareStatement(sql);

				pstmt.setInt(1, room); // 1은 첫번째 ?를 의미

				// executeQuery : select 실행문
				rs = pstmt.executeQuery();

				if (rs.next())
					flagForCapaChk = true; // 값이 있다면 true 반환 -> 중복된 일정이 있다.
					p_cost = rs.getInt(1); // 룸별 1박당 가격 들고와서 int형 p_cost에 넣기
					r_capacity = rs.getInt(2); // 룸별 최대수용인원 들고와서 int형 r_capacity에 넣기
					
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return flagForCapaChk;
		}

			
		
		
		
}
