package net.enclosing.todoist;

public class Note {
	private String content = "";
	private String item_id = "";
	private String id = "";
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String note_id) {
		this.id = id;
	}
	public void updateNote() {
		try {
			Json.updateNote(this.getContent(), this.getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
