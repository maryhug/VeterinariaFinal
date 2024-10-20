package Panel;

import Authentication.LoginUsers;
import Methods.ReportAppointments.ReportMethod;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReportAppointment extends JFrame {
    private JPanel contentPane;
    private JComboBox<String> comboBoxPetName;
    private JComboBox<String> comboBoxAppointmentDay;
    private JTextArea textAreaAppointmentReason;

    public ReportAppointment() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 755, 520);
        setLocationRelativeTo(null);
        setTitle("Motivo Cita");

        JPanel panelHome = new JPanel();
        panelHome.setLayout(null);
        setContentPane(panelHome);

        JPanel panelOptions = new JPanel();
        panelOptions.setBounds(10, 11, 225, 449);
        panelHome.add(panelOptions);
        panelOptions.setLayout(null);

        JButton btnPatientList = new JButton("Lista de Pacientes");
        btnPatientList.setBounds(0, 110, 225, 49);
        btnPatientList.addActionListener(e -> {
            dispose();
            PatientList lista = new PatientList();
            lista.setVisible(true);
        });
        panelOptions.add(btnPatientList);

        JButton btnMedicalHistory = new JButton("Historial Clinico");
        btnMedicalHistory.setBounds(0, 162, 225, 49);
        btnMedicalHistory.addActionListener(e -> {
            dispose();
            MedicalHistory l = new MedicalHistory();
            l.setVisible(true);
        });
        panelOptions.add(btnMedicalHistory);

        JButton btnAppointments = new JButton("Agenda de Citas");
        btnAppointments.setBounds(0, 213, 225, 49);
        btnAppointments.addActionListener(e -> {
            dispose();
            Appointments l = new Appointments();
            l.setVisible(true);
        });
        panelOptions.add(btnAppointments);

        JButton btnReports = new JButton("Reporte Cita");
        btnReports.setBounds(0, 323, 225, 49);
        btnReports.addActionListener(e -> {
            dispose();
            ReportAppointment l = new ReportAppointment();
            l.setVisible(true);
        });
        panelOptions.add(btnReports);

        JButton btnSearchPatients = new JButton("Busqueda de Pacientes");
        btnSearchPatients.setBounds(0, 268, 225, 49);
        btnSearchPatients.addActionListener(e -> {
            dispose();
            SearchPatients l = new SearchPatients();
            l.setVisible(true);
        });
        panelOptions.add(btnSearchPatients);

        JButton btnExit = new JButton("Salir del programa");
        btnExit.setBounds(0, 399, 225, 49);
        btnExit.addActionListener(e -> {
            dispose();
            LoginUsers l = new LoginUsers();
            l.setVisible(true);
        });
        panelOptions.add(btnExit);

        JPanel panelReportForm = new JPanel();
        panelReportForm.setBounds(245, 11, 489, 449); // Ajustar el tamaño del formulario
        panelReportForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelReportForm.setLayout(new GridLayout(5, 2, 10, 10));
        panelHome.add(panelReportForm);

        JLabel lblPetName = new JLabel("Nombre de la Mascota:");
        comboBoxPetName = new JComboBox<>(ReportMethod.loadPetNamesFromFile());
        panelReportForm.add(lblPetName);
        panelReportForm.add(comboBoxPetName);

        JLabel lblAppointmentDay = new JLabel("Día de la Cita:");
        comboBoxAppointmentDay = new JComboBox<>();
        panelReportForm.add(lblAppointmentDay);
        panelReportForm.add(comboBoxAppointmentDay);

        JLabel lblAppointmentReason = new JLabel("Motivo del reporte de Cita:");
        textAreaAppointmentReason = new JTextArea(3, 20);
        JScrollPane scrollPane = new JScrollPane(textAreaAppointmentReason);
        panelReportForm.add(lblAppointmentReason);
        panelReportForm.add(scrollPane);

        JButton btnSubmit = new JButton("Guardar Reporte");
        panelReportForm.add(new JLabel());
        panelReportForm.add(btnSubmit);

        btnSubmit.addActionListener(e -> {
            String petName = (String) comboBoxPetName.getSelectedItem();
            String appointmentDay = (String) comboBoxAppointmentDay.getSelectedItem();
            String appointmentReason = textAreaAppointmentReason.getText();

            if (petName != null && appointmentDay != null && !appointmentReason.isEmpty()) {
                ReportMethod.saveAppointmentToFile(petName, appointmentDay, appointmentReason);
                JOptionPane.showMessageDialog(null, "Reporte Guardado para " + petName + " el " + appointmentDay);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
            }
        });
        comboBoxPetName.addActionListener(e -> {
            String selectedPet = (String) comboBoxPetName.getSelectedItem();
            List<String> appointmentDates = ReportMethod.getAppointmentsForPet(selectedPet);
            comboBoxAppointmentDay.removeAllItems();
            for (String date : appointmentDates) {
                comboBoxAppointmentDay.addItem(date);
            }
        });
    }
}
