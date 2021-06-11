package pl.training.blog.commons.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;

import javax.jms.Queue;
import javax.naming.NamingException;

@Configuration
public class MailsConfiguration {

    @Bean
    public Queue mailQueue() throws NamingException {
        return new JndiTemplate().lookup("jboss/exported/jms/queue/Mail", Queue.class);
    }

}
