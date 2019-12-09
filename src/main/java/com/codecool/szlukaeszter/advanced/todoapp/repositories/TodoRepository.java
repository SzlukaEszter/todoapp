package com.codecool.szlukaeszter.advanced.todoapp.repositories;


import com.codecool.szlukaeszter.advanced.todoapp.model.Status;
import com.codecool.szlukaeszter.advanced.todoapp.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo>findAllByStatus(Status status);
}
