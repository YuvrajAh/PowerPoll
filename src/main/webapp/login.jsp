<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style type="text/css">
body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  margin: 0;
  padding: 0;
  background: linear-gradient(to right, #ff9933, #ffffff, #138808);
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-container {
  background-color: #fff;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  color: #333;
  margin-bottom: 20px;
}

.input-group {
  margin-bottom: 20px;
}

.input-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #555;
}

.input-group input {
  width: 100%;
  padding: 12px;
  border: 2px solid #ccc;
  border-radius: 6px;
  transition: border-color 0.3s ease;
}

.input-group input:focus {
  border-color: #007bff;
  outline: none;
}

button {
  padding: 12px 24px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0056b3;
}

</style>
</head>
<body>
	<div class="container">
    <div class="login-container">
      <h2>Login to Vote</h2>
      <form action="login" method="POST">
        <div class="input-group">
          <label for="voterid">Voter ID:</label>
          <input type="text" id="voterid" name="txtUserId" required>
        </div>
        <div class="input-group">
          <label for="password">Password:</label>
          <input type="password" id="password" name="txtPass" required>
        </div>
        <button type="submit">Login</button>
      </form>
      <%try{int temp =  (Integer) request.getAttribute("status"); 
      	if(temp==1){out.println("<h3>Incorrect Password</h3>");}}catch(Exception ex){}%>
    </div>
  </div>
</body>
</html>