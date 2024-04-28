package loginServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	public static void castVote(int partyId,int userId) {
		try {
		st.executeUpdate("Update party_master set vote_count=cote_count+1 where party_id="+partyId+";");
		st.executeUpdate("Update user_master set vote_Status=0 where user_id="+userId);
		}catch(Exception ex2) {
			ex2.printStackTrace();
		}
		
	}
}
