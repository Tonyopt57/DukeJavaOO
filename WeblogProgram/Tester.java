
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("short-test_log");
        cc.printAll();
    }
    
    public void testUniqueIp(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        int totalUniqueIp=cc.countUniqueIPs();
        System.out.println("Number of unique IP: "+totalUniqueIp);
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog1_log");
        cc.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        System.out.println(cc.uniqueIPVisitsOnDay("Sep 27"));
        System.out.println(cc.uniqueIPVisitsOnDay("Sep 27").size());
    }
    
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        int num=cc.countUniqueIPsInRange(200,299);
        System.out.println("There are "+num+" entries in this range.");
    }
    
    
    public void testCountVisitPerIP(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("short-test_log");
        HashMap<String,Integer> counts=cc.countVisitPerIP();
        System.out.println(counts);
    }
    
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        HashMap<String,Integer> counts=cc.countVisitPerIP();
        int max=cc.mostNumberVisitsByIP(counts);
        System.out.println("Most number Visits by IP: "+max);
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        HashMap<String,Integer> counts=cc.countVisitPerIP();
        ArrayList<String> MostVisitIP=cc.iPsMostVisits(counts);
        System.out.println("The Ip that visit most is: "+MostVisitIP);
    }
    
    public void testUniqueIPsForDays(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> IpsForDays=cc.UniqueiPsForDays();
        System.out.println(IpsForDays);
    }
    
    public void testIPsForDays(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> IpsForDays=cc.iPsForDays();
        System.out.println(IpsForDays);
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> IpsForDays=cc.iPsForDays();
        String maxVisitDay=cc.dayWithMostIPVisits(IpsForDays);
        System.out.println("The day that most Ip visited is: "+maxVisitDay);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer cc=new LogAnalyzer();
        cc.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> IpsForDays=cc.iPsForDays();
        ArrayList<String> mostInDay=cc.iPsWithMostVisitsOnDay(IpsForDays,"Sep 30");
        System.out.println("Ip with the most visit on day: "+mostInDay);
    }
}
