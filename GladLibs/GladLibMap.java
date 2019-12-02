
/**
 * 在这里给出对类 GladLibMap 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String,ArrayList<String>> myMap;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    private Random myRandom;
    private ArrayList<String> usedwordList;
    
    public GladLibMap(){
        myMap=new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedwordList=new ArrayList<String> ();        
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
    }    
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }    
    
    public void initializeFromSource(String source){
        String [] labels={"adjective","noun","color","country",
        "name","animal","timeframe","verb","fruit"};
        for (String s:labels){
            ArrayList<String> words=readIt(source+"/"+s+".txt");
            myMap.put(s,words);
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String labels){
        if (labels.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(labels));
    }
    
    
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        String total="";
        while (usedwordList.contains(sub)){
            sub=getSubstitute(w.substring(first+1,last));
        }
        usedwordList.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    public int totalWordsInMap(){
        int totalsize=0;
        for (String s:myMap.keySet()){
            int fsize=myMap.get(s).size();
            totalsize+=fsize;
        }
        return totalsize;
    }
    

    public int totalWordsConsidered(){
        int totalconsider=0;
        ArrayList<String> used=new ArrayList<String>();
        FileResource fr=new FileResource("data/madtemplate3.txt");
        for (String s:fr.words()){
            if (s.indexOf("<")==0){             
                String added=s.substring(1,s.indexOf(">"));
                if (!used.contains(added)){
                    used.add(added);
                }
            }
        }
        for (String s:myMap.keySet()){
            int fsize=myMap.get(s).size();
            if (used.contains(s)){
                totalconsider+=fsize;
            }
        }
        return totalconsider;
    }

    public void makeStory(){
        usedwordList.clear();
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\ntotal number of words that were placed: "+usedwordList.size());
        int totalsize=totalWordsInMap();
        System.out.println("\ntotal number of words that can be selected: "+totalsize);
        int totalconsider=totalWordsConsidered();
        System.out.println("Total words considered in the story: "+totalconsider);
    }    
}

