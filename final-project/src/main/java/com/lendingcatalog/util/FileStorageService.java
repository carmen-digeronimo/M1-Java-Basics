package com.lendingcatalog.util;

import com.lendingcatalog.util.exception.FileStorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorageService {

    // Requirement: File I/O
    public static void writeContentsToFile(String contents, String filename, boolean appendFile) throws FileStorageException {

        File printFile = new File(filename);

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(printFile, appendFile))){
            writer.println(contents);
        } catch (FileNotFoundException e){
            throw new FileStorageException("The file does not exist: " + filename);
        }

    }

    public static List<String> readContentsOfFile(String filename) throws FileStorageException {

        List<String> fileContents = new ArrayList<>();

        File bookFile = new File(filename);

        try (Scanner fileInput = new Scanner(bookFile)){

            while (fileInput.hasNextLine()){
                String lineOfText = fileInput.nextLine();
                fileContents.add(lineOfText);
            }
        } catch (FileNotFoundException e){
            throw new FileStorageException("The file does not exist: " + filename);
        }

        return fileContents;
    }
}
