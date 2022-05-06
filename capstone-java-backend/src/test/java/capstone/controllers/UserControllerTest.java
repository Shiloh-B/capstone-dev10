package capstone.controllers;

import capstone.models.UserInfo;
import capstone.security.AppUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    void shouldFindByUsername() {
        String username = "nik";
        ResponseEntity<UserInfo> result = userController.findByUsername(username);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    void shouldFailToFindAndReturnNotFound(){
        try {
            String username = "cannotfindthis";
            ResponseEntity<UserInfo> result = userController.findByUsername(username);
            assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        } catch (org.springframework.security.core.userdetails.UsernameNotFoundException e){
            e.printStackTrace();
        }
    }
}