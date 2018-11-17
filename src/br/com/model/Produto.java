package br.com.model;

public class Produto {

	private int id;
	private String nome;
	private double preco;
	private int categoria;

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(String nome, double preco, int categ) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

}
