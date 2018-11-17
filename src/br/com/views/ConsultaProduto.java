package br.com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import br.com.dao.ComandosSQL;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.Font;

public class ConsultaProduto extends JFrame {

	private JPanel contentPane;
	private JTable jtProduto;
	DefaultTableModel modelo = new DefaultTableModel();
	private JTextField tfProduConsul;
	public static String colun_vlr;
	public static String colun_vlr1;
	public static String colun_vlr2;
	public static String colun_vlr3;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaProduto frame = new ConsultaProduto();
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
	public ConsultaProduto() {
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
		
		JLabel lblPesquisarProdutos = new JLabel("Pesquisar Produtos:");
		lblPesquisarProdutos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPesquisarProdutos.setForeground(Color.BLACK);
		lblPesquisarProdutos.setBounds(16, 37, 117, 14);
		contentPane.add(lblPesquisarProdutos);
		
		JButton btnNewButton = new JButton("Novo");
		btnNewButton.setIcon(new ImageIcon(ConsultaProduto.class.getResource("/com/img/novo.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto capr = new CadastroProduto();
				capr.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(16, 255, 105, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setIcon(new ImageIcon(ConsultaProduto.class.getResource("/com/img/voltar.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaProduto.this.dispose();
				Menu men = new Menu();
				men.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(379, 255, 105, 35);
		contentPane.add(btnNewButton_1);
		
		tfProduConsul = new JTextField();
		tfProduConsul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				String Produto_consulta = tfProduConsul.getText();
				DefaultTableModel modelo = (DefaultTableModel)jtProduto.getModel();
				modelo.setNumRows(0); //inicia com 0 linhas

				ResultSet rs = ComandosSQL.ConsultaProduto(Produto_consulta);
				try {
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id_prod"), rs.getString("nome_prod"), rs.getString("preco_prod"), rs.getString("nome_categ")});
					}
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		tfProduConsul.setBounds(155, 28, 192, 30);
		contentPane.add(tfProduConsul);
		tfProduConsul.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Pesquisar");
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\Matheus Eduardo\\eclipse-workspace\\Vendas\\img\\pesquisar.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Produto_consulta = tfProduConsul.getText();
				DefaultTableModel modelo = (DefaultTableModel)jtProduto.getModel();
				modelo.setNumRows(0); //inicia com 0 linhas

				ResultSet rs = ComandosSQL.ConsultaProduto(Produto_consulta);
				try {
					while (rs.next()) {
						modelo.addRow(new Object[] {rs.getString("id_prod"), rs.getString("nome_prod"), rs.getString("preco_prod"), rs.getString("nome_categ")});
					}
					rs.close();
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(359, 26, 125, 35);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 69, 465, 175);
		contentPane.add(scrollPane);
		
		jtProduto = new JTable();
		scrollPane.setViewportView(jtProduto);
		jtProduto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Produto", "Valor", "Categoria"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtProduto.getColumnModel().getColumn(0).setPreferredWidth(15);
		jtProduto.getColumnModel().getColumn(1).setPreferredWidth(100);
		jtProduto.getColumnModel().getColumn(2).setPreferredWidth(15);
		
		JSpinner spinner = new JSpinner();
		scrollPane.setColumnHeaderView(spinner);
		
		JButton btnNewButton_3 = new JButton("Editar");
		btnNewButton_3.setIcon(new ImageIcon(ConsultaProduto.class.getResource("/com/img/editar.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (jtProduto.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um registro para editar");
				}else {
					int linha = jtProduto.getSelectedRow();
					String colun_vlr = jtProduto.getValueAt(linha, 0).toString(); // saber coluna para ser capturada o valor (momento está pegando o CODIGO)
					String colun_vlr1 = jtProduto.getValueAt(linha, 1).toString();// saber coluna para ser capturada o valor (momento está pegando o NOME PRODUTO)
					String colun_vlr2 = jtProduto.getValueAt(linha, 2).toString();// saber coluna para ser capturada o valor (momento está pegando o PRECO)
					String colun_vlr3 = jtProduto.getValueAt(linha, 3).toString();// saber coluna para ser capturada o valor (momento está pegando o CATEGORIA)
					//System.out.println(colun_vlr3);
					
					AtualizarProduto ap = new AtualizarProduto();	
					
					setCod(colun_vlr);
					ap.tfCodProd.setText(colun_vlr);
					setProd(colun_vlr1);
					ap.tfNomeProdut.setText(colun_vlr1);
					setPre(colun_vlr2);
					ap.tfPrecoProd.setText(colun_vlr2);
					setCategoria(colun_vlr3);
					
					ap.cbCateg.setSelectedItem(colun_vlr3);
					
					ConsultaProduto.this.dispose();
					ap.setVisible(true);


				}
			}
		});
		btnNewButton_3.setBounds(137, 255, 105, 35);
		contentPane.add(btnNewButton_3);
		
		
		JButton btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.setIcon(new ImageIcon(ConsultaProduto.class.getResource("/com/img/lixeira.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if (jtProduto.getSelectedRow() != -1) {
		            int linha = jtProduto.getSelectedRow();
		            Object captura_cod = jtProduto.getValueAt(linha, 0);
		            Object captura_nome = jtProduto.getValueAt(linha, 1).toString();
		            
		            System.out.println(captura_cod);
		            String nome = "Deseja deletar o produto: " + captura_nome + " ?";
					int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Exclusão ", JOptionPane.YES_NO_OPTION);
					if (opcao_escolhida == JOptionPane.YES_OPTION) {
						ResultSet rs = ComandosSQL.ExcluirProduto(captura_cod);
						((DefaultTableModel) jtProduto.getModel()).removeRow(jtProduto.getSelectedRow());
					    JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!");
					} else {
					    return;
					}
		        } else {
		            JOptionPane.showMessageDialog(null, "Selecione um registro para excluir");
		        }	
			}
			
		});
		btnNewButton_4.setBounds(258, 255, 105, 35);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Logan\\eclipse-workspace\\Vendas\\img\\500_310.png"));
		lblNewLabel.setBounds(0, 0, 500, 310);
		contentPane.add(lblNewLabel);
		
	}

	public void setCod(String cod) {
		this.colun_vlr = cod;
	}
	
	public String getCod() {
		return this.colun_vlr;
	}
	
	public void setProd(String prod) {
		this.colun_vlr1 = prod;
	}
	
	public String getProd() {
		return this.colun_vlr1;
	}
	
	public void setPre(String pre) {
		this.colun_vlr2 = pre;
	}
	
	public String getPre() {
		return this.colun_vlr2;
	}
	
	public void setCategoria(String categoria) {
		this.colun_vlr3 = categoria;
	}
	
	public String getCategoria() {
		return this.colun_vlr3;
	}
}
