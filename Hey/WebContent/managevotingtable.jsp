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
	<h2>Manage Voting table</h2>
		<s:form action="managevotingtable" method="post">
		<s:text name="Operation(0-add table to election, 1-remove table from election):" />
		<s:textfield name="operationoftable" /><br>
		<s:text name="Election id:" />
		<s:textfield name="electionidoftable"/><br>
		<s:text name="Id of table:" />
		<s:textfield name="tableid" /><br>
		
		<s:submit />
	</s:form>

</body>
</html>