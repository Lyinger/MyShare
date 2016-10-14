package com.example.kingwen.frescofragment.Beans;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;

/**
 * Created by kingwen on 2016/6/2.
 */
public class RecBook implements Serializable {

    //书名
    private String bookName;

    //作者
    private  String bookAuthor;

    //照片
    private URI bookUri;

    //简介
    private String bookSummary;

    public RecBook(String bookName, String bookAuthor, URI bookUri, String bookSummary, String bookAssess) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookUri = bookUri;
        this.bookSummary = bookSummary;
        this.bookAssess = bookAssess;
    }

    //推荐理由
    public String getBookAssess() {

        return bookAssess;
    }public void setBookAssess(String bookAssess) {
        this.bookAssess = bookAssess;
    }private  String bookAssess;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public URI getBookUri() {
        return bookUri;
    }

    public void setBookUri(URI bookUri) {
        this.bookUri = bookUri;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public RecBook(String bookName, String bookAuthor, URI bookUri, String bookSummary) {

        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookUri = bookUri;
        this.bookSummary = bookSummary;
    }
}
