package br.com.views;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import br.com.dao.ComandosSQL;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Button;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Vendas extends JFrame {
	private static String[] colunas = new String[] { "Produto", "Preço" };
	private static String[][] dados = new String[][] {};

	static DefaultTableModel model = new DefaultTableModel(dados, colunas);
	private JPanel contentPane;
	private static JTable table;
	static JLabel displayValor = new JLabel("");
	private static double totalApagar = 0.0;
	private JTextPane adicional = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vendas frame = new Vendas();
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
	public Vendas() throws SQLException {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1076);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(1852, 36, 49, 41);
		btnNewButton.setIcon(new ImageIcon(Vendas.class.getResource("/com/img/sair.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu m = new Menu();
				m.setVisible(true);
				dispose();
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(1443, 276, 400, 217);

		table = new JTable();
		table.setRowHeight(30);
		table.setModel(model);

		scrollPane_1.setViewportView(table);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setBounds(1443, 554, 90, 75);
		btnNewButton_3.setIcon(new ImageIcon(Vendas.class.getResource("/com/img/confirmar.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getRowCount() > 0) {
					Pagamento p = new Pagamento();
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

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(1518, 500, 90, 41);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		displayValor.setBounds(1656, 509, 101, 41);
		displayValor.setFont(new Font("Tahoma", Font.BOLD, 20));
		// construindo menu

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(127, 288, 924, 585);
		construirMenuPedidos(tabbedPane);

		JButton btnNewButton_5 = new JButton("Adicionar");
		btnNewButton_5.setBounds(1300, 317, 90, 75);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// adicionar item nao cadastrado
				String preco = adicional.getText();
				String novopreco = preco.replace(",", ".");
				model.addRow(new String[] { "Outros", preco });
				// conversao pada double
				double valor = Double.parseDouble(novopreco);
				// calculando
				
				totalApagar = totalApagar + valor;
				// atualizando o valor na tela
				String resultado = String.format("%.2f", totalApagar);
				displayValor.setText("" + resultado);
				adicional.setText("");

			}
		});
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(5, 5, 1908, 1066);
		lblNewLabel.setIcon(new ImageIcon(Vendas.class.getResource("/com/img/fundo_principal.jpg")));
		// botao remover produto da tabela
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBounds(1753, 554, 90, 75);
		btnNewButton_2.setIcon(new ImageIcon(Vendas.class.getResource("/com/img/excluir.png")));
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
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		contentPane.add(tabbedPane);
		adicional.setBounds(1248, 276, 177, 28);
		contentPane.add(adicional);
		contentPane.add(btnNewButton_5);
		contentPane.add(scrollPane_1);
		contentPane.add(lblTotal);
		contentPane.add(btnNewButton_2);
		contentPane.add(displayValor);
		contentPane.add(btnNewButton_3);
		contentPane.add(lblNewLabel);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		this.setUndecorated(true);
	}

	public void construirMenuPedidos(JTabbedPane tb) throws SQLException {
		ResultSet categ = ComandosSQL.getCategorias();

		// adicionando as categorias
		while (categ.next()) {
			ResultSet prod = ComandosSQL.getProdutos(categ.getString(1));
			JScrollPane scrollPane = new JScrollPane();
			JPanel panel = new JPanel();
			int barraDeRolagem = 0;
			while (prod.next()) {
				// instanciando os bototes
				JButton btnNewButton_1 = new JButton(prod.getString(1));
				btnNewButton_1.setPreferredSize(new Dimension(120, 50));
				panel.add(btnNewButton_1);
				barraDeRolagem += 15;
				// System.out.println(barraDeRolagem);
				btnNewButton_1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String pedido = e.getActionCommand();
						String preco = ComandosSQL.getPreco(pedido);
						model.addRow(new String[] { pedido, preco });

						// int n = model.getRowCount() -1;
						double precouni = Double.parseDouble(preco);
						totalApagar += precouni;
						String resultado = String.format("%.2f", totalApagar);
						displayValor.setText("" + resultado);
					}
				});

			}
			scrollPane.setViewportView(panel);
			tb.addTab(categ.getString(2), null, scrollPane, null);
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
				
				System.out.println("CT "+(totalApagar-valor));
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
			System.out.println(" "+btn.getName() );
			totalApagar = 0;
			model.setDataVector(null, colunas);
			displayValor.setText("");
			return 0;
		}
	}
}
