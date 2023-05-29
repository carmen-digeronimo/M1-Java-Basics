package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.util.UUID;

public class Movie implements CatalogItem {

    private String id;
    private String name;
    private String director;
    private LocalDate releaseDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie(String name, String director, LocalDate releaseDate) {
        this.name = name;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public Movie(String name, String director, String releaseDate) {
        this(name, director, LocalDate.parse(releaseDate));
    }

    public String toString(){
        return "* " + name + System.lineSeparator()
                + " - Directed by: " + director + System.lineSeparator()
                + " - Released: " + releaseDate + System.lineSeparator()
                + " - Id: " + id;
    }



    @Override
    public boolean matchesName(String searchStr) {
        return name.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return director.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return releaseDate.getYear() == searchYear;
    }

    @Override
    public void registerItem() {
        id = UUID.randomUUID().toString();
        try {
            FileStorageService.writeContentsToFile(toString(), "src/main/resources/logs/MovieLog", true);
        } catch (FileStorageException e) {
            System.out.println(e.getMessage());
        }
    }
}
