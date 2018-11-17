package br.com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComandosSQL {
	// Definição das variáveis

	static final String URL = "jdbc:mysql://localhost:3306/bd_lanchonete1?useTimezone=true&serverTimezone=UTC"; //incica o caminho do banco de dados
	static final String USER = "root"; // aqui vai o nome usuario que vc quer acessar
	static final String PASS = "123456"; // aqui a senha do seu banco

	public static Connection criarConexao() throws ClassNotFoundException, SQLException{
	Class.forName("org.postgresql.Driver");
	Connection conecta = DriverManager.getConnection(URL, USER, PASS);
	if (conecta != null){
	System.out.println("Conexão efetuada com sucesso...");
	return conecta;
	}
	return null;
	}
	
	public static ResultSet getCadastroProduto(String Produto_consulta) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			// Registrar o drive JDBC
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Registro do JDBC Driver...");
			}

			System.out.println("conectando...");
			// Criando a conexao com os parametros do banco
			con = DriverManager.getConnection(URL, USER, PASS);

			System.out.println("Conectado!");

			// Criando o statement
			stmt = con.createStatement();

			// usando banco
			String SQLUso = "use bd_lanchonete1";
			stmt.execute(SQLUso);

			String sql = "select id_prod,nome_prod , preco_prod, c.nome_categ \r\n" + 
					"from produtos p, categorias c \r\n" + 
					"where p.categ_prod = c.id_categ and nome_prod like '"+ Produto_consulta + "%'";
			rs = stmt.executeQuery(sql);
			// retornando dados
			return rs;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;

	}

		public static String getPreco(String nomeProd) {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				// Registrar o drive JDBC
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("Registro do JDBC Driver...");
				}

				System.out.println("conectando...");
				// Criando a conexao com os parametros do banco
				con = DriverManager.getConnection(URL, USER, PASS);

				System.out.println("Conectado!");

				// Criando o statement
				stmt = con.createStatement();

				// usando banco
				String SQLUso = "use bd_lanchonete1";
				stmt.execute(SQLUso);

				String selectSql = "select p.preco_prod from produtos p where p.nome_prod = '"+nomeProd+"'  ";
				rs = stmt.executeQuery(selectSql);
				// imprimir tabela
				if(rs.next()) {
					
					String preco = rs.getString(1);
					//System.out.println(preco);
					return preco;
				}
				return "erro!";

			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return null;

		}
		
		public static ResultSet getProdutos(String categ) {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				// Registrar o drive JDBC
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("Registro do JDBC Driver...");
				}

				System.out.println("conectando...");
				// Criando a conexao com os parametros do banco
				con = DriverManager.getConnection(URL, USER, PASS);

				System.out.println("Conectado!");

				// Criando o statement
				stmt = con.createStatement();

				// usando banco
				String SQLUso = "use bd_lanchonete1";
				stmt.execute(SQLUso);

				String selectSql = "select p.nome_prod from produtos p where categ_prod = "+categ;
				rs = stmt.executeQuery(selectSql);
				// imprimir tabela
				return rs;

			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return null;

		}
		
		
		
		public static ResultSet getCategorias() {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				// Registrar o drive JDBC
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("Registro do JDBC Driver...");
				}

				System.out.println("conectando...");
				// Criando a conexao com os parametros do banco
				con = DriverManager.getConnection(URL, USER, PASS);

				System.out.println("Conectado!");

				// Criando o statement
				stmt = con.createStatement();

				// usando banco
				String SQLUso = "use bd_lanchonete1";
				stmt.execute(SQLUso);

				String selectSql = "select  c.id_categ, c.nome_categ from categorias c";
				rs = stmt.executeQuery(selectSql);
				// retornando dados
				return rs;

			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			return null;

		}

		public static void teste() {
			Connection con = null;
			Statement stmt = null;
			ResultSet rs = null;

			try {
				// Registrar o drive JDBC
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					System.out.println("Registro do JDBC Driver...");
				}

				System.out.println("conectando...");
				// Criando a conexao com os parametros do banco
				con = DriverManager.getConnection(URL, USER, PASS);

				System.out.println("Conectado!");

				// Criando o statement
				stmt = con.createStatement();

				// usando banco
				String SQLUso = "use bd_lanchonete1";
				stmt.execute(SQLUso);

				String selectSql = "select * from produtos";
				rs = stmt.executeQuery(selectSql);
				// imprimir tabela
				while (rs.next()) {
					// Retrieve by column name
					int id = rs.getInt(1);
					String nomeProd = rs.getString(2);
					String precoProd = rs.getString(3);
					int categ = rs.getInt(4);
					// Display values
					System.out.print(
							"ID: " + id + ", Nome: " + nomeProd + ", Preço: " + precoProd + ", Categoria: " + categ + "\n");
				}

			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {

				try {
					rs.close();
					stmt.close();
					con.close();

				} catch (SQLException e2) {
					// TODO: handle exception
					System.out.println("Portas abertas!");
					e2.printStackTrace();
				}

			}

		}


}
