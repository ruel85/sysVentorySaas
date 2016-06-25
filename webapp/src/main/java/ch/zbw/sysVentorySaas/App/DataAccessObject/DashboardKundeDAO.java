package ch.zbw.sysVentorySaas.App.DataAccessObject;

import ch.zbw.sysVentorySaas.App.helpers.JobStatus;
import ch.zbw.sysVentorySaas.interfaces.IDashBoardKunde;

public class DashboardKundeDAO implements IDashBoardKunde{

	@Override
	public JobStatus getLetzterJobStatus() {
		return JobStatus.Fehler;
	}

	@Override
	public int getAnzahlAusfaelle() {
		return 10;
	}

	@Override
	public int getAnzahlScanJobs() {
		return 35;
	}

}
