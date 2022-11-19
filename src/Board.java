import java.awt.*;

public class Board {
    public Point bestMove;
    public int[] boardState;

    public Board(int[] board, Point move) {
        boardState = board;
        bestMove = move;
    }



}
