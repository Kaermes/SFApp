/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sfapp;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.io.*;
import net.lingala.zip4j.core.ZipFile;
/**
 *
 * @author Aleksi Aalto
 */
public class SoftwareFactoryApplication {

    
    public static void main(String[] args) {
        
        File reports = new File("csv.zip");
        File folder = new File("somefolder");
        List<String> allLines = new ArrayList<String>();
        String[] fileExtensions = new String[1];
        fileExtensions[0] = "csv";
        
        
        HashMap<String, Integer> categories = new HashMap<String, Integer>();
        HashMap<String, Integer> cities = new HashMap<String, Integer>();
        
        //download file, unzip, read lines to a list
        try {
            URL certURL = new URL("http://pilvilinna.cert.fi/opendata/autoreporter/csv.zip");
            FileUtils.copyURLToFile(certURL, reports);
            ZipFile zipFile = new ZipFile(reports);
            zipFile.extractAll(folder.getName());
            
            for (File f : FileUtils.listFiles(folder, fileExtensions, false)) {
                allLines.addAll(FileUtils.readLines(f));
            }        
        } catch (Exception e){
            System.out.println("Something went wrong... " + e.getMessage());
        }
        
        //go through all lines, separate the interesting parts and calculate frequencies
        for (String line : allLines) {
            String[] reportElements = line.split("\\|");
            if (reportElements.length < 9) { //get rid of the few first lines
                continue;
            }

            String categoriesKey = reportElements[5];
            String citiesKey = reportElements[7]+reportElements[8];
            
            if (categories.containsKey(categoriesKey)) {
                categories.put(categoriesKey, categories.get(categoriesKey) +1);
            }
            else {
                categories.put(categoriesKey, 1);
            }
            
            if (cities.containsKey(citiesKey)) {
                cities.put(citiesKey, cities.get(citiesKey) +1);
            }
            else {
                cities.put(citiesKey, 1);
            }
            
        }
        
        //System.out.println("\n ***frequency of categories*** \n");
        
        for (Entry<String, Integer> e : categories.entrySet()) {
            System.out.print(""+ e.getKey()+" "+ e.getValue() +"\n");
        }
        
        //System.out.println("\n ***frequency of locations*** \n");
        
        for (Entry<String, Integer> e : cities.entrySet()) {
            System.out.print(""+ e.getKey()+" "+ e.getValue() +"\n");
        }
        
    }
}
