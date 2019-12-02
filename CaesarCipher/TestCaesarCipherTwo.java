
/**
 * 在这里给出对类 TestCaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class TestCaesarCipherTwo {
    public int[] countLetters(String message) {
        //snippet from lecture
        String alph = "abcdefghijklmnoqprstuvwxyz";
        //ABCDEFGHIJKLMNOPQRSTUVWXYZ
        int[] counts = new int[26];
        for (int k=0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1) {
                counts[dex] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] values) {
        int maxDex = 0;
        for (int k=0; k < values.length; k++) {
            if (values[k] > values[maxDex]) {
                maxDex = k;
            }
        }
        return maxDex;
    }
    
    public String halfOfString(String message,int start){
        StringBuilder halfString=new StringBuilder();
        for (int i=start;i<message.length();i+=2){
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }
    
    public int breakCaesarCipher(String input){
        int [] frequency=countLetters(input);
        int maxdex=maxIndex(frequency);
        int dkey=maxdex-4;
        if (maxdex < 4) {
            dkey = 26 - (4-maxdex);
        }
        return 26-dkey;        
    }    
    
    public void simpleTest(){
        CaesarCipherTwo cc1=new CaesarCipherTwo(21,8);
        FileResource f=new FileResource();
        String read=f.asString();;
        String encrypted=cc1.encrypt2(read);
        System.out.println("Message after encrypted: "+encrypted);          
        CaesarCipherTwo cc2=new CaesarCipherTwo(26-21,26-8);
        String decrypted=cc2.encrypt2(encrypted);
        System.out.println("Message after decrypted: "+decrypted);      
    }
    
    public String decryptTwoKeys(String encrypted) {
        String firstHalfEncrypted = halfOfString(encrypted,0);
        String secondHalfEncrypted = halfOfString(encrypted,1);
        int firstHalfKey = breakCaesarCipher(firstHalfEncrypted);
        int secondHalfKey = breakCaesarCipher(secondHalfEncrypted);
        CaesarCipher cc = new CaesarCipher();
        
        System.out.println("First key:\t" + firstHalfKey + "\nSecond key:\t"
                            + secondHalfKey);
                            
        return cc.encryptTwoKeys(encrypted,firstHalfKey,secondHalfKey);
    }    
    
    public void simpleTest2(){
        FileResource f=new FileResource();
        String read=f.asString();;
        System.out.println("Encrypted message:\n" + read);
        System.out.println("\nDecrypted message:\n" + decryptTwoKeys(read));
        
        //String encrypted2 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        //System.out.println("Encrypted message:\n" + encrypted2);
        //System.out.println("\nDecrypted message:\n" + decryptTwoKeys(encrypted2));
        
        //CaesarCipher cc = new CaesarCipher();
        //String encrypted2 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        //System.out.println("Encrypted message:\n" + encrypted2);
        //System.out.println("\nDecrypted message:\n" + cc.encryptTwoKeys(encrypted2,12,2));        
    }
}
