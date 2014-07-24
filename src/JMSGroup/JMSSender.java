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
 * JSM消息组发送方
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
			//连接JMS提供者创建连接，会话和发送者
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
	 * 将多条信息作为一个组发送
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
	 * 在消息组中发送一条简单的TextMessage
	 * @param text
	 * @throws JMSException 
	 */
	public void sendMessage(String text) throws JMSException{
		TextMessage textMessage = session.createTextMessage(text);
		textMessage.setStringProperty("JMSXGroupID", "GROUP1");
		
		sender.send(textMessage);
	}
	
	/**
	 * 发送一条包含序列标记的空有效负载信息
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
