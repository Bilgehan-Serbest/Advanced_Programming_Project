<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, com.library.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css"/>
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
			<th colspan="9">Borrowed Books</th>		
			<th colspan="9">Registered Libraries</th>		
		</tr>
		
		<%
			List<MEMBER> mList = (List<MEMBER>) request.getAttribute("member_list");
		
			for(Integer i=0; i<mList.size(); i++){
		%>
		
			<tr>
				<td><%= mList.get(i).getID() %></td>
				<td><%= mList.get(i).getFIRSTNAME() %></td>
				<td><%= mList.get(i).getLASTNAME() %></td>
				<td><%= mList.get(i).getDOB() %></td>
				<td><%= mList.get(i).getGENDER() %></td>

				<td colspan="9">
				<%
					if(mList.get(i).getBOOKS().size()>0){
						List<BOOK> bList = (List<BOOK>) mList.get(i).getBOOKS();
						for(Integer k=0; k<bList.size(); k++){
				 %>
					<%= k+1 %>) <%= bList.get(k).getTITLE() %> , <%= bList.get(k).getAUTHOR() %> , <%= bList.get(k).getPUBLISHER() %> <br />
				 <%
					 		}
				 		} else{
				  %>
					  	This member has not borrowed any books yet.
				  <%
					  	}
				   %>
				</td>

				<td colspan="9">
				<%
					if(mList.get(i).getLIBRARIES().size()>0){
						List<LIBRARY> lryList = (List<LIBRARY>) mList.get(i).getLIBRARIES();
						for(Integer k=0; k<lryList.size(); k++){
				 %>
					<%= k+1 %>) <%= lryList.get(k).getNAME() %> , <%= lryList.get(k).getADDRESS() %> <br />
				 <%
					 		}
				 		} else{
				  %>
					  	This member has not registered to any libraries yet.
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