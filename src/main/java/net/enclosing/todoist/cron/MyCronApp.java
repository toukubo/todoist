package net.enclosing.todoist.cron;

import it.sauronsoftware.cron4j.Scheduler;

public class MyCronApp {

	  /**
	   * @param args
	   */
	  public static void main(String[] args) {
	    MyCronApp app = new MyCronApp();
	    try {
	      app.schedulerSimple();
	      System.out.println("Press Ctrl+C to stop.");
	      Thread.sleep(100000000);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  public  void schedulerSimple() {
	    Scheduler scheduler = new Scheduler();
	    // every minute.
	    scheduler.schedule("*/5 * * * *", new CronTask());
	    // start cron4j scheduler.
	    scheduler.start();
	  }
	}
