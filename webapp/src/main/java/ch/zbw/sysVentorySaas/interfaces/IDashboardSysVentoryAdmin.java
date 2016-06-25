package ch.zbw.sysVentorySaas.interfaces;

import ch.zbw.sysVentorySaas.App.helpers.JobStatus;

public interface IDashboardSysVentoryAdmin {
	public JobStatus getStatusLetzterJob(int idCompany);
	public int getAnzahlAusfaelle(int idCompany);
	public int getAnzahlScans(int idCompany);
}
