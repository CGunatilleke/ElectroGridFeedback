<%@page import="model.Request"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Request Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/requests.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
	<h1>REQUEST MANAGEMENT</h1>
	
	<form id="formRequest" name="formRequest">
	
		 Full Name:
		 <input id="fullname" name="fullname" type="text"
		 class="form-control form-control-sm">
		 
		 <br> Email:
		 <input id="email" name="email" type="text"
		 class="form-control form-control-sm">
		 
		 <br>Phone No:
		 <input id="phoneno" name="phoneno" type="text"
		 class="form-control form-control-sm">
		 
		 <br> Address of the Premises where Electricity Supply is Required:
		 <input id="pAddress" name="pAddress" type="text"
		 class="form-control form-control-sm">
		 <br>
		 
		 <br>Required Electricity Supply:
		 <input id="electricitySupply" name="electricitySupply" type="text"
		 class="form-control form-control-sm">
		 <br>
		 
		 <br>Purpose of Usage:
		 <input id="purposeofusage" name="purposeofusage" type="text"
		 class="form-control form-control-sm">
		 <br>
		 
		 <input id="btnSave" name="btnSave" type="button" value="Save"
		 class="btn btn-primary">
		 <input type="hidden" id="hidRequestIDSave"
		 name="hidRequestIDSave" value="">
		 
		</form>
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
			 <%
			 Request requestObj = new Request();
			 out.print(requestObj.readRequests());
			 %>
</div>
</div> </div> </div>
</body>
</html>