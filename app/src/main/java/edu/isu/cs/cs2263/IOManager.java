package edu.isu.cs.cs2263;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IOManager {

    public List<Student> readData(String file) {
        Gson gson = new Gson();
        try {
            File f = new File(file);
            if (!f.exists() || !f.canRead()) {
                System.out.println(file + " couldn't be found/read.");
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            String input = (String)ois.readObject();
            Type t = new TypeToken<ArrayList<Student>>(){}.getType();
            List<Student> inList = gson.fromJson(input, t);
            ois.close();
            fis.close();
            System.out.println("Finished reading data from " + file + ".");
            return inList;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void writeData(String file, List<Student> data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        System.out.println(json);
        try {
            try {
                File f = new File(file);
                if (f.exists() && f.canWrite()) {
                    f.delete();
                    System.out.println("Deleted file: " + file + " to prepare for new data.");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(json);
            oos.close();
            fos.close();
            System.out.println("Finished writing data to " + file + ".");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
