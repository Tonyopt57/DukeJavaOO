
/**
 * 在这里给出对类 WordsInFiles 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.io.File;
import edu.duke.*;
import java.util.*;

public class WordsInFiles {

    private HashMap<String,ArrayList<String>> WordsInFiles;
    public WordsInFiles(){
         WordsInFiles=new HashMap<String,ArrayList<String>>();
    }
    
    private void addWordsFromFiles(File f){
        FileResource fr=new FileResource(f);
        String fname=f.getName();
        for (String s:fr.words()){
            if (!WordsInFiles.containsKey(s)){
                ArrayList<String> name=new ArrayList<String>();
                name.add(fname);
                WordsInFiles.put(s,name);
            }
            else {
                ArrayList<String> curr=WordsInFiles.get(s);
                if (!curr.contains(fname)){
                    curr.add(fname);
                    WordsInFiles.put(s,curr);
                }
            }
        }
    }
    
    public void buildWordFileMap(){
        WordsInFiles.clear();
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            addWordsFromFiles(f);
        }
    }
    
    public int maxNumber(){
        int max=0;
        for (String s:WordsInFiles.keySet()){
            int currnumber=WordsInFiles.get(s).size();
            if (currnumber>max){
                max=currnumber;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> numberForWords=new ArrayList<String>();
        for (String s:WordsInFiles.keySet()){
            ArrayList<String> curr=WordsInFiles.get(s);
            if (curr.size()==number){
                numberForWords.add(s);
            }
        }
        return numberForWords;
    }
    
    public void printFilesIn(String word){
        ArrayList<String> curr=WordsInFiles.get(word);
        for (int index = 0; index < curr.size(); index++) {
            System.out.println(curr.get(index));
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int maxnumber=maxNumber();
        System.out.println("Words happen most happen "+maxnumber);
        for (String s:WordsInFiles.keySet()){
            System.out.print(WordsInFiles.get(s)+"\t"+s+"\n");
        }
        System.out.println("Words that showed in 3 files are "+wordsInNumFiles(3));
        System.out.println(wordsInNumFiles(4).size());
        ArrayList<String> tree=WordsInFiles.get("tree");
        System.out.println(tree);
    }
}

/**
public class wordsInFiles {
    private HashMap<String, ArrayList<String>> wordMap;
    // constructor no void sign
    public wordsInFiles(){
        wordMap = new HashMap<String, ArrayList<String>>();
    }
    private void addWords(File f){
        FileResource fi = new FileResource(f);
        String fname = f.getName();
        for (String s : fi.words()){
            if (!wordMap.containsKey(s)){
                ArrayList<String> name = new ArrayList<String>();
                name.add(fname);
                wordMap.put(s,name);
            } else {
                ArrayList<String> curr = wordMap.get(s);
                if (!curr.contains(fname)){
                    curr.add(fname);
                    wordMap.put(s,curr);
                }
            }
        }
    }
    private void buildWordMap(){
        wordMap.clear();
        DirectoryResource di = new DirectoryResource();
        for (File f : di.selectedFiles()){
            addWords(f);
        }
    }
    private ArrayList wordsInNum(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String s: wordMap.keySet()){
            
            if (wordMap.get(s).size() == number){
                words.add(s);
            }
        }
        return words;
    }
    public void tester(){
        buildWordMap();
        for (String s: wordMap.keySet()){
            System.out.println(wordMap.get(s)+"\t"+s);
        }
        System.out.println("words that showed in three files are: "+wordsInNum(3));
    }
}
*/