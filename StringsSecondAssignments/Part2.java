
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    public int howMany(String stringa,String stringb){
        int startindex=0;
        int count=0;
        while (true){
        int currentindex=stringb.indexOf(stringa,startindex);
        if (currentindex==-1){break;}
        else{
            count+=1;
            startindex=currentindex+stringa.length();
        }
    }return count;
    }
    public void testhowMany(){
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
    }

}
