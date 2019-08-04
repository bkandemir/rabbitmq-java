package edu.sabanciuniv.rabbitmq.consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.*;

public class Reciever2 {
	
	private final static String QUEUE_NAME = "sample_queue2";

	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection conn = factory.newConnection();
	    
	    Channel channel = conn.createChannel();
	    
	    channel.queueDeclare(QUEUE_NAME, true, false, false, null);
	    
	    
	    
	    Consumer c = new DefaultConsumer(channel) {
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope,
	                                   AMQP.BasicProperties properties, byte[] body) throws IOException {
	            try {
	            	sendMessage("Consumer", body);
	            } finally {
	                getChannel().basicAck(envelope.getDeliveryTag(), false);
	                System.out.println("Consumer - Acknowledgement OK");
	            }
	        }
	    };
	    
	    channel.basicConsume(QUEUE_NAME, false, c);

	}

	private static void sendMessage(String name, byte[] body) {
	    String message = new String(body);
	    System.out.println(name + " Message Received: " + message);
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    System.out.println(name + " Done! " + message);
	}

}

