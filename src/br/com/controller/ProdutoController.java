package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.CategoriaDAO;
import br.com.dao.ProdutoDAO;
import br.com.model.Categoria;
import br.com.model.Produto;

public class ProdutoController {
	
	private ProdutoDAO prod;
	private CategoriaDAO categ;
	
	
	public ProdutoController() {
		prod = new ProdutoDAO();
		categ = new CategoriaDAO();
	}
	
	 public ArrayList<Produto> getProdutos(int id_categ){
		return prod.listaProdutos(id_categ);
	 }
	 
	 
	 public ArrayList<Categoria> listaCategoria() {
		return categ.listaCategoria();
		 
	 }
	 
	 public Produto getProduto(String nome)  {
		return prod.getProduto(nome);}
	

}
