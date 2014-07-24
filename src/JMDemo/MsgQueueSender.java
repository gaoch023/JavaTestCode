package JMDemo;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MsgQueueSender {

	/**
	 * @功能:JMS中实现点对点消息服务--发送消息
	 * @作者：
	 * @日期：2012-10-17
	 */

	private QueueSender sender;
	private TextMessage msg;

	public MsgQueueSender(String[] argv) throws NamingException, JMSException {
		/* 初始化上下文对象 */
		String url = "t3://localhost:7001";
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, url);
		Context ctx = new InitialContext(p);

		/* 创建一个连接工厂 */
		QueueConnectionFactory qConFactory = (QueueConnectionFactory) ctx
				.lookup("weblogic.jms.ConnectionFactory");

		/* 创建一个队列 */
		Queue messageQueue = (Queue) ctx.lookup("jms/MyMDB");

		/* 创建连接 */
		QueueConnection qCon = qConFactory.createQueueConnection();

		/* 创建一个会话 */
		QueueSession session = qCon.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);

		/* 创建一个发送者 */
		sender = session.createSender(messageQueue);

		/* 创建一个消息 */
		msg = session.createTextMessage();

	}

	public void runClient(String str) throws JMSException {
		/* 设置消息，并发送 */
		msg.setText("Hello");
		sender.send(msg);
		msg.setText("Welcome to JMS");
		sender.send(msg);
		msg.setText(str);
		sender.send(msg);
	}

	public static void main(String[] args) throws Exception {
		try {
			MsgQueueSender mqs = new MsgQueueSender(args);
			mqs.runClient("aaa");

		} catch (NamingException e) {
			System.err.println("");
			System.err.println("**请确保已经正确地设置JMS服务器。在运行之前必须配置JMS服务器和正确的JMS目的。");
			System.err.println("");
			throw e;
		}
	}

}
