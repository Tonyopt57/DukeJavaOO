
/**
 * 在这里给出对类 Tester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class Tester {
    public void testCaesar(){
        CaesarCipher cc=new CaesarCipher(3);
        FileResource fr=new FileResource();
        String content=fr.asString();
        String encode=cc.encrypt(content);
        System.out.println(encode);
        String decode=cc.decrypt(encode);
        System.out.println(decode);
    }
    
    public void testCaesarCracker(){
        CaesarCracker xx=new CaesarCracker();
        //CaesarCracker xx=new CaesarCracker('a');
        FileResource fr=new FileResource();
        String content=fr.asString();        
        int key=xx.getKey(content);
        System.out.println(key);
    }
    
    public void testVigenereCipher(){
        int[] key={17,14,12,4};
        VigenereCipher cc=new VigenereCipher(key);
        FileResource fr=new FileResource();
        String content=fr.asString();        
        String encode=cc.encrypt(content);
        System.out.println(encode);
        String decode=cc.decrypt(encode);
        System.out.println(decode);        
    }
    
    public void testsliceString(){
        VigenereBreaker cc=new VigenereBreaker();
        System.out.println(cc.sliceString("abcdefghijklm", 0, 3));
        System.out.println(cc.sliceString("abcdefghijklm", 4, 5));
    }
    
    public void testTryKeyLength(){
        FileResource fr=new FileResource();
        String content=fr.asString(); 
        VigenereBreaker cc=new VigenereBreaker();
        int [] key=cc.tryKeyLength(content,4,'e');
        for (int i=0;i<key.length;i++){
            System.out.println("Key "+(i+1)+": "+key[i]);
        }
    }
}
