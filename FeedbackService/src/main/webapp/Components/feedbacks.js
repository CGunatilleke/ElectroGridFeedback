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

	//if hidFeedbackIDSave value is null set as POST else set as PUT
	 var type = ($("#hidFeedbackIDSave").val() == "") ? "POST" : "PUT";
	 
	 //ajax communication 
	 $.ajax({
		
				 url : "FeedbacksAPI",
				 type : type,
				 data : $("#formFeedback").serialize(),
				 dataType : "text",
				 complete : function(response, status)
				 {
				 onFeedbackSaveComplete(response.responseText, status);
			 }
 });
});

//function onFeedbackSaveComplete  after save request
function onFeedbackSaveComplete(response, status)
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
	$("#hidFeedbackIDSave").val("");
	$("#formFeedback")[0].reset();
}

//Update Button
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidFeedbackIDSave").val($(this).data("feedbackid"));
	 $("#fcname").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#date").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#feedback").val($(this).closest("tr").find('td:eq(2)').text());
 
});


//Delete Operation
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
	 {
	 url : "FeedbacksAPI",
	 type : "DELETE",
	 data : "feedbackId=" + $(this).data("feedbackid"),
	 dataType : "text",
	 complete : function(response, status)
	 {
	 onFeedbackDeleteComplete(response.responseText, status);
 	 }
 });
});

//after completeing the delete request
function onFeedbackDeleteComplete(response, status)
{
	if (status == "success")
	 {
		 var resultSet = JSON.parse(response);
		 
		 if (resultSet.status.trim() == "success")
		 {
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 
		 	 $("#divFeedbacksGrid").html(resultSet.data);
		 	 
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
if ($("#fcname").val().trim() == "")
 {
 return "Insert Customer Name.";
 }
// Date
if ($("#date").val().trim() == "")
 {
 return "Insert Date.";
 }
 //feedback
 if ($("#feedback").val().trim() == "")
 {
 return "Insert Feedback.";
 }
 
	return true;
}



























