package pl.training.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

@EnableTransactionManagement
@EnableAspectJAutoProxy
@Configuration
public class BlogConfiguration {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ConnectionFactory connectionFactory() throws NamingException {
        return new JndiTemplate().lookup("java:/ConnectionFactory", ConnectionFactory.class);
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        var cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
        return new JmsTemplate(cachingConnectionFactory);
    }

    @Bean
    public DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        var factoryBean = new DefaultJmsListenerContainerFactory();
        factoryBean.setConnectionFactory(connectionFactory);
        factoryBean.setConcurrency("5-10");
        return factoryBean;
    }

}
