package me.fwfurtado;

import static me.fwfurtado.BinderConfiguration.SEARCH_INPUT;
import static me.fwfurtado.BinderConfiguration.SEARCH_OUTPUT;

import java.math.BigDecimal;
import java.util.stream.LongStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(@Output(SEARCH_OUTPUT) MessageChannel channel) {
        return args -> {

            LongStream.range(1L, 10L).forEach(value -> {

                MessageBuilder<Counter> builder = MessageBuilder
                    .withPayload(new Counter(value));

                if (value % 2 == 0) {
                    builder.setHeader("X-AUTH", "123");
                }

                Message<Counter> message = builder.build();

                channel.send(message);
            });
        };
    }

    @StreamListener(value = SEARCH_INPUT, condition = "headers.containsKey('X-AUTH') && headers.get('X-AUTH') == '456'")
    void handle(Counter counter) {
        System.out.println("With condition" + counter);
    }



//    @StreamListener(value = SEARCH_INPUT)
//    void handle2(Counter counter) {
//        System.out.println("Without condition" + counter);
//    }
}
