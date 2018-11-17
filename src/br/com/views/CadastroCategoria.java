package br.com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.dao.ComandosSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CadastroCategoria extends JFrame {

	private JPanel contentPane;
	private JTextField tfCateg;
	private static JTable jtCategoria;
	DefaultTableModel modelo = new DefaultTableModel();
	private final JTextField tfCod = new JTextField();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCategoria frame = new CadastroCategoria();
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
	public CadastroCategoria() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 310);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240,230,140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
        this.setUndecorated(true);
        
		
		JButton btnNewButton = new JButton("Gravar");
		btnNewButton.setIcon(new ImageIcon(CadastroCategoria.class.getResource("/com/img/salvar.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){

				boolean verificarTfCodNula = tfCod.getText().isEmpty();
				boolean verificarTfCategNula1 = tfCateg.getText().isEmpty();

					if (tfCateg.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(null, "Insira um nome válido");
					}
					else {
						if(verificarTfCodNula == true && verificarTfCategNula1 == false) {
							String Categoria_consulta = tfCateg.getText();
							ResultSet rs = ComandosSQL.InserirCategoria(Categoria_consulta);
							
							JOptionPane.showMessageDialog(null, "Categoria cadastrada com sucesso!"); 
					}
						if(verificarTfCodNula == false){
							try {
								int linha = jtCategoria.getSelectedRow();
								String Categoria_consulta = tfCateg.getText();
								String colun_vlr = jtCategoria.getValueAt(linha, 0).toString(); // saber coluna para ser capturada o valor (momento está pegando o CODIGO)
								
								ResultSet rs = ComandosSQL.AlterarCategoria(Categoria_consulta, colun_vlr);

								JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
								} 
							
							catch (ArrayIndexOutOfBoundsException e) {
								e.printStackTrace();
							}
						}
					
				}

				
				tfCod.setText("");
				tfCateg.setText("");
				tfCateg.requestFocus();
				
				try {
					
					ResultSet rs = ComandosSQL.ConsultaCategoria();
					
					DefaultTableModel modelo = (DefaultTableModel)jtCategoria.getModel();
					modelo.setNumRows(0); //inicia com 0 linhas
					
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id_categ"), rs.getString("nome_categ")});
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		});
		
		btnNewButton.setBounds(17, 259, 105, 35);
		contentPane.add(btnNewButton);
		
		tfCateg = new JTextField();
		tfCateg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				ResultSet rs = ComandosSQL.ConsultaCategoria();
			
				DefaultTableModel modelo = (DefaultTableModel)jtCategoria.getModel();
				modelo.setNumRows(0); //inicia com 0 linhas
				
				while (rs.next()) {
					modelo.addRow(new Object[] {rs.getString("id_categ"), rs.getString("nome_categ")});
				}
			
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		tfCateg.setBounds(249, 67, 172, 30);
		contentPane.add(tfCateg);
		tfCateg.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 108, 465, 140);
		contentPane.add(scrollPane);
		
		jtCategoria = new JTable(new DefaultTableModel(
				
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Categoria"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(jtCategoria);
		
		JSpinner spinner = new JSpinner();
		scrollPane.setColumnHeaderView(spinner);
		
		JButton btnNewButton_2 = new JButton("Excluir");
		btnNewButton_2.setIcon(new ImageIcon(CadastroCategoria.class.getResource("/com/img/lixeira.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (jtCategoria.getSelectedRow() != -1) {
		            int linha = jtCategoria.getSelectedRow();
		            Object linha_deletar = (jtCategoria.getValueAt(linha, 0));       // saber coluna para ser capturada o valor (momento está pegando o CODIGO)
		            String linha_nome = jtCategoria.getValueAt(linha, 1).toString(); // saber coluna para ser capturada o valor (momento está pegando o NOME DA CATEGORIA)
		            String nome = "Deseja deletar a categoria: " + linha_nome + " ?";
					
					int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Exclusão ", JOptionPane.YES_NO_OPTION);
					
					if (opcao_escolhida == JOptionPane.YES_OPTION) {
						ResultSet rsc = ComandosSQL.getProd_vinculado_categ(linha_deletar);
						try {
							while (rsc.next()) {
								int Total = rsc.getInt("TOTAL");
								if (Total == 0) {
									ResultSet rs = ComandosSQL.DeletarCategoria(linha_deletar);
									((DefaultTableModel) jtCategoria.getModel()).removeRow(linha);
								    JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");	
								}
								else {
									JOptionPane.showMessageDialog(null, "Não foi passível excluir a categoria, devido estar vinculada a algum produto.");
								}
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
											    
					}
		        } else {
		            JOptionPane.showMessageDialog(null, "Selecione um registro para excluir");
		        }		
			}
		});
		btnNewButton_2.setBounds(259, 259, 105, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Editar");
		btnNewButton_3.setIcon(new ImageIcon(CadastroCategoria.class.getResource("/com/img/editar.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (jtCategoria.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro para editar");
				}
				else {
					int linha = jtCategoria.getSelectedRow(); // saber linha selecionada
					String colun_vlr = jtCategoria.getValueAt(linha, 0).toString(); // saber coluna para ser capturada o valor (momento está pegando o CODIGO)
					String colun_vlr2 = jtCategoria.getValueAt(linha, 1).toString();// saber coluna para ser capturada o valor (momento está pegando o NOME DA CATEGORIA)
					tfCateg.setText(colun_vlr2);
					tfCod.setText(colun_vlr);
					tfCateg.requestFocus();

				}
				
			}
		});
		btnNewButton_3.setBounds(138, 259, 105, 35);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Voltar");
		btnNewButton_4.setIcon(new ImageIcon(CadastroCategoria.class.getResource("/com/img/voltar.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroCategoria.this.dispose();
				CadastroProduto consulprod = new CadastroProduto();
				consulprod.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(380, 259, 105, 35);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Categorias");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(43, 46, 150, 14);
		contentPane.add(lblNewLabel);
		tfCod.setEnabled(false);
		tfCod.setEditable(false);
		tfCod.setBounds(249, 26, 45, 30);
		contentPane.add(tfCod);
		tfCod.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3d :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(165, 34, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Categoria :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(138, 75, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Logan\\eclipse-workspace\\Vendas\\img\\500_310.png"));
		lblNewLabel_3.setBounds(0, 0, 500, 310);
		contentPane.add(lblNewLabel_3);
		modelo.addColumn("Código");
		modelo.addColumn("Categoria");

		
	}
}