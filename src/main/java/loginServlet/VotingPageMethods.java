package loginServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.*;

public class VotingPageMethods {
	private static String dbUserName="root";
	private static String dbPass="Admin@123";
	private static Statement st;
	private static Connection con;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/powerpoll",dbUserName,dbPass);
			st = con.createStatement();
		}catch (Exception ex) {ex.printStackTrace();}
		
	}
	
	public static void callingVotingPage(HttpServletRequest request, HttpServletResponse response,int userId) {
		try{String[] userDetails = VotingPageMethods.getUserdetails(userId); 
		System.out.println(userDetails[0] + ":" + userDetails[1]);
		request.setAttribute("userName", userDetails[0]);
		request.setAttribute("contactNo", userDetails[1]);
		request.setAttribute("voteStatus", userDetails[2]);
		//response.sendRedirect("first.jsp");
		ResultSet resultSet = st.executeQuery("Select party_id,party_name,party_symbol from party_master");
		request.setAttribute("partyResultSet", resultSet);
		request.getRequestDispatcher("voting.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String[] getUserdetails(int userId) {
		String[] details =new String[3];
		try {
			String userName="";
			String contactNo="";
			String voteStatus ="";
			ResultSet rs= st.executeQuery("Select user_id,user_name,contact_no,vote_status from user_master where user_id ="+userId+" ;");
			while(rs.next()) {
				userName = rs.getString("user_name");
				contactNo = rs.getString("contact_no");
				voteStatus = "" + rs.getBoolean("vote_status");
				System.out.println("Vote Status: "+voteStatus);
			}
			details[0] = userName;
			details[1] = contactNo;
			details[2] = voteStatus;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return details;
	}
	
	public static String[] getUserdetails(String userId) {
		String[] details =new String[3];
		try {
			String userName="";
			String contactNo="";
			String voteStatus ="";
			ResultSet rs= st.executeQuery("Select user_id,user_name,contact_no,vote_status from user_master where user_id ="+userId+" ;");
			while(rs.next()) {
				userName = rs.getString("user_name");
				contactNo = rs.getString("contact_no");
				voteStatus = "" + rs.getBoolean("vote_status");
				System.out.println("Vote Status: "+voteStatus);
			}
			details[0] = userName;
			details[1] = contactNo;
			details[2] = voteStatus;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return details;
	}
}
