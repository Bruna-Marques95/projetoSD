<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>iVotas - Log In</title>
</head>
<body>
	<h1>iVotas - Sign up</h1>
	<s:form action="signup" method="post">
		<s:text name="Full name:" />
		<s:textfield name="name" /><br>
		<s:text name="Username:" />
		<s:textfield name="username" /><br>
		<s:text name="Password:" />
		<s:textfield name="password" /><br>
		<s:text name="Telephone Number:" />
		<s:textfield name="phonenumber" /><br>
		<s:text name="Address:" />
		<s:textfield name="address" /><br>
		<s:text name="Date of expiration:" />
		<s:textfield name="expiracydate" /><br>
		<s:text name="Citizen card number(8digits):" />
		<s:textfield name="ccnumber" /><br>
		<s:text name="Organic unit:" />
		<s:textfield name="organicunit" /><br>
		<s:text name="Occupation:" />
		<s:textfield name="occupation" /><br>
		<s:text name="Permission(0-user,1-admin):" />
		<s:textfield name="permission" /><br>
		<s:submit />
	</s:form>
</body>
</html>
 
