/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author FAWZAN
 */
public class Admin extends User {

    public String id;
    public String name;
    public String phone;
    public String confirmPass;

    public Admin() {
        this.isAdmin = true;
    }

    public Admin(String idOrEmail) {

        this.isAdmin = true;

        try {
            File adminFile = new File("files/admins.txt");
            if (adminFile.exists()) {
                BufferedReader data;
                data = new BufferedReader(new FileReader(adminFile));
                String line;
                data.readLine(); // Read the header row
                while ((line = data.readLine()) != null) {

                    if (idOrEmail.compareTo(line.split("\t")[0]) == 0 || idOrEmail.compareTo(line.split("\t")[2]) == 0) {

                        this.id = line.split("\t")[0];
                        this.name = line.split("\t")[1];
                        this.email = line.split("\t")[2];
                        this.phone = line.split("\t")[3];
                        break;

                    }

                }//while

                data.close();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        try {

            File userFile = new File("files/users.txt");
            if (userFile.exists()) {

                BufferedReader data;
                data = new BufferedReader(new FileReader(userFile));

                String line;
                data.readLine(); // Read the header row
                while ((line = data.readLine()) != null) {

                    if (this.email.compareTo(line.split("\t")[0]) == 0) {
                        this.password = line.split("\t")[1];
                        this.securityQ = line.split("\t")[3];
                        this.securityAns = line.split("\t")[4];
                        break;

                    }

                }//while

                data.close();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public boolean updateValidate() {

        //First Name validity
        if (this.name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty", "Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Pass length 8-20
        if (this.password.length() == 0) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (this.password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password length cannot be less than 8", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (this.password.length() > 20) {
            JOptionPane.showMessageDialog(null, "Password length cannot be more than 20", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Confirm Pass                
        if (this.password.compareTo(this.confirmPass) != 0) {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Phone Number validity
        if (this.phone.length() == 0) {
            JOptionPane.showMessageDialog(null, "Phone Number cannot be empty", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!this.phone.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Phone Number validity
        if (this.securityQ.length() == 0) {
            JOptionPane.showMessageDialog(null, "Security Question cannot be empty", "Security Question Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Phone Number validity
        if (this.securityAns.length() == 0) {
            JOptionPane.showMessageDialog(null, "Security Answer cannot be empty", "Security Answer Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean update() {

        try {

            BufferedReader data;
            data = new BufferedReader(new FileReader("files/users.txt"));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            line = data.readLine(); // Read the header row
            inputBuffer.append(line).append("\n");

            while ((line = data.readLine()) != null) {
                if (this.email.compareTo(line.split("\t")[0]) == 0) {
                    String userType;
                    if (this.isAdmin) {
                        userType = "admin";
                    } else {
                        userType = "customer";
                    }

                    line = this.email + "\t" + this.password + "\t" + userType + "\t" + this.securityQ + "\t" + this.securityAns;

                }

                inputBuffer.append(line).append("\n");

            }
            data.close();

            FileOutputStream fileOut;
            fileOut = new FileOutputStream("files/users.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (IOException e) {
            return false;

        }

        try {

            BufferedReader data;
            data = new BufferedReader(new FileReader("files/admins.txt"));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            line = data.readLine(); // Read the header row
            inputBuffer.append(line).append("\n");

            while ((line = data.readLine()) != null) {
                if (this.id.compareTo(line.split("\t")[0]) == 0) {

                    line = this.id + "\t" + this.name + "\t" + this.email + "\t" + this.phone;

                }

                inputBuffer.append(line).append("\n");

            }
            data.close();

            FileOutputStream fileOut;
            fileOut = new FileOutputStream("files/admins.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (IOException e) {
            return false;

        }

        return true;

    }
}
