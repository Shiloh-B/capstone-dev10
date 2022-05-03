package capstone.controllers;

import capstone.models.AppUser;
import capstone.security.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private AppUserService appUserService;

    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> findByUsername(@PathVariable String username) {
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(username);
        if(appUser == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        // remove the password hash from the user before sending back
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

}
