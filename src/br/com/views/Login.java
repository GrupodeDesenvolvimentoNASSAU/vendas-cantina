package br.com.views;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private JPanel panel;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        } 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 576, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240,230,140));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
        this.setUndecorated(true);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBackground(new Color(238,232,170));
		panel.setBounds(132, 62, 304, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Label label = new Label("Usu\u00E1rio :");
		label.setBounds(21, 93, 59, 22);
		label.setForeground(new Color(0, 0, 0));
		panel.add(label);
		label.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 95, 192, 30);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		Label label_1 = new Label("Senha :");
		label_1.setBounds(21, 141, 51, 22);
		panel.add(label_1);
		label_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Acessar");
		btnNewButton.setBounds(21, 201, 105, 34);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(178,34,34));
		panel_1.setBounds(0, 0, 304, 56);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblLogin.setBounds(0, 11, 293, 30);
		panel_1.add(lblLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(86, 141, 192, 30);
		panel.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(173, 201, 105, 34);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Copy cp = new Copy();
				cp.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Login.class.getResource("/com/img/logoMHD.png")));
		btnNewButton_2.setBounds(132, 348, 304, 47);
		contentPane.add(btnNewButton_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mn = new Menu();
				mn.setVisible(true);
				dispose();
			}
		});
	}
}
