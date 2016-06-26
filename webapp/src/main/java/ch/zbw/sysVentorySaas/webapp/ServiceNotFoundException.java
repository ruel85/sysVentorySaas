package ch.zbw.sysVentorySaas.webapp;

public class ServiceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4110989488980708057L;
	private int errorId;
	
	public int getErrorId(){
		return errorId;
	}
	
	public ServiceNotFoundException(String msg, int errorId){
		super(msg);
		this.errorId = errorId;
	}
	
	public ServiceNotFoundException(String msg, Throwable cause)
	{
		super(msg, cause);
	}

}
