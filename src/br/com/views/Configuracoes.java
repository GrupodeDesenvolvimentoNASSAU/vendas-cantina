package br.com.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Configuracoes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuracoes frame = new Configuracoes();
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
	public Configuracoes() {
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
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Quest\u00E3o sobre usu\u00E1rios e privil\u00E9gios");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewJgoodiesLabel.setBounds(99, 11, 307, 14);
		contentPane.add(lblNewJgoodiesLabel);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setIcon(new ImageIcon(Configuracoes.class.getResource("/com/img/voltar.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Configuracoes.this.dispose();
				Menu men = new Menu();
				men.setVisible(true);
			}
		});
		btnNewButton.setBounds(379, 255, 105, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Funcionário");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Configuracoes.this.dispose();
				CadastroFuncionario cadFun = new CadastroFuncionario();
				cadFun.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(47, 93, 89, 44);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCadastros = new JLabel("Cadastros:");
		lblCadastros.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCadastros.setBounds(47, 57, 80, 14);
		contentPane.add(lblCadastros);
	}
}
