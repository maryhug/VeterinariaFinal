package Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Authentication.LoginUsers;
import Methods.SearchPatients.SearchPatientMethods;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchPatients extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldSearch;
	private JComboBox<String> comboBoxPatients;
	private JTextArea textAreaResponse;
	private JButton btnDeletePatient;

	public SearchPatients() {
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

		JLabel lblBusquedaDePacientes = new JLabel("Busqueda de Pacientes");
		lblBusquedaDePacientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusquedaDePacientes.setFont(new Font("Hello Valentica", Font.PLAIN, 50));
		lblBusquedaDePacientes.setBounds(10, 11, 469, 60);
		panelResponse.add(lblBusquedaDePacientes);

		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(10, 82, 469, 366);
		panelResponse.add(panelSearch);
		panelSearch.setLayout(null);

		JLabel lblSearch = new JLabel("Seleccione un paciente: ");
		lblSearch.setHorizontalAlignment(SwingConstants.LEFT);
		lblSearch.setBounds(10, 11, 240, 24);
		panelSearch.add(lblSearch);

		comboBoxPatients = new JComboBox<>();
		comboBoxPatients.setBounds(10, 40, 347, 24);
		panelSearch.add(comboBoxPatients);

		JButton btnSearch = new JButton("Buscar");
		btnSearch.setBackground(new Color(240, 240, 240));
		btnSearch.setBounds(370, 41, 89, 23);
		panelSearch.add(btnSearch);

		textAreaResponse = new JTextArea();
		textAreaResponse.setBounds(10, 80, 440, 250);
		textAreaResponse.setEditable(false);
		panelSearch.add(textAreaResponse);

		btnDeletePatient = new JButton("Eliminar Paciente");
		btnDeletePatient.setBackground(new Color(55, 160, 255));
		btnDeletePatient.setBounds(150, 330, 160, 30);
		btnDeletePatient.setVisible(false);
		panelSearch.add(btnDeletePatient);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPatient = (String) comboBoxPatients.getSelectedItem();
				if (selectedPatient != null) {
					String medicalHistory = SearchPatientMethods.getPatientInfo(selectedPatient);
					textAreaResponse.setText(medicalHistory);
					btnDeletePatient.setVisible(true);
				} else {
					textAreaResponse.setText("Seleccione un paciente.");
					btnDeletePatient.setVisible(false);
				}
			}
		});

		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPatient = (String) comboBoxPatients.getSelectedItem();
				if (selectedPatient != null) {
					int confirmation = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este paciente?",
							"Confirmación de eliminación", JOptionPane.YES_NO_OPTION);

					if (confirmation == JOptionPane.YES_OPTION) {
						SearchPatientMethods.DeletePet(selectedPatient);
						JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente.");
						comboBoxPatients.removeItem(selectedPatient);
						textAreaResponse.setText("");
						btnDeletePatient.setVisible(false);
					}
				}
			}
		});

		SearchPatientMethods.loadPatients(comboBoxPatients);

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


		JButton btnReports = new JButton("Reporte Cita");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ReportAppointment l = new ReportAppointment();
				l.setVisible(true);
			}
		});
		btnReports.setBounds(0, 323, 225, 49);
		panelOptions.add(btnReports);


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

		JButton btnBusquedaPacientes = new JButton("Busqueda de Pacientes");
		btnBusquedaPacientes.setBackground(new Color(240, 240, 240));
		btnBusquedaPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBusquedaPacientes.setBounds(0, 268, 225, 49);
		panelOptions.add(btnBusquedaPacientes);
	}
}