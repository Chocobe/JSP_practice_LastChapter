package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberSearchController implements Controller {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		// 1. Parameter 추출
		String id = request.getParameter("id");
		String job = request.getParameter("job");
		String path = null;
		
		if(job.equals("search")) {
			path = "/memberSearch.jsp";
			
		} else if(job.equals("update")) {
			path = "/memberUpdate.jsp";
			
		} else if(job.equals("delete")) {
			path = "/memberDelete.jsp";
		}
		
		// 2. 유효성 검사
		if(id.isEmpty()) {
			request.setAttribute("error", "ID를 입력해 주시기 바랍니다");
			HttpUtil.forward(request, response, path);
			return;
		}
		
		// 3. Service 객체의 메소드 호출
		MemberService service = MemberService.getInstance();
		MemberVO member = service.memberSearch(id);
		
		// 4. Output View 페이지로 이동
		if(member == null) {
			request.setAttribute("result", "검색된 정보가 없습니다");
		}
		request.setAttribute("member", member);
		
		if(job.equals("search")) {
			path = "/result/memberSearchOutput.jsp";
		}
		
		HttpUtil.forward(request, response, path);
	}
}
