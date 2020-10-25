package com.mastertheboss.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.jndi.JndiContext;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

@Deprecated
public class StartSocketServerNetty1 {

    public static void main(String... args) throws Exception {

        mainWithDecoderEncoder();
    }

    public static void mainWithDecoderEncoder() throws Exception {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://0.0.0.0:61616");
        JndiContext registry = new JndiContext();
        //ChannelHandlerFactory lengthDecoder = ChannelHandlerFactories.newLengthFieldBasedFrameDecoder(900000000, 0, 4, 0, 4);

       // StringDecoder stringDecoder = new StringDecoder();
        //registry.bind("length-decoder", lengthDecoder);
       // registry.bind("string-decoder", stringDecoder);

       // LengthFieldPrepender lengthEncoder = new LengthFieldPrepender(4);
      //  StringEncoder stringEncoder = new StringEncoder();
       // registry.bind("length-encoder", lengthEncoder);
       // registry.bind("string-encoder", stringEncoder);

       // List<ChannelHandler> decoders = new ArrayList<ChannelHandler>();
       // decoders.add(lengthDecoder);
       // decoders.add(stringDecoder);

       // List<ChannelHandler> encoders = new ArrayList<ChannelHandler>();
        //encoders.add(lengthEncoder);
        //encoders.add(stringEncoder);

       // registry.bind("encoders", encoders);
        //registry.bind("decoders", decoders);
        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addComponent("jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new SocketRouteBuilder());
        camelContext.start();

    }

    public static void mainWithoutDecoder() throws Exception {
        JndiContext jndiContext = new JndiContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                "tcp://0.0.0.0:61616");

        //  jndiContext.bind("maxFrameSize", "190000000");
        // jndiContext.bind("MilkOrder", new MilkOrderProcessor());
        CamelContext camelContext = new DefaultCamelContext(jndiContext);
        camelContext.addComponent("jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new SocketRouteBuilder());
        camelContext.start();
//        Main main = new Main();
//        main.addRouteBuilder(SocketRouteBuilder.class);
//        main.run(args);
    }
}
