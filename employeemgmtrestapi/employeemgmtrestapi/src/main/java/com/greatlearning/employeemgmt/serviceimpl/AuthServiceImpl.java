package com.greatlearning.employeemgmt.serviceimpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatlearning.employeemgmt.model.Role;
import com.greatlearning.employeemgmt.model.User;
import com.greatlearning.employeemgmt.repository.RoleRepository;
import com.greatlearning.employeemgmt.repository.UserRepository;
import com.greatlearning.employeemgmt.security.JwtTokenProvider;
import com.greatlearning.employeemgmt.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(User user) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(User user) {
    	
        user.setUsername(user.getUsername());
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        //below line is assigining user role to ever new user
        Role userRole = roleRepository.findByName("USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
    }
}
