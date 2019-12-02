
/**
 * 在这里给出对类 WordFrequency 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;
public class WordFrequency {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequency(){
        myWords=new ArrayList<String>();
        myFreqs=new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource f=new FileResource();
        
        for(String s : f.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int most=0;
        int maxindex=0;
        for (int i=0;i<myWords.size();i++){
            int curr=myFreqs.get(i);
            if (most==0 ||curr>most){
                most=curr;
                maxindex=i;
            }
        }
        return maxindex;
    }
    
    public void Tester(){
        findUnique();
        int maxdex=findIndexOfMax();
        String mostnumber=myWords.get(maxdex);
        int mostappear=myFreqs.get(maxdex);
        System.out.println("Total number of unique words: "+myWords.size());
        for (int i=0;i<myWords.size();i++){
            System.out.println(myFreqs.get(i)+"\t"+myWords.get(i));
        }  
        System.out.println("The words that appear most is "+mostnumber+" appear "+mostappear+" times");
      
    }
}
