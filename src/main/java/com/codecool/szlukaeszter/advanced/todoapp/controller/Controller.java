package com.codecool.szlukaeszter.advanced.todoapp.controller;

import com.codecool.szlukaeszter.advanced.todoapp.entity.Status;
import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import com.codecool.szlukaeszter.advanced.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class Controller {

    private static final String SUCCESS = "{\"success\":true}";
    @Autowired
    TodoService todoService;

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam HashMap<String, String> req) {
        todoService.add(Todo
                .builder()
                .title(req.get("title"))
                .status(Status.ACTIVE)
                .build());
        return SUCCESS;
    }

    @PostMapping("/list")
    List<Todo> getList(@RequestParam HashMap<String, String> req) {
        List<Todo> resultList = todoService.ofStatus(req.get("status"));
        resultList.stream()
                .forEach(todo -> {
                    todo.calculateCompleted();
                });
        return resultList;
    }


   /*




    // Remove all completed
    delete("/todos/completed", (req, res) -> {
        TodoDao.removeCompleted();
        return SUCCESS;
    });

    // Toggle all status
    put("/todos/toggle_all", (req, res) -> {
        String complete = req.queryParams("toggle-all");
        TodoDao.toggleAll(complete.equals("true"));
        return SUCCESS;
    });

    // Remove by id
    delete("/todos/:id", (req, res) -> {
        TodoDao.remove(req.params("id"));
        return SUCCESS;
    });

    // Update by id
    put("/todos/:id", (req, res) -> {
        TodoDao.update(req.params("id"), req.queryParams("todo-title"));
        return SUCCESS;
    });

    // Find by id
    get("/todos/:id", (req, res) -> TodoDao.find(req.params("id")).getTitle());

    // Toggle status by id
    put("/todos/:id/toggle_status", (req, res) -> {
        boolean completed = req.queryParams("status").equals("true");
        TodoDao.toggleStatus(req.params("id"), completed);
        return SUCCESS;
    });
}
*/
}
