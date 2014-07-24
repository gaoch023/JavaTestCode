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
	 * @����:JMS��ʵ�ֵ�Ե���Ϣ����--������Ϣ
	 * @���ߣ�
	 * @���ڣ�2012-10-17
	 */

	private QueueSender sender;
	private TextMessage msg;

	public MsgQueueSender(String[] argv) throws NamingException, JMSException {
		/* ��ʼ�������Ķ��� */
		String url = "t3://localhost:7001";
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, url);
		Context ctx = new InitialContext(p);

		/* ����һ�����ӹ��� */
		QueueConnectionFactory qConFactory = (QueueConnectionFactory) ctx
				.lookup("weblogic.jms.ConnectionFactory");

		/* ����һ������ */
		Queue messageQueue = (Queue) ctx.lookup("jms/MyMDB");

		/* �������� */
		QueueConnection qCon = qConFactory.createQueueConnection();

		/* ����һ���Ự */
		QueueSession session = qCon.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);

		/* ����һ�������� */
		sender = session.createSender(messageQueue);

		/* ����һ����Ϣ */
		msg = session.createTextMessage();

	}

	public void runClient(String str) throws JMSException {
		/* ������Ϣ�������� */
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
			System.err.println("**��ȷ���Ѿ���ȷ������JMS��������������֮ǰ��������JMS����������ȷ��JMSĿ�ġ�");
			System.err.println("");
			throw e;
		}
	}

}
