package ch.zbw.sysVentorySaas.App.model;

public class Processor {
	
	private int idProcessor;
	private String name;
//	private String manufacturer;
	private int cores;
	private String frequency;

	private Device device;
	
	public Processor(String name, int cores, String frequency) {
		this.name = name;
//		this.manufacturer=manufacturer;
		this.cores=cores;
		this.frequency=frequency;
	}
	
	public Processor(){
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public int getIdProcessor() {
		return idProcessor;
	}

	public void setIdProcessor(int idProcessor) {
		this.idProcessor = idProcessor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public String getManufacturer() {
//		return manufacturer;
//	}
//
//	public void setManufacturer(String manufacturer) {
//		this.manufacturer = manufacturer;
//	}

	public int getCores() {
		return cores;
	}

	public void setCores(int cores) {
		this.cores = cores;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}	
}
