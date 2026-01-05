package com.todo.ToDo_App.Controller;

import com.todo.ToDo_App.Repositary.UserRepository;
import com.todo.ToDo_App.Service.UserService;
import com.todo.ToDo_App.Utils.JwtUtil;
import com.todo.ToDo_App.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=body.get("password");

        var userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return new ResponseEntity<>("User Not Registered",HttpStatus.UNAUTHORIZED);
        }


        User user=userOptional.get();
        if(!passwordEncoder.matches(password,user.getPassword())){
            return new ResponseEntity<>("Invalid User",HttpStatus.UNAUTHORIZED);
        }


        String token=jwtUtil.generateToken(email);

        return ResponseEntity.ok(Map.of("token",token));

    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String,String> body){
        String email=body.get("email");
        String password=passwordEncoder.encode(body.get("password"));

        if(userRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("User Already Exists", HttpStatus.CONFLICT);
        }

        userService.createUser(User.builder().email(email).password(password).build());

        return new ResponseEntity<>("SuccessFully Registered",HttpStatus.CREATED);
    }
}
