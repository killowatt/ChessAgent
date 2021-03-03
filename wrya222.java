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
        //if (state.check && !state.next().iterator().hasNext())
        //    return state.player != ourplayer ? 10000 : -100000;

        //if (state.check)
        //    return state.player != ourplayer ? 1000 : -10000;

        if (state.movesUntilDraw == 0 || (state.over && !state.check))
            return 0;

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
        if (piece instanceof Pawn)
            return 100 + (piece.player == Player.BLACK ? PieceSquareTables.pawnTableWhite[piece.rank][piece.file] : PieceSquareTables.pawnTableBlack[piece.rank][piece.file]);
        else if (piece instanceof Knight)
            return 320 + (piece.player == Player.BLACK ? PieceSquareTables.knightTableWhite[piece.rank][piece.file] : PieceSquareTables.knightTableBlack[piece.rank][piece.file]);
        else if (piece instanceof Bishop)
            return 330 + (piece.player == Player.BLACK ? PieceSquareTables.bishopTableWhite[piece.rank][piece.file] : PieceSquareTables.bishopTableBlack[piece.rank][piece.file]);
        else if (piece instanceof Rook)
            return 500 + (piece.player == Player.BLACK ? PieceSquareTables.rookTableWhite[piece.rank][piece.file] : PieceSquareTables.rookTableBlack[piece.rank][piece.file]);
        else if (piece instanceof Queen)
            return 900 + (piece.player == Player.BLACK ? PieceSquareTables.queenTableWhite[piece.rank][piece.file] : PieceSquareTables.queenTableBlack[piece.rank][piece.file]);
        else if (piece instanceof King)
            return 20000 + (piece.player == Player.BLACK ? PieceSquareTables.kingTableWhite[piece.rank][piece.file] : PieceSquareTables.kingTableBlack[piece.rank][piece.file]);
        else
            return 0; // Unknown piece
    }
}
