package SOAP;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Vector;

import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.SOAPException;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;

/**
 * SOAP客户端调用程序
 * @author Administrator
 *
 */
public class Client {
	public static void main(String[] args){
		try{
			URL url = null;
			String name = null;
			
			url = new URL("http://localhost/soap/servlet/rpcrouter");
			if(args.length == 0){
				name = "friend";
			}else{
				name = args[0];
			}
			
			//构造Call对象
			Call call = new Call();
			call.setTargetObjectURI("urn:hello");
			call.setMethodName("sayHello");
			call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
			
			//添加参数
			Vector params = new Vector();
			params.addElement(new org.apache.soap.rpc.Parameter("name", String.class, name, null));
			call.setParams(params);
					
			//取得回复信息
			Response resp = null;
			
			try{
				//发起调用
				resp = call.invoke(url, "");
			}catch(SOAPException e){
				System.out.println("远程调用服务失败");
				e.printStackTrace();
				System.exit(-1);
			}
			
			//检查应答
			if(!resp.generatedFault()){
				//如果没有返回错误信息
				Parameter ret = resp.getReturnValue();
				Object value = ret.getValue();
				System.out.println(value);
			}else{
				//如果返回错误信息则打印出错误
				Fault falut = resp.getFault();
				System.out.println(MessageFormat.format("Fault Code = {0}, Fault String : {1}", falut.getFaultCode(), falut.getFaultString()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
