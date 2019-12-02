
/**
 * 在这里给出对类 TestCaesarCipher1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class TestCaesarCipher1 {
    public int[] countLetters(String message) {
        //snippet from lecture
        String alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
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
        FileResource f=new FileResource();
        String read=f.asString();
        CaesarCipher1 cc1=new CaesarCipher1(18);
        String encrypted=cc1.encrypt(read);
        System.out.println("Message after encrypted: "+encrypted);
        
        CaesarCipher1 cc2=new CaesarCipher1(26-18);
        String decrypted=cc2.encrypt(encrypted);
        System.out.println("Message after decrypted: "+decrypted);
        
        int dkey1=breakCaesarCipher(read);
        System.out.println("Key1 is "+dkey1);
        CaesarCipher1 cc=new CaesarCipher1(dkey1);
        String encrypted1 = cc.encrypt(read);
        System.out.println(encrypted1);
    }    
}
