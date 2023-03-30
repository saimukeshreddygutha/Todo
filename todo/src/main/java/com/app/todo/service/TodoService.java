package com.app.todo.service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

import com.app.todo.entity.Todo;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todoCount = 0;
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername() == username;
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(String username, String desc, LocalDate targetDate, boolean done) {
		
		
		Todo todo = new Todo(++todoCount, username, desc, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	
	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
