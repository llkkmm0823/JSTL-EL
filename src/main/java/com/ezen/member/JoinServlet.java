package com.ezen.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDao;
import com.ezen.dto.MemberDto;

/**
 * Servlet implementation class joinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("member/joinForm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전달된 내용을 MemberDto에 담아서 insertMember()메서드를 호출. 전달인수 : MemberDto
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = new MemberDto();
		
		String name = request.getParameter("name");
		mdto.setName(name);
		
		mdto.setUserid(request.getParameter("userid"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		int result= mdao.insertMember(mdto);
		//회원가입을 마친 후 loginForm.jsp로 되돌아감. 이때 회원가입 결과를 message에 담아서 감
		if(result==1) request.setAttribute("message","회원가입이 완료되었습니다. 로그인하세요");
		else request.setAttribute("message","회원가입이 실패하였습니다. 관리자에 문의하세요");
		
		RequestDispatcher rd = request.getRequestDispatcher("member/loginForm.jsp");
		rd.forward(request, response);

	
	}

}
