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
	<h2>Manage Faculties</h2>
		<s:form action="managefaculties" method="post">
		<s:text name="Operation(0-create, 1-remove, 2-edit):" />
		<s:textfield name="operation" /><br>
		<s:text name="Type(0-faculty, 1-department):" />
		<s:textfield name="typeofbuilding" /><br>
		<s:text name="Name:" />
		<s:textfield name="nameofbuilding" /><br>
		<s:text name="NewName:" />
		<s:textfield name="newnameofbuilding" placeholder=""  /><br>
		<s:submit />
	</s:form>	

</body>
</html>