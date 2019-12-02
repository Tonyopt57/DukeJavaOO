
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
       
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        
        while(stopIndex != -1){
            
            if((stopIndex - startIndex) % 3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        
        return -1;
    }
    public String findGene(String dna, int Index){
        
        int startIndex = dna.indexOf("ATG", Index);
        
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int minIndex = 0;
        
        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    public void printAllGenes(String dna) {
      int startIndex = 0;
      
      while ( true ) {
          String currentGene = findGene(dna, startIndex);
          if (currentGene.isEmpty()) {
              break;
          }
          System.out.println(currentGene);
          startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public int countGene(String dna){
        int startIndex=0;
        int count=0;
        while (true){
          String currentGene = findGene(dna, startIndex);
          if (currentGene.isEmpty()) {
              break;
          }else{
              count+=1;
              startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
            }
        }return count;
    }
    
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
        System.out.println("Number of Genes confirmed: "+countGene(dna));
    }
    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("ATG");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}