package models;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;



public class SlangWordManager {
    public HashMap<String, SlangWord> listSlang = new HashMap<>();
    public HashMap<String, Set<String>> listMean = new HashMap<>();
    public List<String> histories = new ArrayList<>();
    public List<List<String>> originSlangs = new ArrayList<>();
    public HashMap<String, Set<String>> originMeans = new HashMap<>();

    public SlangWordManager(){
        try {
       
            BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream("slang.txt")));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                if (line.equals("")){
                    continue;
                }
                String part[] = line.split("`");
                if(part.length == 2) {
                    listSlang.put(part[0], new SlangWord(part[0], part[1]));
                    originSlangs.add(List.of(part[0], part[1]));
                    String part2[] = part[1].split(" ");
                    for(int i = 0; i< part2.length; i++){
                        char lastChar = part2[i].charAt(part2[i].length() - 1);
                        if (lastChar == '|') {
                            part2[i] = part2[i].substring(0, part2[i].length() - 1);
                        }

                        listMean.putIfAbsent(part2[i], new HashSet<>());
                        originMeans.putIfAbsent(part2[i], new HashSet<>());
                        
                        if(!part2[i].isEmpty()){
                            listMean.get(part2[i]).add(part[0]);
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
        return listSlang.containsKey(slang);
    }

    public void addSlangOverwrite(SlangWord slangWord){
        listSlang.put(slangWord.getSlang(), slangWord);
                  
        String part2[] = slangWord.getMean().split(" ");
        for(int i = 0; i< part2.length; i++){
            char lastChar = part2[i].charAt(part2[i].length() - 1);
            if (lastChar == '|') {
                part2[i] = part2[i].substring(0, part2[i].length() - 1);
            }

            listMean.putIfAbsent(part2[i], new HashSet<>());

            if(!part2[i].isEmpty())listMean.get(part2[i]).add(slangWord.getSlang());
        }
    }
     public void addSlangDup(SlangWord slangWord){

       String newMean = listSlang.get(slangWord.getSlang()).getMean() + "| " +slangWord.getMean();
        listSlang.get(slangWord.getSlang()).setMean(newMean);

         String part2[] = slangWord.getMean().split(" ");
        for(int i = 0; i< part2.length; i++){
            char lastChar = part2[i].charAt(part2[i].length() - 1);
            if (lastChar == '|') {
                part2[i] = part2[i].substring(0, part2[i].length() - 1);
            }
            listMean.putIfAbsent(part2[i], new HashSet<>());
            if(!part2[i].isEmpty())listMean.get(part2[i]).add(slangWord.getSlang());
        }

    }

    public void deleteSlangWord(String slang){
        listSlang.remove(slang);
        for (Set<String> value : listMean.values()) { 
            value.remove(slang); 
        }
    }
    public void editSlangWord(SlangWord oldSlangWord, SlangWord newSlangWord){
        if(oldSlangWord.getSlang().equals(newSlangWord.getSlang())){
            listSlang.get(oldSlangWord.getSlang()).setMean(newSlangWord.getMean());
            for (Set<String> value : listMean.values()) { 
               
                value.remove(oldSlangWord.getSlang());
            }
            String part2[] = newSlangWord.getMean().split(" ");
            for(int i = 0; i< part2.length; i++){
                char lastChar = part2[i].charAt(part2[i].length() - 1);
                if (lastChar == '|') {
                    part2[i] = part2[i].substring(0, part2[i].length() - 1);
                }
                listMean.putIfAbsent(part2[i], new HashSet<>());
                if(!part2[i].isEmpty())listMean.get(part2[i]).add(newSlangWord.getSlang());
            }
        }else{
            listSlang.remove(oldSlangWord.getSlang());
            listSlang.put(newSlangWord.getSlang(), newSlangWord);
            for (Set<String> value : listMean.values()) { 
            if(value.contains(oldSlangWord.getSlang())){
                value.remove(oldSlangWord.getSlang());
                value.add(newSlangWord.getSlang());
            }
        }
        }
       


    }
    public void resetSlangWords(){
        HashMap<String , SlangWord> newList = new HashMap<>();
        originSlangs.forEach(slang -> {
            newList.put(slang.get(0), new SlangWord(slang.get(0), slang.get(1)));
        });
        listSlang = newList;

        HashMap<String, Set<String>> newMeans = new HashMap<>();
        for(Map.Entry<String, Set<String>> entry : originMeans.entrySet())
        {
            newMeans.put(entry.getKey(),new HashSet<>(entry.getValue()) );
        }
        listMean = newMeans;
     
    }
    public int getRandomNumber() {
    
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); 
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        long seed = (long)year * 10000 + (long)month * 100 + day; 
        
        Random random = new Random(seed);
        
        int range = listSlang.size();
        int randomNumber = random.nextInt(range);

        return randomNumber;
    }
}
