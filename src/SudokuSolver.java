import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

class SudokuSolver {


    // I chose to place this in a new sudokusolver class because it looks cleaner grouping the get neighbors with the solve
    // also, the only time that getNeighbors is going to be called is when you solve the board so it is better
    // rather than putting this in a board class.
    private ArrayList<Board> getNeighbors(Board sudoku) {
        int[][] sudokuBoard = sudoku.getSudokuBoard();
        ArrayList<Board> neighbors = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j <9; j++){
                if (sudokuBoard[i][j] == 0) {
                    ArrayList<Board> neighbor = sudoku.findNeighbor(i, j);
                    neighbors.addAll(neighbor);
                }
            }
        }
        return neighbors;
    }

    // I chose to return out the board and also prints it to the screen, because if we chose to solve the puzzle, we might
    // not want to show it but to just store in in a variable and later on it could be use in a function
    // lets say, to compare it with a board that might also be a solution, or we can make a program where the users
    // can type in number and check whether it is correct or not, if we only prints out the board, it would not be
    // this versatile.
    Board solve(Board sudoku) {
        Board solved = new Board(sudoku.getSudokuBoard());
        if (solved.isSolved()) {
            System.out.println(solved);
            return solved;
        }
        List<Board> neighbors = getNeighbors(solved);

        System.out.println("Finding neighbors. Num Blanks: " + solved.getNumBlanks());

        for (Board validNeigbor : neighbors) {
            Board result = solve(validNeigbor);
            if (result != null) {return result;}
        }
        return null;
    }



}