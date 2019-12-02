import java.util.*;
import edu.duke.*;
import java.io.File;
public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //REPLACE WITH YOUR CODE
        StringBuilder slice=new StringBuilder();
        for (int i=whichSlice;i<message.length();i+=totalSlices){
            slice.append(message.charAt(i));
        }
        return slice.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        CaesarCracker ccr=new CaesarCracker(mostCommon);
        for (int i=0;i<klength;i++){
            String s=sliceString(encrypted,i,klength);
            int dkey=ccr.getKey(s);
            key[i]=dkey;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr=new FileResource();
        String content=fr.asString();
        int [] key=tryKeyLength(content,5,'e');
        VigenereCipher vc=new VigenereCipher(key);
        System.out.println(vc.decrypt(content));
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary=new HashSet<String>();
        for (String line:fr.lines()){
            line=line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }
    
    public int countWords(String message,HashSet<String> dictionary){
        String [] words=message.split("\\W+");
        int i=0;
        for (String s:words){
            String word=s.toLowerCase();
            if (dictionary.contains(word)){
                i+=1;
            }
        }
        return i;
    }
    
    public String breakForLanguage(String encrypted,HashSet<String> dictionary){
        int max=0;
        String decode="";
        for (int i=1;i<101;i++){
            int [] key=tryKeyLength(encrypted,i,'e');
            VigenereCipher vc=new VigenereCipher(key);
            String content=vc.decrypt(encrypted);
            int count=countWords(content,dictionary);
            if (count>max){
                max=count;
                decode=content;
            }
        }
        return decode;
    }
    //count how many valid words
    public int breakForLanguage2(String encrypted,HashSet<String> dictionary){
        int max=0;
        String decode="";
        for (int i=1;i<101;i++){
            int [] key=tryKeyLength(encrypted,i,'e');
            VigenereCipher vc=new VigenereCipher(key);
            String content=vc.decrypt(encrypted);
            int count=countWords(content,dictionary);
            if (count>max){
                max=count;
                decode=content;
            }
        }
        return max;
    }
    //return keylength
    public int breakForLanguage3(String encrypted,HashSet<String> dictionary){
        int klength=0;
        int max=0;
        String decode="";
        for (int i=1;i<101;i++){
            int [] key=tryKeyLength(encrypted,i,'e');
            VigenereCipher vc=new VigenereCipher(key);
            String content=vc.decrypt(encrypted);
            int count=countWords(content,dictionary);
            if (count>max){
                max=count;
                klength=i;
                decode=content;
            }
        }
        return klength;
    }      
    
    public void breakVigenere2 (){
        FileResource fr=new FileResource();
        String content=fr.asString();
        FileResource fr2=new FileResource();
        HashSet<String> dictionary=readDictionary(fr2);
        String doc=breakForLanguage(content,dictionary);
        int max=breakForLanguage2(content,dictionary);
        int klength=breakForLanguage3(content,dictionary);
        System.out.println(doc);
        System.out.println("valid words in dictory: "+max);
        System.out.println("Key length is: "+klength);
    }
    
    public void breakVigenere3 (){
        FileResource fr=new FileResource();
        String content=fr.asString();
        FileResource fr2=new FileResource();
        HashSet<String> dictionary=readDictionary(fr2);
        int [] key=tryKeyLength(content,38,'e');
        VigenereCipher vc=new VigenereCipher(key); 
        String decode=vc.decrypt(content);
        int count=countWords(decode,dictionary);
        System.out.println("Valid words count: "+count);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet="abcdefghijklmnopqrstuvwxyz";
        HashMap<Character,Integer> countChar=new HashMap<Character,Integer>();
        for (String s:dictionary){
            String slower=s.toLowerCase();
            for (int i=0; i<slower.length();i++){
                char ch=slower.charAt(i);
                if (!countChar.containsKey(ch)){
                    countChar.put(ch,1);
                }
                else{
                    countChar.put(ch,countChar.get(ch)+1);
                }
            }
        }
        int max=0;
        for (char ch:countChar.keySet()){
            int curr=countChar.get(ch);
            if (curr>max){
                max=curr;
                
            }
        }

        for (char ch:countChar.keySet()){
            if (countChar.get(ch)==max){
                return ch;
            }
        }
        return 'e';
    }
    
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int max=0;
        for (String s:languages.keySet()){
            String content=breakForLanguage(encrypted,languages.get(s));
            int curr=countWords(content,languages.get(s));
            if (curr>max){
                max=curr;
            }
        }
        for (String s:languages.keySet()){
            String content=breakForLanguage(encrypted,languages.get(s));
            int curr=countWords(content,languages.get(s));
            if (curr==max){
                System.out.println(content+"\n"+s);
            }
        }
    }
    
    public String breakVigenere4 (String encrypted,HashSet<String> dictionary){
        int max=0;
        String decode="";
        for (int i=1;i<101;i++){
            int [] key=tryKeyLength(encrypted,i,mostCommonCharIn(dictionary));
            VigenereCipher vc=new VigenereCipher(key);
            String content=vc.decrypt(encrypted);
            int count=countWords(content,dictionary);
            if (count>max){
                max=count;
                decode=content;
            }
        }
        return decode;
    }
    
    public void breakVigenere4 (){
        FileResource mfr=new FileResource();
        String content=mfr.asString();
        HashMap<String,HashSet<String>> hm=new HashMap<String,HashSet<String>>();
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            hm.put(f.getName(),readDictionary(fr));
            System.out.println(f.getName()+" read.");
        }
        System.out.println();
        breakForAllLangs(content,hm);
    }
}
