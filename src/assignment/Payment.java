/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
public class Payment {
    public String id;
    public String cardNumber;
    public String cardHolder;
    public String expiryDate;
    public String cvv;
    public float total;
    
    public Payment(String id, String cardNumber, String cardHolder, String expiryDate, String cvv, float total)
    {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.total = total;
    }
    
    public String add()
    {
        
        try {
            File folder = new File("files");
            if(!folder.exists())
                folder.mkdir();
            
            
            File paymentFile = new File("files/payments.txt");

            if (paymentFile.createNewFile()){
                FileWriter fw;
                fw = new FileWriter("files/payments.txt", true);

                fw.write("ID\tCardNumber\tCard Holder\tExpiry Date\tCVV\tTotal\n");
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
            fw = new FileWriter("files/payments.txt", true);
        
        
            fw.write(this.id + "\t" + this.cardNumber.substring(this.cardNumber.length()-4) + "\t" + this.cardHolder  + "\t"+ this.expiryDate + "\t"+ this.cvv + "\t" + this.total + "\n");
            fw.close();
        } 
        catch (IOException e) {
            
            e.printStackTrace(); //IDK WHAT IS THIS FOR
        }
        
        
        return this.id;
    }
    
    public boolean validate()
    {
        if(this.cardNumber.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Card Number is invalid", "Card Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.cardHolder.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Card Holder Name is invalid", "Card Holder Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.cvv.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "CVV is invalid", "CVV Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Date date;
        try 
        {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(this.expiryDate);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Expiry Date is invalid", "Expiry Date Error", JOptionPane.ERROR_MESSAGE);
            
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
        return true;
    }
}
