package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Task, Long>{
}
