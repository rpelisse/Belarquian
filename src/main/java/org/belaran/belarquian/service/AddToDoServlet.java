package org.belaran.belarquian.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
@SuppressWarnings("serial")
public class AddToDoServlet extends HttpServlet {

	private static String PAGE_HEADER = "<html><head><title>Add ToDo</title></head><body>";

	private static String PAGE_FOOTER = "</body></html>";

	@Inject
	private TodoService todoService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<form id=\"add-item\" action=\"./add\" method=\"POST\">");
		writer.println("<input id=\"item_id\" type=\"text\" name=\"id\"><br/><br/>");
		writer.println("<input id=\"item_label\" type=\"text\" name=\"label\"><br/><br/>");
		writer.println("<input id=\"item_submit\"type=\"submit\" value=\"Submit\">");
		writer.println("</form>");
		writer.println(PAGE_FOOTER);
		writer.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		String id = req.getParameter("id");
		String label = req.getParameter("label");
		
		todoService.addToDo(new ToDoItem(Long.valueOf(id), label));
		
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("Item Added:" + id + " - " + label);
		writer.println(PAGE_FOOTER);
		writer.close();
	}
}
