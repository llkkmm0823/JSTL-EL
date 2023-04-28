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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
// login.do로 별칭 설정
// create때 설정하지 못했더라도 이 공간에 별칭 넣어주면 사용 가능
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get으로 호출되었을 때 실행됨
		String url = "member/loginForm.jsp";

		HttpSession session = request.getSession();
		if (session.getAttribute("loginUser") != null) { // loginUser가 null값이 아닐경우 : 누군가 로그인이 되어있을 경우
			url = "main.do";

		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 전달된 아이디 비번 변수에 저장
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		// 전달된 userid 로 member table에서 회원정보를 조회하여 리턴,
		// 검색 결과에 따라 pwd도 비교하여 정상 로그인 여부를 결정

		// 로그인이 실패했을 때를 대비해서 포워딩 할 경로를 먼저 설정
		String url = "member/loginForm.jsp";

		// 전달된 userid를 전달인수로 하는 getMember()메서드를 호출해서 해당 회원의 정보를 dto에 담아 리턴받음
		MemberDao mdao = MemberDao.getInstance();
		MemberDto mdto = mdao.getMember(userid);

		// getMember에 의해 리턴된 결과에 따라 로그인의 결과도 결정되어 해당페이지로 이동
		if (mdto == null) {// 조회된 아이디가 없을 경우
			request.setAttribute("message", "존재하지 않는 아이디입니다.");
		} else if (mdto.getPwd() == null) {// 비밀번호가 null값인 경우
			// 아래 코드와 맥락상 일치할 수 있으나 보통 equals 메서드는 null값인 경우 바로 오류를 발생시키기 때문에 이 코드를 기입함
			request.setAttribute("message", "DB 오류. 관리자에게 문의하세요");
		} else if (!mdto.getPwd().equals(pwd)) {// 비밀번호가 입력한 비밀번호와 일치하지 않을 경우
			request.setAttribute("message", "비밀번호가 맞지 않습니다");
		} else if (mdto.getPwd().equals(pwd)) {// 비밀번호가 입력한 비밀번호와 일치하여 정상로그인이 될 경우

			url = "main.do";
//			url=member/main.jsp
			// 로그인한 사람의 정보(mdto)를 session에 저장
			// session은 각 페이지에 있는 request객체에서 얻어 쓸 수 있는데,
			// jsp페이지에서는 그 페이지가 갖고 잇는 request안의 session을 별도 작업없이 그냥 사용해도 되지만,
			// 서블릿에서는 request를 전달인수로 받아서 매개변수에 저장된 형태로 쓰기 때문에 별도로 추출하는 동작 필요
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mdto);

			// 회원정보 조회 후 조회내용을 request에 담아서 갈 예정
//			ArrayList<MemberDto> list = mdao.selectMember();
//			request.setAttribute("memberList", list);
			// main.jsp로 가능 경로가 이 코드 뿐만 아니라 다른 곳에 존재할 가능성이 있고,
			// 필요한 코드가 이 외에도 많을 수 있으므로 main.do 라는 서블렛을 생성하여 서블렛을 거쳐 main으로 이동할 수 있게 함

		} else {// 어떤 이유에서인지 모르겠으나 로그인이 되지 않는 경우
			request.setAttribute("message", "무슨 이유에서인지 로그인이 되지 않습니다. 관리자에게 문의하세요");
		}
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
