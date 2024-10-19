package Methods.Appointments;

import Authentication.LoginUsers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Modify extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxPatients;
	private JComboBox<String> comboBoxDay;
	private JComboBox<String> comboBoxMonth;
	private JComboBox<String> comboBoxYear;
	private JComboBox<String> comboBoxPlace;

	private List<String> appointments;
	private String username = LoginUsers.globalUsername;
	private String path = "src/Utils/Appointments/appointmets" + username + ".txt";

	public Modify() {
		setLayout(null);
		comboBoxPatients = new JComboBox<>();
		comboBoxPatients.setBounds(10, 59, 220, 22);
		add(comboBoxPatients);

		JLabel lblPatients = new JLabel("Pacientes:");
		lblPatients.setBounds(10, 34, 145, 22);
		add(lblPatients);

		JLabel lblDay = new JLabel("Dia:");
		lblDay.setBounds(268, 13, 45, 22);
		add(lblDay);

		comboBoxDay = new JComboBox<>();
		comboBoxDay.setModel(new DefaultComboBoxModel<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDay.setBounds(323, 11, 45, 26);
		add(comboBoxDay);

		JLabel lblMonth = new JLabel("Mes:");
		lblMonth.setBounds(268, 48, 45, 22);
		add(lblMonth);

		comboBoxMonth = new JComboBox<>();
		comboBoxMonth.setModel(new DefaultComboBoxModel<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		comboBoxMonth.setBounds(323, 46, 80, 26);
		add(comboBoxMonth);

		JLabel lblNewLabelYear = new JLabel("Año:");
		lblNewLabelYear.setBounds(268, 83, 45, 22);
		add(lblNewLabelYear);

		comboBoxYear = new JComboBox<>();
		comboBoxYear.setModel(new DefaultComboBoxModel<>(new String[]{"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxYear.setBounds(323, 81, 80, 26);
		add(comboBoxYear);

		JLabel lblNewLabelPlace = new JLabel("Lugar:");
		lblNewLabelPlace.setBounds(268, 118, 45, 22);
		add(lblNewLabelPlace);

		comboBoxPlace = new JComboBox<>();
		comboBoxPlace.setModel(new DefaultComboBoxModel<>(new String[]{"San Javier", "Centro", "Estadio", "Poblado", "Envigado"}));
		comboBoxPlace.setBounds(323, 116, 80, 26);
		add(comboBoxPlace);

		JLabel lblModify = new JLabel("Modificar");
		lblModify.setHorizontalAlignment(SwingConstants.CENTER);
		lblModify.setBounds(20, 10, 119, 19);
		add(lblModify);

		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(268, 158, 100, 23);
		add(btnSave);

		loadAppointmentsFromFile();

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveModifiedAppointment();
			}
		});
	}

	private void loadAppointmentsFromFile() {
		appointments = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			StringBuilder appointment = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				if (!line.trim().isEmpty()) {
					appointment.append(line).append("\n");
				} else {
					appointments.add(appointment.toString().trim());
					appointment.setLength(0);
				}
			}
			if (appointment.length() > 0) {
				appointments.add(appointment.toString().trim());
			}

			for (String app : appointments) {
				if (app.contains("Paciente:")) {
					String[] parts = app.split("\n");
					String patient = parts[0].split(":")[1].trim();
					comboBoxPatients.addItem(patient);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar las citas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveModifiedAppointment() {
		int selectedIndex = comboBoxPatients.getSelectedIndex();
		if (selectedIndex >= 0) {
			String selectedPatient = (String) comboBoxPatients.getSelectedItem();
			String selectedDay = (String) comboBoxDay.getSelectedItem();
			String selectedMonth = (String) comboBoxMonth.getSelectedItem();
			String selectedYear = (String) comboBoxYear.getSelectedItem();
			String selectedPlace = (String) comboBoxPlace.getSelectedItem();

			String modifiedAppointment =
					"Paciente: " + selectedPatient + "\n" +
					"Fecha: " + selectedDay + " de " + selectedMonth + " de " + selectedYear + "\n" +
					"Lugar: " + selectedPlace;

			for (int i = 0; i < appointments.size(); i++) {
				String appointment = appointments.get(i);
				if (appointment.contains("Paciente: " + selectedPatient)) {
					appointments.set(i, modifiedAppointment);
					break;
				}
			}
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, false))) {
				for (String appointment : appointments) {
					writer.write(appointment);
					writer.newLine();
					writer.newLine();
				}
				JOptionPane.showMessageDialog(this, "Cita modificada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(this, "Error al guardar la cita", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Por favor selecciona un paciente", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}