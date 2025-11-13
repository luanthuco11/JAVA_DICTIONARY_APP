package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {
   public HashMap<String, String> listHashMap = new HashMap<>();
   public HashMap<String, List<String>> listSlang = new HashMap<>();
    public List<String> histories = new ArrayList<>();
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
                if(part.length == 2) {
                    listHashMap.put(part[0], part[1]);
                    String part2[] = part[1].split(" ");
                    for(int i = 0; i< part2.length; i++){
                        char lastChar = part2[i].charAt(part2[i].length() - 1);
                        if (lastChar == '|') {
                            part2[i] = part2[i].substring(0, part2[i].length() - 1);
                        }
                        listSlang.putIfAbsent(part2[i], new ArrayList<>());
                        if(!part2[i].isEmpty())listSlang.get(part2[i]).add(part[0]);
                    }
                }
            }
            System.out.println("Da lay data xong");
       
            reader.close();
        } catch (Exception e) {
            System.out.println("Error to import from file");
        }
    }
}
