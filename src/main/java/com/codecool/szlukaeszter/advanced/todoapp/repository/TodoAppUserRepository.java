package com.codecool.szlukaeszter.advanced.todoapp.repository;

import com.codecool.szlukaeszter.advanced.todoapp.entity.TodoAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoAppUserRepository extends JpaRepository<TodoAppUser, Long> {
    Optional<TodoAppUser>findByUsername(String username);
}
