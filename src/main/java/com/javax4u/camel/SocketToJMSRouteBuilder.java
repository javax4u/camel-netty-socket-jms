package com.javax4u.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Predicate;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SocketToJMSRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

       // from("netty:tcp://localhost:4209?allowDefaultCodec=true&sync=false")
        //from("netty:tcp://localhost:4209?textline=false&sync=false")//not working no error
        //from("netty:tcp://localhost:4209?decoders=#length-decoder&sync=false")
                from("netty:tcp://localhost:4209?allowDefaultCodec=false&sync=false")//not working no error
                //.allowDefaultCodec
                .process(new MyProcessor())
                //.setHeader(Exchange.HTTP_METHOD, simple("POST"))
                //.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                //.setHeader(Exchange.HTTP_QUERY, constant("hl=en&q=activemq"))
                .to("log:?level=DEBUG&showBody=true")
                .to("jms:queue:VdoxxQueue");
        // .to("http://localhost/html5/vdoxx/rfidscan/consumeRFIDScan.htm?mode=11").process(new MyProcessor());

    }

}

class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        Message socketData = exchange.getMessage();
        String stringMessage = socketData.getBody(String.class);
        System.out.println("** MyProcessor******\n");
     
            System.out.println(stringMessage);
        exchange.getIn().setBody(stringMessage);

        // exchange.getIn().setBody(socketData);
    }
}

class MyPredicate implements Predicate {

    @Override
    public boolean matches(Exchange exchange) {
        System.out.println("tostring: " + exchange.toString());
        return true;
    }

}
