package me.fwfurtado;

import me.fwfurtado.BinderConfiguration.Binders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableBinding(Binders.class)
public class BinderConfiguration {

    public static final String SEARCH_OUTPUT = "search-out";
    public static final String SEARCH_INPUT = "search-in";

    interface Binders {
        @Output(SEARCH_OUTPUT)
        MessageChannel searchOut();

        @Input(SEARCH_INPUT)
        MessageChannel searchIn();
    }

}
