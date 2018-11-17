package br.com.views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Frame;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Pagamento extends JFrame {

	private JPanel contentPane;
	private JTextField entrada;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagamento frame = new Pagamento();
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
	public Pagamento() {

		setTitle("Pagamento");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 200, 334, 241);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Impimir nota");
		chckbxNewCheckBox.setBounds(108, 61, 103, 30);
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnDH = new JButton("");
		btnDH.setIcon(new ImageIcon(Pagamento.class.getResource("/com/img/notes.png")));
		btnDH.setName("Dinheiro");
		btnDH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!entrada.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Erro! venda não finalizada. \n "
							+ "Dica: toque no botão CARTÃO.");
				}else {AcaoPagar(btnDH);}
				
			}
		});
		btnDH.setBounds(12, 106, 90, 75);
		contentPane.add(btnDH);
		
		JButton btnCT = new JButton("");
		btnCT.setIcon(new ImageIcon(Pagamento.class.getResource("/com/img/cards.png")));
		btnCT.setName("Cartao");
		btnCT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AcaoPagar(btnCT);
				
			}
		});
		btnCT.setBounds(214, 106, 90, 75);
		contentPane.add(btnCT);
		
		entrada = new JTextField();
		entrada.setBounds(116, 27, 86, 25);
		contentPane.add(entrada);
		entrada.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Entrada");
		lblNewLabel.setBounds(135, 0, 49, 25);
		contentPane.add(lblNewLabel);
		
	}

private void AcaoPagar(JButton btn) {
	String tx = entrada.getText();
	int res = Vendas.confirmaPgmt(tx, btn);
	if (res == 1 | res == 0) {
		dispose();
		}	
	}
}



