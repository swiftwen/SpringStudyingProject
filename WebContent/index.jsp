<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>welcome</title>
</head>
<body>
 <h1>welcome to my index page!</h1>
 <h1>${name }</h1>
 <h2>${time }</h2>
 
 <%--  <a href="user/register.do">register</a>
 <h1>register</h1>
 <form method="POST" action="<%= request.getContextPath()%>/abc/test.do">
    UserName : <input type="text" name="userInfo.userName" /><br/>
    Password : <input type="text" name="userInfo.password" /><br/>
    Age : <input type="text" name="userInfo.age" /><br/>
    <input type="submit" value="register" />
 </form> --%>
 
</body>
</html>