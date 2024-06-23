package br.com.gunter.crudweb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.gunter.crudweb.config.DatabaseManager;

public class TaskDAO {

	private DatabaseManager dm;

	public TaskDAO() {
		dm = new DatabaseManager();
	}

	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<>();
		try (Connection conn = dm.getConnection()) {
			String sql = "SELECT id, name FROM task";
			try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					Task task = new Task(rs.getInt("id"), rs.getString("name"));
					tasks.add(task);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public void salvar(Task task) {
		try (Connection conn = dm.getConnection()) {
			String sql = "INSERT INTO task (name) VALUES (?)";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, task.getNome());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(Task task) {
		try (Connection conn = dm.getConnection()) {
			String sql = "UPDATE task SET name = ? WHERE id = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, task.getNome());
				pstmt.setInt(2, task.getId());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteById(int id) {
		try (Connection conn = dm.getConnection()) {
            String sql = "DELETE FROM task WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}
