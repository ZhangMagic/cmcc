package com.cmcc.dpi;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
    public static void rwFile(){
        FileWriter fw = null;
        try {
            fw = new FileWriter("text.txt", true);
                fw.write("123");
                fw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        rwFile();
    }

}
