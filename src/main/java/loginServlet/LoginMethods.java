package loginServlet;

import java.sql.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;

public class LoginMethods {
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
	
	
	public static boolean checkPass(int formUserId,String formPass) {
		boolean isauthenticate=false;
		String dbPass = "";
		int dbUserId= -1;
		try {
			ResultSet rs = st.executeQuery("select user_id,password from user_master where user_id = "+ formUserId+";");
			while(rs.next()) {
				dbUserId =  rs.getInt("user_id");
				dbPass =  rs.getString("Password");	
			}
			System.out.println("Userid: "+dbUserId+"Pass:" + dbPass);
			
			if((dbUserId==formUserId) && (dbPass.equals(formPass))) {
				System.out.println("UserName & Password matched start process to forwars to OTP page.");
				isauthenticate = true;
			}else {
				System.out.println("UserName & Password did not matched returned back to same page");
			}
			
		}catch (Exception ex) {ex.printStackTrace();}
		
		return isauthenticate;
	}
	
	
	
	public static int dbSessionEntry(int userId,String IpAddress,String sessionId) {
		int temp = 0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  
	   System.out.println(dtf.format(now));
	   String dateTime = dtf.format(now);
	   try {
		String query = "Insert into login_session(user_id,ip_address,created_on,status,session_id) values("+userId+","+"\""+IpAddress+"\""+",STR_TO_DATE(\""+dateTime+"\",\"%Y/%m/%d %H:%i:%s\"),1,"+"\""+sessionId+"\""+");";
		System.out.println(query);
		ResetPreviousSessionsStatus(userId);
		//st.executeUpdate(query);
		temp=1;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return temp;
	}
	
	
	
	public static void ResetPreviousSessionsStatus(int userId) {
		try {
			ResultSet rs1 = st.executeQuery("select login_id from login_session  where user_id="+userId+" && status=1 order By created_on DESC limit 1;");
			while(rs1.next()) {
				int loginId = rs1.getInt("login_id");
				st.executeUpdate("Update login_session set status=0 where login_id="+loginId+";");
			}
			System.out.println("Update query to update the last status");		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static int getUserType(int userId) {
		int userType=-1;
		try {
			ResultSet r2 = st.executeQuery("Select user_id,user_type from user_master where user_id="+userId+";");
			while(r2.next()) {
				userType = r2.getInt("user_type");
				System.out.println(userType);
			}
			
		}catch (Exception e3) {
			e3.printStackTrace();
		}
		
		return userType;
	}
	
	
}
