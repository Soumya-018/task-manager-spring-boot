package com.soumya.taskmanager.Controller;

import com.soumya.taskmanager.Dto.LoginRequest;
import com.soumya.taskmanager.Dto.RegisterRequest;
import com.soumya.taskmanager.Entity.User;
import com.soumya.taskmanager.Security.JwtService;
import com.soumya.taskmanager.Service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
      Authentication authentication =  authenticationManager
               .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        return jwtService.generateToken(authentication.getName());


    }
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request){
        User user =new User();
                user.setUsername(request.getUsername());
                user.setEmail(request.getEmail());
                user.setPassword(request.getPassword());

        userService.registerUser(user);
        return "user registered successfully";
    }
}
