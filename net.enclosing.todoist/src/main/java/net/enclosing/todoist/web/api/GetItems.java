package net.enclosing.todoist.web.api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;
import net.enclosing.todoist.Item;
import net.enclosing.todoist.Project;
import net.enclosing.todoist.Todoist;

@WebServlet("/GetItems")
public class GetItems extends HttpServlet{

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
        	response.setHeader("Access-Control-Allow-Origin", "*");
        	final String projectName = request.getParameter("project");
        	
    		Map<String, Object> map = new HashMap<String,Object>();
    		Collection<Item> items = null;
    		if(projectName==null || projectName.equals("")){
    			items = Todoist.getItems();;
    		}else{
    			Project project =  Project.findProject(projectName);
    			items = project.getItems();
    		}
            
			map.put("items", items );

//    		Conversion conversion = new Conversion();
    		System.err.println(map);
    		
    		
    		//json
    		OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
    		JSON.encode(map,writer);

    		writer.flush();
    		writer.close();
        	
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
