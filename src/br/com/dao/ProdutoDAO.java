package br.com.dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import br.com.interfaces.ProdutoDAOI;
import br.com.jdbc.Conexao;
import br.com.model.Categoria;
import br.com.model.Produto;

public class ProdutoDAO implements ProdutoDAOI {

	public ProdutoDAO() {
		//c = new Conexao();

	}

	@Override
	public ArrayList<Produto> listaProdutos(int id_categ) {
		ArrayList<Produto> listaDeProdutos = new ArrayList<>();
		
		try {
			
			Connection con = Conexao.getInstancia();
			String sql = "select * from produtos p where categ_prod = ? order by p.nome_prod asc";
			PreparedStatement stmt =  (PreparedStatement) con.prepareStatement(sql);
			stmt.setInt(1, id_categ);
			ResultSet rs = stmt.executeQuery();
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

	@Override
	public Produto getProduto(String nome){
		PreparedStatement stmt = null;
		ResultSet rs =null;
		Connection con = null;
		
try {
			con = Conexao.getInstancia();
			String sql = "select * from produtos p where nome_prod = ?";
			 stmt =  (PreparedStatement) con.prepareStatement(sql);
			stmt.setString(1, nome);
			 rs = stmt.executeQuery();
			Produto p = new Produto();
			while (rs.next()) {
				p = empacotarPro(rs);
				return p;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
				stmt.close();
				rs.close();
				System.out.println("conexao fechada!");
				
			} catch (SQLException e2) {e2.printStackTrace();}
			
		}
		
		return null;
	}



}
