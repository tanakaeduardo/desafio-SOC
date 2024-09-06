<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
    <style type="text/css">
        table{
            border-collapse: collapse;
            margin: 0 auto;
        }
        th, td{padding: 10px;}
        tfoot{text-align: center;}
        #exames div {
		   width:300px;
		   height: 50px;
		   word-wrap: break-word;
		   overflow-x: auto;
		}
    </style>
</head>
<body>
		<h1 align="center"><s:text name="Avaliacao SOC"/></h1>
		<table border="1" >
     		<s:form action="list"  method="post">
	      		<s:submit value="Consultar Exames"/>
            </s:form>
         
            <s:form action="listFunc"  method="post">
    	        <s:submit value="Consultar Funcionarios"/>
       		 </s:form>
       		          
            <s:form action="listEreal"  method="post">
    	        <s:submit value="Consultar Exames Realizados"/>
       		 </s:form>
        </table>
</body>
</html>