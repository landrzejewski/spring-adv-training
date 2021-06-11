package pl.training;

import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Log
public class Client {

    @Autowired
    @Setter
    private UsersService usersService;

    @PostConstruct
    public void init() {
        usersService.add(new User("Jan", "Kowalski"));
        log.info(usersService.getAll().toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

}
