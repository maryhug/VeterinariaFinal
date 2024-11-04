package Methods.MedicalHistory;

import Authentication.LoginUsers;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryMethod {
    private static String username = LoginUsers.globalUsername;
    private static String PATH = "src/Utils/Patients/pacientes"+ username +".txt";
    private static String PATH3 = "src/Utils/ReportAppointments/Reports" + username + ".txt";


    public static void loadPatients(JComboBox<String> comboBoxPatients) {
        List<String> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(": ");
                if (parts.length > 1 && parts[0].trim().equals("Nombre de la mascota")) {
                    patients.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        for (String patient : patients) {
            comboBoxPatients.addItem(patient);
        }
    }


    public static String getMedicalHistory(String patientName) {
        StringBuilder history = new StringBuilder();
        boolean patientFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre de la mascota: ") && line.contains(patientName)) {
                    patientFound = true;
                    history.append(line).append("\n");
                } else if (patientFound) {
                    if (line.trim().isEmpty()) {
                        break;
                    }
                    if (line.startsWith("Historial Clinico:") ||
                            line.startsWith("Señas particulares:") ||
                            line.startsWith("Nombre del propietario:") ||
                            line.startsWith("Teléfono del propietario:")) {
                        history.append(line).append("\n");
                    }
                }
            }
            if (history.length() == 0) {
                return "Historial clínico no encontrado para el paciente: " + patientName;
            }
        } catch (IOException e) {
           // e.printStackTrace();
            return "Error al leer el historial clínico.";
        }
        return history.toString().trim();
    }

    public static List<String> loadReports(String petName) {
        List<String> reports = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH3))) {
            String line;
            StringBuilder report = new StringBuilder();
            boolean isMatchingReport = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre de la Mascota:")) {
                    if (report.length() > 0 && isMatchingReport) {
                        reports.add(report.toString());
                        report.setLength(0);
                    }
                    isMatchingReport = line.contains(petName);
                }
                report.append(line).append("\n");
            }
            if (report.length() > 0 && isMatchingReport) {
                reports.add(report.toString());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los reportes.");
        }
        return reports;
    }
    public static JPanel createReportCard(String report) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setPreferredSize(new Dimension(450, 50));
        JTextArea textArea = new JTextArea();
        textArea.setText(report); // Mostrar el reporte completo
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        card.add(textArea, BorderLayout.CENTER);
        return card;
    }


}
