package com.softwarepassion.tutorial.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Router
 */
public class MyRouteBuilder extends RouteBuilder {

    public void configure() {
        from("timer://tutorial?fixedRate=true&delay=3000&period=10000")   // 1
        .setBody(constant("")).setHeader(Exchange.HTTP_METHOD, constant("POST"))                           // 2 
            .to("http://twitter.com").convertBodyTo(String.class).process(new Processor(){

                        public void process(Exchange exchange) throws Exception {
                                Object body = exchange.getIn().getBody();
                                System.out.println("BODY: " + body);
                        }

            });
    }
}

