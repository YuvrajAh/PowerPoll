package loginServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.sql.*;

public class CastVoteMethods {
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
	
	public static void castVote(int partyId,String userId, HttpServletResponse response) {
		try{response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String details[] = VotingPageMethods.getUserdetails(userId);
			String tempDetail = details[2];
			
			if(tempDetail.equals("false")) {
				System.out.println("Update party_master set vote_count=vote_count+1 where party_id="+partyId+";");
				st.executeUpdate("Update party_master set vote_count=vote_count+1 where party_id="+partyId+";");
				st.executeUpdate("Update user_master set vote_Status=1 where user_id="+userId);
				System.out.println("Vote Casted");
				out.println("Thank You . \n Your Vote has Been Casted");
			}else {
				out.println("Thank You . \n Your Vote has already Been Casted Once");
			}
		
		}catch(Exception ex2) {
			ex2.printStackTrace();
		}}
		catch(Exception ex1) {
			ex1.printStackTrace();
		}
	}
}
