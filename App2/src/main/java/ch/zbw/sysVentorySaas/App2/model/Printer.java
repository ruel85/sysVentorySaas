package ch.zbw.sysVentorySaas.App2.model;

public class Printer {

	private int idPrinter;
	private String name;
	private String description;
	
	public Printer(String name, String description) {
		this.name=name;
		this.description=description;
	}
	
	public Printer(){
	}

	public int getIdPrinter() {
		return idPrinter;
	}

	public void setIdPrinter(int idPrinter) {
		this.idPrinter = idPrinter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
