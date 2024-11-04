package Methods.SearchPatients;

import Authentication.LoginUsers;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SearchPatientMethods {
    private static String username = LoginUsers.globalUsername;
    private static String PATH = "src/Utils/Patients/pacientes"+username+".txt";

    public static void loadPatients(JComboBox<String> comboBoxPatients) {
        try (BufferedReader br = new BufferedReader(new FileReader(String.format(PATH)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre de la mascota: ")) {
                    String patientName = line.substring("Nombre de la mascota: ".length()).trim();
                    comboBoxPatients.addItem(patientName);
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public static String getPatientInfo(String patientName) {
        StringBuilder patientInfo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(String.format(PATH)))) {
            String line;
            boolean patientFound = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Nombre de la mascota: ") && line.contains(patientName)) {
                    patientFound = true;
                }
                if (patientFound) {
                    patientInfo.append(line).append("\n");
                    if (line.trim().isEmpty()) {
                        break;
                    }
                }
            }
            if (patientInfo.length() == 0) {
                return "Información no encontrada para el paciente: " + patientName;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "Error al leer la información del paciente.";
        }
        return patientInfo.toString().trim();
    }

    public static void DeletePet(String nombreMascota) {
        List<String> lineasArchivo = new ArrayList<>();
        boolean eliminarBloque = false;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains("Nombre de la mascota: " + nombreMascota)) {
                    eliminarBloque = true;
                }
                if (eliminarBloque) {
                    if (linea.startsWith("__________________________________________")) {
                        eliminarBloque = false;
                    }
                    continue;
                }
                lineasArchivo.add(linea);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATH))) {
            for (String linea : lineasArchivo) {
                pw.println(linea);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        //System.out.println("El bloque de la mascota '" + nombreMascota + "' ha sido eliminado.");
    }

}