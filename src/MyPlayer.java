import java.awt.*;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;

    public MyPlayer() {
        columns = new int[10];

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         */
        g3();
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
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
        return myMove;
    }

    public void g3() {
        for (int r = 1; r < 4; r++) {
            for (int e = 0; e < 4; e++) {
                for (int g = 0; g < 4; g++) {
                    if (g >= 0 && e <= r && g <= e) {
                        int[] board = new int[3];
                        board[0] = r;
                        board[1] = e;
                        board[2] = g;

//                        System.out.println(board[0] + ", " + board[1] + ", " + board[2]);

                        ResultBoard(r, e, g);

                    }
                }
            }
        }
    }

    //Chomp these balls
    public void ResultBoard(int r, int e, int g) {

        int[] board = new int[3];
        board[0] = r;
        board[1] = e;
        board[2] = g;

        System.out.println("Original board: " + r + " " + e + " " + g);

        for (int i = g - 1; i >= 0; i--) {

            System.out.println(r + ", " + e + ", " + i);

        } // right


        for (int i = e - 1; i >= 0; i--) {
            if (g > i) {
                g = i;
            }
            System.out.println(r + ", " + i + ", " + g);

        }//middle column

        g = board[2];
        for (int i = r - 1; i >= 1; i--) {
            if (e > i) {
                e = i;
            }
            if (g > i) {
                g = i;
            }
            System.out.println(i + ", " + e + ", " + g);

        }//left column

        //System.out.println("New board: " + board[0] + ", " + board[1] + ", " + board[2]);
    }

}
