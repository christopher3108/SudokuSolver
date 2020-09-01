import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Board {

    private int[][] sudokuBoard;

    Board(File sudoku) throws FileNotFoundException {
        BoardReader sudokuType = BoardReaderFactory.getBoardType(sudoku);
        sudokuBoard = sudokuType.getBoard(sudoku);
    }

    Board(int[][] initial) {
        sudokuBoard = initial;
    }

    private Board(int[][] initial, int row, int column, int number) {
        sudokuBoard = new int[9][9];
        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial[i].length; j++) {
                sudokuBoard[i][j] = initial[i][j];
            }
        }
        sudokuBoard[row][column] = number;
    }

    public void set(int row, int column, int number) {
        sudokuBoard[row][column] = number;
    }

    int[][] getSudokuBoard() {
        return sudokuBoard;
    }

    int getNumBlanks() {
        int numBlanks = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[i][j] == 0) {
                    numBlanks++;
                }
            }
        }
        return numBlanks;
    }

    boolean isSolved() {
        return (isValid() && isInConstraint());
    }

    boolean isValid() {
        return (isValidRow() && isValidColumn() && isValidGrid());
    }

    private boolean isInConstraint() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean isValidRow() {
        Set<Integer> boardCheck = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[i][j] != 0) {
                    if (!boardCheck.add(sudokuBoard[i][j])) {
                        return false;
                    }
                    boardCheck.add(sudokuBoard[i][j]);
                }
            }
            boardCheck.clear();
        }
        return true;
    }

    private boolean isValidColumn() {
        Set<Integer> boardCheck = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[j][i] != 0) {
                    if (!boardCheck.add(sudokuBoard[j][i])) {
                        return false;
                    }
                    boardCheck.add(sudokuBoard[j][i]);
                }
            }
            boardCheck.clear();
        }
        return true;
    }

    private boolean isValidGrid() {
        Set<Integer> boardCheck = new HashSet<>();
        for (int i = 0; i < 7; i += 3) {
            for (int j = 0; j < 7; j += 3) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (sudokuBoard[i + k][j + l] != 0) {
                            if (!boardCheck.add(sudokuBoard[i + k][j + l])) {
                                return false;
                            }
                            boardCheck.add(sudokuBoard[i + k][j + l]);
                        }
                    }
                }
                boardCheck.clear();
            }
        }
        return true;
    }

    ArrayList<Board> findNeighbor(int row, int column) {
        ArrayList<Board> neighborList = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            Board neighbor = new Board(this.sudokuBoard, row, column, i);
            if (neighbor.isValid()) {
                neighborList.add(neighbor);
            }
        }
        return neighborList;
    }

    public String toString() {
        StringBuilder sudokuString = new StringBuilder();
        int line = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard[line][j] == 0) {
                    sudokuString.append(".");
                } else {
                    sudokuString.append(sudokuBoard[line][j]);
                }
            }
            sudokuString.append("\n");
            line++;
        }
        return sudokuString.toString();
    }
}

