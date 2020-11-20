package com.example.library;

public class Book {

    public int id;
    public String title;
    public String author;
    public String pages;



    public Book(String title, String author, String pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Book(int id, String title, String author, String pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
