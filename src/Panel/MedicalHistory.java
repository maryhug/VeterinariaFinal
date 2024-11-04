package Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Authentication.LoginUsers;
import Methods.MedicalHistory.MedicalHistoryMethod;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;

public class MedicalHistory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldHistory;
	private JComboBox<String> comboBoxPatients;
	private JTextArea textAreaHistory;
	private static String username = LoginUsers.globalUsername;
	private static String PATH3 = "src/Utils/ReportAppointments/Reports" + username + ".txt";


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

		JLabel lblHistorialClinico = new JLabel("Historial Clinico");
		lblHistorialClinico.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialClinico.setFont(new Font("Hello Valentica", Font.PLAIN, 50));
		lblHistorialClinico.setBounds(10, 11, 469, 60);
		panelResponse.add(lblHistorialClinico);

		JPanel panelHistory = new JPanel();
		panelHistory.setBounds(10, 92, 469, 356);
		panelResponse.add(panelHistory);
		panelHistory.setLayout(new BorderLayout());

		JPanel selectPatientPanel = new JPanel();
		selectPatientPanel.setLayout(new FlowLayout());
		JLabel lblHistory = new JLabel("Seleccione un paciente:");
		comboBoxPatients = new JComboBox<>();
		JButton btnHistory = new JButton("Buscar");
		MedicalHistoryMethod.loadPatients(comboBoxPatients);
		JPanel reportPanel = new JPanel();
		reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(reportPanel);

		textAreaHistory = new JTextArea();
		textAreaHistory.setEditable(false);
		textAreaHistory.setPreferredSize(new Dimension(450, 100));

		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportPanel.removeAll();
				textAreaHistory.setText("");
				String selectedPatient = (String) comboBoxPatients.getSelectedItem();
				if (selectedPatient != null) {
					List<String> reports = MedicalHistoryMethod.loadReports(selectedPatient);
					if (reports.isEmpty()) {
						JLabel noReportsLabel = new JLabel("No hay reportes médicos para este paciente.");
						reportPanel.add(noReportsLabel);
					} else {
						for (String report : reports) {
							JPanel reportCard = MedicalHistoryMethod.createReportCard(report);
							reportPanel.add(reportCard);
						}
					}
					String history = MedicalHistoryMethod.getMedicalHistory(selectedPatient);
					textAreaHistory.setText(history);
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un paciente.");
				}
				reportPanel.revalidate();
				reportPanel.repaint();
			}
		});
		selectPatientPanel.add(lblHistory);
		selectPatientPanel.add(comboBoxPatients);
		selectPatientPanel.add(btnHistory);
		panelHistory.add(selectPatientPanel, BorderLayout.NORTH);
		panelHistory.add(textAreaHistory, BorderLayout.CENTER);
		panelHistory.add(scrollPane, BorderLayout.SOUTH);
	}

}