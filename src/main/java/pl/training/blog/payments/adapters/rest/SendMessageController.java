package pl.training.blog.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.payments.adapters.jee.JmsSender;

@RestController
@RequiredArgsConstructor
public class SendMessageController {

    private final JmsSender jmsSender;

    @PostMapping("messages")
    public ResponseEntity<Void> sendMessage(@RequestBody String text) {
        jmsSender.send(text);
        return ResponseEntity.accepted().build();
    }

}
