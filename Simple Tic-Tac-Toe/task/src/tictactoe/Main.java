package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char[][] matrices = new char[5][9];

        createGameLayout(matrices);
        printMatrices(matrices);
        while (true) {
            boolean repeat = false;
            while (!repeat) {
                repeat = analyzeCoordinates(matrices, 'X');
            }
            printMatrices(matrices);
            if (isStateWinOrDraw(matrices)) {
                System.exit(0);
            }

            repeat = false;
            while (!repeat) {
                repeat = analyzeCoordinates(matrices, 'O');
            }
            printMatrices(matrices);
            if (isStateWinOrDraw(matrices)) {
                System.exit(0);
            }
        }
    }

    public static boolean analyzeCoordinates(char[][] matrices, char xOrO) {
        boolean isInputCorrect = false;
        int n = 0;
        int m = 0;
        while (!isInputCorrect) {
            System.out.print("Enter the coordinates:");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            if (inputArr[0].matches("^[0-9]")) {
                n = Integer.parseInt(inputArr[0]);
                if (inputArr[1].matches("^[0-9]")) {
                    m = Integer.parseInt(inputArr[1]);
                    isInputCorrect = true;
                }
            } else {
                System.out.println("You should enter numbers!");
            }
        }

        isInputCorrect = false;
        while (!isInputCorrect) {

            if (!isCoordinateFromZerotothree(n, m)) {
                System.out.println("Coordinates should be from 1 to 3!");
                break;
            }
            if (isCellOccupied(n, m, matrices)) {
                System.out.println("This cell is occupied! Choose another one!");
                break;
            }
            if (!isCellOccupied(n, m, matrices) && isCoordinateFromZerotothree(n, m)) {
                matrices[n][m*2] = xOrO;
                isInputCorrect = true;
            }
        }
        return isInputCorrect;
    }

    public static boolean isCoordinateFromZerotothree(int n, int m) {
        return (1 <= n && n <= 3 && 1 <= m && m <= 3);
    }

    public static boolean isCellOccupied(int n, int m, char[][] matrices) {
        return matrices[n][m * 2] != '_';
    }

    public static void createGameLayout(char[][] matrices) {
        for (int i = 0; i < matrices.length; i++) {
            for (int j = 0; j < matrices[i].length; j++) {
                matrices[i][j] = '_';
                if (j % 2 != 0) {
                    matrices[i][j] = ' ';
                }
                if (i == 0 || i == 4) {
                    matrices[i][j] = '-';
                }
                if (i >= 1 && i <= 3) {
                    matrices[i][0] = '|';
                    matrices[i][8] = '|';
                }
            }
        }
    }

    public static void printMatrices(char[][] matrices) {
        for (char[] matrix : matrices
        ) {
            for (char c : matrix
            ) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static boolean isStateWinOrDraw(char [][] matrices) {
        boolean isWinOrDraw = false;
       if (isWinsState(matrices)[0]) {
            if (isWinsState(matrices)[1]) {
                System.out.println("X wins");
            } else {
                System.out.println("O wins");
            }
           isWinOrDraw = true;
       } else if (isDrawState(matrices)) {
           System.out.println("Draw");
           isWinOrDraw = true;
       }
        return isWinOrDraw;
    }

    public static boolean[] isWinsState(char[][] matrices) {
        boolean[] result = new boolean[2];
        for (int i = 1; i < 4; i++) {
            if (matrices[i][2] != '_' && matrices[i][2] == matrices[i][4] && matrices[i][2] == matrices[i][6]) {
                result[0] = true;
                if (matrices[i][2] == 'X') {
                    result[1] = true;
                }
                break;
            }
        }
        for (int i = 2; i < 7; i += 2) {
            if (matrices[1][i] != '_' && matrices[1][i] == matrices[2][i] && matrices[3][i] == matrices[2][i]) {
                result[0] = true;
                if (matrices[1][i] == 'X') {
                    result[1] = true;
                }
                break;
            }
        }
        if (matrices[1][2] != '_' && matrices[1][2] == matrices[2][4] && matrices[2][4] == matrices[3][6]) {
            result[0] = true;
            if (matrices[1][2] == 'X') {
                result[1] = true;
            }
        }
        if (matrices[1][6] != '_' && matrices[1][6] == matrices[2][4] && matrices[2][4] == matrices[3][2]) {
            result[0] = true;
            if (matrices[1][6] == 'X') {
                result[1] = true;
            }
        }
        return result;
    }

    public static boolean isDrawState(char[][] matrices) {
        int xHits = 0;
        int oHits = 0;
        boolean isDraw = false;
        for (int i = 1; i < 4; i++) {
            for (int j = 2; j < 7; j += 2) {
                if (matrices[i][j] == 'X') {
                    xHits++;
                }
                if (matrices[i][j] == 'O') {
                    oHits++;
                }
            }
        }
        if (xHits == 5 || oHits == 5) {
            isDraw = true;
        }
        return isDraw;
    }

}
