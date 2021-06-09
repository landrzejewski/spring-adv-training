package pl.training.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PaymentsRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(PaymentsHandler paymentsHandler) {
        return RouterFunctions
                .route(GET("/payments").and(accept(MediaType.APPLICATION_JSON)), paymentsHandler::getPayments)
                .andRoute(POST("/payments").and(accept(MediaType.APPLICATION_JSON)), paymentsHandler::process);

    }

}
