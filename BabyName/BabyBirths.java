/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(){
        FileResource fr=new FileResource();
        int male=0;
        int female=0;
        int malenamenumber=0;
        int femalenamenumber=0;
        for (CSVRecord rec:fr.getCSVParser(false)){
            String sex=rec.get(1);
            if (sex.equals("F")){
                female+=Integer.parseInt(rec.get(2));
                femalenamenumber+=1;
            }
            else{
                male+=Integer.parseInt(rec.get(2));
                malenamenumber+=1;
            }
        }
        System.out.println("Number of Female: "+female);
        System.out.println("How many different names for Female: "+femalenamenumber);
        System.out.println("Number of Male: "+male);
        System.out.println("How many different names for Male: "+malenamenumber);
        System.out.println("Total Number of Babies: "+(male+female));
        System.out.println("Total number of different names: "+(malenamenumber+femalenamenumber));
       }
       
    public int getRank(int year,String name,String gender){
        FileResource fr=new FileResource("data/yob"+year+"short.csv");
        StorageResource store=new StorageResource();
        int rank=0;
        for (CSVRecord rec:fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rank+=1;
                store.add(rec.get(0));
                if (name.equals(rec.get(0))){
                    break;
                }
            }
        }
        if (store.contains(name)){
            rank+=0;
        }
        else{
            rank=-1;
        }
        return rank;
    }
    
    public void testGetRank(){
        int MasonrankM=getRank(2012,"Mason","M");
        int MasonrankF=getRank(2012,"Mason","F");
        System.out.println(MasonrankM);
        System.out.println(MasonrankF);
    }
    
    public String getName(int year,int rank,String gender){
        FileResource fr=new FileResource("data/yob"+year+"short.csv");
        int number=0;
        String name="";
        for (CSVRecord rec:fr.getCSVParser(false)){
           if (rec.get(1).equals(gender)){
               number+=1;
           }
           if (number==rank){
               name=rec.get(0);
           }
        }
        if (number==0){
            name="NO NAME";
        }
        return name;
    }
    
    public void testGetName(){
        String name1=getName(2012,2,"M");
        System.out.println(name1);
    }
    
    public void whatIsNameInYear(String name,int year,int newYear,String gender){
        int rank=getRank(year,name,gender);
        String newName=getName(newYear,rank,gender);
        if (gender.equals("F")){
            System.out.println(name+" born in "+ year+" would be "+newName+" if she was born in "+newYear);
        }
        else{
            System.out.println(name+" born in "+ year+" would be "+newName+" if he was born in "+newYear);
        }
    }
    
    public void testwhatIsNameInYear(){
        whatIsNameInYear("Isabella",2012,2014,"F");
    }
    
    public int yearOfHighestRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        File Temp=null;
        int currenthigh=0;
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            int rank=0;
            int number=0;
            for (CSVRecord rec:fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    rank+=1;
                    if (name.equals(rec.get(0))){
                        number+=1;
                        if (currenthigh==0 || currenthigh>rank){
                            currenthigh=rank;
                            Temp=f;
                        }
                        break;
                    }                    
                }
            }
            if (number ==0){
                currenthigh=-1;
            }
        }
        if (currenthigh==-1){
            return -1;
        }
        else{
            return Integer.parseInt(Temp.getName().substring(3,7));
        }
    }
    
    public void testYearOfHighestRank(){
        int highestrankyear=yearOfHighestRank("Mason","M");
        System.out.println("His Highest ranking was in "+highestrankyear);
    }
    
    public double getAverageRank(String name,String gender){
        DirectoryResource dr=new DirectoryResource();
        int currenthigh=0;
        double count=0;
        double total=0;
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            count+=1;
            int rank=0;
            int number=0;
            for (CSVRecord rec:fr.getCSVParser(false)){
                if (rec.get(1).equals(gender)){
                    rank+=1;
                    if (name.equals(rec.get(0))){
                        number+=1;
                        if (currenthigh==0 || currenthigh>rank){
                            currenthigh=rank;
                        }
                        break;
                    }                    
                }
            }
            total+=rank;
        }
        return total/count;
    }
    
    public void testgetAverageRank(){
        double average=getAverageRank("Mason","M");
        System.out.println("The average ranking is "+average);
    }
    
    public int getTotalBirthsRankedHigher(int year,String name,String gender){
        FileResource fr=new FileResource("data/yob"+year+"short.csv");
        int rank=0;
        int total=0;
        for (CSVRecord rec:fr.getCSVParser(false)){
            if (rec.get(1).equals(gender)){
                rank+=1;
                
                if (name.equals(rec.get(0))){
                    break;
                }
                else{
                    total+=Integer.parseInt(rec.get(2));
                }
            }
        }
        return total;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        int NumberOfRankedHigher=getTotalBirthsRankedHigher(2012,"Ethan","M");
        System.out.println(NumberOfRankedHigher);
    }
}
