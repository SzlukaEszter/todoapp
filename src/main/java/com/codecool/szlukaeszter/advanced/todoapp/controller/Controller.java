package com.codecool.szlukaeszter.advanced.todoapp.controller;

import com.codecool.szlukaeszter.advanced.todoapp.entity.Status;
import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import com.codecool.szlukaeszter.advanced.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/todos/completed")
    public String removeAllCompleted(){
        todoService.removeCompleted();
        return SUCCESS;
    }

    @PutMapping("/todos/toggle_all")
    public String toggleAllStatus(@RequestParam boolean toggleAll){
        todoService.toggleAll(toggleAll);
        return SUCCESS;
    }


   /*

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
