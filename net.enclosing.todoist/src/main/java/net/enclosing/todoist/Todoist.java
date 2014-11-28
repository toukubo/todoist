package net.enclosing.todoist;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Collections2;
import com.google.gson.reflect.TypeToken;

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
//		new Todoist();
		allItems();
	}

	public static List<Project> getProjects() {
		return Json.getProjects("getProjects");
		
	}
	public static Collection<Item> find(String query, Collection<Item> allItems){
		Collection<Item> foundItems = Collections2.filter(allItems, new Equals(query));
		return foundItems;
	}

	public static Collection<Item> allItems() {
		Collection<Item> allItems  = new ArrayList<Item>();
		List<Project> projects = Project.projects;
		for (Project project : projects) {
			allItems.addAll(project.getItems());
			allItems.addAll(project.getAllCompletedItems());
		}
		return allItems ;
	}

	public static Collection<Item> getItems() {
		Collection<Item> allItems  = new ArrayList<Item>();
		List<Project> projects = Project.projects;
		for (Project project : projects) {
			allItems.addAll(project.getItems());
		}
		return allItems ;
	}

	public static Item getItem(String itemId) {
		Map<String, String> params = new HashMap<String, String>();
		
		try {
			params.put("ids", "["+itemId+"]");
			String apimethod = "getItemsById";
			return  Json.getItems(apimethod ,params).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
}
