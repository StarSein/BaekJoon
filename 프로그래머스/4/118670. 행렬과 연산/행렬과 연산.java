import java.util.*;


class Solution {
    
    int[][] rc, answer;
    int rowSize, colSize;
    final int N = 0, NE = 1, E = 2, SE = 3, S = 4, SW = 5, W = 6, NW = 7;
    ArrayDeque<Integer>[] dqOutsides;
    ArrayDeque<ArrayDeque<Integer>> dqInsides;
    
    public int[][] solution(int[][] rc, String[] operations) {
        this.rc = rc;
        
        rowSize = rc.length;
        colSize = rc[0].length;
        
        dqOutsides = new ArrayDeque[8];
        
        dqOutsides[NE] = new ArrayDeque<>(List.of(rc[0][colSize - 1]));
        dqOutsides[SE] = new ArrayDeque<>(List.of(rc[rowSize - 1][colSize - 1]));
        dqOutsides[SW] = new ArrayDeque<>(List.of(rc[rowSize - 1][0]));
        dqOutsides[NW] = new ArrayDeque<>(List.of(rc[0][0]));
        
        dqOutsides[N] = colSize > 2 ? arrToDeque(0, 1, 0, colSize - 2) : new ArrayDeque<>();
        dqOutsides[E] = rowSize > 2 ? arrToDeque(1, colSize - 1, rowSize - 2, colSize - 1) : new ArrayDeque<>();
        dqOutsides[S] = colSize > 2 ? arrToDeque(rowSize - 1, 1, rowSize - 1, colSize - 2) : new ArrayDeque<>();
        dqOutsides[W] = rowSize > 2 ? arrToDeque(rowSize - 2, 0, 1, 0) : new ArrayDeque<>();
        
        dqInsides = new ArrayDeque<>();
        for (int r = 1; r < rowSize - 1; r++) {
            dqInsides.offer(colSize > 2 ? arrToDeque(r, 1, r, colSize - 2) : new ArrayDeque<>());
        }
        
        
        for (String operation : operations) {
            if (operation.charAt(0) == 'S') {
                shiftRow();
            } else {
                rotate();
            }
        }
        
        answer = new int[rowSize][colSize];
        
        answer[0][colSize - 1] = dqOutsides[NE].pollFirst();
        answer[rowSize - 1][colSize - 1] = dqOutsides[SE].pollFirst();
        answer[rowSize - 1][0] = dqOutsides[SW].pollFirst();
        answer[0][0] = dqOutsides[NW].pollFirst();
        dequeToAnswer(dqOutsides[N], 0, 1, 0, colSize - 2);
        dequeToAnswer(dqOutsides[E], 1, colSize - 1, rowSize - 2, colSize - 1);
        dequeToAnswer(dqOutsides[S], rowSize - 1, 1, rowSize - 1, colSize - 2);
        dequeToAnswer(dqOutsides[W], rowSize - 2, 0, 1, 0);
        for (int r = 1; r < rowSize - 1; r++) {
            ArrayDeque<Integer> dqRow = dqInsides.pollFirst();
            dequeToAnswer(dqRow, r, 1, r, colSize - 2);
        }
        
        return answer;
    }
    
    ArrayDeque<Integer> arrToDeque(int sr, int sc, int er, int ec) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        while (true) {
            dq.offerLast(rc[sr][sc]);
            
            if (sr == er && sc == ec) {
                break;
            }
            
            if (sr < er) sr++;
            else if (sr > er) sr--;
            else if (sc < ec) sc++;
            else if (sc > ec) sc--;
        }
        return dq;
    }
    
    void dequeToAnswer(ArrayDeque<Integer> dq, int sr, int sc, int er, int ec) {
        while (!dq.isEmpty()) {
            answer[sr][sc] = dq.pollFirst();
            
            if (sr < er) sr++;
            else if (sr > er) sr--;
            else if (sc < ec) sc++;
            else if (sc > ec) sc--;
        }
    }
    
    void shiftRow() {
        dqOutsides[NW].offerFirst(dqOutsides[SW].pollLast());
        dqOutsides[W].offerLast(dqOutsides[NW].pollLast());
        dqOutsides[SW].offerFirst(dqOutsides[W].pollFirst());
        
        dqOutsides[NE].offerFirst(dqOutsides[SE].pollLast());
        dqOutsides[E].offerFirst(dqOutsides[NE].pollLast());
        dqOutsides[SE].offerFirst(dqOutsides[E].pollLast());
        
        dqInsides.offerFirst(dqOutsides[N]);
        dqOutsides[N] = dqOutsides[S];
        dqOutsides[S] = dqInsides.pollLast();
    }
    
    void rotate() {
        for (int dir = 1; dir < 3; dir++) {
            dqOutsides[dir + 1].offerFirst(dqOutsides[dir].pollLast());
        }
        dqOutsides[4].offerLast(dqOutsides[3].pollLast());
        dqOutsides[5].offerFirst(dqOutsides[4].pollFirst());
        for (int dir = 5; dir < 9; dir++) {
            dqOutsides[(dir + 1) % 8].offerFirst(dqOutsides[dir % 8].pollLast());
        }
    }
}