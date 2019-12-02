
/**
 * 在这里给出对类 CaesarCipher1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class CaesarCipher1 {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher1(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted=new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)){
                int idx = alphabet.indexOf(currChar);
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            else{
                char toUpper=Character.toUpperCase(currChar);
                int idx= alphabet.indexOf(toUpper);
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher1 cc=new CaesarCipher1(26-mainKey);
        String decrypted=cc.encrypt(input);
        return decrypted;
    }
    
    public void Test(){
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message);
        System.out.println(encrypted);
    }
}
