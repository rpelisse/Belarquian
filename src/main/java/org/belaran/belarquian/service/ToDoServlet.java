package org.belaran.belarquian.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/app")
@SuppressWarnings("serial")
public class ToDoServlet extends HttpServlet {

	private static String PAGE_HEADER = "<html><head><title>ToDo Service</title></head><body>";

	private static String PAGE_FOOTER = "</body></html>";

	@Inject
	private TodoService todoService;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println(PAGE_HEADER);
		writer.println("<ul>");
		for (ToDoItem item : todoService.getTodos()) {
			writer.println("<li><a name=\"" + item.id + "\">" + item.label
					+ "</a></li>");
		}
		writer.println("</ul>");
		writer.println(PAGE_FOOTER);
		writer.close();
	}

}
