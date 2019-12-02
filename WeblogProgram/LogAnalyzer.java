
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records=new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr=new FileResource(filename);
         for (String s:fr.lines()){
             WebLogParser c1=new WebLogParser();
             LogEntry cc=c1.parseEntry(s);
             records.add(cc);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> content=new ArrayList<String>();
         for (LogEntry s:records){
             String ipAddress=s.getIpAddress();
             if (!content.contains(ipAddress)){
                 content.add(ipAddress);
             }
         }
         return content.size();
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry s:records){
             int statcode=s.getStatusCode();
             String Rdate=s.getAccessTime().toString();
             if (statcode>num){
                 System.out.println(s);
                 System.out.println(Rdate);
             }
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         ArrayList<String> datevisit=new ArrayList<String>();
         ArrayList<String> content=new ArrayList<String>();
         for (LogEntry s:records){
             String ipAddress=s.getIpAddress();
             String Rdate=s.getAccessTime().toString();
             String date=Rdate.substring(4,10);
             if (!content.contains(ipAddress)){
                if (date.equals(someday)){ 
                    datevisit.add(s.toString());
                    content.add(ipAddress);
                }
             }
         }
         return datevisit;
     }
     
     public int countUniqueIPsInRange(int low,int high){
         ArrayList<String> content=new ArrayList<String>();
         ArrayList<String> total=new ArrayList<String>();
         for (LogEntry s:records){
             String ipAddress=s.getIpAddress();
             int stacode=s.getStatusCode();
             if (!content.contains(ipAddress)){
                 if (stacode>=low && stacode<high){
                     total.add(s.toString());
                     content.add(ipAddress);                     
                 }
             }
         }
         return total.size();
     }
     
     public HashMap<String,Integer> countVisitPerIP(){
         HashMap<String,Integer> counts=new HashMap<String,Integer>();
         for (LogEntry le:records){
             String ipAddress=le.getIpAddress();
             if (!counts.containsKey(ipAddress)){
                 counts.put(ipAddress,1);
             }
             else{
                 int num=counts.get(ipAddress);
                 counts.put(ipAddress,num+1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> counts){
         //counts=countVisitPerIP();
         int max=0;
         for (String s:counts.keySet()){
             int curr=counts.get(s);
             if (curr>max){
                 max=curr;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts){
         ArrayList<String> IpVisitMost=new ArrayList<String>();
         int max=0;
         for (String s:counts.keySet()){
             int curr=counts.get(s);
             if (curr>max){
                 max=curr;
             }
         }
         for (String s:counts.keySet()){
             if (counts.get(s)==max){
                 IpVisitMost.add(s);
             }
         }
         return IpVisitMost;
     }
     
     public HashMap<String,ArrayList<String>> UniqueiPsForDays(){
         HashMap<String,ArrayList<String>> IpsForDays=new HashMap<String,ArrayList<String>>();
         
         for (LogEntry s:records){
             ArrayList<String> UniqueIP=new ArrayList<String>();
             String ipAddress=s.getIpAddress();
             String Rdate=s.getAccessTime().toString();
             String date=Rdate.substring(4,10);
             if (!IpsForDays.containsKey(date)){
                 UniqueIP.add(ipAddress);
                 IpsForDays.put(date,UniqueIP);
             }
             else{
                 if (!IpsForDays.get(date).contains(ipAddress)){
                     IpsForDays.get(date).add(ipAddress);
                 }
             }
         }
         return IpsForDays;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> IpsForDays=new HashMap<String,ArrayList<String>>();
         
         for (LogEntry s:records){
             
             String ipAddress=s.getIpAddress();
             String Rdate=s.getAccessTime().toString();
             String date=Rdate.substring(4,10);
             if (!IpsForDays.containsKey(date)){
                 
                 IpsForDays.put(date,new ArrayList<String>());
             }
             IpsForDays.get(date).add(ipAddress);
         }
         return IpsForDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays){
         
         int max=0;
         String maxVisitDay="";
         for (String s:iPsForDays.keySet()){
             int curr=iPsForDays.get(s).size();
             if (curr>max){
                 max=curr;
                 maxVisitDay=s;
             }
         }
         return maxVisitDay;
     }
     
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> eachday, String day) {
        ArrayList<String> ip = new ArrayList<String>();
        HashMap<String, Integer> eachip = new HashMap<String, Integer>();
        ArrayList<String> mostthatday = new ArrayList<String>();
        int mostnumber = 0;
        int currentnumber;
        for (String time: eachday.keySet()) {
            if (time.equals(day)) {
                ip = eachday.get(time);
            }
        }
        for (int k = 0; k < ip.size(); k++) {
            if (!eachip.containsKey(ip.get(k))) {
                eachip.put(ip.get(k), 1);
            }
            else eachip.put(ip.get(k), eachip.get(ip.get(k))+1);
        }
        mostthatday = iPsMostVisits(eachip);
        return mostthatday;
    }
}
