/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Book {

    private String bookID, title, author, category;
    private double bookPrice;
    private int quantity;
    private Date publishDate;

    public Book(String bookID, String title, String author, String category, String publishDate, int quantity, double price) throws ParseException {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        setYearPub(publishDate);
        this.quantity = quantity;
        this.bookPrice = price;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getYearPub() {
        return publishDate;
    }

    public void setYearPub(String publishDate) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        this.publishDate = date.parse(publishDate);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return bookPrice;
    }

    public void setPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public boolean updateQuantity(String id, int newQ) {
        if (this.getBookID().equals(id)) {
            this.quantity = newQ;
            return true;
        } else {
            return false;
        }
    }

    public boolean updatePrice(String id, double newP) {
        if (this.getBookID().equals(id)) {
            this.bookPrice = newP;
            return true;
        } else {
            return false;
        }
    }

    public Double calPromotional() {
        Double price = this.getPrice();
        if(price <= 5){
            return price; 
        }else if(price > 5 && price <8){
            return price * 0.02;
        }else{
            return price *0.05;
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat dateToString = new SimpleDateFormat("dd/MM/yyyy");
        String publishDateToString = dateToString.format(publishDate);
        return String.format("%-5s | %-24s | %-24s | %-24s | %-24s | %-14d | %-14.2f | %-14.2f |", bookID, title, author, category, publishDateToString, quantity, bookPrice, calPromotional());
    }

}
