package JMSGroup;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;

/**
 * JSM��Ϣ�鷢�ͷ�
 * @author Administrator
 *
 */
public class JMSSender {
	private QueueConnection connection = null;
	private QueueSession session = null;
	private QueueSender sender = null;
	private QueueConnectionFactory factory = null; 
	private Queue queue = null;
	
	public JMSSender(){
		try{
			//����JMS�ṩ�ߴ������ӣ��Ự�ͷ�����
			Context ctx = new InitialDirContext();
			factory = (QueueConnectionFactory)ctx.lookup("QueueCF");
			connection = factory.createQueueConnection();
			connection.start();
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue)ctx.lookup("queue1");
			sender = session.createSender(queue);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ��������Ϣ��Ϊһ���鷢��
	 * @throws JMSException 
	 */
	public void sendMessage() throws JMSException{
		sendSequenceMarker("STRAT_SEQUENCE");
		sendMessage("First Message");
		sendMessage("Second Message");
		sendMessage("Third Message");
		sendSequenceMarker("END_SEQUENCE");
	}
	
	/**
	 * ����Ϣ���з���һ���򵥵�TextMessage
	 * @param text
	 * @throws JMSException 
	 */
	public void sendMessage(String text) throws JMSException{
		TextMessage textMessage = session.createTextMessage(text);
		textMessage.setStringProperty("JMSXGroupID", "GROUP1");
		
		sender.send(textMessage);
	}
	
	/**
	 * ����һ���������б�ǵĿ���Ч������Ϣ
	 * @param marker
	 * @throws JMSException 
	 */
	public void sendSequenceMarker(String marker) throws JMSException{
		BytesMessage msg = session.createBytesMessage();
		msg.setStringProperty("SequenceMarker", marker);
		msg.setStringProperty("JMSXGroupID", "GROUP1");
		
		sender.send(msg);
	}
}
