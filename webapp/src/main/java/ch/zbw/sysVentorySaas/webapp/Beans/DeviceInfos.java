package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ch.zbw.sysVentorySaas.App.DataAccessObject.DeviceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.NetworkInterfaceDAO;
import ch.zbw.sysVentorySaas.App.DataAccessObject.OperatingSystemDAO;
import ch.zbw.sysVentorySaas.App.model.Device;
import ch.zbw.sysVentorySaas.App.model.NetworkInterface;
import ch.zbw.sysVentorySaas.App.model.OperatingSystem;

@ManagedBean
@SessionScoped
public class DeviceInfos implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;

	private int idDevice;

	private List<NetworkInterface> interfaces;
	private List<OperatingSystem> operatingSystem;

	public String getDeviceInfosByDevice(int idDevice) {
		this.setIdDevice(idDevice);
		this.interfaces = NetworkInterfaceDAO.getNetworkInterfacesByDeviceId(idDevice);
		this.setOperatingSystem(OperatingSystemDAO.getOperatingSystemByIdDevice(idDevice));
		return "DeviceInfos";
	}

	public List<NetworkInterface> getInterfaces() {
		return this.interfaces;
	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public List<OperatingSystem> getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(List<OperatingSystem> operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
}
