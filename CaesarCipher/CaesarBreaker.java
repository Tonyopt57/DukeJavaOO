
/**
 * 在这里给出对类 CaesarBreaker 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.FileResource;

public class CaesarBreaker {
    public int[] countLetters(String message) {
        //snippet from lecture
        String alph = "abcdefghijklmnoqprstuvwxyz";
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
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted,26-dkey);
    }
    
    public String halfOfString(String message,int start){
        StringBuilder halfString=new StringBuilder();
        for (int i=start;i<message.length();i+=2){
            halfString.append(message.charAt(i));
        }
        return halfString.toString();
    }
    
    public int getKey(String s){
        int [] frequency=countLetters(s);
        int maxdex=maxIndex(frequency);
        int dkey=maxdex-4;
        if (maxdex < 4) {
            dkey = 26 - (4-maxdex);
        }
        return 26-dkey;
    }
    
    public String decryptTwoKeys(String encrypted){
        String halfString1=halfOfString(encrypted,0);
        String halfString2=halfOfString(encrypted,1);
        int dkey1=getKey(halfString1);
        int dkey2=getKey(halfString2);
        System.out.println("Key1 is "+dkey1);
        System.out.println("Key2 is "+dkey2);
        CaesarCipher cc=new CaesarCipher();
        return cc.encryptTwoKeys(encrypted,dkey1,dkey2);
    }
    
    public void testDecrypt() {
        FileResource fileResource = new FileResource();
        String encrypted = fileResource.asString();
        System.out.println("Encrypted message:\n" + encrypted);
        System.out.println("\nDecrypted message:\n" + decrypt(encrypted));
        
        String encrypted2 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println("Encrypted message:\n" + encrypted2);
        System.out.println("\nDecrypted message:\n" + decryptTwoKeys(encrypted2));
    }
}
