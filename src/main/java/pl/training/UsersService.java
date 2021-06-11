package pl.training;

import java.util.List;

public interface UsersService {

    void add(User user);

    List<User> getAll();

}
