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
<!-- String opcao, String eleicaoID, String nomeLista, String tipoLista -->
	<h2>Manage Lists</h2>
		<s:form action="managelists" method="post">
		<s:text name="Operation(0-create, 1-remove):" />
		<s:textfield name="option" /><br>
		<s:text name="Election id:" />
		<s:textfield name="electionid" placeholder="" /><br>
		<s:text name="List name:" />
		<s:textfield name="listname" /><br>
		<s:text name="Type of list:" />
		<s:textfield name="typeoflist" placeholder=""  /><br>
		<s:submit />
	</s:form>	

</body>
</html>