package capstone.data;

import capstone.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserTemplateRepositoryTest {
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

    private User makeUser() {
        User user = new User();
        user.setUserID(1);
        user.setUsername("nik");
        user.setPasswordHash("password");
        return user;
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}