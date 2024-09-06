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
        #funcionarios div {
		   width:300px;
		   height: 50px;
		   word-wrap: break-word;
		   overflow-x: auto;
		}
    </style>
</head>
<body>
<h1 align="center"><s:text name="Consulta de Funcionarios"/></h1>
<table border="1" >
        <s:form action="listFuncionarios"  method="post">
	 		<s:textfield name="funcionario.cd_funcionario" value="" label="Codigo Funcionario" />
			<s:textfield name="funcionario.nm_funcionario" label="Nome Funcionario" />
            
            <s:submit value="Consultar"/>
        </s:form>
        </table>
        <br/><br/>
<h1 align="center" style="color: green">Lista de Funcionarios</h1>
	<table border="1" id="funcionarios">
		<thead>
			<tr>
				<th colspan="7">
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
				</th>
			</tr>
			<tr>
				<th>Codigo Funcionario</th>
				<th>Nome Funcionario</th>
				<th colspan="2" >Acao</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="row" items="${funcionarioList}">
			<tr>
				<td>${row.cd_funcionario}</td>
				<td>${row.nm_funcionario}</td>
				<td><a href="editFunc.html?cd_funcionario=${row.cd_funcionario}">Editar Funcionario</a></td>
				<td><a onclick="return confirm('Deseja apagar este funcionario?')" href="deleteFunc.html?cd_funcionario=${row.cd_funcionario}">Apagar Funcionario</a></td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="2"><a href="index.html">Menu Principal</a></th>	
				<th colspan="2"><a href="insertFunc.html">Inserir Funcionario</a></th>
			</tr>
			
		</tfoot>
	</table>
</body>
</html>