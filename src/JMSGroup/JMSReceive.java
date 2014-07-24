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
 * JMS消息接受者
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
			//连接JMS提供者创建连接，会话和发送者
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
	 * 消息处理方法
	 */
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try{
			if(message.propertyExists("SequenceMarker")){
				String marker = message.getStringProperty("SequenceMarker");
				
				//如果正在启动一个消息组
				if(marker.equals("START_SEQUENCE")){
						messageBuffer.clear();
				}
				
				//如果正在结束消息组，应处理消息并确认所有的消息已被处理
				if(marker.equals("END_SEQUENCE")){
					//处理消息
					System.out.println("Message:");
					
					for (String msg : messageBuffer) {
						System.out.println(msg);						
					}
					
					//确认收到消息
					message.acknowledge();
				}
			}
			
			//如果某条消息是一个非标记消息，则保存该消息内容
			if(message instanceof TextMessage){
				TextMessage msg = (TextMessage)message;
				addMessage(msg.getText());
			}
			
			//等待下一条消息
			System.out.println("Waiting for messages");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 增加消息到缓存中
	 */
	private void addMessage(String text){
		messageBuffer.add(text);
	}
}
