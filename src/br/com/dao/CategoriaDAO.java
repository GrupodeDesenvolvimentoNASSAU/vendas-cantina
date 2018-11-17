package br.com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.interfaces.CategoriaDAOI;
import br.com.jdbc.Conexao;
import br.com.model.Categoria;

public class CategoriaDAO implements CategoriaDAOI{
	Conexao c;
	
	public CategoriaDAO() {
		c = new Conexao();
	}
	
	private Categoria empacotarCat(ResultSet rs) throws SQLException {
	Categoria c = new Categoria();
	c.setId(rs.getInt(1));
	c.setNome(rs.getString(2));
	return c;
}

public ArrayList<Categoria> listaCategoria() {

	ArrayList<Categoria> listaCategoria = new ArrayList<>();
	try {
		Connection con = c.getConexao();
		String sql = "select * from categorias";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Categoria c = new Categoria();
		while (rs.next()) {
			c = empacotarCat(rs);
			listaCategoria.add(c);
			c = null;
		}
		con.close();
		stmt.close();
		rs.close();

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return listaCategoria;
}

}
