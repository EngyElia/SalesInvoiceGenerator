/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author acer
 */
public class InvoiceLine_TblModel extends AbstractTableModel {
    
    
    private ArrayList<InvoiceLine> lineArray;
    private String[] columns = {"Item Name","Unit Price","Count","Line Total"};

    public InvoiceLine_TblModel(ArrayList<InvoiceLine> lineArray) {
        this.lineArray = lineArray;
    }

   
    
    @Override
    public int getRowCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    return  lineArray == null ? 0 : lineArray.size();
    }

    @Override
    public int getColumnCount() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return columns.length;
    
    }

    @Override
    public Object getValueAt(int rI, int cI) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(lineArray == null)
        {return "";}
        
        else{ InvoiceLine line = lineArray.get(rI);
        switch(cI){
            case 0: return line.getItem();
            case 1: return line.getPrice();
            case 2: return line.getCount();
            case 3: return line.getLineTotal();
            default:  return "";}
        }
    
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
       // return super.getColumnName(i); //To change body of generated methods, choose Tools | Templates.
    }

    
}
