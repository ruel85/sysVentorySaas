package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.model.ScanJob;

@ManagedBean
@SessionScoped
public class Scans implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String startTime;
	private String endTime;
	private String statusName;
	private String statusDescription;
	private List<ScanJob> scans;
	
	public String checkScans()
	{
		return "Scans";
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public List<ScanJob> getScans() {
		ScanJobDAO scansDao = new ScanJobDAO();
		this.scans = scansDao.getAllScanJobs();
		return this.scans;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getStatusName() {
		return statusName;
	}
}
