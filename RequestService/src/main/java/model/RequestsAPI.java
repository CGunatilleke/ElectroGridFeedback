package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//for Map
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class RequestsAPI
 */
@WebServlet("/RequestsAPI")
public class RequestsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Request requestObj = new Request();

// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request){
	 
		Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 
	 String[] params = queryString.split("&");
	 
	 for (String param : params){ 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 
		}
	}catch (Exception e)
		 {
		 }
		return map; 

		 
	 }
 public RequestsAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			 throws ServletException, IOException
	{
			String output = requestObj.insertRequest(request.getParameter("fullname"),
			request.getParameter("email"),
			request.getParameter("phoneno"),
			request.getParameter("pAddress"),
			request.getParameter("electricitySupply"),
			request.getParameter("purposeofusage"));
			response.getWriter().write(output);
			}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			//parameter map
		 Map paras = getParasMap(request);
			 
		String output = requestObj.updateRequest(paras.get("hidRequestIDSave").toString(),
				paras.get("fullname").toString(),
				paras.get("email").toString(),
				paras.get("phoneno").toString(),
				paras.get("pAddress").toString(),
				paras.get("electricitySupply").toString(),
				paras.get("purposeofusage").toString());
		
		//sending the output to client
				response.getWriter().write(output);
	}
			
	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		
		//sending values to delete function using map function
		String output = requestObj.deleteRequest(paras.get("requestId").toString());
		
		//send output to client
		response.getWriter().write(output);
	}

}
