/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.controller;

import com.svg.model.InvoiceHeader;
import com.svg.model.InvoiceHeader_TblModel;
import com.svg.model.InvoiceLine;
import com.svg.view.InvoiceFrame;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */
public class Invoice_ActionListeners implements ActionListener {
   private InvoiceFrame frame;
   
    public Invoice_ActionListeners(InvoiceFrame frame){
        this.frame = frame;
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    switch (ae.getActionCommand())
    { 
        case "Load File":
            loadFiles();
           
            break;
        case "Save File":
            saveFiles();
            break;
        case "Create New Invoice":
            createNewInvoice();
            break;
        case "Delete Invoice":
            deleteInvoice();
            break;
        case "Save":
            createNewLine();
            break;
        case "Cancel":
            deleteLine();
            break;
                
    }
    
    
    }
private void deleteInvoice() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void createNewLine() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteLine() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private void saveFiles() {
    }

    private void loadFiles() {
        JFileChooser fileChooser = new JFileChooser();
        try {
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
          File headerFile = fileChooser.getSelectedFile();
          Path headerPath = Paths.get(headerFile.getAbsolutePath());
          List<String> headerLines = Files.readAllLines(headerPath);
          ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
          for(String headerLine : headerLines){
             String[]arr = headerLine.split(",");
             String str1 = arr[0];
             String str2 = arr[1];
             String str3 = arr[2];
             int code = Integer.parseInt(str1);
             Date invoiceDate = InvoiceFrame.dateFormat.parse(str2);
             InvoiceHeader header = new InvoiceHeader(code,str3,invoiceDate);
             invoiceHeaders.add(header);
          }
          frame.setInvoicesArray(invoiceHeaders);
                  
             result = fileChooser.showOpenDialog(frame);
             if(result == JFileChooser.APPROVE_OPTION){
                 File lineFile = fileChooser.getSelectedFile();
                 Path linePath =Paths.get(lineFile.getAbsolutePath());
                 List<String> lineLines = Files.readAllLines(linePath);
                 ArrayList<InvoiceLine> InvoiceLines = new ArrayList<>();
                 for(String lineLine : lineLines){
                     String[] arr = lineLine.split(",");
                       String str1 = arr[0];
                       String str2 = arr[1];
                       String str3 = arr[2];
                       String str4 = arr[3];
                       int invCode = Integer.parseInt(str1);
                       double price  = Double.parseDouble(str3);
                       int count = Integer.parseInt(str4);
                      InvoiceHeader inv = frame.getInvObject(invCode);
                      InvoiceLine line = new InvoiceLine(str4, price, count, inv);
                      inv.getLines().add(line);
                      
                 }
                 
             }
            InvoiceHeader_TblModel  headerTblModel = new InvoiceHeader_TblModel(invoiceHeaders);
            frame.setHeaderTbModel(headerTblModel);
            frame.getInvHTbl().setModel( headerTblModel);
            
           System.out.println("selected lines");
            
        }
        
        }
        
        catch(IOException ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                }
        catch(ParseException ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                }
        }

    private void createNewInvoice() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    }



  

