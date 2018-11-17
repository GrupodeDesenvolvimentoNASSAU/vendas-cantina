package br.com.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Relatorio extends JFrame {

	private JPanel contentPane;
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					Relatorio frame = new Relatorio();
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
	public Relatorio() {
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
		
		JLabel lblNewLabel = new JLabel("Data inicial :");
		lblNewLabel.setBounds(39, 102, 81, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data final :");
		lblNewLabel_1.setBounds(255, 102, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser dataInicial = new JDateChooser();
		dataInicial.setBounds(114, 97, 125, 25);
		contentPane.add(dataInicial);
		
		JButton btnNewButton = new JButton("Imprimir");
		btnNewButton.setIcon(new ImageIcon(Relatorio.class.getResource("/com/img/imprimir.png")));
		btnNewButton.setBounds(10, 266, 110, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.setIcon(new ImageIcon(Relatorio.class.getResource("/com/img/voltar.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Relatorio.this.dispose();
				Menu men = new Menu();
				men.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(385, 266, 105, 35);
		contentPane.add(btnNewButton_1);
		
		JDateChooser dataFinal = new JDateChooser();
		dataFinal.setBounds(322, 97, 125, 25);
		contentPane.add(dataFinal);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Logan\\eclipse-workspace\\Vendas\\img\\500_310.png"));
		lblNewLabel_2.setBounds(0, 0, 500, 310);
		contentPane.add(lblNewLabel_2);
		
		
		}
}
