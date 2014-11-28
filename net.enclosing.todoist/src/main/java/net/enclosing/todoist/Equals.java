package net.enclosing.todoist;

import com.google.common.base.Predicate;

public class Equals implements Predicate<Item> {

	
	private String query;

	public Equals(String query) {
		this.query = query;
	}
	public boolean apply(Item item) {
		if(this.query.length()>50 && item.getContent().length()>50){
			return item.getContent().substring(0,50).equals(this.query.substring(0,50));
		}
		return item.getContent().equals(this.query);
	}
}
