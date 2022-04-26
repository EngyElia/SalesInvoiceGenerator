/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.controller;

import com.svg.model.InvoiceHeader;
import com.svg.model.InvoiceLine;
import com.svg.model.InvoiceLine_TblModel;
import com.svg.view.InvoiceFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author acer
 */
public class TableSelectedListener implements ListSelectionListener {

    public TableSelectedListener(InvoiceFrame frame) {
        this.frame = frame;
    }
    private InvoiceFrame frame ;
    

    @Override
    public void valueChanged(ListSelectionEvent lse) {
    
     int invSelectedInvIndes = frame.getInvHTbl().getSelectedRow();
       
       System.out.println("Invoice Selected: " + invSelectedInvIndes);
       if(invSelectedInvIndes != 1){
       InvoiceHeader selectedInvoice= frame.getInvoicesArray().get(invSelectedInvIndes);
       ArrayList<InvoiceLine> lines = selectedInvoice.getLines();
       InvoiceLine_TblModel lineTableModel = new InvoiceLine_TblModel(lines);
       frame.setLinesArray(lines);
       frame.getInvLTbl().setModel(lineTableModel);
       frame.getCustNameLbl().setText(selectedInvoice.getCustomer());
       frame.getInvNumLbl().setText(""+ selectedInvoice.getNum());
       frame.getInvTotalLbl().setText(""+ selectedInvoice.getInvoiceTotal());
       frame.getInvDateLbl().setText(InvoiceFrame.dateFormat.format(selectedInvoice.getInvDate()));}
       
       
       
    }
    
    
}
