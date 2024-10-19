package Methods.Appointments;

import Authentication.LoginUsers;

import javax.swing.*;
import java.io.*;

public class Add extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBoxPatients;
	private JComboBox<String> comboBoxDay;
	private JComboBox<String> comboBoxMonth;
	private JComboBox<String> comboBoxYear;
	private JComboBox<String> comboBoxPlace;

	String username = LoginUsers.globalUsername;
	String path = "src/Utils/Patients/pacientes" + username + ".txt";

	public Add() {
		setLayout(null);
		comboBoxPatients = new JComboBox<>();
		comboBoxPatients.setBounds(10, 59, 220, 22);
		add(comboBoxPatients);

		loadPetNamesFromFile();

		JLabel lblPatients = new JLabel("Nombre del usuario que quiere la cita: ");
		lblPatients.setBounds(10, 34, 200, 22);
		add(lblPatients);

		JLabel lblDay = new JLabel("Día:");
		lblDay.setBounds(268, 13, 45, 22);
		add(lblDay);

		comboBoxDay = new JComboBox<>();
		comboBoxDay.setModel(new DefaultComboBoxModel<>(new String[]{
				"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
				"21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDay.setBounds(323, 11, 45, 26);
		add(comboBoxDay);

		JLabel lblMonth = new JLabel("Mes:");
		lblMonth.setBounds(268, 48, 45, 22);
		add(lblMonth);

		comboBoxMonth = new JComboBox<>();
		comboBoxMonth.setModel(new DefaultComboBoxModel<>(new String[]{
				"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"}));
		comboBoxMonth.setBounds(323, 46, 80, 26);
		add(comboBoxMonth);

		JLabel lblYear = new JLabel("Año:");
		lblYear.setBounds(268, 83, 45, 22);
		add(lblYear);

		comboBoxYear = new JComboBox<>();
		comboBoxYear.setModel(new DefaultComboBoxModel<>(new String[]{"2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxYear.setBounds(323, 81, 80, 26);
		add(comboBoxYear);

		JLabel lblPlace = new JLabel("Lugar:");
		lblPlace.setBounds(268, 118, 45, 22);
		add(lblPlace);

		comboBoxPlace = new JComboBox<>();
		comboBoxPlace.setModel(new DefaultComboBoxModel<>(new String[]{"San Javier", "Floresta", "Poblado", "Bello", "Copacabana"}));
		comboBoxPlace.setBounds(323, 116, 80, 26);
		add(comboBoxPlace);

		JButton btnSave = new JButton("Guardar");
		btnSave.setBounds(268, 158, 100, 23);
		add(btnSave);

		btnSave.addActionListener(e -> saveAppointment());

		JLabel lblAgg = new JLabel("Agregar");
		lblAgg.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgg.setBounds(20, 10, 119, 19);
		add(lblAgg);
	}

	private void loadPetNamesFromFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Nombre de la mascota:")) {
					String[] parts = line.split(":");
					if (parts.length > 1) {
						String petName = parts[1].trim();
						comboBoxPatients.addItem(petName);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar los nombres de las mascotas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void saveAppointment() {
		String path1 = "src/Utils/Appointments/appointmets" + username + ".txt";
		String selectedPatient = (String) comboBoxPatients.getSelectedItem();
		String selectedDay = (String) comboBoxDay.getSelectedItem();
		String selectedMonth = (String) comboBoxMonth.getSelectedItem();
		String selectedYear = (String) comboBoxYear.getSelectedItem();
		String selectedPlace = (String) comboBoxPlace.getSelectedItem();

		String appointmentDetails =
				"Paciente: " + selectedPatient + "\n" +
				"Fecha: " + selectedDay + " de " + selectedMonth + " de " + selectedYear + "\n" +
				"Lugar: " + selectedPlace + "\n" + "\n\n";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path1, true))) {
			writer.write(appointmentDetails);
			writer.newLine();
			JOptionPane.showMessageDialog(this, "Cita guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al guardar la cita", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}