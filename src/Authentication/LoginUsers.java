package Authentication;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Methods.Login.LoginMethod;
import Panel.PrincipalMenu;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		panelLogin = new JPanel();
		panelLogin.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelLogin);
		panelLogin.setLayout(null);

		JPanel LoginPanel = new JPanel();
		LoginPanel.setForeground(new Color(0, 0, 0));
		LoginPanel
				.setBorder(UIManager.getBorder("TextField.border"));
		LoginPanel.setBounds(47, 50, 308, 362);
		panelLogin.add(LoginPanel);
		LoginPanel.setLayout(null);

		JLabel lblNameVet = new JLabel("PetVital");
		lblNameVet.setForeground(new Color(0, 0, 0));
		lblNameVet.setFont(new Font("Hello Valentica", Font.BOLD, 50));
		lblNameVet.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameVet.setBounds(59, 37, 188, 59);
		LoginPanel.add(lblNameVet);

		textUser = new JTextField();
		textUser.setBounds(60, 140, 200, 30);
		LoginPanel.add(textUser);
		textUser.setColumns(10);

		JLabel lblUser = new JLabel("Usuario: ");
		lblUser.setForeground(new Color(0, 0, 0));
		lblUser.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 17));
		lblUser.setBounds(69, 115, 65, 14);
		LoginPanel.add(lblUser);

		JLabel lblPassword = new JLabel("Contraseña: ");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 17));
		lblPassword.setBounds(70, 181, 100, 14);
		LoginPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(59, 206, 200, 30);
		LoginPanel.add(passwordField);

		btnEnter = new JButton("Ingresar");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUser.getText();
				String password = String.valueOf(passwordField.getPassword());

				if (username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Completa los espacios en blanco");
					return;
				}
				boolean userFound = LoginMethod.authenticateUser(username,password);
				if (userFound) {
					dispose();
					PrincipalMenu i = new PrincipalMenu();
					i.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
				}
			}
		});

		btnEnter.setBounds(110, 247, 110, 30);
		LoginPanel.add(btnEnter);

		btnRegister = new JButton("Registrate");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterUsers p = new RegisterUsers();
				p.setVisible(true);
			}
		});
		btnRegister.setBounds(110, 284, 110, 30);
		LoginPanel.add(btnRegister);

		JPanel panelDesignImagen = new JPanel();
		panelDesignImagen.setBackground(new Color(255, 255, 255));
		panelDesignImagen.setForeground(new Color(255, 255, 255));
		panelDesignImagen
				.setBorder(new TitledBorder(
						new TitledBorder(
								new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255),
										new Color(160, 160, 160)),
								"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
						"", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelDesignImagen.setBounds(365, 50, 308, 362);
		panelLogin.add(panelDesignImagen);
		panelDesignImagen.setLayout(null);

		JLabel lblImagenLogin = new JLabel("");
		lblImagenLogin.setBackground(Color.WHITE);
		lblImagenLogin.setForeground(Color.WHITE);
		lblImagenLogin.setIcon(new ImageIcon(LoginUsers.class.getResource("/Images/Login.png")));
		lblImagenLogin.setBounds(10, 37, 288, 288);
		panelDesignImagen.add(lblImagenLogin);
	}

}
