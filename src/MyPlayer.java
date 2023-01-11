import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    public ArrayList<int[]> resultingMoves;
    public ArrayList<Board> losingBoards = new ArrayList<>();
    public boolean isLosingBoard;
    public ArrayList<Board> winningBoards = new ArrayList<>();
    public int xcord;
    public int ycord;

    public MyPlayer() {
        columns = new int[10];

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
        g3();

        // for (Board wboard : winningBoards) {
        //System.out.println(wboard.boardState[0] + "" + wboard.boardState[1] + "" + wboard.boardState[2]);
        //}

        //System.out.print("Losing boards size: " + losingBoards.size());

    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        columns = checkChips(gameBoard);
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */
        Point myMove = new Point(row, column);

        for (int i = 0; i < losingBoards.size(); i++) {
            if (losingBoards.get(i).boardState[0] == columns[0] &&
                    losingBoards.get(i).boardState[1] == columns[1]
                    && losingBoards.get(i).boardState[2] == columns[2]
                    && losingBoards.get(i).boardState[3] == columns[3]
                    && losingBoards.get(i).boardState[4] == columns[4]
                    && losingBoards.get(i).boardState[5] == columns[5]
                    && losingBoards.get(i).boardState[6] == columns[6]
                    && losingBoards.get(i).boardState[7] == columns[7]
                    && losingBoards.get(i).boardState[8] == columns[8]
                    && losingBoards.get(i).boardState[9] == columns[9]
            ) {
                //System.out.println("making losing board move");
                myMove = losingBoards.get(i).bestMove;
            }
        }

//       for (int x=0; x<winningBoards.size();x++){
//           if (winningBoards.get(x).boardState[0] == columns[0] && winningBoards.get(x).boardState[1] == columns[1] && winningBoards.get(x).boardState[2] == columns[2]){
//               myMove = winningBoards.get(x).bestMove;
//           }
//       }

        for (Board winningBoard : winningBoards) {
            //System.out.println("Move to make: " + winningBoard.bestMove);
            if (winningBoard.boardState[0] == columns[0]
                    && winningBoard.boardState[1] == columns[1] && winningBoard.boardState[2] == columns[2]
                    && winningBoard.boardState[3] == columns[3] && winningBoard.boardState[4] == columns[4]
                    && winningBoard.boardState[5] == columns[5] && winningBoard.boardState[6] == columns[6]
                    && winningBoard.boardState[7] == columns[7] && winningBoard.boardState[8] == columns[8]
                    && winningBoard.boardState[9] == columns[9]) {
                //System.out.println(winningBoard.bestMove.x + "making winning board move" + winningBoard.bestMove.y);
                myMove = winningBoard.bestMove;
            }
        }//getting the move from the winning board
        /*
        for (Board winningBoard : winningBoards) {
            System.out.print(winningBoard.boardState[0] + " ,");
            System.out.print(winningBoard.boardState[1] + " ,");
            System.out.println(winningBoard.boardState[2] +" move to make("+winningBoard.bestMove.x+ "  "+winningBoard.bestMove.y);
        }

         */
        row = myMove.x;
        column = myMove.y;
        myMove = new Point(column, row);
        System.out.println("MOOOOOVE "+myMove.x + "  " + myMove.y);
        return myMove;
    }

    public int[] checkChips(Chip[][] gameBoard) {
        int[] cols = new int[10];

        for (int c = 0; c < 10; c++) {
            for (int r = 0; r < 10; r++) {
                if (gameBoard[r][c].isAlive == true) {
                    cols[c] = cols[c] + 1;
                }
            }
        }
        return cols;
    }

    public void g3() {

        for (int a = 1; a <= 10; a++) {
            for (int b = 0; b <= a; b++) {
                for (int c = 0; c <= b; c++) {
                    for (int d = 0; d <= c; d++) {
                        for (int e = 0; e <= d; e++) {
                            for (int f = 0; f <= e; f++) {
                                for (int g = 0; g <= f; g++) {
                                    for (int h = 0; h <= g; h++) {
                                        for (int i = 0; i <= h; i++) {
                                            for (int j = 0; j <= i; j++) {
                                                if (true) {
                        /*int[] board = new int[3];
                        board[0] = r;
                        board[1] = e;
                        board[2] = g;*/
                                                    int board[] = {a, b, c, d, e, f, g, h, i, j};


                                                    if (board[0] == 1 && board[1] == 0 && board[2] == 0) {
                                                        losingBoards.add(new Board(board, new Point(1, 0)));
                                                    }
                                                    ResultBoard(a, b, c, d, e, f, g, h, i, j);
                                                    checkLosingBoards(board);
                                                }


//                        System.out.println(board[0] + ", " + board[1] + ", " + board[2]);


//                        winningMove(board, resultingMoves);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
       /* for (int i = 0; i < losingBoards.size(); i++) {
            System.out.println(Arrays.toString(losingBoards.get(i).boardState));

        }*/
    }

    //check a result board if it's in the losing board list
    //if the possible board is equal to the losing board than the positive board is the winning board
    public boolean checkLosingBoards ( int[] currentBoard){
        boolean foundWinner = false;


        for (int j = 0; j < losingBoards.size(); j++) {
            if (losingBoards.get(j).boardState[0] == currentBoard[0]
                    && losingBoards.get(j).boardState[1] == currentBoard[1]
                    && losingBoards.get(j).boardState[2] == currentBoard[2]
                    && losingBoards.get(j).boardState[3] == currentBoard[3]
                    && losingBoards.get(j).boardState[4] == currentBoard[4]
                    && losingBoards.get(j).boardState[5] == currentBoard[5]
                    && losingBoards.get(j).boardState[6] == currentBoard[6]
                    && losingBoards.get(j).boardState[7] == currentBoard[7]
                    && losingBoards.get(j).boardState[8] == currentBoard[8]
                    && losingBoards.get(j).boardState[9] == currentBoard[9]) {
                //System.out.println("winna winna chicken dinna");
                foundWinner = true;
                break;
            }

        }

        /*for (int[] losingBoard : losingBoards) {
            if (losingBoard[0] == currentBoard[0] && losingBoard[1] == currentBoard[1] && losingBoard[2] == currentBoard[2]) {
                System.out.println("loser");
            }
        }*/
        return foundWinner;
    }

                       /* public Point pickChip ( int[] currentBoard, ArrayList<int[]>pBoards, ArrayList < int[]>lBoards){

                            int difference;
                            for (int i = 0; i < pBoards.size(); i++) {
                                for (int j = 0; j < lBoards.size(); j++) {
                                    if (lBoards.get(j)[0] == pBoards.get(i)[0] && lBoards.get(j)[1] == pBoards.get(j)[1] && lBoards.get(j)[2] == pBoards.get(j)[2]) {
                                        if (lBoards.get(j)[0] < currentBoard[0]) {
                                            difference = currentBoard[0] - lBoards.get(j)[0] - 1;

                                            xcord = 0;
                                            ycord = currentBoard[0] - difference - 1;

                                            System.out.println("[" + xcord + "," + ycord + "]");
                                            break;

                                        }

                                        if (lBoards.get(j)[1] < currentBoard[1]) {
                                            difference = currentBoard[1] - lBoards.get(j)[1] - 1;

                                            xcord = 1;
                                            ycord = currentBoard[1] - difference - 1;

                                            System.out.println("[" + xcord + "," + ycord + "]");
                                            break;

                                        }
                                        if (lBoards.get(j)[2] < currentBoard[2]) {
                                            difference = currentBoard[2] - lBoards.get(j)[2] - 1;

                                            xcord = 2;
                                            ycord = currentBoard[2] - difference - 1;

                                            System.out.println("[" + xcord + "," + ycord + "]");
                                            break;

                                        }

                                    }
                                }
                            }
                            Point BestMove = new Point(xcord, ycord);
                            return BestMove;

                        }*/


    public void ResultBoard ( int a, int b, int c, int d, int e, int f, int g, int h, int i, int j){

        Point moveToMake = new Point(0, 0);

        int[] board = {a, b, c, d, e, f, g, h, i, j};
//        int[] originalBoard = board.clone();

        //System.out.println("Original board: " + a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g + " " + h + " " + i + " " + j);
        boolean checkWinner = false;

        for (int x = j - 1; x >= 0; x--) {


            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e
                        && loser.boardState[5] == f && loser.boardState[6] == g && loser.boardState[7] == h
                        && loser.boardState[8] == i && loser.boardState[9] == x) {
                    checkWinner = true;
                    moveToMake = new Point(9, x);
                    //System.out.println("j loop: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + x + " " + "Move to get there: " + "(" + "9, " + x + ")");
                }
            }
        } // column 10


        for (int x = i - 1; x >= 0; x--) {
            if (j>x) {
                j=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e
                        && loser.boardState[5] == f && loser.boardState[6] == g && loser.boardState[7] == h
                        && loser.boardState[8] == x && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(8, x);
                    //System.out.println("i loop: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + x + ", " + j + " " + "Move to get there: " + "(" + "8, " + x + ")");
                }
            }

        } //column 9

        j = board[9];

        for (int x = h - 1; x >= 0; x--) {
            if (i>x) {
                i=x;
            }
            if (j>x) {
                j=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e && loser.boardState[5] == f
                        && loser.boardState[6] == g && loser.boardState[7] == x && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(7, x);
                    //System.out.println("h loop: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + x + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "7, " + x + ")");
                }
            }

            //if check winner is true, we did find a losing board. Meaning the board we're at is a winning board.
            //if check winner is false, we did NOT find losing boards
            //

        } //column 8

        i = board[8];
        j = board[9];

        for (int x = g - 1; x >= 0; x--) {
            if (h>x) {
                h=x;
            }
            if (i>x) {
                i=x;
            }
            if (j>x) {
                j=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e && loser.boardState[5] == f
                        && loser.boardState[6] == x && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(6, x);
                    //System.out.println("g loop: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + x + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "6, " + x + ")");
                }
            }

        } //column 7

        h = board[7];
        i = board[8];
        j = board[9];

        for (int x = f - 1; x >= 0; x--) {
            if (g>x) {
                g=x;
            }
            if (h>x) {
                h=x;
            }
            if (i>x) {
                i=x;
            }
            if (j>x) {
                j=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e && loser.boardState[5] == x
                        && loser.boardState[6] == g && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(5, x);
                    //System.out.println("f loop: " + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + x + ", " + g + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "5, " + x + ")");
                }
            }

        } //column 6

        g = board[6];
        h = board[7];
        i = board[8];
        j = board[9];

        for (int x = e - 1; x >= 0; x--) {
            if (f>x) {
                f=x;
            }
            if (g>x) {
                g=x;
            }
            if (h>x) {
                h=x;
            }
            if (i>x) {
                i=x;
            }
            if (j>x) {
                j=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == x && loser.boardState[5] == f
                        && loser.boardState[6] == g && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(4, x);
                    //System.out.println("e loop: " + a + ", " + b + ", " + c + ", " + d + ", " + x + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "4, " + x + ")");
                }
            }

        } //column 5

        f = board[5];
        g = board[6];
        h = board[7];
        i = board[8];
        j = board[9];

        for (int x = d - 1; x >= 0; x--) {
            if (j>x) {
                j=x;
            }
            if (i>x) {
                i=x;
            }
            if (h>x) {
                h=x;
            }
            if (g>x) {
                g=x;
            }
            if (f>x) {
                f=x;
            }
            if (e>x) {
                e=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == x && loser.boardState[4] == e && loser.boardState[5] == f
                        && loser.boardState[6] == g && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(3, x);
                    //System.out.println("d loop: " + a + ", " + b + ", " + c + ", " + x + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "3, " + x + ")");
                }
            }

        } //column 4

        e = board[4];
        f = board[5];
        g = board[6];
        h = board[7];
        i = board[8];
        j = board[9];

//        board = originalBoard.clone();

        for (int x = c - 1; x >= 0; x--) {
            if (j>x) {
                j=x;
            }
            if (i>x) {
                i=x;
            }
            if (h>x) {
                h=x;
            }
            if (g>x) {
                g=x;
            }
            if (f>x) {
                f=x;
            }
            if (e>x) {
                e=x;
            }
            if (d>x) {
                d=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == b && loser.boardState[2] == x
                        && loser.boardState[3] == d && loser.boardState[4] == e && loser.boardState[5] == f
                        && loser.boardState[6] == g && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(2, x);
                    //System.out.println("c loop: " + a + ", " + b + ", " + x + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "2, " + x + ")");
                }
            }

        } //column 3

        d = board[3];
        e = board[4];
        f = board[5];
        g = board[6];
        h = board[7];
        i = board[8];
        j = board[9];

//        board = originalBoard.clone();

        for (int x = b - 1; x >= 0; x--) {

            if (c>x) {
                c=x;
            }
            if (d>x) {
                d=x;
            }
            if (e>x) {
                e=x;
            }
            if (j>x) {
                j=x;
            }
            if (i>x) {
                i=x;
            }
            if (h>x) {
                h=x;
            }
            if (g>x) {
                g=x;
            }
            if (f>x) {
                f=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == a && loser.boardState[1] == x && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e && loser.boardState[5] == f
                        && loser.boardState[6] == g && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(1, x);
                    //System.out.println("b loop: " + a + ", " + x + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "1, " + x + ")");

                }
            }

        } //column 2

        c = board[2];
        d = board[3];
        e = board[4];
        f = board[5];
        g = board[6];
        h = board[7];
        i = board[8];
        j = board[9];

//        board = originalBoard.clone();

        for (int x = a - 1; x >= 1; x--) {
            if (j>x) {
                j=x;
            }
            if (i>x) {
                i=x;
            }
            if (h>x) {
                h=x;
            }
            if (g>x) {
                g=x;
            }
            if (f>x) {
                f=x;
            }
            if (e>x) {
                e=x;
            }
            if (d>x) {
                d=x;
            }
            if (c>x) {
                c=x;
            }
            if (b>x) {
                b=x;
            }
            if (a>x) {
                a=x;
            }

            for (Board loser : losingBoards) {
                if (loser.boardState[0] == x && loser.boardState[1] == b && loser.boardState[2] == c
                        && loser.boardState[3] == d && loser.boardState[4] == e && loser.boardState[5] == f
                        && loser.boardState[6] == g && loser.boardState[7] == h && loser.boardState[8] == i
                        && loser.boardState[9] == j) {
                    checkWinner = true;
                    moveToMake = new Point(0, x);
                    //System.out.println("a loop: " + x + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + " " + "Move to get there: " + "(" + "1, " + x + ")");
                }
            }
//            b = board[1];
//            c = board[2];
//            d = board[3];
//            e = board[4];
//            f = board[5];
//            g = board[6];
//            h = board[7];
//            i = board[8];
//            j = board[9];
        } //column 1





        if (checkWinner == false) {
            //System.out.println("HAHAHAHAHAH LOSERRRR");
            losingBoards.add(new Board(board, new Point(0, 0)));
            isLosingBoard = true;
        } else {
            winningBoards.add(new Board(board, moveToMake));
        }
        //System.out.println("New board: " + board[0] + ", " + board[1] + ", " + board[2]);
    }

}
