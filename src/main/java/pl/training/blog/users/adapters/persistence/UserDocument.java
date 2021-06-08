package pl.training.blog.users.adapters.persistence;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Builder
@Data
public class UserDocument {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

}
