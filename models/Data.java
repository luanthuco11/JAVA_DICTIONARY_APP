package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Data {
   public HashMap<String, String> listHashMap = new HashMap<>(133334, 0.75f);

    public Data(){
        try {
            int countLine = 1;
            BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream("slang.txt")));
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                countLine++;
                if (line.equals("")){
                    System.out.println("Errot at line " + countLine);
                    continue;
                }
                
                String part[] = line.split("`");
                if(part.length == 2) listHashMap.put(part[0], part[1]);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Error to import from file");
        }
    }
}
