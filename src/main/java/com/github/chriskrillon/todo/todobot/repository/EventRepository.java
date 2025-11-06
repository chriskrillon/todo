package com.github.chriskrillon.todo.todobot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.chriskrillon.todo.todobot.model.Event;


@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

}
