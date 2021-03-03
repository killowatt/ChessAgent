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

    private static int getBoardValue(Board board)
    {
        int score = 0;
        for (Piece piece : board)
        {
            int value = getPieceValue(piece);
            score += piece.player == Player.WHITE ? value : -value;
        }
        return score;
    }

    private static int getPieceValue(Piece piece)
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
}
