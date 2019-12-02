
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {

    
    public String countryinfo(CSVParser parser, String country){
        String countriesinfo="Not Found";
        for (CSVRecord record:parser){
            String countries=record.get("Country");
            if (countries.contains(country)){
                String export=record.get("Exports");
                String value=record.get("Value (dollars)");
                countriesinfo=country+": "+export+": "+value;
            }
        }return countriesinfo;
        
    }
    

   
    public void listExportersTwoProducts(CSVParser parser,String exportItem1, 
    String exportItem2){
        for (CSVRecord record:parser){
            String export=record.get("Exports");
            if (export.contains(exportItem1)){
                if (export.contains(exportItem2)){
                    String country=record.get("Country");
                    System.out.println(country);
                }
            }
         }
    }
    
    public int numberOfExporters(CSVParser parser,String exportItem){
        int count=0;
        for (CSVRecord record:parser){
            String export=record.get("Exports");
            if (export.contains(exportItem)){
                count+=1;
            }
        }return count;
    }
    
    public void bigExporter(CSVParser parser,String amount){
        String exportvalue="Value (dollars)";
        for (CSVRecord record:parser){
            String value=record.get(exportvalue);
            if (value.length()>amount.length()){
                String country=record.get("Country");
                System.out.println(country+" "+value);
            }
        }
    }


    public void tester(){
        FileResource fr=new FileResource();
        CSVParser parser=fr.getCSVParser();
        //System.out.println(countryinfo(parser,"Nauru"));
        //System.out.println(countryinfo(parser,"China"));
        //listExportersTwoProducts(parser,"cotton","flowers");
        //System.out.println(numberOfExporters(parser,"cocoa"));
        bigExporter(parser,"$999,999,999,999");
    }

}
