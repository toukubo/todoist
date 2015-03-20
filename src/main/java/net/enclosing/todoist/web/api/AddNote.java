package net.enclosing.todoist.web.api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.enclosing.todoist.Item;
import net.enclosing.todoist.Todoist;

@WebServlet("/AddNote")
public class AddNote extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
        	request.setCharacterEncoding("UTF-8");

        	response.setHeader("Access-Control-Allow-Origin", "*");
//        	String query = "reminderOrder:* -reminderTime:day+1 -reminderDoneTime:day+1";
        	String itemId = request.getParameter("id");
        	Item item = Todoist.getItem(itemId); 
        	String noteContent = request.getParameter("noteContent");
        	String queryString = URLDecoder.decode(request.getQueryString(), "UTF-8");
System.err.println(queryString);
        	System.err.println(noteContent);
			item.addNote(URLEncoder.encode(noteContent ));


//    		Conversion conversion = new Conversion();
    		
    		
    		//json
    		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
    		writer.write("success");
    		writer.flush();
    		writer.close();
    		
    		
    		//mustache 
//            MustacheFactory mf = new DefaultMustacheFactory();
//            Mustache mustache = mf.compile("views/index.html");
//
//        	StringWriter stringWriter = new StringWriter();
        	
//        	mustache.execute(new PrintWriter(stringWriter), map).flush();
        	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
//		response.getWriter().write(conversion.getResult());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}