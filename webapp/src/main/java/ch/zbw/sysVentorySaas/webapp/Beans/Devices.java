package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;
import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.ScanJobDAO;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.ScanJob;

@ManagedBean
@SessionScoped
public class Devices implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private int scanId;

	private List<Device> devices;

	public String getDevicesByScanJob(int scanId) {
		this.scanId = scanId;
		DeviceDAO devicesDao = new DeviceDAO();
		this.devices = devicesDao.getDevicesByScanJob(scanId);
		return "Devices";
	}

	public List<Device> getDevices() {
		return this.devices;
	}

}
