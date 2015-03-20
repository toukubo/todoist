package net.enclosing.todoist.cron;

import java.util.Date;

import net.enclosing.todoist.auto.ProjectLabelling;
import net.enclosing.todoist.auto.SettingDueTimeFromPriority;

public class CronTask implements Runnable {

	public void run() {
		System.out.println(new Date()+": Hello cron4j!");
		new ProjectLabelling();
		new SettingDueTimeFromPriority();
	}

}
