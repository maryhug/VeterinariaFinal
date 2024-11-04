package Authentication;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Methods.Login.LoginMethod;
import Panel.PrincipalMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUsers extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textUser;
	private JPasswordField passwordField;
	private JButton btnEnter;
	private JButton btnRegister;
	private JPanel panelLogin;
	public static String globalUsername;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUsers frame = new LoginUsers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginUsers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 520);
		setLocationRelativeTo(null);
		setUndecorated(true);

		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);
		panelLogin.setBackground(new Color(240, 240, 240));

		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBorder(new LineBorder(new Color(221, 169, 246), 2, true)); // Borde azul redondeado
		loginPanel.setBounds(47, 50, 308, 362);
		panelLogin.add(loginPanel);
		loginPanel.setLayout(null);

		JLabel lblNameVet = new JLabel("PetVital");
		lblNameVet.setForeground(new Color(28, 28, 28)); // Azul
		lblNameVet.setFont(new Font("Arial", Font.BOLD, 40));
		lblNameVet.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameVet.setBounds(59, 37, 188, 59);
		loginPanel.add(lblNameVet);

		textUser = new JTextField();
		textUser.setBounds(60, 140, 200, 30);
		textUser.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(221, 169, 246)),
				BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		loginPanel.add(textUser);
		textUser.setColumns(10);

		JLabel lblUser = new JLabel("Usuario:");
		lblUser.setForeground(new Color(100, 100, 100));
		lblUser.setFont(new Font("Arial", Font.PLAIN, 14));
		lblUser.setBounds(60, 115, 65, 14);
		loginPanel.add(lblUser);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(new Color(100, 100, 100));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPassword.setBounds(60, 181, 100, 14);
		loginPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('•');
		passwordField.setBounds(60, 206, 200, 30);
		passwordField.setBorder(BorderFactory.createCompoundBorder(
				new LineBorder(new Color(221, 169, 246)),
				BorderFactory.createEmptyBorder(0, 5, 0, 5)));
		loginPanel.add(passwordField);

		btnEnter = new JButton("Ingresar");
		btnEnter.setBackground(new Color(183, 125, 214));
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setFont(new Font("Arial", Font.BOLD, 14));
		btnEnter.setBorder(new LineBorder(new Color(183, 125, 214), 2, true));
		btnEnter.setFocusPainted(false);
		btnEnter.setBounds(60, 247, 200, 35);
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUser.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Completa los espacios en blanco", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean userFound = LoginMethod.authenticateUser(username, password);
				if (userFound) {
					dispose();
					PrincipalMenu i = new PrincipalMenu();
					i.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginPanel.add(btnEnter);

		btnRegister = new JButton("Registrarse");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setForeground(new Color(221, 169, 246));
		btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
		btnRegister.setBorder(new LineBorder(new Color(221, 169, 246), 2, true));
		btnRegister.setFocusPainted(false);
		btnRegister.setBounds(60, 292, 200, 35);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterUsers p = new RegisterUsers();
				p.setVisible(true);
			}
		});
		loginPanel.add(btnRegister);

		JPanel panelDesignImagen = new JPanel();
		panelDesignImagen.setBackground(new Color(221, 169, 246));
		panelDesignImagen.setBounds(365, 50, 308, 362);
		panelLogin.add(panelDesignImagen);
		panelDesignImagen.setLayout(null);

		JLabel lblImagenLogin = new JLabel("");
		lblImagenLogin.setBackground(Color.WHITE);
		lblImagenLogin.setForeground(Color.WHITE);
		lblImagenLogin.setIcon(new ImageIcon(LoginUsers.class.getResource("/Images/Login.png")));
		lblImagenLogin.setBounds(10, 37, 288, 288);
		panelDesignImagen.add(lblImagenLogin);

		JButton btnClose = new JButton("X");
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(new Color(221, 169, 246));
		btnClose.setBorder(null);
		btnClose.setFocusPainted(false);
		btnClose.setFont(new Font("Arial", Font.BOLD, 15));
		btnClose.setBounds(720, 10, 25, 25);
		btnClose.addActionListener(e -> System.exit(0));
		panelLogin.add(btnClose);

	}
}
