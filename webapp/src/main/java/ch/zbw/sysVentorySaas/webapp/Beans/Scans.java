package ch.zbw.sysVentorySaas.webapp.Beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ch.zbw.sysVentorySaas.webapp.Util.SessionUtils;

@ManagedBean
@SessionScoped
public class Scans implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	public String checkScans()
	{
		return "Scans";
	}
}
