package nbrenwald.portfolio.java;
import java.util.Arrays;

public class Backtracking{

public static int countPermutations(int[][] grid) {
  // for each cell that is 0
  int i = 0;
  int j = 0;
  while (i < 10) {

    if (grid[i][j] == 0) {
      int sum = 0;
      for(int n = 1; n <= 10; n++) {
        grid[i][j] = n;
        sum += countPermutations(grid);
      }
      return sum;
    }

    i = (i+1)/9;
    j = (j+1)%9;
  }
  
  
  return 1;
}

public static boolean isSolved(int[][] grid) {
  for (int[] row : grid) {
    for (int cell : row) {
      if (cell == 0) {
        return false;
      }
    }
  }
  return true;
}

public static boolean isValidMove(int[][] grid, int row, int col, int n) {
  // check row
  for (int i = 0; i < grid[row].length; i++) {
    if (grid[row][i] == n && i != col) {
      return false;
    }
  }

  // check column
  for (int i = 0; i < grid.length; i++) {
    if (grid[i][col] == n && i != row) {
      return false;
    }
  }

  // check in the same square
  // use mod to calculate an offset
  int rowOffset = row / 3;
  int colOffset = col / 3;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if ((grid [i + (3*rowOffset)][j + (3*colOffset)] == n) && (i + (3*rowOffset) != row) && (j + (3*colOffset) != col)) {
        return false;
      }
    }
  }

  return true;
}

public static boolean solveSudoku(int[][] grid) {
  if (grid == null) throw new NullPointerException("grid is null");
  
  // Backtracking
  // first check if it is solved. If yes, then we can just return
  if (isSolved(grid)) {
    return true;
  }

  for (int i = 0; i < grid.length ; i++) {
    for (int j = 0; j < grid[i].length; j++) {
      if(grid[i][j] == 0) {
        // try and figure out next valid move
        for (int n = 1; n <= 9; n++) {
          if(isValidMove(grid, i, j, n)){
            grid[i][j] = n;
            if (solveSudoku(grid)) {
              return true;
            }
            else {
              grid[i][j] = 0;
            }
          }
        }
        return false;
      }
    }
  }
 return false;
}

public static void main(String[] args) {
  int[][] grid = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                      {5, 2, 0, 0, 0, 0, 0, 0, 0},
                      {0, 8, 7, 0, 0, 0, 0, 3, 1},
                      {0, 0, 3, 0, 1, 0, 0, 8, 0},
                      {9, 0, 0, 8, 6, 3, 0, 0, 5},
                      {0, 5, 0, 0, 9, 0, 6, 0, 0},
                      {1, 3, 0, 0, 0, 0, 2, 5, 0},
                      {0, 0, 0, 0, 0, 0, 0, 7, 4},
                      {0, 0, 5, 2, 0, 6, 3, 0, 0}};

  solveSudoku(grid);

  for (int[] row : grid) {
    System.out.println(Arrays.toString(row));
  }
  System.out.println(countPermutations(grid));
}

}