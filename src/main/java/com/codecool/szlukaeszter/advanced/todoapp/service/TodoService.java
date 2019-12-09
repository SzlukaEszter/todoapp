package com.codecool.szlukaeszter.advanced.todoapp.service;

import com.codecool.szlukaeszter.advanced.todoapp.entity.Status;
import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import com.codecool.szlukaeszter.advanced.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public void add(Todo todo) { todoRepository.save(todo);
    }

    public Todo find(Long id) {
        return todoRepository.getOne(id);
    }

    public void update(Long id, String title) {
        find(id).setTitle(title);
    }

    public  List<Todo> ofStatus(String statusString) {
        return (statusString == null || statusString.isEmpty()) ? all() : ofStatus(Status.valueOf(statusString.toUpperCase()));
    }

    public  List<Todo> ofStatus(Status status) {
        return todoRepository.findAllByStatus(status);
    }

    public void remove(Long id) {
        todoRepository.delete(find(id));
    }

    public void removeCompleted() {
        todoRepository.deleteAll(ofStatus(Status.COMPLETE));
    }

    public void toggleStatus(Long id, boolean isComplete) {
        Todo todo = find(id);
        if (isComplete) {
            todo.setStatus(Status.COMPLETE);
        } else {
            todo.setStatus(Status.ACTIVE);
        }
    }

    public void toggleAll(boolean complete) {
        List<Todo> all = all();
        all.forEach(t -> t.setStatus(complete ? Status.COMPLETE : Status.ACTIVE));
        todoRepository.saveAll(all);
    }

    public  List<Todo> all() {
        return todoRepository.findAll();
    }
}
