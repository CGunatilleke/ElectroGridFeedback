package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*Servlet Implementation class feedbackAPI */

@WebServlet("/FeedbacksAPI")
public class FeedbacksAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
				Feedback feedbackObj = new Feedback();
		
protected void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException
	{
		String output = feedbackObj.insertFeedback(
						request.getParameter("fcname"),
						request.getParameter("date"),
						request.getParameter("feedback"));
						
				//sending output to client
						response.getWriter().write(output);
	}

public FeedbacksAPI() {
	super();
        // TODO Auto-generated constructor stub
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	response.getWriter().append("Served at: ").append(request.getContextPath());
}

//Convert request parameters to a Map
private static Map getParasMap(HttpServletRequest request)
{

	Map<String, String> map = new HashMap<String, String>();
	try
	{
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		String queryString = scanner.hasNext() ?
							 scanner.useDelimiter("\\A").next() : "";
		scanner.close();
		
		String[] params = queryString.split("&");
		for (String param : params)
		{ 
			 String[] p = param.split("=");
			 map.put(p[0], p[1]);
		 }
	 }
	catch (Exception e)
	{
	}
			
	return map;

}

protected void doPut(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException
{
	//parameter map
	Map paras = getParasMap(request);
	
	//getting values from the map and sending to update function
	String output = feedbackObj.updateFeedback(paras.get("hidFeedbackIDSave").toString(),
		paras.get("fcname").toString(),
		paras.get("date").toString(),
		paras.get("feedback").toString());
		
	//sending the output to client
	response.getWriter().write(output);

}
		
protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException
		
{
	//parameter map
	Map paras = getParasMap(request);
	
	//getting values from the map and sending to delete function
	String output = feedbackObj.deleteFeedback(
	paras.get("feedbackId").toString());
	
	//sending the output to client
	response.getWriter().write(output);
}



 

}
