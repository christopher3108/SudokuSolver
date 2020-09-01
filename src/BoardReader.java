import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface BoardReader{

    public int[][] getBoard(File sudoku) throws FileNotFoundException;

}
