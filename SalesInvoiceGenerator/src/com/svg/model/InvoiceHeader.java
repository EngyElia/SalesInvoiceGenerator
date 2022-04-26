/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svg.model;

/**
 *
 * @author acer
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 import java.util.ArrayList;


public class InvoiceHeader {
    private int num;
    private String customer;
    private Date invDate;
    private ArrayList<InvoiceLine> lines;
    private DateFormat dTFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    

    public InvoiceHeader() {
    }

    public InvoiceHeader(int num, String customer, Date invDate) {
        this.num = num;
        this.customer = customer;
        this.invDate = invDate;
    }

    public Date getInvDate() {
        return invDate;
    }

    public void setInvDate(Date invDate) {
        this.invDate = invDate;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<InvoiceLine> getLines() {
        if(lines == null){
            lines = new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
     public double getInvoiceTotal()
     {
         double total =0.0;
         for(int i=0; i< getLines().size();i++){
             total += getLines().get(i).getLineTotal();
             
         }
         return total;
          
     }

    @Override
    public String toString() {
        return num + "," + dTFormat.format(invDate) + "," + customer;
    }
    
    
    
}
