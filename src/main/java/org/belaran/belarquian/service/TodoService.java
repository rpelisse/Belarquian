package org.belaran.belarquian.service;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TodoService {

	private class ToDoItem {
		public long id;
		public String label;
		@Override
		public String toString() { return "(" + id + ") " + label; }
	}
	
	private List<ToDoItem> todos = new ArrayList<>(0);
	
	public void printToDos(PrintStream to) {
		for ( ToDoItem item : todos )
			to.println(item.toString());
    }

	public void addToDo(ToDoItem item) {
		todos.add(item);
	}
	
}
