package nbrenwald.portfolio.java;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args )
    {
    	/*int[] anArray = {10,9,8,7,6,5,4,3,2,1};
    	Sorter.quickSort(anArray,0,0);
    	Sorter.printArray(anArray);
    	*/
    	int[] anArray = {-10,-5,1,-2,100,-50,55,-3};
        System.out.println(Finder.findMaxOfMaxSubArray(anArray));
        Sorter.printArray(Finder.findMaxSubArray(anArray));
        

        
        
    	
    }
    
    
}
