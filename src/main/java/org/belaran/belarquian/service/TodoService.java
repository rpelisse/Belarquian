package org.belaran.belarquian.service;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TodoService {

	private List<ToDoItem> todos = new ArrayList<>(0);
	
	public void printToDos(PrintStream to) {
		for ( ToDoItem item : todos )
			to.println(item.toString());
    }

	public void addToDo(ToDoItem item) {
		todos.add(item);
	}

	public List<ToDoItem> getTodos() {
		return todos;
	}

	public void setTodos(List<ToDoItem> todos) {
		this.todos = todos;
	}
	
	
}
