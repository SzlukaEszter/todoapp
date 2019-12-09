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

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    private boolean completed;

    public void calculateCompleted(){
        completed = status.equals(Status.COMPLETE);
    }

}
