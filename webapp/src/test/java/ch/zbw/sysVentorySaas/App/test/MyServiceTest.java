package ch.zbw.sysVentorySaas.App.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyServiceTest {
	 
    private HttpServer server;
    private WebTarget target;
 
    @Before
    public void setUp() throws Exception {

    }
 
    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }
 
    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("myresource").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
