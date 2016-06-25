package ch.zbw.sysVentorySaas.App.DataAccessObject;

import ch.zbw.sysVentorySaas.App.helpers.JobStatus;
import ch.zbw.sysVentorySaas.interfaces.IDashboardSysVentoryAdmin;

public class DashboardDAO implements IDashboardSysVentoryAdmin {

	@Override
	public JobStatus getStatusLetzterJob(int idCompany) {
		return JobStatus.Erledigt;
	}

	@Override
	public int getAnzahlAusfaelle(int idCompany) {
		return 0;
	}

	@Override
	public int getAnzahlScans(int idCompany) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
