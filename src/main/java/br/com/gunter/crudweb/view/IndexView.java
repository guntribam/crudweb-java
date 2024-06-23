package br.com.gunter.crudweb.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexView {

	public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		Path filePath = Paths.get(new File("src/main/webapp/index.html").getAbsolutePath());

		if (Files.exists(filePath)) {
			response.getWriter().write(Files.readString(filePath));
		} else {
			response.getWriter().write("<h1>Arquivo não encontrado</h1>");
		}
	}
}
