package br.com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Copy extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Copy frame = new Copy();
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
	public Copy() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(SystemColor.textHighlightText);
		panel.setBounds(47, 27, 342, 197);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JTextPane txtpnSistemaEmDesenvolvimento = new JTextPane();
		txtpnSistemaEmDesenvolvimento.setFont(new Font("Calibri", Font.BOLD, 16));
		txtpnSistemaEmDesenvolvimento.setBounds(53, 11, 254, 33);
		txtpnSistemaEmDesenvolvimento.setText("Sistema em desenvolvimento por :");
		panel.add(txtpnSistemaEmDesenvolvimento);
		
		JEditorPane dtrpnDenilson = new JEditorPane();
		dtrpnDenilson.setFont(new Font("Arial", Font.PLAIN, 14));
		dtrpnDenilson.setBounds(99, 55, 135, 20);
		dtrpnDenilson.setText("Denilson Naz\u00E1rio;");
		panel.add(dtrpnDenilson);
		
		JTextPane txtpnMatheusEduardo = new JTextPane();
		txtpnMatheusEduardo.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnMatheusEduardo.setText("Matheus Eduardo;");
		txtpnMatheusEduardo.setBounds(99, 86, 135, 20);
		panel.add(txtpnMatheusEduardo);
		
		JTextPane txtpnHigorFreitas = new JTextPane();
		txtpnHigorFreitas.setFont(new Font("Arial", Font.PLAIN, 14));
		txtpnHigorFreitas.setText("Higor Freitas.");
		txtpnHigorFreitas.setBounds(99, 125, 135, 20);
		panel.add(txtpnHigorFreitas);
	}
}
