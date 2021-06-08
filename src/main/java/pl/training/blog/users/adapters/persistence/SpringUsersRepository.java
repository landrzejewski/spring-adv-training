package pl.training.blog.users.adapters.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringUsersRepository extends MongoRepository<UserDocument, String> {

    List<UserDocument> findByLastName(String lastName);

}
