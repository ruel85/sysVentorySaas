package ch.zbw.sysVentorySaas.interfaces;

import ch.zbw.sysVentorySaas.App.helpers.JobStatus;

public interface IDashBoardKunde {

	public JobStatus getLetzterJobStatus ();
	public int getAnzahlAusfaelle();
	public int getAnzahlScanJobs();

}
