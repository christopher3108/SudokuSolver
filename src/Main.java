import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File sudoku = new File("src/sudoku.test");
        Board one = new Board(sudoku);
        SudokuSolver solver = new SudokuSolver();

        System.out.println(one);
        solver.solve(one);
    }


}