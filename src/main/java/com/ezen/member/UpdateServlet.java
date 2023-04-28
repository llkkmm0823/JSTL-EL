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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	수정을 하기 위해 가져오는 곳
		//회원 정보수정은 보통 회원 아이디를 전달받아 아이디로 조회한 결과를 회원수정 폼으로 같이 갖고감
		/*
		  String userid = request.getParameter("userid"); MemberDao mdao=
		 MemberDao.getInstance(); MemberDto mdto= mdao.getMember(userid);
		 request.setAttribute("curUser", mdto);
		 ㄴ 세션의 loginUser에 정보가 저장되어 있고, jsp페이지에서도 세션값에 언제나 접근이 가능한데 괜히 조회를 한번 더 하는 꼴
		 */
		
		//로그인한 유저를 수정할 예정이기 때문에(세션에 값이 저장되어있기 때문에) 조회코드는 생략하고 값 이동만 해주면 됨
		RequestDispatcher dp = request.getRequestDispatcher("member/updateForm.jsp");
		dp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//실제로 수정이 일어나는 곳
		request.setCharacterEncoding("UTF-8");
		MemberDto mdto = new MemberDto() ;
		
		mdto.setName(request.getParameter("name"));
		mdto.setUserid(request.getParameter("userid"));
		mdto.setPwd(request.getParameter("pwd"));
		mdto.setEmail(request.getParameter("email"));
		mdto.setPhone(request.getParameter("phone"));
		mdto.setAdmin(Integer.parseInt(request.getParameter("admin")));
		
		MemberDao mdao = MemberDao.getInstance();

		int result = mdao.updateMember(mdto);
		
		if(result==1) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto); //세션 로그인 정보를 수정된 내용으로 교체	
		}
		
		RequestDispatcher dp = request.getRequestDispatcher("main.do");
		dp.forward(request, response);

		
	}

}
