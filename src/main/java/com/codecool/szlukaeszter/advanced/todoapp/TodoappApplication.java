package com.codecool.szlukaeszter.advanced.todoapp;

import com.codecool.szlukaeszter.advanced.todoapp.entity.Status;
import com.codecool.szlukaeszter.advanced.todoapp.entity.Todo;
import com.codecool.szlukaeszter.advanced.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


@SpringBootApplication
public class TodoappApplication {



	public static void main(String[] args) {
		SpringApplication.run(TodoappApplication.class, args);
	}

		@Autowired
		TodoRepository todoRepository;
	@Bean
	public CommandLineRunner init (){
		return args -> {
			Todo first = Todo.builder()
					.title("First Todo")
					.status(Status.ACTIVE)
					.build();
			System.out.println(first);

			Todo second = Todo.builder()
					.title("Second Todo")
					.status(Status.ACTIVE)
					.build();

			System.out.println(second);

			Todo third = Todo.builder()
					.title("Third Todo")
					.status(Status.ACTIVE)
					.build();

			System.out.println(third);

			todoRepository.saveAll(Arrays.asList(first, second, third));
		};

	}

}
