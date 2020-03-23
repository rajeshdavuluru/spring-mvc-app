package com.verizon.mdbs;

import javax.ejb.ActivationConfigProperty;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(
	activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") ,
		@ActivationConfigProperty(propertyName="connectionFactoryJndiName",propertyValue="QCF"),
		@ActivationConfigProperty(propertyName="destinationJndiName", propertyValue="TestQ")
	},mappedName="TestQ"
)
public class TestMDB implements MessageListener{

	public void onMessage(Message message)
	{
		TextMessage textMessage = (TextMessage) message;
		try {
			System.out.println("nnt(mdb) MyMDB Received n"+ textMessage.getText());
		}
		catch (JMSException e)
		{
			e.printStackTrace();
		}
	}

}
