<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.airline.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css"/>
<title>Flights List</title>
</head>
<body>

	<h1>List of Flights</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>From</th>
			<th>To</th>
			<th>Time</th>
			<th>Price</th>
			<th>Airplane</th>
			<th>Seating</th>
			<th>Number of pilots</th>
			<th>Pilot names</th>		
		</tr>
		
		<%
			List<FLIGHT> fList = (List<FLIGHT>) request.getAttribute("flight_list");
		
			for(Integer i=0; i<fList.size(); i++){
		%>
		
			<tr>
				<td><%= fList.get(i).getID() %></td>
				<td><%= fList.get(i).getFLIGHTORIGIN() %></td>
				<td><%= fList.get(i).getFLIGHTDESTINATION() %></td>
				<td><%= fList.get(i).getFLIGHTTIME() %></td>
				<td><%= fList.get(i).getPRICE() %></td>
				
				<td><%= fList.get(i).getAIRPLANEDETAIL().getPLANEMAKE() + " " + fList.get(i).getAIRPLANEDETAIL().getMODELNAME()%></td>
				<td><%= fList.get(i).getAIRPLANEDETAIL().getSEATINGCAPACITY()%></td>
				
				<td>
					<%
						if(fList.get(i).getPilots() != null){					
					 %>
				
						<%= fList.get(i).getPilots().size() %> pilots
						<%
							}
							else{						
						 %>
						No pilots yet
					<%
						}
					 %>
				</td>
				
				<td>
					<%
						if(fList.get(i).getPilots()!=null){	
							List<PILOT> pList =	(List<PILOT>) fList.get(i).getPilots();
							for(Integer j=0;j<pList.size();j++){			
					 %>
					 	<%=
					 		(j+1) + ") " + pList.get(j).getFIRSTNAME() + " "+ pList.get(j).getLASTNAME()
					 		+ " (" + pList.get(j).getPILOTRANK()+")" + "<br />"
					 	 %>
					 
					 
					 <%
					 		}
					 	}
					 
					  %>
				
				</td>
			</tr>
			
			<tr>
				<td colspan="9">
					<%
						if(fList.get(i).getPassengers().size()>0){
							List<PASSENGER> passengerList = (List<PASSENGER>) fList.get(i).getPassengers();
							
							for(Integer k=0; k < passengerList.size(); k++){							
					 %>
					 		<%= k+1 %>) <%= (String) passengerList.get(k).getFIRSTNAME() %> <%= (String) passengerList.get(k).getLASTNAME() %> <br />
					 
					 <%
					 		}
				 		} else{
					  %>
					  	No Passengers on this flight yet.
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