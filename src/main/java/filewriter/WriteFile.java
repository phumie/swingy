package filewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.nio.charset.Charset;
import model.heroes.Hero;

public class WriteFile {
    public static File file = null;
    private static FileWriter fWriter;

    public WriteFile() {
        
    }

    public static void createFile(){
        try{
            if (file == null){
                file = new File("swingy.txt");
                file.createNewFile();
            }
            
            fWriter = new FileWriter(file, true);
            // System.out.println("File created");
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        } 
    }

    public static void writeToFile(String str){
        try{
            file = new File("swingy.txt");
            fWriter = new FileWriter(file, true);
            
            fWriter.append(str);
            fWriter.append('\n');
            fWriter.close();
            // System.out.println(str);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        } 
    }

    public static void closeFile(){
        try{
            if (fWriter != null)
                fWriter.close();
            else
                return;
            // System.out.println("File closed");
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}


