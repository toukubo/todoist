package net.enclosing.todoist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.SysexMessage;

public class Project {
	public static final String INBOXID = "102178079";
	private static final Object CLIENT = "Client";
	private String name = "";
	private String id = "";
	private Integer indent = 0;
	public static final List<Project> projects = Todoist.getProjects();;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Project(String id,String name){
		this.id = id;
		this.name = name;
	}
	
	
	public Integer getIndent() {
		return indent;
	}
	public void setIndent(Integer indent) {
		this.indent = indent;
	}
	public List<Item> getItems(){
		
		final String apimethod = "getUncompletedItems";
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", this.getId());
		try {
			return  Json.getItems(apimethod,params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean isClient(){
		return !isTop() && getRoot().getName().equals(Project.CLIENT);
	}
	
	public Project getRoot() {
		Project root = getParent(getIndent()-1);
		if(root==null) return this;
		return root;
	}
	public boolean isTop() {
		return this.getIndent().intValue()==1;
	}
	public Project getParent(int level){
		final int currentindent = this.getIndent();
		if(currentindent - level < 1 || level > 3)
			return null;
		
		final int targetIndent = currentindent - level;
		final int indexOfThis = indexOf(this);
		int index = indexOfThis;
		while(index >= 0){
			if(projects.get(index).getIndent()==targetIndent)
				return projects.get(index);
			index--;
		}
		return null;
		
	}
	public Project getParent() {
		return getParent(1);
	}
	private int indexOf(Project project) {
		List<Project> projects = this.projects;
		for (Project project2 : projects) {
			if(project2.getName().equals(project.getName()))
				return projects.indexOf(project2);
		}
		return -1;
	}
	
	
	
	public static void main(String[] args) {
		List<Project> projects = Todoist.getProjects();
		for (Project project : projects) {
			tempPrintBigItems(project);
//			System.err.println(project.getId() + " : " + project.getName() + ":" + project.getIndent());
		}
	}
	private static void tempPrintBigItems(Project project) {
		List<Item> items = project.getItems();
		for (Item item : items) {
			if(item.getContent().length()>100){
				System.err.println("big item found");
				System.err.println(item.getContent());
			}
		}
	}
}
