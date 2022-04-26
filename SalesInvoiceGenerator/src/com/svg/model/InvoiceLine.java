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
public class InvoiceLine {
    
    private String item;
    private double price;
    private int count;
    private InvoiceHeader header;

    public InvoiceLine() {
    }

    public InvoiceLine(String item, double price, int count, InvoiceHeader header) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.header = header;
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public double getLineTotal()
    {
        return price * count ;
    }

    @Override
    public String toString() {
        ///return "InvoiceLine{" + "item=" + item + ", price=" + price + ", count=" + count + ", header=" + header + '}';
        return header.getNum()+","+ item + "," + price + "," + count ;
    }
    
    
    
    
    
    
}




