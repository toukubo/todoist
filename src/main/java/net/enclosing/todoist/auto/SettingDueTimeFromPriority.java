package net.enclosing.todoist.auto;

import java.util.List;

import net.enclosing.todoist.Item;
import net.enclosing.todoist.Label;
import net.enclosing.todoist.Project;

public class SettingDueTimeFromPriority {

	public static void main(String[] args) {
//		Project inbox = new Project(Project.INBOXID,"inbox");
//		Prioritize prioritize = new Prioritize(inbox);
		SettingDueTimeFromPriority settingDueTimeFromPriority  = new SettingDueTimeFromPriority();
	}
	public SettingDueTimeFromPriority(){
		List<Project> projects = Project.projects;
		for (Project project : projects) {
			new SettingDueTimeFromPriority(project);
		}
		
	}
	public SettingDueTimeFromPriority(Project project){
		
		List<Item> items = project.getItems();
		for (Item item : items) {
			if((item.getPriority().equals("3")
					|| item.getPriority().equals("2"))
					&& item.getDate_string() !=null 
					&& !item.getDate_string().equals("") 
					&& !item.getDate_string().contains("@")){
				System.err.println(item.getDate_string());
				item.setDate_string(item.getDate_string()+ " @ 20:00");
				item.updateDue();
			}
			if(item.getPriority().equals("4")
					&& item.getDate_string() !=null 
					&& !item.getDate_string().equals("") 
					&& !item.getDate_string().contains("@")){
				System.err.println(item.getDate_string());
				item.setDate_string(item.getDate_string()+ " @ 13:00");
				item.updateDue();

			}
				
			
		}
	}
}
