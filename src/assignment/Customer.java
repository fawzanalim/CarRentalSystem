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
public class Customer extends User{
    public String id;
    public String name;
    public String phone;
    public String confirmPass;
    
    public Customer(String id, String name, String email, String phone)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    public Customer(String name, String email, String phone, String password, String confirmPass, String securityQ, String securityAns)
    {
        
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirmPass = confirmPass;
        this.securityQ = securityQ;
        this.securityAns = securityAns;
        this.isAdmin = false;
    }
    
    public Customer(String idOrEmail)
    {
        
        this.isAdmin = false;
        
        try  
        {  
            File customerFile = new File("files/customers.txt");
            if (customerFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(customerFile));
                String line;
                data.readLine(); // Read the header row
                while((line = data.readLine()) != null)
                {

                    if(idOrEmail.compareTo(line.split("\t")[0]) == 0 || idOrEmail.compareTo(line.split("\t")[2]) == 0)
                    {
                        
                        this.id = line.split("\t")[0];
                        this.name = line.split("\t")[1];
                        this.email = line.split("\t")[2];
                        this.phone = line.split("\t")[3];
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
        
        
        try  
        {  
            
            File userFile = new File("files/users.txt");
            if (userFile.exists())
            {
                
                BufferedReader data;
                data = new BufferedReader(new FileReader(userFile));
                
                String line;
                data.readLine(); // Read the header row
                while((line = data.readLine()) != null)
                {
                    
                    if(this.email.compareTo(line.split("\t")[0]) == 0)
                    {
                        this.password = line.split("\t")[1];
                        this.securityQ = line.split("\t")[3];
                        this.securityAns = line.split("\t")[4];
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
        
    }
    
    @Override
    public boolean validate()
    {
        if(this.email.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Email cannot be empty", "Email Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try  
        {  
            File userFile = new File("files/users.txt");
            if (userFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(userFile));
                String line;
                data.readLine(); // Read the header row
                while((line = data.readLine()) != null)
                {

                    if(this.email.compareTo(line.split("\t")[0]) == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Email already in use.", "Email Error", JOptionPane.ERROR_MESSAGE);
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
        }
        
        
        //First Name validity
        if(this.name.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Name cannot be empty", "Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Pass length 8-20
        if(this.password.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.password.length() < 8)
        {
            JOptionPane.showMessageDialog(null, "Password length cannot be less than 8", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.password.length() > 20)
        {
            JOptionPane.showMessageDialog(null, "Password length cannot be more than 20", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Confirm Pass                
        if (this.password.compareTo(this.confirmPass) != 0)
        {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        
        //Phone Number validity
        if(this.phone.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Phone Number cannot be empty", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.phone.matches("[0-9]+"))
        {
            JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Phone Number validity
        if(this.securityQ.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Security Question cannot be empty", "Security Question Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Phone Number validity
        if(this.securityAns.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Security Answer cannot be empty", "Security Answer Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean updateValidate()
    {
        
        
        
        //First Name validity
        if(this.name.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Name cannot be empty", "Name Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Pass length 8-20
        if(this.password.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.password.length() < 8)
        {
            JOptionPane.showMessageDialog(null, "Password length cannot be less than 8", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.password.length() > 20)
        {
            JOptionPane.showMessageDialog(null, "Password length cannot be more than 20", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Confirm Pass                
        if (this.password.compareTo(this.confirmPass) != 0)
        {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        
        //Phone Number validity
        if(this.phone.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Phone Number cannot be empty", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!this.phone.matches("[0-9]+"))
        {
            JOptionPane.showMessageDialog(null, "Invalid Phone Number", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //Phone Number validity
        if(this.securityQ.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Security Question cannot be empty", "Security Question Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        //Phone Number validity
        if(this.securityAns.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Security Answer cannot be empty", "Security Answer Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private String getNextId()
    {
        String nextId = "";
        try  
        {  
            File customerFile = new File("files/customers.txt");
            if (customerFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(customerFile));
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
            e.printStackTrace();  
        }
        return nextId;
        
    }

    public String register()
    {
        this.id = getNextId();
        
        

        try {
            File folder = new File("files");
            if(!folder.exists())
                folder.mkdir();
            
            
            File customerFile = new File("files/customers.txt");

            if (customerFile.createNewFile()){
                FileWriter fw;
                fw = new FileWriter("files/customers.txt", true);

                fw.write("ID\tName\tEmail\tPhone\tNumber\n");
                fw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            File folder = new File("files");
            if(!folder.exists())
                folder.mkdir();
            
            
            File userFile = new File("files/users.txt");

            if (userFile.createNewFile()){
                FileWriter fw;
                fw = new FileWriter("files/users.txt", true);

                fw.write("Email\tPassword\tUser Type\tSecurity Question\tSecurity Answer\n");
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
            fw = new FileWriter("files/customers.txt", true);
        
        
            fw.write(this.id + "\t" + this.name + "\t"+ this.email + "\t"+ this.phone + "\n");
            fw.close();
        } 
        catch (IOException e) {
            
            e.printStackTrace(); //IDK WHAT IS THIS FOR
        }
        
        try 
        {
        //Check if exist
        //If it exists append
        //If doesnt exist crete file and write header row then append
           
            FileWriter fw;
            fw = new FileWriter("files/users.txt", true);
        
        
            fw.write(this.email + "\t" + this.password + "\t"+ "customer" + "\t"+ this.securityQ  + "\t" + this.securityAns + "\n");
            fw.close();
        } 
        catch (IOException e) {
            
            e.printStackTrace(); //IDK WHAT IS THIS FOR
        }
        
        JOptionPane.showMessageDialog(null, "Registered Successfully", "Registration Message", JOptionPane.INFORMATION_MESSAGE);

        return this.id;
    }
    
   
    public boolean update()
    {
        
        try  
        {  
            
            BufferedReader data;
            data = new BufferedReader(new FileReader("files/users.txt"));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            line = data.readLine(); // Read the header row
            inputBuffer.append(line).append("\n");
                
            while((line = data.readLine()) != null)
            {
                if(this.email.compareTo(line.split("\t")[0]) == 0)
                {
                    String userType;
                    if(this.isAdmin)
                        userType = "admin";
                    else
                        userType = "customer";
                    
                    line = this.email + "\t" + this.password + "\t"+ userType + "\t"+ this.securityQ  + "\t" + this.securityAns;
                    
                }
                
                inputBuffer.append(line).append("\n");
                
            }
            data.close();

            
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("files/users.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            
        }  
        catch(IOException e)  
        {  
            return false;
            
        }
        
        try  
        {  
            
            BufferedReader data;
            data = new BufferedReader(new FileReader("files/customers.txt"));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            line = data.readLine(); // Read the header row
            inputBuffer.append(line).append("\n");
                
            while((line = data.readLine()) != null)
            {
                if(this.id.compareTo(line.split("\t")[0]) == 0)
                {
                    
                    line = this.id + "\t" + this.name + "\t"+ this.email + "\t"+ this.phone;
                    
                }
                
                inputBuffer.append(line).append("\n");
                
            }
            data.close();

            
            FileOutputStream fileOut;
            fileOut = new FileOutputStream("files/customers.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
            
        }  
        catch(IOException e)  
        {  
            return false;
            
        }
        
        return true;
        
    }
}
