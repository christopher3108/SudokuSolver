import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BoardReaderSDK implements BoardReader {

    @Override
    public int[][] getBoard(File sudoku) throws FileNotFoundException {
        int[][] sudokuBoard = new int [9][9];
        Scanner input = new Scanner(sudoku);
        int row = 0;
        while (input.hasNextLine()) {
            String line = input.nextLine();
            for (int i = 0; i < 9; i++) {
                if (line.charAt(i) == '.') {
                    sudokuBoard[row][i] = 0;
                }
                else {
                    sudokuBoard[row][i] = line.charAt(i) - '0' ;
                }
            }
            row++;
        }
        return sudokuBoard;
    }

}
