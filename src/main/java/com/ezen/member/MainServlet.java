package com.ezen.member;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class MainServlet
 */
@WebServlet("/main.do")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String url= "member/main.jsp";
		
		//main.jsp로 가기 전 로그인이 되어있는 상태인지 먼저 점검
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser")==null) 
		url = "member/loginForm.jsp"; // 로그인이 안된 상태이면 로그인 페이지로 경로를 변경
		
		MemberDao mdao = MemberDao.getInstance();
		ArrayList<MemberDto> list = mdao.selectMember();
		
		
		request.setAttribute("mList", list);
		
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
