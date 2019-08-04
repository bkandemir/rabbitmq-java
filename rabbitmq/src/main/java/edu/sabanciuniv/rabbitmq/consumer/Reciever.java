package edu.sabanciuniv.rabbitmq.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Reciever {
	
	private static final String QUEUE_NAME = "sample_queue1";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		Consumer c = new DefaultConsumer(channel) {
			
			@Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" Message Received '" + message + "'");
            }
			
		};
		
		channel.basicConsume(QUEUE_NAME, true,c);

	}


}
