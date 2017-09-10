package jws.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import jws.ws.OutInfo;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class Client {

	

	public static void main(String args[]) throws Exception {


//		client1();
		
		client2();

	}
	
	
	
	public static void client1() {
		QName service_name = new QName("http://ws.jws/", "OutInfo");
		QName port_name = new QName("http://ws.jws/", "OutInfoPort");
		String endpointAddress = "http://localhost:9000/out";
		Service create = Service.create(service_name);
		create.addPort(port_name, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
		
		OutInfo outInfo = create.getPort(OutInfo.class);
		String message = outInfo.outInfo("kill -9 0000");
		System.out.println(message);
		
	}
	
	public static void client2() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean(); 
		factory.getInInterceptors().add(new LoggingInInterceptor()); 
		factory.getOutInterceptors().add(new LoggingOutInterceptor()); 
		factory.setServiceClass(OutInfo.class); 
		factory.setAddress("http://localhost:9000/out"); 
		OutInfo client = (OutInfo) factory.create(); 
		String reply = client.outInfo("jaxwsproxy client---");
		System.out.println("Server said: " + reply); 
	}
}
