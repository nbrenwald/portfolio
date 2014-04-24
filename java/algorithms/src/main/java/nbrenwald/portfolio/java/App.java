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
    	int[] anArray = {1,0,4};
    	printArray(anArray);
    	Sorter.countingSort(anArray);
    	printArray(anArray);
    	
    }
}
