package br.com.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewPgmt extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField entrada;
	private JTextField jTextField;
	JLabel troco ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ViewPgmt dialog = new ViewPgmt();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewPgmt() {
		setBounds(100, 100, 521, 300);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JRadioButton radioimprimi = new JRadioButton("Imprimir");
		radioimprimi.setBounds(59, 94, 109, 47);
		contentPanel.add(radioimprimi);
		
		entrada = new JTextField();
		entrada.setBounds(59, 57, 97, 30);
		contentPanel.add(entrada);
		entrada.setColumns(10);
		
		JButton btndh = new JButton("New button");
		btndh.setName("Dinheiro");
		btndh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewVendas.imprimirPedido(radioimprimi.isSelected());
				
				if(!entrada.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Erro! venda não finalizada. \n "
							+ "Dica: toque no botão CARTÃO.");
				}else {AcaoPagar(btndh);}
			}
		});
		btndh.setBounds(10, 169, 89, 54);
		contentPanel.add(btndh);
		
		JButton btnct = new JButton("New button");
		btnct.setName("CTD");
		btnct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewVendas.imprimirPedido(radioimprimi.isSelected());
				AcaoPagar(btnct);
			}
		});
		btnct.setBounds(145, 169, 89, 54);
		contentPanel.add(btnct);
		
		jTextField = new JTextField();
		jTextField.setBounds(349, 57, 97, 30);
		contentPanel.add(jTextField);
		jTextField.setColumns(10);
		
		JButton btnTroco = new JButton("New button");
		btnTroco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewVendas.calcularTroco(troco, jTextField.getText());
				
				
			}
		});
		btnTroco.setBounds(357, 169, 89, 54);
		contentPanel.add(btnTroco);
		
		troco = new JLabel("");
		troco.setBounds(349, 110, 97, 31);
		contentPanel.add(troco);
		
		JLabel lblNewLabel = new JLabel("Entrada");
		lblNewLabel.setBounds(59, 26, 73, 20);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Troco");
		lblNewLabel_1.setBounds(349, 21, 81, 30);
		contentPanel.add(lblNewLabel_1);
	}
	private void AcaoPagar(JButton btn) {
		String tx = entrada.getText();
		int res = ViewVendas.confirmaPgmt(tx, btn);
		if (res == 1 | res == 0) {
			dispose();
			}	
		}
}
