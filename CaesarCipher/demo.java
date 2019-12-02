
/**
 * 在这里给出对类 demo 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class demo {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public demo(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        mainKey = key;
    }
    
    public String encrypt(String input){
        StringBuilder sb = new StringBuilder(input);
        String alphaLower = alphabet.toLowerCase();
        String shiftLower = shiftedAlphabet.toLowerCase();
        for (int i=0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);
            int idxLower = alphaLower.indexOf(c);
            if(Character.isLowerCase(c)){
                if(idxLower != -1){
                    c = shiftLower.charAt(idxLower);
                    sb.setCharAt(i, c);
                }
            }
            else{
                if (idx != -1){
                    c = shiftedAlphabet.charAt(idx);
                    sb.setCharAt(i, c);
                }
            }
        }
        return sb.toString();
    }
    
    public String decrypt(String input){
        demo cc = new demo(26 - mainKey);
        String decrypt = cc.encrypt(input);
        return decrypt;
    }
    
    public void testEncrypt(){
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encryptedMessage = encrypt(message);
        System.out.println(encryptedMessage);
        /*I was making a mistake as I was decrypting the string 'message', instead of the
         * encryptedMessage, so the result was another encryption instead of a decryption:
         */
        System.out.println("Decrypted message: " + decrypt(encryptedMessage));
    }

}
