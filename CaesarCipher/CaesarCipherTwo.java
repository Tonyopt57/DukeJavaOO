
/**
 * 在这里给出对类 CaesarCipherTwo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1,int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        mainKey1=key1;
        mainKey2=key2;
    }
    
    public String encrypt2(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if ((i+1)%2!=0){
                if (Character.isUpperCase(currChar)){
                    int idx = alphabet.indexOf(currChar);
                    if(idx != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChar = shiftedAlphabet1.charAt(idx);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else{
                    char toUpper=Character.toUpperCase(currChar);
                    int idx= alphabet.indexOf(toUpper);
                    if(idx != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChar = Character.toLowerCase(shiftedAlphabet1.charAt(idx));
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            else{
                if (Character.isUpperCase(currChar)){
                    int idx = alphabet.indexOf(currChar);
                    if(idx != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChar = shiftedAlphabet2.charAt(idx);
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    }
                }
                else{
                    char toUpper=Character.toUpperCase(currChar);
                    int idx= alphabet.indexOf(toUpper);
                    if(idx != -1){
                        //Get the idxth character of shiftedAlphabet (newChar)
                        char newChar = Character.toLowerCase(shiftedAlphabet2.charAt(idx));
                        //Replace the ith character of encrypted with newChar
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc=new CaesarCipherTwo(26-mainKey1,26-mainKey2);
        String decrypted=cc.encrypt2(input);
        return decrypted;        
    }
}
