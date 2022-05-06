package capstone.security;

import capstone.data.UserRepository;
import capstone.models.AppUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class AppUserServiceTest {

    @Autowired
    AppUserService appUserService;

    @MockBean
    UserRepository repository;

    @Test
    void shouldLoadUserByUsername() {
        AppUser toFind = makeUser();
        when(repository.findByUsername("nik")).thenReturn(toFind);
        AppUser actual = (AppUser) appUserService.loadUserByUsername("nik");
        assertEquals(toFind.getAppUserId(), actual.getAppUserId());
    }
    private AppUser makeUser(){
        List<String> roles = new ArrayList<>();
        AppUser user = new AppUser( 1,
        "nik",
        "pass",
        false,
        roles);
        return user;
    }
    }