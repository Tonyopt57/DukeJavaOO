
/**
 * 在这里给出对类 CountWord 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import java.util.*;
public class CountWord {
    private HashMap<String,Integer> codonMap;
    public CountWord(){
        codonMap=new HashMap<String,Integer>();
    }

    public void buildCodonMap(int start,String dna){
        codonMap.clear();
        for (int index = start; dna.length() - index > 3; index += 3) {
            String currentCodon = dna.substring(index, index + 3);
            if (!codonMap.containsKey(currentCodon)) {
                codonMap.put(currentCodon, 1);
            } else {
                codonMap.put(currentCodon, codonMap.get(currentCodon) + 1);
            }
        }
    }
    
    public String getMostCommonCodon(){
        int most=0;
        String mostcommon="";
        for (String s:codonMap.keySet()){
            int curr=codonMap.get(s);
            if (curr>most){
                most=curr;
                mostcommon=s;
            }
        }
        return mostcommon;
    }
    
    public void printCodonCounts(int start,int end){
        for (String s:codonMap.keySet()){
            int count=codonMap.get(s);
            if (count>start && count<end){
                System.out.print(s+"\t"+count);
            }
        }
    }
    
    public void testBuildCodonMap() {
        FileResource fileResource = new FileResource();
        String dna = fileResource.asString();
        dna = dna.toUpperCase();
        for (int index = 0; index <= 2; index++) {
            System.out.println("\nTesting with start position " + index + ":\n");
            buildCodonMap(index, dna);
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("Total unique codons found: " + codonMap.size());
            System.out.println("\nMost common codon: " + mostCommonCodon
                    + "\t" + codonMap.get(mostCommonCodon));
            printCodonCounts(4, 8);
            for (String s:codonMap.keySet()){
                int curr=codonMap.get(s);
                if (curr==7){
                    System.out.println("appears 7 times: "+s);
                }
            }
        }

    }
    
    
}
