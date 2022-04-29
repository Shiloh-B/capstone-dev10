package data;

import capstone.data.UserTemplateRepository;
import capstone.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

class UserTemplateRepositoryTest {
    @Autowired
    UserTemplateRepository repository;


    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void add() {
        User user = makeUser();
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(user.getUserID(), actual.getUserID());
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
    private User makeUser(){
        User user = new User();
        user.setUserID(1);
        user.setUsername("nik");
        user.setPasswordHash("password");
        return user;
    }
}