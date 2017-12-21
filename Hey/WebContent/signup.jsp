<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>iVotas - Sign Up</title>
</head>
<body>
	<h1>iVotas - Sign up</h1>
	<s:form action="signup" method="post">
		<s:text name="Name:" />
		<s:textfield name="name" /><br>
		<s:text name="Username:" />
		<s:textfield name="username" /><br>
		<s:text name="Password:" />
		<s:textfield name="password" /><br>
		<s:text name="Phonenumber:" />
		<s:textfield name="phonenumber" /><br>
		<s:text name="Address:" />
		<s:textfield name="address" /><br>
		<s:text name="Expiracyday:" />
		<s:textfield name="expiracyday" />
		<s:text name="Expiracymonth:" />
		<s:textfield name="expiracymonth" />
		<s:text name="Expiracyyear:" />
		<s:textfield name="expiracyyear" /><br>
		<s:text name="Ccnumber:" />
		<s:textfield name="ccnumber" /><br>
		<s:text name="Organicunit:" />
		<s:textfield name="organicunit" /><br>
		<s:text name="Occupation:" />
		<s:textfield name="occupation" /><br>
		<s:text name="Permission:" />
		<s:textfield name="permission" /><br>
		<s:submit />
	</s:form>
	<p><a href="<s:url action="logout" />">Click here to go to login</a></p>
	
</body>
</html>
 
