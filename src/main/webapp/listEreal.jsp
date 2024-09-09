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
<h1 align="center"><s:text name="Consulta de Exames Realizados"/></h1>
<table border="1" >
        <s:form action="listExameRealizados"  method="post">
<%-- 	 		<s:textfield name="exameRealizado.cd_funcionario"  value=""  label="Codigo Funcionario" /> --%>
<%-- 			<s:textfield name="exameRealizado.cd_exame"  value=""  label="Codigo Exame" /> --%>

			<s:select label="Funcionarios" 
						list="funcionarioList"
						listKey="cd_funcionario"
			       		listValue="nm_funcionario"
						value="exameRealizado.cd_funcionario"
						name="exameRealizado.cd_funcionario" />
						
			<s:select label="Exames"
						list="exameList"
						listKey="cd_exame"
			       		listValue="nm_exame"
						name="exameRealizado.cd_exame" 
						value="exameRealizado.cd_exame"/>
    
            <s:submit value="Consultar"/>
        </s:form>
        </table>
        <br/><br/>
<h1 align="center" style="color: green">Lista de Exames Realizado</h1>
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
				<th>Cd Funcionario</th>
				<th>Nm Funcionario</th>
				<th>Cd Exame</th>
				<th>Nm Exame</th>
				<th>Dt Realizacao</th>
				<th colspan="2" >Acao</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="row" items="${exameRealizadoList}">
			<tr>
				<td>${row.cd_funcionario}</td>
				<td>${row.funcionario.nm_funcionario}</td>
				<td>${row.cd_exame}</td>
				<td>${row.exame.nm_exame}</td>
				<td>${row.dt_realizacao}</td>
				<td><a href="editEreal.html?cd_funcionario=${row.cd_funcionario}&cd_exame=${row.cd_exame}">Editar Ex. Realizado</a></td>
				<td><a onclick="return confirm('Deseja apagar este exame realizado?')" href="deleteEreal.html?cd_funcionario=${row.cd_funcionario}&cd_exame=${row.cd_exame}">Apagar Ex. Realizado</a></td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="4"><a href="index.html">Menu Principal</a></th>	
				<th colspan="3"><a href="insertEreal.html">Inserir Exame Realizado</a></th>
			</tr>
			
		</tfoot>
	</table>
</body>
</html>