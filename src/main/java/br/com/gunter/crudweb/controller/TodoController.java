package br.com.gunter.crudweb.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.com.gunter.crudweb.model.Task;
import br.com.gunter.crudweb.model.TaskDAO;

public class TodoController {

	private TaskDAO taskDAO;
	private Gson gson;

	public TodoController() {
		taskDAO = new TaskDAO();
		gson = new Gson();
	}

	public void get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Task> todos = taskDAO.getTasks();
		String json = gson.toJson(todos);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(json);
	}

	public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Task task = readJsonFromRequest(req, Task.class);
		taskDAO.salvar(task);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"success\":true}");
	}

	public void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Task task = readJsonFromRequest(req, Task.class);
		taskDAO.update(task);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"success\":true}");
	}


	public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuffer pathInfo = req.getRequestURL();
		int id = getResourceId(pathInfo);
		taskDAO.deleteById(id);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write("{\"success\":true}");
	}
	
	private int getResourceId(StringBuffer pathInfo) throws IOException {
		String[] urlSplitted = pathInfo.toString().split("/");
		String resourceId = urlSplitted[urlSplitted.length - 1];
		int id = Integer.parseInt(resourceId);
		return id;
	}

	private <T> T readJsonFromRequest(HttpServletRequest req, Class<T> clazz) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = req.getReader()) {
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}
		String reqBody = sb.toString();
		return gson.fromJson(reqBody, clazz);
	}

}
