package com;
import model.Feedback;

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Feedbacks")

public class FeedbackService {
	
	
Feedback feedbacksObj = new Feedback();
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String insertFeedback(@FormParam("fcname") String fcname, 
	 @FormParam("date") String date, 
	 @FormParam("feedback") String feedback)
	 
	{ 
	 String output = feedbacksObj.insertFeedback(fcname, date, feedback); 
	return output; 
	}
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readFeedback() 
	 { 
	 return feedbacksObj.readFeedback(); 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	
	public String updateFeedback(String feedbackData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject feedbackObject = new JsonParser().parse(feedbackData).getAsJsonObject(); 
	 
	//Read the values from the JSON object
	 String feedbackId = feedbackObject.get("feedbackId").getAsString(); 
	 String fcname = feedbackObject.get("fcname").getAsString(); 
	 String date = feedbackObject.get("date").getAsString(); 
	 String feedback = feedbackObject.get("feedback").getAsString(); 
	 
	 String output = feedbacksObj.updateFeedback(feedbackId, fcname, date, feedback); 
	return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteService(String feedbackData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser()); 
	 
	//Read the value from the element feedbackId
	 String feedbackId = doc.select("feedbackId").text(); 
	 String output = feedbacksObj.deleteFeedback(feedbackId); 
	return output; 
	}

	
	
	

}
