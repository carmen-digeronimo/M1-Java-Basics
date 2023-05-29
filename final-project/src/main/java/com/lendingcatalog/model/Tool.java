package com.lendingcatalog.model;

import com.lendingcatalog.util.FileStorageService;
import com.lendingcatalog.util.exception.FileStorageException;

import java.time.LocalDate;
import java.util.UUID;

public class Tool implements CatalogItem{

    private String id;
    private String type;
    private String manufacturer;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Tool(String type, String manufacturer, int count) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.count = count;
    }

    public Tool(String type, String manufacturer, String count) {
        this(type, manufacturer, Integer.parseInt(count));
    }

    public String toString(){
        return "* " + type + System.lineSeparator()
                + " - Manufactured by: " + manufacturer + System.lineSeparator()
                + " - Amount: " + count + System.lineSeparator()
                + " - Id: " + id;
    }


    @Override
    public boolean matchesName(String searchStr) {
        return type.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesCreator(String searchStr) {
        return manufacturer.equalsIgnoreCase(searchStr);
    }

    @Override
    public boolean matchesYear(int searchYear) {
        return false;
    }

    @Override
    public void registerItem() {
        id = UUID.randomUUID().toString();
        try {
            FileStorageService.writeContentsToFile(toString(), "src/main/resources/logs/ToolLog", true);
        } catch (FileStorageException e) {
            System.out.println(e.getMessage());
        }
    }
}
