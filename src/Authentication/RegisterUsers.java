package Authentication;

import Methods.Register.RegisterMethod;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterUsers extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel panelRegister;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JButton btnIngresar;

	public RegisterUsers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 520);
		setLocationRelativeTo(null);
		setUndecorated(true); // Elimina los bordes de la ventana para un aspecto más moderno

		panelRegister = new JPanel();
		panelRegister.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelRegister);
		panelRegister.setLayout(null);
		panelRegister.setBackground(new Color(240, 240, 240)); // Fondo gris claro

		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(Color.WHITE);
		registerPanel.setBorder(new LineBorder(new Color(0, 120, 215), 2, true)); // Borde azul redondeado
		registerPanel.setBounds(379, 50, 308, 362);
		panelRegister.add(registerPanel);
		registerPanel.setLayout(null);

		JLabel lblNombreVet = new JLabel("Registrate!");
		lblNombreVet.setForeground(new Color(0, 120, 215)); // Azul
		lblNombreVet.setFont(new Font("Arial", Font.BOLD, 40));
		lblNombreVet.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreVet.setBounds(36, 37, 236, 59);
		registerPanel.add(lblNombreVet);

		textUsuario = new JTextField();
		textUsuario.setBounds(59, 140, 200, 30);
		textUsuario.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(0, 120, 215)), // Borde azul
				BorderFactory.createEmptyBorder(0, 5, 0, 5))); // Padding interno
		registerPanel.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(100, 100, 100));
		lblUsuario.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUsuario.setBounds(59, 115, 65, 14);
		registerPanel.add(lblUsuario);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setForeground(new Color(100, 100, 100));
		lblContraseña.setFont(new Font("Arial", Font.PLAIN, 14));
		lblContraseña.setBounds(59, 181, 100, 14);
		registerPanel.add(lblContraseña);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('•');
		passwordField.setBounds(59, 206, 200, 30);
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(0, 120, 215)), // Borde azul
				BorderFactory.createEmptyBorder(0, 5, 0, 5))); // Padding interno
		registerPanel.add(passwordField);

		btnIngresar = new JButton("Registrarse");
		btnIngresar.setBackground(new Color(0, 120, 215));
		btnIngresar.setForeground(Color.WHITE);
		btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
		btnIngresar.setBorder(new LineBorder(new Color(0, 90, 180), 2, true));
		btnIngresar.setFocusPainted(false);
		btnIngresar.setBounds(59, 260, 200, 35);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsuario.getText();
				String password = new String(passwordField.getPassword());
				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Completa todos los espacios", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				RegisterMethod.RegisterIntrod(username, password);
				dispose();
				LoginUsers l = new LoginUsers();
				l.setVisible(true);
			}
		});
		registerPanel.add(btnIngresar);

		JPanel DiseñoPanel = new JPanel();
		DiseñoPanel.setBackground(new Color(0, 120, 215)); // Fondo azul
		DiseñoPanel.setBounds(40, 50, 308, 362);
		panelRegister.add(DiseñoPanel);
		DiseñoPanel.setLayout(null);

		JLabel lblImagenLogin = new JLabel("");
		lblImagenLogin.setBackground(Color.WHITE);
		lblImagenLogin.setForeground(Color.WHITE);
		lblImagenLogin.setIcon(new ImageIcon(RegisterUsers.class.getResource("/Images/Register.png")));
		lblImagenLogin.setBounds(10, 37, 288, 288);
		DiseñoPanel.add(lblImagenLogin);

// Agregar botón de cierre
		JButton btnClose = new JButton("X");
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(new Color(0, 120, 215));
		btnClose.setBorder(null);
		btnClose.setFocusPainted(false);
		btnClose.setFont(new Font("Arial", Font.BOLD, 15));
		btnClose.setBounds(720, 10, 25, 25);
		btnClose.addActionListener(e -> System.exit(0));
		panelRegister.add(btnClose);
	}
}