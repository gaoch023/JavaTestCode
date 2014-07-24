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
 * JMS�����ԻỰ����
 * @author Administrator
 *
 */
public class JMSTransacted {
	private QueueConnection connection = null;
	private QueueSession session = null;
	private QueueSender sender = null;
	
	/**
	 *	���캯��
	 */
	public JMSTransacted(){
		try{
			//�������ӣ��Ự���ͷ�����
			Context ctx = new InitialDirContext();
			QueueConnectionFactory factory = (QueueConnectionFactory)ctx.lookup("queue1");
			connection = factory.createQueueConnection();
			//��������Ự
			session = connection.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) ctx.lookup("queue1");
			sender = session.createSender(queue);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * ������Ϣ
	 * @throws JMSException 
	 */
	public void sendMessages() throws JMSException{
		try{
			//��һ�������з�����Ϣ
			System.out.println("Session Transacted:" +session.getTransacted());
			sendMessage("First Message");
			sendMessage("Second Message");
			sendMessage("Third Message");
			
			//�ύ����
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
			
			//��������쳣����ʹ����ع�
			System.out.println("Exception caught, rolling back session");
			session.rollback();
		}
	}
	
	/**
	 * ����һ���򵥵��ı���Ϣ
	 * @throws JMSException 
	 */
	public void sendMessage(String text) throws JMSException{
		//����Ϣ���ڷ���һ���򵥵��ı���Ϣ
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
