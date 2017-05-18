<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
</head>
<body>
    <form method="POST" action="<%= request.getContextPath()%>/user/register.do">
       UserName: <input type="text" name="userName" /><br/>
       PassWord: <input type="text" name="password" /><br/>
       Age: <input type="text" name="age" /><br/>
       <input type="submit" value="register" />
    </form>
</body>
</html>