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
 * SOAP�ͻ��˵��ó���
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
			
			//����Call����
			Call call = new Call();
			call.setTargetObjectURI("urn:hello");
			call.setMethodName("sayHello");
			call.setEncodingStyleURI(Constants.NS_URI_SOAP_ENC);
			
			//��Ӳ���
			Vector params = new Vector();
			params.addElement(new org.apache.soap.rpc.Parameter("name", String.class, name, null));
			call.setParams(params);
					
			//ȡ�ûظ���Ϣ
			Response resp = null;
			
			try{
				//�������
				resp = call.invoke(url, "");
			}catch(SOAPException e){
				System.out.println("Զ�̵��÷���ʧ��");
				e.printStackTrace();
				System.exit(-1);
			}
			
			//���Ӧ��
			if(!resp.generatedFault()){
				//���û�з��ش�����Ϣ
				Parameter ret = resp.getReturnValue();
				Object value = ret.getValue();
				System.out.println(value);
			}else{
				//������ش�����Ϣ���ӡ������
				Fault falut = resp.getFault();
				System.out.println(MessageFormat.format("Fault Code = {0}, Fault String : {1}", falut.getFaultCode(), falut.getFaultString()));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
