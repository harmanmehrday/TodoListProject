<%@ page contentType="text/html; charset=ISO-8859-1" %>
 <%@ taglib prefix = "tag" uri = "http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="java.util.ArrayList" %> 
<%@ page import="model.*" %>    
    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	table,th,td{
		border : 1px solid black;
	}
</style>
</head>
<body>   
    <%
    	ArrayList<todolist> list =  (ArrayList<todolist>) request.getAttribute("list");
   		if(list == null){
   			response.sendRedirect("displayItem");
   		}
    %>
	<form action="addItem" method="post">
  		<label for="item">Item </label>
  		<input type="text" id="item" name="item">&nbsp;&nbsp;&nbsp;
  		<input type="submit" value="Submit">
	</form>
	<br/><br/>
	<tag:if test="${list != null}">
		<table>
			<tr>
    			<th>Id</th>
    			<th>Item</th>
    			<th>Date</th>
    			<th>Delete</th>
  			</tr>
			<tag:forEach var="item" items="${list}" varStatus="loop">
			<tr>
				<td>
					${loop.index+1} &nbsp;&nbsp;&nbsp;
				</td>	
				<td>
				 	${item.getItem()} &nbsp;&nbsp;&nbsp;
				</td>
				<td>	
				 	${item.getDatetime()} &nbsp;&nbsp;&nbsp;
				</td>
				<td>	
				 	<a href="deleteItem?index=${item.getId()}"> Delete</a>
				</td>	 
			</tr>
			</tag:forEach>
		</table>				
	</tag:if>
</body>
</html>