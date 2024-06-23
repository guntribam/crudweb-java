package br.com.gunter.crudweb.model;

public class Task {

	private Integer id;
	private String nome;

	public Task(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", nome=" + nome + "]";
	}

}
