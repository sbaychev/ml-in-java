package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenerateIndexFile {

    public static void main(String[] args) {

        // Specify the folder you want to read files from
        String baseFolderPath = "src/main/resources/dataset/train";

        // The name of the output index file
        String outputFilePath = "src/main/resources/index.txt";

        // List to store file paths and names
        List<String> records = new ArrayList<>();

        // Recursively traverse the directory to get all files
        getFiles(baseFolderPath, records);

        // Write the records to the index file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            for (String rekord : records) {
                writer.write(rekord);
                writer.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Index file generated successfully!");
    }

    private static void getFiles(String folderPath, List<String> records) {

        File folder = new File(folderPath);

        File[] files = folder.listFiles();

        if (files != null) {

            for (File file : files) {

                if (file.isDirectory()) {

                    getFiles(file.getAbsolutePath(), records);

                } else {
//                    String relativePath = Paths.get(folderPath).relativize(Paths.get(file.getAbsolutePath())).toString();
                    String parentFolderName = new File(folderPath).getName();
                    String aRecord = file.getAbsolutePath() + " " + parentFolderName;
                    records.add(aRecord);
                }
            }
        }
    }
}
