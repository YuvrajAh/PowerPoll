package loginServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CastVote
 */
public class CastVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CastVote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{String vote =  request.getParameter("party");
//		System.out.println(vote);
		int partyId = Integer.parseInt(vote); 
		System.out.println(111);
		String UserId = request.getParameter("txtUserId");
//		String UserId = (String) request.getAttribute("userId");
//		System.out.println(UserId);
//		int userId = Integer.parseInt(UserId);
		System.out.println(UserId);
		System.out.println("Cast my Vote to  : "+vote);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (partyId!=0) {
		CastVoteMethods.castVote(partyId, UserId , response);}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		}catch (Exception ex) {
			ex.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
