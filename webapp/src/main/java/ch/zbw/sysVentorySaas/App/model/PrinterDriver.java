package ch.zbw.sysVentorySaas.App.model;

public class PrinterDriver {

	private int idPrinterDriver;
	private String name;
	
	public PrinterDriver()
	{
	}
	
	public PrinterDriver(String name)
	{
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdPrinterDriver() {
		return idPrinterDriver;
	}

	public void setIdPrinterDriver(int idPrinterDriver) {
		this.idPrinterDriver = idPrinterDriver;
	}
}
