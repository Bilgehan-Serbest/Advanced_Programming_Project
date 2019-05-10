<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style>
		body{			
			font-family: arial, sans-serif;
			font-size: 14px;
		}
		.select-css {
		    display: block;
		    font-size: 16px;
		    font-family: sans-serif;
		    font-weight: 700;
		    color: #444;
		    line-height: 1.3;
		    padding: .6em 1.4em .5em .8em;
		    width: 100%;
		    max-width: 100%; 
		    box-sizing: border-box;
		    margin: 0;
		    border: 1px solid #aaa;
		    box-shadow: 0 1px 0 1px rgba(0,0,0,.04);
		    border-radius: .5em;
		    -moz-appearance: none;
		    -webkit-appearance: none;
		    appearance: none;
		    background-color: #fff;
		    background-image: url('data:image/svg+xml;charset=US-ASCII,%3Csvg%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%20width%3D%22292.4%22%20height%3D%22292.4%22%3E%3Cpath%20fill%3D%22%23007CB2%22%20d%3D%22M287%2069.4a17.6%2017.6%200%200%200-13-5.4H18.4c-5%200-9.3%201.8-12.9%205.4A17.6%2017.6%200%200%200%200%2082.2c0%205%201.8%209.3%205.4%2012.9l128%20127.9c3.6%203.6%207.8%205.4%2012.8%205.4s9.2-1.8%2012.8-5.4L287%2095c3.5-3.5%205.4-7.8%205.4-12.8%200-5-1.9-9.2-5.5-12.8z%22%2F%3E%3C%2Fsvg%3E'),
		      linear-gradient(to bottom, #ffffff 0%,#e5e5e5 100%);
		    background-repeat: no-repeat, repeat;
		    background-position: right .7em top 50%, 0 0;
		    background-size: .65em auto, 100%;
		}
		.select-css::-ms-expand {
		    display: none;
		}
		.select-css:hover {
		    border-color: #888;
		}
		.select-css:focus {
		    border-color: #aaa;
		    box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
		    box-shadow: 0 0 0 3px -moz-mac-focusring;
		    color: #222; 
		    outline: none;
		}
		.select-css option {
		    font-weight:normal;
		}
		form {
			float: left;	
			margin-left: 3em;
			width: 14%;									
		}		

	</style>
<meta charset="ISO-8859-1">
</head>
<body>
<div class="jumbotron">
	<h1>Library Management System</h1>

	<hr/>		

	<form method="POST" action="AddLibrary">		
		<h2>Add New Library</h2>
		<br/>
		<input class="form-control" name="libName" type="text" placeholder="Library Name"></input>
		<br />		
		 <textarea class="form-control z-depth-1" id="exampleFormControlTextarea6" name="libAdr" rows="2" placeholder="Library Address"></textarea>		
		<br />			
		<button class="btn btn-secondary btn-sm" name="submit">Add Library</button>				
	</form>	

	<form method="POST" action="CreateBookAndAddToLibrary">		
		<h2>Add Book to Library</h2>
		<br/>
		<input class="form-control" name="bookTitle" type="text" placeholder="Book Title"></input>
		<br />		
		<input class="form-control" name="bookAuthor" type="text" placeholder="Author"></input>
		<br />		
		<input class="form-control" name="bookPublisher" type="text" placeholder="Publisher"></input>
		<br />				
		<input class="form-control" name="bookLibraryId" type="text" placeholder="Library Id"></input>
		<br />		
		<button class="btn btn-secondary btn-sm" type="submit">Add Book to Library</button>		
	</form>

	<form method="POST" action="CreateMember"> 
		<h2>Create Member</h2>
		<br/>
		<input class="form-control" name="memberFirstName" type="text" placeholder="First Name"></input>
		<br />		
		<input class="form-control" name="memberLastName" type="text" placeholder="Last Name"></input>	
		<br />		
		<input class="form-control" name="memberDob" placeholder="Date of Birth (mm/dd/yyyy)" type="text"></input>
		<br />
		<select class="select-css" name="gender">
			<option value="Female">Female</option>
			<option value="Male">Male</option>
		</select>		
		<br />		
		<button class="btn btn-secondary btn-sm" type="submit">Create Member</button>		
	</form>	
	

	<form method="POST" action="AddLibrarianToLibrary">	
		<h2>Add Librarian to Library</h2>
		<input class="form-control" name="librarianFirstName" type="text" placeholder="First Name"></input>
		<br />
		<input class="form-control" name="librarianLastName" type="text" placeholder="Last Name"></input>
		<br />
		<input class="form-control" name="librarianLibraryId" type="text" placeholder="Library Id"></input>
		<br />
		<button class="btn btn-secondary btn-sm" type="submit">Add Librarian to Library</button>					
	</form>
	
	<form method="post" action="RegisterMemberToLibrary">
		<h2>Register Member to Library</h2>
		<input class="form-control" name="registerMemberId" type="text" placeholder="Member Id"></input>
		<br />
		<input class="form-control" name="registerLibraryId" type="text" placeholder="Library Id"></input>
		<br />
		<button class="btn btn-secondary btn-sm" type="submit">Register Member to Library</button>		
		
	</form>
	
	<form method="post" action="IssueBookToMember">
		<h2>Lend Book to Member</h2>
		<input class="form-control" name="lendMemberId" type="text" placeholder="Member Id"></input>
		<br />
		<input class="form-control" name="lendBookId" type="text" placeholder="Book Id"></input>
		<br />
		<button class="btn btn-secondary btn-sm" type="submit">Lend Book to Member</button>				
	</form>	
	<br style="line-height: 696px"/>
	
</div>
</body>
</html>