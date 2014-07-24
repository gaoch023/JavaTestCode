package JMSTransacted;

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
 * JMS事务性会话测试
 * @author Administrator
 *
 */
public class JMSTransacted {
	private QueueConnection connection = null;
	private QueueSession session = null;
	private QueueSender sender = null;
	
	/**
	 *	构造函数
	 */
	public JMSTransacted(){
		try{
			//创建连接，会话，和发送者
			Context ctx = new InitialDirContext();
			QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("queue1");
			connection = factory.createQueueConnection();
			//创建事物会话
			session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) ctx.lookup("queue1");
			sender = session.createSender(queue);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * 发送消息
	 * @throws JMSException 
	 */
	public void sendMessages() throws JMSException{
		try{
			//在一个事物中发送消息
			System.out.println("Session Transacted:" +session.getTransacted());
			sendMessage("First Message");
			sendMessage("Second Message");
			sendMessage("Third Message");
			
			//提交事物
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			
			//如果出现异常，则使事物回滚
			System.out.println("Exception caught, rolling back session");
			session.rollback();
		}
	}
	
	/**
	 * 发送一个简单的文本信息
	 * @throws JMSException 
	 */
	public void sendMessage(String text) throws JMSException{
		//在消息组内发送一个简单的文本信息
		TextMessage msg = session.createTextMessage(text);
		sender.send(msg);
	}
	
	public static void main(String[] args){
		try{
			JMSTransacted app = new JMSTransacted();
			app.sendMessages();
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
