package pl.training.blog.jee;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/*@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "BlogQueue"),
})*/
@Service
@Log
public class MessagesService /*implements MessageListener*/ {

    @JmsListener(destination = "BlogQueue")
    @SneakyThrows
    //@Override
    public void onMessage(Message message) {
        var text = message.getBody(String.class);
        log.info("New message: " + text);
    }

}
