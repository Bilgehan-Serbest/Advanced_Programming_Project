<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.airline.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css"/>
<title>Passengers List</title>
</head>
<body>

	<h1>List of Passengers</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of birth</th>
			<th>Gender</th>
		</tr>
		
		<%
			List<PASSENGER> pList = (List<PASSENGER>) request.getAttribute("passenger_list");
		
			for(Integer i=0; i<pList.size(); i++){
		%>
		
			<tr>
				<td><%= pList.get(i).getID() %></td>
				<td><%= pList.get(i).getFIRSTNAME() %></td>
				<td><%= pList.get(i).getLASTNAME() %></td>
				<td><%= pList.get(i).getDOB() %></td>
				<td><%= pList.get(i).getGENDER() %></td>	
			</tr>
			
			<tr>
				<td colspan="5">
					<%
						if(pList.get(i).getFlights().size()>0){
						
							List<FLIGHT> fList = (List<FLIGHT>) pList.get(i).getFlights();
							
							for(Integer k=0; k< fList.size();k++){
					 %>
					 		<%= k+1 %>) <%= fList.get(k).getFLIGHTORIGIN() %> to <%= fList.get(k).getFLIGHTDESTINATION() %> @ <%= fList.get(k).getFLIGHTTIME() %> <br />
					 <%
					 		}
				 		} else{
					  %>
					  	No flight tickets yet.
					  <%
					  	}
					   %>
				</td>
			</tr>
		
		<%
			}
		 %>
		
	</table>

</body>
</html>