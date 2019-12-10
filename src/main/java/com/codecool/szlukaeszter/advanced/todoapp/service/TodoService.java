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
        todoRepository.updateTodoById(id, title);
    }

   public  List<Todo> ofStatus(String statusString) {
        if (statusString == null || statusString.isEmpty()){
            return all();
        }
        else {
            boolean completed = statusString.equals("complete") ? true : false;
            return ofStatus(completed);
        }
    }

   public  List<Todo> ofStatus(boolean completed) {
        return todoRepository.findAllByCompleted(completed);
    }

    public void remove(Long id) {
        todoRepository.delete(find(id));
    }

    public void removeCompleted() {
        todoRepository.deleteAll(ofStatus(true));
    }

    public void toggleStatus(Long id, boolean isComplete) {
       todoRepository.updateStatusById(id, isComplete);
    }

    public void toggleAll(boolean complete) {
    todoRepository.updateAllStatuses(complete);
    }

    public  List<Todo> all() {
        return todoRepository.findAll();
    }
}
