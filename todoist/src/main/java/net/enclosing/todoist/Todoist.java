package net.enclosing.todoist;

import java.util.List;

import net.enclosing.todoist.auto.Context;
import net.enclosing.todoist.auto.Prioritize;
import net.enclosing.todoist.auto.ProjectLabelling;

public class Todoist {
	public static final String APIURL = "http://todoist.com/API/";
	public static final String QUERY = "query";
	public static final String QUERYPARAM = "queries";

	public Todoist() {
		new Context();
		new ProjectLabelling();
		new Prioritize();
	}

	public static void syncFromCloudDB(){
		net.enclosing.list.List list = new net.enclosing.list.List();
		List<Item> items = list.list(Item.class);
		for (Item item : items) {
			System.err.println(item.getContent());
			if(item.getId()==null || item.getId().equals("")){
				item.add();
			}else{
				item.update();
			}
		}
	}
	public static void main(String[] args) {
//		Todoist.syncFromCloudDB();
		new Todoist();
	}

	public static List<Project> getProjects() {
		return Json.getProjects("getProjects");
		
	}
	
}
