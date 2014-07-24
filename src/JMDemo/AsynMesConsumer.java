package JMDemo;

import java.util.Properties;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AsynMesConsumer implements MessageListener {

	/**
	 * @���ܣ��첽������Ϣ
	 * @���ߣ�
	 * @���ڣ�2012-10-18
	 */

	private int EXPECTED_MESSAGE_COUNT = 2;
	private int messageCount = 0;
	private QueueReceiver receiver;
	private TextMessage msg;

	public AsynMesConsumer() throws NamingException, JMSException {
		/* ��ʼ�������Ķ��� */
		String url = "t3://localhost:7001";
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, url);
		Context ctx = new InitialContext(p);

		/* �������ӹ��� */
		QueueConnectionFactory qConFactory = (QueueConnectionFactory) ctx
				.lookup("weblogic.jms.ConnectionFactory");
		/* �������� */
		Queue messageQueue = (Queue) ctx.lookup("jms/MyMDB");
		/* �������� */
		QueueConnection qCon = qConFactory.createQueueConnection();
		/* ����һ���Ự */
		QueueSession session = qCon.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);
		/* ����һ�������� */
		receiver = session.createReceiver(messageQueue);
		/* ����һ����Ϣ���� */
		receiver.setMessageListener(this);
		qCon.start();

	}

	@Override
	public void onMessage(Message m) {

		try {
			msg = (TextMessage) m;
			System.out.println("Receiver:" + msg.getText());

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageCount++;
	}

	public boolean expectMoreMessage() {
		return messageCount < EXPECTED_MESSAGE_COUNT;
	}

	public static void main(String[] args) throws Exception {
		int MAX_TRIES = 10;
		int tryCount = 0;
		AsynMesConsumer consumer = new AsynMesConsumer();
		while (consumer.expectMoreMessage() && (tryCount < MAX_TRIES)) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tryCount++;
		}

	}
}
