package br.com.gunter.crudweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.gunter.crudweb.view.IndexView;

@WebServlet("/*")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndexView indexView;
	private TodoController todoController;

	public IndexServlet() {
		indexView = new IndexView();
		todoController = new TodoController();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		if ("/todo".equals(path)) {
			todoController.get(req, resp);
		} else {
			indexView.render(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		todoController.post(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		todoController.put(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		todoController.delete(req, resp);
	}

}
