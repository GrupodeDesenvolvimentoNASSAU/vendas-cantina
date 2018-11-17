package br.com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import br.com.dao.ComandosSQL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.TextField;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import java.awt.Font;

public class CadastroProduto extends JFrame {

	private JPanel contentPane;
	public JTextField tfNomeProdut;
	public JTextField tfPrecoProd;
	DefaultTableModel modelo = new DefaultTableModel();
	public JButton btnNewButton_2;
	public JComboBox<String> cbCateg = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {
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
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setIcon(new ImageIcon(CadastroProduto.class.getResource("/com/img/voltar.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto.this.dispose();
				ConsultaProduto consulprod = new ConsultaProduto();
				consulprod.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(379, 255, 105, 34);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Novo produto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(41, 45, 150, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(115, 108, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Pre\u00E7o :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(115, 148, 48, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Categoria :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(89, 186, 76, 20);
		contentPane.add(lblNewLabel_4);
		
		tfNomeProdut = new JTextField();
		tfNomeProdut.setBounds(173, 100, 172, 30);
		contentPane.add(tfNomeProdut);
		tfNomeProdut.setColumns(10);
		
		tfPrecoProd = new JTextField();
		tfPrecoProd.setBounds(173, 140, 172, 30);
		contentPane.add(tfPrecoProd);
		tfPrecoProd.setColumns(10);
		
		ConsultaProduto consulprod = new ConsultaProduto();
			
		JButton btnNewButton_2 = new JButton("...");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CadastroProduto.this.dispose();
				CadastroCategoria categ = new CadastroCategoria();
				categ.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(355, 184, 25, 25);
		contentPane.add(btnNewButton_2);

		try {
			ResultSet rs = ComandosSQL.ConsultaCategoria();			
			cbCateg.addItem("");
		    while(rs.next()){  
		          cbCateg.addItem(rs.getString("nome_categ"));
		          //comboBox.setSelectedIndex(0);
		          cbCateg.setSelectedItem("comboBox.addItem");
		      }  
		   rs.close();

		} catch (SQLException  e1) {
			e1.printStackTrace();
		}
		
		
		getContentPane().add(cbCateg);
		cbCateg.setBounds(173, 181, 172, 30);
		contentPane.add(cbCateg);
		
		JButton btnNewButton_3 = new JButton("Gravar");
		btnNewButton_3.setIcon(new ImageIcon(CadastroProduto.class.getResource("/com/img/salvar.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Nome_Categ = String.valueOf(cbCateg.getSelectedItem());
				String NomeProduto = tfNomeProdut.getText();
				String PrecoProduto = tfPrecoProd.getText();
				//String CodProduto = tfCodProd.getText();
				
				boolean verificarTfNomeProdutNull = tfNomeProdut.getText().isEmpty();
				boolean verificarTfPrecoProd = tfPrecoProd.getText().isEmpty();
				boolean verificarTfCateg = Nome_Categ.isEmpty();
				//boolean verificarTfCodNull = tfCodProd.getText().isEmpty();
				
				if (verificarTfNomeProdutNull == false && verificarTfPrecoProd == false && verificarTfCateg == false) {
					ResultSet rs = ComandosSQL.getNomeCategoria(Nome_Categ);
					
					try {
					while (rs.next()) {
						int IdCateg = rs.getInt("id_categ");
						ResultSet rs1 = ComandosSQL.InserirProduto(NomeProduto, PrecoProduto, IdCateg);
						JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
					}

					//tfCodProd.setText("");
					tfNomeProdut.setText("");
					tfPrecoProd.setText("");
					cbCateg.setSelectedIndex(0);
					tfNomeProdut.requestFocus();
					
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Por favor preencher todas as informações");
				}
			}
		});
		btnNewButton_3.setBounds(16, 255, 105, 34);
		contentPane.add(btnNewButton_3);
	}

}
