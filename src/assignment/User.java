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
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author FAWZAN
 */
public class User {
    protected String email;
    protected String password;
    protected String securityQ;
    protected String securityAns;
    protected boolean isAdmin = false;
    
    public User()
    {
        
    }
    
    public User(String email)
    {
        this.email = email;
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
                    
                    if(this.email.compareToIgnoreCase(line.split("\t")[0]) == 0)
                    {
                        this.email = line.split("\t")[0];
                        this.password = line.split("\t")[1];
                        this.securityQ = line.split("\t")[3];
                        this.securityAns = line.split("\t")[4];
                        if(line.split("\t")[2].compareTo("admin") == 0)
                            this.isAdmin = true;
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
    
    public User(String email, String password)
    {
        this.email = email;
        this.password = password;
        
    }
    
    public boolean isAdmin()
    {
        return isAdmin;
    }
    
    public boolean login()
    {
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
                    if(this.email.compareTo(line.split("\t")[0]) == 0 && this.password.compareTo(line.split("\t")[1]) == 0)
                    {
                        
                        if(line.split("\t")[2].compareTo("admin") == 0)
                        {
                            this.isAdmin = true;
                        }
                        data.close();
                        return true;
                    }
                }
                data.close();
            }
            
            File adminFile = new File("files/admins.txt");
            
            if(!adminFile.exists())
            {
                try 
                {
                    File folder = new File("files");
                    if(!folder.exists())
                        folder.mkdir();

                    if (adminFile.createNewFile())
                    {
                        FileWriter fw;
                        fw = new FileWriter("files/admins.txt", true);

                        fw.write("ID\tName\tEmail\tPhone\n");
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
                    fw = new FileWriter("files/admins.txt", true);


                    fw.write("0001\tAdmin\tadmin\t0100000\n");
                    fw.close();
                } 
                catch (IOException e) 
                {

                    e.printStackTrace(); 
                }
                
                try 
                {
                    File folder = new File("files");
                    if(!folder.exists())
                        folder.mkdir();


                

                    if (userFile.createNewFile())
                    {
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
                    fw = new FileWriter("files/users.txt", true);


                    fw.write("admin\tpassword\tadmin\tFirst Letter of the alphabet\tA\n");
                    fw.close();
                } 
                catch (IOException e) {

                    e.printStackTrace(); 
                }
            }
        }
        catch(IOException e)  
        {  
            e.printStackTrace();  
        }
        return false;
    }
    
    public boolean validate()
    {
        if(this.email.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Email cannot be empty", "Email Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.password.length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Password Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
            
            return true;
            
        }  
        catch(IOException e)  
        {  
            return false;
            
        }
        
    }
}
