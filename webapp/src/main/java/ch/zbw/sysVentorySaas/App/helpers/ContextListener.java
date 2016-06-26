package ch.zbw.sysVentorySaas.App.helpers;

import java.util.Timer;

import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanSettingDAO;

@WebListener
public class ContextListener implements ServletContextListener {
	private boolean initializeScheduler = true; // nur 1 Application-Server darf den Scheduler initialisieren!
	private TimerManager tm;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Starting Scheduler up!");
        if(initializeScheduler) {
        	try {
				tm = new TimerManager(ScanSettingDAO.getAllScanSettings(), false); // false wenn nicht im JUnit!
	    		Timer myTimer = new Timer();
	    		myTimer.schedule(tm, 0, 1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
        tm.cancel();
        tm = null;
    }
}