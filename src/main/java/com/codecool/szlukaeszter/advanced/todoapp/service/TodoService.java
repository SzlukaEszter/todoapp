package com.codecool.szlukaeszter.advanced.todoapp.service;

import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import com.codecool.szlukaeszter.advanced.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public void add(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo find(Long id) {
        return todoRepository.getOne(id);
    }

    @Transactional
    public void update(Long id, String title) {
        todoRepository.updateTodoById(id, title);
    }

    public List<Todo> ofStatus(String statusString) {
        if (statusString == null || statusString.isEmpty()) {
            return all();
        } else {
            boolean completed = statusString.equals("complete") ? true : false;
            return ofStatus(completed);
        }
    }

    public List<Todo> ofStatus(boolean completed) {
        return todoRepository.findAllByCompleted(completed);
    }

    @Transactional
    public void remove(Long id) {
        todoRepository.delete(find(id));
    }

    @Transactional
    public void removeCompleted() {
        todoRepository.deleteAll(ofStatus(true));
    }

    @Transactional
    public void toggleStatus(Long id, boolean isComplete) {
        todoRepository.updateCompletedById(id, isComplete);
    }

    @Transactional
    public void toggleAll(boolean complete) {
        todoRepository.updateAllCompleted(complete);
    }

    public List<Todo> all() {
        return todoRepository.findAll();
    }
}
