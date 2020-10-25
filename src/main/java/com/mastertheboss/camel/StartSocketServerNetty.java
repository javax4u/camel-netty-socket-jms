package com.mastertheboss.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.jndi.JndiContext;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class StartSocketServerNetty {

    public static void main(String... args) throws Exception {

        mainWithDecoderEncoder();
    }

    public static void mainWithDecoderEncoder() throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://0.0.0.0:61616");
        JndiContext registry = new JndiContext();
       
        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addComponent("jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new SocketRouteBuilder());
        camelContext.start();

    }

   }
