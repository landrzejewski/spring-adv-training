package pl.training.blog.commons.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SendMailController {

    private final TextSource textSource;
    private final MailService mailService;

    @GetMapping("mails")
    public ResponseEntity<Void> sendMail(Locale locale) {
        var text = textSource.build("hello", Map.of("user", "Jan"), locale.getLanguage());
        mailService.send(MailMessage.builder()
                .recipient("l.andrzejewski@sages.com.pl")
                .title("Hello")
                .text(text)
                .build());
        return ResponseEntity.accepted().build();
    }

}
