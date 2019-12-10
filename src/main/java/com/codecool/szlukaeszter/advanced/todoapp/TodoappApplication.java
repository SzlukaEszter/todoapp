package com.codecool.szlukaeszter.advanced.todoapp;

import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import com.codecool.szlukaeszter.advanced.todoapp.entity.TodoAppUser;
import com.codecool.szlukaeszter.advanced.todoapp.repository.TodoAppUserRepository;
import com.codecool.szlukaeszter.advanced.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@SpringBootApplication
public class TodoappApplication {


    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoAppUserRepository todoAppUserRepository;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public static void main(String[] args) {
        SpringApplication.run(TodoappApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            Todo first = Todo.builder()
                    .title("First Todo")
                    .completed(false)
                    .build();
            System.out.println(first);

            Todo second = Todo.builder()
                    .title("Second Todo")
                    .completed(false)
                    .build();

            System.out.println(second);

            Todo third = Todo.builder()
                    .title("Third Todo")
                    .completed(false)
                    .build();

            System.out.println(third);

            todoRepository.saveAll(Arrays.asList(first, second, third));

            TodoAppUser user1 = TodoAppUser.builder()
                    .username("Simple")
                    .password(passwordEncoder.encode("password"))
                    .build();

            TodoAppUser user2 = TodoAppUser.builder()
                    .username("Admin")
                    .password(passwordEncoder.encode("password"))
                    .build();

            todoAppUserRepository.saveAll(Arrays.asList(user1, user2));
        };

    }

}
