<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, com.library.models.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="resources/css/jpaStyles.css"/>
<title>Library List</title>
</head>
<body>

<h1>List of Libraries</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Address</th>	
			<th colspan="9">Added Books</th>
			<th colspan="9">Registered Members</th>
			<th colspan="9">Registered Librarians</th>				
		</tr>
		
		<%
			List<LIBRARY> lryList = (List<LIBRARY>) request.getAttribute("library_list");
		
			for(Integer i=0; i<lryList.size(); i++){
		%>
		
			<tr>
				<td><%= lryList.get(i).getID() %></td>
				<td><%= lryList.get(i).getNAME() %></td>
				<td><%= lryList.get(i).getADDRESS() %></td>				

				<td colspan="9">
				<%
					if(lryList.get(i).getBOOKS().size()>0){
						List<BOOK> bList = (List<BOOK>) lryList.get(i).getBOOKS();
						for(Integer k=0; k<bList.size(); k++){
				 %>
					<%= k+1 %>) <%= bList.get(k).getTITLE() %> , <%= bList.get(k).getAUTHOR() %> , <%= bList.get(k).getPUBLISHER() %> <br />
				 <%
					 		}
				 		} else{
				  %>
					  	This library doesn't have any registered books yet.
				  <%
					  	}
				   %>
				</td>

				<td colspan="9">
				<%
					if(lryList.get(i).getMEMBERS().size()>0){
						List<MEMBER> mList = (List<MEMBER>) lryList.get(i).getMEMBERS();
						for(Integer k=0; k<mList.size(); k++){
				 %>
					<%= k+1 %>) <%= mList.get(k).getFIRSTNAME() %> , <%= mList.get(k).getLASTNAME() %>  , <%= mList.get(k).getDOB() %>  , <%= mList.get(k).getGENDER() %> <br />
				 <%
					 		}
				 		} else{
				  %>
					  	This library doesn't have any registered members yet.
				  <%
					  	}
				   %>
				</td>

				<td colspan="9">
				<%
					if(lryList.get(i).getLIBRARIANS().size()>0){
						List<LIBRARIAN> lrnList = (List<LIBRARIAN>) lryList.get(i).getLIBRARIANS();
						for(Integer k=0; k<lrnList.size(); k++){
				 %>
					<%= k+1 %>) <%= lrnList.get(k).getFIRSTNAME() %> , <%= lrnList.get(k).getLASTNAME() %> <br />
				 <%
					 		}
				 		} else{
				  %>
					  	This library doesn't have any registered librarians yet.
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