package com.codecool.szlukaeszter.advanced.todoapp.security;

import com.codecool.szlukaeszter.advanced.todoapp.entity.TodoAppUser;
import com.codecool.szlukaeszter.advanced.todoapp.repository.TodoAppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private TodoAppUserRepository users;

    public CustomUserDetailService(TodoAppUserRepository users) {
        this.users = users;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TodoAppUser user = users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));

        return new User(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}