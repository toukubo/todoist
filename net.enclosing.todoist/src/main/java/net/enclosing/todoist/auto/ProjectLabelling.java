package net.enclosing.todoist.auto;

import java.util.List;

import net.enclosing.todoist.Item;
import net.enclosing.todoist.Json;
import net.enclosing.todoist.Label;
import net.enclosing.todoist.Project;
import net.enclosing.todoist.Todoist;

public class ProjectLabelling {
	public static void main(String[] args) {
		new ProjectLabelling();
	}
	public ProjectLabelling(Project project){
		
		final List<Item> items = project.getItems();
		for (Item item : items) {
			Label projectLabel = new Label(project.getName());
			if(!item.hasLabel(projectLabel)){
				item.addLabel(project.getName());
				item.addLabel(new Label(project.getName()));
			}

			if(item.isDirty())
				item.update();
		}
	}

	public ProjectLabelling() {
		List<Project> projects =  Todoist.getProjects();
		List<Label> labels = Label.LABELS;
		for (Project project : projects) {
			boolean projectlabelExist = false;
			for (Label label : labels) {
				if(label.getName().equalsIgnoreCase(project.getName())){
					projectlabelExist = true;
				}
			}
			if(!projectlabelExist){
				Json.addLabel(project.getName());
			}
			if(!project.getId().equals(Project.INBOXID)){
				new ProjectLabelling(project);
			}
		}
	}
}
