/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author FAWZAN
 */
public class AddNewBooking extends javax.swing.JFrame {
    private DefaultTableModel table1;
    private DefaultTableModel table2;
    private DefaultTableModel table3;
    
    private DefaultTableModel customerTable;
    List<Customer> customers = new ArrayList<Customer>();
    
    private DefaultTableModel carTable;
    List<Car> cars = new ArrayList<Car>();
    
    private DefaultTableModel bookingTable;
    List<Booking> bookings = new ArrayList<Booking>();
    
    LocalDateTime now = LocalDateTime.now(); 
        
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ZoneId defaultZoneId = ZoneId.systemDefault();
    
    /**
     * Creates new form AdminDashboard
     */
    public AddNewBooking() {
        initComponents();
        
        this.carTable = (DefaultTableModel) jTableCar.getModel();
        this.bookingTable = (DefaultTableModel) jTableBookings.getModel();
        this.customerTable = (DefaultTableModel) jTableCustomer.getModel();
        
        this.getAllCars();
        this.showAllCars();
        
        this.getAllCustomers();
        this.showAllCustomers();
        
        
        
        
         
        bookingDateField.setText(now.format(dateTimeFormatter));
        
    }

    AddNewBooking(Booking booking) {
        
    }
    
    private void getAllCars()
    {
        this.carTable = (DefaultTableModel) jTableCar.getModel();
        try  
        {  
            File carFile = new File("files/cars.txt");
            if (carFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(carFile));
                String line;
                data.readLine(); // Read the header row
                int i = 0;
                while((line = data.readLine()) != null) 
                {
                    
                    Car car = new Car(line.split("\t")[0], line.split("\t")[1], line.split("\t")[2], line.split("\t")[3], line.split("\t")[4], line.split("\t")[5], line.split("\t")[6]);
                    this.cars.add(car);
                    i++;
                    

                }
                data.close();
            }
            
            
            
            
            //while

            
        }  
        catch(IOException e)  
        {  
            
            e.printStackTrace();  
        }
    }
    
    private void showAllCars()
    {
        for(int i = 0; i < this.cars.size(); i++)
        {
            this.carTable.insertRow(this.carTable.getRowCount(), new Object[] {this.cars.get(i).id, this.cars.get(i).manufacturer, this.cars.get(i).model, this.cars.get(i).color, this.cars.get(i).seats, this.cars.get(i).rent});
        }
        
    }
    
    private void getAllCustomers()
    {
        this.customerTable = (DefaultTableModel) jTableCustomer.getModel();
        try  
        {  
            File customerFile = new File("files/customers.txt");
            if (customerFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(customerFile));
                String line;
                data.readLine(); // Read the header row
                int i = 0;
                while((line = data.readLine()) != null) 
                {
                    
                    Customer customer = new Customer(line.split("\t")[0], line.split("\t")[1], line.split("\t")[2], line.split("\t")[3]);
                    this.customers.add(customer);
                    i++;
                    

                }
                data.close();
            }
            
            
            
            
            //while

            
        }  
        catch(IOException e)  
        {  
            
            e.printStackTrace();  
        }
    }
    
    private void showAllCustomers()
    {
        for(int i = 0; i < this.customers.size(); i++)
        {
            this.customerTable.insertRow(this.customerTable.getRowCount(), new Object[] {this.customers.get(i).id, this.customers.get(i).name, this.customers.get(i).email, this.customers.get(i).phone});
        }
        
    }
    
    private void getAllBookings() throws ParseException
    {
        this.bookings.clear();
        try  
        {  
            File bookingFile = new File("files/bookings.txt");
            if (bookingFile.exists())
            {
                BufferedReader data;
                data = new BufferedReader(new FileReader(bookingFile));
                String line;
                data.readLine(); // Read the header row
                int i = 0;
                while((line = data.readLine()) != null) 
                {
                    
                    Booking booking = new Booking(line.split("\t")[0], line.split("\t")[1], 
                            line.split("\t")[2], line.split("\t")[3], line.split("\t")[4], 
                            new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[5]), 
                            new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[6]), 
                            new SimpleDateFormat("dd/MM/yyyy").parse(line.split("\t")[7]));
                    
                    if(0 == booking.carId.compareTo(jTableCar.getValueAt(jTableCar.getSelectedRow(), 0).toString())
                            && !booking.checkinDate.before(new SimpleDateFormat("dd/MM/yyyy").parse(now.format(dateTimeFormatter)))
                            && booking.status.compareTo("Cancelled") != 0)
                    {
                        this.bookings.add(booking);
                    }
                    
                    i++;
                    

                }
                data.close();
            }
            
            
            
            
            //while

            
        }  
        catch(IOException e)  
        {  
            
            e.printStackTrace();  
        } 
    }
    
    private void showAllBookings()
    {
        for(int i = 0; i < this.bookings.size(); i++)
        {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            this.bookingTable.insertRow(this.bookingTable.getRowCount(), new Object[] {this.bookings.get(i).status, dateFormat.format(this.bookings.get(i).bookingDate), dateFormat.format(this.bookings.get(i).checkoutDate), dateFormat.format(this.bookings.get(i).checkinDate)});
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        modelLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        searchCarField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCustomer = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCar = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableBookings = new javax.swing.JTable();
        modelLabel1 = new javax.swing.JLabel();
        bookingSearchField = new javax.swing.JTextField();
        modelLabel2 = new javax.swing.JLabel();
        customerSearchField = new javax.swing.JTextField();
        bookingDateField = new javax.swing.JTextField();
        modelLabel3 = new javax.swing.JLabel();
        checkoutDateField = new javax.swing.JTextField();
        modelLabel4 = new javax.swing.JLabel();
        modelLabel5 = new javax.swing.JLabel();
        checkinDateField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0, 150));
        jPanel1.setMaximumSize(new java.awt.Dimension(340, 460));
        jPanel1.setMinimumSize(new java.awt.Dimension(340, 460));
        jPanel1.setPreferredSize(new java.awt.Dimension(340, 460));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        modelLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modelLabel.setForeground(new java.awt.Color(255, 255, 255));
        modelLabel.setText("Search Car");
        jPanel1.add(modelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 110, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Add New Booking");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 380, -1));

        searchCarField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        searchCarField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCarFieldActionPerformed(evt);
            }
        });
        searchCarField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchCarFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchCarFieldKeyReleased(evt);
            }
        });
        jPanel1.add(searchCarField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 140, 30));

        saveButton.setBackground(new java.awt.Color(153, 153, 255));
        saveButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Pay");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jPanel1.add(saveButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 600, 160, 45));

        backButton.setBackground(new java.awt.Color(153, 153, 255));
        backButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        jPanel1.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 140, 45));

        jTableCustomer.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTableCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Email", "Phone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCustomer.setShowGrid(false);
        jTableCustomer.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableCustomer);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 450, 730, 190));

        jTableCar.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTableCar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Manufacturer", "Model", "Color", "No. of Seats", "Rent/Day"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCar.setShowGrid(false);
        jTableCar.getTableHeader().setReorderingAllowed(false);
        jTableCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCarMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTableCarMouseReleased(evt);
            }
        });
        jTableCar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableCarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableCarKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCar);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 730, 190));

        jTableBookings.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTableBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Booking Date", "Checkout Date", "Checkin Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBookings.setEnabled(false);
        jTableBookings.setFocusable(false);
        jTableBookings.setShowGrid(false);
        jTableBookings.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTableBookings);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 730, 190));

        modelLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modelLabel1.setForeground(new java.awt.Color(255, 255, 255));
        modelLabel1.setText("Search Booking");
        jPanel1.add(modelLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 150, 30));

        bookingSearchField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        bookingSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingSearchFieldActionPerformed(evt);
            }
        });
        bookingSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bookingSearchFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bookingSearchFieldKeyReleased(evt);
            }
        });
        jPanel1.add(bookingSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 140, 30));

        modelLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modelLabel2.setForeground(new java.awt.Color(255, 255, 255));
        modelLabel2.setText("Search Customer");
        jPanel1.add(modelLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 170, 30));

        customerSearchField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        customerSearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerSearchFieldActionPerformed(evt);
            }
        });
        customerSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                customerSearchFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customerSearchFieldKeyReleased(evt);
            }
        });
        jPanel1.add(customerSearchField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 140, 30));

        bookingDateField.setEditable(false);
        bookingDateField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        bookingDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingDateFieldActionPerformed(evt);
            }
        });
        bookingDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bookingDateFieldKeyPressed(evt);
            }
        });
        jPanel1.add(bookingDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 160, 140, 30));

        modelLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modelLabel3.setForeground(new java.awt.Color(255, 255, 255));
        modelLabel3.setText("Booking Date");
        jPanel1.add(modelLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 130, 130, 30));

        checkoutDateField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        checkoutDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkoutDateFieldActionPerformed(evt);
            }
        });
        checkoutDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkoutDateFieldKeyPressed(evt);
            }
        });
        jPanel1.add(checkoutDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 280, 140, 30));

        modelLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modelLabel4.setForeground(new java.awt.Color(255, 255, 255));
        modelLabel4.setText("Checkout Date");
        jPanel1.add(modelLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 250, 130, 30));

        modelLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        modelLabel5.setForeground(new java.awt.Color(255, 255, 255));
        modelLabel5.setText("Checkin Date");
        jPanel1.add(modelLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 400, 130, 30));

        checkinDateField.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        checkinDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkinDateFieldActionPerformed(evt);
            }
        });
        checkinDateField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                checkinDateFieldKeyPressed(evt);
            }
        });
        jPanel1.add(checkinDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 430, 140, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 1210, 670));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assignment/images/pexels-engin-akyurt-1435904.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String carId;
        String userId;
        String status = "Not Paid";
        Date bookingDate;
        Date checkoutDate;
        Date checkinDate;
        
        try
        {
            carId = jTableCar.getValueAt(jTableCar.getSelectedRow(), 0).toString();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Select a car please.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            userId = jTableCustomer.getValueAt(jTableCustomer.getSelectedRow(), 0).toString();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Select a user please.", "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            bookingDate = new SimpleDateFormat("dd/MM/yyyy").parse(bookingDateField.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Invalid booking date", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            checkoutDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkoutDateField.getText());
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Invalid checkout date", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            checkinDate = new SimpleDateFormat("dd/MM/yyyy").parse(checkinDateField.getText());
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Invalid checkin date", "Date Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Booking booking = new Booking(carId, userId, status, bookingDate, checkoutDate, checkinDate);
        
        if(booking.validate())
        {
            if(booking.add() != null)
            {

                AddPayment apf = new AddPayment(booking);
                this.setVisible(false);
                apf.setLocation(this.getLocation());
                apf.setVisible(true);
                apf.setResizable(false);
                

            }
        }
        
    }//GEN-LAST:event_saveButtonActionPerformed
    
    
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        
        try {
            BookingManagement bmf;
            this.setVisible(false);
            bmf = new BookingManagement();
            bmf.setLocation(this.getLocation());
            bmf.setVisible(true);
            bmf.setResizable(false);
        } catch (ParseException ex) {
            Logger.getLogger(AddNewBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void searchCarFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCarFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCarFieldActionPerformed

    private void searchCarFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchCarFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCarFieldKeyPressed

    private void bookingSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingSearchFieldActionPerformed

    private void bookingSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bookingSearchFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingSearchFieldKeyPressed

    private void customerSearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerSearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerSearchFieldActionPerformed

    private void customerSearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerSearchFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerSearchFieldKeyPressed

    private void bookingDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingDateFieldActionPerformed

    private void bookingDateFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bookingDateFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_bookingDateFieldKeyPressed

    private void checkoutDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkoutDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkoutDateFieldActionPerformed

    private void checkoutDateFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkoutDateFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkoutDateFieldKeyPressed

    private void checkinDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkinDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkinDateFieldActionPerformed

    private void checkinDateFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_checkinDateFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkinDateFieldKeyPressed

    private void jTableCarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCarMouseClicked
 
    }//GEN-LAST:event_jTableCarMouseClicked

    private void jTableCarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCarKeyPressed
        
    }//GEN-LAST:event_jTableCarKeyPressed

    private void jTableCarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCarMouseReleased
        this.bookingTable.setRowCount(0);
        
        try {
            this.getAllBookings();
        } catch (ParseException ex) {
            Logger.getLogger(AddNewBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.showAllBookings();
    }//GEN-LAST:event_jTableCarMouseReleased

    private void jTableCarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCarKeyReleased
        this.bookingTable.setRowCount(0);
        
        try {
            this.getAllBookings();
        } catch (ParseException ex) {
            Logger.getLogger(AddNewBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.showAllBookings();
    }//GEN-LAST:event_jTableCarKeyReleased

    private void searchCarFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchCarFieldKeyReleased
        String query = searchCarField.getText();
        table1 = (DefaultTableModel) jTableCar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table1);
        jTableCar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query.trim()));
    }//GEN-LAST:event_searchCarFieldKeyReleased

    private void bookingSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bookingSearchFieldKeyReleased
        String query = bookingSearchField.getText();
        table2 = (DefaultTableModel) jTableBookings.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table2);
        jTableBookings.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query.trim()));
    }//GEN-LAST:event_bookingSearchFieldKeyReleased

    private void customerSearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerSearchFieldKeyReleased
        String query = customerSearchField.getText();
        table3 = (DefaultTableModel) jTableCustomer.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table3);
        jTableCustomer.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + query.trim()));
    }//GEN-LAST:event_customerSearchFieldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField bookingDateField;
    private javax.swing.JTextField bookingSearchField;
    private javax.swing.JTextField checkinDateField;
    private javax.swing.JTextField checkoutDateField;
    private javax.swing.JTextField customerSearchField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableBookings;
    private javax.swing.JTable jTableCar;
    private javax.swing.JTable jTableCustomer;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JLabel modelLabel1;
    private javax.swing.JLabel modelLabel2;
    private javax.swing.JLabel modelLabel3;
    private javax.swing.JLabel modelLabel4;
    private javax.swing.JLabel modelLabel5;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField searchCarField;
    // End of variables declaration//GEN-END:variables
}
