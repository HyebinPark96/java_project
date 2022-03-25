/*-- 로그인매니저 | 마지막 수정날짜: 2022-03-23 | 마지막 수정인: 김서하--*/
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


// DB연동의 기능의 클래스 
public class LoginMgr {
	
	private String logSucId;
	private int mode;
	
	
	// 매번 정보 가져오는 과부하 안걸리게 pool에 만든 인스턴스를 담기
	private DBConnectionMgr pool;
	
	public LoginMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	
	// SELECT : ID,비번 매개변수로 받아서 DB와 일치하는지 학인 후 로그인
	public String loginChk(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		boolean flag = false; // flag의 불리언값에 따라 결과 나뉨
		
		try {
			con = pool.getConnection();
			
			//쿼리문
			sql = "SELECT id FROM user WHERE id = ? AND pwd = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id); // 1 : 첫번째 물음표
			pstmt.setString(2, pwd); // 2 : 첫번째 물음표
			// executeQuery : select 실행문
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				flag = true;  // 값이 있다면 true 반환
				logSucId = rs.getString(1); // 가져오는 첫번째 열 (id)를 logSucId의 값으로 대입
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return logSucId;
	}
	
	// SELECT : ID 받아와서 관리자 여부 확인
	public int modeChk(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = pool.getConnection();
			
			//쿼리문
			sql = "SELECT mode FROM user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id); // 1 : 첫번째 물음표
			// executeQuery : select 실행문
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mode = rs.getInt(1); // 가져오는 첫번째 열 (모드값)를 mode의 값으로 대입
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mode;
	}	
	
	// SELECT : 회원가입시 아이디 중복확인 
	public boolean idChk(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		boolean flagForIdDup = false; // flagForIdDup의 불리언값에 따라 결과 나뉨
		
		try {
			con = pool.getConnection();
			//쿼리문
			sql = "select id from user where id = ? ";
			// 아래형식으로 가독성 높이기도 가능
//			sql += "SELECT id ";
//			sql += "FROM   user ";
//			sql += "WHERE id = ? "; //1
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // 1 : 첫번째 물음표
			
			// executeQuery : select 실행문
			rs = pstmt.executeQuery(); 

			if(rs.next())
				flagForIdDup = true; // 값이 있다면 true 반환
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForIdDup; 
	} 
	

	
	
	
	// INSERT : 회원가입완료 -> DB에 입력
	public boolean userSign(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForUserSign = false; 
		
		try {
			con = pool.getConnection();
			//쿼리문
			sql = "INSERT user (id, pwd, name, email, phone, birthday, gender, mode)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";//values (데이터값들)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());//1은 첫번째 ?를 의미
			pstmt.setString(2, bean.getPwd());//2은 두번째 ?를 의미
			pstmt.setString(3, bean.getName());//3은 세번째 ?를 의미
			pstmt.setString(4, bean.getEmail());//4은 네번째 ?를 의미
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getBirthday());
			pstmt.setString(7, bean.getGender());
			pstmt.setInt(8, bean.getMode());
			
			// executeUpdate : insert, update, delete 실행문
			int cnt = pstmt.executeUpdate(); 
			// 적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 (1행씩 넣을거니까) 
			if(cnt==1) 
				flagForUserSign = true; // 값이 있다면 true 반환
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForUserSign;
	} //---회원가입
	
	// SELECT: UpdateUser_회원정보조회
	public UserBean userInfo(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		UserBean bean = new UserBean();

		try {
			con = pool.getConnection();
			sql = "select name, email, phone, birthday, gender from user where id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setName(rs.getString("name"));
				bean.setEmail(rs.getString("email"));
				bean.setPhone(rs.getString("phone"));
				bean.setBirthday(rs.getString("birthday"));
				bean.setGender(rs.getString("gender"));
//				System.out.println("[loginMgr_userInfo]: userid:(" + id + ")정보를 bean에 담았습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);			
		}
		return bean;
	}
	

	// UPDATE: UpdateUser_내정보 수정 (회원)
	public boolean userUpdt(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForUserUpdt = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE user SET pwd =?, email =?, phone =? "
					+ "WHERE id = ?";//values (데이터값들)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPwd());//1은 첫번째 ?를 의미
			pstmt.setString(2, bean.getEmail());//2은 두번째 ?를 의미
			pstmt.setString(3, bean.getPhone());//3은 세번째 ?를 의미
			pstmt.setString(4, bean.getId());//4은 네번째 ?를 의미
			//적용된 레코드 개수 : 에러 및 처리 : 0, 정상적인 처리 : 1 
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flagForUserUpdt = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForUserUpdt;
	} //--내정보수정	
	
	// SELECT: ReservationUser_회원예약조회
		public ReservationBean resInfo(String id) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			ReservationBean bean = new ReservationBean();

			try {
				con = pool.getConnection();
				sql = "select res_no, r_room, startdate, enddate, r_status, p_cost from reservation where id = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					bean.setRes_no(rs.getString("res_no"));
					bean.setR_room(rs.getString("r_room"));
					bean.setStartdate(rs.getString("startdate"));
					bean.setEnddate(rs.getString("enddate"));
					bean.setR_status(rs.getString("r_status"));
					bean.setP_cost(rs.getInt("p_cost"));
//					System.out.println("[loginMgr_resInfo]: userid:(" + id + ")예약정보를 bean에 담았습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);			
			}
			return bean;
		}
	
	
}
