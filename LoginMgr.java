// mgr : Manager
package javaproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// DB������ ����� Ŭ���� 
public class LoginMgr {

	// �Ź� ���� �������� ������ �Ȱɸ��� pool�� ���� �ν��Ͻ��� ���
	private DBConnectionMgr pool;
	
	public LoginMgr() {
		pool = DBConnectionMgr.getInstance();
	}

	// SELECT : ID,��� �Ű������� �޾Ƽ� DB�� ��ġ�ϴ��� ���� �� �α���
	public boolean loginChk(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false; // flag�� �Ҹ��𰪿� ���� ��� ����
		
		try {
			con = pool.getConnection();
			
			//������
			sql = "select id from user where id = ? and pwd = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id); // 1 : ù��° ����ǥ
			pstmt.setString(2, pwd); // 2 : ù��° ����ǥ
			// executeQuery : select ���๮
			rs = pstmt.executeQuery();
			
			if(rs.next())
				flag = true;  // ���� �ִٸ� true ��ȯ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	// SELECT : ȸ�����Խ� ���̵� �ߺ�Ȯ�� 
	public boolean idChk(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flagForIdDup = false; // flagForIdDup�� �Ҹ��𰪿� ���� ��� ����
		
		try {
			con = pool.getConnection();
			//������
			sql = "select id from user where id = ? ";
			// �Ʒ��������� ������ ���̱⵵ ����
//			sql += "SELECT id ";
//			sql += "FROM   user ";
//			sql += "WHERE id = ? "; //1
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id); // 1 : ù��° ����ǥ
			
			// executeQuery : select ���๮
			rs = pstmt.executeQuery(); 
			
			if(rs.next())
				flagForIdDup = true; // ���� �ִٸ� true ��ȯ
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flagForIdDup; 
	} 
	
	
	// INSERT : ȸ�����ԿϷ� -> DB�� �Է�
	public boolean userSign(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForUserSign = false; 
		
		try {
			con = pool.getConnection();
			//������
			sql = "INSERT user (id, pwd, name, email, phone, birthday, gender, mode)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";//values (�����Ͱ���)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());//1�� ù��° ?�� �ǹ�
			pstmt.setString(2, bean.getPwd());//2�� �ι�° ?�� �ǹ�
			pstmt.setString(3, bean.getName());//3�� ����° ?�� �ǹ�
			pstmt.setString(4, bean.getEmail());//4�� �׹�° ?�� �ǹ�
			pstmt.setString(5, bean.getPhone());
			pstmt.setString(6, bean.getBirthday());
			pstmt.setString(7, bean.getGender());
			pstmt.setInt(8, bean.getMode());
			
			// executeUpdate : insert, update, delete ���๮
			int cnt = pstmt.executeUpdate(); 
			// ����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 (1�྿ �����Ŵϱ�) 
			if(cnt==1) 
				flagForUserSign = true; // ���� �ִٸ� true ��ȯ
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForUserSign;
	} //---ȸ������
	
	// UPDATE: ������ ���� (ȸ��)
	public boolean userUpdt(UserBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flagForUserUpdt = false;
		try {
			con = pool.getConnection();
			sql = "UPDATE user SET pwd =?, email =?, phone =? "
					+ "WHERE id = ?";//values (�����Ͱ���)
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPwd());//1�� ù��° ?�� �ǹ�
			pstmt.setString(2, bean.getEmail());//2�� �ι�° ?�� �ǹ�
			pstmt.setString(3, bean.getPhone());//3�� ����° ?�� �ǹ�
			pstmt.setString(4, bean.getId());//4�� �׹�° ?�� �ǹ�
			//����� ���ڵ� ���� : ���� �� ó�� : 0, �������� ó�� : 1 
			int cnt = pstmt.executeUpdate();
			if(cnt==1) flagForUserUpdt = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flagForUserUpdt;
	} //--����������	
	
}
