package JMSGroup;

import java.util.ArrayList;
import java.util.List;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;

/**
 * JMS��Ϣ������
 * @author Administrator
 *
 */
public class JMSReceive implements MessageListener{
	private QueueConnection connection = null;
	private QueueSession session = null;
	private QueueConnectionFactory factory = null; 
	private Queue queue = null;
	private QueueReceiver receive;
	
	private List<String> messageBuffer = new ArrayList<String>();
	
	public JMSReceive(){
		try{
			//����JMS�ṩ�ߴ������ӣ��Ự�ͷ�����
			Context ctx = new InitialDirContext();
			factory = (QueueConnectionFactory)ctx.lookup("QueueCF");
			connection = factory.createQueueConnection();
			connection.start();
			session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (Queue)ctx.lookup("queue1");
			
			receive = session.createReceiver(queue);
			receive.setMessageListener(this);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * ��Ϣ������
	 */
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			if(message.propertyExists("SequenceMarker")){
				String marker = message.getStringProperty("SequenceMarker");
				
				//�����������һ����Ϣ��
				if(marker.equals("START_SEQUENCE")){
						messageBuffer.clear();
				}
				
				//������ڽ�����Ϣ�飬Ӧ������Ϣ��ȷ�����е���Ϣ�ѱ�����
				if(marker.equals("END_SEQUENCE")){
					//������Ϣ
					System.out.println("Message:");
					
					for (String msg : messageBuffer) {
						System.out.println(msg);						
					}
					
					//ȷ���յ���Ϣ
					message.acknowledge();
				}
			}
			
			//���ĳ����Ϣ��һ���Ǳ����Ϣ���򱣴����Ϣ����
			if(message instanceof TextMessage){
				TextMessage msg = (TextMessage)message;
				addMessage(msg.getText());
			}
			
			//�ȴ���һ����Ϣ
			System.out.println("Waiting for messages");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ������Ϣ��������
	 */
	private void addMessage(String text){
		messageBuffer.add(text);
	}
}
