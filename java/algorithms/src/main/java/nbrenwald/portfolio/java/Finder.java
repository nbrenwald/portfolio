package nbrenwald.portfolio.java;

public class Finder {
  public static int findMaxOfMaxSubArray(int[] inArray){
    // Algorithm runs in O(n) time;
    // returns the max value found
    if(inArray.length >0){
    int[] tmp = findMaxSubArray(inArray);
    int max =0;
    for(int i : tmp){
      max += i;
    }
    return max;
    }
    return 0;
  }
  
  public static int[] findMaxSubArray(int[] inArray){
    if(inArray.length > 0){
      int maxEndingHere = inArray[0];
      int maxSoFar = inArray[0];
      int maxSoFarStartIndex=0;
      int maxSoFarEndIndex=0;
      int maxEndingHereStartIndex=0;
      int maxEndingHereEndIndex=0;
      
      for(int i = 1 ; i < inArray.length; i++){
        if(inArray[i] >= maxEndingHere+inArray[i]){
          maxEndingHere = inArray[i];
          maxEndingHereStartIndex=i;
          maxEndingHereEndIndex=i;
          
        }
        else{
          maxEndingHere = maxEndingHere + inArray[i];
          maxEndingHereEndIndex ++;
        }
        
        if(maxEndingHere >= maxSoFar){
          maxSoFar = maxEndingHere;
          maxSoFarStartIndex=maxEndingHereStartIndex;
          maxSoFarEndIndex=maxEndingHereEndIndex;
          
        }
      }
      
      int[] subArray = new int[maxSoFarEndIndex-maxSoFarStartIndex+1];
      for (int i = maxSoFarStartIndex; i <= maxSoFarEndIndex; i++){
        subArray[i-maxSoFarStartIndex]=inArray[i];
      }
      return subArray;
    }
    return null;
  }

}
