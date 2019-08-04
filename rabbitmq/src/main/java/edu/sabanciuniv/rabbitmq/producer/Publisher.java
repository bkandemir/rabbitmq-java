package edu.sabanciuniv.rabbitmq.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
	
	private final static String QUEUE_NAME = "sample_queue1";

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
        
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        
        String message = "bkandemir - Hello";
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" Message Sent '" + message + "'");
        
        channel.close();
        conn.close();
	}

}
