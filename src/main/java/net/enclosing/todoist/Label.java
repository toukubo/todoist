package net.enclosing.todoist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Label {
	public static final List<Label> LABELS = Json.getLabels(null, null);
	public static final String CONTEXTME = "contextme";
	public static final Integer CONTEXTMEID = new Integer(329325);
	public static final String CLIENT = "client";
//	public static final Integer CLIENTID = new Integer(329325);
	
	private static final String PAD = "PAD";
	private static final String AIR = "AIR";
	private static final String PKNK = "PKNK";
	private static final String HOME = "HOME";
	private static final String TKP = "TKP";
	private static final String SUN = "SUN";
	
	public static final Integer AIRID = new Integer(329385);
	public static final Integer PKNKID = new Integer(329388);
	public static final Integer SUNID = new Integer(329389);
	public static final Integer TKPID =  new Integer(329390);
	public static final Integer HOMEID =  new Integer(329318);
	public static final Integer PADID =  new Integer(329387);
	public static final Integer ADDHOWID =  new Integer(331131);
	
	public static final Integer PRIORITYCONFIRMED =  new Integer(329076);

	
	
	public static final String[] CONTEXTS = new String[]{AIR,PKNK,SUN,TKP,HOME};

	private static final String HOW = "HOW";
	private static final String NODEPAD = "NODEPAD";
	
	public static final List<Integer> SUBCONTEXTIDS = new ArrayList<Integer>();
	
	public static final Integer OUTLININGID =  new Integer(330250);
	public static final Integer OUTLINEDID =  new Integer(330333);
	public static final Integer CODEID =  new Integer(329679);
	public static final Integer HOWID = new Integer(329393);
	public static final Integer Q =  new Integer(330250);
	public static final Integer MODELINGID = new Integer(330248);
	public static final Integer CHARTID = new Integer(330246);
	public static final Integer WAITID = new Integer(330648);
	public static final Integer NODELOGID = new Integer(329439);
	public static final Integer COMPARISONID = new Integer(329467);
	public static final Integer DELEGATEDID = new Integer(330208);


	public static final List<Integer> CONTEXTIDS = new ArrayList<Integer>();
	public static final Map<String, Integer> CONTEXTMAP = new HashMap<String, Integer>();
	public static final Map<Integer, Integer> SUBCONTEXTMAP = new HashMap<Integer, Integer>();
	
	public static final Label[] CONTEXTLABLES = new Label[]{new Label(HOW,HOWID),new Label(AIR,AIRID),
												new Label(PKNK,PKNKID),new Label(SUN,SUNID),new Label(TKP,TKPID),new Label(HOME,HOMEID)};
	static{
		
		CONTEXTMAP.put(AIR, AIRID);
		CONTEXTMAP.put(HOW, HOWID);
		CONTEXTMAP.put(PKNK, PKNKID);
		CONTEXTMAP.put(SUN, SUNID);
		CONTEXTMAP.put(TKP, TKPID);
		CONTEXTMAP.put(HOME, HOMEID);
		CONTEXTMAP.put(PAD, PADID);
		
		CONTEXTIDS.add(AIRID);
		CONTEXTIDS.add(PADID);
		CONTEXTIDS.add(PKNKID);
		CONTEXTIDS.add(SUNID);
		CONTEXTIDS.add(TKPID);
		CONTEXTIDS.add(HOMEID);
		
		SUBCONTEXTIDS.add(OUTLININGID);
		SUBCONTEXTMAP.put(OUTLININGID, PADID);

		SUBCONTEXTIDS.add(HOWID);
		SUBCONTEXTMAP.put(HOWID, PADID);
		
//		SUBCONTEXTIDS.add(Application.NODEPADID);
//		SUBCONTEXTMAP.put(Application.NODEPADID, AIRID);
		
		SUBCONTEXTIDS.add(MODELINGID);
		SUBCONTEXTMAP.put(MODELINGID, CHARTID);
		SUBCONTEXTMAP.put(MODELINGID, PADID);

		SUBCONTEXTIDS.add(Q);
		SUBCONTEXTMAP.put(Q, AIRID);
		
		SUBCONTEXTIDS.add(OUTLINEDID);
		SUBCONTEXTMAP.put(OUTLINEDID, AIRID);
		
		SUBCONTEXTIDS.add(CODEID);
		SUBCONTEXTMAP.put(CODEID, Application.ECLIPSEID);
		
		SUBCONTEXTIDS.add(WAITID);
		SUBCONTEXTMAP.put(WAITID, NODELOGID);
		
		SUBCONTEXTIDS.add(NODELOGID);
		SUBCONTEXTMAP.put(NODELOGID, Application.NODEPADID);
		
		SUBCONTEXTIDS.add(COMPARISONID);
		SUBCONTEXTMAP.put(COMPARISONID, Application.NODEPADID);
		SUBCONTEXTMAP.put(COMPARISONID, PADID);

		SUBCONTEXTIDS.add(DELEGATEDID);
		SUBCONTEXTMAP.put(DELEGATEDID, AIRID);

	}

	private String id = "";
	private String name = "";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Label(String name){
		this.name=name;
		this.id = getLabelId(name).toString();
	}
	public Label(String name,Integer ID){
		this.name=name;
		this.id = ID.toString();
	}

	
	public static Integer getLabelId(String name) {
		System.err.println(CONTEXTMAP);
		if(CONTEXTMAP.containsKey(name))
			return CONTEXTMAP.get(name);
		if(Json.getLabel(name)!=null)
			return Integer.valueOf(Json.getLabel(name).getId());
		return new Integer(0);
	}
	public static void main(String[] args) {
		Json.getLabels(null,null);
	}
	public static String labelingByString(String label) {
		return "@"+label + " " ;
	}
	public String labelingString() {
		return "@"+ this.getName() + " " ;
	}
}
