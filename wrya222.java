import edu.uky.ai.chess.Agent;
import edu.uky.ai.chess.state.*;

class epic
{
    int value;
    State state;

    public epic(int value, State state)
    {
        this.value = value;
        this.state = state;
    }

    static epic max(epic left, epic right)
    {
        if (left.value >= right.value)
            return left;
        else
            return right;
    }

    static epic min(epic left, epic right)
    {
        if (left.value <= right.value)
            return left;
        else
            return right;
    }
}

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

        State bestState = null;
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
        if (state.check && !state.next().iterator().hasNext())
            return state.player != ourplayer ? 10000000 : -10000000;

        if (state.check)
            return state.player != ourplayer ? 10000 : -10000;

        if (depth == 0)
            return getStateValue(state);

        if (state.player == ourplayer)
        {
            int value = Integer.MIN_VALUE;
            for (State next : state.next())
            {
                value = Math.max(value, minMax(depth - 1, next, alpha, beta));
                alpha = Math.max(alpha, value);
                if (beta <= alpha)
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
                if (beta <= alpha)
                    break;
            }
            return value;
        }

    }

    private static epic oldminmax(int depth, State state, int alpha, int beta)
    {
        if (state.check && state.player == Player.WHITE)
            return new epic(10000, state);

        if (state.check && state.player == Player.BLACK)
            return new epic(-10000, state);

        if (state.over || state.movesUntilDraw == 0)
            return new epic(0, state);

        if (depth == 0)
            return new epic(getBoardValue(state.board), state);

        if (state.player == Player.WHITE)
        {
            epic value = new epic(Integer.MIN_VALUE, null);

            for (State next : state.next())
            {
                value = epic.max(value, oldminmax(depth - 1, next, alpha, beta));
                //alpha = Math.max(alpha, value.value);
                //if (alpha >= beta)
                //    break;
            }
            return value;
        }
        else
        {
            epic value = new epic(Integer.MAX_VALUE, null);
            for (State next : state.next())
            {
                value = epic.min(value, oldminmax(depth - 1, next, alpha, beta));
                //beta = Math.min(beta, value.value);
                //if (beta <= alpha)
                //    break;
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
