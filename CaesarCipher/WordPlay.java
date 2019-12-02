
/**
 * 在这里给出对类 WordPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class WordPlay {
    public boolean isVowel(char ch){
        if (ch==('a')||ch==('e')||ch==('i')|| ch==('o')||ch==('u')||ch==('A')||
        ch==('E')||ch==('I')||ch==('O')||ch==('U')){
            return true;
        }
        return false;
    }
    
    public void testIsVowel(){
        System.out.println(isVowel('A'));
    }
    
    public String replaceVowel(String phrase,char ch){
        StringBuilder replace=new StringBuilder("");
        for (int i=0;i<phrase.length();i++){
            char in=phrase.charAt(i);
            if (isVowel(in)){
                replace.append(ch);
            }
            else{
                replace.append(in);
            }
        }
        return replace.toString();
    }
    
    public void testReplaceVowel(){
        System.out.println(replaceVowel("ACEBFT",'#'));
    }
    
    public String emphasize(String phrase,char ch){
        StringBuilder replace=new StringBuilder("");
        for (int i=0;i<phrase.length();i++){
            char in=phrase.charAt(i);
            if (in==ch){
                if((i+1)%2!=0){
                    replace.append('*');
                }
                else{replace.append('+');}
            }
            else{
                replace.append(in);
            }
        }
        return replace.toString();
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga",'a'));
    }
}
