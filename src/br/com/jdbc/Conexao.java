package br.com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.dao.ProdutoDAO;
import br.com.model.Categoria;
import br.com.model.Produto;

public class Conexao {

	// Definição das variáveis
	private static final String JDBC_DRIVER = "com.mysql.jdbc.driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bd_lanchonete1?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "123456";

	public  Connection getConexao() {
		Connection con = null;
		Statement stmt = null;
		

		try {
			// Registrar o drive JDBC
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				System.out.println("Registro do JDBC Driver...");
			}

			System.out.println("conectando...");
			// Criando a conexao com os parametros do banco
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Conectado!");
			// Criando o statement
			stmt = con.createStatement();

			// usando banco
			String SQLUso = "use bd_lanchonete1";
			stmt.execute(SQLUso);
			System.out.println("usando BD lanchonete");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;
	}
	
	public static void main(String[] args) {
		ProdutoDAO p = new ProdutoDAO();
		ArrayList<Categoria> listaProdutos = new ArrayList<Categoria>();
		//listaProdutos = p.listaCategoria();
		for (Categoria produto : listaProdutos) {
			
			System.out.println("Nome: " + produto.getNome());
			
		}
	}

}
