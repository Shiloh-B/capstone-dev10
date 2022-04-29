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
        assertTrue(list.size()>=1 && list.size() <=3);
    }

    @Test
    void shouldFindByUsername() {
        User shiloh = repository.findByUsername("shiloh");
        assertEquals(shiloh.getPasswordHash(), "password");
        assertEquals(shiloh.getUsername(), "shiloh");
    }
    @Test
    void shouldAdd() {
        User user = makeUser();
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(user.getUserID(), actual.getUserID());
    }
    @Test
    void shouldUpdate() {
        User test = makeUser();
        test.setUsername("test");
        assertTrue(repository.update(test));
        assertEquals(test.getUsername(), repository.findByUsername("test").getUsername());
    }

    @Test
    void shouldDeleteByUsername() {
        assertTrue(repository.deleteByUsername("shiloh"));
    }

    private User makeUser() {
        User user = new User();
        user.setUserID(1);
        user.setUsername("shiloh");
        user.setPasswordHash("password");
        user.setDisabled(false);
        return user;
    }
}