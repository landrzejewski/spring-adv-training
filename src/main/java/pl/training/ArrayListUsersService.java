package pl.training;

import java.util.ArrayList;
import java.util.List;

public class ArrayListUsersService implements UsersService {

    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

}
