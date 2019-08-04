package edu.sabanciuniv.rabbitmq.producer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;


public class Publisher2 {

	private static final String QUEUE_NAME = "sample_queue2";

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		// TODO Auto-generated method stub
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
        for (int i = 0; i < 10; i++) {
            String message = "Message " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Message Sent: " + message);
            Thread.sleep(1000);
        }
        
        channel.close();
        conn.close();

	}

}
