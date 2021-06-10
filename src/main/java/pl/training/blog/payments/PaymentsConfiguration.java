package pl.training.blog.payments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;
import pl.training.blog.jee.ExchangeRate;
import pl.training.blog.payments.adapters.logging.FilePaymentsLogger;
import pl.training.blog.payments.application.GetPaymentService;
import pl.training.blog.payments.application.PaymentIdGenerator;
import pl.training.blog.payments.application.ProcessPaymentService;
import pl.training.blog.payments.application.UUIDPaymentIdGenerator;
import pl.training.blog.payments.ports.persistence.PaymentsQueries;
import pl.training.blog.payments.ports.persistence.PaymentsUpdates;
import pl.training.blog.payments.ports.time.TimeProvider;
import pl.training.blog.payments.ports.usecases.GetPaymentUseCase;
import pl.training.blog.payments.ports.usecases.ProcessPaymentUseCase;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.naming.NamingException;
import java.nio.file.Paths;

@Configuration
public class PaymentsConfiguration {

    @Bean
    public FilePaymentsLogger filePaymentLogger() {
        return new FilePaymentsLogger(Paths.get("logs.txt"));
    }

    @Bean
    public PaymentIdGenerator paymentIdGenerator() {
        return new UUIDPaymentIdGenerator();
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(PaymentIdGenerator paymentIdGenerator, PaymentsUpdates paymentsUpdates, TimeProvider timeProvider) {
        return new ProcessPaymentService(paymentIdGenerator, paymentsUpdates, timeProvider);
    }

    @Bean
    public GetPaymentUseCase getPaymentUseCase(PaymentsQueries paymentsQueries) {
        return new GetPaymentService(paymentsQueries);
    }

    @Bean
    public ExchangeRate exchangeRate() throws NamingException {
        return new JndiTemplate().lookup("java:global/blog-1.0-SNAPSHOT/FakeExchangeRate", ExchangeRate.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        return new JndiTemplate().lookup("java:/ConnectionFactory", ConnectionFactory.class);
    }

    @Bean
    public Queue messagesQueue() throws NamingException {
        return new JndiTemplate().lookup("jboss/exported/jms/queue/Blog", Queue.class);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        var cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        return new JmsTemplate(cachingConnectionFactory);
    }

}
