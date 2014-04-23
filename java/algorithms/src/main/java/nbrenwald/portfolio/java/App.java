package nbrenwald.portfolio.java;

/**
 * Hello world!
 *
 */
public class App 
{
	private static void printArray(int[] inArray){
		for(int i = 0; i < inArray.length; i++){
			System.out.print(inArray[i]+" , ");
		}
		System.out.println();
	}
	
    public static void main( String[] args )
    {
    	int[] anArray = {1,2,3,4,5,6,6};
    	printArray(anArray);
    	Sorter.quickSort(anArray, 0, anArray.length-1);
    	printArray(anArray);
    	
    }
}
