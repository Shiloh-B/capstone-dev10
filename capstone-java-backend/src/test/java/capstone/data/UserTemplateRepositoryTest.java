package capstone.data;

import capstone.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserTemplateRepositoryTest {

    @Autowired
    KnownGoodState knownGoodState;
    @Autowired
    UserTemplateRepository repository;
    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<User> list = repository.findAll();
        assertNotNull(list);
        System.out.println(list.get(0).getUsername());
        System.out.println(list.get(1).getUsername());
        assertEquals(list.size(), 1);
    }

    @Test
    void shouldFindById() {
    }
    @Test
    void shouldAdd() {
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