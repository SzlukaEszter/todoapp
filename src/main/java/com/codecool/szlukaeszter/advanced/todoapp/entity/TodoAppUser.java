package com.codecool.szlukaeszter.advanced.todoapp.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();
}
