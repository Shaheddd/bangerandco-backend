package com.example.bangerandco.controller;

import com.example.bangerandco.model.Role;
import com.example.bangerandco.model.RoleType;
import com.example.bangerandco.model.UserLogin;
import com.example.bangerandco.payload.request.LoginRequest;
import com.example.bangerandco.payload.request.SignUpRequest;
import com.example.bangerandco.payload.response.JwtResponse;
import com.example.bangerandco.payload.response.MessageResponse;
import com.example.bangerandco.repository.RoleRepository;
import com.example.bangerandco.repository.UserRepository;
import com.example.bangerandco.security.InputUserDetails;
import com.example.bangerandco.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Validated LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        InputUserDetails inputUserDetails = (InputUserDetails) authentication.getPrincipal();
        List<String> roles = inputUserDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

       return ResponseEntity.ok(new JwtResponse
               (jwt,
               inputUserDetails.getId(),
               inputUserDetails.getUsername(),
               inputUserDetails.getEmail(),
               roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Validated SignUpRequest signupRequest) {
        if(userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error! Username is already taken!"));
        }

        if ((userRepository.existsByEmail(signupRequest.getEmail()))) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error! Email is already in use!"));
        }

        UserLogin userLogin = new UserLogin(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> stringRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (stringRoles == null) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error! Role is not found"));
            roles.add(userRole);
        } else {
            stringRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error! Role is not found!"));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleType.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error! Role Not Found"));
                        roles.add(userRole);
                }
            });
        }
        userLogin.setRoles(roles);
        userRepository.save(userLogin);

        return ResponseEntity.ok(new MessageResponse("User Registered Successfully!"));
    }

}
