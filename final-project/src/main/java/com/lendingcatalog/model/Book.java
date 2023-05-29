package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Book implements CatalogItem {

    private String id;
    private String title;
    private String author;
    private LocalDate publishDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Book(String title, String author, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public Book(String title, String author, String publishDate) {
        this(title, author, LocalDate.parse(publishDate));
    }

    public String toString(){
        return "* " + title + System.lineSeparator()
                + " - Written by: " + author + System.lineSeparator()
                + " - Published: " + publishDate + System.lineSeparator()
                + " - Id: " + id;
    }


    @Override
    public boolean matchesName(String searchStr) {
        return title.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return author.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return publishDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() {
        id = UUID.randomUUID().toString();
        try {
            FileStorageService.writeContentsToFile(toString(), "src/main/resources/logs/BookLog", true);
        } catch (FileStorageException e) {
            System.out.println(e.getMessage());
        }

    }
}
