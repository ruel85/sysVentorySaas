package ch.zbw.sysVentorySaaS.service.snmpscanner;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * SNMPThread ist f�r eine einzige SNMP-Abfrage verantwortlich
 * 
 * @author Damjan Djuranovic
 *
 */
public class SNMPThread implements Runnable {
	private Snmp snmp;
	private String ipv4;
	private int timeout;
	private int retries;
	private final String portSNMP = "/161";
	private final String protocol = "udp:";
	private final String community = "public";
	private volatile boolean reachable;

	/**
	 * Konstruktor, setzt die max. Versuche auf 3x
	 * 
	 * @param ipv4
	 * @param timeout
	 */
	public SNMPThread(String ipv4, int timeout) {
		this.ipv4 = ipv4;
		this.timeout = timeout;
		this.retries = 3;
	}

	/**
	 * @param oids
	 * @return
	 * @throws IOException
	 */
	public ResponseEvent get(OID oids[]) throws IOException {
		PDU pdu = new PDU();
		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}
		pdu.setType(PDU.GET);
		ResponseEvent event = snmp.send(pdu, getTarget(), null);
		if (event != null)
			return event;
		else
			return null;
	}

	/**
	 * @param oid
	 * @return
	 * @throws IOException
	 */
	public String getAsString(OID oid) throws IOException {
		ResponseEvent event = get(new OID[] { oid });
		if (event.getResponse() != null)
			return event.getResponse().get(0).getVariable().toString();
		else
			return null;
	}

	/**
	 * @return
	 */
	public String getIpv4() {
		return ipv4;
	}

	/**
	 * @return
	 */
	private Target getTarget() {
		Address targetAddress = GenericAddress.parse(protocol + ipv4 + portSNMP);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString(community));
		target.setAddress(targetAddress);
		target.setRetries(retries);
		target.setTimeout(timeout);
		target.setVersion(SnmpConstants.version2c);
		return target;
	}

	/**
	 * @return
	 */
	public boolean isReachable() {
		return reachable;
	}

	@Override
	public void run() {
		TransportMapping<?> transport = null;
		try {
			transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			transport.listen();
			if (getAsString(new OID(".1")) != null) {
				reachable = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
