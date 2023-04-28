package com.ezen.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

/**
 * Servlet implementation class WithdrawServlet
 */
@WebServlet("/withdraw.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션에서 로그인한 사람의 아이디를 추출하고 테이블에서 해당 레코드를 삭제
		//dao메서드 이름은 deleteMember
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto)session.getAttribute("loginUser") ;

		MemberDao mdao = MemberDao.getInstance();
		int result=mdao.deleteMember(mdto.getUserid());
		
		//탈퇴를 성공하면 세션의 내용을 지우고 "회원탈퇴가 완료되었습니다" 라는 메세지를 가지고 로그인 폼으로 돌아가야함
		if(result==1) {
			session.removeAttribute("loginUser");
			request.setAttribute("message","회원탈퇴가 완료되었습니다");
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("member/loginForm.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
