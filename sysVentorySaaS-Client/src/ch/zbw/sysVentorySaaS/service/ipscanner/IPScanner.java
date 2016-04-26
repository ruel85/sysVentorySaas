package ch.zbw.sysVentorySaaS.service.ipscanner;

import java.io.IOException;
import java.net.InetAddress;

public class IPScanner {

	public IPScanner() {
		checkHosts("255.255.0");
	}

	public static void main(String[] args) {
		new IPScanner();

	}
	
	public void checkHosts(String subnet){
		   int timeout=1000;
		   for (int i=1;i<255;i++){
		       String host=subnet + "." + i;
		       try {
				if (InetAddress.getByName(host).isReachable(timeout)){
				       System.out.println(host + " is reachable");
				   }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		}

}
