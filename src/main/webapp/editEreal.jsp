<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Exame Realizado</title>
        <style type="text/css">
            body{
                text-align: center;
            }
            table{
                border-collapse: collapse;
                margin: 0 auto;
            }
            th, td{padding: 10px;}
            input{padding: 10px;}
            tfoot{text-align: center;}
        </style>
</head>
<body>
<h1 align="center">Editar Exame Realizado</h1>
	<s:if test="sm != null">
		<font color="green">
			<s:property value="sm"/>
		</font>	
	</s:if>
	<s:if test="em != null">
		<font color="red">
			<s:property value="em"/>
		</font>	
	</s:if>
	<s:form action="updateExameRealizado" method="post">
		<s:textfield name="exameRealizado.cd_funcionario" readonly="true"  label="Codigo Funcionario" />
		<s:textfield name="exameRealizado.funcionario.nm_funcionario" readonly="true"  label="Nome Funcionario" />
		<s:textfield name="exameRealizado.cd_exame" readonly="true" label="Codigo Exame" />
		<s:textfield name="exameRealizado.exame.nm_exame" readonly="true" label="Nome Exame" />
		<s:textfield name="cd_exame_novo" value="" label="Novo Codigo Exame" />
		<s:textfield name="exameRealizado.dt_realizacao" disabled="true" label="Data Realizacao" />
		<s:submit value="Alterar"></s:submit>
	</s:form>	
	<s:form action="listEreal" method="post">
		<s:submit value="Voltar"></s:submit>
	</s:form>	
</body>
</html>