package Methods.Appointments;

import Authentication.LoginUsers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Delete extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	String username = LoginUsers.globalUsername;
	String path = "src/Utils/Appointments/appointmets" + username + ".txt";

	public Delete() {
		setLayout(null);
		JLabel lblNewLabel = new JLabel("Pacientes: ");
		lblNewLabel.setBounds(10, 38, 145, 22);
		add(lblNewLabel);

		comboBox = new JComboBox<>();
		comboBox.setBounds(10, 65, 220, 22);
		add(comboBox);
		loadPatientsFromFile();

		JButton btnDelete = new JButton("Eliminar");
		btnDelete.setBounds(280, 162, 100, 23);
		add(btnDelete);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedPatient = (String) comboBox.getSelectedItem();
				if (selectedPatient != null) {
					System.out.println("Nombre del paciente seleccionado: " + selectedPatient);
					deletePatient(selectedPatient);
					reloadComboBox();
				}
			}
		});
		setVisible(true);
	}

	private void loadPatientsFromFile() {
		comboBox.removeAllItems();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder appointment = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("Paciente:")) {
					if (!appointment.isEmpty()) {
						comboBox.addItem(appointment.toString().trim());
					}
					appointment.setLength(0);
				}
				appointment.append(line.trim()).append("\n");
			}
			if (!appointment.isEmpty()) {
				comboBox.addItem(appointment.toString().trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deletePatient(String patientToDelete) {
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			StringBuilder appointment = new StringBuilder();
			String line;
			boolean found = false;
			List<String> appointments = new ArrayList<>();

			while ((line = br.readLine()) != null) {
				if (line.trim().startsWith("Paciente:")) {
					if (!appointment.isEmpty()) {
						if (appointment.toString().trim().equals(patientToDelete)) {
							found = true;
						} else {
							appointments.add(appointment.toString().trim());
							appointments.add("");
						}
					}
					appointment.setLength(0);
				}
				appointment.append(line.trim()).append("\n");
			}
			if (!appointment.isEmpty()) {
				if (appointment.toString().trim().equals(patientToDelete)) {
					found = true;
				} else {
					appointments.add(appointment.toString().trim() + "\n");
					appointments.add("");
				}
			}
			if (found) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
					for (String appt : appointments) {
						if (!appt.trim().isEmpty()) {
							bw.write(appt);
						}
					}
				}
				System.out.println("Paciente eliminado: " + patientToDelete);
			} else {
				System.out.println("No se encontró al paciente: " + patientToDelete);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void reloadComboBox() {
		loadPatientsFromFile();
	}

}