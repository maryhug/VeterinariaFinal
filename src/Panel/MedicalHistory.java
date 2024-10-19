package Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Authentication.LoginUsers;
import Methods.MedicalHistory.MedicalHistoryMethod;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedicalHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldHistory;
	private JComboBox<String> comboBoxPatients;
	private JTextArea textAreaHistory;

	public MedicalHistory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 520);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBounds(0, 0, 739, 481);
		contentPane.add(contentPane_1);

		JPanel panelResponse = new JPanel();
		panelResponse.setLayout(null);
		panelResponse.setBounds(240, 11, 489, 459);
		contentPane_1.add(panelResponse);

		JPanel panelOptions = new JPanel();
		panelOptions.setLayout(null);
		panelOptions.setBounds(10, 11, 225, 459);
		contentPane_1.add(panelOptions);

		JButton btnPetsRegistration = new JButton("Ingresar Pacientes");
		btnPetsRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PetsRegistration l = new PetsRegistration();
				l.setVisible(true);
			}
		});
		btnPetsRegistration.setBackground(UIManager.getColor("Button.background"));
		btnPetsRegistration.setBounds(0, 57, 225, 49);
		panelOptions.add(btnPetsRegistration);

		JButton btnPatientList = new JButton("Lista de Pacientes");
		btnPatientList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PatientList lista = new PatientList();
				lista.setVisible(true);
			}
		});
		btnPatientList.setBackground(UIManager.getColor("Button.background"));
		btnPatientList.setBounds(0, 110, 225, 49);
		panelOptions.add(btnPatientList);

		JButton btnMedicalHistory = new JButton("Historial Clínico");
		btnMedicalHistory.setBackground(new Color(240, 240, 240));
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

		JLabel lblHistorialClinico = new JLabel("Historial Clinico");
		lblHistorialClinico.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialClinico.setFont(new Font("Hello Valentica", Font.PLAIN, 50));
		lblHistorialClinico.setBounds(10, 11, 469, 60);
		panelResponse.add(lblHistorialClinico);

		JPanel panelHistory = new JPanel();
		panelHistory.setBounds(10, 92, 469, 356);
		panelResponse.add(panelHistory);
		panelHistory.setLayout(null);

		JLabel lblHistory = new JLabel("Seleccione un paciente:");
		lblHistory.setHorizontalAlignment(SwingConstants.LEFT);
		lblHistory.setBounds(10, 11, 274, 25);
		panelHistory.add(lblHistory);

		comboBoxPatients = new JComboBox<>();
		comboBoxPatients.setBounds(10, 42, 349, 25);
		panelHistory.add(comboBoxPatients);

		JButton btnHistory = new JButton("Buscar");
		btnHistory.setBounds(370, 43, 89, 23);
		panelHistory.add(btnHistory);

		textAreaHistory = new JTextArea();
		textAreaHistory.setBounds(10, 80, 450, 250);
		textAreaHistory.setEditable(false);
		panelHistory.add(textAreaHistory);
		MedicalHistoryMethod.loadPatients(comboBoxPatients);

		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaHistory.setText("");
				String selectedPatient = (String) comboBoxPatients.getSelectedItem();
				if (selectedPatient != null) {
					String history = MedicalHistoryMethod.getMedicalHistory(selectedPatient);
					textAreaHistory.setText(history);
				} else {
					JOptionPane.showMessageDialog(null, "seleccione un paciente.");
				}
			}
		});
	}
}