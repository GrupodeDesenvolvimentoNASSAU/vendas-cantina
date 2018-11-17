package br.com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 310);
		contentPane = new JPanel();
		//contentPane.setBackground(new Color(240,230,140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
        this.setUndecorated(true);
		
        JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Menu.class.getResource("/com/img/vendas.png")));
		btnNewButton.setToolTipText("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ViewVendas vna = new ViewVendas();
					vna.setVisible(true);
					dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(95, 45, 90, 75);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon(Menu.class.getResource("/com/img/produtos.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaProduto cp = new ConsultaProduto();
				cp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(95, 163, 90, 75);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setIcon(new ImageIcon(Menu.class.getResource("/com/img/historico.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorio rel = new Relatorio();
				rel.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(314, 45, 90, 75);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setIcon(new ImageIcon(Menu.class.getResource("/com/img/configuracoes.png")));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Configuracoes config = new Configuracoes();
				config.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setBounds(314, 163, 90, 75);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Voltar");
		btnNewButton_4.setIcon(new ImageIcon("C:\\Users\\Logan\\eclipse-workspace\\Vendas\\img\\voltar.png"));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.this.dispose();
				Login log = new Login();
				log.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(385, 264, 105, 35);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Vendas");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewJgoodiesLabel.setBounds(116, 120, 69, 14);
		contentPane.add(lblNewJgoodiesLabel);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Produtos");
		lblNewJgoodiesLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewJgoodiesLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewJgoodiesLabel_1.setBounds(105, 237, 70, 14);
		contentPane.add(lblNewJgoodiesLabel_1);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Hist\u00F3rico");
		lblNewJgoodiesLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewJgoodiesLabel_2.setBounds(330, 120, 74, 14);
		contentPane.add(lblNewJgoodiesLabel_2);
		
		JLabel lblNewJgoodiesLabel_3 = DefaultComponentFactory.getInstance().createLabel("Configura\u00E7\u00F5es");
		lblNewJgoodiesLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewJgoodiesLabel_3.setBounds(314, 238, 95, 14);
		contentPane.add(lblNewJgoodiesLabel_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Logan\\eclipse-workspace\\Vendas\\img\\500_310.png"));
		lblNewLabel.setBounds(0, 0, 500, 310);
		contentPane.add(lblNewLabel);
	}
}

