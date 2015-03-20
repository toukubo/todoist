package net.enclosing.todoist.auto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.enclosing.todoist.Item;
import net.enclosing.todoist.Label;
import net.enclosing.todoist.Project;
import net.enclosing.todoist.Todoist;

public class Context {
	private static boolean onetime  =false;
	public static void main(String[] args) {
//		Context context = new Context(new Project("102188358","MISCS"));
		Context contextMe2 = new Context();
	}
	public Context(){
		List<Project> projects = Todoist.getProjects();
		for (Project project : projects) {
			new Context(project);
		}
	}

	public Context(Project project) {
		List<Item> items = project.getItems();
		for (Item item : items) {
			
			if(onetime){
				onetime(item);
			}else{
				contextme(item);
				maillabel(item);
				contextLabelling(item);
				appsToContext(item);
				addhow(item);
				subcontextToContext(item);
				if(!item.hasLabel(new Label(".priorityConfirmed"))&&!item.hasLabel(new Label("prioritize",new Integer(329525)))){
					item.addLabel(new Label("prioritize"));
				}else if(item.hasLabel(new Label(".priorityConfirmed"))){
					item.getLabels().remove(Integer.valueOf(new Label("prioritize").getId()));
				}
			}
			if(item.isDirty())
				item.update();
		}
		
	}

	private void addhow(Item item) {
		if( !(item.hasLabel(Label.HOWID)
		|| item.hasLabel(Label.AIRID))){
			return ;
		}
		
		if(!containsHowStrings(item)){
			if(!item.hasLabel(Label.ADDHOWID)){
				item.addLabel(Label.ADDHOWID);
			}
		}else{
			if(item.hasLabel(Label.ADDHOWID)){
				item.getLabels().remove(Label.ADDHOWID);
			}
		}
	}
	private boolean containsHowStrings(Item item) {
		String[] howStrings = new String[]{"how to","how can we","どうすれば、"};
		for (String howString : howStrings) {
			item.getContent().contains(howString);
		}
		return false;
	}
	private void onetime(Item item) {
		item.getLabels().remove(Label.ADDHOWID);
		item.setDirty(true);
//		if(item.hasLabel(new Label("prioritize",new Integer(329525))))
//			item.getLabels().remove(Integer.valueOf(new Label("prioritize").getId()));
	}
	private void appsToContext(Item item) {
//		if(item.hasContext()) return;
		if(item.getAppContexted()!=null){
			item.addLabel(Label.AIRID);
		}
	}
	private void subcontextToContext(Item item) {
//		if(item.hasContext()) return;
		if(item.getSubContexted()!=null){
			item.addLabel(Label.SUBCONTEXTMAP.get(item.getSubContexted()));
		}
	}
	private void temporalRemoval(Item item) {
		item.getLabels().remove(Label.AIRID);
		item.setDirty(true);

	}

	private void contextLabelling(Item item) {
		if(item.hasContext()&&!item.containsSharps()) return;
		final String regex = "#.*$";
		final String regex2 = "#.*[\\s]";
	    Pattern p = Pattern.compile(regex);
	    Pattern p2 = Pattern.compile(regex2);
	    Matcher m1 = p.matcher(item.getContent());
	    Matcher m2 = p2.matcher(item.getContent());
	    System.err.println(item.getContent());
	    String group = null;
	    if (m1.find()){
	    	System.out.println("マッチします");
	    	group = m1.group();
	    }else if(m2.find()){
	    	group = m2.group();
	    }else{
	      System.out.println("マッチしません");
	    }
	    if(group!=null){
	    	item.setContent(item.getContent().replaceAll(group, ""));
	    	item.addLabel(new Label(group.replaceAll("#", "")));
	    	item.addLabel(group.replaceAll("#", ""));
	    }
	}

	private void maillabel(Item item) {
		if(item.getContent().contains("Fw: ") && !item.hasLabel(new Label("mail"))){
			item.addLabel(Label.CLIENT);
			item.addLabel(new Label("mail"));
			item.addLabel(new Label("incomming"));
		}
	}

	private void subcontextme(Item item) {
		
	}

	private void contextme(Item item) {
		if(!item.hasContext() && !item.hasLabel(new Label("contextme"))){
			item.addLabel(Label.CONTEXTMEID);
		}else if(item.hasContext() &&item.hasLabel(new Label("contextme"))){
			item.getLabels().remove(Label.CONTEXTMEID);
			item.setDirty(true);
		}
	}
}
