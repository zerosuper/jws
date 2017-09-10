package jws.server;

import javax.xml.ws.Endpoint;

import jws.ws.OutInfo;
import jws.ws.OutInfoService;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class Server {

	public static void main(String[] args) {
		
		publish1();
		
//		publish2();	
		
	}
	
	//发布方式一：The JAX-WS standard Endpoint APIs
	public static void publish1() {
		System.out.println("Starting Server");
		OutInfo implementor = new OutInfoService();
		String address = "http://localhost:9000/out";   //service访问地址
		Endpoint.publish(address, implementor);
		System.out.println("publish...");
	}
	
	//发布方式二：使用cxf提供的JaxWsServerFactoryBean
	public static void publish2() {
		OutInfo outInfo = new OutInfoService(); 
		JaxWsServerFactoryBean svrFactory = new JaxWsServerFactoryBean(); 
		svrFactory.setServiceClass(OutInfo.class); 
		svrFactory.setAddress("http://localhost:9000/out"); 
		svrFactory.setServiceBean(outInfo); 
		svrFactory.getInInterceptors().add(new LoggingInInterceptor());     //加入日志
		svrFactory.getOutInterceptors().add(new LoggingOutInterceptor()); 
		svrFactory.create();
	}
}
