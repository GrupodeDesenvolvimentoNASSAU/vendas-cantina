package br.com.interfaces;

import java.util.ArrayList;

import br.com.model.Categoria;
import br.com.model.Produto;

public interface ProdutoDAOI {
	
	public  ArrayList<Produto> listaProdutos(int id_categ);

}
