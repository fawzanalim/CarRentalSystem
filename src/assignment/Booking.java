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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FAWZAN
 */
public class Booking {
    public String id;
    public String carId;
    public String userId;
    public String paymentId;
    public String status;
    public Date bookingDate = new Date();
    public Date checkoutDate = new Date();
    public Date checkinDate = new Date();
    
    
    public Booking(String id, String carId, String userId, String paymentId, String status, Date bookingDate, Date checkoutDate, Date checkinDate)
    {
        this.id = id;
        this.carId = carId;
        this.userId = userId;
        this.paymentId = paymentId;
        this.status = status;
        this.bookingDate = bookingDate;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }
    public Booking(String carId, String userId, String status, Date bookingDate, Date checkoutDate, Date checkinDate)
    {
        this.carId = carId;
        this.userId = userId;
        this.status = status;
        this.bookingDate = bookingDate;
        this.checkoutDate = checkoutDate;
        this.checkinDate = checkinDate;
    }
    
    public Booking(String id) throws ParseException
    {
        
        try  
        {  
            File bookingFile = new File("files/bookings.txt");
            if (bookingFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(bookingFile));
                String line;
                data.readLine(); // Read the header row
                while((line = data.readLine()) != null)
                {

                    if(id.compareTo(line.split("\t")[0]) == 0)
                    {
                        this.id = line.split("\t")[0];
                        this.carId = line.split("\t")[1];
                        this.userId = line.split("\t")[2];
                        this.paymentId = line.split("\t")[3];
                        this.status = line.split("\t")[4];
                        this.bookingDate = new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[5]);
                        this.checkoutDate = new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[6]);
                        this.checkinDate = new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[7]);
                        break;
                        
                    }

                }//while
                
                data.close();
            }
                
        }  
        catch(IOException e)  
        {  
            
            e.printStackTrace();  
        }
        try {
            File folder = new File("files");
            if(!folder.exists())
                folder.mkdir();
            
            
            File bookingFile = new File("files/bookings.txt");

            if (bookingFile.createNewFile()){
                FileWriter fw;
                fw = new FileWriter("files/bookings.txt", true);

                fw.write("ID\tCarId\tUser ID\tPayment ID\tStatus\tBooking Date\tCheckout Date\tCheckin Date\n");
                fw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        
    }
    
    private String getNextId()
    {
        String nextId = "";
        try  
        {  
            File bookingFile = new File("files/bookings.txt");
            if (bookingFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(bookingFile));
                String line;
                data.readLine(); // Read the header row
                while((line = data.readLine()) != null)
                {
                   nextId = String.format("%04d", Integer.parseInt(line.split("\t")[0]) + 1);

                }//while

                data.close();
            }
            else
            {
                nextId = String.format("%04d", 1);
            }
            
        }  
        catch(IOException e)  
        {  
            this.id = String.format("%04d", 1);
            this.paymentId = this.id;
            e.printStackTrace();  
        }
        return nextId;
        
    }
    
    
    
    public String add()
    {
        this.id = getNextId();
        this.paymentId = this.id;
        

        try {
            File folder = new File("files");
            if(!folder.exists())
                folder.mkdir();
            
            
            File bookingFile = new File("files/bookings.txt");

            if (bookingFile.createNewFile()){
                FileWriter fw;
                fw = new FileWriter("files/bookings.txt", true);

                fw.write("ID\tCarId\tUser ID\tPayment ID\tStatus\tBooking Date\tCheckout Date\tCheckin Date\n");
                fw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        try 
        {
        //Check if exist
        //If it exists append
        //If doesnt exist crete file and write header row then append
           
            FileWriter fw;
            fw = new FileWriter("files/bookings.txt", true);
        
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            fw.write(this.id + "\t" + this.carId + "\t"+ this.userId + "\t"+ this.paymentId + "\t" + this.status + "\t" + dateFormat.format(this.bookingDate) + "\t" + dateFormat.format(this.checkoutDate) + "\t" + dateFormat.format(this.checkinDate) + "\n");
            fw.close();
        } 
        catch (IOException e) {
            
            e.printStackTrace(); //IDK WHAT IS THIS FOR
        }
        
        
        return this.id;
    }
    
    public boolean update()
    {
        
        try  
        {  
            
            BufferedReader data;
            data = new BufferedReader(new FileReader("files/bookings.txt"));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            line = data.readLine(); // Read the header row
            inputBuffer.append(line).append("\n");
                
            while((line = data.readLine()) != null)
            {
                if(this.id.compareTo(line.split("\t")[0]) == 0)
                {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    line = this.id + "\t"+ this.carId + "\t"+ this.userId + "\t" + this.paymentId + "\t" + this.status + "\t" + dateFormat.format(this.bookingDate) + "\t" + dateFormat.format(this.checkoutDate) + "\t" + dateFormat.format(this.checkinDate);
                    
                }
                
                inputBuffer.append(line).append("\n");
                
            }
            data.close();

            
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("files/bookings.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            
            return true;
            
        }  
        catch(IOException e)  
        {  
            return false;
            
        }
        
    }
    
    public boolean validate()
    {
        if(this.checkoutDate.before(this.bookingDate))
        {
            JOptionPane.showMessageDialog(null, "Checkout date cannot be before booking date.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.checkinDate.before(this.checkoutDate))
        {
            JOptionPane.showMessageDialog(null, "Checkin date cannot be before checkout date.", "Date Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try  
        {  
            File bookingFile = new File("files/bookings.txt");
            if (bookingFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(bookingFile));
                String line;
                data.readLine(); // Read the header row
                while((line = data.readLine()) != null)
                {
                    
                    
                    Date checkoutDate = new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[6]);
                    Date checkinDate = new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[7]);
                    
                    if(this.carId.compareTo(line.split("\t")[1]) == 0
                            && line.split("\t")[4].compareTo("Cancelled") != 0
                            && !this.checkoutDate.before(checkoutDate) 
                            && !this.checkoutDate.after(checkinDate) 
                            && !this.checkinDate.before(checkoutDate) 
                            && !this.checkinDate.after(checkinDate))
                    {
                        JOptionPane.showMessageDialog(null, "Selected car has already been booked during this time.\nPick a different car or different time slot.", "Date Error", JOptionPane.ERROR_MESSAGE);
                        data.close();
                        return false;
                    }

                }//while

                data.close();
            }
                
        }  
        catch(IOException e)  
        {  
            
            e.printStackTrace();  
        } catch (ParseException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
