package controllers;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import security.JwtConverter;
import security.UserService;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins ={"http://localhost:3030"})
@RequestMapping("/api")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtConverter converter;
    private final security.UserService UserService;

    public AuthController(AuthenticationManager authenticationManager, JwtConverter converter, UserService UserService) {
        this.authenticationManager = authenticationManager;
        this.converter = converter;
        this.UserService = UserService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody Map<String, String> credentials) {

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password"));

        try {
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                String jwtToken = converter.getTokenFromUser((User) authentication.getPrincipal());

                HashMap<String, String> map = new HashMap<>();
                map.put("jwt_token", jwtToken);

                return new ResponseEntity<>(map, HttpStatus.OK);
            }

        } catch (AuthenticationException ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> credentials) {
        User user = null;

        try {
            String username = credentials.get("username");
            String password = credentials.get("password");

            user = UserService.create(username, password);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(List.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (DuplicateKeyException ex) {
            return new ResponseEntity<>(List.of("The provided username already exists"), HttpStatus.BAD_REQUEST);
        }

        HashMap<String, Integer> map = new HashMap<>();
        map.put("appUserId", user.getUserId());

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
