package net.enclosing.todoist.auto;

import java.util.List;

import net.enclosing.todoist.Project;
import net.enclosing.todoist.Todoist;

public class ToCloudDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ToCloudDB();

	}
	public ToCloudDB(){
	
		List<Project> projects =  Todoist.getProjects();
		net.enclosing.list.List list = new net.enclosing.list.List();
		list.writeList(projects,Project.class);
		
	}
}
