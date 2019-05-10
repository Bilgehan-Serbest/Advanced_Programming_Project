<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, com.library.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Member List</title>
</head>
<body>

<h1>List of Members</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of Birth</th>
			<th>Gender</th>		
		</tr>
		
		<%
			List<Member> mList = (List<Member>) request.getAttribute("flight_list");
		
			for(Integer i=0; i<mList.size(); i++){
		%>
		
			<tr>
				<td><%= mList.get(i).getId() %></td>
				<td><%= mList.get(i).getFirstName() %></td>
				<td><%= mList.get(i).getLastName() %></td>
				<td><%= mList.get(i).getDob() %></td>
				<td><%= mList.get(i).getGender() %></td>
				
				
				
			</tr>
		
			
		
		<%
			}
		 %>
		
	</table>

</body>
</html>