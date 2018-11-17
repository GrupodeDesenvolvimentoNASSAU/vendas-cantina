package br.com.views;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import javax.swing.table.DefaultTableModel;


import java.awt.Frame;
import java.awt.Toolkit;



import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import br.com.controller.ProdutoController;
import br.com.dao.ComandosSQL;
import br.com.model.Categoria;
import br.com.model.Produto;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;

public class ViewVendas extends JFrame {
	private static String[] colunas = new String[] { "Produto", "Preço" };
	private static String[][] dados = new String[][] {};

	static DefaultTableModel model = new DefaultTableModel(dados, colunas);
	private JPanel contentPane;
	private static JTable table;
	static JLabel displayValor = new JLabel("");
	private static double totalApagar = 0.0;
	private JTextPane adicional = new JTextPane();
	private final ProdutoController produtocontroller = new ProdutoController();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewVendas frame = new ViewVendas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public ViewVendas() throws SQLException {
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2020, 1076);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(1852, 32, 49, 41);
		btnNewButton.setIcon(new ImageIcon(ViewVendas.class.getResource("/com/img/sair.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1135, 265, 215, 217);
		contentPane.add(scrollPane_1);

		table = new JTable();
		table.setRowHeight(30);
		table.setModel(model);

		scrollPane_1.setViewportView(table);
		// botao remover produto da tabela
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(ViewVendas.class.getResource("/com/img/excluir.png")));
		btnNewButton_2.setBounds(1281, 518, 90, 75);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// removendo item a tabela

				if (table.getSelectedRow() != -1) {
					// linha selecionada
					int linha = table.getSelectedRow();
					// pegando valor do campo da jtable
					String valor1 = (String) table.getValueAt(linha, 1);
					// conversao pada double
					double valor = Double.parseDouble(valor1);
					// calculando
					totalApagar = totalApagar - valor;
					// atualizando o valor na tela
					String resultado = String.format("%.2f", totalApagar);
					displayValor.setText("" + resultado);
					// removendo
					model.removeRow(table.getSelectedRow());

				} else {

					JOptionPane.showMessageDialog(null, "Erro! Selecione um item da tabela.");
				}

			}
		});
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(ViewVendas.class.getResource("/com/img/confirmar.png")));
		btnNewButton_3.setBounds(1142, 518, 90, 75);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (table.getRowCount() > 0) {
					ViewPgmt p = new ViewPgmt();
					p.setLocationRelativeTo(null);
					p.setVisible(true);
					
					
					/*
					 * Object[] possibleValues = { "Dinheiro", "Cartão"}; String valorSelec =
					 * (String) JOptionPane.showInputDialog(null, "Formas de Pagamento",
					 * "Confirmação de Pedidos", JOptionPane.INFORMATION_MESSAGE, null,
					 * possibleValues, possibleValues[0]);
					 * 
					 * if (valorSelec != null ) { totalApagar = 0;
					 * //JOptionPane.showConfirmDialog(null, "Deseja imprimir a nota?"); Object[]
					 * options = {"Sim", "Não"}; int
					 * i=JOptionPane.showOptionDialog(null,"Deseja imprimir a nota?","Confirmação",
					 * JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0
					 * ]); model.setDataVector(null, colunas); displayValor.setText("");}
					 */

				} else {
					JOptionPane.showMessageDialog(null, "Erro! \n Lista de pedidos vazia.");
				}
			}
		});
		contentPane.add(btnNewButton_3);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotal.setBounds(1142, 485, 90, 41);
		contentPane.add(lblTotal);
		displayValor.setFont(new Font("Tahoma", Font.BOLD, 20));
		displayValor.setBounds(1249, 485, 101, 41);
		contentPane.add(displayValor);
		// construindo menu

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(60, 286, 924, 585);
		
		construirMenuPedidos(tabbedPane);

		contentPane.add(tabbedPane);

		JButton btnNewButton_5 = new JButton("Adicionar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			if(!adicional.getText().isEmpty() && !adicional.getText().equals("0")) {
				// adicionar item nao cadastrado
				String preco = adicional.getText();
				String novopreco = preco.replace(",", ".");
				// conversao pada double
				double valor = Double.parseDouble(novopreco);
				String vlr = String.valueOf(valor);
				model.addRow(new String[] { "Outros", vlr });
				// calculando

				totalApagar = totalApagar + valor;
				// atualizando o valor na tela
				String resultado = String.format("%.2f", totalApagar);
				displayValor.setText("" + resultado);
				adicional.setText(""); }
			else {JOptionPane.showMessageDialog(null, "Campo vazio");
			
			}

			}
		});
		btnNewButton_5.setBounds(1005, 318, 120, 50);
		contentPane.add(btnNewButton_5);

		adicional.setBounds(1004, 265, 121, 28);
		contentPane.add(adicional);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\denil\\eclipse-workspace\\Vendas\\img\\fundo_principal.jpg"));
		lblNewLabel.setBounds(10, 0, 2020, 1076);
		contentPane.add(lblNewLabel);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		this.setUndecorated(true);
	}

	public void construirMenuPedidos(JTabbedPane tb) throws SQLException {
		
		ArrayList<Categoria> listacateg = new ArrayList<Categoria>();
		listacateg = produtocontroller.listaCategoria();
		for (Categoria categoria : listacateg) {
			ArrayList<Produto> prod = new ArrayList<Produto>();
			prod = produtocontroller.getProdutos(categoria.getId());
			JScrollPane scrollPane = new JScrollPane();
			JPanel panel = new JPanel();
			int barraDeRolagem = 0;
			// adicionando as categorias
			for (Produto produto : prod) {
				// instanciando os bototes
				JButton btnNewButton_1 = new JButton(produto.getNome());
				btnNewButton_1.setPreferredSize(new Dimension(120, 50));
				panel.add(btnNewButton_1);
				barraDeRolagem += 15;
				System.out.println(barraDeRolagem);
				btnNewButton_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String pedido = e.getActionCommand();
						Produto pro = produtocontroller.getProduto(pedido);
						String preco = String.valueOf(pro.getPreco());
						model.addRow(new String[] { pro.getNome(), preco });

						// int n = model.getRowCount() -1;
						double precouni = Double.parseDouble(preco);
						totalApagar += precouni;
						String resultado = String.format("%.2f", totalApagar);
						displayValor.setText("" + resultado);
					}
				});

			}

			scrollPane.setViewportView(panel);
		
			tb.addTab(categoria.getNome(), null, scrollPane, null);
			panel.setPreferredSize(new Dimension(900, barraDeRolagem));
		}

	}

	public static JTable getTable() {
		return table;

	}

	public static int confirmaPgmt(String entrada, JButton btn) {

		if (!entrada.isEmpty()) {
			String novaentrada = entrada.replace(",", ".");
			double valor = 0;
			valor = Double.parseDouble(novaentrada);

			if (valor < totalApagar) {

				System.out.println("CT " + (totalApagar - valor));
				System.out.println("Entrada: " + valor);
				totalApagar = 0;
				model.setDataVector(null, colunas);
				displayValor.setText("");

				return 1;

			} else {
				JOptionPane.showMessageDialog(null, "Erro! a entrada tem que ser menor que o valor total");
				return -1;
			}

		} else {
			System.out.println("Forma de Pagamento: " + btn.getName());
			totalApagar = 0;
			model.setDataVector(null, colunas);
			displayValor.setText("");
			return 0;
		}
	}
	
	public static void imprimirPedido(boolean valor) {
		if (valor) {
			
			
			for (int i = 0; i < table.getRowCount(); i++) {
				System.out.println("Nome: " +table.getValueAt(i, 0) + " Preço: " + table.getValueAt(i, 1));
				
			}System.out.println("Total "+totalApagar);
			
			
		}
		
		
	}
	
	public static void calcularTroco(JLabel jl, String entrada) {
		
		if(!entrada.isEmpty()) {
		String novaentrada = entrada.replace(",", ".");
		double valor = 0;
		valor = Double.parseDouble(novaentrada);
		valor = valor - totalApagar;
		jl.setFont(new Font("Tahoma", Font.BOLD, 20));
		String resultado = String.format("%.2f", valor);
		jl.setText("" + resultado); }
		
	}
	
	
}
