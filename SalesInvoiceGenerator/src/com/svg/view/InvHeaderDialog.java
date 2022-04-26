/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.view;

/**
 *
 * @author acer
 */
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class InvHeaderDialog extends JDialog{

    private JTextField customerNameField;
    private JTextField invoiceDateField;
    private JLabel customerNameLbl;
    private JLabel invoiceDateLbl;
    private JButton okButton;
    private JButton cancelButton;

    public InvHeaderDialog(InvoiceFrame frame) {
        customerNameLbl = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invoiceDateLbl = new JLabel("Invoice Date:");
        invoiceDateField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createInvoiceOK");
        cancelButton.setActionCommand("createInvoiceCancel");
        
        okButton.addActionListener(frame.getActionListeners());
        cancelButton.addActionListener(frame.getActionListeners());
        setLayout(new GridLayout(3, 2));
        
        add(invoiceDateLbl);
        add(invoiceDateField);
        add(customerNameLbl);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return customerNameField;
    }

    public JTextField getInvDateField() {
        return invoiceDateField;
    }

    
}
