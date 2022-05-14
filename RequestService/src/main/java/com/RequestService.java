package com;
import model.Request;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/requestservice")

public class RequestService {

	Request requestObj = new Request ();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
	public String readRequests()
	 {
	 return requestObj.readRequests();
	 } 
//insert 
@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)
public String insertRequest(@FormParam("fullname") String fullname,
 @FormParam("email") String email,
 @FormParam("phoneno") String phoneno,
 @FormParam("pAddress") String pAddress,
@FormParam("electricitySupply") String electricitySupply,
@FormParam("purposeofusage") String purposeofusage)
{
 String output = requestObj.insertRequest(fullname,email, phoneno,pAddress,electricitySupply,purposeofusage);
return output;
}

//update
@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)

public String updateRequest(String requestData)
{
//Convert the input string to a JSON object
	
 JsonObject requestObject = new JsonParser().parse(requestData).getAsJsonObject();

 //Read the values from the JSON object
 String requestId = requestObject.get("requestId").getAsString();
 String fullname = requestObject.get("fullname").getAsString();
 String email = requestObject.get("email").getAsString();
 String phoneno = requestObject.get("phoneno").getAsString();
 String pAddress = requestObject.get("pAddress").getAsString();
 String electricitySupply = requestObject.get("electricitySupply").getAsString();
 String purposeofusage = requestObject.get("purposeofusage").getAsString();
 
 String output = requestObj.updateRequest(requestId,fullname, email,phoneno,pAddress,electricitySupply,purposeofusage);

 return output;
}
//delete
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteRequest(String requestData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(requestData, "", Parser.xmlParser());

//Read the value from the element <requestId>
 String requestId = doc.select("requestId").text();
 String output = requestObj.deleteRequest(requestId);
return output;
}


	
}
