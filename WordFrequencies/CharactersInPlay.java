
/**
 * 在这里给出对类 CharactersInPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import java.util.*;
import edu.duke.*;
public class CharactersInPlay {
    private ArrayList<String> myChar;
    private ArrayList<Integer> myCount;
    
    public CharactersInPlay(){
        myChar=new ArrayList<String>();
        myCount=new ArrayList<Integer>();
    }
    
    public void update(String person){
        person=person.toLowerCase();
        int index=myChar.indexOf(person);
        if (index==-1){
            myChar.add(person);
            myCount.add(1);
        }
        else{
            int num=myCount.get(index);
            myCount.set(index,num+1);
        }
    }

    public void findAllCharacters(){
        myChar.clear();
        myCount.clear();
        FileResource f=new FileResource();
        for (String line:f.lines()){
            int periodinline=line.indexOf(".");
            if(periodinline!=-1){
                update(line.substring(0,periodinline));
            }
        }
    }
    
    public int findIndexOfMax(){
        int most=0;
        int maxindex=0;
        for (int i=0;i<myChar.size();i++){
            int curr=myCount.get(i);
            if (most==0 ||curr>most){
                most=curr;
                maxindex=i;
            }
        }
        return maxindex;
    }
    
    public void Tester(){
        findAllCharacters();
        //for (int i=0;i<myChar.size();i++){
            //System.out.println(myChar.get(i)+"\t"+myCount.get(i));
        //}
        int maximum=findIndexOfMax();
        System.out.println(myChar.get(maximum)+"\t"+myCount.get(maximum));
        charactersWithNumParts(10,15);
        
    }
    
    public void charactersWithNumParts(int num1,int num2){
        for (int i=0;i<myChar.size();i++){
            if (myCount.get(i)>=num1 && myCount.get(i)<=num2){
                System.out.println(myChar.get(i)+"\t"+myCount.get(i));
            }
        }
    }
}
