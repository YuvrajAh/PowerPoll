package loginServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static HttpSession session;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		int userId = Integer.parseInt(request.getParameter("txtUserId"));
		String pass = request.getParameter("txtPass");
//		int userId = Integer.parseInt("1");
//		String pass = "user@1";
		boolean passCheck = LoginMethods.checkPass(userId, pass);
		System.out.println(passCheck);
		if (LoginMethods.checkPass(userId, pass)){
			session = request.getSession();
			session.setAttribute("isAuthenticated", "1");
			session.setAttribute("userEid", userId);
			System.out.println(session.getId());
			int userType = LoginMethods.getUserType(userId);
			String userIpAddress = request.getRemoteAddr();
			int temp=0;
			while(temp==0) {
				temp = LoginMethods.dbSessionEntry(userId, userIpAddress,session.getId());
			}
			System.out.println(userIpAddress);
			if(userType == 0) {
				VotingPageMethods.callingVotingPage(request, response, userId);				
			}
			else if(userType == 1) {
				response.sendRedirect("dashboard.jsp");
			}			
		}
		else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			request.setAttribute("status",1);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			//response.sendRedirect("login.jsp");
			
			
		}}catch (Exception ex) {
			response.sendRedirect("/PowerPoll");
			ex.printStackTrace();
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
