package br.com.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.interfaces.ProdutoDAOI;
import br.com.jdbc.Conexao;
import br.com.model.Categoria;
import br.com.model.Produto;

public class ProdutoDAO implements ProdutoDAOI {
	private Conexao c;

	public ProdutoDAO() {
		c = new Conexao();

	}

	@Override
	public ArrayList<Produto> listaProdutos(int id_categ) {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		try {
			
			Connection con = c.getConexao();
			String sql = "select * from produtos p where categ_prod = "+id_categ;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Produto p = new Produto();
			while (rs.next()) {
				p = empacotarPro(rs);
				listaDeProdutos.add(p);
				p = null;
			}
			con.close();
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaDeProdutos;
	}

	private Produto empacotarPro(ResultSet rs) throws SQLException {
		Produto p = new Produto();
		p.setId(rs.getInt(1));
		p.setNome(rs.getString(2));
		p.setPreco(rs.getDouble(3));
		p.setCategoria(rs.getInt(4));
		return p;
	}

	

}
