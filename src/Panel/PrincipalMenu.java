package Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Authentication.LoginUsers;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PrincipalMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelHome;

	public PrincipalMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 755, 520);
		setLocationRelativeTo(null);
		panelHome = new JPanel();
		panelHome.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelHome);
		panelHome.setLayout(null);

		JPanel panelOptions = new JPanel();
		panelOptions.setBounds(10, 11, 225, 449);
		panelHome.add(panelOptions);
		panelOptions.setLayout(null);

		JButton btnPatientList = new JButton("Lista de Pacientes");
		btnPatientList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PatientList lista = new PatientList();
				lista.setVisible(true);
			}
		});
		btnPatientList.setBounds(0, 110, 225, 49);
		panelOptions.add(btnPatientList);

		JButton btnMedicalHistory = new JButton("Historial Clinico");
		btnMedicalHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MedicalHistory l = new MedicalHistory();
				l.setVisible(true);
			}
		});
		btnMedicalHistory.setBounds(0, 162, 225, 49);
		panelOptions.add(btnMedicalHistory);

		JButton btnAppointments = new JButton("Agenda de Citas");
		btnAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Appointments l = new Appointments();
				l.setVisible(true);
			}
		});
		btnAppointments.setBounds(0, 213, 225, 49);
		panelOptions.add(btnAppointments);

		JButton btnExit = new JButton("Salir del programa");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginUsers l = new LoginUsers();
				l.setVisible(true);
			}
		});
		btnExit.setBounds(0, 399, 225, 49);
		panelOptions.add(btnExit);

		JButton btnSearchPatients = new JButton("Busqueda de Pacientes");
		btnSearchPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SearchPatients l = new SearchPatients();
				l.setVisible(true);
			}
		});
		btnSearchPatients.setBounds(0, 268, 225, 49);
		panelOptions.add(btnSearchPatients);
		
		JButton btnPetsRegistration = new JButton("Ingresar de Pacientes");
		btnPetsRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PetsRegistration l = new PetsRegistration();
				l.setVisible(true);
			}
		});
		btnPetsRegistration.setBounds(0, 57, 225, 49);
		panelOptions.add(btnPetsRegistration);

		JPanel panelResponse = new JPanel();
		panelResponse.setBounds(245, 11, 489, 449);
		panelHome.add(panelResponse);
		panelResponse.setLayout(null);

		JLabel lblGifResponse = new JLabel("");
		lblGifResponse.setHorizontalAlignment(SwingConstants.CENTER);
		lblGifResponse.setIcon(new ImageIcon(PrincipalMenu.class.getResource("/images/mainBanner.gif")));
		lblGifResponse.setBounds(0, 121, 489, 200);
		panelResponse.add(lblGifResponse);
		
		JLabel lblTextMenu = new JLabel("Menu Opciones");
		lblTextMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblTextMenu.setFont(new Font("Hello Valentica", Font.PLAIN, 35));
		lblTextMenu.setBounds(152, 55, 205, 55);
		panelResponse.add(lblTextMenu);
	}
}
