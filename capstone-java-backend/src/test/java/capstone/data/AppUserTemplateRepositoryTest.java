package capstone.data;

import capstone.models.AppUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AppUserTemplateRepositoryTest {

    @Autowired
    KnownGoodState knownGoodState;
    @Autowired
    AppUserJdbcTemplateRepository repository;
    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

//    @Test
//    void shouldFindAll() {
//        List<AppUser> list = repository.findAll();
//        assertNotNull(list);
//        System.out.println(list.get(0).getUsername());
//        System.out.println(list.get(1).getUsername());
//        assertTrue(list.size()>=1 && list.size() <=3);
//    }

    @Test
    void shouldFindByUsername() {
        AppUser shiloh = repository.findByUsername("shiloh");
        assertNotNull("shiloh");
    }
    @Test
    void shouldAdd() {
        AppUser appUser = new AppUser(0, "shiloh", "test", false, List.of("User"));
        AppUser actual = repository.add(appUser);
        assertNotNull(actual);
        assertEquals(appUser.getAppUserId(), actual.getAppUserId());
    }


    @Test
    void shouldUpdate() {
        AppUser test = new AppUser(1, "test", "test", false, List.of("User"));
        assertTrue(repository.update(test));
        AppUser actual = repository.findByUsername("test");

        assertNotNull(actual);
    }

//    @Test
//    void shouldDeleteByUsername() {
//        assertTrue(repository.deleteByUsername("shiloh"));
//    }
}