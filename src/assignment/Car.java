/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author FAWZAN
 */
public class Car {

    public String id;
    public String manufacturer;
    public String model;
    public String color;
    public String seats;
    public String rent;
    public String status;

    public Car(String id, String manufacturer, String model, String color, String seats, String rent, String status) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.seats = seats;
        this.rent = rent;
        this.status = status;
    }

    public Car(String manufacturer, String model, String color, String seats, String rent, String status) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.seats = seats;
        this.rent = rent;
        this.status = status;
    }

    public Car(String id) {

        try {
            File carFile = new File("files/cars.txt");
            if (carFile.exists()) {
                BufferedReader data;
                data = new BufferedReader(new FileReader(carFile));
                String line;
                data.readLine(); // Read the header row
                while ((line = data.readLine()) != null) {

                    if (id.compareTo(line.split("\t")[0]) == 0) {
                        this.id = line.split("\t")[0];
                        this.manufacturer = line.split("\t")[1];
                        this.model = line.split("\t")[2];
                        this.color = line.split("\t")[3];
                        this.seats = line.split("\t")[4];
                        this.rent = line.split("\t")[5];
                        this.status = line.split("\t")[6];

                        break;

                    }

                }//while

                data.close();
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            File folder = new File("files");
            if (!folder.exists()) {
                folder.mkdir();
            }

            File carFile = new File("files/cars.txt");

            if (carFile.createNewFile()) {
                FileWriter fw;
                fw = new FileWriter("files/cars.txt", true);

                fw.write("ID\tManufacturer\tModel\tColor\tNo. of Seats\tRent\tStatus\n");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getNextId() {
        String nextId = "";
        try {
            File carFile = new File("files/cars.txt");
            if (carFile.exists()) {
                BufferedReader data;
                data = new BufferedReader(new FileReader(carFile));
                String line;
                data.readLine(); // Read the header row
                while ((line = data.readLine()) != null) {
                    nextId = String.format("%04d", Integer.parseInt(line.split("\t")[0]) + 1);

                }//while

                data.close();
            } else {
                nextId = String.format("%04d", 1);
            }

        } catch (IOException e) {
            this.id = String.format("%04d", 1);
            e.printStackTrace();
        }
        return nextId;

    }

    public boolean validate() {
        if (this.manufacturer.length() == 0) {
            JOptionPane.showMessageDialog(null, "Manufacturer cannot be empty", "Manufacturer Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //First Name validity
        if (this.model.length() == 0) {
            JOptionPane.showMessageDialog(null, "Model cannot be empty", "Model Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Pass length 8-20
        if (this.color.length() == 0) {
            JOptionPane.showMessageDialog(null, "Color cannot be empty", "Color Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Phone Number validity
        if (this.seats.length() == 0) {
            JOptionPane.showMessageDialog(null, "No. of seats cannot be empty", "Seats Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Phone Number validity
        if (this.rent.length() == 0) {
            JOptionPane.showMessageDialog(null, "Rent cannot be empty", "Rent Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!this.rent.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Invalid Rent", "Rent Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Phone Number validity
        if (this.status.length() == 0) {
            JOptionPane.showMessageDialog(null, "Status cannot be empty", "Security Answer Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String add() {
        this.id = getNextId();

        try {
            File folder = new File("files");
            if (!folder.exists()) {
                folder.mkdir();
            }

            File carFile = new File("files/cars.txt");

            if (carFile.createNewFile()) {
                FileWriter fw;
                fw = new FileWriter("files/cars.txt", true);

                fw.write("ID\tManufacturer\tModel\tColor\tSeats\tRent\tStatus\n");
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //Check if exist
            //If it exists append
            //If doesnt exist crete file and write header row then append

            FileWriter fw;
            fw = new FileWriter("files/cars.txt", true);

            fw.write(this.id + "\t" + this.manufacturer + "\t" + this.model + "\t" + this.color + "\t" + this.seats + "\t" + this.rent + "\t" + this.status + "\n");
            fw.close();
        } catch (IOException e) {

            e.printStackTrace(); //IDK WHAT IS THIS FOR
        }

        return this.id;
    }

    public boolean update() {
        try {

            BufferedReader data;
            data = new BufferedReader(new FileReader("files/cars.txt"));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            line = data.readLine(); // Read the header row
            inputBuffer.append(line).append("\n");

            while ((line = data.readLine()) != null) {
                if (this.id.compareTo(line.split("\t")[0]) == 0) {

                    line = this.id + "\t" + this.manufacturer + "\t" + this.model + "\t" + this.color + "\t" + this.seats + "\t" + this.rent + "\t" + this.status;

                }

                inputBuffer.append(line).append("\n");

            }
            data.close();

            FileOutputStream fileOut;
            fileOut = new FileOutputStream("files/cars.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();

        } catch (IOException e) {
            return false;

        }
        return true;
    }

}
