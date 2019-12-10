package com.codecool.szlukaeszter.advanced.todoapp.repository;

import com.codecool.szlukaeszter.advanced.todoapp.entity.TodoAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoAppUserRepository extends JpaRepository<TodoAppUser, Long> {
}
