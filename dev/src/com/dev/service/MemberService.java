package com.dev.service;

import java.util.ArrayList;

import com.dev.dao.MemberDAO;
import com.dev.vo.MemberVO;

// 모델 - 서비스
public class MemberService {
	private static MemberService service = new MemberService();
	public MemberDAO dao = MemberDAO.getInstance();
	
	
	private MemberService() { }
	
	
	public static MemberService getInstance() {
		return service;
	}
	
	
	public void memberInsert(MemberVO member) {
		dao.memberInsert(member);
	}
	
	
	public MemberVO memberSearch(String id) {
		return dao.memberSearch(id);
	}
	
	
	public void memberUpdate(MemberVO member) {
		dao.memberUpdate(member);
	}
	
	
	public void memberDelete(String id) {
		dao.memberDelete(id);
	}
	
	
	public ArrayList<MemberVO> memberList() {
		return dao.memberList();
	}
}
