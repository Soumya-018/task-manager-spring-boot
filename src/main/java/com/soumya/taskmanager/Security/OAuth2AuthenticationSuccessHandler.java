package com.soumya.taskmanager.Security;

import com.soumya.taskmanager.Entity.User;
import com.soumya.taskmanager.Repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response
    ,Authentication authentication)throws IOException, ServletException {

        OAuth2User oauthUser =
                (OAuth2User) authentication.getPrincipal();

        System.out.println("User attributes:");
        System.out.println(oauthUser.getAttributes());

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        System.out.println("Google user Email:"+ email);

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {

                    User newUser = new User();

                    newUser.setUsername(name);
                    newUser.setEmail(email);

                    // Random password because Google handles authentication
                    newUser.setPassword(
                            passwordEncoder.encode(UUID.randomUUID().toString())
                    );

                    newUser.setRole("USER");

                    return userRepository.save(newUser);
                });




        String token = jwtService.generateToken(email);
        response.getWriter().write("Google login successfull\nJWT:"+ token);


    }
}
