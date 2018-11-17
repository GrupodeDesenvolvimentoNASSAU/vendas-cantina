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
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.jdbc.Conexao;

public class DelecaoFuncionario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField tfNomeFun;
	private JTextField tfID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DelecaoFuncionario frame = new DelecaoFuncionario();
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
	public DelecaoFuncionario() {
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
				DelecaoFuncionario.this.dispose();
				Configuracoes config = new Configuracoes();
				config.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(335, 260, 105, 34);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Dele\u00E7\u00E3o de Funcion\u00E1rios");
		lblNewLabel.setBounds(182, 24, 160, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome :");
		lblNewLabel_1.setBounds(84, 78, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(84, 121, 89, 14);
		contentPane.add(lblNewLabel_4);
		
		tfNomeFun = new JTextField();
		tfNomeFun.setBounds(182, 78, 172, 30);
		contentPane.add(tfNomeFun);
		tfNomeFun.setColumns(10);
		
		tfID = new JTextField();
		tfID.setBounds(182, 113, 46, 30);
		contentPane.add(tfID);
		tfID.setColumns(5);
		
		/*
		JButton btnNewButton_3 = new JButton("Deletar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean verificarTfNomeFunNull = tfNomeFun.getText().isEmpty();
				boolean verificarTfIDNull = tfID.getText().isEmpty();
				
				if (verificarTfNomeFunNull == false && verificarTfIDNull == false) {
				try {
					Connection con = Conexao.criarConexao();
						String idfun = tfID.getText();
						String sql = "delete from funcionario where id_func = '" + idfun + "'";
						PreparedStatement stmt1 = con.prepareStatement(sql);
						stmt1.execute();
						stmt1.close();
						JOptionPane.showMessageDialog(null, "Funcionário deletado");

					
					con.close();
					
					tfNomeFun.setText("");
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
