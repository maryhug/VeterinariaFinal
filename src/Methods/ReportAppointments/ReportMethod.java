package Methods.ReportAppointments;

import Authentication.LoginUsers;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReportMethod {
    private static String username = LoginUsers.globalUsername;
    private static String PATH = "src/Utils/Patients/pacientes" + username + ".txt";
    private static String PATH2 = "src/Utils/Appointments/appointmets" + username + ".txt";
    private static String PATH3 = "src/Utils/ReportAppointments/Reports" + username + ".txt";


    public static String[] loadPetNamesFromFile() {
        List<String> petNames = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre de la mascota: ")) {
                    String petName = line.substring("Nombre de la mascota: ".length()).trim();
                    petNames.add(petName);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return petNames.toArray(new String[0]);
    }


    public static List<String> getAppointmentsForPet(String selectedPet) {
        List<String> appointmentDates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH2))) {
            String line;
            boolean isCorrectPet = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Paciente: ")) {
                    String petName = line.substring("Paciente: ".length()).trim();
                    isCorrectPet = petName.equalsIgnoreCase(selectedPet);
                }
                if (isCorrectPet && line.startsWith("Fecha: ")) {
                    String appointmentDate = line.substring("Fecha: ".length()).trim();
                    appointmentDates.add(appointmentDate);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return appointmentDates;
    }


    public static void saveAppointmentToFile(String petName, String appointmentDay, String appointmentReason) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH3, true))) {
            writer.write("Nombre de la Mascota: " + petName + "\n");
            writer.write("Fecha de la Cita: " + appointmentDay + "\n");
            writer.write("Motivo de la Cita: " + appointmentReason + "\n");
            writer.write("----------------------------------------------------\n");
            System.out.println("Reporte Guardado en " + PATH3);
        } catch (IOException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar la cita.");
        }
    }
}
