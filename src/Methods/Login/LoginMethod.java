package Methods.Login;

import Authentication.LoginUsers;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginMethod {
   private static String PATH = "src/Utils/Users/users.txt";

    public static boolean authenticateUser(String username, String password){
        boolean userFound = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("_");
                if (userData.length == 2 && userData[0].equals(username) && userData[1].equals(password)) {
                    userFound = true;
                    LoginUsers.globalUsername = username;
                    //System.out.println(LoginUsers.globalUsername);
                    break;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de usuarios.");
            ex.printStackTrace();
            return false;
        }
        return userFound;
    }
}
