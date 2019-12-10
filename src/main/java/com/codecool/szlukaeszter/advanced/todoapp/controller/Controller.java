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
                .title(req.get("todo-title"))
                .completed(false)
                .build());
        return SUCCESS;
    }

    @PostMapping("/list")
    Todo[] getList(@RequestParam HashMap<String, String> req) {
        List<Todo> resultList = todoService.ofStatus(req.get("status"));
        return resultList.toArray(new Todo[resultList.size()]);
    }

    @DeleteMapping("/todos/completed")
    public String removeAllCompleted() {
        todoService.removeCompleted();
        return SUCCESS;
    }

    @PutMapping("/todos/toggle_all")
    public String toggleAllStatus(@RequestParam boolean toggleAll) {
        todoService.toggleAll(toggleAll);
        return SUCCESS;
    }

    @DeleteMapping("/todos/{id}")
    public String removeById(@PathVariable long id) {
        todoService.remove(id);
        return SUCCESS;
    }


   /*


    // Update by id
    put("/todos/:id", (req, res) -> {
        TodoDao.update(req.params("id"), req.queryParams("todo-title"));
        return SUCCESS;
    });
*/
   @GetMapping("/todos/{id}")
   public String findById(@PathVariable long id){
       return todoService.find(id).getTitle();
   }

   @PutMapping("/todos/{id}/toggle_status")
    public String toggleStatusById(@PathVariable long id, @RequestParam boolean status){
       System.out.println("id: " + id);
       System.out.println("status: " + status);
       todoService.toggleStatus(id, status);
       return SUCCESS;
   }
}
