package com.codecool.szlukaeszter.advanced.todoapp.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private boolean completed;

    public Status getStatus(){
        return completed ? Status.COMPLETE : Status.ACTIVE;
    }




}
