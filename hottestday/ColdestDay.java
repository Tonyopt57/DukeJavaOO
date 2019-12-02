
/**
 * 在这里给出对类 ColdestDay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class ColdestDay {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestSoFar=null;
        for (CSVRecord currentRow: parser){
            if (coldestSoFar==null){
                coldestSoFar=currentRow;
            }
            else{
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp<coldestTemp){
                    coldestSoFar=currentRow;
                }
            }
        }
        return coldestSoFar;
    }
    
    public String fileWithColdestTemperature(){
        CSVRecord coldestSoFar=null;
        File Temp=null;
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
           FileResource fr=new FileResource(f);
           CSVRecord currentRow=coldestHourInFile(fr.getCSVParser());
           if (coldestSoFar==null){
                coldestSoFar=currentRow;
           }
           else{
                double currentTemp=Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp=Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp==-9999){continue;}
                if (currentTemp<coldestTemp){
                    coldestSoFar=currentRow;
                    
                    Temp=f;
                }
           }
        }
        return Temp.getName();
    }
    
    public void testFileWithColdestTemperature(){
        String fname=fileWithColdestTemperature();
        System.out.println("Coldest day was in file "+fname);
        FileResource fr=new FileResource("nc_weather/2013/"+fname);
        CSVRecord coldest=coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day is "+coldest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        for (CSVRecord record:fr.getCSVParser()){
            System.out.println(record.get("DateUTC")+" "+record.get("TemperatureF"));
        }
    }
    
    public void testColdestHourInFile(){
        FileResource fr=new FileResource();
        CSVRecord coldest=coldestHourInFile(fr.getCSVParser());
        System.out.println("The coldest Temperature is: "+coldest.get("TemperatureF")
        +" at "+coldest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord MoistSoFar=null;
        for (CSVRecord currentRow: parser){
            if (currentRow.get("Humidity").equals("N/A")){continue;}
            if (MoistSoFar==null){
                MoistSoFar=currentRow;
            }
            else{
                double currentTemp=Double.parseDouble(currentRow.get("Humidity"));
                double MoistTemp=Double.parseDouble(MoistSoFar.get("Humidity"));
                if (currentTemp<MoistTemp){
                    MoistSoFar=currentRow;
                }
            }
        }
        return MoistSoFar;
    }
    
    public void testlowestHumidityInFile(){
        FileResource fr=new FileResource();
        CSVRecord lessMoist=lowestHumidityInFile(fr.getCSVParser());
        System.out.println("The lowest Humidity is: "+lessMoist.get("Humidity")
        +" at "+lessMoist.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestRow=null;
        DirectoryResource dr=new DirectoryResource();
        for (File f:dr.selectedFiles()){
            FileResource fr=new FileResource(f);
            CSVRecord currentRow=lowestHumidityInFile(fr.getCSVParser());
            if (lowestRow==null){
                lowestRow=currentRow;
            }
            else{
                double currentHu=Double.parseDouble(currentRow.get("Humidity"));
                double lowestHu=Double.parseDouble(lowestRow.get("Humidity"));
                if (currentHu<lowestHu){
                    lowestRow=currentRow;
                }
            }
        }
        return lowestRow;
    }
    
    public void testLowerstHumidityInManyFiles(){
        CSVRecord lowesthumidity=lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was "+lowesthumidity.get("Humidity")+
        " at "+lowesthumidity.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double currenttotal=0;
        double count=0;
        for (CSVRecord currentRow:parser){
            count+=1;
            double currentvalue=Double.parseDouble(currentRow.get("TemperatureF"));
            currenttotal+=currentvalue;
        }
        return currenttotal/count;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr=new FileResource();
        double average=averageTemperatureInFile(fr.getCSVParser());
        System.out.println("The average Temperature is: "+ average);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double currenttotal=0;
        double count=0;
        double average=0;
        for (CSVRecord currentRow:parser){
            double currentHu=Double.parseDouble(currentRow.get("Humidity"));
            if (currentHu>=value){
                count+=1;
                double currentvalue=Double.parseDouble(currentRow.get("TemperatureF"));
                currenttotal+=currentvalue;
                average=currenttotal/count;
            }
        }
        return average;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr=new FileResource();
        double average=averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if (average==0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is "+average);
        }
    }
}
