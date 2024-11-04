package Methods.PatientList;

import Authentication.LoginUsers;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatientListMethod {
   private static String username = LoginUsers.globalUsername;
   private static String PATH = "src/Utils/Patients/pacientes"+username+".txt";

    public static void listPatients(JPanel panel){
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                JLabel lblUser = new JLabel(linea);
                lblUser.setFont(new Font("Arial", Font.PLAIN, 12));
                lblUser.setForeground(Color.BLACK);
                panel.add(lblUser);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        panel.revalidate();
        panel.repaint();
    }
}
