package com.ezen.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MemberDao;

/**
 * Servlet implementation class EditAdminServlet
 */
@WebServlet("/editadmin.do")
public class EditAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditAdminServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 아이디 현재 등급을 전달받아 변수에 저장
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		String admin = request.getParameter("admin");
		
		//현재 등급을 보고 수정할 등급으로 변수값을 수정
		if(admin.equals("1"))admin="0";
		else admin = "1";
		
		//수정 메서드 이름은 editAdmin으로 하고 전달인수는 아이디와 수정할 등급 두 개만 전달
		MemberDao mdao = MemberDao.getInstance();
		mdao.editAdmin(userid,admin);
		
		//수정을 완료하면 main.jsp로 되돌아감
		RequestDispatcher dp = request.getRequestDispatcher("main.do");
		dp.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
