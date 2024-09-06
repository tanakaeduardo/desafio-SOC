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
<h1 align="center"><s:text name="Consulta de Exames"/></h1>
<table border="1" >
        <s:form action="listExames"  method="post">
	 		<s:textfield name="exame.cd_exame" value="" label="Codigo Exame" />
			<s:textfield name="exame.nm_exame" label="Nome Exame" />
			<s:radio key="ativo" list="ativoList" label="Status" />
            
            <s:submit value="Consultar"/>
        </s:form>
        </table>
        <br/><br/>
<h1 align="center" style="color: green">Lista de Exames</h1>
	<table border="1" id="exames">
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
				<th>Codigo Exame</th>
				<th>Nome Exame</th>
				<th>Status Exame</th>
				<th>Descricao Exame</th>
				<th>Sub Descricao Exame</th>
				<th colspan="2" >Acao</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="row" items="${exameList}">
			<tr>
				<td>${row.cd_exame}</td>
				<td>${row.nm_exame}</td>
				<td>${row.ds_ativo}</td>
				<td><div>${row.ds_detalhe_exame}</div></td>
				<td><div>${row.ds_detalhe_exame1}</div></td>
				<td><a href="edit.html?cd_exame=${row.cd_exame}">Editar Exame</a></td>
				<td><a onclick="return confirm('Deseja apagar este exame?')" href="delete.html?cd_exame=${row.cd_exame}">Apagar Exame</a></td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
    			<th colspan="3"><a href="index.html">Menu Principal</a></th>
				<th colspan="4"><a href="insert.html">Inserir Exame</a></th>
			</tr>
			
		</tfoot>
	</table>
</body>
</html>