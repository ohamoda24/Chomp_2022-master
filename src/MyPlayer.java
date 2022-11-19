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

        for (Board wboard : winningBoards) {
            System.out.println(wboard.boardState[0] + "" + wboard.boardState[1] + "" + wboard.boardState[2]);
        }

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
            if (losingBoards.get(i).boardState[0] == columns[0] && losingBoards.get(i).boardState[1] == columns[1] && losingBoards.get(i).boardState[2] == columns[2] && losingBoards.get(i).boardState[3] == columns[3]) {
                System.out.println("making losing board move");
                myMove = losingBoards.get(i).bestMove;
            }
        }

//       for (int x=0; x<winningBoards.size();x++){
//           if (winningBoards.get(x).boardState[0] == columns[0] && winningBoards.get(x).boardState[1] == columns[1] && winningBoards.get(x).boardState[2] == columns[2]){
//               myMove = winningBoards.get(x).bestMove;
//           }
//       }

        for (Board winningBoard : winningBoards) {
            System.out.println(winningBoard.boardState[0] + " vs " + columns[0]);
            System.out.println(winningBoard.boardState[1] + " vs " + columns[1]);
            System.out.println(winningBoard.boardState[2] + " vs " + columns[2]);
            System.out.println("Move to make: " + winningBoard.bestMove);
            if (winningBoard.boardState[0] == columns[0] && winningBoard.boardState[1] == columns[1] && winningBoard.boardState[2] == columns[2]) {
                System.out.println(winningBoard.bestMove.x+"making winning board move"+winningBoard.bestMove.y);
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
            System.out.println(myMove.x+"  "+myMove.y);
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

        for (int r = 1; r < 4; r++) {
            for (int e = 0; e < 4; e++) {
                for (int g = 0; g < 4; g++) {
                    if (g >= 0 && e <= r && g <= e) {
                        /*int[] board = new int[3];
                        board[0] = r;
                        board[1] = e;
                        board[2] = g;*/
                        int board[] = {r, e, g};


                        if (board[0] == 1 && board[1] == 0 && board[2] == 0) {
                            losingBoards.add(new Board(board, new Point(1,0)));
                        }


//                        System.out.println(board[0] + ", " + board[1] + ", " + board[2]);

                        ResultBoard(r, e, g);
                            checkLosingBoards(board);
//                        winningMove(board, resultingMoves);

                    }
                }
            }
        }
        for (int i = 0; i < losingBoards.size(); i++) {
            System.out.println(Arrays.toString(losingBoards.get(i).boardState));

        }
    }

    //check a result board if it's in the losing board list
    //if the possible board is equal to the losing board than the positive board is the winning board
    public boolean checkLosingBoards(int[] currentBoard) {
        boolean foundWinner = false;


        for (int j = 0; j < losingBoards.size(); j++) {
            if (losingBoards.get(j).boardState[0] == currentBoard[0] && losingBoards.get(j).boardState[1] == currentBoard[1] && losingBoards.get(j).boardState[2] == currentBoard[2]) {
                System.out.println("winna winna chicken dinna");
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

    public Point pickChip(int[] currentBoard, ArrayList<int[]> pBoards, ArrayList<int[]> lBoards) {

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
        Point BestMove = new Point(xcord,ycord);
        return BestMove;

    }


    public void ResultBoard(int r, int e, int g) {

        Point moveToMake = new Point(0,0);

        int[] board = {r, e, g};

        System.out.println("Original board: " + r + " " + e + " " + g);
        boolean checkWinner = false;

        for (int i = g - 1; i >= 0; i--) {

            System.out.println(r + ", " + e + ", " + i + " Move to get there: " + i + 2);

            for (Board loser : losingBoards){
                if (loser.boardState[0] == r && loser.boardState[1] == e && loser.boardState[2] == i){
                    checkWinner = true;
                    moveToMake = new Point(i,2);
                }
            }
        } // right

        for (int i = e - 1; i >= 0; i--) {
            if (g > i) {
                g = i;
            }
            System.out.println(r + ", " + i + ", " + g + " Move to get there: " + i + 1);
            for (Board loser : losingBoards){
                if (loser.boardState[0] == r && loser.boardState[1] == i && loser.boardState[2] == g){
                    checkWinner = true;
                    moveToMake = new Point(i,1);
                }
            }

        } //middle column

        g = board[2];
        for (int i = r - 1; i >= 1; i--) {
            if (e > i) {
                e = i;
            }
            if (g > i) {
                g = i;
            }
            System.out.println(i + ", " + e + ", " + g + " Move to get there: " + i + 1);
            for (Board loser : losingBoards){
                if (loser.boardState[0] == i && loser.boardState[1] == e && loser.boardState[2] == g){
                    checkWinner = true;
                    moveToMake = new Point(i,0);
                }
            }

            //if check winner is true, we did find a losing board. Meaning the board we're at is a winning board.
            //if check winner is false, we did NOT find losing boards
            //

        } //left column


        if (checkWinner == false) {
            System.out.println("HAHAHAHAHAH LOSERRRR");
            losingBoards.add(new Board(board, new Point(0,0)));
            isLosingBoard = true;
        } else {
            winningBoards.add(new Board(board, moveToMake));
        }
        //System.out.println("New board: " + board[0] + ", " + board[1] + ", " + board[2]);
    }

}
