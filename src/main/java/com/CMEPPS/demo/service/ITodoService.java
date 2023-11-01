
package com.CMEPPS.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.CMEPPS.demo.model.Todo;

public interface ITodoService {

	List<Todo> getTodosByUser(String user);
	
	List<Todo> getTodosPendientesByUser(String user);

	Optional<Todo> getTodoById(long id);

	void updateTodo(Todo todo);

	void addTodo(String name, String desc, Date targetDate, int horasEstimadas, int prioridad, boolean isDone);

	void deleteTodo(long id);

	void saveTodo(Todo todo);
}