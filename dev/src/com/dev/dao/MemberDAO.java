package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dev.vo.MemberVO;

public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "JSP";
	private static final String PW = "tiger";
	
	private MemberDAO() { }
	
	
	public static MemberDAO getInstance() {
		return dao;
	}
	
	
	public Connection connect() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PW);
			
		} catch(Exception ex) {
			System.out.println("오류 발생 : " + ex);
		}
		
		return conn;
	}
	
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
				
			} catch(Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
		
		close(conn, ps);
	}
	
	
	public void close(Connection conn, PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
				
			} catch(Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
				
			} catch(Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
	}
	
	
	public void memberInsert(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("INSERT INTO MEMBER VALUES(?, ?, ?, ?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMail());
			
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
			System.out.println("오류 발생 : " + ex);
			
		} finally {
			close(conn, pstmt);
		}
	}
	
	
	public MemberVO memberSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO member = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("ID"));
				member.setPasswd(rs.getString("PASSWD"));
				member.setName(rs.getString("NAME"));
				member.setMail(rs.getString("MAIL"));
			}
			
		} catch(Exception ex) {
			System.out.println(ex);
			
		} finally {
			close(conn, pstmt, rs);
		}
		
		return member;
	}
	
	
	public void memberUpdate(MemberVO member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET PASSWD=?, NAME=?, MAIL=? WHERE ID=?");
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getMail());
			pstmt.setString(4, member.getId());
			
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
			System.out.println("오류 발생 : " + ex);
			
		} finally {
			close(conn, pstmt);
		}
	}
	
	
	public void memberDelete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("DELETE FROM MEMBER WHERE ID=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch(Exception ex) {
			System.out.println("오류 발생 : " + ex);
			
		} finally {
			close(conn, pstmt);
		}
	}
	
	
	public ArrayList<MemberVO> memberList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		MemberVO member = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("ID"));
				member.setPasswd(rs.getString("PASSWD"));
				member.setName(rs.getString("NAME"));
				member.setMail(rs.getString("MAIL"));
				
				list.add(member);
			}
			
		} catch(Exception ex) {
			System.out.println("오류 발생 : " + ex);
			
		} finally {
			close(conn, pstmt, rs);
		}
		
		return list;
	}
}
