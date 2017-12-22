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
	<h2>Create election</h2>
		<s:form action="createelection" method="post">
		<s:text name="Type of election(0-General Council, 1-Student Association):" />
		<s:textfield name="typeofelection" /><br>
		<s:text name="Title of election:" />
		<s:textfield name="titleofelection" /><br>
		<s:text name="Description:" />
		<s:textfield name="descriptionofelection" /><br>
		<s:text name="Start day:" />
		<s:textfield name="startday" />
		<s:text name="Start month:" />
		<s:textfield name="startmonth" />
		<s:text name="Start year:" />
		<s:textfield name="startyear" />
		<s:text name="Start hour:" />
		<s:textfield name="starthour" />
		<s:text name="Start minute:" />
		<s:textfield name="startminute" /><br>
		<s:text name="End day:" />
		<s:textfield name="endday" />
		<s:text name="End month:" />
		<s:textfield name="endmonth" />
		<s:text name="End year:" />
		<s:textfield name="endyear" />
		<s:text name="End hour:" />
		<s:textfield name="endhour" />
		<s:text name="End minute:" />
		<s:textfield name="endminute" /><br>
		<s:text name="Organic Unit:" />
		<s:textfield name="organicunit" placeholder=""/><br>
		<s:text name="Association name:" />
		<s:textfield name="associationname" placeholder=""/><br>
		<s:submit />
	</s:form>

</body>
</html>