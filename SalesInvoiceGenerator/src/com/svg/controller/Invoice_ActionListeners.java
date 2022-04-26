/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.controller;

import com.svg.model.InvoiceHeader;
import com.svg.model.InvoiceHeader_TblModel;
import com.svg.model.InvoiceLine;
import com.svg.model.InvoiceLine_TblModel;
import com.svg.view.InvHeaderDialog;
import com.svg.view.InvLineDialog;
import com.svg.view.InvoiceFrame;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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
   private InvHeaderDialog header_Dialog;
   private InvLineDialog line_Dialog;
   
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
        case  "createInvoiceOK" :
           newInvDialogOk();
            break;
        case "createInvoiceCancel":
            newInvDialogCancel();
            
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
        
        case "createLineOK":
            newLineDialogOk();
            break;
        case "createLineCancel":
            newLineDialogCancel();
            
            break;
                
    }
    
    
    }
    private void deleteInvoice() {
       
        int selectedInvIndex = frame.getInvHTbl().getSelectedRow();
        if (selectedInvIndex != -1) {
            frame.getInvoicesArray().remove(selectedInvIndex);
            frame.getHeaderTbModel().fireTableDataChanged();

            frame.getInvLTbl().setModel(new InvoiceLine_TblModel(null));
            frame.setLinesArray(null);
            frame.getCustNameLbl().setText("");
            frame.getInvNumLbl().setText("");
            frame.getInvTotalLbl().setText("");
            frame.getInvDateLbl().setText("");
        }

    }

   

    private void deleteLine() {
      int selectedLineIndex = frame.getInvLTbl().getSelectedRow();
      int selectedInvoiceIndex = frame.getInvHTbl().getSelectedRow();
      if(selectedLineIndex != -1){
          frame.getLinesArray().remove(selectedLineIndex);
          InvoiceLine_TblModel invLineTblModel =(InvoiceLine_TblModel) frame.getInvLTbl().getModel() ;
          invLineTblModel.fireTableDataChanged();
          frame.getInvTotalLbl().setText(""+frame.getInvoicesArray().get(selectedInvoiceIndex).getInvoiceTotal());
          frame.getHeaderTbModel().fireTableDataChanged();
          frame.getInvHTbl().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
          
      }
    }
    private void saveFiles() {
        ArrayList<InvoiceHeader> invoiceArray = frame.getInvoicesArray();
        JFileChooser headerFC = new JFileChooser();
        try{ 
            int result = headerFC.showSaveDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File headerFile = headerFC.getSelectedFile();
                FileWriter hfwriter = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for(InvoiceHeader invoice : invoiceArray){
                    headers += invoice.toString();
                    headers += "\n";
                    for(InvoiceLine line : invoice.getLines()){
                        lines += line.toString();
                        lines += "\n" ;  
                    }
                }
                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                
                
               result = headerFC.showSaveDialog(frame);
               File lineFile = headerFC.getSelectedFile();
               FileWriter lineFw = new FileWriter(lineFile);
               hfwriter.write(lines);
               hfwriter.close();
               lineFw.close();
              
            }
        }
        catch(IOException ie){
            JOptionPane.showMessageDialog(frame, ie.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
                      InvoiceLine line = new InvoiceLine(str2, price, count, inv);
                      inv.getLines().add(line);
                      
                 }
                 System.out.println("** READING FILES START **");
                 System.out.println("*&& HEADER FILE &&*");
                  for (String headerLine : headerLines){
                     System.out.println(headerLine);
               }  
                System.out.println("*&& LINE FILE &&*");  
                for (String lineLine: lineLines){
                System.out.println(lineLine);
              }  
               System.out.println("** READING FILES END **");
                 
             }
            InvoiceHeader_TblModel  headerTblModel = new InvoiceHeader_TblModel(invoiceHeaders);
            frame.setHeaderTbModel(headerTblModel);
            frame.getInvHTbl().setModel( headerTblModel);
            
           System.out.println("Files Loaded");
            
        }
        
        }
        
        catch(IOException ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                }
        catch(ParseException px){
                JOptionPane.showMessageDialog(frame, px.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                
                }
        }
 
    private void createNewInvoice() {
        header_Dialog = new InvHeaderDialog(frame);
        header_Dialog.setVisible(true);
    }

    private void newInvDialogOk() {
        header_Dialog.setVisible(false);
        String customerName = header_Dialog.getCustNameField().getText();
        String dt=header_Dialog.getInvDateField().getText();
        Date date= new Date();
        try{
        date = InvoiceFrame.dateFormat.parse(dt);}
        catch(ParseException pe)
        {
           JOptionPane.showMessageDialog(frame,"Can't Parse Date, Resetting to Tpday","Invalid date Format",JOptionPane.ERROR_MESSAGE); 
        }
        int invoiceNum = 0;
        for(InvoiceHeader inv :frame.getInvoicesArray()){
            if(inv.getNum() > invoiceNum) { invoiceNum = inv.getNum();}
        }invoiceNum++;
        InvoiceHeader invH = new InvoiceHeader(invoiceNum,customerName,date);
        frame.getInvoicesArray().add(invH);
        frame.getHeaderTbModel().fireTableDataChanged();
        header_Dialog.dispose();
        header_Dialog = null;
    }

    private void newInvDialogCancel() {
             
        header_Dialog.setVisible(false);
        header_Dialog.dispose();
        header_Dialog = null; 
    }
    
     private void createNewLine() {
       line_Dialog = new InvLineDialog(frame);
        line_Dialog.setVisible(true);
    }

    private void newLineDialogOk() {
        line_Dialog.setVisible(false);
        String name = line_Dialog.getItemNameField().getText();
        String strCount = line_Dialog.getItemCountField().getText();
        String strPrice = line_Dialog.getItemPriceField().getText();
        int count = 1;
        double price = 1.0;
        try{
            count = Integer.parseInt(strCount);
        } catch(NumberFormatException ex)
        {
            JOptionPane.showInternalMessageDialog(frame,"Can't convert Count","Invalid Number Format",count);
        }
        try{
            price = Double.parseDouble(strPrice);
        } catch(NumberFormatException ex)
        {
            JOptionPane.showInternalMessageDialog(frame,"Can't convert Price","Invalid Number Format",JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvoiceHeader = frame.getInvHTbl().getSelectedRow();
        if(selectedInvoiceHeader != -1)
        {
            InvoiceHeader invHeader = frame.getInvoicesArray().get(selectedInvoiceHeader);
            InvoiceLine invLine = new InvoiceLine(name,price,count,invHeader);
            //invHeader.getLines().add(invLine);
            frame.getLinesArray().add(invLine);
            InvoiceLine_TblModel invLineTblModel =(InvoiceLine_TblModel) frame.getInvLTbl().getModel() ;
            invLineTblModel.fireTableDataChanged();
            frame.getHeaderTbModel().fireTableDataChanged();
            
        
        frame.getInvHTbl().setRowSelectionInterval(selectedInvoiceHeader, selectedInvoiceHeader);
    } 
        line_Dialog.dispose();
        line_Dialog = null; 
      
    }

    private void newLineDialogCancel() {
        line_Dialog.setVisible(false);
        line_Dialog.dispose();
        line_Dialog = null; 
    }

    
    
    }



  

