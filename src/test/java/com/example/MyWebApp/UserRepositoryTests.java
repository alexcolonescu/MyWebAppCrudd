package com.example.MyWebApp;

import com.example.MyWebApp.user.User;
import com.example.MyWebApp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew() {

        User user = new User();
        user.setEmail("costel3.steventson@gmail.com");
        user.setPassword("Steven123456");
        user.setFirstName("alex");
        user.setLastName("steventson");


        User saveUser = repo.save(user);

        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);

    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
        for (User user : users) {

            System.out.println(user);
        }

    }

    @Test
    public void testUpdate() {

        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);
        repo.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello2000");
        repo.save(user);
        User userUpdate = repo.findById(userId).get();
        Assertions.assertThat(userUpdate.getPassword()).isEqualTo("hello2000");

    }

    @Test
    public void testGet(){
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        User user = optionalUser.get();
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
