package com.codecool.szlukaeszter.advanced.todoapp.repository;


import com.codecool.szlukaeszter.advanced.todoapp.entity.Status;
import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

   List<Todo>findAllByCompleted(boolean completed);

    @Query("update Todo t set t.completed = ?2 where t.id = ?1")
    @Modifying(clearAutomatically = true)
    int updateCompletedById(long id, boolean completed);

    @Query("update Todo t set t.completed = ?1")
    @Modifying(clearAutomatically = true)
    int updateAllCompleted(boolean completed);

    @Query("update Todo t set t.title = ?2 where t.id = ?1")
    @Modifying(clearAutomatically = true)
    int updateTodoById(long id, String title);



}
