//Hide the divisions and show the status messages
$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
	 {
		 $("#alertSuccess").hide();
	 }
	 $("#alertError").hide();
});
//save button 
$(document).on("click", "#btnSave", function(event)
{

	//clear previous alerts 
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();
 
	//form validations
	var status = validateFeedbackForm();
	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
	 	return;
	 }

	//if hidRequestIDSave value is null set as POST else set as PUT
	 var type = ($("#hidRequestIDSave").val() == "") ? "POST" : "PUT";
	 
	 //ajax communication 
	 $.ajax({
		
				 url : "RequestsAPI",
				 type : type,
				 data : $("#formRequest").serialize(),
				 dataType : "text",
				 complete : function(response, status)
				 {
				 onRequestSaveComplete(response.responseText, status);
			 }
 });
});

//function onRequestSaveComplete  after save request
function onRequestSaveComplete(response, status)
{
	if (status == "success")
	 {
		var resultSet = JSON.parse(response); //response status is success
	 
	 if (resultSet.status.trim() == "success")
	 {
		 $("#alertSuccess").text("Successfully saved.");
		 $("#alertSuccess").show();
		 
		//data in json to html
	 	 $("#divFeedbacksGrid").html(resultSet.data);
	 	
	 	//if json status Error 
	 } else if (resultSet.status.trim() == "error") //json status is error
	 {
		
		 $("#alertError").text(resultSet.data);
		 $("#alertError").show();
	 }
	 
} else if (status == "error")

{	//response status is error
	 $("#alertError").text("Error while saving.");
	 $("#alertError").show();
} else
{	//if an unknown error
	 $("#alertError").text("Unknown error while saving..");
	 $("#alertError").show();
}
	//reset form
	$("#hidRequestIDSave").val("");
	$("#formRequest")[0].reset();
}

//Update Button
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidRequestIDSave").val($(this).data("requestid"));
	 $("#fullname").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#phoneno").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#pAddress").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#electricitySupply").val($(this).closest("tr").find('td:eq(4)').text());
	 $("#purposeofusage").val($(this).closest("tr").find('td:eq(5)').text());
 
});


//Delete Operation
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
	 url : "RequestsAPI",
	 type : "DELETE",
	 data : "requestId=" + $(this).data("requestid"),
	 dataType : "text",
	 complete : function(response, status)
	 {
	 onRequestDeleteComplete(response.responseText, status);
 	 }
 });
});

//after completeing the delete request
function onRequestDeleteComplete(response, status)
{
	if (status == "success")
	 {
		 var resultSet = JSON.parse(response);
		 
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 
		 	 $("#divRequestsGrid").html(resultSet.data);
		 	 
		 } else if (resultSet.status.trim() == "error")
		 {
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		 }
		 
	} else if (status == "error")
	{
		 $("#alertError").text("Error while deleting.");
		 $("#alertError").show();
	} else
		 {
		 $("#alertError").text("Unknown error while deleting..");
		 $("#alertError").show();
 }
}
//form validations
function validateFeedbackForm()
{

//customer name
if ($("#fullname").val().trim() == "")
 {
 return "Insert Customer Full Name.";
 }
// Email
if ($("#email").val().trim() == "")
 {
 return "Insert Valid Email Address.";
 }
 //Phoneno
 if ($("#phoneno").val().trim() == "")
 {
 return "Insert Phone no.";
 }
 //Address of the Premises where Electricity Supply is Required
  if ($("#pAddress").val().trim() == "")
 {
 return "Insert Address of the Premises where Electricity Supply is Required.";
 }
 
 //Required Electricity Supply
  if ($("#electricitySupply").val().trim() == "")
 {
 return "Insert Required Electricity Supply.";
 
 }
 //Purpose of Usage
  if ($("#purposeofusage").val().trim() == "")
 {
 return "Insert Purpose of Usage.";
  
 }
	return true;
}