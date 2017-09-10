package jws.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(serviceName = "oo", targetNamespace="http://google.ls")
//@SOAPBinding(style = Style.RPC)
public class OutInfoService implements OutInfo {

	@WebMethod(operationName = "sayHi")
	public String outInfo(@WebParam(name = "info") String message) {
		
		
		System.out.println(message + "--test");
		
		return message + "--test";
	}

}
