<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>iVotas - Administrator Console</title>
</head>
<body>
	<h1>iVotas - Administrator Console</h1>
	
	<a href="managefacilities.jsp"> >Click here to go to manage faculties and departments</a>
	
	<br>
	<br>
	
	<a href="createelection.jsp"> >Click here to go to create election</a>
	
	<br>
	<br>
	
	
	
	<a href="managelists.jsp"> >Click here to add/remove lists from an election</a>
	<br>
	<br>
	
	<a href="managevotingtable.jsp"> >Click here to add/remove tables from an election</a>
	
	<br>
	<br>
	
	<a href="editelection.jsp"> >Click here to edit an election</a>
	
	
		
	<p><a href="<s:url action="logout" />">Click here to logout</a></p>

</body>
</html>