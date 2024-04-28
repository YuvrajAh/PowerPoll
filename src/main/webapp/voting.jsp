<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.io.*" %>
<%      try{System.out.println(session.getAttribute("isAuthenticated"));
		String a =(String) session.getAttribute("isAuthenticated");
		if(a.equals("1")){
%>
	<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Voting System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        header {
            background-color: #007bff;
            color: #fff;
            padding: 20px;
            text-align: center;
            width: 100%;
            box-sizing: border-box;
        }

        .main-content {
            display: flex;
            justify-content: center;
            width: 100%;
            box-sizing: border-box;
            padding: 20px;
            gap: 20px;
        }

        aside {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            width: 30%;
            align-self: flex-start;
        }

        section {
            max-width: 70%;
            display: flex;
            justify-content: center;
        }

        .container {
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .party-list {
            list-style-type: none;
            padding: 0;
            margin-bottom: 20px;
        }
        
        .party-list li {
            margin-bottom: 10px;
        }
        
        .vote-btn {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        
        .vote-btn:hover {
            background-color: #0056b3;
        }

        footer {
            background-color: #007bff;
            color: #fff;
            text-align: center;
            padding: 10px 0;
            width: 100%;
            box-sizing: border-box;
            margin-top: auto;
        }
    </style>
</head>
<body>
    <header>
        <h1>Welcome to the Voting System</h1>
    </header>
    <div class="main-content">
        <aside>
            <h2>User Details</h2>
            <!-- Replace with user details -->
            <p>Username: <% String temp2 = "-1";  String temp  = (String) request.getAttribute("userName"); out.println(temp);%></p>
            <p>Contact Number: <% String temp1  = (String) request.getAttribute("contactNo"); out.println(temp1);%></p>
            <!-- Add more user details as needed -->

            <h2>Election Details</h2>
            <!-- Replace with election details -->
            <p>Election Name: General Elections 2024</p>
            <p>Date: 25th April 2024</p>
            <!-- Add more election details as needed -->
        </aside>
        <section>
            <div class="container">
                <% String voteStatus  = (String) request.getAttribute("voteStatus");
                if(voteStatus.equals("false")){
                %>
                <h2>Vote for Your Desired Party</h2>
                <form action="CastVote" method="POST">
                <ul class="party-list">
                <% ResultSet partyDetails = (ResultSet) request.getAttribute("partyResultSet"); 
                while(partyDetails.next()){
                	int partyId =  partyDetails.getInt("party_id");
                	String partyName = partyDetails.getString("party_name");
                	System.out.println(partyId+" : "+partyName);
                	out.println("<li><input type=\"radio\" id=\""+partyId+"\" name=\"party\" value=\""+partyId+"\"><label for=\""+partyId+"\">"+partyName+"</label></li>");
                }
                	%>
                    <li>
                        <input type="radio" id="0" name="party" value="0">
                        <label for="0">NOTA</label>
                    </li>
                    </ul>
                    <input type="hidden" id="UserEid" name="txtUserId" value="<% out.println(session.getAttribute("userEid")); %>">
            		<% request.setAttribute("userId", temp2); %>
                    <!-- Add more parties as needed -->
                <button type="submit" class="vote-btn">Vote</button>
                </form><%}else{
                	out.println("<h1>You have Already Voted</h1>");
                }  %>
            </div>
        </section>
    </div>
    <footer>
        &copy; 2024 VotingSystem.com
    </footer>
<%
	}else{
			response.getWriter().append("failed please get back to login page and login: ").append(request.getContextPath()); 
			response.sendRedirect("login.jsp");
		}
		
	}catch(Exception ex){
		ex.printStackTrace();
		response.getWriter().append("failed please get back to login page and login: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}
%>
</body>
</html>