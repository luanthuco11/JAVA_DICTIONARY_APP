package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class SlangWordManager {
    public HashMap<String, SlangWord> listHashMap = new HashMap<>();
    public HashMap<String, List<String>> listSlang = new HashMap<>();
    public List<String> histories = new ArrayList<>();
    public List<List<String>> originSlangs = new ArrayList<>();
    public HashMap<String, List<String>> originMeans = new HashMap<>();

    public SlangWordManager(){
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
                    listHashMap.put(part[0], new SlangWord(part[0], part[1]));
                    originSlangs.add(List.of(part[0], part[1]));
                    String part2[] = part[1].split(" ");
                    for(int i = 0; i< part2.length; i++){
                        char lastChar = part2[i].charAt(part2[i].length() - 1);
                        if (lastChar == '|') {
                            part2[i] = part2[i].substring(0, part2[i].length() - 1);
                        }
                        listSlang.putIfAbsent(part2[i], new ArrayList<>());
                        originMeans.putIfAbsent(part2[i], new ArrayList<>());
                        
                        if(!part2[i].isEmpty()){
                            listSlang.get(part2[i]).add(part[0]);
                            originMeans.get(part2[i]).add(part[0]);
                        }
                    }
                }
            }
            System.out.println("Da lay data xong");
       
            reader.close();
        } catch (Exception e) {
            System.out.println("Error to import from file");
        }
    }

    public boolean isExist(String slang){
        return listHashMap.containsKey(slang);
    }

    public void addSlangOverwrite(SlangWord slangWord){
        listHashMap.put(slangWord.getSlang(), slangWord);
        
        
                  
        String part2[] = slangWord.getMean().split(" ");
        for(int i = 0; i< part2.length; i++){
            char lastChar = part2[i].charAt(part2[i].length() - 1);
            if (lastChar == '|') {
                part2[i] = part2[i].substring(0, part2[i].length() - 1);
            }
            listSlang.putIfAbsent(part2[i], new ArrayList<>());
            if(!part2[i].isEmpty())listSlang.get(part2[i]).add(slangWord.getSlang());
        }
    }
     public void addSlangDup(SlangWord slangWord){

       String newMean = listHashMap.get(slangWord.getSlang()).getMean() + "| " +slangWord.getMean();
        listHashMap.get(slangWord.getSlang()).setMean(newMean);

         String part2[] = slangWord.getMean().split(" ");
        for(int i = 0; i< part2.length; i++){
            char lastChar = part2[i].charAt(part2[i].length() - 1);
            if (lastChar == '|') {
                part2[i] = part2[i].substring(0, part2[i].length() - 1);
            }
            listSlang.putIfAbsent(part2[i], new ArrayList<>());
            if(!part2[i].isEmpty())listSlang.get(part2[i]).add(slangWord.getSlang());
        }

    }

    public void deleteSlangWord(String slang){
        listHashMap.remove(slang);
    }
    public void editSlangWord(String oldSlang, SlangWord newSlangWord){
        if(oldSlang.equals(newSlangWord.getSlang())){
            listHashMap.get(oldSlang).setMean(newSlangWord.getMean());
        }else{
            listHashMap.remove(oldSlang);
            listHashMap.put(newSlangWord.getSlang(), newSlangWord);
        }
    }
    public void resetSlangWords(){
        HashMap<String , SlangWord> newList = new HashMap<>();
        originSlangs.forEach(slang -> {
            newList.put(slang.get(0), new SlangWord(slang.get(0), slang.get(1)));
        });
        listHashMap = newList;

        HashMap<String, List<String>> newMeans = new HashMap<>();
        for(Map.Entry<String, List<String>> entry : originMeans.entrySet())
        {
            newMeans.put(entry.getKey(),new ArrayList<>(entry.getValue()) );
        }
        listSlang = newMeans;
        System.out.println("Reset done");
    }
}
