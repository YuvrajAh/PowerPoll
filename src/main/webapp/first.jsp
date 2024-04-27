<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");  
        System.out.println("222");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/powerpoll","root","Admin@123");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select user_id,password from user_master where user_id="+1+";");
        String dbPass = null;
        String dbUserId = null;
        String userId1 = ""+1;
        while (rs.next()){
	        	dbUserId = (""+rs.getInt("user_id")+"");
	        	dbPass = rs.getString("password");
	        	System.out.println(dbUserId);
	        	System.out.println(dbPass);
//		            System.out.print(rs.getCharacterStream("LOANNO")+"\t");
//		            System.out.print(rs.getString("CNAME")+"\t");
//		            System.out.print(rs.getString("BNAME")+"\t");
//		            System.out.print(rs.getInt("AMOUNT")+"\n");
	        }
	       
	}catch (Exception ex){ex.printStackTrace();}
	%>
</body>
</html>