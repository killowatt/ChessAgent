import edu.uky.ai.SearchBudgetExceededException;
import edu.uky.ai.chess.Agent;
import edu.uky.ai.chess.state.*;

/**
 * @author William Yates
 */
public class wrya222 extends Agent
{
    public wrya222()
    {
        super("wrya222");
    }

    @Override
    protected State chooseMove(State current)
    {
        ourplayer = current.player;

        State bestState = current.next().iterator().next();
        int bestValue = Integer.MIN_VALUE;

//        // TODO: transposition table
//        int bestdepth = 0;
//        try
//        {
//            for (int depth = 1; depth <= 10; depth++)
//            {
//                State bState = current.next().iterator().next();
//                int bValue = Integer.MIN_VALUE;
//
//                for (State state : current.next()) {
//                    int value = minMax(depth - 1, state, Integer.MIN_VALUE, Integer.MAX_VALUE);
//                    if (value > bestValue) {
//                        bState = state;
//                        bValue = value;
//                    }
//                }
//
//                System.out.println(depth + " was reached");
//                bestValue = bValue;
//                bestState = bState;
//                bestdepth = depth;
//            }
//        }
//        catch (SearchBudgetExceededException e)
//        {
//            System.out.println("caught @ " + bestdepth);
//        }

        final int depth = 2;
        for (State state : current.next())
        {
            int value = minMax(depth - 1, state, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (value > bestValue)
            {
                    bestState = state;
                    bestValue = value;
            }
        }

        return bestState;
    }

    static Player ourplayer;
    private static int minMax(int depth, State state, int alpha, int beta)
    {
        if (depth == 0)
            return getStateValue(state);

        if (state.player == ourplayer)
        {
            int value = Integer.MIN_VALUE;
            for (State next : state.next())
            {
                value = Math.max(value, minMax(depth - 1, next, alpha, beta));
                alpha = Math.max(alpha, value);
                if (alpha >= beta)
                    break;
            }
            return value;
        }
        else
        {
            int value = Integer.MAX_VALUE;
            for (State next : state.next())
            {
                value = Math.min(value, minMax(depth - 1, next, alpha, beta));
                beta = Math.min(beta, value);
                if (alpha >= beta)
                    break;
            }
            return value;
        }

    }

    private static int getStateValue(State state)
    {
        if (state.movesUntilDraw == 0 || (state.over && !state.check))
            return 0;

        if (state.check)
            return state.player != ourplayer ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        int score = 0;
        for (Piece piece : state.board)
        {
            int value = getPieceValue(piece);
            score += piece.player == ourplayer ? value : -value;
        }
        return score;
    }

    private static int getPieceValue(Piece piece)
    {
        return gpv2(piece);
    }

    // no placement bias
    private static int gpv1(Piece piece)
    {
        if (piece instanceof Pawn)
            return 1;
        else if (piece instanceof Knight)
            return 3;
        else if (piece instanceof Bishop)
            return 3;
        else if (piece instanceof Rook)
            return 5;
        else if (piece instanceof Queen)
            return 9;
        else if (piece instanceof King)
            return 90;
        else
            return 0; // Unknown piece
    }

    // using placement bias tables
    private static int gpv2(Piece piece)
    {
        int x = piece.file;
        int y = piece.rank;

        boolean isWhite = piece.player == Player.WHITE;
        isWhite = !isWhite; // ?!?

        if (piece instanceof Pawn)
            return 100 + (isWhite ? PieceSquareTables.pawnTableWhite[y][x] : PieceSquareTables.pawnTableBlack[y][x]);
        else if (piece instanceof Knight)
            return 320 + (isWhite ? PieceSquareTables.knightTableWhite[y][x] : PieceSquareTables.knightTableBlack[y][x]);
        else if (piece instanceof Bishop)
            return 330 + (isWhite ? PieceSquareTables.bishopTableWhite[y][x] : PieceSquareTables.bishopTableBlack[y][x]);
        else if (piece instanceof Rook)
            return 500 + (isWhite ? PieceSquareTables.rookTableWhite[y][x] : PieceSquareTables.rookTableBlack[y][x]);
        else if (piece instanceof Queen)
            return 900 + (isWhite ? PieceSquareTables.queenTableWhite[y][x] : PieceSquareTables.queenTableBlack[y][x]);
        else if (piece instanceof King)
            return 20000 + (isWhite ? PieceSquareTables.kingTableWhite[y][x] : PieceSquareTables.kingTableBlack[y][x]);
        else
            return 0; // Unknown piece
    }
}
