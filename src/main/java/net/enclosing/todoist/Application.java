package net.enclosing.todoist;

import java.util.ArrayList;
import java.util.List;

public class Application {
	public static final Integer NODEPADID = new Integer(329386);
	public static final Integer FLICKRID = new Integer(329487);
	public static final Integer EVERNOTEID = new Integer(329447);
	public static final Integer TODOISTID = new Integer(329417);
	public static final Integer BASECAMPID = new Integer(329417);
	
	public static final List<Integer> APPLICATIONS = new ArrayList<Integer>();
	public static final Integer ECLIPSEID = new Integer(329496);
	static{
//		Application.APPLICATIONS.add(NODEPADID);
		Application.APPLICATIONS.add(FLICKRID);
		Application.APPLICATIONS.add(EVERNOTEID);
		Application.APPLICATIONS.add(TODOISTID);
		Application.APPLICATIONS.add(BASECAMPID);
		Application.APPLICATIONS.add(ECLIPSEID);
	}
}
