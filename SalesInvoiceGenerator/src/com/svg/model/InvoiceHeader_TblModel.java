/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.model;

import com.svg.view.InvoiceFrame;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author acer
 */
public class InvoiceHeader_TblModel extends AbstractTableModel {
    private ArrayList<InvoiceHeader> invoicesArray;
    private String[] columns = {"Invoice Num","Invoice Date","Customer Name","Invoice Total"};

    public InvoiceHeader_TblModel(ArrayList<InvoiceHeader> invoicesArray) {
        this.invoicesArray = invoicesArray;
    }
    

    @Override
    public int getRowCount() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     return invoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader inv = invoicesArray.get(rowIndex);
        switch (columnIndex){ 
            case 0 :  return inv.getNum();
            case 1 : return InvoiceFrame.dateFormat.format(inv.getInvDate());
            case 2 : return inv.getCustomer();
            case 3 : return inv.getInvoiceTotal();
        }
        
        
   return "";
    }

    @Override
    public String getColumnName(int column) {
        return columns[column]; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
