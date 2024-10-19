package Methods.Register;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterMethod {
    private static String PATH = "src/Utils/Users/users.txt";

    public static void RegisterIntrod(String username, String password){
        try {
            File file = new File(PATH);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(username + "_" + password);
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            }
        } catch (
                IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos.");
            ex.printStackTrace();
        }
    }
}
