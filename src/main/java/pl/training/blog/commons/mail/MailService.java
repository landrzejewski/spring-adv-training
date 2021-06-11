package pl.training.blog.commons.mail;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
@Log
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final JmsTemplate jmsTemplate;
    private final Queue mailQueue;

    @Value("${sender}")
    private String sender;

    @SneakyThrows
    public void send(MailMessage mailMessage) {
        jmsTemplate.convertAndSend(mailQueue.getQueueName(), mailMessage);
    }

    @JmsListener(destination = "MailQueue")
    public void onMessage(MailMessage mailMessage) {
        mailSender.send(mimeMessagePreparator(mailMessage));
    }

    private MimeMessagePreparator mimeMessagePreparator(MailMessage mailMessage) {
        return mimeMessage -> {
            var messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            messageHelper.setFrom(sender);
            messageHelper.setTo(mailMessage.getRecipient());
            messageHelper.setSubject(mailMessage.getTitle());
            messageHelper.setText(mailMessage.getText(), true);
        };
    }

}
