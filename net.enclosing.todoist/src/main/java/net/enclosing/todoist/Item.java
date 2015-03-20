package net.enclosing.todoist;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

public class Item {
	private String content = "";
	private String project_id = "";
	private String id = "";
	private String checked = "";
	private String priority = "";
	private boolean priorityConfirmed = false;
	private boolean smallEnough = false;
	private boolean workflow = false;
	private boolean taggedEnough = false;
	private boolean nonDelegatable = false;
	private String date_string = "";
	
	public String getDate_string() {
		return date_string;
	}
	public void setDate_string(String date_string) {
		this.date_string = date_string;
	}
	private List<Integer> labels = new ArrayList<Integer>();
	private boolean dirty;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
		this.setDirty(true);
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
		this.setDirty(true);
	}
	
	
	

	public boolean isSmallEnough() {
		return smallEnough;
	}
	public void setSmallEnough(boolean smallEnough) {
		this.smallEnough = smallEnough;
	}
	public boolean isWorkflow() {
		return workflow;
	}
	public void setWorkflow(boolean workflow) {
		this.workflow = workflow;
		this.setDirty(true);
	}
	public boolean isTaggedEnough() {
		return taggedEnough;
	}
	public void setTaggedEnough(boolean taggedEnough) {
		this.taggedEnough = taggedEnough;
		this.setDirty(true);

	}
	public boolean isNonDelegatable() {
		return nonDelegatable;
	}
	public void setNonDelegatable(boolean nonDelegatable) {
		this.nonDelegatable = nonDelegatable;
		this.setDirty(true);

	}
	public void setPriorityConfirmed(boolean priorityConfirmed) {
		this.priorityConfirmed = priorityConfirmed;
		this.setDirty(true);
	}
	public List<Integer> getLabels() {
		return labels;
	}
	public void setLabels(List<Integer> labels) {
		this.labels = labels;
		this.setDirty(true);
	}
	public void addLabel(String label){
		this.content = Label.labelingByString(label) + this.content;
		this.setDirty(true);
	}
	public void addLabel(Label label){
		this.addLabel(Integer.valueOf(label.getId()));
		this.setDirty(true);
	}
	public void addLabel(Integer label){
		this.labels.add(label);
		this.setDirty(true);
	}

	public boolean hasContext(){
		List<Integer> labels = this.labels;
		for (Integer labelid : labels) {
			if(Label.CONTEXTIDS.contains(labelid)) return true;
		}
		return false;
	}
	public static List<Item> getItems(String apimethod,Map<String, String> params) throws Exception {
		params = params==null?new HashMap<String, String>():params;

		List<Item> items =  Json.getItems(apimethod, params);// 
		Utils.printItems(items);

		return items;
	}
	public void update()  {
		try {
			final String updateapimethod = "updateItem";
			Map<String, String> updateLabelParams = new HashMap<String, String>();
			updateLabelParams.put("id", getId());
			updateLabelParams.put("content", Utils.encode(getContent()));
			updateLabelParams.put("priority", getPriority());
			updateLabelParams.put("labels",Json.getLabelsString(this.getLabels()));
			Json.postItem(updateapimethod,updateLabelParams);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}	
	public void updateDue()  {
		try {
			final String updateapimethod = "updateItem";
			Map<String, String> updateLabelParams = new HashMap<String, String>();
			updateLabelParams.put("id", getId());
			updateLabelParams.put("date_string", URLEncoder.encode(getDate_string(), "UTF-8"));
			Json.postItem(updateapimethod,updateLabelParams);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private String getDateString() {
		return "";
//		return ;
	}
	private Integer labelsContains(List<Integer> list){
		boolean flag = false;
		for (Integer labelid : this.getLabels()) {
			if(list.contains(labelid)){
				flag = true;
				return labelid;
			}
		}
		return null;

	}
	public Integer getSubContexted(){
		return labelsContains(Label.SUBCONTEXTIDS);
	}
	public Integer getAppContexted(){
		return labelsContains(Application.APPLICATIONS);
	}

	
	
	public boolean hasLabels() {
		return getLabels().size()>0;
	}
	public boolean isPriorityConfirmed() {
		return this.priorityConfirmed;
	}
	public void setDirty(boolean b) {
		this.dirty = true;
	}
	public boolean isDirty() {
		return dirty;
	}
	public boolean hasLabel(Label label) {
		return labels.contains(Integer.valueOf(label.getId()));
	}
	public boolean hasProjectLabels(Project project) {
		if(this.hasLabel(new Label(project.getName())) && project.getName().contains(" ")) return true;
		
		return false;
	}
	public boolean hasLabel(Integer labelid) {
		return labels.contains(labelid);
	}
	public Collection<Note> getNotes(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("item_id", this.getId());
		Type stringType = new TypeToken<Collection<Note>>(){}.getType();
		Collection<Note> notes = (Collection<Note>)Json.getObjects("getNotes", map , stringType);;
		return notes;
	}
	public String add() {
		try {
			final String updateapimethod = "addItem";
			Map<String, String> updateLabelParams = new HashMap<String, String>();
			updateLabelParams.put("content", Utils.encode(getContent()));
			if(!getProject_id().equals("")){
				updateLabelParams.put("project_id", getProject_id());
			}
			if(!getPriority().equals("")){
				updateLabelParams.put("priority", getPriority());

			}
			if(this.getLabels().size()!=0){
				updateLabelParams.put("labels",Json.getLabelsString(this.getLabels()));
			}
			return Json.postItem(updateapimethod,updateLabelParams);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public void addNote(String noteContent){
		try {
			Json.addNote(noteContent, this.id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	public boolean containsSharps() {
		return this.getContent().contains("#");
	}
}
