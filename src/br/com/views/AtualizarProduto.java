package br.com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.dao.ComandosSQL;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.Font;

public class AtualizarProduto extends JFrame {

	private JPanel contentPane;
	public JTextField tfCodProd;
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
	public AtualizarProduto() {
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
				AtualizarProduto.this.dispose();
				ConsultaProduto consulprod = new ConsultaProduto();
				consulprod.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(379, 255, 105, 34);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Editar Produto");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(41, 45, 150, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(115, 119, 48, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Pre\u00E7o :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(115, 159, 48, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Categoria :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(89, 200, 76, 14);
		contentPane.add(lblNewLabel_4);
		
		tfNomeProdut = new JTextField();
		tfNomeProdut.setBounds(173, 111, 172, 30);
		contentPane.add(tfNomeProdut);
		tfNomeProdut.setColumns(10);
		
		tfPrecoProd = new JTextField();
		tfPrecoProd.setBounds(173, 151, 172, 30);
		contentPane.add(tfPrecoProd);
		tfPrecoProd.setColumns(10);
		
		ConsultaProduto consulprod = new ConsultaProduto();

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
		cbCateg.setBounds(173, 192, 172, 30);
		contentPane.add(cbCateg);
		
		JButton btnNewButton_3 = new JButton("Atualizar");
		btnNewButton_3.setIcon(new ImageIcon(AtualizarProduto.class.getResource("/com/img/update.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Nome_Categ = String.valueOf(cbCateg.getSelectedItem());
				String NomeProduto = tfNomeProdut.getText();
				String PrecoProduto = tfPrecoProd.getText();
				String CodProduto = tfCodProd.getText();
				
				boolean verificarTfNomeProdutNull = tfNomeProdut.getText().isEmpty();
				boolean verificarTfPrecoProd = tfPrecoProd.getText().isEmpty();
				boolean verificarTfCateg = Nome_Categ.isEmpty();
				//boolean verificarTfCodNull = tfCodProd.getText().isEmpty();
				
				if(verificarTfNomeProdutNull == false && verificarTfPrecoProd == false && verificarTfCateg == false){
					ResultSet rs = ComandosSQL.getNomeCategoria(Nome_Categ);
					
					try {						
						while (rs.next()) {
							int IdCateg = rs.getInt("id_categ");
							ResultSet rs1 = ComandosSQL.AlterarProduto(NomeProduto, PrecoProduto, IdCateg, CodProduto);
						}
						
						tfCodProd.setText("");
						tfNomeProdut.setText("");
						tfPrecoProd.setText("");
						cbCateg.setSelectedIndex(0);
						tfNomeProdut.requestFocus();
						
						JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
						} 
					
					catch (SQLException | ArrayIndexOutOfBoundsException ex) {
						ex.printStackTrace();
					}
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Por favor preencher todas as informações");
				}
			}
		});
		btnNewButton_3.setBounds(16, 255, 120, 34);
		contentPane.add(btnNewButton_3);
		
		tfCodProd = new JTextField();
		tfCodProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		tfCodProd.setEnabled(false);
		tfCodProd.setEditable(false);
		tfCodProd.setBounds(173, 70, 45, 30);
		contentPane.add(tfCodProd);
		tfCodProd.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("C\u00F3d :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(124, 78, 39, 14);
		contentPane.add(lblNewLabel_2);
	}

}
