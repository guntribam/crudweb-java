package br.com.gunter.crudweb.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	private static final String URL = ConfigUtil.getProperty("db.url");

	public DatabaseManager() {
		try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
			var sql = "CREATE TABLE IF NOT EXISTS task (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)";
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL);
	}

}
