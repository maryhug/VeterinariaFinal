package Methods.PetsRegistration;

import Authentication.LoginUsers;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class PetsRegistrationMethod {
    private static String username = LoginUsers.globalUsername;
    private static String PATH = "src/Utils/Patients/pacientes"+username+".txt";

    public static void savePatientInfo(String petName, String species, String otherSpecies, String sex, String birthDate,
                                       String distinctiveMarks, String weight, String sterilization, String medicalHistory,
                                       String ownerName, String ownerPhone, String ownerAddress) {
        String content = "Nombre de la mascota: " + petName + "\n" +
                "Especie: " + species + "\n" +
                "Otra especie: " + otherSpecies + "\n" +
                "Sexo: " + sex + "\n" +
                "Fecha de nacimiento: " + birthDate + "\n" +
                "Señas particulares: " + distinctiveMarks + "\n" +
                "Peso: " + weight + "\n" +
                "Esterilización: " + sterilization + "\n" +
                "Historial Clinico: " + medicalHistory + "\n" +
                "Nombre del propietario: " + ownerName + "\n" +
                "Teléfono del propietario: " + ownerPhone + "\n" +
                "Dirección del propietario: " + ownerAddress + "\n" +
                "\n" +
                "\n" + "__________________________________________" + "\n";

        try {
            String path = String.format(PATH);
            FileWriter writer = new FileWriter(path, true);
            writer.write(content);
            writer.close();
            JOptionPane.showMessageDialog(null, "Datos guardados");
        } catch (IOException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar los datos.");
        }
    }

}
