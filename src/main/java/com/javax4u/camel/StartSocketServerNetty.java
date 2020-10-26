package com.javax4u.camel;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.jndi.JndiContext;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class StartSocketServerNetty {

    public static void main(String... args) {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://0.0.0.0:61616");
        JndiContext registry;
        try {
            registry = new JndiContext();

            CamelContext camelContext = new DefaultCamelContext(registry);
            camelContext.addComponent("jms",
                    JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
            camelContext.addRoutes(new SocketToJMSRouteBuilder());
            camelContext.start();
        } catch (Exception ex) {
            Logger.getLogger(StartSocketServerNetty.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
