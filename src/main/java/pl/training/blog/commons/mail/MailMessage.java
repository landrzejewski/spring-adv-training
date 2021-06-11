package pl.training.blog.commons.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailMessage implements Serializable {

    private String recipient;
    private String title;
    private String text;

}
