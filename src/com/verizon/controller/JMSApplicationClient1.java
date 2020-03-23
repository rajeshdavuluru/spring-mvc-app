package com.verizon.controller;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSApplicationClient1 {
	public static void main(String[] args) {
        Connection connection;
        try {
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
            env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
            env.put(Context.SECURITY_PRINCIPAL, "rdav3004");
            env.put(Context.SECURITY_CREDENTIALS, "Welcome1");
            Context remoteContext = new InitialContext(env);
             
            ConnectionFactory factory =                        
                (ConnectionFactory)remoteContext.
                        lookup("jms/RemoteConnectionFactory");
            Queue queue = (Queue) remoteContext.
                    lookup("jms/queue/MyQueue");
            connection = factory.createConnection();
            Session session = connection.createSession(false,
                    QueueSession.AUTO_ACKNOWLEDGE);
             
            MessageProducer producer = session.createProducer(queue);
             
            //1. Sending TextMessage to the Queue
            TextMessage message = session.createTextMessage();
            message.setText("Hello EJB3 MDB Queue!!!");
            producer.send(message);
            System.out.println("1. Sent TextMessage to the Queue");
             
            //2. Sending ObjectMessage to the Queue
          /*  ObjectMessage objMsg = session.createObjectMessage();
            Employee employee = new Employee();
            employee.setId(2163);
            employee.setName("Kumar");
            employee.setDesignation("CTO");
            employee.setSalary(100000);
            objMsg.setObject(employee);                    
            producer.send(objMsg);
            System.out.println("2. Sent ObjectMessage to the Queue");
            
            */
             
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }              
    }
}
