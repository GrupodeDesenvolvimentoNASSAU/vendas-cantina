package br.com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.dao.ComandosSQL;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.jdbc.Conexao;

import javax.swing.JTextPane;
import javax.swing.JCheckBox;

public class CadastroFuncionario extends JFrame {
	

	private JPanel contentPane;
	private JTextField tfNomeFun;
	private JTextField tfSenha;
	private JTextField tfTipo;
	int priv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFuncionario frame = new CadastroFuncionario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroFuncionario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240,230,140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
        this.setUndecorated(true);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionario.this.dispose();
				Configuracoes config = new Configuracoes();
				config.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(335, 260, 105, 34);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Funcionários");
		lblNewLabel.setBounds(182, 24, 160, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome :");
		lblNewLabel_1.setBounds(84, 78, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("senha:");
		lblNewLabel_3.setBounds(84, 124, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("tipo");
		lblNewLabel_4.setBounds(82, 165, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		tfNomeFun = new JTextField();
		tfNomeFun.setBounds(182, 78, 172, 30);
		contentPane.add(tfNomeFun);
		tfNomeFun.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(182, 116, 172, 30);
		contentPane.add(tfSenha);
		tfSenha.setColumns(10);
		
		tfTipo = new JTextField();
		tfTipo.setBounds(182, 157, 46, 30);
		contentPane.add(tfTipo);
		tfTipo.setColumns(5);
		
		JLabel lblVendedor = new JLabel("0 = Vendedor, 1 = Gerente");
		lblVendedor.setBounds(252, 161, 160, 22);
		contentPane.add(lblVendedor);
		
		
		/*JCheckBox chckbxDarPrivilgios = new JCheckBox("Dar privil\u00E9gios");
		chckbxDarPrivilgios.setBounds(178, 194, 149, 23);
		contentPane.add(chckbxDarPrivilgios);
		
		if (chckbxDarPrivilgios.isSelected()) {
            priv = 1;
        } else {
        	priv = 0;
        }*/
		
		
		
		JButton btnNewButton_4 = new JButton("Deletar funcionário");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroFuncionario.this.dispose();
				DelecaoFuncionario delfun = new DelecaoFuncionario();
				delfun.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(145, 260, 149, 29);
		contentPane.add(btnNewButton_4);
		
		/*
		JButton btnNewButton_3 = new JButton("Gravar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verificarTfNomeFunNull = tfNomeFun.getText().isEmpty();
				boolean verificarTSenhaNull = tfSenha.getText().isEmpty();
				boolean verificarTfTipoNull = tfTipo.getText().isEmpty();
				
				if (verificarTfNomeFunNull == false && verificarTSenhaNull == false  && verificarTfTipoNull == false) {
				try {
					Connection con = Conexao.criarConexao();
						String sql = "insert into funcionario(nome_func, senha_func, tipo) value ('"+ tfNomeFun.getText() +"','"+tfSenha.getText()+"','"+tfTipo.getText()+"')";
						PreparedStatement stmt1 = con.prepareStatement(sql);
						stmt1.execute();
						stmt1.close();
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

					
					con.close();
					
					tfNomeFun.setText("");
					tfSenha.setText("");
					tfNomeFun.requestFocus();
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Por favor preencher todas as informações referente ao Funcionário");
				}
					
			}
		});
		btnNewButton_3.setBounds(10, 260, 105, 34);
		contentPane.add(btnNewButton_3);
		*/
	}
}