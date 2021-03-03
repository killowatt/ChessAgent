import java.util.Arrays;
import java.util.Collections;

public class PieceSquareTables
{
    private static int[][] reverse(int[][] array)
    {
        int[][] copy = array.clone();
        Collections.reverse(Arrays.asList(copy));
        return copy;
    }

    public static int[][] pawnTableWhite =
            {
                    { 0,  0,  0,  0,  0,  0,  0,  0  },
                    { 50, 50, 50, 50, 50, 50, 50, 50 },
                    { 10, 10, 20, 30, 30, 20, 10, 10 },
                    { 5,  5, 10, 25, 25, 10,  5,  5  },
                    { 0,  0,  0, 20, 20,  0,  0,  0  },
                    { 5, -5,-10,  0,  0,-10, -5,  5  },
                    { 5, 10, 10,-20,-20, 10, 10,  5  },
                    { 0,  0,  0,  0,  0,  0,  0,  0  }
            };

    public static int[][] pawnTableBlack = reverse(pawnTableWhite);

    public static int[][] knightTableWhite =
            {
                    { -50,-40,-30,-30,-30,-30,-40,-50 },
                    { -40,-20,  0,  0,  0,  0,-20,-40 },
                    { -30,  0, 10, 15, 15, 10,  0,-30 },
                    { -30,  5, 15, 20, 20, 15,  5,-30 },
                    { -30,  0, 15, 20, 20, 15,  0,-30 },
                    { -30,  5, 10, 15, 15, 10,  5,-30 },
                    { -40,-20,  0,  5,  5,  0,-20,-40 },
                    { -50,-40,-30,-30,-30,-30,-40,-50 }
            };

    public static int[][] knightTableBlack = reverse(knightTableWhite); // not needed

    public static int[][] bishopTableWhite =
            {
                    { -50,-40,-30,-30,-30,-30,-40,-50 },
                    { -40,-20,  0,  0,  0,  0,-20,-40 },
                    { -30,  0, 10, 15, 15, 10,  0,-30 },
                    { -30,  5, 15, 20, 20, 15,  5,-30 },
                    { -30,  0, 15, 20, 20, 15,  0,-30 },
                    { -30,  5, 10, 15, 15, 10,  5,-30 },
                    { -40,-20,  0,  5,  5,  0,-20,-40 },
                    { -50,-40,-30,-30,-30,-30,-40,-50 },
            };

    public static int[][] bishopTableBlack = reverse(bishopTableWhite);

    public static int[][] rookTableWhite =
            {
                    { 0,  0,  0,  0,  0,  0,  0,  0  },
                    { 5, 10, 10, 10, 10, 10, 10,  5  },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { 0,  0,  0,  5,  5,  0,  0,  0  }
            };

    public static int[][] rookTableBlack = reverse(rookTableWhite);

    public static int[][] queenTableWhite =
            {
                    { -20,-10,-10, -5, -5,-10,-10,-20 },
                    { -10,  0,  0,  0,  0,  0,  0,-10 },
                    { -10,  0,  5,  5,  5,  5,  0,-10 },
                    { -5,  0,  5,  5,  5,  5,  0, -5  },
                    { 0,  0,  5,  5,  5,  5,  0, -5   },
                    { -10,  5,  5,  5,  5,  5,  0,-10 },
                    { -10,  0,  5,  0,  0,  0,  0,-10 },
                    { -20,-10,-10, -5, -5,-10,-10,-20 }
            };

    public static int[][] queenTableBlack = reverse(queenTableWhite); // not needed

    // Mid-game table
    public static int[][] kingTableWhite =
            {
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -20,-30,-30,-40,-40,-30,-30,-20 },
                    { -10,-20,-20,-20,-20,-20,-20,-10 },
                    { 20, 20,  0,  0,  0,  0, 20, 20  },
                    { 20, 30, 10,  0,  0, 10, 30, 20  }
            };

    public static int[][] kingTableBlack = reverse(kingTableWhite);
}
