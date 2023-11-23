class Solution {
    
    class Result {
        int moveCnt;
        boolean aWin;
        
        Result(int moveCnt, boolean aWin) {
            this.moveCnt = moveCnt;
            this.aWin = aWin;
        }
    }
    int rowSize;
    int colSize;
    int[] dr = new int[] {0, 1, 0, -1};
    int[] dc = new int[] {1, 0, -1, 0};
    final int INF_POS = -1;
    final Result A_WIN_RES = new Result(0, true);
    final Result B_WIN_RES = new Result(0, false);
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        rowSize = board.length;
        colSize = board[0].length;
        
        int bitmask = boardToBitmask(board);
        
        Result optimalResult = playTurnA(bitmask, locToPos(aloc), locToPos(bloc));
        return optimalResult.moveCnt;
    }
    
    int boardToBitmask(int[][] board) {
        int ret = 0;
        int pos = 0;
        for (int r = 0; r < rowSize; r++) {
            for (int c = 0; c < colSize; c++) {
                if (board[r][c] == 1) {
                    ret |= 1 << pos;
                }
                pos++;
            }
        }
        return ret;
    }
    
    int locToPos(int[] loc) {
        return loc[0] * colSize + loc[1];
    }
    
    int[] posToLoc(int pos) {
        return new int[] {pos / colSize, pos % colSize};
    }
    
    int moveIfAble(int board, int[] loc, int d) {
        int nr = loc[0] + dr[d];
        int nc = loc[1] + dc[d];
        if (nr < 0 || nr >= rowSize || nc < 0 || nc >= colSize) {
            return INF_POS;
        }
        
        int npos = locToPos(new int[] {nr, nc});
        if ((board & (1 << npos)) == 0) {
            return INF_POS;
        }
        
        return npos;
    }
    
    Result getBetterResult(boolean aTurn, Result curRes, Result newRes) {
        boolean curWin = (aTurn && curRes.aWin) || (!aTurn && !curRes.aWin);
        boolean newWin = (aTurn && newRes.aWin) || (!aTurn && !newRes.aWin);

        if (curWin && newWin) {
            return (curRes.moveCnt < newRes.moveCnt ? curRes : newRes);
        } else if (curWin) {
            return curRes;
        } else if (newWin) {
            return newRes;
        } else {
            return (curRes.moveCnt > newRes.moveCnt ? curRes : newRes);
        }
    }
    
    Result playTurnA(int board, int a, int b) {
        Result ret = B_WIN_RES;
        if ((board & (1 << a)) == 0) {
            return ret;
        }
        
        int[] loc = posToLoc(a);
        for (int d = 0; d < 4; d++) {
            int npos = moveIfAble(board, loc, d);
            if (npos == INF_POS) {
                continue;
            }
            
            Result nextResult = playTurnB(board & ~(1 << a), npos, b);
            ret = getBetterResult(true, ret, new Result(1 + nextResult.moveCnt, nextResult.aWin));
        }
        
        return ret;
    }
    
    Result playTurnB(int board, int a, int b) {
        Result ret = A_WIN_RES;
        if ((board & (1 << b)) == 0) {
            return ret;
        }
        
        int[] loc = posToLoc(b);
        for (int d = 0; d < 4; d++) {
            int npos = moveIfAble(board, loc, d);
            if (npos == INF_POS) {
                continue;
            }
            
            Result nextResult = playTurnA(board & ~(1 << b), a, npos);
            ret = getBetterResult(false, ret, new Result(1 + nextResult.moveCnt, nextResult.aWin));
        }
        
        return ret;
    }
}