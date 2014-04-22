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
    	int[] anArray = {9,1,8,11,7,3,6,4,5};
    	//int[] anArray = {1,3,5,4,6,7,8,9,11};
    	
    	//int[] anArray = {4,5,3,2};
    	int[] tempArray=new int[anArray.length];
    	printArray(anArray);
    	Sorter.quickSort(anArray, tempArray, 0, anArray.length);
    	printArray(anArray);
    }
}
