package net.enclosing.todoist.auto;

import java.util.List;

import net.enclosing.todoist.Item;
import net.enclosing.todoist.Label;
import net.enclosing.todoist.Project;

public class Prioritize {
	public static void main(String[] args) {
//		Project inbox = new Project(Project.INBOXID,"inbox");
//		Prioritize prioritize = new Prioritize(inbox);
		Prioritize prioritize = new Prioritize();
	}
	public Prioritize(){
		List<Project> projects = Project.projects;
		for (Project project : projects) {
			new Prioritize(project);
		}
		
	}
	public Prioritize(Project project){
		
		List<Item> items = project.getItems();
		boolean isClient = project.isClient();
		for (Item item : items) {
			if(item.getContent().contains("!p2")){
				item.setContent(item.getContent().replaceAll("!p2", ""));
				item.setPriority("3");
			}
			if(item.getPriority().equals("1")){
				item.setPriority("3");
			}
				
			if(isClient)
				clientItemToOne(item);

			if(item.isDirty())
				item.update();
			
		}
	}
	private void clientItemToOne(Item item) {
		if(!item.getPriority().equals("4")){
			item.setPriority("4");
			item.addLabel(new Label(".priorityConfirmed"));
			item.getLabels().remove(Integer.valueOf(new Label("prioritize").getId()));
		}else if(!item.hasLabel(new Label(".priorityConfirmed"))){
			item.addLabel(new Label(".priorityConfirmed"));
			item.getLabels().remove(Integer.valueOf(new Label("prioritize").getId()));
		}
	}
}
