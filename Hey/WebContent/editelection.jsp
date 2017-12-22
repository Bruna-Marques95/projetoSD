<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Edit election</h2>
		<s:form action="editelection" method="post">
		<s:text name="Text(0-change text, 1-change date):" />
		<s:textfield name="opedit" /><br>
		<s:text name="Title(case of 0) or start date[dd-mm-yyyyy-hh24-mi] (case of 1):" />
		<s:textfield name="newarg1" /><br>
		<s:text name="Description(case 0) or end date[dd-mm-yyyyy-hh24-mi] (case 1) :" />
		<s:textfield name="newarg2" /><br>
		<s:text name="Election id" />
		<s:textfield name="idelec" /><br>
		<s:submit />
	</s:form>	

</body>
</html>