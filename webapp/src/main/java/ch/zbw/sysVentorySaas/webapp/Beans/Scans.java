package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.ArrayList;
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

	private List<ScanJob> scans;

	public String checkScans() {
		return "Scans";
	}

	public List<ScanJob> getScans() {
		ScanJobDAO scansDao = new ScanJobDAO();
//		List<ScanJob> allJobs = scansDao.getAllScanJobs();
//		int userCompany = SessionUtils.getUser().getCompany().getIdCompany();
//		for (ScanJob job : allJobs) {
//			//if (job.getIdCompany() == userCompany) {
//				this.scans.add(job);
//			//}
//		}
		this.scans = scansDao.getAllScanJobs();

		return this.scans;
	}
}
