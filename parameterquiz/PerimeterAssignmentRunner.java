import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public double getNumPoints (Shape s) {
        double totalPoint=0;
        for (Point currPt : s.getPoints()){
            totalPoint+=1;
        }
        return totalPoint;
    }


    public double getAverage(Shape s){
        double len=getPerimeter(s);
        double num=getNumPoints(s);
        return len/num;
    }
    
    public double getLargestSide(Shape s){
        double maxside=0;
        Point prevPt = s.getLastPoint();
        for (Point currPt: s.getPoints()){
            double currDist=prevPt.distance(currPt);
            if (maxside==0 || maxside < currDist){
                maxside=currDist;
            }

        }
        return maxside;
    }

    public double getLargestX(Shape s){
        double maxX=0;
        for (Point currPt: s.getPoints()){
            double valuex=currPt.getX();
            if (maxX==0 || maxX<valuex){
                maxX=valuex;
    }
        }
        return maxX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr=new DirectoryResource();
        double largestall=0;
        //FileResource largestFile=null;
        for (File f:dr.selectedFiles()){
            FileResource file=new FileResource(f);
            Shape shape=new Shape(file);
            double perim=getPerimeter(shape);
            if (perim>largestall){
                largestall=perim;
            }
        }
        return largestall;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
           // replace this code
        DirectoryResource dr=new DirectoryResource();
        double largestall=0;
        File temp = null; 
        for (File f:dr.selectedFiles()){
            FileResource file=new FileResource(f);
            Shape shape=new Shape(file);
            double perim=getPerimeter(shape);
            if (perim>largestall){
                largestall=perim;
                temp=f;
            }
        }
        return temp.getName();
    }

    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPer=getLargestPerimeterMultipleFiles();
        System.out.println("The largest parimeter in all the file: "+ largestPer);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestfile=getFileWithLargestPerimeter();
        System.out.println("The file name for the largest parimeter is: "+largestfile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        double Num=getNumPoints(s);
        System.out.println("Number of Point:"+Num);
        double Avg=getAverage(s);
        System.out.println("Average side length: "+Avg);
        double maximum=getLargestSide(s);
        System.out.println("The largest side is: "+maximum);
        double maximumx=getLargestX(s);
        System.out.println("The largest x is: "+maximumx);
        testPerimeterMultipleFiles();
        testFileWithLargestPerimeter();
    }
    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
