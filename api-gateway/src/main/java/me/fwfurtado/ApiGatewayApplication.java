package me.fwfurtado;

import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.post.LocationRewriteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@EnableZuulProxy
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }


    @Bean
    LocationRewriteFilter locationRewriteFilter() {
        return new LocationRewriteFilter() {
            @Override
            public boolean shouldFilter() {
                RequestContext ctx = RequestContext.getCurrentContext();
                HttpStatus statusCode = HttpStatus.valueOf(ctx.getResponseStatusCode());
                return statusCode.is3xxRedirection() || statusCode.is2xxSuccessful();
            }
        };
    }
}
