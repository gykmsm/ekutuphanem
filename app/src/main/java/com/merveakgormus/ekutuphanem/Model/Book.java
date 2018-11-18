package com.merveakgormus.ekutuphanem.Model;

import android.view.View;

public class Book {
    private String bookname;
    private String bookauthor;
    private Integer pagenumber;
    private Double price;
    private String covertype;
    private String productdescription;
    private String storageUrl;

    public Book(String bookname, String bookauthor, Integer pagenumber, Double price, String covertype, String productdescription, String storageUrl) {
        this.bookname = bookname;
        this.bookauthor = bookauthor;
        this.pagenumber = pagenumber;
        this.price = price;
        this.covertype = covertype;
        this.productdescription = productdescription;
        this.storageUrl = storageUrl;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }


    public String getCovertype() {
        return covertype;
    }

    public void setCovertype(String covertype) {
        this.covertype = covertype;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public interface CustomItemClickListener {
        void onItemClick(View v, int position);
    }
}
