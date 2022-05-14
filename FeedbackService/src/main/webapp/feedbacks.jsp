<%@ page import ="model.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/feedbacks.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>FEEDBACK MANAGEMENT</h1>

				<!--Feedback details form-->
				<form id="formFeedback"
				 name="formFeedback" >

					<br>Customer Name: 
					<input id="fcname"
					 name="fcname"
					 type="text"
					 class="form-control form-control-sm"> 
					
					<br>Date: 
					<input id="date"
					 name="date" 
					 type="text"
					 class="form-control form-control-sm"> 
					
					<br> Feedback or Complaint:
					<input id="feedback"
					name="feedback" 
					type="text"
					class="form-control form-control-sm"> 
					
					<br> <input
						id="btnSave" 
						name="btnSave" 
						type="button" 
						value="Save"
						class="btn btn-primary"> 
						
						<input type="hidden"
						id="hidFeedbackIDSave" 
						name="hidFeedbackIDSave" value="">
				</form>
	
				<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divFeedbacksGrid">
				<%
						Feedback feedback = new Feedback();
						out.print(feedback.readFeedback());
						
				%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>