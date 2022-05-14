package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Feedback {
	
	private Connection connect() 
	 { 
		Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:6065/request", "root", ""); 
	 } 
	 catch (Exception e) 
	 
	 {
		 e.printStackTrace();
	 } 
	 return con; 
	 
} 

//insert method
	
	public String insertFeedback(String fcname, String date, String feedback) 
{ 
	 String output = ""; 
	 
	 try
	 { 
		 Connection con = connect(); //check db connection to insert
		 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for inserting."; 
		 
	 } 
	 
	 // create a prepared statement
	 String query = " insert into fcservice (`feedbackId`,`fcname`,`date`,`feedback`)"  +
	 " values (?, ?, ?, ?)"; 
	
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, fcname); 
	 preparedStmt.setString(3, date); 
	 preparedStmt.setString(4, feedback); 

	 // execute the statement 
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 String newFeedbacks = readFeedback();
	 output = "{\"status\":\"success\", \"data\": \"" + newFeedbacks + "\"}";
	
}catch (Exception e) 
	 { 
	 	output = "{\"status\":\"error\", \"data\": \"Error while inserting the Details.\"}" ;
	 	System.err.println(e.getMessage()); 
	 } 
	 return output; 
	
}

//read method
public String readFeedback() 
 { 
	 String output = ""; 
	 
	 try
	 	{ 
		 	Connection con = connect(); 
		 			if (con == null) 
	 {
		 				return "Error while connecting to the database for reading.";
	 }

//access the table
	 String query = "select * from fcservice"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
// Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Customer name</th>" +"<th>Date</th>" +"<th>Feedback or Complaint</th>"
    + "<th>Update</th>"
	+"<th>Remove</th></tr>"; 

	
// iterate through the rows in the result set
while (rs.next()) 
{ 
	 String feedbackId = Integer.toString(rs.getInt("feedbackId")); 
	 String fcname = rs.getString("fcname"); 
	 String date = rs.getString("date"); 
	 String feedback = rs.getString("feedback");
	 
	

// Add into the html table
	 
	 output += "<tr><td>"  + fcname + "</td>";
	 output += "<td>" + date + "</td>"; 
	 output += "<td>" + feedback + "</td>";
 
//buttons	 
output += "<td>"
+"<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-warning' data-feedbackid='" + feedbackId + "'>"
+ "</td>"
+"<td>"
+"<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-sm btn-danger' data-feedbackid='" +feedbackId + "'> " 
+"</td></tr>";

} 
	 con.close(); 
	 
// Complete the html table
	 output += "</table>"; 
} 
	 
catch (Exception e) 
	 { 
	 output = "Error while reading the Details"; 
	 System.err.println(e.getMessage()); 
	 } 
	 	
	 return output; 
	
 }

//Update Feedback
public String updateFeedback(String feedbackId, String fcname, String date, String feedback) 
{ 
	 String output = ""; 
	 
	 try
	 { 
		 	Connection con = connect();
		 	
		 	if (con == null) 
		 	{
		 			return "Error while connecting to the database for updating."; 
	 
		 	}
	 
		 
// create a prepared statement
		 	String query = "UPDATE fcservice SET fcname=?, date=?, feedback=?  WHERE feedbackId=?"; 
		
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setString(1, fcname); 
			 preparedStmt.setString(2, date); 
			 preparedStmt.setString(3, feedback); 
			 preparedStmt.setInt(4, Integer.parseInt(feedbackId));
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newFeedbacks = readFeedback();
			 output = "{\"status\":\"success\", \"data\": \"" + newFeedbacks + "\"}";
			 
	 		} 
	catch (Exception e) 
	 
		{ 
			 output =  "{\"status\":\"error\", \"data\":\"Error while updating the Details.\"}";
			 System.err.println(e.getMessage()); 
		} 
	 
		return output; 

} 
	
//Delete Feedbacks

public String deleteFeedback(String feedbackId) 
	 
{ 
	 String output = ""; 

	 try
	 { 
		 Connection con = connect(); 
	 
	 if (con == null) 
	 {
		 return "Error while connecting to the database for deleting."; 
     }
	 
	 // create a prepared statement
	 String query = "delete from fcservice where feedbackId=?"; 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(feedbackId)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 String newFeedbacks = readFeedback();
	 output = "{\"status\":\"success\", \"data\": \"" + newFeedbacks + "\"}";
	 
	 } 
	 
	 catch (Exception e) 
	 { 
		 output =  "{\"status\":\"error\", \"data\":\"Error while Deleting the Details.\"}";
		 System.err.println(e.getMessage()); 
	 } 
	
	 return output; 
	 }

}
