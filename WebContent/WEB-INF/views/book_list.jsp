<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.library.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css"/>
<title>Book List</title>
</head>
<body>

<h1>List of Books</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Name of the Borrower</th>
			<th>Name of the Registered Library</th>								
		</tr>
		
		<%
			List<BOOK> bList = (List<BOOK>) request.getAttribute("book_list");
		
			for(Integer i=0; i<bList.size(); i++){
		%>
		
			<tr>
				<td><%= bList.get(i).getID() %></td>
				<td><%= bList.get(i).getTITLE() %></td>
				<td><%= bList.get(i).getAUTHOR() %></td>
				<td><%= bList.get(i).getPUBLISHER() %></td>
				<td>
				<%
					if(bList.get(i).getMEMBEROFBOOK() != null){				
				 %>
 				 <%= bList.get(i).getMEMBEROFBOOK().getFIRSTNAME() %>
 					
 				<% }
 					else{
 				 %>
 				 	Null
 				 <% } %>
 				 </td>
				<td><%= bList.get(i).getLIBRARYOFBOOK().getNAME() %></td>
			</tr>
		
			
		
		<%
			}
		 %>
		
	</table>

</body>
</html>