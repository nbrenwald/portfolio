package nbrenwald.portfolio.java;

public class DynamicProgramming {

  public static int knapsack(int capacity, int[] sizes, int[] values) {

    int[][] dp = new int[sizes.length][capacity+1];
    int[][] dpRows = new int[sizes.length][capacity+1];
    int[][] dpCols = new int[sizes.length][capacity+1];

    dp[0][sizes[0]] = values[0];
    

    int maxValue = 0;
    int maxi = 0;
    int maxj = 0;

    for (int i = 1; i < dp.length; i++) {
      for (int j = 0; j <= capacity; j++) {
        dp[i][j] = Math.max(dp[i-1][j], (sizes[i] <= j) ? dp[i-1][j-sizes[i]] + values[i] : 0 );
        if ( dp[i-1][j] < ( (sizes[i] <= j) ? dp[i-1][j-sizes[i]] + values[i] : 0 ) ) {
          dpCols[i][j] = j-sizes[i];
        } else {
          dpCols[i][j] = j;
        }
        if( maxValue < dp[i][j] ) {
          maxValue = dp[i][j];
          maxi = i;
          maxj = j;
        }
      }
    }
    
   int i = maxi;
   int j = maxj;
   while (i >= 0) {
     int parentColumn = dpCols[i][j];
     if(parentColumn != j) {
       System.out.println("included "+sizes[i]);
       j = parentColumn;
     }
     i--;
   }
   return maxValue; 
  }
  
  public static int balancedPartition(int[] a) {
    // O(n2K)
    
    // I need to figure out the largest value in a, and the sum
    int max = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = 0; i < a.length; i++) {
      sum += a[i];
      max = Math.max(max, a[i]);
    }

    int[][] dp = new int[a.length][sum];


    // set the base case
    dp[0][a[0]] = 1;
    for (int i = 0; i < dp.length; i++){
      dp[i][0] = 1;
    }

    int s = sum / 2;
    int min = Integer.MAX_VALUE;

    for (int i = 1; i < dp.length; i++){
      for (int j = 0; j < dp[i].length; j++) {
        dp[i][j] = Math.max(dp[i-1][j], (a[i] <= j) ? dp[i-1][j-a[i]] : 0);
        if (dp[i][j] == 1) {
          min = Math.min(min, Math.abs(sum-(2*j)));
        }
      }
    }

    return min;
  }

  public static int maxValue(int[] a) {
    int[][] dp = new int[a.length][a.length];

    for (int i = 0; i < a.length; i++) {
      dp[i][i] = a[i];
    }

    for (int i = 0; i + 1 < a.length; i++) {
      dp[i][i+1] = Math.max(a[i], a[i+1]);
    }


    for (int len = 3; len <= a.length; len++){
      for (int i = 0; i + len -1 < a.length; i++){
        int j = i + len - 1;
        dp[i][j] = Math.max( Math.min( dp[i+2][j], dp[i+1][j-1] ) + a[i], Math.min( dp[i][j-2], dp[i+1][j-1]) + a[j] );
      }
    }
    
    return dp[0][a.length-1];
  }
  
  public static void main(String[] args) {
    int[] sizes = {3,4,5};
    int[] values = {100,200,400};
    int capacity = 14;
    System.out.println(knapsack(capacity, sizes, values));
    
    int[] coins = {100,500,40,99,43,22};
    System.out.println(maxValue(coins));
  }
}
